package com.ratta.controller;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.dto.CommonScheduleDTO;
import com.ratta.dto.ScheduleLogDTO;
import com.ratta.dto.ScheduleTaskDTO;
import com.ratta.dto.ScheduleTaskVagueDTO;
import com.ratta.service.ScheduleTaskService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.ScheduleLogVO;
import com.ratta.vo.ScheduleTaskBaenVO;
import com.ratta.vo.ScheduleTaskVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:39
 */
@RestController
@Api(description = "任务调度接口集合")
public class ScheduleTaskController {
    @Autowired
    private ScheduleTaskService scheduleTaskService;

    @Autowired
    private HttpServletRequest request;

    /**
     * 查询调度任务信息
     *
     * @param scheduleTaskVagueDTO
     * @return
     */
    @PostMapping("/schedule")
    @ApiOperation("查询调度任务信息接口")
    @ApiImplicitParam(name = "scheduleTaskVagueDTO", dataType = "ScheduleTaskVagueDTO")
    public CommonListVO<ScheduleTaskVO> query(@RequestBody @Valid ScheduleTaskVagueDTO scheduleTaskVagueDTO) {
        return scheduleTaskService.query(scheduleTaskVagueDTO);
    }

    /**
     * 启用|停用定时任务
     *
     * @param commonScheduleDTO
     * @return
     */
    @PutMapping("/schedule/open/close")
    @ApiOperation("启用|停用定时任务接口")
    @ApiImplicitParam(name = "commonScheduleDTO", dataType = "CommonScheduleDTO")
    public BaseVO removeTask(@RequestBody @Valid CommonScheduleDTO commonScheduleDTO) {
        return scheduleTaskService.enabDisabTask(commonScheduleDTO, request.getHeader("User-ID"));
    }

    /**
     * 通过id查询任务调度信息
     *
     * @param id
     * @return
     */
    @GetMapping("/schedule/{id}")
    @ApiOperation("根据ID查询任务信息接口")
    @ApiImplicitParam(name = "id", value = "任务ID")
    public ScheduleTaskBaenVO queryById(@PathVariable Long id) {
        return scheduleTaskService.queryById(id);
    }

    /**
     * 修改任务信息
     *
     * @param scheduleTaskDTO
     * @return
     */
    @PutMapping("/schedule")
    @ApiOperation("修改调度任务信息接口")
    @ApiImplicitParam(name = "scheduleTaskDTO", dataType = "ScheduleTaskDTO")
    BaseVO updateScheduleTask(@RequestBody @Valid ScheduleTaskDTO scheduleTaskDTO) {
        return scheduleTaskService.updateScheduleTask(scheduleTaskDTO, request.getHeader("User-ID"));
    }

    /**
     * 执行定时任务
     *
     * @param id
     * @return
     */
    @GetMapping("/schedule/execute/{id}")
    @ApiOperation("执行定时任务接口")
    @ApiImplicitParam(name = "id", value = "任务ID")
    public BaseVO executeTask(@PathVariable Long id) throws SchedulerException {
        return scheduleTaskService.executeTask(id);
    }

    /**
     * <p>queryLog</p>
     * <p>通过参数查询日志</p>
     *
     * @param scheduleLogDTO
     */
    @PostMapping("/schedule/param")
    @ApiOperation("通过参数查询日志接口")
    @ApiImplicitParam(name = "scheduleLogDTO", dataType = "ScheduleLogDTO")
    public CommonListVO<ScheduleLogVO> queryLogByParam(@RequestBody @Valid ScheduleLogDTO scheduleLogDTO) {
        return scheduleTaskService.queryLogByParam(scheduleLogDTO);
    }
}
