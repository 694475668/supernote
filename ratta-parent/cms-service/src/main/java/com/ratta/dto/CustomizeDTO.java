package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomizeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@ApiModelProperty(value = "用户名", required = true)
	@NotBlank(message = "用户名不能为空")
	private String userName;
	
	@ApiModelProperty(value = "地区")
	private String area;
	
	@ApiModelProperty(value = "国家码")
	private String countryCode;
	
	@ApiModelProperty(value = "手机号")
	private String telephone;
	
	@ApiModelProperty(value = "邮箱", required = true)
	@NotBlank(message = "邮箱不能为空")
	private String email;
	
	@ApiModelProperty(value = "问题描述")
	private String description;
	
	@ApiModelProperty(value = "设备型号(A5, A6)", required = true)
	@NotBlank(message = "设备不能为空")
	private String product;
	
	@ApiModelProperty(value = "封套颜色", required = true)
	@NotBlank(message = "封套颜色不能为空")
	private String coverColor;
	
	@ApiModelProperty(value = "笔颜色", required = true)
	@NotBlank(message = "笔颜色不能为空")
	private String penColor;
}
