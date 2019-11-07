package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class TaskAddDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "设备号", required = true)
	@NotBlank(message = "设备号不能为空")
	private String equipmentNumber;
	@ApiModelProperty(value = "任务码", required = true)
	@NotBlank(message = "任务码不能为空")
	private String taskCode;
}
