package com.ratta.service;

import com.ratta.dto.FeedbackRecordDTO;
import com.ratta.dto.FeedbackRecordPartDTO;
import com.ratta.dto.FeedbackRecordQueryDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.FeedbackRecordVO;

/**
 * @author page
 */
public interface FeedbackRecordService {
	/**
	 * 
	 * <p>query</p>
	 * <p>查询问题反馈</p>
	 * @param feedbackRecordQueryDTO 查询参数
	 * @return 问题反馈列表
	 */
	CommonListVO<FeedbackRecordVO> query(FeedbackRecordQueryDTO feedbackRecordQueryDTO);
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加问题反馈信息</p>
	 * @param  feedbackRecordDTO 问题反馈信息
	 * @return 数据库影响行数
	 */
	BaseVO save(FeedbackRecordDTO feedbackRecordDTO);
	
	/**
	 * 
	 * <p>updatePart</p>
	 * <p>更新问题反馈</p>
	 * @param feedbackRecordPartDTO 问题反馈部分参数
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO updatePart(FeedbackRecordPartDTO feedbackRecordPartDTO,int id,String userId);
}
