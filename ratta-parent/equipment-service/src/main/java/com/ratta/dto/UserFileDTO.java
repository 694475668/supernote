package com.ratta.dto;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class UserFileDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@ApiModelProperty(value = "用户ID", required = true)
	@NotNull(message = "用户ID不能为空")
	private Long userId;
	
	@ApiModelProperty(value = "容量", required = true)
	@NotNull(message = "容量不能为空")
	private Long totalCapacity;
	
	@ApiModelProperty(value = "标签页", required = true)
	@NotNull(message = "标签页不能为空")
	private List<String> label;
}
