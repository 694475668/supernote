package com.ratta.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ratta.constants.Constant;
import com.ratta.domain.ProductCustomizeDO;
import com.ratta.mapper.ProductCustomizeMapper;
import com.ratta.service.ProductCustomizeService;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.ProductCustomizeVO;
import com.ratta.vo.UserBeanVO;

/**
 * @author yll
 *
 */
@Service
public class ProductCustomizeServiceImpl implements ProductCustomizeService {

	@Autowired
	private ProductCustomizeMapper productCustomizeMapper;
	@Autowired
	private SystemBaseUtil systemBaseUtil;
	
	@Override
	public ProductCustomizeVO selectProductCustomize() {
		ProductCustomizeVO productCustomizeVO = new ProductCustomizeVO();
		ProductCustomizeDO productCustomizeDO = productCustomizeMapper.selectProductCustomize();
		productCustomizeVO.setCustomizeFlag(Constant.N);
		if(productCustomizeDO != null && !StringUtils.isEmpty(productCustomizeDO.getCustomizeFlag())) {
			productCustomizeVO.setCustomizeFlag(productCustomizeDO.getCustomizeFlag());
		}
		return productCustomizeVO;
	}

	@Override
	public BaseVO updateProductCustomizeFlag(String flag, String userId) {
		ProductCustomizeDO productCustomizeDO = productCustomizeMapper.selectProductCustomize();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		if(productCustomizeDO == null) {
			productCustomizeMapper.insert(userBeanVO.getUsername());
		}
		productCustomizeMapper.updateFlag(userBeanVO.getUsername(), flag);
		return new BaseVO();
	}

}
