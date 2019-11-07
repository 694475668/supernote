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
public class FaqTitleVO{

	@ApiModelProperty(value = "id")
	private int id;

	@ApiModelProperty(value = "序号")
	private int seq;
	
	@ApiModelProperty(value = "类型(0：FAQ，1：故障排除)")
	private String type;

	@ApiModelProperty(value = "问题标题中文")
	private String titleCn;
	
	@ApiModelProperty(value = "问题标题英文")
	private String titleEn;
	
	@ApiModelProperty(value = "上线下线")
	private String line;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	
	@ApiModelProperty(value = "修改人")
	private String updateUser;
}
