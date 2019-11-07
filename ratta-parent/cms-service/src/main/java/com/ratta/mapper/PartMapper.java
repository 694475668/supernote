package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.PartDO;

@Mapper
public interface PartMapper {

	/**
	 * 查询配件
	 * 
	 * @param partDO
	 * @return
	 */
	List<PartDO> query(PartDO partDO);

	/**
	 * 根据产品型号查询配件
	 * 
	 * @param partDO
	 * @return
	 */
	List<PartDO> queryByProductType(@Param("productType") String productType, @Param("line") String line);

	/**
	 * 查询单个配件
	 * 
	 * @param id
	 * @return
	 */
	PartDO queryById(@Param("id") int id);

	/**
	 * 按排序查询配件系列
	 * 
	 * @param serialNumber
	 * @return
	 */
	List<PartDO> queryOrderByDeq(@Param("serialNumber") String serialNumber, @Param("line") String line);

	/**
	 * 根据条件查询配件
	 * 
	 * @param serialNumber
	 * @param seq
	 * @param line
	 * @return
	 */
	PartDO queryBySeqAndLine(@Param("serialNumber") String serialNumber, @Param("productType") String productType,
			@Param("seq") int seq, @Param("line") String line);

	/**
	 * 根据条件查询配件
	 * 
	 * @param serialNumber
	 * @param seq
	 * @param productType
	 * @return
	 */
	PartDO queryBySerialNumberAndSeq(@Param("serialNumber") String serialNumber, @Param("seq") int seq,
			@Param("productType") String productType);

	/**
	 * 最大序号
	 * 
	 * @param serialNumber
	 * @param productType
	 * @return
	 */
	int queryMaxSeq(@Param("serialNumber") String serialNumber, @Param("productType") String productType);

	/**
	 * 新增
	 * 
	 * @param partDO
	 * @return
	 */
	int insert(PartDO partDO);

	/**
	 * 修改
	 * 
	 * @param partDO
	 * @return
	 */
	int update(PartDO partDO);

	/**
	 * 修改首页标志
	 * 
	 * @return
	 */
	int updateHomeFlag(@Param("serialNumber") String serialNumber);

	/**
	 * 删除
	 * 
	 * @param id
	 * @return
	 */
	int delete(@Param("id") int id);

	/**
	 * 修改上下线标志
	 * 
	 * @param partDO
	 * @return
	 */
	int updateLine(PartDO partDO);

	/**
	 * 修改序号
	 * 
	 * @param partDO
	 * @return
	 */
	int updateSeq(PartDO partDO);

	/**
	 * 修改序号
	 * 
	 * @param oldSeq
	 * @param newSeq
	 * @return
	 */
	int updateAllSeq(int oldSeq, int newSeq, @Param("serialNumber") String serialNumber,
			@Param("productType") String productType, @Param("line") String line);
}