package com.ratta.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomizeRemarkDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@ApiModelProperty(value = "备注")
	private String remark;
	
	@ApiModelProperty(value = "处理状态")
	private String flag;
	
}
