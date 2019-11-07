package com.ratta.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductBannerAllVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "pc端图片路径")
    private String urlPc;

	@ApiModelProperty(value = "移动端图片路径")
    private String urlMobile;

	@ApiModelProperty(value = "产品型号(A5、A6)")
    private String type;

	@ApiModelProperty(value = "图片中文标题")
    private String titleCn;

	@ApiModelProperty(value = "图片英文标题")
    private String titleEn;

	@ApiModelProperty(value = "图片中文描述")
    private String descriptionCn;

	@ApiModelProperty(value = "图片英文描述")
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
 
	@ApiModelProperty(value = "上线、下线标识（0：上线；1：下线）")
    private String line;
	
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

	@ApiModelProperty(value = "创建时间")
    private Date createTime;

	@ApiModelProperty(value = "创建人")
    private String createUser;

	@ApiModelProperty(value = "修改时间")
    private Date updateTime;

	@ApiModelProperty(value = "修改人")
    private String updateUser;

}
