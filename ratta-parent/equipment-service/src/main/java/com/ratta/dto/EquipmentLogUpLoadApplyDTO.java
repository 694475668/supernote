package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class EquipmentLogUpLoadApplyDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "设备号", required = true)
    @NotBlank(message = "设备号不能为空")
    private String equipmentNo;
    /**
     * 日志名不能为空
     */
    @ApiModelProperty(value = "日志名", required = true)
    @NotBlank(message = "日志名不能为空")
    private String logName;
}
