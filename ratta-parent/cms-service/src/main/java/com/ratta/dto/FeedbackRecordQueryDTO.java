package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 请求数据查询传输
 * @author page
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class FeedbackRecordQueryDTO extends BaseListDTO {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "处理状态（0已处理、1待处理)")
	private String status;

	@ApiModelProperty(value = "设备号")
	private String equipmentNumber;
	
	@ApiModelProperty(value = "手机号")
	private String telephone;
	
	@ApiModelProperty(value = "邮箱")
	private String email;
	
	@ApiModelProperty(value = "反馈内容")
	private String description;
	
	@ApiModelProperty(value = "id")
	private int id;
}
