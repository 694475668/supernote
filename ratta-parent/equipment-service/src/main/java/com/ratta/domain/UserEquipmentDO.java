package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserEquipmentDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	/* 设备编号 */
    private String equipmentNumber;

    private Long userId;

    private String name;

	/* 设备状态：Y正常；N锁定 */
    private String status;

    private Date createTime;

	public UserEquipmentDO() {
		super();
	}

	public UserEquipmentDO(String equipmentNumber, Long userId, String name) {
		super();
		this.equipmentNumber = equipmentNumber;
		this.userId = userId;
		this.name = name;
	}

}