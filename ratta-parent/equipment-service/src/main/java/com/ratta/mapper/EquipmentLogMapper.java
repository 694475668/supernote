package com.ratta.mapper;
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

import com.ratta.domain.EquipmentLogDO;
import com.ratta.domain.EquipmentLogQueryDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-26 15:24
 */
@Mapper
public interface EquipmentLogMapper {
    /**
     * <p>query</p>
     * <p>根据参数查询设备记日志记录</p>
     *
     * @param equipmentLogQueryDO
     * @return 日志记录列表
     */
    List<EquipmentLogDO> query(EquipmentLogQueryDO equipmentLogQueryDO);

    /**
     * <p>delete</p>
     * <p>根据id查询删除设备日志信息</p>
     *
     * @param id
     * @return
     */
    int delEquipmentLog(@Param("id") Long id);

    /**
     * <p>queryById</p>
     * <p>根据ID查询日志记录</p>
     *
     * @param id id
     * @return
     */
    EquipmentLogDO queryEquipmentLogById(@Param("id") Long id);

    /**
     * <p>updateByParam</p>
     * <p>根据ID更新下载标识、查看标识、备注</p>
     *
     * @param equipmentLogDO id
     *                       flag       查看标识
     *                       sDownload 是否下载标识
     *                       remark   标识备注
     * @return
     */
    int updateEquipment(EquipmentLogDO equipmentLogDO);

    /**
     * <p>queryEquipmentLogByParam</p>
     * <p>根据参数查询日志记录</p>
     *
     * @param id
     * @param logName
     * @return
     */
    EquipmentLogDO queryEquipmentLogByParam(@Param("id") Long id, @Param("logName") String logName);

    /**
     * 添加日志信息
     * @param equipmentLogDO
     * @return
     */
    int savaEquipmentLog(EquipmentLogDO equipmentLogDO);

    /**
     * 查询某个时间点之前的数据
     * @param daysBefore
     * @return
     */
    List<EquipmentLogDO> selectByDays(@Param("daysBefore") Long daysBefore);
}
