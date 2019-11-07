package com.ratta.service;

import com.ratta.dto.AboutAllDTO;
import com.ratta.dto.AboutDTO;
import com.ratta.dto.AboutUpdateDTO;
import com.ratta.dto.LanDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.vo.AboutAllVO;
import com.ratta.vo.AboutListVO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;

/**
 * @author yll
 *
 */
public interface AboutService {

	/**
	 * 新增关于页详情
	 * @param aboutDTO
	 * @param userId
	 * @return BaseVO
	 */
	BaseVO addAbout(AboutDTO aboutDTO, String userId);
	
	/**
	 * 修改关于页详情
	 * @param id
	 * @param aboutUpdateDTO
	 * @param userId
	 * @return
	 */
	BaseVO updateAbout(Integer id, AboutUpdateDTO aboutUpdateDTO, String userId);
	
	/**
	 * 上线、下线关于页详情
	 * @param id
	 * @param lineDTO
	 * @param userId
	 * @return
	 */
	BaseVO updateAboutLine(Integer id, LineDTO lineDTO, String userId);
	
	/**
	 * 管理平台按条件查询关于页详情（分页）
	 * @param aboutAllDTO
	 * @return
	 */
	CommonListVO<AboutAllVO> selectAboutAll(AboutAllDTO aboutAllDTO);
	
	/**
	 * 官网查询关于页详情
	 * @param aboutDTO
	 * @param line
	 * @return
	 */
	CommonListVO<AboutListVO> selectAboutList(LanDTO lanDTO, String line);
	
	/**
	 * 修改关于页详情序号
	 * @param seqDTO
	 * @param id
	 * @param userId
	 * @return
	 */
	BaseVO updateSeq(SeqDTO seqDTO, int id, String userId);
	
	BaseVO deleteAbout(Integer id);
}
