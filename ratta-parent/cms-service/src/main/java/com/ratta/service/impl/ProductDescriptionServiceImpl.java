package com.ratta.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.ProductDescriptionDO;
import com.ratta.dto.*;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.ProductDescriptionMapper;
import com.ratta.service.ProductDescriptionService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ProductDescriptionServiceImpl implements ProductDescriptionService {

	@Autowired
	private SystemBaseUtil systemBaseUtil;
	@Autowired
	private ProductDescriptionMapper productDescriptionMapper;
	private static final String ORDERBY = "type, line,seq asc";

	@Override
	public BaseVO addProductDescription(ProductDescriptionDTO productDescriptionDTO, String userId) {
		ProductDescriptionDO productDescriptionDO = new ProductDescriptionDO();
		BeanUtils.copyProperties(productDescriptionDTO, productDescriptionDO);
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));

		if (productDescriptionDTO.getSeq() == null) {
			int maxSeq = productDescriptionMapper.queryMaxSeq(productDescriptionDTO.getType());
			productDescriptionDO.setSeq(maxSeq + 1);
		}

		productDescriptionDO.setCreateUser(userBeanVO.getUsername());
		productDescriptionDO.setUpdateUser(userBeanVO.getUsername());
		productDescriptionMapper.insert(productDescriptionDO);
		return new BaseVO();
	}

	@Override
	public BaseVO updateProductDescription(Integer id, ProductDescriptionUpdateDTO productDescriptionUpdateDTO,
			String userId) {
		BaseVO baseVO = new BaseVO();
		ProductDescriptionDO productDescriptionDO = new ProductDescriptionDO();
		BeanUtils.copyProperties(productDescriptionUpdateDTO, productDescriptionDO);
		// 修改首页相亲信息
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		productDescriptionDO.setCreateUser(userBeanVO.getUsername());
		productDescriptionDO.setUpdateUser(userBeanVO.getUsername());
		productDescriptionDO.setId(id);
		productDescriptionMapper.updateById(productDescriptionDO);
		return baseVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateProductDescriptionLine(Integer id, LineDTO lineDTO, String userId) {
		BaseVO baseVO = new BaseVO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		ProductDescriptionDO productDescriptionDO = productDescriptionMapper.queryById(id);
		// 如果是将状态改为上线
		if (lineDTO.getLine().equals("0")) {
			// 如果原来状态为预览或者下线
			if (productDescriptionDO.getLine().equals("1") || productDescriptionDO.getLine().equals("2")) {
				ProductDescriptionDO productDescriptionDOQ = productDescriptionMapper
						.queryBySeqAndLine(productDescriptionDO.getType(), productDescriptionDO.getSeq(), "0");
				// 如果原来上线状态没有这个序号
				if (productDescriptionDOQ == null) {
					productDescriptionMapper.updateLine(id, "0", userBeanVO.getUsername());
				}
				// 如果原来上线有这个序号
				if (productDescriptionDOQ != null) {
					productDescriptionMapper.updateLine(productDescriptionDOQ.getId(), "2", userBeanVO.getUsername());
					productDescriptionMapper.updateLine(id, "0", userBeanVO.getUsername());
				}
			}
			// 如果原来状态为上线状态
			if (productDescriptionDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为预览
		if (lineDTO.getLine().equals("1")) {
			// 如果原来状态为上线或者下线
			if (productDescriptionDO.getLine().equals("0") || productDescriptionDO.getLine().equals("2")) {
				ProductDescriptionDO productDescriptionDOQ = productDescriptionMapper
						.queryBySeqAndLine(productDescriptionDO.getType(), productDescriptionDO.getSeq(), "1");
				// 如果原来预览状态没有这个序号
				if (productDescriptionDOQ == null) {
					productDescriptionMapper.updateLine(id, "1", userBeanVO.getUsername());
				}
				// 如果原来预览有这个序号
				if (productDescriptionDOQ != null) {
					productDescriptionMapper.updateLine(productDescriptionDOQ.getId(), "2", userBeanVO.getUsername());
					productDescriptionMapper.updateLine(id, "1", userBeanVO.getUsername());
				}
			}
			// 如果原来状态为预览状态
			if (productDescriptionDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为下线
		if (lineDTO.getLine().equals("2")) {
			// 如果原来状态为上线或者预览
			if (productDescriptionDO.getLine().equals("0") || productDescriptionDO.getLine().equals("1")) {
				productDescriptionMapper.updateLine(id, "2", userBeanVO.getUsername());
			}
			// 如果原来状态为下线状态
			if (productDescriptionDO.getLine().equals("2")) {
				return baseVO;
			}
		}
		return baseVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommonListVO<ProductDescriptionAllVO> selectProductDescriptionAll(
			ProductDescriptionAllDTO productDescriptionAllDTO) {
		CommonListVO<ProductDescriptionAllVO> productDescriptionList = new CommonListVO<ProductDescriptionAllVO>();
		Integer pageNo = Integer.valueOf(productDescriptionAllDTO.getPageNo());
		Integer pageSize = Integer.valueOf(productDescriptionAllDTO.getPageSize());
		PageInfo<ProductDescriptionDO> pageInfo = PageHelper.startPage(pageNo, pageSize).setOrderBy(ORDERBY)
				.doSelectPageInfo(
						() -> this.productDescriptionMapper.selectProductDescription(productDescriptionAllDTO.getId(),
								productDescriptionAllDTO.getType(), productDescriptionAllDTO.getLine(), null));
		BeanUtils.copyProperties(pageInfo, productDescriptionList);
		productDescriptionList.setVoList(
				ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), ProductDescriptionAllVO.class));
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
		for (ProductDescriptionAllVO productDescriptionAllVO : productDescriptionList.getVoList()) {
			String productTypeName = "";
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (productDescriptionAllVO.getType().equals(dictionaryVO.getValue())) {
					productTypeName = productTypeName + dictionaryVO.getValueCn();
				}
			}
			productDescriptionAllVO.setType(productTypeName);

		}
		return productDescriptionList;
	}

	@Override
	public CommonListVO<ProductDescriptionListVO> selectProductDescriptionList(ProductDTO productDTO, String line) {
		CommonListVO<ProductDescriptionListVO> productDescriptionListVOList = new CommonListVO<ProductDescriptionListVO>();
		List<ProductDescriptionListVO> productDescriptionList = new ArrayList<ProductDescriptionListVO>();
		List<ProductDescriptionDO> productDescriptionDOList = productDescriptionMapper
				.queryProductDescriptionList( productDTO.getType(), line);
		for (ProductDescriptionDO productDescriptionDO : productDescriptionDOList) {
			ProductDescriptionListVO productDescriptionListVO = new ProductDescriptionListVO();
			ProductDescriptionInfoVO productDescriptionInfoVO = new ProductDescriptionInfoVO();
			BeanUtils.copyProperties(productDescriptionDO, productDescriptionInfoVO);
			if (Constant.CN.equals(productDTO.getLanguage().toUpperCase())) {
				productDescriptionInfoVO.setTitle(productDescriptionDO.getTitleCn());
				productDescriptionInfoVO.setDescriptionPc(productDescriptionDO.getDescriptionPcCn());
				productDescriptionInfoVO.setDescriptionMobile(productDescriptionDO.getDescriptionMobileCn());
				productDescriptionListVO.setTitle(productDescriptionDO.getTitleCn());
			}
			if (Constant.EN.equals(productDTO.getLanguage().toUpperCase())) {
				productDescriptionInfoVO.setTitle(productDescriptionDO.getTitleEn());
				productDescriptionInfoVO.setDescriptionPc(productDescriptionDO.getDescriptionPcEn());
				productDescriptionInfoVO.setDescriptionMobile(productDescriptionDO.getDescriptionMobileEn());
				productDescriptionListVO.setTitle(productDescriptionDO.getTitleEn());
			}
			productDescriptionListVO.setTitle(productDescriptionInfoVO.getTitle());
			productDescriptionListVO.setProductDescriptionInfo(productDescriptionInfoVO);
			productDescriptionList.add(productDescriptionListVO);
		}
		productDescriptionListVOList.setVoList(productDescriptionList);
		return productDescriptionListVOList;
	}

	@Override
	public BaseVO updateSeq(SeqDTO seqDTO, int id, String userId) {
		List<ProductDescriptionDO> productDescription = productDescriptionMapper
				.selectProductDescription(String.valueOf(id), null, null, null);
		if (productDescription.size() < 0) {
			return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
		}
		if (seqDTO.getSeq() == productDescription.get(0).getSeq()) {
			return new BaseVO();
		}
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		ProductDescriptionDO productDescriptionDO = new ProductDescriptionDO();
		BeanUtils.copyProperties(seqDTO, productDescriptionDO);
		productDescriptionDO.setId(id);
		productDescriptionDO.setUpdateTime(new Date());
		productDescriptionDO.setUpdateUser(userBeanVO.getUsername());
		if(!"2".equals(productDescription.get(0).getLine())) {
			// 同步修改整体排序
			productDescriptionMapper.updateAllSeq(productDescription.get(0).getSeq(), seqDTO.getSeq(),
					productDescription.get(0).getType(),productDescription.get(0).getLine());
		}
		productDescriptionMapper.updateSeq(productDescriptionDO);
		return new BaseVO();
	}

	@Override
	public BaseVO deleteProductDescription(Integer id) {
		// 根据id删除产品页banner图信息
		productDescriptionMapper.deleteById(id);
		return new BaseVO();
	}

}
