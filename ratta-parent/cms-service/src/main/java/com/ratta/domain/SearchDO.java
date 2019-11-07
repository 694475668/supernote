package com.ratta.domain;

import java.io.Serializable;

import lombok.Data;

/**
 * 数据库表对象
 * @author page
 */
@Data
public class SearchDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *主键id
	 */
	private int id;
	
	/**
	 * 类型（0：说明书；1：固件）
	 */
	private String type;
	
	/**
	 * 标题
	 */
	private String title;
	
	/**
	 * 描述
	 */
	private String description;
}
