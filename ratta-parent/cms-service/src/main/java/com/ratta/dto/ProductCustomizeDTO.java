package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductCustomizeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "是否启用套餐定制（Y：启用；N：停用）", required = true)
	@NotBlank(message = "是否启用套餐定制不能为空")
	private String flag;

}
