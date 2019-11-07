package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

@Data
public class HomeBannerOutVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "序号")
    private Integer seq;

    @ApiModelProperty(value = "字体颜色")
    private String colorPc;

    @ApiModelProperty(value = "字体颜色")
    private String colorMobile;

    @ApiModelProperty(value = "pc端图片路径")
    private String urlPc;

    @ApiModelProperty(value = "手机端图片路径")
    private String urlMobile;

    @ApiModelProperty(value = "官网需要的标题字段")
    private String nameCn;

    @ApiModelProperty(value = "官网需要的标题字段")
    private String nameEn;

    @ApiModelProperty(value = "官网需要的描述字段")
    private String descriptionCn;

    @ApiModelProperty(value = "官网需要的描述字段")
    private String descriptionEn;

    @ApiModelProperty(value = "上下标志（0：上；1：下）")
    private String type;

    @ApiModelProperty(value = "按钮标志（Y：是；N：否）")
    private String buttonFlag;

    @ApiModelProperty(value = "按钮内外标志（0:内；1：外）")
    private String buttonInnerOut;

    @ApiModelProperty(value = "0：supernote产品；1：配件系列")
    private String buttonType;

    @ApiModelProperty(value = "产品id")
    private String productId;

    @ApiModelProperty(value = "按钮链接")
    private String buttonLinkCn;
    @ApiModelProperty(value = "按钮链接")
    private String buttonLinkEn;

    @ApiModelProperty(value = "官网需要的按钮字段")
    private String buttonNameCn;

    @ApiModelProperty(value = "官网需要的按钮字段")
    private String buttonNameEn;
}
