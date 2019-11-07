package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.AboutDO;

@Mapper
public interface AboutMapper {

	int insert(AboutDO aboutDO);

	int update(AboutDO aboutDO);

	List<AboutDO> select(@Param("id") String id, @Param("line") String line, @Param("seq") Integer seq);

	int updateLine(@Param("id") Integer id, @Param("line") String line, @Param("userName") String userName);

	AboutDO queryById(@Param("id") Integer id);

	AboutDO queryBySeqAndLine(@Param("seq") Integer seq, @Param("line") String line);
	
	List<AboutDO> queryList(@Param("line") String line);

	int updateAllSeq(int oldSeq, int newSeq, String line);

	int updateSeq(AboutDO aboutDO);

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
	 * @return 最大序号
	 */
	int queryMaxSeq();

}
