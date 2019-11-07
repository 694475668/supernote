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


import com.ratta.domain.ScheduleLogDO;
import com.ratta.domain.ScheduleTaskDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 用户映射接口******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:54
 */
public interface ScheduleMapper {

    /**
     * <p>query</p>
     * <p>查询调度任务信息</p>
     *
     * @param name   查询参数
     * @param status 查询参数
     * @return 调度任务列表
     */
    List<ScheduleTaskDO> query(@Param("name") String name, @Param("status") String status);

    /**
     * <p>queryById</p>
     * <p>根据id查询调度任务信息</p>
     *
     * @param id 调度任务id
     * @return 调度任务信息
     */
    ScheduleTaskDO queryById(@Param("id") Long id);

    /**
     * <p>update</p>
     * <p>修改调度任务信息</p>
     *
     * @param scheduleTaskDO 调度任务信息
     * @return 影响数据库行数
     */
    int updateScheduleTask(ScheduleTaskDO scheduleTaskDO);

    /**
     * <p>queryLog</p>
     * <p>通过参数查询日志</p>
     *
     * @param ScheduleLogDO
     */
    List<ScheduleLogDO> queryLogByParam(ScheduleLogDO ScheduleLogDO);

    /**
     * <p>deleteByDays</p>
     * <p>删除记录</p>
     *
     * @param daysBefore 几天前的数据
     * @return 数据库影响行数
     */
    int deleteByDays(@Param("daysBefore") Long daysBefore);

    /**
     * <p>saveLog</p>
     * <p>添加日志</p>
     *
     * @param scheduleLogDO 调度任务日志
     * @return 数据库影响行数
     */
    int saveLog(ScheduleLogDO scheduleLogDO);
}
