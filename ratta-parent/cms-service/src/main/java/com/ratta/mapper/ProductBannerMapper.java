package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.ProductBannerDO;

@Mapper
public interface ProductBannerMapper {

	int insert(ProductBannerDO productBannerDO);

	int updateById(ProductBannerDO record);

	ProductBannerDO queryById(@Param("id") Integer id);

	ProductBannerDO queryByLine(@Param("type") String type, @Param("line") String line);

	List<ProductBannerDO> query();

	int updateLine(@Param("id") Integer id, @Param("line") String line, @Param("userName") String userName);

	int updateOffline(@Param("type") String type, @Param("userName") String userName);

	int deleteById(Integer id);

	List<ProductBannerDO> selectProductBanner(@Param("id") String id, @Param("type") String type,
			@Param("line") String line);
	
	List<ProductBannerDO> queryProductBanner(@Param("type") String type, @Param("line") String line);

}