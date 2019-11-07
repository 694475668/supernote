package com.ratta.service;

import com.ratta.dto.FaqItemDTO;
import com.ratta.dto.FaqItemQueryDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.FaqItemVO;

/**
 * @author page
 */
public interface FaqItemService {
	/**
	 * 
	 * <p>query</p>
	 * <p>查询FAQ条目</p>
	 * @param faqItemQueryDTO 查询参数
	 * @return FAQ条目列表
	 */
	CommonListVO<FaqItemVO> query(FaqItemQueryDTO faqItemQueryDTO);
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加FAQ条目信息</p>
	 * @param  faqItemDTO FAQ条目信息
	 * @param  userId userId
	 * @return 数据库影响行数
	 */
	BaseVO save(FaqItemDTO faqItemDTO,String userId);
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新FAQ条目信息</p>
	 * @param faqItemDTO FAQ条目
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO update(FaqItemDTO faqItemDTO,int id,String userId);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除FAQ条目信息</p>
	 * @param id FAQ条目id
	 * @return 返回结果
	 */
	BaseVO delete(int id);
	
	/**
	 * 
	 * <p>queryById</p>
	 * <p>根据id查询FAQ条目信息</p>
	 * @param id id
	 * @return FAQ条目信息
	 */
	CommonVO<FaqItemVO> queryById(int id);
	
	/**
	 * 
	 * <p>updateLine</p>
	 * <p>修改FAQ条目上线下线</p>
	 * @param lineDTO FAQ条目
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO updateLine(LineDTO lineDTO,int id,String userId);
	
	/**
	 * 
	 * <p>updateSeq</p>
	 * <p>修改FAQ条目序号</p>
	 * @param seqDTO FAQ条目
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO updateSeq(SeqDTO seqDTO,int id,String userId);
}
