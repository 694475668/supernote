package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ProductVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "产品名称")
	private String productName;

	@ApiModelProperty(value = "产品类型")
	private String productType;

}
