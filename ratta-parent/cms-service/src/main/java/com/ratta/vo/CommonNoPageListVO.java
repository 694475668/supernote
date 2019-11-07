package com.ratta.vo;

import java.util.List;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 返回多个对象
 * @author page
 * @param <T>
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
public class CommonNoPageListVO<T> extends BaseVO{
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "数据集合")
	private List<T> voList;

}
