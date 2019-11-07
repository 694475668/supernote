package com.ratta.target.equipment.dao;


import org.apache.ibatis.annotations.Insert;

import com.ratta.entity.equipment.EquipmentDO;
import com.ratta.entity.equipment.EquipmentLogDO;
import com.ratta.entity.equipment.UserEquipmentDO;

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
public interface EquipmentTargetDao {
    /**
     * 添加源库设备记录到新库
     *
     * @param equipmentDO
     * @return
     */
    @Insert("INSERT INTO t_equipment VALUES(#{id},#{equipmentNumber},#{firmwareVersion},#{updateStatus},#{remark},#{createTime},#{updateTime})")
    int insertEquipmentList(EquipmentDO equipmentDO);

    /**
     * 添加源库设备日志记录到新库
     *
     * @param equipmentLogDO
     * @return
     */
    @Insert("INSERT INTO t_equipment_log VALUES(#{id},#{equipmentNumber},#{logName},#{type},#{firmwareVersion},#{createTime},#{remark},#{isDownload},#{flag})")
    int insertEquipmentLogList(EquipmentLogDO equipmentLogDO);

    /**
     * 添加源库用户设备记录到新库
     *
     * @param userEquipmentDO
     * @return
     */
    @Insert("INSERT INTO t_user_equipment VALUES(#{id},#{equipmentNumber},#{userId},#{name},#{status},#{createTime})")
    int insertUserEquipmentList(UserEquipmentDO userEquipmentDO);
}
