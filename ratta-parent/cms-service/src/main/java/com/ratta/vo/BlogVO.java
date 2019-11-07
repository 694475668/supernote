package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
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
public class BlogVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "序号")
    private Integer seq;

    @ApiModelProperty(value = "官网需要字段作者")
    private String author;

    @ApiModelProperty(value = "pc端图片路径")
    private String urlPc;

    @ApiModelProperty(value = "手机端图片路径")
    private String urlMobile;

    @ApiModelProperty(value = "官网需要标题名称")
    private String title;

    @ApiModelProperty(value = "官网需要内容字段")
    private String content;

    @ApiModelProperty(value = "(ZH-CN 中文，EN 英文)")
    private String language;

    @ApiModelProperty(value = "（0：上线，1：下线）")
    private String line;

    @ApiModelProperty(value = "虚假浏览数")
    private String viewCount;

    @ApiModelProperty(value = "真实浏览数")
    private String viewCountReal;

    @ApiModelProperty(value = "虚假评论数")
    private String commentsCount;

    @ApiModelProperty(value = "真实评论数")
    private String commentsCountReal;

    @ApiModelProperty(value = "虚假点赞数")
    private String thumbsCount;

    @ApiModelProperty(value = "真实点赞数")
    private String thumbsCountReal;

    @ApiModelProperty(value = "Y:推荐；N不推荐")
    private String recommendFlag;
    
    @ApiModelProperty(value = "seo标题")
   	private String seoTitle;
   	
   	@ApiModelProperty(value = "seo关键字")
   	private String seoKeyword;
   	
   	@ApiModelProperty(value = "seo描述")
   	private String seoDescription;
   	
    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateUser;

}
