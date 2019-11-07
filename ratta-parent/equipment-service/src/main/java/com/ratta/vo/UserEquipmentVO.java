package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserEquipmentVO extends BaseVO {

	@ApiModelProperty(value = "设备编号 ")
    private String equipmentNumber;
	@ApiModelProperty(value = "用户ID")
    private Long userId;
	@ApiModelProperty(value = "设备名称")
    private String name;
	@ApiModelProperty(value = "设备状态：Y正常；N锁定")
    private String status;
}
