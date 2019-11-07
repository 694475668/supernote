package com.ratta.entity.file;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户表（t_recycle_file）
 * 
 * @author guxiaoyu
 */
@Data
public class RecycleFileDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	private Long fileId;

	/**
	 * 用户id
	 */
	private Long userId;

	/**
	 * 文件名
	 */
	private String fileName;

	/**
	 * 文件大小
	 */
	private Long size;

	/**
	 * 文件夹标志
	 */
	private String isfolder;

	/**
	 * 最后更新时间
	 */
	private Date createTime;
	
	/**
	 * 最后更新时间
	 */
	private Date lastUpdateTime;

	/**
	 * 摸
	 */
	private Long mod;

}
