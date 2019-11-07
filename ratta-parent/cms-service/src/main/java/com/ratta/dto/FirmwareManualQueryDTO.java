package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 请求数据查询传输
 * @author page
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FirmwareManualQueryDTO extends BaseListDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "类型（0：说明书；1：固件）")
	private String type;
	
	@ApiModelProperty(value = "产品类型")
	private String productType;

	@ApiModelProperty(value = "上线下线")
	private String line;
}
