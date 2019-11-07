package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDescriptionListVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "详情标题")
    private String title;

	private ProductDescriptionInfoVO productDescriptionInfo;
	
	@ApiModelProperty(value = "产品图片路径")
    private String productPictureUrl;
	
}
