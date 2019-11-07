package com.ratta.domain;

import java.io.Serializable;

import lombok.Data;
import java.util.Date;

@Data
public class TaskDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long taskId;

    private String equipmentNumber;

    private String taskCode;

    private Date createTime;

}