package com.ratta.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.ProductBannerDO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.ProductBannerAllDTO;
import com.ratta.dto.ProductBannerDTO;
import com.ratta.dto.ProductDTO;
import com.ratta.mapper.ProductBannerMapper;
import com.ratta.service.ProductBannerService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.DictionaryVO;
import com.ratta.vo.ProductBannerAllVO;
import com.ratta.vo.ProductBannerOneVO;
import com.ratta.vo.UserBeanVO;

/**
 * @author yll
 *
 */
@Service
public class ProductBannerServiceImpl implements ProductBannerService {

	@Autowired
	private SystemBaseUtil systemBaseUtil;
	@Autowired
	private ProductBannerMapper productBannerMapper;
	private static final String ORDERBY = "create_time desc";

	@Override
	public BaseVO addProductBanner(ProductBannerDTO productBannerDTO, String userId) {
		ProductBannerDO productBanner = new ProductBannerDO();
		BeanUtils.copyProperties(productBannerDTO, productBanner);
		// 添加首页banner图信息
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		productBanner.setCreateUser(userBeanVO.getUsername());
		productBanner.setUpdateUser(userBeanVO.getUsername());
		productBannerMapper.insert(productBanner);
		return new BaseVO();
	}

	@Override
	public BaseVO updateProductBanner(Integer id, ProductBannerDTO productBannerDTO, String userId) {
		ProductBannerDO productBanner = new ProductBannerDO();
		BeanUtils.copyProperties(productBannerDTO, productBanner);
		// 修改首页banner图信息
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		productBanner.setUpdateUser(userBeanVO.getUsername());
		productBanner.setId(id);
		productBannerMapper.updateById(productBanner);
		return new BaseVO();
	}

	@Override
	public BaseVO deleteProductBanner(Integer id) {
		// 根据id删除产品页banner图信息
		productBannerMapper.deleteById(id);
		return new BaseVO();
	}

	@Override
	public ProductBannerOneVO selectProductBannerOne(ProductDTO productBannerOneDTO, String line) {
		ProductBannerOneVO productBannerVO = new ProductBannerOneVO();
		List<ProductBannerDO> productBanner = productBannerMapper.queryProductBanner(
				productBannerOneDTO.getType(), line);
		if(productBanner.size() < 1 && "1".equals(line)) {
			productBanner = productBannerMapper.queryProductBanner(
					productBannerOneDTO.getType(), "0");
		}
		if (!productBanner.isEmpty()) {
			BeanUtils.copyProperties(productBanner.get(0), productBannerVO);
			if (Constant.CN.equals(productBannerOneDTO.getLanguage().toUpperCase())) {
				productBannerVO.setTitle(productBanner.get(0).getTitleCn());
				productBannerVO.setDescription(productBanner.get(0).getDescriptionCn());
				productBannerVO.setButtonName(productBanner.get(0).getButtonNameCn());
				productBannerVO.setButtonLink(productBanner.get(0).getButtonLinkCn());
				productBannerVO.setDetail(productBanner.get(0).getDetailCn());
				productBannerVO.setSeoTitle(productBanner.get(0).getSeoTitleCn());
				productBannerVO.setSeoKeyword(productBanner.get(0).getSeoKeywordCn());
				productBannerVO.setSeoDescription(productBanner.get(0).getSeoDescriptionCn());
			}
			if (Constant.EN.equals(productBannerOneDTO.getLanguage().toUpperCase())) {
				productBannerVO.setTitle(productBanner.get(0).getTitleEn());
				productBannerVO.setDescription(productBanner.get(0).getDescriptionEn());
				productBannerVO.setButtonName(productBanner.get(0).getButtonNameEn());
				productBannerVO.setButtonLink(productBanner.get(0).getButtonLinkEn());
				productBannerVO.setDetail(productBanner.get(0).getDetailEn());
				productBannerVO.setSeoTitle(productBanner.get(0).getSeoTitleEn());
				productBannerVO.setSeoKeyword(productBanner.get(0).getSeoKeywordEn());
				productBannerVO.setSeoDescription(productBanner.get(0).getSeoDescriptionEn());
			}

			List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
			String productTypeName = "";
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (productBannerVO.getType().equals(dictionaryVO.getValue())) {
					productTypeName = productTypeName + dictionaryVO.getValueCn();
				}
			}
			productBannerVO.setType(productTypeName);
		}
		return productBannerVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateProductBannerLine(Integer id, LineDTO lineDTO, String userId) {
		BaseVO baseVO = new BaseVO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		ProductBannerDO productBannerDO = productBannerMapper.queryById(id);
		// 如果是将状态改为上线
		if (lineDTO.getLine().equals("0")) {
			// 如果原来状态为预览或者下线
			if (productBannerDO.getLine().equals("1") || productBannerDO.getLine().equals("2")) {
				ProductBannerDO productBannerDOQ = productBannerMapper.queryByLine(productBannerDO.getType(), "0");
				// 如果原来上线状态没有这个序号
				if (productBannerDOQ == null) {
					productBannerMapper.updateLine(id, "0", userBeanVO.getUsername());
				}
				// 如果原来上线有这个序号
				if (productBannerDOQ != null) {
					productBannerMapper.updateLine(productBannerDOQ.getId(), "2", userBeanVO.getUsername());
					productBannerMapper.updateLine(id, "0", userBeanVO.getUsername());
				}
			}
			// 如果原来状态为上线状态
			if (productBannerDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为预览
		if (lineDTO.getLine().equals("1")) {
			// 如果原来状态为上线或者下线
			if (productBannerDO.getLine().equals("0") || productBannerDO.getLine().equals("2")) {
				ProductBannerDO productBannerDOQ = productBannerMapper.queryByLine(productBannerDO.getType(), "1");
				// 如果原来预览状态没有这个序号
				if (productBannerDOQ == null) {
					productBannerMapper.updateLine(id, "1", userBeanVO.getUsername());
				}
				// 如果原来预览有这个序号
				if (productBannerDOQ != null) {
					productBannerMapper.updateLine(productBannerDOQ.getId(), "2", userBeanVO.getUsername());
					productBannerMapper.updateLine(id, "1", userBeanVO.getUsername());
				}
			}
			// 如果原来状态为预览状态
			if (productBannerDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为下线
		if (lineDTO.getLine().equals("2")) {
			// 如果原来状态为上线或者预览
			if (productBannerDO.getLine().equals("0") || productBannerDO.getLine().equals("1")) {
				productBannerMapper.updateLine(id, "2", userBeanVO.getUsername());
			}
			// 如果原来状态为下线状态
			if (productBannerDO.getLine().equals("2")) {
				return baseVO;
			}
		}
		return baseVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommonListVO<ProductBannerAllVO> selectProductBannerAll(ProductBannerAllDTO productBannerAllDTO) {
		CommonListVO<ProductBannerAllVO> productBannerList = new CommonListVO<ProductBannerAllVO>();
		Integer pageNo = Integer.valueOf(productBannerAllDTO.getPageNo());
		Integer pageSize = Integer.valueOf(productBannerAllDTO.getPageSize());
		PageInfo<ProductBannerDO> pageInfo = PageHelper.startPage(pageNo, pageSize).setOrderBy(ORDERBY)
				.doSelectPageInfo(() -> this.productBannerMapper.selectProductBanner(productBannerAllDTO.getId(),
						productBannerAllDTO.getType(), productBannerAllDTO.getLine()));
		productBannerList.setVoList(
				ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), ProductBannerAllVO.class));
		BeanUtils.copyProperties(pageInfo, productBannerList);
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
		for (ProductBannerAllVO productBannerAllVO : productBannerList.getVoList()) {
			String productTypeName = "";
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (productBannerAllVO.getType().equals(dictionaryVO.getValue())) {
					productTypeName = productTypeName + dictionaryVO.getValueCn();
				}
			}
			productBannerAllVO.setType(productTypeName);
		}
		return productBannerList;
	}
}
