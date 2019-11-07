package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AboutDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    
	@ApiModelProperty(value = "pc端中文描述", required = true)
	@NotBlank(message = "pc端中文描述不能为空")
	private String descriptionPcCn;
	
	@ApiModelProperty(value = "pc端英文描述", required = true)
	@NotBlank(message = "pc端英文描述不能为空")
	private String descriptionPcEn;
	
	@ApiModelProperty(value = "移动端中文描述", required = true)
	@NotBlank(message = "移动端中文描述不能为空")
	private String descriptionMobileCn;
	
	@ApiModelProperty(value = "移动端英文描述", required = true)
	@NotBlank(message = "移动端英文描述不能为空")
	private String descriptionMobileEn;
	
	@ApiModelProperty(value = "详情名称中文", required = true)
	@NotBlank(message = "详情名称中文不能为空")
    private String titleCn;
	
	@ApiModelProperty(value = "详情名称英文", required = true)
	@NotBlank(message = "详情名称英文不能为空")
    private String titleEn;
	
	@ApiModelProperty(value = "序号")
    private Integer seq;

}
