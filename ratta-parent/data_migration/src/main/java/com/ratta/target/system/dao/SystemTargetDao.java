package com.ratta.target.system.dao;


import com.ratta.entity.system.DictionaryDO;
import com.ratta.entity.system.ReferenceDO;
import com.ratta.entity.system.ScheduleLogDO;
import com.ratta.entity.system.ScheduleTaskDO;
import com.ratta.vo.DictionaryVO;
import com.ratta.vo.ReferenceVO;
import com.ratta.vo.UserBeanVO;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

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
 * Title:程序的奥秘 Description 目标dao: Company:
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019年7月22日 下午2:24:56
 */
public interface SystemTargetDao {
    /**
     * 添加源库数据字典记录到新库
     *
     * @param dictionaryDO
     * @return
     */
    @Insert("INSERT INTO t_dictionary VALUES(#{id},#{name},#{value},#{valueCn},#{valueEn},#{opUser},#{opTime},#{remark})")
    int insertDictionaryList(DictionaryDO dictionaryDO);

    /**
     * 添加源库参数记录到新库
     *
     * @param referenceDO
     * @return
     */
    @Insert("INSERT INTO t_reference VALUES(#{id},#{serial},#{name},#{value},#{valueCn},#{opUser},#{opTime},#{remark})")
    int insertReferenceList(ReferenceDO referenceDO);

    /**
     * 添加源库调度任务到新库
     *
     * @param scheduleTaskDO
     * @return
     */
    @Insert("INSERT INTO t_schedule_task VALUES(#{id},#{name},#{remark},#{cron},#{status},#{createTime},#{createUser},#{updateTime},#{updateUser},#{bzCode})")
    int insertScheduleTaskList(ScheduleTaskDO scheduleTaskDO);

    /**
     * 添加源库调度日志到新库
     *
     * @param scheduleLogDO
     * @return
     */
    @Insert("INSERT INTO t_schedule_log VALUES (#{id},#{ksrq},#{jsrq},#{taskId},#{result})")
    int insertScheduleLogList(ScheduleLogDO scheduleLogDO);


    /**
     * 查询数据字典
     *
     * @param name
     * @return
     */
    @Select("select t.id,t.name,t.value,t.value_cn as valueCn ,t.value_en as valueEn,t.remark,\n" +
            "        u.name as opUser,t.op_time as opTime from (\n" +
            "        select t.* from (\n" +
            "        select t.* from t_dictionary t\n" +
            "        where 1=1 and name =#{name})t\n" +
            "        )t left join t_user u on u.username=t.op_user")
    List<DictionaryVO> findDictionary(@Param("name") String name);

    /**
     * 查询参数
     *
     * @param name
     * @return
     */
    @Select(" select t.id,t.name,t.serial,t.value,t.value_cn as valueCn,\n" +
            "        u.name as\n" +
            "        opUser,t.op_time as opTime,remark from\n" +
            "        t_reference t left join t_user u on u.username=t.op_user\n" +
            "        where 1=1 and t.`name`=#{name}")
    List<ReferenceVO> findReferenceDO(@Param("name") String name);

    /**
     * 查询用户的所有id
     *
     * @return
     */
    @Select("SELECT DISTINCT id FROM t_user")
    List<UserBeanVO> findUserId();

    /**
     * 根据id查询用户信息
     *
     * @param id
     * @return
     */
    @Select(" select id,username,name,phone,address,email,pwd,counts,status,is_active isActive,last_login_date lastLoginDate,create_time createTime\n" +
            ",create_user createUser,update_time updateTime,update_user updateUser,modify_pwd modifyPwd  from t_user where id=#{id};")
    UserBeanVO getUserById(@Param("id") Long id);

    /**
     * 查询所有不能重复的name
     *
     * @return
     */
    @Select("SELECT DISTINCT name FROM t_reference")
    List<ReferenceVO> findReferenceName();

    /**
     * 查询所有不能重复的name
     *
     * @return
     */
    @Select("SELECT DISTINCT name FROM t_dictionary")
    List<DictionaryVO> findDictionaryName();
}
