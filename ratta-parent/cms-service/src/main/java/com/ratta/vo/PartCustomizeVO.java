package com.ratta.vo;

import java.io.Serializable;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 
 * @author gxy
 *
 */
@Data
public class PartCustomizeVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "封套系列")
	private List<PartOutVO> coverPartList;

	@ApiModelProperty(value = "笔系列")
	private List<PartOutVO> penPartList;
}
