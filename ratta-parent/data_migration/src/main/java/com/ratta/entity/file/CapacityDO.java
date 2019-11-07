package com.ratta.entity.file;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 数据库表对象capacity
 * 
 * @author gxy
 *
 */
@Data
public class CapacityDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	private Long id;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 已用容量
	 */
	private Long usedCapacity;

	/**
	 * 总容量
	 */
	private Long totalCapacity;

	/**
	 * 创建时间
	 */
	private Date createTime;

	/**
	 * 更新时间
	 */
	private Date updateTime;
}
