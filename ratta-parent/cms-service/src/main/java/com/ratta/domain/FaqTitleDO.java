package com.ratta.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表对象
 * @author page
 */
@Data
public class FaqTitleDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *主键id
	 */
	private int id;
	
	/**
	 * 序号
	 */
	private Integer seq;
	
	/**
	 * 问题标题中文
	 */
	private String titleCn;
	/**
	 * 问题标题英文
	 */
	private String titleEn;
	/**
	 * 0：FAQ，1：故障排除
	 */
	private String type;
	/**
	 * 上线下线
	 */
	private String line;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 创建人
	 */
	private String createUser;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改人
	 */
	private String updateUser;
	
}
