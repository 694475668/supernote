package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class BindStatusVO extends BaseVO {

	
	@ApiModelProperty(value = "绑定状态(true:绑定；false:未绑定)")
	private Boolean bindStatus = false;

}
