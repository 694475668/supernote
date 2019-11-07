package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据传输
 * 
 * @author page
 *
 */
@Data
public class FaqTitlePartVO{
	@ApiModelProperty(value = "id")
	private int id;

	@ApiModelProperty(value = "问题标题中文")
	private String titleCn;
}
