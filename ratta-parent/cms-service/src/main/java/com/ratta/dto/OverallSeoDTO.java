package com.ratta.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据传输
 * @author page
 */
@Data
public class OverallSeoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "页面编号")
	private String pageSerial;
	
	@ApiModelProperty(value = "seo标题中文")
	private String seoTitleCn;
	
	@ApiModelProperty(value = "seo标题英文")
	private String seoTitleEn;
	
	@ApiModelProperty(value = "seo关键字中文")
	private String seoKeywordCn;
	
	@ApiModelProperty(value = "seo关键字英文")
	private String seoKeywordEn;
	
	@ApiModelProperty(value = "seo描述中文")
	private String seoDescriptionCn;
	
	@ApiModelProperty(value = "seo描述英文")
	private String seoDescriptionEn;
}
