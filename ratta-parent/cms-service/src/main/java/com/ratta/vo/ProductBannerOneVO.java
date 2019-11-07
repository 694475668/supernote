package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ProductBannerOneVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "产品型号(A5、A6)")
	private String type;
	
	@ApiModelProperty(value = "pc端图片路径")
    private String urlPc;
	
	@ApiModelProperty(value = "移动端图片路径")
    private String urlMobile;

	@ApiModelProperty(value = "图片标题")
    private String title;

	@ApiModelProperty(value = "图片描述")
    private String description;
	
	@ApiModelProperty(value = "图片描述详情")
    private String detail;

	@ApiModelProperty(value = "按钮名称")
    private String buttonName;
	
	@ApiModelProperty(value = "按钮链接")
    private String buttonLink;
	
	@ApiModelProperty(value = "seo标题")
	private String seoTitle;
	
	@ApiModelProperty(value = "seo关键字")
	private String seoKeyword;
	
	@ApiModelProperty(value = "seo描述")
	private String seoDescription;
}
