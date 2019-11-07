package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.ProductDescriptionDO;

@Mapper
public interface ProductDescriptionMapper {

	int insert(ProductDescriptionDO productDescriptionDO);

	int updateById(ProductDescriptionDO productDescriptionDO);

	ProductDescriptionDO queryById(@Param("id") Integer id);

	ProductDescriptionDO queryBySeqAndLine(@Param("type") String type, @Param("seq") Integer seq,
			@Param("line") String line);

	List<ProductDescriptionDO> selectProductDescription(@Param("id") String id, @Param("type") String type,
			@Param("line") String line, @Param("seq") String seq);

	int updateLine(@Param("id") Integer id, @Param("line") String line, @Param("userName") String userName);
	
	List<ProductDescriptionDO> queryProductDescriptionList(@Param("type") String type, @Param("line") String line);
	
	int updateAllSeq(int oldSeq, int newSeq, String type, String line);

	int updateSeq(ProductDescriptionDO productDescriptionDO);

	int deleteById(Integer id);

	/**
	 * 
	 * <p>
	 * queryMaxSeq
	 * </p>
	 * <p>
	 * 查询最大的排序
	 * </p>
	 * 
	 * @param type 类型
	 * @return 最大序号
	 */
	int queryMaxSeq(String type);

}
