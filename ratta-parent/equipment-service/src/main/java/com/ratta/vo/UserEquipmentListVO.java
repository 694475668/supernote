package com.ratta.vo;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class UserEquipmentListVO extends BaseVO {
	@ApiModelProperty(value = "数据集合")
    private List<UserEquipmentVO> equipmentVOList;
}
