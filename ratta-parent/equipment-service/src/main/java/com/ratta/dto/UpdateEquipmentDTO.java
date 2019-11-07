package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UpdateEquipmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "设备号", required = true)
	@NotNull(message = "设备号不能为空")
	private String equipmentNumber;
	
	@ApiModelProperty(value = "版本号 (updateStatus=2 时，版本号必须要有值)")
	private String firmwareVersion;
	
	@ApiModelProperty(value = "更新状态（0 初始版本   1  未更新  2  已更新）", required = true)
	@NotNull(message = "更新状态不能为空")
	private String updateStatus;

}
