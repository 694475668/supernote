package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 请求数据新增传输
 * @author page
 */
@Data
public class FirmwareManualDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "类型（0：说明书；1：固件）", required = true)
	@NotBlank(message = "类型不能为空")
	private String type;
	
	@ApiModelProperty(value = "缩略图路径", required = true)
	@NotBlank(message = "缩略图路不能为空")
	private String thumbnailUrl;
	
	@ApiModelProperty(value = "版本号中文", required = true)
	@NotBlank(message = "版本号中文不能为空")
	private String versionCn;
	
	@ApiModelProperty(value = "版本号英文", required = true)
	@NotBlank(message = "版本号英文不能为空")
	private String versionEn;
	
	@ApiModelProperty(value = "上线下线")
	private String line;

	@ApiModelProperty(value = "下载地址中文")
	private String urlCn;
	
	@ApiModelProperty(value = "大小中文", required = true)
	@NotBlank(message = "大小中文不能为空")
	private String sizeCn;
	
	@ApiModelProperty(value = "大小英文", required = true)
	@NotBlank(message = "大小英文不能为空")
	private String sizeEn;

	@ApiModelProperty(value = "下载地址英文")
	private String urlEn;
	
	@ApiModelProperty(value = "标题中文", required = true)
	@NotBlank(message = "标题中文不能为空")
	private String titleCn;

	@ApiModelProperty(value = "标题英文", required = true)
	@NotBlank(message = "标题英文不能为空")
	private String titleEn;
	
	@ApiModelProperty(value = "描述中文", required = true)
	@NotBlank(message = "描述中文不能为空")
	private String descriptionCn;

	@ApiModelProperty(value = "描述英文", required = true)
	@NotBlank(message = "描述英文不能为空")
	private String descriptionEn;
}
