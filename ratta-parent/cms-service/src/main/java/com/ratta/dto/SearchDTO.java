package com.ratta.dto;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 请求数据查询传输
 * @author page
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class SearchDTO extends BaseListDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "关键字", required = true)
	@NotBlank(message = "关键字不能为空")
	private String key;

	@ApiModelProperty(value = "语言", required = true)
	@NotBlank(message = "语言不能为空")
	private String language;
}
