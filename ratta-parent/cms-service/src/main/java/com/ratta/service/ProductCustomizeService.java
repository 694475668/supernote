package com.ratta.service;

import com.ratta.vo.BaseVO;
import com.ratta.vo.ProductCustomizeVO;

public interface ProductCustomizeService {

	ProductCustomizeVO selectProductCustomize();
	
	BaseVO updateProductCustomizeFlag(String flag, String userId);
}
