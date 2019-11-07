package com.ratta.service;

import com.ratta.dto.LanguageDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.PartDTO;
import com.ratta.dto.PartLanguageDTO;
import com.ratta.dto.PartQueryDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.PartCustomizeVO;
import com.ratta.vo.PartHomeVO;
import com.ratta.vo.PartOutVO;
import com.ratta.vo.PartVO;

/**
 * @author gxy
 *
 */
public interface PartService {

	/**
	 * 查询配件列表（cms）
	 * 
	 * @param partInnerDTO
	 * @return
	 */
	CommonListVO<PartVO> queryInner(PartQueryDTO partQueryDTO);

	/**
	 * 查询配件列表（官网）
	 * 
	 * @param languageDTO
	 * @return
	 */
	CommonVO<PartCustomizeVO> queryPartCustomize(PartLanguageDTO partLanguageDTO, String line);

	/**
	 * 查询配件列表（首页）
	 * 
	 * @param languageDTO
	 * @return
	 */
	CommonNoPageListVO<PartHomeVO> queryPartHome(LanguageDTO languageDTO, String line);

	/**
	 * 查询配件列表(配件页)
	 * 
	 * @param languageDTO
	 * @param serialNumber
	 * @param line
	 * @return
	 */
	CommonNoPageListVO<PartOutVO> queryPartOut(LanguageDTO languageDTO, String serialNumber, String line);

	/**
	 * 查询单条记录
	 * 
	 * @param id
	 * @return
	 */
	CommonVO<PartVO> queryById(int id);

	/**
	 * 添加配件
	 * 
	 * @param partDTO
	 * @param userId
	 * @return
	 */
	BaseVO add(PartDTO partDTO, String userId);

	/**
	 * 修改配件
	 * 
	 * @param partDTO
	 * @param id
	 * @param userId
	 * @return
	 */
	BaseVO update(PartDTO partDTO, int id, String userId);

	/**
	 * 删除配件
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
