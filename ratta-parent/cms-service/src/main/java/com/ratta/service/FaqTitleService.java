package com.ratta.service;

import com.ratta.dto.FaqTitleDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.FaqTitleQueryDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.dto.TypeLanDTO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FaqTitleItemVO;
import com.ratta.vo.FaqTitlePartVO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.FaqTitleVO;

/**
 * @author page
 */
public interface FaqTitleService {
	/**
	 * 
	 * <p>query</p>
	 * <p>查询FAQ标题</p>
	 * @param faqTitleQueryDTO 查询参数
	 * @return FAQ标题列表
	 */
	CommonListVO<FaqTitleVO> query(FaqTitleQueryDTO faqTitleQueryDTO);
	
	/**
	 * 
	 * <p>queryByType</p>
	 * <p>通过类型查询FAQ列表信息 </p>
	 * @param type 类型
	 * @return FAQ标题列表
	 */
	CommonNoPageListVO<FaqTitlePartVO> queryByType(String type);
	
	/**
	 * 
	 * <p>queryByTypeAndLan</p>
	 * <p>通过类型和语言查询FAQ标题条目信息 </p>
	 * @param typeLanDTO 查询参数
	 * @return FAQ标题条目列表
	 */
	CommonNoPageListVO<FaqTitleItemVO> queryByTypeAndLan(TypeLanDTO typeLanDTO,String line);
	
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加FAQ标题信息</p>
	 * @param  faqTitleDTO FAQ标题信息
	 * @param  userId userId
	 * @return 数据库影响行数
	 */
	BaseVO save(FaqTitleDTO faqTitleDTO,String userId);
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新FAQ标题信息</p>
	 * @param faqTitleDTO FAQ标题
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO update(FaqTitleDTO faqTitleDTO,int id,String userId);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除FAQ标题信息</p>
	 * @param id FAQ标题id
	 * @return 返回结果
	 */
	BaseVO delete(int id);
	
	/**
	 * 
	 * <p>queryById</p>
	 * <p>根据id查询FAQ标题信息</p>
	 * @param id id
	 * @return FAQ标题信息
	 */
	CommonVO<FaqTitleVO> queryById(int id);
	
	/**
	 * 
	 * <p>updateLine</p>
	 * <p>修改FAQ标题上线下线</p>
	 * @param lineDTO FAQ标题
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO updateLine(LineDTO lineDTO,int id,String userId);
	
	/**
	 * 
	 * <p>updateSeq</p>
	 * <p>修改FAQ标题序号</p>
	 * @param seqDTO FAQ标题
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO updateSeq(SeqDTO seqDTO,int id,String userId);
}
