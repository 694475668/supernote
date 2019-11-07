package com.ratta.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.PartDO;
import com.ratta.domain.PartSeriesDO;
import com.ratta.dto.LanguageDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.PartSeriesDTO;
import com.ratta.dto.PartSeriesQueryDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.PartMapper;
import com.ratta.mapper.PartSeriesMapper;
import com.ratta.service.PartSeriesService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.DictionaryVO;
import com.ratta.vo.PartSeriesOutVO;
import com.ratta.vo.PartSeriesVO;
import com.ratta.vo.ProductVO;
import com.ratta.vo.RecommendBoxVO;
import com.ratta.vo.RecommendVO;
import com.ratta.vo.UserBeanVO;

import lombok.extern.slf4j.Slf4j;

/**
 * @author gxy
 *
 */
@Service
@Slf4j
public class PartSeriesServiceImpl implements PartSeriesService {

	@Autowired
	private SystemBaseUtil systemBaseUtil;

	@Autowired
	private PartSeriesMapper partSeriesMapper;

	@Autowired
	private PartMapper partMapper;

	@SuppressWarnings("unchecked")
	@Override
	public CommonListVO<PartSeriesVO> queryInner(PartSeriesQueryDTO partSeriesQueryDTO) {
		PartSeriesDO partSeriesDO = new PartSeriesDO();
		BeanUtils.copyProperties(partSeriesQueryDTO, partSeriesDO);
		PageInfo<PartSeriesDO> pageInfo = PageHelper
				.startPage(partSeriesQueryDTO.getPageNo(), partSeriesQueryDTO.getPageSize()).setOrderBy("line,seq asc")
				.doSelectPageInfo(() -> partSeriesMapper.query(partSeriesDO));

		CommonListVO<PartSeriesVO> commonListVO = new CommonListVO<PartSeriesVO>();
		BeanUtils.copyProperties(pageInfo, commonListVO);
		commonListVO
				.setVoList(ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), PartSeriesVO.class));
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART_SERIES");
		for (PartSeriesVO partSeriesVO : commonListVO.getVoList()) {
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (partSeriesVO.getSerialNumber().equals(dictionaryVO.getValue())) {
					partSeriesVO.setNameCn(dictionaryVO.getValueCn());
					partSeriesVO.setNameEn(dictionaryVO.getValueEn());
				}
			}
		}
		return commonListVO;
	}

	@Override
	public CommonVO<PartSeriesVO> queryById(int id) {
		PartSeriesDO partSeriesDO = partSeriesMapper.queryById(id);
		CommonVO<PartSeriesVO> commonVO = new CommonVO<PartSeriesVO>();
		if (partSeriesDO == null) {
			commonVO.setSuccess(false);
			commonVO.setErrorCode(ErrorCodeEnum.E1441.getKey());
			commonVO.setErrorMsg(ErrorCodeEnum.E1441.getValue());
			return commonVO;
		}
		PartSeriesVO partSeriesVO = new PartSeriesVO();
		BeanUtils.copyProperties(partSeriesDO, partSeriesVO);
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART_SERIES");
		for (DictionaryVO dictionaryVO : dictionaryVOList) {
			if (partSeriesVO.getSerialNumber().equals(dictionaryVO.getValue())) {
				partSeriesVO.setNameCn(dictionaryVO.getValueCn());
				partSeriesVO.setNameEn(dictionaryVO.getValueEn());
			}
		}
		commonVO.setVoT(partSeriesVO);
		return commonVO;

	}

	@Override
	public BaseVO add(PartSeriesDTO partSeriesDTO, String userId) {
		BaseVO baseVO = new BaseVO();

		String[] recommendIdArray = partSeriesDTO.getRecommendId().split(",");
		String[] recommendUrlArray = partSeriesDTO.getRecommendUrl().split(",");

		if (recommendIdArray.length != recommendUrlArray.length) {
			log.info("推荐类型和推荐缩略图个数不相等");
			baseVO.setErrorCode(ErrorCodeEnum.E1472.getKey());
			baseVO.setErrorMsg(ErrorCodeEnum.E1472.getValue());
			baseVO.setSuccess(false);
			return baseVO;
		}

		PartSeriesDO partSeriesDO = new PartSeriesDO();
		BeanUtils.copyProperties(partSeriesDTO, partSeriesDO);
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));

		if (partSeriesDTO.getSeq() == null) {
			int maxSeq = partSeriesMapper.queryMaxSeq();
			partSeriesDO.setSeq(maxSeq + 1);
		}

		partSeriesDO.setLine("2");
		partSeriesDO.setCreateUser(userBeanVO.getUsername());
		partSeriesDO.setUpdateUser(userBeanVO.getUsername());
		partSeriesDO.setUpdateTime(new Date());
		partSeriesDO.setCreateTime(new Date());
		partSeriesMapper.insert(partSeriesDO);
		return baseVO;
	}

	@Override
	public BaseVO update(PartSeriesDTO partSeriesDTO, int id, String userId) {
		BaseVO baseVO = new BaseVO();
		String[] recommendIdArray = partSeriesDTO.getRecommendId().split(",");
		String[] recommendUrlArray = partSeriesDTO.getRecommendUrl().split(",");

		if (recommendIdArray.length != recommendUrlArray.length) {
			log.info("推荐类型和推荐缩略图个数不相等");
			baseVO.setErrorCode(ErrorCodeEnum.E1472.getKey());
			baseVO.setErrorMsg(ErrorCodeEnum.E1472.getValue());
			baseVO.setSuccess(false);
			return baseVO;
		}
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		PartSeriesDO partSeriesDO = new PartSeriesDO();
		BeanUtils.copyProperties(partSeriesDTO, partSeriesDO);
		partSeriesDO.setId(id);
		partSeriesDO.setUpdateTime(new Date());
		partSeriesDO.setUpdateUser(userBeanVO.getUsername());
		partSeriesMapper.update(partSeriesDO);
		return baseVO;
	}

	@Override
	public BaseVO delete(int id) {
		partSeriesMapper.delete(id);
		return new BaseVO();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateLine(LineDTO lineDTO, int id, String userId) {
		BaseVO baseVO = new BaseVO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		PartSeriesDO partSeriesDO = partSeriesMapper.queryById(id);
		// 如果是将状态改为上线
		if (lineDTO.getLine().equals("0")) {
			// 如果原来状态为预览或者下线
			if (partSeriesDO.getLine().equals("1") || partSeriesDO.getLine().equals("2")) {
				// 判断上线的其他位置是否有相同的系列编号
				PartSeriesDO partSeriesDO0 = partSeriesMapper.queryBySerialNumber(partSeriesDO.getSerialNumber(), "0");
				if (partSeriesDO0 != null) {
					if (!partSeriesDO0.getSeq().equals(partSeriesDO.getSeq())) {
						baseVO.setSuccess(false);
						baseVO.setErrorCode(ErrorCodeEnum.E1473.getKey());
						baseVO.setErrorMsg(ErrorCodeEnum.E1473.getValue());
						return baseVO;
					}
				}
				PartSeriesDO partSeriesDOQ = partSeriesMapper.queryBySeqAndLine(partSeriesDO.getSeq(), "0");
				// 如果原来上线状态没有这个序号
				if (partSeriesDOQ == null) {
					update(id, "0", userBeanVO);
				}
				// 如果原来上线有这个序号
				if (partSeriesDOQ != null) {
					update(partSeriesDOQ.getId(), "2", userBeanVO);
					update(id, "0", userBeanVO);
				}
			}
			// 如果原来状态为上线状态
			if (partSeriesDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为预览
		if (lineDTO.getLine().equals("1")) {
			// 如果原来状态为上线或者下线
			if (partSeriesDO.getLine().equals("0") || partSeriesDO.getLine().equals("2")) {
				// 判断上线的其他位置是否有相同的系列编号
				PartSeriesDO partSeriesDO0 = partSeriesMapper.queryBySerialNumber(partSeriesDO.getSerialNumber(), "1");
				if (partSeriesDO0 != null) {
					if (!partSeriesDO0.getSeq().equals(partSeriesDO.getSeq())) {
						baseVO.setSuccess(false);
						baseVO.setErrorCode(ErrorCodeEnum.E1473.getKey());
						baseVO.setErrorMsg(ErrorCodeEnum.E1473.getValue());
						return baseVO;
					}
				}
				PartSeriesDO partSeriesDOQ = partSeriesMapper.queryBySeqAndLine(partSeriesDO.getSeq(), "1");
				// 如果原来预览状态没有这个序号
				if (partSeriesDOQ == null) {
					update(id, "1", userBeanVO);
				}
				// 如果原来预览有这个序号
				if (partSeriesDOQ != null) {
					update(partSeriesDOQ.getId(), "2", userBeanVO);
					update(id, "1", userBeanVO);
				}
			}
			// 如果原来状态为预览状态
			if (partSeriesDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为下线
		if (lineDTO.getLine().equals("2")) {
			// 如果原来状态为上线或者预览
			if (partSeriesDO.getLine().equals("0") || partSeriesDO.getLine().equals("1")) {
				update(id, "2", userBeanVO);
			}
			// 如果原来状态为下线状态
			if (partSeriesDO.getLine().equals("2")) {
				return baseVO;
			}
		}
		return baseVO;
	}

	private void update(int id, String line, UserBeanVO userBeanVO) {
		PartSeriesDO partSeriesDO = new PartSeriesDO();
		partSeriesDO.setId(id);
		partSeriesDO.setLine(line);
		partSeriesDO.setUpdateTime(new Date());
		partSeriesDO.setUpdateUser(userBeanVO.getUsername());
		partSeriesMapper.updateLine(partSeriesDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateSeq(SeqDTO seqDTO, int id, String userId) {
		PartSeriesDO partSeriesDO = partSeriesMapper.queryById(id);
		if (partSeriesDO == null) {
			return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
		}

		if (seqDTO.getSeq() == partSeriesDO.getSeq()) {
			return new BaseVO();
		}

		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		PartSeriesDO partSeriesDOQ = new PartSeriesDO();
		BeanUtils.copyProperties(seqDTO, partSeriesDOQ);
		partSeriesDOQ.setId(id);
		partSeriesDOQ.setUpdateTime(new Date());
		partSeriesDOQ.setUpdateUser(userBeanVO.getUsername());
		if (!"2".equals(partSeriesDO.getLine())) {
			// 同步修改整体排序
			partSeriesMapper.updateAllSeq(partSeriesDO.getSeq(), seqDTO.getSeq(), partSeriesDO.getLine());
		}
		partSeriesMapper.updateSeq(partSeriesDOQ);
		return new BaseVO();
	}

	@Override
	public CommonNoPageListVO<RecommendBoxVO> queryRecommendBox() {

		CommonNoPageListVO<RecommendBoxVO> CommonNoPageListVOList = new CommonNoPageListVO<RecommendBoxVO>();
		List<RecommendBoxVO> RecommendBoxVOList = new ArrayList<RecommendBoxVO>();
		List<DictionaryVO> dictionaryVOList1 = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
		for (DictionaryVO dictionaryVO : dictionaryVOList1) {
			RecommendBoxVO recommendBoxVO = new RecommendBoxVO();
			recommendBoxVO.setRecommendId("0-" + dictionaryVO.getValue());
			recommendBoxVO.setRecommendName(dictionaryVO.getValueCn());
			RecommendBoxVOList.add(recommendBoxVO);
		}
		PartSeriesDO partSeriesDOQ = new PartSeriesDO();
		partSeriesDOQ.setLine("0");
		List<PartSeriesDO> partSeriesDOList = partSeriesMapper.query(partSeriesDOQ);

		List<DictionaryVO> dictionaryVOList2 = systemBaseUtil.queryDictionaryNameAndValue("PART_SERIES");
		for (PartSeriesDO partSeriesDO : partSeriesDOList) {
			RecommendBoxVO recommendBoxVO = new RecommendBoxVO();
			recommendBoxVO.setRecommendId("1-" + partSeriesDO.getSerialNumber());
			for (DictionaryVO dictionaryVO : dictionaryVOList2) {
				if (partSeriesDO.getSerialNumber().equals(dictionaryVO.getValue())) {
					recommendBoxVO.setRecommendName(dictionaryVO.getValueCn());
				}
			}
			RecommendBoxVOList.add(recommendBoxVO);
		}
		CommonNoPageListVOList.setVoList(RecommendBoxVOList);
		return CommonNoPageListVOList;
	}

	@Override
	public CommonNoPageListVO<PartSeriesOutVO> queryOut(LanguageDTO languageDTO, String line) {
		List<PartSeriesDO> partSeriesDOList = partSeriesMapper.queryOrderByDeq(null, line);
		CommonNoPageListVO<PartSeriesOutVO> commonNoPageListVO = new CommonNoPageListVO<PartSeriesOutVO>();
		List<PartSeriesOutVO> partSeriesOutVOList = new ArrayList<PartSeriesOutVO>();
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART_SERIES");
		List<DictionaryVO> dictionaryVOList2 = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");

		for (PartSeriesDO partSeriesDO : partSeriesDOList) {
			PartSeriesOutVO partSeriesOutVO = setPartSeriesOut(dictionaryVOList, dictionaryVOList2, partSeriesDOList,
					partSeriesDO, languageDTO);
			partSeriesOutVOList.add(partSeriesOutVO);
		}
		commonNoPageListVO.setVoList(partSeriesOutVOList);
		return commonNoPageListVO;
	}

	@Override
	public CommonVO<PartSeriesOutVO> queryOutById(String serialNumber, LanguageDTO languageDTO, String line) {
		PartSeriesDO partSeriesDO = new PartSeriesDO();
		if ("0".equals(line)) {
			partSeriesDO = partSeriesMapper.queryBySerialNumber(serialNumber, line);
		}
		if ("1".equals(line)) {
			partSeriesDO = partSeriesMapper.queryBySerialNumber(serialNumber, line);
			if (partSeriesDO == null) {
				partSeriesDO = partSeriesMapper.queryBySerialNumber(serialNumber, "0");
			}
		}
		CommonVO<PartSeriesOutVO> commonVO = new CommonVO<PartSeriesOutVO>();
		if (partSeriesDO == null) {
			commonVO.setSuccess(false);
			commonVO.setErrorCode(ErrorCodeEnum.E1441.getKey());
			commonVO.setErrorMsg(ErrorCodeEnum.E1441.getValue());
			return commonVO;
		}
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART_SERIES");
		List<DictionaryVO> dictionaryVOList2 = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");

		List<PartSeriesDO> partSeriesDOListD = partSeriesMapper.queryOrderByDeq(null, line);
		PartSeriesOutVO partSeriesOutVO = setPartSeriesOut(dictionaryVOList, dictionaryVOList2, partSeriesDOListD,
				partSeriesDO, languageDTO);

		List<ProductVO> productList = new ArrayList<ProductVO>();
		// 查询该系列下所有产品
		List<PartDO> partDOList = new ArrayList<PartDO>();
		if ("0".equals(line)) {
			partDOList = partMapper.queryOrderByDeq(serialNumber, line);
		}
		if ("1".equals(line)) {
			List<PartDO> partDOList0 = partMapper.queryOrderByDeq(serialNumber, "0");
			List<PartDO> partDOList1 = partMapper.queryOrderByDeq(serialNumber, "1");
			if (partDOList1.isEmpty()) {
				partDOList = partDOList0;
			} else {
				Map<String, PartDO> map = new LinkedHashMap<String, PartDO>();
				for (PartDO partDO : partDOList0) {
					map.put(partDO.getSeq() + partDO.getProductType(), partDO);
				}
				for (PartDO partDO : partDOList1) {
					map.put(partDO.getSeq() + partDO.getProductType(), partDO);
				}
				// 遍历map
				for (Map.Entry<String, PartDO> entry : map.entrySet()) {
					partDOList.add(entry.getValue());
				}
			}
		}

		Map<String, String> productMap = new HashMap<String, String>();
		for (PartDO partDO : partDOList) {
			productMap.put(partDO.getProductType(), "");
		}

		for (Map.Entry<String, String> entry : productMap.entrySet()) {
			for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
				if (entry.getKey().equals(dictionaryVO2.getValue())) {
					if (languageDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
						entry.setValue(dictionaryVO2.getValueCn());
					}
					if (languageDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
						entry.setValue(dictionaryVO2.getValueEn());
					}
				}
			}
		}

		for (Map.Entry<String, String> entry : productMap.entrySet()) {
			ProductVO productVO = new ProductVO();
			productVO.setProductType(entry.getKey());
			productVO.setProductName(entry.getValue());
			productList.add(productVO);
		}
		partSeriesOutVO.setProductList(productList);
		commonVO.setVoT(partSeriesOutVO);
		return commonVO;

	}

	private PartSeriesOutVO setPartSeriesOut(List<DictionaryVO> dictionaryVOList, List<DictionaryVO> dictionaryVOList2,
			List<PartSeriesDO> partSeriesDOListD, PartSeriesDO partSeriesDO, LanguageDTO languageDTO) {
		PartSeriesOutVO partSeriesOutVO = new PartSeriesOutVO();
		if (languageDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
			List<RecommendVO> recommendVOList = new ArrayList<RecommendVO>();
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (partSeriesDO.getSerialNumber().equals(dictionaryVO.getValue())) {
					partSeriesOutVO.setName(dictionaryVO.getValueCn());
				}
			}
			partSeriesOutVO.setContentMobile(partSeriesDO.getContentMobileCn());
			partSeriesOutVO.setContentPc(partSeriesDO.getContentPcCn());
			partSeriesOutVO.setDescription(partSeriesDO.getDescriptionCn());
			partSeriesOutVO.setHomeDescription(partSeriesDO.getHomeDescriptionCn());
			partSeriesOutVO.setContentMobile(partSeriesDO.getContentMobileCn());
			partSeriesOutVO.setPrice(partSeriesDO.getPriceCn());
			partSeriesOutVO.setButtonLink(partSeriesDO.getButtonLinkCn());
			partSeriesOutVO.setButtonName(partSeriesDO.getButtonNameCn());
			partSeriesOutVO.setSeoTitle(partSeriesDO.getSeoTitleCn());
			partSeriesOutVO.setSeoKeyword(partSeriesDO.getSeoKeywordCn());
			partSeriesOutVO.setSeoDescription(partSeriesDO.getSeoDescriptionCn());

			String[] recommendArray = partSeriesDO.getRecommendId().split(",");
			String[] recommendUrlArray = partSeriesDO.getRecommendUrl().split(",");
			for (String recommend : recommendArray) {
				RecommendVO recommendVO = new RecommendVO();
				recommendVO.setRecommendId(recommend.substring(recommend.indexOf("-") + 1));
				if (recommend.substring(0, recommend.indexOf("-")).equals("0")) {
					for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
						if (recommend.substring(recommend.indexOf("-") + 1).equals(dictionaryVO2.getValue())) {
							recommendVO.setName(dictionaryVO2.getValueCn());
						}
					}
				}
				if (recommend.substring(0, recommend.indexOf("-")).equals("1")) {
					for (PartSeriesDO partSeriesDOD : partSeriesDOListD) {
						if (recommend.substring(recommend.indexOf("-") + 1).equals(partSeriesDOD.getSerialNumber().toString())) {
							for (DictionaryVO dictionaryVO : dictionaryVOList) {
								if (partSeriesDOD.getSerialNumber().equals(dictionaryVO.getValue())) {
									recommendVO.setName(dictionaryVO.getValueCn());
								}
							}
						}
					}
				}
				recommendVO.setType(recommend.substring(0, recommend.indexOf("-")));
				recommendVOList.add(recommendVO);
			}

			for (int i = 0; i < recommendVOList.size(); i++) {
				recommendVOList.get(i).setRecommendUrl(recommendUrlArray[i]);
			}
			partSeriesOutVO.setRecommendVOList(recommendVOList);
			BeanUtils.copyProperties(partSeriesDO, partSeriesOutVO);
		}
		if (languageDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
			List<RecommendVO> recommendVOList = new ArrayList<RecommendVO>();
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (partSeriesDO.getSerialNumber().equals(dictionaryVO.getValue())) {
					partSeriesOutVO.setName(dictionaryVO.getValueEn());
				}
			}
			partSeriesOutVO.setContentMobile(partSeriesDO.getContentMobileEn());
			partSeriesOutVO.setContentPc(partSeriesDO.getContentPcEn());
			partSeriesOutVO.setDescription(partSeriesDO.getDescriptionEn());
			partSeriesOutVO.setHomeDescription(partSeriesDO.getHomeDescriptionEn());
			partSeriesOutVO.setContentMobile(partSeriesDO.getContentMobileEn());
			partSeriesOutVO.setPrice(partSeriesDO.getPriceEn());
			partSeriesOutVO.setButtonLink(partSeriesDO.getButtonLinkEn());
			partSeriesOutVO.setButtonName(partSeriesDO.getButtonNameEn());
			partSeriesOutVO.setSeoTitle(partSeriesDO.getSeoTitleEn());
			partSeriesOutVO.setSeoKeyword(partSeriesDO.getSeoKeywordEn());
			partSeriesOutVO.setSeoDescription(partSeriesDO.getSeoDescriptionEn());

			String[] recommendArray = partSeriesDO.getRecommendId().split(",");
			String[] recommendUrlArray = partSeriesDO.getRecommendUrl().split(",");
			for (String recommend : recommendArray) {
				RecommendVO recommendVO = new RecommendVO();
				recommendVO.setRecommendId(recommend.substring(recommend.indexOf("-") + 1));
				if (recommend.substring(0, recommend.indexOf("-")).equals("0")) {
					for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
						if (recommend.substring(recommend.indexOf("-") + 1).equals(dictionaryVO2.getValue())) {
							recommendVO.setName(dictionaryVO2.getValueEn());
						}
					}
				}
				if (recommend.substring(0, recommend.indexOf("-")).equals("1")) {
					for (PartSeriesDO partSeriesDOD : partSeriesDOListD) {
						if (recommend.substring(recommend.indexOf("-") + 1).equals(partSeriesDOD.getSerialNumber().toString())) {
							for (DictionaryVO dictionaryVO : dictionaryVOList) {
								if (partSeriesDOD.getSerialNumber().equals(dictionaryVO.getValue())) {
									recommendVO.setName(dictionaryVO.getValueEn());
								}
							}
						}
					}
				}
				recommendVO.setType(recommend.substring(0, recommend.indexOf("-")));
				recommendVOList.add(recommendVO);
			}

			for (int i = 0; i < recommendVOList.size(); i++) {
				recommendVOList.get(i).setRecommendUrl(recommendUrlArray[i]);
			}
			partSeriesOutVO.setRecommendVOList(recommendVOList);
			BeanUtils.copyProperties(partSeriesDO, partSeriesOutVO);
		}
		return partSeriesOutVO;

	}

}
