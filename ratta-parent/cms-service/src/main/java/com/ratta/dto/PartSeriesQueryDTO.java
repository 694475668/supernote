package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class PartSeriesQueryDTO extends BaseListDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "系列编号")
	private String serialNumber;

	@ApiModelProperty(value = "系列类型(0:笔，1：笔芯，2：封套)")
	private String type;

	@ApiModelProperty(value = "上线、下线标识(0：上线；1：下线)")
	private String line;

}
