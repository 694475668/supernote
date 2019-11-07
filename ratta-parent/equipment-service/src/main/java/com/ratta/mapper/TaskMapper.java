package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.TaskDO;

@Mapper
public interface TaskMapper {

	List<TaskDO> queryTaskList(@Param("equipmentNumber") String equipmentNumber);
	
	TaskDO queryTask(@Param("equipmentNumber") String equipmentNumber, @Param("taskCode") String taskCode);
	
	int insert(@Param("equipmentNumber") String equipmentNumber, @Param("taskCode") String taskCode);
	
	int delete(@Param("equipmentNumber") String equipmentNumber, @Param("taskCode") String taskCode);
	
}
