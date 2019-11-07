package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author gxy
 *
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PartOutVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "系列号")
	private String serialNumber;

	@ApiModelProperty(value = "产品编号")
	private String productNumber;

	@ApiModelProperty(value = "产品名称")
	private String name;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "配件产品图")
	private String productUrl;

	@ApiModelProperty(value = "配件选配图")
	private String matchUrl;

	@ApiModelProperty(value = "颜色中文")
	private String colourName;

	@ApiModelProperty(value = "色块图")
	private String colourUrl;

	@ApiModelProperty(value = "首页显示（0:是，1：否）")
	private String homeFlag;

	@ApiModelProperty(value = "首页缩略图")
	private String homeUrl;

	@ApiModelProperty(value = "产品类型（0:A5;1:A6;2:全部）")
	private String productType;

}
