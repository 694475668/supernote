package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author yll
 *
 */
@Data
public class ProductDescriptionDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    /**
     * ID
     */
	private Integer id;
	/**
     * pc端中文描述
     */
	private String descriptionPcCn;
	/**
     * pc端英文描述
     */
	private String descriptionPcEn;
	/**
     * 移动端中文描述
     */
	private String descriptionMobileCn;
	/**
     * 移动端英文描述
     */
	private String descriptionMobileEn;
    /**
     * 详情名称中文
     */
    private String titleCn;
    /**
     * 详情名称英文
     */
    private String titleEn;
    /**
     * 产品图片路径
     */
    private String productPictureUrl;
    /**
     * A5，A6
     */
    private String type;
    /**
     * 上线、下线标识（0：上线；1：下线）
     */
    private String line;
    /**
     * 序号
     */
    private Integer seq;
    
    private Date createTime;

    private String createUser;

    private Date updateTime;

    private String updateUser;

}