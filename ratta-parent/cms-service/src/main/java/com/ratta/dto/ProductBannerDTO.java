package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductBannerDTO implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "产品型号（A5，A6）", required = true)
	@NotBlank(message = "产品型号不能为空")
    private String type;
	
	@ApiModelProperty(value = "pc端图片路径", required = true)
	@NotBlank(message = "pc端图片路径不能为空")
    private String urlPc;
	
	@ApiModelProperty(value = "移动端图片路径", required = true)
	@NotBlank(message = "移动端图片路径不能为空")
    private String urlMobile;

	@ApiModelProperty(value = "图片中文标题", required = true)
	@NotBlank(message = "图片中文标题不能为空")
    private String titleCn;

	@ApiModelProperty(value = "图片英文标题", required = true)
	@NotBlank(message = "图片英文标题不能为空")
    private String titleEn;

	@ApiModelProperty(value = "图片中文描述", required = true)
	@NotBlank(message = "图片中文描述不能为空")
    private String descriptionCn;

	@ApiModelProperty(value = "图片英文描述", required = true)
	@NotBlank(message = "图片英文描述不能为空")
    private String descriptionEn;
	
	@ApiModelProperty(value = "图片中文描述详情")
    private String detailCn;
   
	@ApiModelProperty(value = "图片英文描述详情")
    private String detailEn;

	@ApiModelProperty(value = "按钮中文名称")
    private String buttonNameCn;

	@ApiModelProperty(value = "按钮英文名称")
    private String buttonNameEn;
	
	@ApiModelProperty(value = "按钮链接CN")
    private String buttonLinkCn;
	
	@ApiModelProperty(value = "按钮链接EN")
    private String buttonLinkEn;
	
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
