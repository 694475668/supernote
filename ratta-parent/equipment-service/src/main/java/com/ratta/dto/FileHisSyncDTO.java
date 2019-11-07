package com.ratta.dto;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FileHisSyncDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @ApiModelProperty(value = "用户ID", required = true)
    @NotNull(message = "用户ID不能为空")
	private Long userId;

    @ApiModelProperty(value = "设备号", required = true)
    @NotNull(message = "设备号不能为空")
    private String equipmentNo;
}
