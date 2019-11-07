package com.ratta.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 数据库表对象
 * @author page
 */
@Data
public class FirmwareManualDO implements Serializable {

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
	 * 类型（0：说明书；1：固件）
	 */
	private String type;
	/**
	 * 缩略图路径
	 */
	private String thumbnailUrl;
	
	/**
	 * 上线下线中文
	 */
	private String versionCn;
	
	/**
	 * 上线下线英文
	 */
	private String versionEn;
	
	/**
	 * 大小中文
	 */
	private String sizeCn;
	
	/**
	 * 大小英文
	 */
	private String sizeEn;
	
	/**
	 * 上线下线
	 */
	private String line;
	
	/**
	 * 问题标题中文
	 */
	private String urlCn;
	
	/**
	 * 问题标题中文
	 */
	private String urlEn;
	/**
	 * 问题标题英文
	 */
	private String titleCn;
	
	/**
	 * 问题标题英文
	 */
	private String titleEn;
	
	/**
	 * 问题标题英文
	 */
	private String descriptionCn;
	
	/**
	 * 问题标题英文
	 */
	private String descriptionEn;
	
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
