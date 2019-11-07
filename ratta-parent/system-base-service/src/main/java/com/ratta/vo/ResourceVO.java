package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * @author 刘明
 * 资源展示对象
 * 2019-06-06
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class ResourceVO extends BaseVO {
    private static final long serialVersionUID = 1L;
    /**
     * 资源ID
     */
    @ApiModelProperty(value = "资源ID")
    private String id;

    /**
     * 资源名称
     */
    @ApiModelProperty(value = "资源名称")
    private String name;

    /**
     * 资源备注
     */
    @ApiModelProperty(value = "资源备注")
    private String remark;

    /**
     * 资源序号
     */
    @ApiModelProperty(value = "资源序号")
    private Integer seq;

    /**
     * 资源链接
     */
    @ApiModelProperty(value = "资源链接")
    private String url;

    /**
     * 父级ID
     */
    @ApiModelProperty(value = "父级ID")
    private String pid;

    /**
     * 资源类型原值
     */
    @ApiModelProperty(value = "资源类型")
    private String tresourcetypeId;

    /**
     * 系统类型
     */
    @ApiModelProperty(value = "系统类型")
    private String systemId;
    /**
     * 父级name
     */
    @ApiModelProperty(value = "父级ID")
    private String pName;
}
