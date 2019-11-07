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
public class FeedbackRecordDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "设备号", required = true)
	@NotBlank(message = "设备号不能为空")
	private String equipmentNumber;

	@ApiModelProperty(value = "姓名", required = true)
	@NotBlank(message = "姓名不能为空")
	private String name;

	@ApiModelProperty(value = "地区")
	private String area;
	
	@ApiModelProperty(value = "国家码")
	private String countryCode;
	
	@ApiModelProperty(value = "手机号")
	private String telephone;
	
	@ApiModelProperty(value = "邮箱", required = true)
	@NotBlank(message = "邮箱不能为空")
	private String email;
	
	@ApiModelProperty(value = "反馈内容", required = true)
	@NotBlank(message = "反馈内容不能为空")
	private String description;
}
