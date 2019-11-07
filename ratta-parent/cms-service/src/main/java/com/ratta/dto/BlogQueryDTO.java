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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class BlogQueryDTO extends PageDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 语言
     */
    @ApiModelProperty(value = " 语言")
    private String language;


    /**
     * （0：上线，1：下线）
     */
    @ApiModelProperty(value = "cms查询条件0：上线，1：下线）")
    private String line;

    /**
     * 上下标志（0：上；1：下）
     */
    @ApiModelProperty(value = "cms查询条件Y:推荐；N不推荐")
    private String recommendFlag;

    /**
     * 标题
     */
    @ApiModelProperty(value = "标题id")
    private String titleId;
}
