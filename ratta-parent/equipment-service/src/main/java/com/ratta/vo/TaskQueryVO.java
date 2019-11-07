package com.ratta.vo;

import java.util.List;

import com.ratta.info.TaskInfo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class TaskQueryVO extends BaseVO {

	@ApiModelProperty(value = "任务集合")
	private List<TaskInfo> taskInfoList;

	@ApiModelProperty(value = "设备号")
	private String equipmentNo;
}
