package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class QueryUserEquipmentDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "页码", required = true)
	@NotBlank(message = "页码不能为空")
	private String pageNo;
	
	@ApiModelProperty(value = "每页显示的个数", required = true)
	@NotBlank(message = "每页显示的个数不能为空")
	private String pageSize;
	
	@ApiModelProperty(value = "设备号")
	private String equipmentNumber;
	
	@ApiModelProperty(value = "国家码")
	private String countryCode;
	
	@ApiModelProperty(value = "手机号")
	private String telephone;
	
	@ApiModelProperty(value = "邮箱")
	private String email;
}
