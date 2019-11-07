package com.ratta.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据查询公共传输属性
 * @author page
 */
@Data
public class BaseListDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "当前页码")
	private int pageNo;
	
	@ApiModelProperty(value = "每页数量")
	private int pageSize;
}
