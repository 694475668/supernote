package com.ratta.service;

import com.ratta.dto.LineDTO;
import com.ratta.dto.PartSeriesDTO;
import com.ratta.dto.LanguageDTO;
import com.ratta.dto.PartSeriesQueryDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.PartSeriesOutVO;
import com.ratta.vo.PartSeriesVO;
import com.ratta.vo.RecommendBoxVO;

/**
 * @author gxy
 *
 */
public interface PartSeriesService {

	/**
	 * 查询配件系列列表（cms）
	 * 
	 * @param partSeriesInnerDTO
	 * @return
	 */
	CommonListVO<PartSeriesVO> queryInner(PartSeriesQueryDTO partSeriesQueryDTO);

	/**
	 * 查询配件系列列表（官网）
	 * 
	 * @param id
	 * @return
	 */
	CommonNoPageListVO<PartSeriesOutVO> queryOut(LanguageDTO languageDTO,String line);

	/**
	 * 查询单条记录（官网）
	 * 
	 * @param id
	 * @return
	 */
	CommonVO<PartSeriesOutVO> queryOutById(String id, LanguageDTO languageDTO,String line);

	/**
	 * 查询下拉框
	 * 
	 * @return
	 */
	CommonNoPageListVO<RecommendBoxVO> queryRecommendBox();

	/**
	 * 查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	CommonVO<PartSeriesVO> queryById(int id);

	/**
	 * 添加配件系列
	 * 
	 * @param partSeriesDTO
	 * @param userId
	 * @return
	 */
	BaseVO add(PartSeriesDTO partSeriesDTO, String userId);

	/**
	 * 修改配件系列
	 * 
	 * @param partSeriesDTO
	 * @param id
	 * @param userId
	 * @return
	 */
	BaseVO update(PartSeriesDTO partSeriesDTO, int id, String userId);

	/**
	 * 删除配件系列
	 * 
	 * @param id
	 * @return
	 */
	BaseVO delete(int id);

	/**
	 * 更新上下线状态
	 * 
	 * @param lineDTO
	 * @param id
	 * @param userId
	 * @return
	 */
	BaseVO updateLine(LineDTO lineDTO, int id, String userId);

	/**
	 * 修改序号
	 * 
	 * @param seqDTO
	 * @param id
	 * @param userId
	 * @return
	 */
	BaseVO updateSeq(SeqDTO seqDTO, int id, String userId);
}
