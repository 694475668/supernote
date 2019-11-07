package com.ratta.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

/**
 * ******Title:<p> 程序的奥秘 </p>******
 * ******Description:<p> </p>******
 * ******Company: <p> 雷塔智能科技有限公司 <p> ******
 *
 * @version :
 * @author: 《刘明》 【QQ 694475668】
 * @date:Created in 2019/9/27 19:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecommendDTO {
    @ApiModelProperty(value = "Y:推荐；N不推荐")
    @NotBlank(message = "推荐不能为空")
    private String recommendFlag;

    @ApiModelProperty(value = "id")
    private Long id;
}
