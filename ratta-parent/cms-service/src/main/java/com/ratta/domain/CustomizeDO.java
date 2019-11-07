package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

@Data
public class CustomizeDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String userName;
    
    private String product;

    private String coverColor;

    private String penColor;

    private String remark;
    
    private String area;
    
    private String countryCode;

    private String telephone;
    
    private String email;
    
    private String description;
    
    private String flag;

    private Date createTime;

    private Date remarkTime;
    
    private String remarkUser;
}