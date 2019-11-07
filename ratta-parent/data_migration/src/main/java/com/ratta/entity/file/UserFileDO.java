package com.ratta.entity.file;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 用户文件表
 * 
 * @author gxy
 *
 */
@Data
public class UserFileDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * 文件id
	 */
	private Long id;
	/**
	 * 用户id
	 */
	private Long userId;
	/**
	 * 父目录id
	 */
	private Long directoryId;
	/**
	 * 文件名
	 */
	private String fileName;
	/**
	 * 文件内部名称
	 */
	private String innerName;
	/**
	 * 文件大小
	 */
	private Integer size;
	/**
	 * 
	 * 文件md5
	 */
	private String md5;

	/**
	 * 文件活跃状态 Y： 正常 N：被删除到回收站
	 */
	private String isactive;

	/**
	 * 是否文件夹标志
	 */
	private String isfolder;
	/**
	 * 文件记录创建时间
	 */
	private Date createTime;
	/**
	 * 文件最后更新时间
	 */
	private Date lastUpdateTime;
	/**
	 * 
	 * 文件编辑时间
	 */
	private Long modifyTime;
	
	/**
	 * 摸
	 */
	private Long mod;

}