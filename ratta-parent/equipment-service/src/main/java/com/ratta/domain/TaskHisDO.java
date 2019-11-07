package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class TaskHisDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long taskId;

    private String equipmentNumber;

    private String taskCode;

    private String result;

    private Date createTime;

}