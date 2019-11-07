package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class UserEquipmentInfoDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;

	/* 设备编号 */
    private String equipmentNumber;

    private String userId;

    private String name;

	/* 设备状态：Y正常；N锁定 */
    private String status;

    private String firmwareVersion;
    
    private String updateStatus;
    
    private Date createTime;

}