package com.ratta.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ratta.domain.TaskHisDO;

@Mapper
public interface TaskHisMapper {

	int insert(TaskHisDO taskHisDO);
}
