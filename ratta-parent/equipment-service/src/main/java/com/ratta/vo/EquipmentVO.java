package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString(callSuper = true)
public class EquipmentVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "设备编号 ")
    private String equipmentNumber;

	@ApiModelProperty(value = "版本号")
    private String firmwareVersion;

	@ApiModelProperty(value = "更新状态（0 初始版本 1 未更新 2 已更新）")
    private String updateStatus;

	@ApiModelProperty(value = "备注")
    private String remark;
}
