package com.ratta.entity.user;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * '登录操作表（t_commonly_area）'
 * @author yll
 *
 */
@Data
public class CommonlyAreaDO implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1949093927484704305L;

	private Long id;

    private Long userId;

    private String countryCode;

    private String areaCode;

    private Integer count;

    private Date createTime;

    private Date updateTime;

}