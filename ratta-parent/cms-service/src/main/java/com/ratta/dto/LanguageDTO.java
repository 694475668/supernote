package com.ratta.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class LanguageDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "语言(zh-CN,en)")
	private String language;

}
