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
public class SearchVO{

	@ApiModelProperty(value = "类型(1:说明书，2:固件，3:FAQ，4：故障排除)")
	private String type;
	
	@ApiModelProperty(value = "id")
	private int id;
	
	@ApiModelProperty(value = "标题")
	private String title;
	
	@ApiModelProperty(value = "描述")
	private String description;
	
	@ApiModelProperty(value = "路径")
	private String url;
}
