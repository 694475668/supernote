package com.ratta.service.impl;

import com.ratta.entity.user.*;
import com.ratta.service.UserService;
import com.ratta.source.dao.SourceDao;
import com.ratta.target.user.dao.UserTargetDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
/**
* Title:程序的奥秘
* Description:
* Company:  
* @author: 刘 明
* @date:Created in 2019年7月24日 下午2:42:08
* @version : 
*/
@Service
@Slf4j
//指定DataSource1Config里面的事务管理机制
@Transactional(transactionManager="test4TransactionManager",rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	/**
	 * 源数据源
	 */
	@Autowired
	private SourceDao sourceDo;
	
	@Autowired
	private UserTargetDao userTargetDao;
	@Override
	public String dataMataMigration() {
		List<CommonlyAreaDO> queryCommonlyAreaList = sourceDo.queryCommonlyAreaList();
		for (CommonlyAreaDO commonlyAreaDO : queryCommonlyAreaList) {
			userTargetDao.insertCommonlyArea(commonlyAreaDO);
		}
		log.info("迁移表    【t_commonly_area】  成功");

		List<CommonlyEquipmentDO> queryCommonlyEquipmentList = sourceDo.queryCommonlyEquipmentList();
		for (CommonlyEquipmentDO commonlyEquipmentDO : queryCommonlyEquipmentList) {
			userTargetDao.insertCommonlyEquipment(commonlyEquipmentDO);
		}
		log.info("迁移表    【t_commonly_equipment】  成功");

		List<LoginRecordDO> queryLoginRecordList = sourceDo.queryLoginRecordList();
		for (LoginRecordDO loginRecordDO : queryLoginRecordList) {
			userTargetDao.insertLoginRecord(loginRecordDO);
		}
		log.info("迁移表    【t_login_record】  成功");

		List<SensitiveRecordDO> querySensitiveRecordList = sourceDo.querySensitiveRecordList();
		for (SensitiveRecordDO sensitiveRecordDO : querySensitiveRecordList) {
			userTargetDao.insertSensitiveRecord(sensitiveRecordDO);
		}
		log.info("迁移表    【t_sensitive_record】  成功");
		
		List<UserDO> queryUserList = sourceDo.queryUserList();
		for (UserDO userDO : queryUserList) {
			if(userDO.getSex()==null){
				userDO.setSex("");
			}
			userTargetDao.insertUser(userDO);
			System.out.println("创建事件"+userDO.getCreateTime());
		}
		log.info("迁移表    【t_user】  成功");
		return "success";
	}
}
