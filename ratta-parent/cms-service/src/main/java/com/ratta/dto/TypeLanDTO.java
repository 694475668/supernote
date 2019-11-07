package com.ratta.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据查询传输
 * @author page
 */
@Data
public class TypeLanDTO{
	@ApiModelProperty(value = "类型（0：FAQ；1：故障排除）", required = true)
	@NotBlank(message = "类型不能为空")
	private String type;

	@ApiModelProperty(value = "语言", required = true)
	@NotBlank(message = "语言不能为空")
	private String language;
}
