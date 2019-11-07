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
public class FirmwareManualPartVO{

	@ApiModelProperty(value = "版本号")
	private String version;
	
	@ApiModelProperty(value = "下载地址中文")
	private String url;
	
	@ApiModelProperty(value = "缩略图路径")
	private String thumbnailUrl;
	
	@ApiModelProperty(value = "标题")
	private String title;
	
	@ApiModelProperty(value = "大小")
	private String size;
	
	@ApiModelProperty(value = "描述")
	private String description;
	
	@ApiModelProperty(value = "更新时间")
	private Date updateTime;
}
