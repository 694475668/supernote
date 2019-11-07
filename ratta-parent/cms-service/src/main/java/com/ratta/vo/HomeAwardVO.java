package com.ratta.vo;

import io.swagger.annotations.ApiModelProperty;
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
public class HomeAwardVO implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * id
	 */
	@ApiModelProperty(value = "id")
	private Long id;

	/**
	 * 序号
	 */
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

	@ApiModelProperty(value = "pc端图片路径")
	private String urlPc;

	@ApiModelProperty(value = "手机端图片路径")
	private String urlMobile;
	/**
	 * 图片中文名称
	 */
	@ApiModelProperty(value = "图片中文名称")
	private String nameCn;
	/**
	 * 图片英文名称
	 */
	@ApiModelProperty(value = "图片英文名称")
	private String nameEn;

	/**
	 * 图片中文名称
	 */
	@ApiModelProperty(value = "官网需要图片名称字段")
	private String name;

	/**
	 * 图片中文描述
	 */
	@ApiModelProperty(value = "图片中文描述")
	private String descriptionCn;

	/**
	 * 图片英文描述
	 */
	@ApiModelProperty(value = "图片英文描述")
	private String descriptionEn;

	/**
	 * 描述
	 */
	@ApiModelProperty(value = "官网需要描述字段")
	private String description;

	/**
	 * 颁奖机构中文
	 */
	@ApiModelProperty(value = "颁奖机构中文")
	private String mechanismCn;

	/**
	 * 颁奖机构英文
	 */

	@ApiModelProperty(value = "颁奖机构英文")
	private String mechanismEn;

	/**
	 * 颁奖机构
	 */
	@ApiModelProperty(value = "官网需要颁奖机构字段")
	private String mechanism;

	/**
	 * （0：上线，1：下线）
	 */
	@ApiModelProperty(value = "0：上线，1：下线）")
	private String line;

	/**
	 * 创建时间
	 */
	@ApiModelProperty(value = "创建时间")
	private Date createTime;

	/**
	 * 创建人
	 */
	@ApiModelProperty(value = "创建人")
	private String createUser;

	/**
	 * 修改时间
	 */
	@ApiModelProperty(value = "修改时间")
	private Date updateTime;

	/**
	 * 修改人
	 */
	@ApiModelProperty(value = "修改人")
	private String updateUser;

}
