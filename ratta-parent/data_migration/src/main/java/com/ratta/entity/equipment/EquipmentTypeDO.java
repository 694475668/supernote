package com.ratta.entity.equipment;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author page
 */
@Data
public class EquipmentTypeDO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;

	/**
	 * 类型
	 */
    private String type;

	/**
	 * 操作人
	 */
    private String opUser;
    /**
	 * 操作时间
	 */
    private Date opTime;
}