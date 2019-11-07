package com.ratta.entity.file;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class FileHisSyncDO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** id */
	private Long id;
	/** fileId */
	private Long fileId;
	/** 用户id */
	private Long userId;
	/** 设备号 */
	private String equipmentNo;
	/** 父目录id */
	private Long directoryId;
	/** 文件名 */
	private String fileName;
	/** 文件在服务器上的名称 */
	private String innerName;
	/** 文件大小 */
	private Integer size;
	/** 文件MD5值 */
	private String md5;
	/** 是否为文件夹 */
	private String isfolder;
	/** 创建时间 */
	private Date createTime;
	/** 修改时间 */
	private Date lastUpdateTime;
	/** 同步时间 */
	private Date syncTime;
	/** 文件的最后修改时间 */
	private Long modifyTime;
	/**
	 * 摸
	 */
	private Long mod;

}