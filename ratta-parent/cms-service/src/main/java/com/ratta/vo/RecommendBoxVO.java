package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class RecommendBoxVO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "推荐ID")
	private String recommendId;

	@ApiModelProperty(value = "推荐名称")
	private String recommendName;
}
