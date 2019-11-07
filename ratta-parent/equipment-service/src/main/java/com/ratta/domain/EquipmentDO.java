package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class EquipmentDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	/* 设备号 */
    private String equipmentNumber;

	/* 固件版本号 */
    private String firmwareVersion;

	/* 更新状态（0 初始版本 1 未更新 2 已更新） */
    private String updateStatus;

	/* 备注 */
    private String remark;

    private Date createTime;

    private Date updateTime;
    
	public EquipmentDO() {
		super();
	}

	public EquipmentDO(String equipmentNumber, String firmwareVersion, String updateStatus) {
		super();
		this.equipmentNumber = equipmentNumber;
		this.firmwareVersion = firmwareVersion;
		this.updateStatus = updateStatus;
	}

}