package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author yll
 *
 */
@Data
public class AboutDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

    private String titleCn;

    private String titleEn;
    
    private String descriptionPcCn;

    private String descriptionPcEn;

    private String descriptionMobileCn;

    private String descriptionMobileEn;

    private String line;

    private Integer seq;

    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}