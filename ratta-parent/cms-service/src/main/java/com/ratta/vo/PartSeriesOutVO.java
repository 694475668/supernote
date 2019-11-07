package com.ratta.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartSeriesOutVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "系列号")
	private String serialNumber;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "系列名称")
	private String name;

	@ApiModelProperty(value = "系列描述")
	private String description;

	@ApiModelProperty(value = "首页系列描述")
	private String homeDescription;

	@ApiModelProperty(value = "按钮名称")
	private String buttonName;

	@ApiModelProperty(value = "按钮链接")
	private String buttonLink;

	@ApiModelProperty(value = "系列类型(0:笔，1：笔芯，2：封套)")
	private String type;

	@ApiModelProperty(value = "价格")
	private String price;

	@ApiModelProperty(value = "多图地址")
	private String mutiUrl;
	
	@ApiModelProperty(value = "seo标题")
	private String seoTitle;
	
	@ApiModelProperty(value = "seo关键字")
	private String seoKeyword;
	
	@ApiModelProperty(value = "seo描述")
	private String seoDescription;

	@ApiModelProperty(value = "pc内容")
	private String contentPc;

	@ApiModelProperty(value = "手机内容")
	private String contentMobile;

	@ApiModelProperty(value = "推荐集合")
	private List<RecommendVO> recommendVOList;

	@ApiModelProperty(value = "产品型号内容集合")
	private List<ProductVO> productList;

}
