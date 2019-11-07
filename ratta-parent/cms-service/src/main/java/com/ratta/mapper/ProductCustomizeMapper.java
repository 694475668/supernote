package com.ratta.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.ProductCustomizeDO;

@Mapper
public interface ProductCustomizeMapper {

	ProductCustomizeDO selectProductCustomize();

	int insert( @Param("userName") String userName);

	int updateFlag( @Param("userName") String userName,
			@Param("customizeFlag") String customizeFlag);
}
