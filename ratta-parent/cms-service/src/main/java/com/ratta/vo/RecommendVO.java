package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecommendVO implements Serializable {
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "推荐ID")
	private String recommendId;

	@ApiModelProperty(value = "推荐类型 0：supernote产品；1：配件系列")
	private String type;

	@ApiModelProperty(value = "缩略图地址")
	private String recommendUrl;

	@ApiModelProperty(value = "推荐名称")
	private String name;
	
}
