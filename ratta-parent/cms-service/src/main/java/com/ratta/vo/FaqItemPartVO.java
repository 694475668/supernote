package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据传输
 * 
 * @author page
 *
 */
@Data
public class FaqItemPartVO{
	@ApiModelProperty(value = "问题")
	private String question;
	
	@ApiModelProperty(value = "答案")
	private String answer;
}
