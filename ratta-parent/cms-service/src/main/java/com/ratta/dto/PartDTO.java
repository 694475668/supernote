package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author gxy
 *
 */
@Data
public class PartDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "系列号", required = true)
	@NotBlank(message = "系列号不能为空")
	private String serialNumber;

	@ApiModelProperty(value = "产品编号", required = true)
	@NotBlank(message = "产品编号不能为空")
	private String productNumber;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "配件产品图", required = true)
	@NotBlank(message = "配件产品图不能为空")
	private String productUrl;

	@ApiModelProperty(value = "配件选配图", required = true)
	@NotBlank(message = "配件选配图不能为空")
	private String matchUrl;

	@ApiModelProperty(value = "颜色名称中文", required = true)
	@NotBlank(message = "颜色名称中文不能为空")
	private String colourNameCn;

	@ApiModelProperty(value = "颜色名称英文", required = true)
	@NotBlank(message = "颜色名称英文不能为空")
	private String colourNameEn;

	@ApiModelProperty(value = "色块图", required = true)
	@NotBlank(message = "色块图不能为空")
	private String colourUrl;

	@ApiModelProperty(value = "首页显示（0:是，1：否）", required = true)
	@NotBlank(message = "首页显示不能为空")
	private String homeFlag;

	@ApiModelProperty(value = "首页缩略图")
	private String homeUrl;

	@ApiModelProperty(value = "产品类型（1:A5;2:A6）", required = true)
	private String productType;

	@ApiModelProperty(value = "上下线标志(0：上线；1：下线）", required = true)
	@NotBlank(message = "上下线标志不能为空")
	private String line;

}
