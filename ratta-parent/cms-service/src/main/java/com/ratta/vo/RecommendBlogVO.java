package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

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
public class RecommendBlogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;


    @ApiModelProperty(value = "(ZH-CN 中文，EN 英文)")
    private String language;
    /**
     * 作者
     */
    @ApiModelProperty(value = "作者")
    private String author;


    /**
     * pc端图片路径
     */
    @ApiModelProperty(value = "pc端图片路径")
    private String urlPc;

    /**
     * 手机端图片路径
     */
    @ApiModelProperty(value = "手机端图片路径")
    private String urlMobile;


    /**
     * 标题中文名称
     */
    @ApiModelProperty(value = "官网需要标题名称")
    private String title;

    /**
     * 内容（中）
     */
    @ApiModelProperty(value = "官网需要内容字段")
    private String content;

    /**
     * （0：上线，1：下线）
     */
    @ApiModelProperty(value = "（0：上线，1：下线）")
    private String line;

    /**
     * 虚假浏览数
     */
    @ApiModelProperty(value = "虚假浏览数")
    private String viewCount;

    /**
     * 真实浏览数
     */
    @ApiModelProperty(value = "真实浏览数")
    private String viewCountReal;

    /**
     * 虚假评论数
     */
    @ApiModelProperty(value = "虚假评论数")
    private String commentsCount;

    /**
     * 真实评论数
     */
    @ApiModelProperty(value = "真实评论数")
    private String commentsCountReal;

    /**
     * 虚假点赞数
     */
    @ApiModelProperty(value = "虚假点赞数")
    private String thumbsCount;

    /**
     * 真实点赞数
     */
    @ApiModelProperty(value = "真实点赞数")
    private String thumbsCountReal;

    /**
     * Y:推荐；N不推荐
     */
    @ApiModelProperty(value = "Y:推荐；N不推荐")
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
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    /**
     * 创建人
     */
    @ApiModelProperty(value = "创建人")
    private String createUser;

    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    /**
     * 修改人
     */
    @ApiModelProperty(value = "修改人")
    private String updateUser;


    /**
     * 推荐文章
     */
    @ApiModelProperty(value = "推荐文章")
    private List<BlogVO> recommendBlogList;
}
