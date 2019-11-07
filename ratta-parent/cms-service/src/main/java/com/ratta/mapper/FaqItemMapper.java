package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.FaqItemDO;

/**
 * @author page
 */
@Mapper
public interface FaqItemMapper {
	/**
	 * 
	 * <p>
	 * query
	 * </p>
	 * <p>
	 * 查询FAQ条目
	 * </p>
	 * 
	 * @param faqItemDO 查询参数
	 * @return FAQ条目列表
	 */
	List<FaqItemDO> query(FaqItemDO faqItemDO);

	/**
	 * 
	 * <p>
	 * queryAllNoPage
	 * </p>
	 * <p>
	 * 不分页查询所有faq条目信息
	 * </p>
	 * 
	 * @param faqTitleId 标题Id
	 * @param line       线上线下
	 * @return FAQ条目列表
	 */
	List<FaqItemDO> queryAllNoPage(@Param("faqTitleId") int faqTitleId, @Param("line") String line);

	/**
	 * 
	 * <p>
	 * save
	 * </p>
	 * <p>
	 * 添加FAQ条目信息
	 * </p>
	 * 
	 * @param faqItemDO FAQ条目信息
	 * @return 数据库影响行数
	 */
	int save(FaqItemDO faqItemDO);

	/**
	 * 
	 * <p>
	 * update
	 * </p>
	 * <p>
	 * 更新FAQ条目信息
	 * </p>
	 * 
	 * @param faqItemDO FAQ条目
	 * @return 数据库影响行数
	 */
	int update(FaqItemDO faqItemDO);

	/**
	 * 
	 * <p>
	 * delete
	 * </p>
	 * <p>
	 * 删除FAQ条目信息
	 * </p>
	 * 
	 * @param id FAQ条目id
	 * @return 数据库影响行数
	 */
	int delete(@Param("id") int id);

	/**
	 * 
	 * <p>
	 * query
	 * </p>
	 * <p>
	 * 根据id查询FAQ条目信息
	 * </p>
	 * 
	 * @param id id
	 * @return FAQ条目信息
	 */
	FaqItemDO queryById(@Param("id") int id);

	/**
	 * 通过条件查询FAQ条目
	 * 
	 * @param faqTitleId
	 * @param seq
	 * @param line
	 * @return
	 */
	FaqItemDO queryBySeqAndLine(@Param("faqTitleId") Integer faqTitleId, @Param("seq") Integer seq,
			@Param("line") String line);

	/**
	 * 
	 * <p>
	 * queryByParam
	 * </p>
	 * <p>
	 * 通过条件查询FAQ条目
	 * </p>
	 * 
	 * @param faqTitleId faqTitleId
	 * @param seq        序号
	 * @return FAQ条目信息
	 */
	FaqItemDO queryByParam(int faqTitleId, int seq);

	/**
	 * 
	 * <p>
	 * updateLine
	 * </p>
	 * <p>
	 * 修改FAQ条目上线下线
	 * </p>
	 * 
	 * @param faqItemDO FAQ条目
	 * @return 数据库影响行数
	 */
	int updateLine(FaqItemDO faqItemDO);

	/**
	 * 
	 * <p>
	 * updateSeq
	 * </p>
	 * <p>
	 * 修改FAQ条目序号
	 * </p>
	 * 
	 * @param faqItemDO FAQ条目
	 * @return 数据库影响行数
	 */
	int updateSeq(FaqItemDO faqItemDO);

	/**
	 * 
	 * <p>
	 * updateAllSeq
	 * </p>
	 * <p>
	 * 修改FAQ条目序号
	 * </p>
	 * 
	 * @param oldSeq 原有序号
	 * @param newSeq 新序号
	 * @param type   类型
	 * @return 数据库影响行数
	 */
	int updateAllSeq(int oldSeq, int newSeq, int faqTitleId,String line);

	/**
	 * 
	 * <p>
	 * queryMaxSeq
	 * </p>
	 * <p>
	 * 查询最大的排序
	 * </p>
	 * 
	 * @param faqTitleId 标题Id
	 * @return 最大序号
	 */
	int queryMaxSeq(int faqTitleId);

}
