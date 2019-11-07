package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 数据库表对象
 * @author page
 */
@Data
public class FeedbackRecordDO implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 *主键id
	 */
	private int id;
	/**
	 * 设备号
	 */
	private String equipmentNumber;
	/**
	 *  姓名
	 */
	private String name;
	/**
	 * 地区
	 */
	private String area;
	
	/**
	 * 国家码
	 */
	private String countryCode;
	
	/**
	 * 手机号
	 */
	private String telephone;
	
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 反馈内容
	 */
	private String description;
	/**
	 * 处理状态（0已处理、1待处理)
	 */
	private String status;
	/**
	 * 备注
	 */
	private String remark;
	/**
	 * 创建时间
	 */
	private Date createTime;
	/**
	 * 修改时间
	 */
	private Date updateTime;
	/**
	 * 修改人
	 */
	private String updateUser;
}
