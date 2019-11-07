package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
@AllArgsConstructor
@NoArgsConstructor
public class HomeAwardDTO implements Serializable {
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

	@ApiModelProperty(value = "pc端图片路径中文", required = true)
	@NotBlank(message = "pc端图片路径中文不能为空")
	private String urlPcCn;

	@ApiModelProperty(value = "手机端图片路径中文", required = true)
	@NotBlank(message = "手机端图片路径中文不能为空")
	private String urlMobileCn;

	@ApiModelProperty(value = "pc端图片路径英文", required = true)
	@NotBlank(message = "pc端图片路径英文不能为空")
	private String urlPcEn;

	@ApiModelProperty(value = "手机端图片路径英文", required = true)
	@NotBlank(message = "手机端图片路径英文不能为空")
	private String urlMobileEn;

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
	 * （0：上线，1：下线）
	 */
	@ApiModelProperty(value = "0：上线，1：下线）")
	@NotBlank(message = "上下线不能为空")
	private String line;

}
