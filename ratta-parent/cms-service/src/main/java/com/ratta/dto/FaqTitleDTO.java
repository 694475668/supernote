package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 请求数据新增传输
 * @author page
 */
@Data
public class FaqTitleDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "0：FAQ，1：故障排除", required = true)
	@NotBlank(message = "类型不能为空")
	private String type;

	@ApiModelProperty(value = "问题标题中文", required = true)
	@NotBlank(message = "问题标题中文不能为空")
	private String titleCn;

	@ApiModelProperty(value = "问题标题英文", required = true)
	@NotBlank(message = "问题标题英文不能为空")
	private String titleEn;
	
	@ApiModelProperty(value = "上线下线")
	private String line;
}
