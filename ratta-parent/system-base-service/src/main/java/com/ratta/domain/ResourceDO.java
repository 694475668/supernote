package com.ratta.domain;

import lombok.Data;

import java.io.Serializable;

/**
 * @author 刘明
 * 资源表对象Resource
 * 2019-06-06
 */
@Data
public class ResourceDO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 资源ID
     */
    private String id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源备注
     */
    private String remark;

    /**
     * 资源序号
     */
    private Integer seq;

    /**
     * 资源链接
     */
    private String url;

    /**
     * 资源类型原值
     */
    private String tresourcetypeId;

    private String systemId;
    /**
     * 资源创建时间
     */
    private String createTime;
    /**
     * 资源创建人
     */
    private String createUser;
    /**
     * 资源最后更新时间
     */
    private String updateTime;
    /**
     * 资源最后更新人
     */
    private String updateUser;

    /**
     * 父级ID
     */
    private String pid;


    private String pName;

    /**
     * 资源类型
     */
    private DictionaryDO dictionaryDO;
}
