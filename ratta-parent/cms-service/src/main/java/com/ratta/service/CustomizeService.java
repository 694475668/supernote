package com.ratta.service;

import com.ratta.dto.CustomizeAllDTO;
import com.ratta.dto.CustomizeDTO;
import com.ratta.dto.CustomizeRemarkDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CustomizeAllVO;

/**
 * @author yll
 *
 */
public interface CustomizeService {

	/**
	 * 新增套餐定制
	 * @param customizeDTO
	 * @return BaseVO
	 */
	BaseVO addCustomize(CustomizeDTO customizeDTO);
	
	/**
	 * 备注定制内容
	 * @param id
	 * @param customizeRemarkDTO
	 * @param userId
	 * @return
	 */
	BaseVO updateCustomizeRemark(Integer id, CustomizeRemarkDTO customizeRemarkDTO, String userId);
	
	/**
	 * 查询套餐定制数据
	 * @param customizeAllDTO
	 * @return
	 */
	CommonListVO<CustomizeAllVO> selectCustomizeAll(CustomizeAllDTO customizeAllDTO);
}
