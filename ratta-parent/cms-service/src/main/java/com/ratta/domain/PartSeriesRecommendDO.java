package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PartSeriesRecommendDO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "ID")
	private Integer id;

	@ApiModelProperty(value = "系列号")
	private String serialNumber;

	@ApiModelProperty(value = "序号")
	private Integer seq;

	@ApiModelProperty(value = "系列中文描述")
	private String recommendPicture;

	@ApiModelProperty(value = "系列英文描述")
	private String recommendUrl;

	@ApiModelProperty(value = "上下线标志(0：上线；1：下线）")
	@NotBlank(message = "上下线标志不能为空")
	private String line;

	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	@ApiModelProperty(value = "创建人")
	private String createUser;

	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	@ApiModelProperty(value = "修改人")
	private String updateUser;

}
