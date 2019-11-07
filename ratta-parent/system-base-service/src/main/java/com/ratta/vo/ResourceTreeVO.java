package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 刘明
 * 页面资源树模型
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public class ResourceTreeVO extends BaseVO {

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
    private String label;
    /**
     * 资源状态
     */
    @ApiModelProperty(value = "默认展开")
    private String state = "open";

    @ApiModelProperty(value = "默认不选中")
    private boolean checked = false;
    /**
     * 自定义属性
     */
    @ApiModelProperty(value = "自定义属性")
    private Map<String, Object> attributes = new HashMap<String, Object>();
    /**
     * 下级资源
     */
    @ApiModelProperty(value = "下级资源")
    private List<ResourceTreeVO> children;
    /**
     * 上级资源ID
     */
    @ApiModelProperty(value = "上级资源ID")
    private String pid;

    /**
     * 上级资源名称
     */
//    @ApiModelProperty(value = "上级资源名称")
//    private String pName;
}
