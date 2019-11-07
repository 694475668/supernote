package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author 刘明
 * 资源传输对象
 */
@Data
public class ResourceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称", required = true)
    @NotNull(message = "资源名称不能为空")
    private String name;

    /**
     * 资源地址
     */
    @ApiModelProperty(value = "资源地址")
    private String url;


    /**
     * 资源类型ID
     */
    @ApiModelProperty(value = "资源类型ID")
    private String resourceTypeId;


    /**
     * 资源序号
     */
    @ApiModelProperty(value = "资源序号")
    private Integer seq;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private String pid;


    /**
     * 资源备注
     */
    @ApiModelProperty(value = "资源备注")
    private String remark;

    /**
     * 系统ID
     */
    @ApiModelProperty(value = "系统ID")
    private String systemId;
}
