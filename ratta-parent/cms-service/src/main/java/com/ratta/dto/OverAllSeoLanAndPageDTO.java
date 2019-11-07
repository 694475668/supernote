package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class OverAllSeoLanAndPageDTO  implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "页面编号", required = true)
	@NotBlank(message = "页面编号不能为空")
	private String pageSerial;
	
	@ApiModelProperty(value = "语言", required = true)
	@NotBlank(message = "语言不能为空")
	private String language;
}
