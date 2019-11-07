package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "语言(zh-CN:中文；en:英文)", required = true)
	@NotBlank(message = "语言不能为空")
	private String language;
	
	@ApiModelProperty(value = "产品型号(A5；A6)", required = true)
	@NotBlank(message = "产品型号不能为空")
	private String type;
}
