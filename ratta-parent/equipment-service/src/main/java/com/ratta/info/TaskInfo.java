package com.ratta.info;

import java.io.Serializable;

import lombok.Data;

@Data
public class TaskInfo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 设备号 */
	private String equipmentNo;
	/** 任务码(01:锁设备；02:解锁设备；03:固件更新)*/
	private String taskCode;
	/** 任务内容 */
	private String taskMsg;
	/** 任务优先级 */
	private String taskPriority;
}
