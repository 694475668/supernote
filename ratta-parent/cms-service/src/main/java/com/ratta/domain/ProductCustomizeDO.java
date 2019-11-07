package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author yll
 *
 */
@Data
public class ProductCustomizeDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String name;
    
    private String type;

    private String customizeFlag;

    private Date updateTime;

    private String updateUser;
}