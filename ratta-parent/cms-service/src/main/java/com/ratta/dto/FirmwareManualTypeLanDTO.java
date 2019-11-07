package com.ratta.dto;

import javax.validation.constraints.NotBlank;

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
public class FirmwareManualTypeLanDTO extends BaseListDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "类型（0：说明书；1：固件）", required = true)
	@NotBlank(message = "类型不能为空")
	private String type;

	@ApiModelProperty(value = "语言", required = true)
	@NotBlank(message = "语言不能为空")
	private String language;
}
