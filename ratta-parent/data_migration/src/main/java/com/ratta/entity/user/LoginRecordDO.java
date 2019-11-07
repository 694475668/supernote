package com.ratta.entity.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * 登录操作表（t_login_record）
 * @author yll
 *
 */
@Data
public class LoginRecordDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/** 主键 */
	private Long id;
	/** 用户id */
    private Long userId;
    /** 登录方式（1 手机；2 邮箱；3微信） */
    private String loginMethod;
    /** IP地址 */
    private String ip;
    /** 浏览器名称 */
    private String browser;
    /** 登录设备（  1 网页端云盘；2 APP; 3 终端；4 用户平台） */
    private String equipment;
    /** 登录时间 */
    private Date createTime;
    /** 操作时间 */
    private Date updateTime;
}