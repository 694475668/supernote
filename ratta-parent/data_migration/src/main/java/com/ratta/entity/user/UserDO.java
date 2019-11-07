package com.ratta.entity.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * '用户表（t_user）'
 * @author yll
 *
 */
@Data
public class UserDO implements Serializable {
	
    /**
	 * 
	 */
	private static final long serialVersionUID = -3343225010285121905L;

	private Long userId;

    private String userName;

    private String countryCode;

    private String telephone;

    private String email;

    private String wechatNo;

    private String sex;

    private String birthday;

    private String personalSign;

    private String hobby;

    private String education;

    private String job;

    private String avatarsUrl;

    private String address;

    private String password;

    private Date createTime;

    private Date updateTime;

    private String isNormal;
}
