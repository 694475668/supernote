package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 数据库表对象
 * @author page
 */
@Data
public class OverallSeoDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *主键id
	 */
	private int id;
	/**
	 * 页面编号
	 */
	private String pageSerial;
	/**
	 * seo标题中文
	 */
	private String seoTitleCn;
	/**
	 * seo标题英文
	 */
	private String seoTitleEn;
	/**
	 * seo关键字
	 */
	private String seoKeywordCn;
	/**
	 * seo关键字英文
	 */
	private String seoKeywordEn;
	/**
	 * seo描述
	 */
	private String seoDescriptionCn;
	/**
	 * seo描述英文
	 */
	private String seoDescriptionEn;
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
