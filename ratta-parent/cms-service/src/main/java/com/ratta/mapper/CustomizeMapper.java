package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.CustomizeDO;

@Mapper
public interface CustomizeMapper {

	int insert(CustomizeDO aboutDO);

	int update(CustomizeDO aboutDO);

	List<CustomizeDO> select(@Param("id") String id,  @Param("product") String product,@Param("userName") String userName,  @Param("telephone") String telephone, 
			@Param("email") String email,@Param("flag") String flag);
}
