package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author gxy
 *
 */
@Data
public class PartDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "系列号")
	private String serialNumber;

	@ApiModelProperty(value = "产品编号")
	private String productNumber;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "配件产品图")
	private String productUrl;

	@ApiModelProperty(value = "配件选配图")
	private String matchUrl;

	@ApiModelProperty(value = "颜色名称中文")
	private String colourNameCn;

	@ApiModelProperty(value = "颜色名称英文")
	private String colourNameEn;

	@ApiModelProperty(value = "色块图")
	private String colourUrl;

	@ApiModelProperty(value = "首页显示（0:是，1：否）")
	private String homeFlag;

	@ApiModelProperty(value = "首页缩略图")
	private String homeUrl;

	@ApiModelProperty(value = "产品类型（0:A5;1:A6;2:全部）")
	private String productType;

	@ApiModelProperty(value = "上下线标志(0：上线；1：下线）")
	@NotBlank(message = "上下线标志不能为空")
	private String line;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "创建人")
	private String createUser;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "修改人")
	private String updateUser;

}
