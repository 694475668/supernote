package com.ratta.target.user.dao;


import org.apache.ibatis.annotations.Insert;

import com.ratta.entity.user.CommonlyAreaDO;
import com.ratta.entity.user.CommonlyEquipmentDO;
import com.ratta.entity.user.LoginRecordDO;
import com.ratta.entity.user.SensitiveRecordDO;
import com.ratta.entity.user.UserDO;

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
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019年7月23日 上午9:28:41
 */
public interface UserTargetDao {
    /**
     * 添加源库登录操作表记录到新库
     * @param commonlyAreaDO
     * @return
     */
    @Insert("INSERT INTO t_commonly_area VALUES(#{id},#{userId},#{countryCode},#{areaCode},#{count},#{createTime},#{updateTime})")
    int insertCommonlyArea(CommonlyAreaDO commonlyAreaDO);

    /**
     * 添加源库登录操作表记录到新库
     * @param commonlyEquipmentDO
     * @return
     */
    @Insert("INSERT INTO t_commonly_equipment VALUES(#{id},#{userId},#{equipmentNumber},#{createTime},#{updateTime})")
    int insertCommonlyEquipment(CommonlyEquipmentDO commonlyEquipmentDO);

    /**
     * 添加源库用户设备记录到新库
     * @param loginRecordDO
     * @return
     */
    @Insert("INSERT INTO t_login_record VALUES(#{id},#{userId},#{loginMethod},#{ip},#{browser},#{equipment},#{createTime},#{updateTime})")
    int insertLoginRecord(LoginRecordDO loginRecordDO);


    /**
     * 添加源库  到新库
     * @param sensitiveRecordDO
     * @return
     */
    @Insert("INSERT INTO t_sensitive_record VALUES(#{id},#{userId},#{operateRecord},#{ip},#{createTime},#{updateTime})")
    int insertSensitiveRecord(SensitiveRecordDO sensitiveRecordDO);


    /**
     * 添加源库  到新库
     * @param userDO
     * @return
     */
    @Insert("INSERT INTO t_user VALUES(#{userId},#{userName},#{countryCode},#{telephone},#{email},#{wechatNo},#{sex},#{birthday},#{personalSign},#{hobby},#{education},#{job},#{avatarsUrl},#{address},#{password},#{createTime},#{updateTime},#{isNormal})")
    int insertUser(UserDO userDO);
}



