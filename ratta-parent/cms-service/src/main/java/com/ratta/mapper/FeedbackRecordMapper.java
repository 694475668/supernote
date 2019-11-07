package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.ratta.domain.FeedbackRecordDO;

/**
 * @author page
 */
@Mapper
public interface FeedbackRecordMapper {
	/**
	 * 
	 * <p>query</p>
	 * <p>查询问题反馈</p>
	 * @param feedbackRecordDO 查询参数
	 * @return 问题反馈列表
	 */
	List<FeedbackRecordDO> query(FeedbackRecordDO feedbackRecordDO);
	
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加问题反馈信息</p>
	 * @param feedbackRecordDO 问题反馈信息
	 * @return 数据库影响行数
	 */
	int save(FeedbackRecordDO feedbackRecordDO);
	
	/**
	 * 
	 * <p>updatePart</p>
	 * <p>更新问题反馈信息</p>
	 * @param feedbackRecordDO 问题反馈
	 * @return 数据库影响行数
	 */
	int updatePart(FeedbackRecordDO feedbackRecordDO);
}
