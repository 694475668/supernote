package com.ratta.vo;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据传输
 * 
 * @author page
 *
 */
@Data
public class FirmwareManualVO{

	@ApiModelProperty(value = "id")
	private int id;

	@ApiModelProperty(value = "序号")
	private int seq;
	
	@ApiModelProperty(value = "类型（0：说明书；1：固件）")
	private String type;
	
	@ApiModelProperty(value = "缩略图路径")
	private String thumbnailUrl;
	
	@ApiModelProperty(value = "版本号中文")
	private String versionCn;
	
	@ApiModelProperty(value = "版本号英文")
	private String versionEn;
	
	@ApiModelProperty(value = "上线下线")
	private String line;
	
	@ApiModelProperty(value = "大小中文")
	private String sizeCn;
	
	@ApiModelProperty(value = "大小英文")
	private String sizeEn;

	@ApiModelProperty(value = "下载地址中文")
	private String urlCn;
	
	@ApiModelProperty(value = "下载地址英文")
	private String urlEn;
	
	@ApiModelProperty(value = "标题中文")
	private String titleCn;
	
	@ApiModelProperty(value = "标题英文")
	private String titleEn;
	
	@ApiModelProperty(value = "描述中文")
	private String descriptionCn;
	
	@ApiModelProperty(value = "描述英文")
	private String descriptionEn;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	
	@ApiModelProperty(value = "修改人")
	private String updateUser;
}
