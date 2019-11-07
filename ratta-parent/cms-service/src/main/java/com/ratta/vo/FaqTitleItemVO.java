package com.ratta.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 请求数据传输
 * 
 * @author page
 *
 */
@Data
public class FaqTitleItemVO{
	@ApiModelProperty(value = "问题标题")
	private String title;
	
	@ApiModelProperty(value = "条目")
	private List<FaqItemPartVO> itemList;
}
