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

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.domain.ScheduleLogDO;
import com.ratta.domain.ScheduleTaskDO;
import com.ratta.domain.UserDO;
import com.ratta.dto.*;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.ScheduleMapper;
import com.ratta.mapper.UserMapper;
import com.ratta.schedule.JobTrigger;
import com.ratta.service.ScheduleTaskService;
import com.ratta.vo.*;
import org.apache.commons.lang3.StringUtils;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-21 9:47
 */
@Service
public class ScheduleTaskServiceImpl implements ScheduleTaskService {

    @Autowired
    private ScheduleMapper scheduleMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JobTrigger jobTrigger;

    /**
     * 查询调度任务信息
     *
     * @param scheduleTaskVagueDTO 查询条件
     * @return
     */
    @Override
    public CommonListVO<ScheduleTaskVO> query(ScheduleTaskVagueDTO scheduleTaskVagueDTO) {
        //创建集合ScheduleTaskVO
        List<ScheduleTaskVO> scheduleTaskVOList = new ArrayList<>();
        //创建RoleListVO对象也就是前端展示数据对象
        CommonListVO<ScheduleTaskVO> commonListVO = new CommonListVO<ScheduleTaskVO>();
        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit 判断排序字段是否有
        if (!"".equals(scheduleTaskVagueDTO.getSortField())) {
            PageHelper.startPage(scheduleTaskVagueDTO.getPageNo(), scheduleTaskVagueDTO.getPageSize(), scheduleTaskVagueDTO.getSortField() + " " + scheduleTaskVagueDTO.getSortRules());
        } else {
            PageHelper.startPage(scheduleTaskVagueDTO.getPageNo(), scheduleTaskVagueDTO.getPageSize());
        }
        List<ScheduleTaskDO> scheduleTaskDOList = scheduleMapper.query(scheduleTaskVagueDTO.getName(), scheduleTaskVagueDTO.getStatus());
        PageInfo<ScheduleTaskDO> uInfo = new PageInfo<>(scheduleTaskDOList);
        //DO转VO
        for (ScheduleTaskDO scheduleTaskDO : scheduleTaskDOList) {
            ScheduleTaskVO scheduleTaskVO = new ScheduleTaskVO();
            BeanUtils.copyProperties(scheduleTaskDO, scheduleTaskVO);
            scheduleTaskVOList.add(scheduleTaskVO);
        }
        //总页数
        commonListVO.setPages(uInfo.getPages());
        //总数据
        commonListVO.setTotal(uInfo.getTotal());

        commonListVO.setVoList(scheduleTaskVOList);
        return commonListVO;
    }

    /**
     * 启用停用定时任务
     *
     * @param commonScheduleDTO
     * @param userId            用户ID
     * @return
     */
    @Override
    public BaseVO enabDisabTask(CommonScheduleDTO commonScheduleDTO, String userId) {
        if (commonScheduleDTO.getIdList().length == 1) {
            ScheduleTaskDO task = scheduleMapper.queryById(Long.parseLong(commonScheduleDTO.getIdList()[0]));
            //任务状态，0 启用，1 停用
            if ("0".equals(commonScheduleDTO.getStatus())) {
                if ("0".equals(task.getStatus())) {
                    return new BaseVO(false, ErrorCodeEnum.E0736.getKey(), ErrorCodeEnum.E0736.getValue());
                }
            } else if ("1".equals(commonScheduleDTO.getStatus())) {
                if ("1".equals(task.getStatus())) {
                    return new BaseVO(false, ErrorCodeEnum.E0737.getKey(), ErrorCodeEnum.E0737.getValue());
                }
            }
        } else if (commonScheduleDTO.getIdList().length > 1) {
            int count = 0;
            for (String id : commonScheduleDTO.getIdList()) {
                ScheduleTaskDO task = scheduleMapper.queryById(Long.parseLong(id));
                //任务状态，0 启用，1 停用
                if ("0".equals(commonScheduleDTO.getStatus())) {
                    if ("0".equals(task.getStatus())) {
                        count++;
                    }
                } else if ("1".equals(commonScheduleDTO.getStatus())) {
                    if ("1".equals(task.getStatus())) {
                        count++;
                    }
                }
            }
            if (count == commonScheduleDTO.getIdList().length) {
                if ("0".equals(commonScheduleDTO.getStatus())) {
                    return new BaseVO(false, ErrorCodeEnum.E0736.getKey(), ErrorCodeEnum.E0736.getValue());
                } else if ("1".equals(commonScheduleDTO.getStatus())) {
                    return new BaseVO(false, ErrorCodeEnum.E0737.getKey(), ErrorCodeEnum.E0737.getValue());
                }
            }
        }

        for (String id : commonScheduleDTO.getIdList()) {
            ScheduleTaskDO task = scheduleMapper.queryById(Long.parseLong(id));
            if (task != null) {
                ScheduleTaskDO scheduleTaskDO = new ScheduleTaskDO();
                //判断是启用还是停用true为启用false为停用
                if ("0".equals(commonScheduleDTO.getStatus())) {
                    scheduleTaskDO.setStatus("0");
                } else if ("1".equals(commonScheduleDTO.getStatus())) {
                    scheduleTaskDO.setStatus("1");
                }
                scheduleTaskDO.setId(task.getId());
                //根据ID查询调度任务信息
                UserDO userById = userMapper.getUserById(Long.valueOf(userId));
                scheduleTaskDO.setUpdateTime(new Date());
                scheduleTaskDO.setUpdateUser(userById.getUsername());
                int row = scheduleMapper.updateScheduleTask(scheduleTaskDO);
                if (row <= 0) {
                    if ("0".equals(commonScheduleDTO.getStatus())) {
                        return new BaseVO(false, ErrorCodeEnum.E0726.getKey(), ErrorCodeEnum.E0726.getValue());
                    } else {
                        return new BaseVO(false, ErrorCodeEnum.E0725.getKey(), ErrorCodeEnum.E0725.getValue());
                    }
                } else {
                    ScheduleTaskDO scheduleTask = scheduleMapper.queryById(Long.parseLong(id));
                    if ("0".equals(commonScheduleDTO.getStatus())) {
                        try {
                            jobTrigger.add(scheduleTask);
                        } catch (SchedulerException e) {
                            e.printStackTrace();
                        }
                    } else {
                        try {
                            jobTrigger.delete(scheduleTask);
                        } catch (SchedulerException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        return new BaseVO();
    }

    /**
     * 通过id查询任务调度信息
     *
     * @param id
     * @return
     */
    @Override
    public ScheduleTaskBaenVO queryById(Long id) {
        ScheduleTaskDO scheduleTaskDO = scheduleMapper.queryById(id);
        ScheduleTaskBaenVO scheduleTaskBaenVO = new ScheduleTaskBaenVO();
        if (scheduleTaskDO != null) {
            BeanUtils.copyProperties(scheduleTaskDO, scheduleTaskBaenVO);
        }
        return scheduleTaskBaenVO;
    }

    /**
     * 修改任务信息
     *
     * @param scheduleTaskDTO
     * @return
     */
    @Override
    public BaseVO updateScheduleTask(ScheduleTaskDTO scheduleTaskDTO, String userId) {
        ScheduleTaskDO scheduleTaskDO = new ScheduleTaskDO();
        ScheduleTaskDO scheduleTaskById = scheduleMapper.queryById(Long.valueOf(scheduleTaskDTO.getId()));
        BeanUtils.copyProperties(scheduleTaskDTO, scheduleTaskDO);
        //设置修改人
        scheduleTaskDO.setUpdateUser(userMapper.getUserById(Long.valueOf(userId)).getUsername());
        scheduleTaskDO.setUpdateTime(new Date());
        int row = scheduleMapper.updateScheduleTask(scheduleTaskDO);
        jobTrigger.update(scheduleTaskById);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E0705.getKey(), ErrorCodeEnum.E0705.getValue());
    }

    /**
     * 执行定时任务
     *
     * @param id
     * @return
     */
    @Override
    public BaseVO executeTask(Long id) {
        try {
            ScheduleTaskDO scheduleTask = scheduleMapper.queryById(id);
            if (id == null) {
                return new BaseVO(false, ErrorCodeEnum.E0728.getKey(), ErrorCodeEnum.E0728.getValue());
            }
            if ("1".equals(scheduleTask.getStatus())) {
                return new BaseVO(false, ErrorCodeEnum.E0738.getKey(), ErrorCodeEnum.E0738.getValue());
            }
            for (ScheduleTaskDO runningTask : jobTrigger.getRunningJob()) {
                if (runningTask.getName().equals(scheduleTask.getName())
                        && Trigger.TriggerState.NORMAL.name().equals(runningTask.getStatus())) {
                    return new BaseVO(false, ErrorCodeEnum.E0727.getKey(), ErrorCodeEnum.E0727.getValue());
                }
            }
            //立即执行任务
            jobTrigger.immediateExecute(scheduleTask);
            return new BaseVO();
        } catch (Exception e) {
            return new BaseVO(false, ErrorCodeEnum.E0729.getKey(), ErrorCodeEnum.E0729.getValue());
        }
    }

    /**
     * <p>queryLog</p>
     * <p>通过参数查询日志</p>
     *
     * @param scheduleLogDTO
     */
    @Override
    public CommonListVO<ScheduleLogVO> queryLogByParam(ScheduleLogDTO scheduleLogDTO) {
        //创建集合ScheduleLogVO
        List<ScheduleLogVO> scheduleLogVOList = new ArrayList<>();
        //创建RoleListVO对象也就是前端展示数据对象
        CommonListVO<ScheduleLogVO> commonListVO = new CommonListVO<ScheduleLogVO>();
        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit 判断排序字段是否有
        if (!"".equals(scheduleLogDTO.getSortField())) {
            PageHelper.startPage(scheduleLogDTO.getPageNo(), scheduleLogDTO.getPageSize(), scheduleLogDTO.getSortField() + " " + scheduleLogDTO.getSortRules());
        } else {
            PageHelper.startPage(scheduleLogDTO.getPageNo(), scheduleLogDTO.getPageSize());
        }

        ScheduleLogDO scheduleLogDO = new ScheduleLogDO();
        BeanUtils.copyProperties(scheduleLogDTO, scheduleLogDO);
        if (!StringUtils.isBlank(scheduleLogDTO.getTaskId())) {
            scheduleLogDO.setTaskId(Long.valueOf(scheduleLogDTO.getTaskId()));
        }
        List<ScheduleLogDO> scheduleLogDOList = scheduleMapper.queryLogByParam(scheduleLogDO);

        PageInfo<ScheduleLogDO> uInfo = new PageInfo<>(scheduleLogDOList);

        //DO转VO
        for (ScheduleLogDO scheduleLog : scheduleLogDOList) {
            ScheduleLogVO scheduleLogVO = new ScheduleLogVO();
            BeanUtils.copyProperties(scheduleLog, scheduleLogVO);
            scheduleLogVO.setJsrq(scheduleLog.getJsrq().substring(0, scheduleLog.getJsrq().lastIndexOf(".")));
            scheduleLogVO.setKsrq(scheduleLog.getKsrq().substring(0, scheduleLog.getKsrq().lastIndexOf(".")));
            scheduleLogVOList.add(scheduleLogVO);
        }
        //总页数
        commonListVO.setPages(uInfo.getPages());
        //总数据
        commonListVO.setTotal(uInfo.getTotal());

        commonListVO.setVoList(scheduleLogVOList);
        return commonListVO;
    }

    /**
     * <p>dataClean</p>
     * <p>数据库定期清理数据</p>
     *
     * @return 1 执行成功  ，0 执行失败
     */
    @Override
    public int dataClean(Long daysBefore) {
        // [数据清理]-清除daysBefore天之前的数据
        int row = scheduleMapper.deleteByDays(daysBefore);
        if (row > 0) {
            return 1;
        }
        return 0;
    }

    /**
     * <p>tokenclean</p>
     * <p>数据库定期清理token表数据</p>
     *
     * @return 1 执行成功  ，0 执行失败
     */
    @Override
    public int tokenclean() {
        return 0;
    }

    /**
     * <p>addLog</p>
     * <p>添加日志记录</p>
     *
     * @param scheduleAddLogDTO 调度任务记录
     * @return 1添加成功  0 添加失败
     */
    @Override
    public int addLog(ScheduleAddLogDTO scheduleAddLogDTO) {
        ScheduleLogDO scheduleLogDO = new ScheduleLogDO();
        scheduleLogDO.setJsrq(scheduleAddLogDTO.getJsrq());
        scheduleLogDO.setKsrq(scheduleAddLogDTO.getKsrq());
        scheduleLogDO.setTaskId(scheduleAddLogDTO.getTaskId());
        scheduleLogDO.setResult(scheduleAddLogDTO.getResult());
        int row = scheduleMapper.saveLog(scheduleLogDO);
        if (row > 0) {
            return 1;
        }
        return 0;
    }
}
