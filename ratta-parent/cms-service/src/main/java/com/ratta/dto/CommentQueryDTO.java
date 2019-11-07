package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.*;

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
@AllArgsConstructor
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class CommentQueryDTO extends PageDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 语言
     */
    @ApiModelProperty(value = " 语言")
    private String language;

    /**
     * 上下标志（0：上；1：下）
     */
    @ApiModelProperty(value = "cms查询条件（0：置顶，1：不置顶）")
    private String roofPlacement;

    /**
     * 上下标志（0：上；1：下）
     */
    @ApiModelProperty(value = "cms查询条件标志（0：待审核；1：审核通过；2：审核不通过）")
    private String audit;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题id")
    private String titleId;

}
