package com.ratta.vo;

import java.util.Date;


import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CustomizeAllVO extends BaseVO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "用户名")
    private String userName;

	@ApiModelProperty(value = "设备类型(A5, A6)")
	private String product;
	
	@ApiModelProperty(value = "封套颜色")
	private String coverColor;
	
	@ApiModelProperty(value = "笔颜色")
	private String penColor;

	@ApiModelProperty(value = "备注")
    private String remark;
    
	@ApiModelProperty(value = "地区")
    private String area;
	
	@ApiModelProperty(value = "国家码")
    private String countryCode;
	
	@ApiModelProperty(value = "手机号")
    private String telephone;

	@ApiModelProperty(value = "邮箱")
    private String email;
    
	@ApiModelProperty(value = "描述")
	private String description;
	    
	@ApiModelProperty(value = "处理状态")
	private String flag;
	
	@ApiModelProperty(value = "定制时间")
    private Date createTime;

	@ApiModelProperty(value = "备注时间")
    private Date remarkTime;
    
	@ApiModelProperty(value = "备注人")
    private String remarkUser;

}
