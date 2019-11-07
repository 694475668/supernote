package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.FaqTitleDO;

/**
 * @author page
 */
@Mapper
public interface FaqTitleMapper {
	/**
	 * 
	 * <p>query</p>
	 * <p>查询FAQ标题</p>
	 * @param faqTitleDO 查询参数
	 * @return FAQ标题列表
	 */
	List<FaqTitleDO> query(FaqTitleDO faqTitleDO);
	
	/**
	 * 
	 * <p>queryByType</p>
	 * <p>通过类型查询FAQ列表信息 </p>
	 * @param type 类型
	 * @return FAQ标题列表
	 */
	List<FaqTitleDO> queryByType(@Param("type") String type,@Param("line") String line);
	
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加FAQ标题信息</p>
	 * @param faqTitleDO FAQ标题信息
	 * @return 数据库影响行数
	 */
	int save(FaqTitleDO faqTitleDO);
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新FAQ标题信息</p>
	 * @param faqTitleDO FAQ标题
	 * @return 数据库影响行数
	 */
	int update(FaqTitleDO faqTitleDO);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除FAQ标题信息</p>
	 * @param id FAQ标题id
	 * @return 数据库影响行数
	 */
	int delete(@Param("id") int id);
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询FAQ标题信息</p>
	 * @param id id
	 * @return FAQ标题信息
	 */
	FaqTitleDO queryById(@Param("id") int id);
	
	/**
	 * 查询该序号
	 * @param seq
	 * @param line
	 * @return
	 */
	FaqTitleDO queryBySeqAndLine(@Param("type") String type,@Param("seq") Integer seq,@Param("line") String line);
	
	/**
	 * 
	 * <p>queryByParam</p>
	 * <p>通过条件查询FAQ标题</p>
	 * @param type 类型
	 * @param seq 序号
	 * @return FAQ标题信息
	 */
	FaqTitleDO queryByParam(String type,int seq);
	
	/**
	 * 
	 * <p>queryMaxSeq</p>
	 * <p>查询最大的排序</p>
	 * @param type 类型
	 * @return 最大序号
	 */
	int queryMaxSeq(String type);
	
	
	/**
	 * 
	 * <p>updateLine</p>
	 * <p>修改FAQ标题上线下线</p>
	 * @param faqTitleDO FAQ标题
	 * @return 数据库影响行数
	 */
	int updateLine(FaqTitleDO faqTitleDO);
	
	
	/**
	 * 
	 * <p>updateSeq</p>
	 * <p>修改FAQ标题序号</p>
	 * @param faqTitleDO FAQ标题
	 * @return 数据库影响行数
	 */
	int updateSeq(FaqTitleDO faqTitleDO);
	
	/**
	 * 
	 * <p>updateAllSeq</p>
	 * <p>修改FAQ标题序号</p>
	 * @param oldSeq 原有序号
	 * @param newSeq 新序号
	 * @param type 类型
	 * @param line 线上线下标志
	 * @return 数据库影响行数
	 */
	int updateAllSeq(int oldSeq, int newSeq, String type, String line);
}
