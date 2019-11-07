package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.PartSeriesDO;

@Mapper
public interface PartSeriesMapper {

	/**
	 * 查询配件系列
	 * 
	 * @param partSeriesDO
	 * @return
	 */
	List<PartSeriesDO> query(PartSeriesDO partSeriesDO);

	/**
	 * 按排序查询配件系列
	 * 
	 * @param type
	 * @param line
	 * @return
	 */
	List<PartSeriesDO> queryOrderByDeq(@Param("type") String type,@Param("line") String line);

	/**
	 * 查询单个配件系列
	 * 
	 * @param id
	 * @return
	 */
	PartSeriesDO queryById(@Param("id") int id);

	/**
	 * 查询某个状态的某个系列
	 * @param serialNumber
	 * @param line
	 * @return
	 */
	PartSeriesDO queryBySerialNumber(@Param("serialNumber") String serialNumber,@Param("line") String line);
	
	/**
	 * 根据序号查询配件系列
	 * 
	 * @param seq  序号
	 * @param line 在线状态
	 * @return
	 */
	PartSeriesDO queryBySeqAndLine(@Param("seq") Integer seq, @Param("line") String line);

	/**
	 * 新增
	 * 
	 * @param partSeriesDO
	 * @return
	 */
	int insert(PartSeriesDO partSeriesDO);

	/**
	 * 修改
	 * 
	 * @param partSeriesDO
	 * @return
	 */
	int update(PartSeriesDO partSeriesDO);

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
	 * @param partSeriesDO
	 * @return
	 */
	int updateLine(PartSeriesDO partSeriesDO);

	/**
	 * 修改序号
	 * 
	 * @param partSeriesDO
	 * @return
	 */
	int updateSeq(PartSeriesDO partSeriesDO);

	/**
	 * 修改序号
	 * 
	 * @param oldSeq
	 * @param newSeq
	 * @return
	 */
	int updateAllSeq(int oldSeq, int newSeq, String line);

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
	 * @return FAQ标题最大序号
	 */
	int queryMaxSeq();

}