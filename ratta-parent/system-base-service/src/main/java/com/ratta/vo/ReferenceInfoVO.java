package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 刘明
 */
@Data
public class ReferenceInfoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 序号
     */
    @ApiModelProperty(value = "序号")
    private String serial;
    /**
     * 参数编码
     */
    @ApiModelProperty(value = "参数编码")
    private String name;
    /**
     * 参数值
     */
    @ApiModelProperty(value = "参数值")
    private String value;

}
