package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 返回单个对象
 * @author page
 * @param <T>
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CommonVO<T> extends BaseVO{

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "数据对象")
	private T voT;
}
