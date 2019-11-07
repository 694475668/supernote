package com.ratta.service;

import com.ratta.dto.TaskQueryDTO;
import com.ratta.vo.TaskQueryVO;

public interface TaskService {

	TaskQueryVO queryTask(TaskQueryDTO taskQueryDTO);
	
}
