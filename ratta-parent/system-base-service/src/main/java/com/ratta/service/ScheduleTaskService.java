package com.ratta.service;
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

import org.quartz.SchedulerException;

import com.ratta.dto.CommonScheduleDTO;
import com.ratta.dto.ScheduleAddLogDTO;
import com.ratta.dto.ScheduleLogDTO;
import com.ratta.dto.ScheduleTaskDTO;
import com.ratta.dto.ScheduleTaskVagueDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.ScheduleLogVO;
import com.ratta.vo.ScheduleTaskBaenVO;
import com.ratta.vo.ScheduleTaskVO;


/**
 * ******Title:程序的奥秘 ******
 * ******Description: 任务调度服务******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-21 9:47
 */
public interface ScheduleTaskService {
    /**
     * 查询调度任务信息
     *
     * @param scheduleTaskVagueDTO 查询条件
     * @return
     */
    CommonListVO<ScheduleTaskVO> query(ScheduleTaskVagueDTO scheduleTaskVagueDTO);

    /**
     * 启用|停用定时任务
     *
     * @param commonScheduleDTO
     * @param userId      用户ID
     * @return
     */
    BaseVO enabDisabTask(CommonScheduleDTO commonScheduleDTO, String userId);


    /**
     * 通过id查询任务调度信息
     *
     * @param id
     * @return
     */
    ScheduleTaskBaenVO queryById(Long id);

    /**
     * 修改任务信息
     *
     * @param scheduleTaskDTO
     * @param userId
     * @return
     */
    BaseVO updateScheduleTask(ScheduleTaskDTO scheduleTaskDTO, String userId);

    /**
     * 执行定时任务
     *
     * @param id
     * @return
     */
    BaseVO executeTask(Long id) throws SchedulerException;

    /**
     * <p>queryLog</p>
     * <p>通过参数查询日志</p>
     *
     * @param scheduleLogDTO
     */
    CommonListVO<ScheduleLogVO> queryLogByParam(ScheduleLogDTO scheduleLogDTO);

    /**
     * <p>dataClean</p>
     * <p>数据库定期清理数据</p>
     * @param daysBefore
     * @return 1 执行成功  ，0 执行失败
     */
    int dataClean(Long daysBefore);

    /**
     * <p>tokenclean</p>
     * <p>数据库定期清理token表数据</p>
     *
     * @return 1 执行成功  ，0 执行失败
     */
    int tokenclean();

    /**
     * <p>addLog</p>
     * <p>添加日志记录</p>
     *
     * @param scheduleAddLogDTO 调度任务记录
     * @return 1添加成功  0 添加失败
     */
    int addLog(ScheduleAddLogDTO scheduleAddLogDTO);
}
