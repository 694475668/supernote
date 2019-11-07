package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PartSeriesDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "系列号")
	private String serialNumber;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "系列中文描述")
	private String descriptionCn;

	@ApiModelProperty(value = "系列英文描述")
	private String descriptionEn;

	@ApiModelProperty(value = "首页系列中文描述")
	private String homeDescriptionCn;

	@ApiModelProperty(value = "首页系列英文描述")
	private String homeDescriptionEn;

	@ApiModelProperty(value = "系列类型(0:笔，1：笔芯，2：封套)")
	private String type;

	@ApiModelProperty(value = "价格中文")
	private String priceCn;

	@ApiModelProperty(value = "价格英文")
	private String priceEn;

	@ApiModelProperty(value = "多图地址")
	private String mutiUrl;

	@ApiModelProperty(value = "pc中文内容")
	private String contentPcCn;

	@ApiModelProperty(value = "手机中文内容")
	private String contentMobileCn;

	@ApiModelProperty(value = "pc英文内容")
	private String contentPcEn;

	@ApiModelProperty(value = "按钮中文名称")
	private String buttonNameCn;

	@ApiModelProperty(value = "按钮英文名称")
	private String buttonNameEn;

	@ApiModelProperty(value = "按钮链接CN")
	private String buttonLinkCn;

	@ApiModelProperty(value = "按钮链接EN")
	private String buttonLinkEn;

	@ApiModelProperty(value = "推荐id")
	private String recommendId;

	@ApiModelProperty(value = "推荐图片地址")
	private String recommendUrl;

	@ApiModelProperty(value = "上下线标志(0：上线；1：下线）", required = true)
	@NotBlank(message = "上下线标志不能为空")
	private String line;

	@ApiModelProperty(value = "手机英文内容")
	private String contentMobileEn;
	
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

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "创建人")
	private String createUser;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "修改人")
	private String updateUser;

}
