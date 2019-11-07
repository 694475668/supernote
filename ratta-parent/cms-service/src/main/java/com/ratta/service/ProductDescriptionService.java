package com.ratta.service;

import com.ratta.dto.LineDTO;
import com.ratta.dto.ProductDTO;
import com.ratta.dto.ProductDescriptionAllDTO;
import com.ratta.dto.ProductDescriptionDTO;
import com.ratta.dto.ProductDescriptionUpdateDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.ProductDescriptionAllVO;
import com.ratta.vo.ProductDescriptionListVO;

/**
 * @author yll
 *
 */
public interface ProductDescriptionService {

	/**
	 * 新增产品页详情
	 * @param productDescriptionDTO
	 * @param userId
	 * @return BaseVO
	 */
	BaseVO addProductDescription(ProductDescriptionDTO productDescriptionDTO, String userId);
	
	/**
	 * 修改产品页详情
	 * @param id
	 * @param productDescriptionUpdateDTO
	 * @param userId
	 * @return
	 */
	BaseVO updateProductDescription(Integer id, ProductDescriptionUpdateDTO productDescriptionUpdateDTO, String userId);
	
	/**
	 * 上线、下线产品页详情
	 * @param id
	 * @param lineDTO
	 * @param userId
	 * @return
	 */
	BaseVO updateProductDescriptionLine(Integer id, LineDTO lineDTO, String userId);
	
	/**
	 * 管理平台按条件查询产品页详情（分页）
	 * @param productDescriptionAllDTO
	 * @return
	 */
	CommonListVO<ProductDescriptionAllVO> selectProductDescriptionAll(ProductDescriptionAllDTO productDescriptionAllDTO);
	
	/**
	 * 官网查询产品页详情
	 * @param productDTO
	 * @param line
	 * @return
	 */
	CommonListVO<ProductDescriptionListVO> selectProductDescriptionList(ProductDTO productDTO, String line);
	
	/**
	 * 修改产品页详情序号
	 * @param seqDTO
	 * @param id
	 * @param userId
	 * @return
	 */
	BaseVO updateSeq(SeqDTO seqDTO, int id, String userId);
	
	BaseVO deleteProductDescription(Integer id);
}
