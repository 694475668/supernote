package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PartQueryDTO extends BaseListDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "系列编号")
	private String serialNumber;

	@ApiModelProperty(value = "产品编号")
	private String productNumber;

	@ApiModelProperty(value = "首页显示标志（0:是，1：否）")
	private String homeFlag;

	@ApiModelProperty(value = "（1:A5;2:A6）")
	private String productType;

	@ApiModelProperty(value = "上线、下线标识(0：上线；1：下线)")
	private String line;

}
