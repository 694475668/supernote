package com.ratta.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
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
import com.ratta.dto.PartDTO;
import com.ratta.dto.PartLanguageDTO;
import com.ratta.dto.PartQueryDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.PartMapper;
import com.ratta.mapper.PartSeriesMapper;
import com.ratta.service.PartService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.DictionaryVO;
import com.ratta.vo.PartCustomizeVO;
import com.ratta.vo.PartHomeVO;
import com.ratta.vo.PartOutVO;
import com.ratta.vo.PartVO;
import com.ratta.vo.RecommendVO;
import com.ratta.vo.UserBeanVO;

/**
 * @author gxy
 *
 */
@Service
public class PartServiceImpl implements PartService {

	@Autowired
	private SystemBaseUtil systemBaseUtil;

	@Autowired
	private PartMapper partMapper;

	@Autowired
	private PartSeriesMapper partSeriesMapper;

	@SuppressWarnings("unchecked")
	@Override
	public CommonListVO<PartVO> queryInner(PartQueryDTO partQueryDTO) {
		PartDO partDO = new PartDO();
		BeanUtils.copyProperties(partQueryDTO, partDO);
		PageInfo<PartDO> pageInfo = PageHelper.startPage(partQueryDTO.getPageNo(), partQueryDTO.getPageSize())
				.setOrderBy("serial_number,product_type,line,seq asc").doSelectPageInfo(() -> partMapper.query(partDO));

		CommonListVO<PartVO> commonListVO = new CommonListVO<PartVO>();
		BeanUtils.copyProperties(pageInfo, commonListVO);
		commonListVO.setVoList(ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), PartVO.class));
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART");
		List<DictionaryVO> dictionaryVOList2 = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
		List<DictionaryVO> dictionaryVOList3 = systemBaseUtil.queryDictionaryNameAndValue("PART_SERIES");
		for (PartVO partVO : commonListVO.getVoList()) {
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (partVO.getProductNumber().equals(dictionaryVO.getValue())) {
					partVO.setNameCn(dictionaryVO.getValueCn());
					partVO.setNameEn(dictionaryVO.getValueEn());
				}
			}
			for (DictionaryVO dictionaryVO3 : dictionaryVOList3) {
				if (partVO.getSerialNumber().equals(dictionaryVO3.getValue())) {
					partVO.setSerialName(dictionaryVO3.getValueCn());
				}
			}
			if (partVO.getProductType().contains(",")) {
				String[] productTypeArray = partVO.getProductType().split(",");
				String productTypeName = "";
				for (String productType : productTypeArray) {
					for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
						if (productType.equals(dictionaryVO2.getValue())) {
							productTypeName = productTypeName + dictionaryVO2.getValueCn() + ",";
						}
					}
				}
				partVO.setProductType(productTypeName.substring(0, productTypeName.length() - 1));
			} else {
				for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
					if (partVO.getProductType().equals(dictionaryVO2.getValue())) {
						partVO.setProductType(dictionaryVO2.getValueCn());
					}
				}
			}
		}
		return commonListVO;
	}

	@Override
	public CommonVO<PartVO> queryById(int id) {
		PartDO partDO = partMapper.queryById(id);
		CommonVO<PartVO> commonVO = new CommonVO<PartVO>();
		if (partDO == null) {
			commonVO.setSuccess(false);
			commonVO.setErrorCode(ErrorCodeEnum.E1441.getKey());
			commonVO.setErrorMsg(ErrorCodeEnum.E1441.getValue());
			return commonVO;
		}
		PartVO partVO = new PartVO();
		BeanUtils.copyProperties(partDO, partVO);
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART");
		List<DictionaryVO> dictionaryVOList2 = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
		List<DictionaryVO> dictionaryVOList3 = systemBaseUtil.queryDictionaryNameAndValue("PART_SERIES");
		for (DictionaryVO dictionaryVO : dictionaryVOList) {
			if (dictionaryVO.getValue().equals(partDO.getProductNumber())) {
				partVO.setNameCn(dictionaryVO.getValueCn());
				partVO.setNameEn(dictionaryVO.getValueEn());
			}
		}
		for (DictionaryVO dictionaryVO3 : dictionaryVOList3) {
			if (partVO.getSerialNumber().equals(dictionaryVO3.getValue())) {
				partVO.setSerialName(dictionaryVO3.getValueCn());
			}
		}
		if (partVO.getProductType().contains(",")) {
			String[] productTypeArray = partVO.getProductType().split(",");
			String productTypeName = "";
			for (String productType : productTypeArray) {
				for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
					if (productType.equals(dictionaryVO2.getValue())) {
						productTypeName = productTypeName + dictionaryVO2.getValueCn() + ",";
					}
				}
			}
			partVO.setProductType(productTypeName.substring(0, productTypeName.length() - 1));
		} else {
			for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
				if (partVO.getProductType().equals(dictionaryVO2.getValue())) {
					partVO.setProductType(dictionaryVO2.getValueCn());
				}
			}
		}
		commonVO.setVoT(partVO);
		return commonVO;

	}

	@Override
	public BaseVO add(PartDTO partDTO, String userId) {
		BaseVO baseVO = new BaseVO();
		String productType = partDTO.getProductType();
		if (productType.contains(",")) {
			String[] productTypeArray = productType.split(",");
			Arrays.sort(productTypeArray);
			productType = "";
			for (String productTypes : productTypeArray) {
				productType = productType + productTypes + ",";
			}
			productType = productType.substring(0, productType.length() - 1);
		}

		partDTO.setProductType(productType);
		PartDO partDO = new PartDO();
		BeanUtils.copyProperties(partDTO, partDO);
		if (partDTO.getSeq() == null) {
			int maxSeq = partMapper.queryMaxSeq(partDTO.getSerialNumber(), partDTO.getProductType());
			partDO.setSeq(maxSeq + 1);
		}
		if (partDTO.getHomeFlag().equals("0")) {
			partMapper.updateHomeFlag(partDTO.getSerialNumber());
		}
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		partDO.setCreateUser(userBeanVO.getUsername());
		partDO.setUpdateUser(userBeanVO.getUsername());
		partDO.setUpdateTime(new Date());
		partDO.setCreateTime(new Date());
		partMapper.insert(partDO);
		return baseVO;
	}

	@Override
	public BaseVO update(PartDTO partDTO, int id, String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		PartDO partDO = new PartDO();
		BeanUtils.copyProperties(partDTO, partDO);
		if (partDTO.getHomeFlag().equals("0")) {
			partMapper.updateHomeFlag(partDTO.getSerialNumber());
		}
		partDO.setId(id);
		partDO.setUpdateTime(new Date());
		partDO.setUpdateUser(userBeanVO.getUsername());
		partMapper.update(partDO);
		return new BaseVO();
	}

	@Override
	public BaseVO delete(int id) {
		partMapper.delete(id);
		return new BaseVO();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateLine(LineDTO lineDTO, int id, String userId) {
		BaseVO baseVO = new BaseVO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		PartDO partDO = partMapper.queryById(id);
		// 如果是将状态改为上线
		if (lineDTO.getLine().equals("0")) {
			// 如果原来状态为预览或者下线
			if (partDO.getLine().equals("1") || partDO.getLine().equals("2")) {
				PartDO partDOQ = partMapper.queryBySeqAndLine(partDO.getSerialNumber(), partDO.getProductNumber(),
						partDO.getSeq(), "0");
				// 如果原来上线状态没有这个序号
				if (partDOQ == null) {
					update(id, "0", userBeanVO);
				}
				// 如果原来上线有这个序号
				if (partDOQ != null) {
					update(partDOQ.getId(), "2", userBeanVO);
					update(id, "0", userBeanVO);
				}
			}
			// 如果原来状态为上线状态
			if (partDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为预览
		if (lineDTO.getLine().equals("1")) {
			// 如果原来状态为上线或者下线
			if (partDO.getLine().equals("0") || partDO.getLine().equals("2")) {
				PartDO partDOQ = partMapper.queryBySeqAndLine(partDO.getSerialNumber(), partDO.getProductNumber(),
						partDO.getSeq(), "1");
				// 如果原来预览状态没有这个序号
				if (partDOQ == null) {
					update(id, "1", userBeanVO);
				}
				// 如果原来预览有这个序号
				if (partDOQ != null) {
					update(partDOQ.getId(), "2", userBeanVO);
					update(id, "1", userBeanVO);
				}
			}
			// 如果原来状态为预览状态
			if (partDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为下线
		if (lineDTO.getLine().equals("2")) {
			// 如果原来状态为上线或者预览
			if (partDO.getLine().equals("0") || partDO.getLine().equals("1")) {
				update(id, "2", userBeanVO);
			}
			// 如果原来状态为下线状态
			if (partDO.getLine().equals("2")) {
				return baseVO;
			}
		}
		return baseVO;
	}

	private void update(int id, String line, UserBeanVO userBeanVO) {
		PartDO partDO = new PartDO();
		partDO.setId(id);
		partDO.setLine(line);
		partDO.setUpdateTime(new Date());
		partDO.setUpdateUser(userBeanVO.getUsername());
		partMapper.updateLine(partDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateSeq(SeqDTO seqDTO, int id, String userId) {
		PartDO partDO = partMapper.queryById(id);
		if (partDO == null) {
			return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
		}

		if (seqDTO.getSeq() == partDO.getSeq()) {
			return new BaseVO();
		}

		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		PartDO partDOQ = new PartDO();
		BeanUtils.copyProperties(seqDTO, partDOQ);
		partDOQ.setId(id);
		partDOQ.setUpdateTime(new Date());
		partDOQ.setUpdateUser(userBeanVO.getUsername());
		if (!"2".equals(partDO.getLine())) {
			// 同步修改整体排序
			partMapper.updateAllSeq(partDO.getSeq(), seqDTO.getSeq(), partDO.getSerialNumber(), partDO.getProductType(),
					partDO.getLine());
		}
		partMapper.updateSeq(partDOQ);
		return new BaseVO();
	}

	@Override
	public CommonVO<PartCustomizeVO> queryPartCustomize(PartLanguageDTO partLanguageDTO, String line) {
		CommonVO<PartCustomizeVO> commonVO = new CommonVO<PartCustomizeVO>();
		PartCustomizeVO partCustomizeVO = new PartCustomizeVO();

		List<PartDO> partDOList = new ArrayList<PartDO>();
		if ("0".equals(line)) {
			partDOList = partMapper.queryByProductType(partLanguageDTO.getProductType(), line);
		}
		if ("1".equals(line)) {
			List<PartDO> partDOList0 = partMapper.queryByProductType(partLanguageDTO.getProductType(), "0");
			List<PartDO> partDOList1 = partMapper.queryByProductType(partLanguageDTO.getProductType(), "1");
			if (partDOList1.isEmpty()) {
				partDOList = partDOList0;
			} else {
				Map<String, PartDO> map = new LinkedHashMap<String, PartDO>();
				for (PartDO partDO : partDOList0) {
					map.put(partDO.getSeq() + partDO.getProductType() + partDO.getSerialNumber(), partDO);
				}
				for (PartDO partDO : partDOList1) {
					map.put(partDO.getSeq() + partDO.getProductType() + partDO.getSerialNumber(), partDO);
				}
				// 遍历map
				for (Map.Entry<String, PartDO> entry : map.entrySet()) {
					partDOList.add(entry.getValue());
				}
			}
		}
		// 封套系列
		List<PartSeriesDO> partSeriesDOCoverList = partSeriesMapper.queryOrderByDeq("2", line);
		List<PartOutVO> coverPartList = new ArrayList<PartOutVO>();
		coverPartList = setPartOutVO(coverPartList, partSeriesDOCoverList, partDOList, partLanguageDTO.getLanguage());
		partCustomizeVO.setCoverPartList(coverPartList);

		// 笔系列
		List<PartSeriesDO> partSeriesDOPenList = partSeriesMapper.queryOrderByDeq("0", line);
		List<PartOutVO> penPartList = new ArrayList<PartOutVO>();
		coverPartList = setPartOutVO(penPartList, partSeriesDOPenList, partDOList, partLanguageDTO.getLanguage());
		partCustomizeVO.setPenPartList(penPartList);

		commonVO.setVoT(partCustomizeVO);
		return commonVO;
	}

	private List<PartOutVO> setPartOutVO(List<PartOutVO> partOutVOList, List<PartSeriesDO> partSeriesDOList,
			List<PartDO> partDOList, String language) {
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART");
		List<DictionaryVO> dictionaryVOList2 = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
		for (PartSeriesDO partSeriesDO : partSeriesDOList) {
			List<PartOutVO> partListInner = new ArrayList<PartOutVO>();
			for (PartDO partDOQ : partDOList) {
				if (partDOQ.getSerialNumber().equals(partSeriesDO.getSerialNumber())) {
					PartOutVO partOutVO = new PartOutVO();
					if (language.toUpperCase().equals(Constant.CN)) {
						partOutVO.setColourName(partDOQ.getColourNameCn());
					}
					if (language.toUpperCase().equals(Constant.EN)) {
						partOutVO.setColourName(partDOQ.getColourNameEn());
					}
					BeanUtils.copyProperties(partDOQ, partOutVO);
					partListInner.add(partOutVO);
				}
			}
			// 对某个封套系列进行排序，并赋值coverPartList
			Collections.sort(partListInner, new Comparator<PartOutVO>() {
				@Override
				public int compare(PartOutVO o1, PartOutVO o2) {
					int diff = o1.getSeq() - o2.getSeq();
					if (diff > 0) {
						return 1;
					} else if (diff < 0) {
						return -1;
					}
					return 0;
				}
			});

			for (PartOutVO partOutVO : partListInner) {
				PartOutVO partOutVONew = new PartOutVO();
				BeanUtils.copyProperties(partOutVO, partOutVONew);
				for (DictionaryVO dictionaryVO : dictionaryVOList) {
					if (partOutVO.getProductNumber().equals(dictionaryVO.getValue())) {
						if (language.toUpperCase().equals(Constant.CN)) {
							partOutVONew.setName(dictionaryVO.getValueCn());
						}
						if (language.toUpperCase().equals(Constant.EN)) {
							partOutVONew.setName(dictionaryVO.getValueEn());
						}
					}
				}
				if (partOutVONew.getProductType().contains(",")) {
					String[] productTypeArray = partOutVONew.getProductType().split(",");
					String productTypeName = "";
					for (String productType : productTypeArray) {
						for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
							if (productType.equals(dictionaryVO2.getValue())) {
								productTypeName = productTypeName + dictionaryVO2.getValueCn() + ",";
							}
						}
					}
					partOutVONew.setProductType(productTypeName.substring(0, productTypeName.length() - 1));
				} else {
					for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
						if (partOutVONew.getProductType().equals(dictionaryVO2.getValue())) {
							partOutVONew.setProductType(dictionaryVO2.getValueCn());
						}
					}
				}
				partOutVOList.add(partOutVONew);
			}
		}
		return partOutVOList;

	}

	@Override
	public CommonNoPageListVO<PartHomeVO> queryPartHome(LanguageDTO languageDTO, String line) {

		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART_SERIES");
		List<DictionaryVO> dictionaryVOList2 = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");

		List<PartSeriesDO> partSeriesDOListD = partSeriesMapper.queryOrderByDeq(null, line);

		CommonNoPageListVO<PartHomeVO> commonNoPageListVO = new CommonNoPageListVO<PartHomeVO>();

		// 笔系列
		List<PartSeriesDO> partSeriesDOPenList = partSeriesMapper.queryOrderByDeq("0", line);
		// 封套系列
		List<PartSeriesDO> partSeriesDOCoverList = partSeriesMapper.queryOrderByDeq("2", line);

		List<PartSeriesDO> partSeriesDOList = new ArrayList<>();
		for (PartSeriesDO partSeriesDO : partSeriesDOPenList) {
			partSeriesDOList.add(partSeriesDO);
		}
		for (PartSeriesDO partSeriesDO : partSeriesDOCoverList) {
			partSeriesDOList.add(partSeriesDO);
		}

		List<PartHomeVO> partHomeVOList = new ArrayList<PartHomeVO>();

		for (PartSeriesDO partSeriesDO : partSeriesDOList) {
			// 查询出该系列下所有配件信息
			List<PartDO> partDOList = getPartList(partSeriesDO.getSerialNumber(), line);
			List<String> colourUrlList = new ArrayList<String>();
			// 给colorList塞值
			String homeUrl = "";
			for (PartDO partDOQ : partDOList) {
				colourUrlList.add(partDOQ.getColourUrl());
				if (partDOQ.getHomeFlag().equals("0")) {
					homeUrl = partDOQ.getHomeUrl();
				}
			}
			PartHomeVO partHomeVO = new PartHomeVO();
			partHomeVO.setColourUrlList(colourUrlList);
			partHomeVO.setHomeUrl(homeUrl);
			partHomeVO.setMutiUrl(partSeriesDO.getMutiUrl());
			if (languageDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
				partHomeVO.setPrice(partSeriesDO.getPriceCn());
				partHomeVO.setHomeDescription(partSeriesDO.getHomeDescriptionCn());
				partHomeVO.setDescription(partSeriesDO.getDescriptionCn());
				partHomeVO.setContentPc(partSeriesDO.getContentPcCn());
				partHomeVO.setContentMobile(partSeriesDO.getContentMobileCn());
				for (DictionaryVO dictionaryVO : dictionaryVOList) {
					if (dictionaryVO.getValue().equals(partSeriesDO.getSerialNumber())) {
						partHomeVO.setName(dictionaryVO.getValueCn());
					}
				}
			}
			if (languageDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
				partHomeVO.setPrice(partSeriesDO.getPriceEn());
				partHomeVO.setHomeDescription(partSeriesDO.getHomeDescriptionEn());
				partHomeVO.setDescription(partSeriesDO.getDescriptionEn());
				partHomeVO.setContentPc(partSeriesDO.getContentPcEn());
				partHomeVO.setContentMobile(partSeriesDO.getContentMobileEn());
				for (DictionaryVO dictionaryVO : dictionaryVOList) {
					if (dictionaryVO.getValue().equals(partSeriesDO.getSerialNumber())) {
						partHomeVO.setName(dictionaryVO.getValueEn());
					}
				}
			}

			List<RecommendVO> recommendVOList = new ArrayList<RecommendVO>();

			String[] recommendArray = partSeriesDO.getRecommendId().split(",");
			String[] recommendUrlArray = partSeriesDO.getRecommendUrl().split(",");
			for (String recommend : recommendArray) {
				RecommendVO recommendVO = new RecommendVO();
				recommendVO.setRecommendId(recommend.substring(recommend.indexOf("-") + 1));
				recommendVO.setType(recommend.substring(0, recommend.indexOf("-")));

				if (recommend.substring(0, recommend.indexOf("-")).equals("0")) {
					for (DictionaryVO dictionaryVO2 : dictionaryVOList2) {
						if (recommend.substring(recommend.indexOf("-") + 1).equals(dictionaryVO2.getValue())) {
							if (languageDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
								recommendVO.setName(dictionaryVO2.getValueCn());
							}
							if (languageDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
								recommendVO.setName(dictionaryVO2.getValueEn());
							}
						}
					}
				}
				if (recommend.substring(0, recommend.indexOf("-")).equals("1")) {
					for (PartSeriesDO partSeriesDOD : partSeriesDOListD) {
						if (recommend.substring(recommend.indexOf("-") + 1).equals(partSeriesDOD.getId().toString())) {
							for (DictionaryVO dictionaryVO : dictionaryVOList) {
								if (partSeriesDOD.getSerialNumber().equals(dictionaryVO.getValue())) {
									if (languageDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
										recommendVO.setName(dictionaryVO.getValueCn());
									}
									if (languageDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
										recommendVO.setName(dictionaryVO.getValueEn());
									}
								}
							}
						}
					}
				}
				recommendVOList.add(recommendVO);
			}

			for (int i = 0; i < recommendVOList.size(); i++) {
				recommendVOList.get(i).setRecommendUrl(recommendUrlArray[i]);
			}
			partHomeVO.setRecommendVOList(recommendVOList);
			BeanUtils.copyProperties(partSeriesDO, partHomeVO);
			partHomeVOList.add(partHomeVO);
		}

		commonNoPageListVO.setVoList(partHomeVOList);
		return commonNoPageListVO;
	}

	@Override
	public CommonNoPageListVO<PartOutVO> queryPartOut(LanguageDTO languageDTO, String serialNumber, String line) {
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PART");
		CommonNoPageListVO<PartOutVO> commonNoPageListVO = new CommonNoPageListVO<PartOutVO>();
		List<PartOutVO> partOutVOList = new ArrayList<PartOutVO>();
		List<PartDO> partDOList = getPartList(serialNumber, line);

		for (PartDO partDO : partDOList) {
			PartOutVO partOutVO = new PartOutVO();
			BeanUtils.copyProperties(partDO, partOutVO);
			if (languageDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
				partOutVO.setColourName(partDO.getColourNameCn());
				for (DictionaryVO dictionaryVO : dictionaryVOList) {
					if (dictionaryVO.getValue().equals(partDO.getProductNumber())) {
						partOutVO.setName(dictionaryVO.getValueCn());
					}
				}
			}
			if (languageDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
				partOutVO.setColourName(partDO.getColourNameEn());
				for (DictionaryVO dictionaryVO : dictionaryVOList) {
					if (dictionaryVO.getValue().equals(partDO.getProductNumber())) {
						partOutVO.setName(dictionaryVO.getValueEn());
					}
				}

			}

			partOutVOList.add(partOutVO);
		}
		commonNoPageListVO.setVoList(partOutVOList);
		return commonNoPageListVO;
	}

	private List<PartDO> getPartList(String serialNumber, String line) {
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
		return partDOList;
	}
}
