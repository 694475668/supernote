package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

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
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class HomeAwardBeanVO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "id")
    private Long id;

    @ApiModelProperty(value = "序号")
    private Integer seq;

	@ApiModelProperty(value = "pc端图片路径中文")
	private String urlPcCn;

	@ApiModelProperty(value = "手机端图片路径中文")
	private String urlMobileCn;

	@ApiModelProperty(value = "pc端图片路径英文")
	private String urlPcEn;

	@ApiModelProperty(value = "手机端图片路径英文")
	private String urlMobileEn;

    @ApiModelProperty(value = "图片中文名称")
    private String nameCn;

    @ApiModelProperty(value = "图片英文名称")
    private String nameEn;

    @ApiModelProperty(value = "图片中文描述")
    private String descriptionCn;

    @ApiModelProperty(value = "图片英文描述")
    private String descriptionEn;

    @ApiModelProperty(value = "颁奖机构中文")
    private String mechanismCn;

    @ApiModelProperty(value = "颁奖机构英文")
    private String mechanismEn;

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
