package com.ratta.service;

import com.ratta.dto.OverAllSeoLanAndPageDTO;
import com.ratta.dto.OverAllSeoQueryDTO;
import com.ratta.dto.OverallSeoDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.OverallSeoPartVO;
import com.ratta.vo.OverallSeoVO;

/**
 * @author page
 */
public interface OverallSeoService {
	/**
	 * 
	 * <p>query</p>
	 * <p>查询整体的SEO</p>
	 * @return 整体的SEO列表
	 */
	CommonListVO<OverallSeoVO> query(OverAllSeoQueryDTO overAllSeoQueryDTO);
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加整体的SEO信息</p>
	 * @param  overallSeoDTO 整体的SEO信息
	 * @param  userId userId
	 * @return 数据库影响行数
	 */
	BaseVO save(OverallSeoDTO overallSeoDTO,String userId);
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新整体的SEO信息</p>
	 * @param overallSeoDTO 整体的SEO
	 * @param id id
	 * @param  userId userId
	 * @return 返回结果
	 */
	BaseVO update(OverallSeoDTO overallSeoDTO,int id,String userId);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除整体的SEO信息</p>
	 * @param id 整体的SEOid
	 * @return 返回结果
	 */
	BaseVO delete(int id);
	
	/**
	 * 
	 * <p>queryById</p>
	 * <p>根据id查询整体的SEO信息</p>
	 * @param id id
	 * @return 整体的SEO信息
	 */
	CommonVO<OverallSeoVO> queryById(int id);
	
	/**
	 * 
	 * <p>queryByLan</p>
	 * <p>根据语言查询整体SEO信息</p>
	 * @param overAllSeoLanAndPageDTO 语言
	 * @return 整体的SEO信息
	 */
	CommonVO<OverallSeoPartVO> queryByLan(OverAllSeoLanAndPageDTO overAllSeoLanAndPageDTO);
}
