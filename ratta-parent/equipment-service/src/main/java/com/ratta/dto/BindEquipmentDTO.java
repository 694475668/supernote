package com.ratta.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class BindEquipmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "设备号", required = true)
	@NotBlank(message = "设备号不能为空")
	private String equipmentNo;

	@ApiModelProperty(value = "账号", required = true)
	@NotNull(message = "账号不能为空")
	private String account;

	@ApiModelProperty(value = "国家码")
    private String countryCode;

	@ApiModelProperty(value = "设备名称", required = true)
	@NotNull(message = "设备名称不能为空")
	private String name;

	@ApiModelProperty(value = "设备总容量", required = true)
	@NotNull(message = "设备总容量不能为空")
	private String totalCapacity;

	@ApiModelProperty(value = "标签页", required = true)
	@NotNull(message = "标签页不能为空")
	private List<String> label;
	
}
