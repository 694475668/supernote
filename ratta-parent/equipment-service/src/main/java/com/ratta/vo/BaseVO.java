package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 
 * @author gxy
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BaseVO {

	@ApiModelProperty(value = "结果码")
	private boolean success = true;

	@ApiModelProperty(value = "错误码")
	private String errorCode;

	@ApiModelProperty(value = "错误信息")
	private String errorMsg;

}
