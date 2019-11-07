package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

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
public class CommentDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private Integer blogId;


    /**
     * 内容（中）
     */
    @ApiModelProperty(value = "内容（中）")
    private String content;

}
