package com.ratta.entity.file;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 操作记录表(t_file_action)
 * 
 * @author gxy
 *
 */
@Data
public class FileActionDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/** id */
	private Long id;
	/** 用户id */
	private Long userId;
	/** 文件id */
	private Long fileId;
	/** 文件名 */
	private String name;
	/** 新文件名 */
	private String newName;
	/** 文件路径 */
	private String path;
	/** 新文件路径 */
	private String newPath;
	/** 文件md5 */
	private String md5;
	/** 文件下载地址 */
	private String url;
	/** 是否文件夹标志 */
	private String isfolder;
	/** 文件大小 */
	private Long size;
	/** 操作动作 */
	private String action;
	/** 创建时间 */
	private Date createTime;
	/** 最后更新时间 */
	private Date lastUpdateTime;
	/**
	 * 摸
	 */
	private Long mod;

}
