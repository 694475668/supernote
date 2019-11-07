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
public class CommentVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "序号")
    private Integer seq;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer blogId;


    /**
     * 评论人名称（中）
     */
    @ApiModelProperty(value = "官网需要评论人名称字段")
    private String userName;

    /**
     * 内容
     */
    @ApiModelProperty(value = "官网需要内容字段")
    private String content;

    /**
     * 标志（0：待审核；1：审核通过；2：审核不通过
     */
    @ApiModelProperty(value = "标志（0：待审核；1：审核通过；2：审核不通过")
    private String audit;

    /**
     * （0：置顶，1：不置顶）
     */
    @ApiModelProperty(value = "（0：置顶，1：不置顶）")
    private String roofPlacement;

    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    private Date createTime;


    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date updateTime;


    /**
     * 博客标题
     */
    @ApiModelProperty(value = "博客标题")
    private String title;

}
