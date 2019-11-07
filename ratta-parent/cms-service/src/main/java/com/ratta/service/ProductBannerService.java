package com.ratta.service;

import com.ratta.dto.LineDTO;
import com.ratta.dto.ProductBannerAllDTO;
import com.ratta.dto.ProductBannerDTO;
import com.ratta.dto.ProductDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.ProductBannerAllVO;
import com.ratta.vo.ProductBannerOneVO;

/**
 * @author yll
 *
 */
public interface ProductBannerService {
	
	ProductBannerOneVO selectProductBannerOne(ProductDTO productBannerOneDTO, String line);
	
	CommonListVO<ProductBannerAllVO> selectProductBannerAll(ProductBannerAllDTO productBannerAllDTO);
	
	BaseVO addProductBanner(ProductBannerDTO productBannerDTO, String userId);
	
	BaseVO updateProductBanner(Integer id, ProductBannerDTO productBannerDTO, String userId);
	
	BaseVO updateProductBannerLine(Integer id, LineDTO lineDTO, String userId);
	
	BaseVO deleteProductBanner(Integer id);
}
