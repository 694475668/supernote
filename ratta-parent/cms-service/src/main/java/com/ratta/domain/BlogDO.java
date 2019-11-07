package com.ratta.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ******Title:<p> 程序的奥秘 </p>******
 * ******Description:<p> </p>******
 * ******Company: <p> 雷塔智能科技有限公司 <p> ******
 *
 * @version :
 * @author: 《刘明》 【QQ 694475668】
 * @date:Created in 2019/9/24 10:38
 */
@Data
public class BlogDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 序号
     */
    private Integer seq;
    /**
     * 作者（
     */
    private String author;

    /**
     * 语言
     */
    private String language;

    /**
     * pc端图片路径
     */
    private String urlPc;

    /**
     * 手机端图片路径
     */
    private String urlMobile;

    /**
     * 标题
     */
    private String title;

    /**
     * 内容
     */
    private String content;

    /**
     * （0：上线，1：下线）
     */
    private String line;

    /**
     * 虚假浏览数
     */
    private String viewCount;

    /**
     * 真实浏览数
     */
    private String viewCountReal;

    /**
     * 虚假评论数
     */
    private String commentsCount;

    /**
     * 真实评论数
     */
    private String commentsCountReal;

    /**
     * 虚假点赞数
     */
    private String thumbsCount;

    /**
     * 真实点赞数
     */
    private String thumbsCountReal;

    /**
     * Y:推荐；N不推荐
     */
    private String recommendFlag;
    /**
	 * seo标题
	 */
	private String seoTitle;
	/**
	 * seo关键字
	 */
	private String seoKeyword;
	/**
	 * seo描述
	 */
	private String seoDescription;
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
