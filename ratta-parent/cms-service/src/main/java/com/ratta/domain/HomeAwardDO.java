package com.ratta.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ******Title:
 * <p>
 * 程序的奥秘
 * </p>
 * ****** ******Description:
 * <p>
 * </p>
 * ****** ******Company:
 * <p>
 * 雷塔智能科技有限公司
 * <p>
 * ******
 *
 * @version :
 * @author: 《刘明》 【QQ 694475668】
 * @date:Created in 2019/9/24 10:38
 */
@Data
public class HomeAwardDO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * id
     */
    private Long id;

    /**
     * 序号
     */
    private Integer seq;

    private String urlPcCn;

    private String urlMobileCn;

    private String urlPcEn;

    private String urlMobileEn;

    /**
     * 图片中文名称
     */
    private String nameCn;
    /**
     * 图片英文名称
     */
    private String nameEn;

    /**
     * 图片中文描述
     */
    private String descriptionCn;

    /**
     * 图片英文描述
     */
    private String descriptionEn;

    /**
     * 颁奖机构中文
     */
    private String mechanismCn;

    /**
     * 颁奖机构英文
     */
    private String mechanismEn;

    /**
     * （0：上线，1：下线）
     */
    private String line;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 修改时间
     */
    private Date updateTime;

    /**
     * 修改人
     */
    private String updateUser;

}
