package com.ratta.vo;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class PictureUploadVO implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "下载地址")
	private String downloadUrl;

}
