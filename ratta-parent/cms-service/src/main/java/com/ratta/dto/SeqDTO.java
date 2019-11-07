package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 请求数据新增传输
 *
 * @author page
 */
@Data
public class SeqDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "序号", required = true)
    @NotNull(message = "序号不能为空")
    private int seq;
}
