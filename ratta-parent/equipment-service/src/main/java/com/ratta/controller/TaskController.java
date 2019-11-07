package com.ratta.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.dto.TaskQueryDTO;
import com.ratta.service.TaskService;
import com.ratta.vo.TaskQueryVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "任务相关接口")
@RestController
public class TaskController {

	@Autowired
	private TaskService taskQueryService;
	
	@ApiOperation(value = "查询任务")
	@ApiImplicitParam(name = "taskQueryDTO", dataType = "TaskQueryDTO")
	@PostMapping("/query/task") 
	public TaskQueryVO queryTask(@RequestBody @Valid TaskQueryDTO taskQueryDTO) {
		return taskQueryService.queryTask(taskQueryDTO); 
	}
}
