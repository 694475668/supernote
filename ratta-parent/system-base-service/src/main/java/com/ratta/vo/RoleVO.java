package com.ratta.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 * @author 刘明
 */
@Data
public class RoleVO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 角色ID
     */
    @ApiModelProperty(value = "角色ID")
    private String id;
    /**
     * 角色名称
     */
    @ApiModelProperty(value = "角色名称")
    private String name;
    /**
     * 角色描述
     */
    @ApiModelProperty(value = "角色描述")
    private String remark;

    /**
     * 拥有资源对象
     */
    @ApiModelProperty(value = "所拥有操作资源集合对象")
    private List<ResourceVO> resourceVOList;
}
