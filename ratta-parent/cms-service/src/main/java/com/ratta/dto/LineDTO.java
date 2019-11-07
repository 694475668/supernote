package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据新增传输
 * @author page
 */
@Data
public class LineDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "上线下线", required = true)
	@NotBlank(message = "上线下线不能为空")
	private String line;
}
