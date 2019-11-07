package com.ratta.service.impl;


/*
                                           _ooOoo_
                                          o8888888o
                                          88" . "88
                                          (| -_- |)
                                          O\  =  /O
                                       ____/`---'\____
                                     .'  \\|     |//  `.
                                    /  \\|||  :  |||//  \
                                   /  _||||| -:- |||||-  \
                                   |   | \\\  -  /// |   |
                                   | \_|  ''\---/''  |   |
                                   \  .-\__  `-`  ___/-. /
                                 ___`. .'  /--.--\  `. . __
                              ."" '<  `.___\_<|>_/___.'  >'"".
                             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
                             \  \ `-.   \_ __\ /__ _/   .-` /  /
                        ======`-.____`-.___\_____/___.-`____.-'======
                                           `=---='
                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                                 佛祖保佑       永无BUG
*/

import com.ratta.entity.equipment.EquipmentTypeDO;
import com.ratta.entity.system.DictionaryDO;
import com.ratta.entity.system.ReferenceDO;
import com.ratta.entity.system.ScheduleLogDO;
import com.ratta.entity.system.ScheduleTaskDO;
import com.ratta.service.SystemMataMigrationService;
import com.ratta.source.dao.SourceDao;
import com.ratta.target.system.dao.SystemTargetDao;
import com.ratta.vo.DictionaryVO;
import com.ratta.vo.ReferenceVO;
import com.ratta.vo.UserBeanVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * Title:程序的奥秘 Description: Company:
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019年7月22日 下午2:19:58
 */
@Service
@Slf4j
//指定DataSource1Config里面的事务管理机制
@Transactional(transactionManager = "test2TransactionManager", rollbackFor = Exception.class)
public class SystemMataMigrationServiceImpl implements SystemMataMigrationService {
    /**
     * 源数据源
     */
    @Autowired
    private SourceDao sourceDo;

    /**
     * 目标数据源
     */
    @Autowired
    private SystemTargetDao targetDo;


    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public String dataMataMigration() {

        // 迁移数据字典表
        List<DictionaryDO> queryDictionaryList = sourceDo.queryDictionaryList();
        List<EquipmentTypeDO> equipmenttypeDOList = sourceDo.queryEquipmentType();
        System.out.println("数据" + queryDictionaryList);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (DictionaryDO dictionaryDO : queryDictionaryList) {
            targetDo.insertDictionaryList(dictionaryDO);
        }
        for (EquipmentTypeDO equipmentTypeDO : equipmenttypeDOList) {
            DictionaryDO dictionaryDO = new DictionaryDO();
            dictionaryDO.setName("EQUIPMENT_MODEL");
            dictionaryDO.setValue(equipmentTypeDO.getId().toString());
            dictionaryDO.setValueCn(equipmentTypeDO.getType());
            dictionaryDO.setValueEn(equipmentTypeDO.getType());
            dictionaryDO.setOpUser(equipmentTypeDO.getOpUser());
            dictionaryDO.setOpTime(equipmentTypeDO.getOpTime());
            dictionaryDO.setRemark("设备型号");
            targetDo.insertDictionaryList(dictionaryDO);
        }

        try {
            targetDo.insertDictionaryList(new DictionaryDO("TRESOURCETYPE_ID", "0", "菜单", "menu", "admin", simpleDateFormat.parse("2019-07-22 09:12:15"), "资源类型"));
            targetDo.insertDictionaryList(new DictionaryDO("TRESOURCETYPE_ID", "1", "功能", "function", "admin", simpleDateFormat.parse("2019-07-22 09:12:15"), "资源类型"));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        log.info("迁移表    【t_dictionary】  成功");
        // 迁移参数表
        List<ReferenceDO> queryReferenceList = sourceDo.queryReferenceList();
        for (ReferenceDO referenceDO : queryReferenceList) {
            if ("FILE_TYPE".equals(referenceDO.getName())) {
                continue;
            }
            targetDo.insertReferenceList(referenceDO);
        }

        log.info("迁移表    【t_reference】  成功");
        //迁移调度任务表
        List<ScheduleTaskDO> queryScheduleTaskList = sourceDo.queryScheduleTaskList();
        for (ScheduleTaskDO scheduleTaskDO : queryScheduleTaskList) {
            if ("file_clean".equals(scheduleTaskDO.getBzCode()) || "token_clean".equals(scheduleTaskDO.getBzCode())) {
                continue;
            }
            targetDo.insertScheduleTaskList(scheduleTaskDO);
        }
        log.info("迁移表    【t_schedule_task】  成功");
        // 迁移调度任务日志表
        List<ScheduleLogDO> queryScheduleLogList = sourceDo.queryScheduleLogList();
        for (ScheduleLogDO scheduleLogDO : queryScheduleLogList) {
            targetDo.insertScheduleLogList(scheduleLogDO);
        }

        log.info("迁移表    【t_schedule_log】  成功");

        //把参数信息放redis中
        List<ReferenceVO> referenceName = targetDo.findReferenceName();
        for (ReferenceVO referenceVO : referenceName) {
            List<ReferenceVO> referenceDOList = targetDo.findReferenceDO(referenceVO.getName());
            redisTemplate.opsForList().leftPushAll(referenceVO.getName(), referenceDOList);
        }

        log.info("参数数据添加到redis中成功");

        //把数据字典信息放redis中
        List<DictionaryVO> dictionaryName = targetDo.findDictionaryName();
        for (DictionaryVO dictionary : dictionaryName) {
            List<DictionaryVO> dictionarysVOList = targetDo.findDictionary(dictionary.getName());
            redisTemplate.opsForList().leftPushAll(dictionary.getName(), dictionarysVOList);
        }
        log.info("数据字典数据添加到redis中成功");

        List<UserBeanVO> user = targetDo.findUserId();
        for (UserBeanVO userVO : user) {
            UserBeanVO userBeanVO = targetDo.getUserById(userVO.getId());
            redisTemplate.opsForValue().set("systemUserKey"+userVO.getId().toString(), userBeanVO);
        }
        log.info("用户信息数据添加到redis中成功");
        return "success";
    }
}
