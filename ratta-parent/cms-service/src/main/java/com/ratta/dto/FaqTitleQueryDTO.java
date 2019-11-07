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
public class FaqTitleQueryDTO extends BaseListDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "类型(0：FAQ，1：故障排除)")
	private String type;

	@ApiModelProperty(value = "上线下线")
	private String line;
}
