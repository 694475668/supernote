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
public class FaqItemVO{

	@ApiModelProperty(value = "id")
	private int id;

	@ApiModelProperty(value = "序号")
	private int seq;
	
	@ApiModelProperty(value = "标题id")
	private int faqTitleId;

	@ApiModelProperty(value = "问题标题中文")
	private String titleCn;
	
	@ApiModelProperty(value = "0：faq；1：故障排除")
	private String type;
	
	@ApiModelProperty(value = "问题中文")
	private String questionCn;
	
	@ApiModelProperty(value = "问题英文")
	private String questionEn;
	
	@ApiModelProperty(value = "答案中文")
	private String answerCn;
	
	@ApiModelProperty(value = "答案英文")
	private String answerEn;
	
	@ApiModelProperty(value = "上线下线")
	private String line;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	
	@ApiModelProperty(value = "修改人")
	private String updateUser;
}
