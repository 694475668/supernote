package com.ratta.dto;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据新增传输
 * @author page
 */
@Data
public class FeedbackRecordPartDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "处理状态（0已处理、1待处理)")
	private String status;

	@ApiModelProperty(value = "备注")
	private String remark;
}
