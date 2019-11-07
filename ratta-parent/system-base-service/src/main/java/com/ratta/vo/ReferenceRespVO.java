package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

/**
 * @author 刘明
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ReferenceRespVO extends BaseVO {

    private static final long serialVersionUID = 1L;
    /**
     * 参数集合
     */
    @ApiModelProperty(value = "参数集合")
    private List<ReferenceInfoVO> paramList;
    /**
     * 随机数，用于安全登录效验
     */
    @ApiModelProperty(value = "随机数，用于安全登录效验")
    private String random;
}
