package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据传输
 * @author page
 *
 */
@Data
public class OverallSeoPartVO{
	@ApiModelProperty(value = "seo标题")
	private String seoTitle;
	
	@ApiModelProperty(value = "seo关键字")
	private String seoKeyword;
	
	@ApiModelProperty(value = "seo描述")
	private String seoDescription;

}
