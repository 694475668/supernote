package com.ratta.vo;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class AboutAllVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "pc端中文描述")
	private String descriptionPcCn;

	@ApiModelProperty(value = "pc端英文描述")
	private String descriptionPcEn;

	@ApiModelProperty(value = "移动端中文描述")
	private String descriptionMobileCn;

	@ApiModelProperty(value = "移动端英文描述")
	private String descriptionMobileEn;

	@ApiModelProperty(value = "详情标题中文")
    private String titleCn;

	@ApiModelProperty(value = "详情标题英文")
    private String titleEn;
	
	@ApiModelProperty(value = "上线、下线标识（0：上线；1：下线）")
    private String line;

	@ApiModelProperty(value = "序号")
    private Integer seq;

	@ApiModelProperty(value = "创建时间")
    private Date createTime;

	@ApiModelProperty(value = "创建人")
    private String createUser;

	@ApiModelProperty(value = "修改时间")
    private Date updateTime;

	@ApiModelProperty(value = "修改人")
    private String updateUser;

}
