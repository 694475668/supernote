package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

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
public class HomeBannerVO implements Serializable {
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

    @ApiModelProperty(value = "图片中文名称")
    private String nameCn;

    @ApiModelProperty(value = "官网需要的标题字段")
    private String name;

    @ApiModelProperty(value = "图片英文名称")
    private String nameEn;

    @ApiModelProperty(value = "图片中文描述")
    private String descriptionCn;

    @ApiModelProperty(value = "图片英文描述")
    private String descriptionEn;

    @ApiModelProperty(value = "官网需要的描述字段")
    private String description;

    @ApiModelProperty(value = "上下标志（0：上；1：下）")
    private String type;

    @ApiModelProperty(value = "按钮标志（Y：是；N：否）")
    private String buttonFlag;

	@ApiModelProperty(value = "按钮内外标志（0:内；1：外）")
	private String buttonInnerOut;

	@ApiModelProperty(value = "产品id")
	private String productId;

	@ApiModelProperty(value = "按钮链接中文")
	private String buttonLinkCn;

	@ApiModelProperty(value = "按钮链接英文")
	private String buttonLinkEn;

    @ApiModelProperty(value = "按钮中文名称")
    private String buttonNameCn;

    @ApiModelProperty(value = "按钮英文名称")
    private String buttonNameEn;

    @ApiModelProperty(value = "官网需要的按钮字段")
    private String buttonName;

    @ApiModelProperty(value = "（0：上线，1：下线）")
    private String line;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "创建人")
    private String createUser;

    @ApiModelProperty(value = "修改时间")
    private Date updateTime;

    @ApiModelProperty(value = "修改人")
    private String updateUser;

}
