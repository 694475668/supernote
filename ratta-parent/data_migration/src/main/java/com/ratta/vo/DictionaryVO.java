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
 * @date:Created in 2019/8/26 11:56
 */
@Data
public class DictionaryVO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 数据ID
     */
    @ApiModelProperty(value = "数据ID")
    private Long id;

    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    private String name;

    /**
     * 数据值
     */
    @ApiModelProperty(value = "数据值")
    private String value;

    /**
     * 中文数据含义
     */
    @ApiModelProperty(value = "中文数据含义")
    private String valueCn;

    /**
     * 英文数据含义
     */
    @ApiModelProperty(value = "英文数据含义")
    private String valueEn;

    /**
     * 修改人员
     */
    @ApiModelProperty(value = "修改人员")
    private String opUser;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    private Date opTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
