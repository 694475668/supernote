package com.ratta.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据传输
 * 
 * @author page
 *
 */
@Data
public class OverallSeoVO{

	@ApiModelProperty(value = "id")
	private int id;
	
	@ApiModelProperty(value = "页面编号")
	private String pageSerial;
	
	@ApiModelProperty(value = "seo标题中文")
	private String seoTitleCn;
	
	@ApiModelProperty(value = "seo标题英文")
	private String seoTitleEn;

	@ApiModelProperty(value = "seo关键字")
	private String seoKeywordCn;
	
	@ApiModelProperty(value = "seo关键字英文")
	private String seoKeywordEn;

	@ApiModelProperty(value = "seo描述")
	private String seoDescriptionCn;

	@ApiModelProperty(value = "seo描述英文")
	private String seoDescriptionEn;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	
	@ApiModelProperty(value = "修改人")
	private String updateUser;
}
