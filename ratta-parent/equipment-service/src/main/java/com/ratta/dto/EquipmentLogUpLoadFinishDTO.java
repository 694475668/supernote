package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EquipmentLogUpLoadFinishDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 设备号
     */
	@ApiModelProperty(value = "设备号", required = true)
	@NotBlank(message = "设备号不能为空")
    private String equipmentNo;
    /**
     * 日志名
     * 
     */
	@ApiModelProperty(value = "日志名", required = true)
	@NotBlank(message = "日志名不能为空")
    private String logName;
    /**
     * 日志类型(1：普通日志；2：错误日志）
     */
	@ApiModelProperty(value = "日志类型(1：普通日志；2：错误日志）", required = true)
	@NotBlank(message = "日志类型不能为空")
    private String type;
    /**
     * 固件版本号
     */
	@ApiModelProperty(value = "固件版本号", required = true)
	@NotBlank(message = "固件版本号不能为空")
    private String firmwareVersion;
}
