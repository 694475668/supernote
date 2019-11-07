package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class CustomizeAllDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@ApiModelProperty(value = "id")
	private String id;
	
	@ApiModelProperty(value = "用户名")
    private String userName;
	
	@ApiModelProperty(value = "手机号")
    private String telephone;
	
	@ApiModelProperty(value = "邮箱")
    private String email;
	
	@ApiModelProperty(value = "产品类型")
    private String product;
	
	@ApiModelProperty(value = "处理状态")
	private String flag;
	
	@ApiModelProperty(value = "页码", required = true)
	@NotBlank(message = "页码不能为空")
	private String pageNo;
	
	@ApiModelProperty(value = "每页显示的个数", required = true)
	@NotBlank(message = "每页显示的个数不能为空")
	private String pageSize;
}
