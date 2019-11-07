package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ProductDescriptionInfoVO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "详情标题")
    private String title;
	
	@ApiModelProperty(value = "pc端描述")
	private String descriptionPc;

	@ApiModelProperty(value = "移动端描述")
	private String descriptionMobile;

}
