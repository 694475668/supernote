package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AboutAllDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "上线、下线标识（0：上线；1：下线）")
    private String line;
	
	@ApiModelProperty(value = "页码", required = true)
	@NotBlank(message = "页码不能为空")
	private String pageNo;
	
	@ApiModelProperty(value = "每页显示的个数", required = true)
	@NotBlank(message = "每页显示的个数不能为空")
	private String pageSize;
}
