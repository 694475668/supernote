package com.ratta.domain;

import java.io.Serializable;
import java.util.Date;

import lombok.Data;

/**
 * @author yll
 *
 */
@Data
public class ProductBannerDO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
    /**
     * ID
     */
	private Integer id;

    /**
     * pc端图片路径
     */
    private String urlPc;
    /**
     * 移动端图片路径
     */
    private String urlMobile;
    /**
     * A5,A6
     */
    private String type;

    /**
     * 图片中文标题
     */
    private String titleCn;
    /**
     * 图片英文标题
     */
    private String titleEn;
    /**
     * 图片中文描述
     */
    private String descriptionCn;
    /**
     * 图片英文描述
     */
    private String descriptionEn;
    /**
     * 图片中文描述详情
     */
    private String detailCn;
    /**
     * 图片英文描述详情
     */
    private String detailEn;
    /**
     * 按钮中文名称
     */
    private String buttonNameCn;
    /**
     * 按钮英文名称
     */
    private String buttonNameEn;
    /**
     * 按钮链接
     */
    private String buttonLinkCn;
    /**
     * 按钮链接
     */
    private String buttonLinkEn;
    /**
     * 上线、下线标识（0：上线；1：下线）
     */
    private String line;
    /**
	 * seo标题中文
	 */
	private String seoTitleCn;
	/**
	 * seo标题英文
	 */
	private String seoTitleEn;
	/**
	 * seo关键字
	 */
	private String seoKeywordCn;
	/**
	 * seo关键字英文
	 */
	private String seoKeywordEn;
	/**
	 * seo描述
	 */
	private String seoDescriptionCn;
	/**
	 * seo描述英文
	 */
	private String seoDescriptionEn;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人
     */
    private String updateUser;
    
}