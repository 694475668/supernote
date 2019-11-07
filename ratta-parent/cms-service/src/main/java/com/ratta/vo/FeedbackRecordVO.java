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
public class FeedbackRecordVO{
	@ApiModelProperty(value = "id")
	private int id;

	@ApiModelProperty(value = "设备号")
	private String equipmentNumber;

	@ApiModelProperty(value = "姓名")
	private String name;
	
	@ApiModelProperty(value = "地区")
	private String area;
	
	@ApiModelProperty(value = "国家码")
	private String countryCode;
	
	@ApiModelProperty(value = "手机号")
	private String telephone;
	
	@ApiModelProperty(value = "邮箱")
	private String email;
	
	@ApiModelProperty(value = "反馈内容")
	private String description;
	
	@ApiModelProperty(value = "处理状态（0已处理、1待处理)")
	private String status;
	
	@ApiModelProperty(value = "备注")
	private String remark;
	
	@ApiModelProperty(value = "反馈时间")
	private Date createTime;
	
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;
	
	@ApiModelProperty(value = "修改人")
	private String updateUser;
}
