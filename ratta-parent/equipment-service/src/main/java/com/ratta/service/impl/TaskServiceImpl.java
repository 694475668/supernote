package com.ratta.service.impl;


import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.domain.EquipmentDO;
import com.ratta.domain.TaskDO;
import com.ratta.domain.TaskHisDO;
import com.ratta.dto.FirmwareTaskQueryPartDTO;
import com.ratta.dto.TaskAddDTO;
import com.ratta.dto.TaskDeleteDTO;
import com.ratta.dto.TaskQueryDTO;
import com.ratta.enums.ErrorCodeEnum;
import com.ratta.info.TaskInfo;
import com.ratta.mapper.EquipmentMapper;
import com.ratta.mapper.TaskHisMapper;
import com.ratta.mapper.TaskMapper;
import com.ratta.service.SystemBusinessService;
import com.ratta.service.TaskService;
import com.ratta.stream.EquipmentChannel;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FirmwareTaskPartVO;
import com.ratta.vo.ReferenceVO;
import com.ratta.vo.StockPartVO;
import com.ratta.vo.TaskQueryVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@EnableBinding(EquipmentChannel.class)
public class TaskServiceImpl implements TaskService {

    @Autowired
    private EquipmentMapper equipmentMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TaskHisMapper taskHisMapper;
    @Autowired
    private SystemBusinessService systemBusinessService;
    public static final String N = "N";

    @SuppressWarnings("rawtypes")
	@Autowired
    private RedisTemplate redisTemplate;

    @SuppressWarnings("unchecked")
	@Override
    public TaskQueryVO queryTask(TaskQueryDTO taskQueryDTO) {
        TaskQueryVO taskQueryVO = new TaskQueryVO();
        List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();
        // 查看设备是否已经激活
        EquipmentDO equipmentDO = equipmentMapper.queryEquipment(taskQueryDTO.getEquipmentNo());
        if (equipmentDO == null) {
            taskQueryVO.setSuccess(false);
            return taskQueryVO;
        }

        CommonVO<StockPartVO> commonVO = systemBusinessService.queryByEquipmentNumber(taskQueryDTO.getEquipmentNo());
        if (!commonVO.isSuccess()) {
            taskQueryVO.setSuccess(false);
            taskQueryVO.setErrorCode(ErrorCodeEnum.E0506.getKey());
            taskQueryVO.setErrorMsg(ErrorCodeEnum.E0506.getValue());
            return taskQueryVO;
        }
        // 查询出所有的任务列表
        List<TaskDO> taskList = taskMapper.queryTaskList(taskQueryDTO.getEquipmentNo());
        StockPartVO stockPartVO = commonVO.getVoT();
        TaskDO taskDO = new TaskDO();

        List<FirmwareTaskPartVO> firmwareTaskPartVOList = systemBusinessService.queryByModelAndPurpose(
				new FirmwareTaskQueryPartDTO(stockPartVO.getEquipmentModel(), stockPartVO.getEquipmentPurpose())).getVoList();
        
        if(firmwareTaskPartVOList.size() > 0) {
        	taskDO.setEquipmentNumber(taskQueryDTO.getEquipmentNo());
			taskDO.setTaskCode("03");
			taskList.add(taskDO);
        }
        
        // 查询优先级
        List<ReferenceVO> referenceVOList = (List<ReferenceVO>) redisTemplate.opsForList().range("TASK_PRIORITY", 0, -1);
        for (TaskDO task : taskList) {
            TaskInfo taskInfo = new TaskInfo();
            BeanUtils.copyProperties(task, taskInfo);
            for (ReferenceVO paramInfo : referenceVOList) {
                if (paramInfo.getSerial().equals(task.getTaskCode())) {
                    taskInfo.setTaskPriority(paramInfo.getValue());
                }
            }
            taskInfo.setEquipmentNo(taskQueryDTO.getEquipmentNo());
            taskInfoList.add(taskInfo);
        }
        taskQueryVO.setTaskInfoList(taskInfoList);
        taskQueryVO.setSuccess(true);
        taskQueryVO.setEquipmentNo(taskQueryDTO.getEquipmentNo());
        return taskQueryVO;
    }

    @StreamListener(value = EquipmentChannel.SYSTEM_BUSINESS_INPUT, condition = "headers['task']=='add'")
    public void addTask(@Payload TaskAddDTO taskAddDTO) {
        // 查询任务是否已经存在
        TaskDO taskDO = taskMapper.queryTask(taskAddDTO.getEquipmentNumber(), taskAddDTO.getTaskCode());
        if (taskDO != null) {
            // 删除任务
            taskMapper.delete(taskAddDTO.getEquipmentNumber(), taskAddDTO.getTaskCode());
            // 添加历史任务
            TaskHisDO taskHisDO = new TaskHisDO();
            BeanUtils.copyProperties(taskDO, taskHisDO);
            taskHisDO.setResult("0");
            taskHisMapper.insert(taskHisDO);
        }
        taskMapper.insert(taskAddDTO.getEquipmentNumber(), taskAddDTO.getTaskCode());
    }

    @Transactional
    @StreamListener(value = EquipmentChannel.SYSTEM_BUSINESS_INPUT, condition = "headers['task']=='delete'")
    public void deleteTask(@Payload TaskDeleteDTO taskDeleteDTO) {
        TaskDO taskDO = taskMapper.queryTask(taskDeleteDTO.getEquipmentNumber(), taskDeleteDTO.getTaskCode());
        if (taskDO == null) {
            log.error("设备:{}不存在任务号为{} 的任务", taskDeleteDTO.getEquipmentNumber(), taskDeleteDTO.getTaskCode());
            return;
        }
        // 删除任务
        taskMapper.delete(taskDeleteDTO.getEquipmentNumber(), taskDeleteDTO.getTaskCode());
        // 添加历史任务
        TaskHisDO taskHisDO = new TaskHisDO();
        BeanUtils.copyProperties(taskDO, taskHisDO);
        taskHisDO.setResult(taskDeleteDTO.getResult());
        taskHisMapper.insert(taskHisDO);
    }
    
    public long splitVersion(String version) {
    	String fOld[] = version.split("\\.");
		return Long.valueOf(fOld[2].substring(0, 9)) + Long.valueOf(fOld[3].substring(0, 3));
    }
    
}
