package com.ratta.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class QueryUserEquipmentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "用户id")
	private String userId;
	
	@ApiModelProperty(value = "设备号")
	private String equipmentNumber;
	
	@ApiModelProperty(value = "设备名")
	private String name;
	
	@ApiModelProperty(value = "设备版本号")
	private String firmwareVersion;
	
	@ApiModelProperty(value = "设备绑定时间")
	private Date createTime;
	
	@ApiModelProperty(value = "国家码")
	private String countryCode;
	
	@ApiModelProperty(value = "手机号")
	private String telephone;
	
	@ApiModelProperty(value = "邮箱")
	private String email;
	
	@ApiModelProperty(value = "设备状态：Y正常；N锁定")
	private String status;
	
	@ApiModelProperty(value = "更新状态（0 初始版本   1  未更新  2  已更新）")
	private String updateStatus;
	
}
