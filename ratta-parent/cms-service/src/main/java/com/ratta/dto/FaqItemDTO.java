package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请求数据新增传输
 * @author page
 */
@Data
public class FaqItemDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "FAK标题id", required = true)
	@NotNull(message = "FAK标题id不能为空")
	private int faqTitleId;

	@ApiModelProperty(value = "问题中文", required = true)
	@NotBlank(message = "问题中文不能为空")
	private String questionCn;

	@ApiModelProperty(value = "问题英文", required = true)
	@NotBlank(message = "问题英文不能为空")
	private String questionEn;
	
	@ApiModelProperty(value = "答案中文", required = true)
	@NotBlank(message = "答案中文不能为空")
	private String answerCn;
	
	@ApiModelProperty(value = "答案英文", required = true)
	@NotBlank(message = "答案英文不能为空")
	private String answerEn;
	
	@ApiModelProperty(value = "上线下线")
	private String line;
}
