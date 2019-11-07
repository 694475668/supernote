package com.ratta.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class SendEmailDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value = "收件人")
	private String to;
	
	@ApiModelProperty(value = "邮件头")
	private String subject;
	
	@ApiModelProperty(value = "邮件内容")
	private String content; 
	
	@ApiModelProperty(value = "附件或图片路径")
	private String rscPath;
	
	@ApiModelProperty(value = "图片自定义ID(最好跟图片名一致)")
	private String rscId;

}