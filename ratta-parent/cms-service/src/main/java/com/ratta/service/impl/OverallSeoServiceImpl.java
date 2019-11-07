package com.ratta.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.OverallSeoDO;
import com.ratta.dto.OverAllSeoLanAndPageDTO;
import com.ratta.dto.OverAllSeoQueryDTO;
import com.ratta.dto.OverallSeoDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.OverallSeoMapper;
import com.ratta.service.OverallSeoService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.DictionaryVO;
import com.ratta.vo.OverallSeoPartVO;
import com.ratta.vo.OverallSeoVO;
import com.ratta.vo.UserBeanVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 业务实现层
 * 
 * @author page
 */
@Service
@Slf4j
public class OverallSeoServiceImpl implements OverallSeoService {

	@Autowired
	private OverallSeoMapper overallSeoMapper;
	@Autowired
	private SystemBaseUtil systemBaseUtil;

	@Override
	@SuppressWarnings("unchecked")
	public CommonListVO<OverallSeoVO> query(OverAllSeoQueryDTO overAllSeoQueryDTO) {
		OverallSeoDO overallSeoDO = new OverallSeoDO();
		BeanUtils.copyProperties(overAllSeoQueryDTO, overallSeoDO);
		PageInfo<OverallSeoDO> pageInfo = PageHelper
				.startPage(overAllSeoQueryDTO.getPageNo(), overAllSeoQueryDTO.getPageSize()).setOrderBy("update_time desc")
				.doSelectPageInfo(() -> overallSeoMapper.queryAll(overallSeoDO));

		CommonListVO<OverallSeoVO> commonListVO = new CommonListVO<OverallSeoVO>();
		BeanUtils.copyProperties(pageInfo, commonListVO);
		List<OverallSeoVO> overallSeoVOList = ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), OverallSeoVO.class);
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PAGE_SERIAL");
		for (OverallSeoVO overallSeoVO : overallSeoVOList) {
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (overallSeoVO.getPageSerial().equals(dictionaryVO.getValue())) {
					overallSeoVO.setPageSerial(dictionaryVO.getValueCn());
				}
			}
		}

		commonListVO.setVoList(overallSeoVOList);
		return commonListVO;
	}

	@Override
	public BaseVO save(OverallSeoDTO overallSeoDTO,String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		OverallSeoDO overallSeoDO = new OverallSeoDO();
		BeanUtils.copyProperties(overallSeoDTO, overallSeoDO);
		List<OverallSeoDO> overallSeoDOList= overallSeoMapper.queryAll(overallSeoDO);
		if (overallSeoDOList != null && overallSeoDOList.size() > 0) {
			log.error("该页面已有SEO！");
			return new BaseVO(false, ErrorCodeEnum.E1403.getKey(), ErrorCodeEnum.E1403.getValue());
		}
		
		overallSeoDO.setCreateTime(new Date());
		overallSeoDO.setCreateUser(userBeanVO.getUsername());
		overallSeoDO.setUpdateTime(new Date());
		overallSeoDO.setUpdateUser(userBeanVO.getUsername());
		overallSeoMapper.save(overallSeoDO);
		return new BaseVO();
	}

	@Override
	public BaseVO delete(int id) {
		overallSeoMapper.delete(id);
		return new BaseVO();
	}

	@Override
	public CommonVO<OverallSeoVO> queryById(int id) {
		OverallSeoDO overallSeoDO = overallSeoMapper.queryById(id);
		CommonVO<OverallSeoVO> commonVO = new CommonVO<OverallSeoVO>();
		if (overallSeoDO == null) {
			commonVO.setSuccess(false);
			commonVO.setErrorCode(ErrorCodeEnum.E1441.getKey());
			commonVO.setErrorMsg(ErrorCodeEnum.E1441.getValue());
			return commonVO;
		}
		OverallSeoVO overallSeoVO = new OverallSeoVO();
		BeanUtils.copyProperties(overallSeoDO, overallSeoVO);
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PAGE_SERIAL");
		for (DictionaryVO dictionaryVO : dictionaryVOList) {
			if (overallSeoVO.getPageSerial().equals(dictionaryVO.getValue())) {
				overallSeoVO.setPageSerial(dictionaryVO.getValueCn());
			}
		}
		commonVO.setVoT(overallSeoVO);
		return commonVO;
	}

	@Override
	public BaseVO update(OverallSeoDTO overallSeoDTO, int id,String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		OverallSeoDO overallSeoDO = new OverallSeoDO();
		BeanUtils.copyProperties(overallSeoDTO, overallSeoDO);
		overallSeoDO.setId(id);
		overallSeoDO.setUpdateTime(new Date());
		overallSeoDO.setUpdateUser(userBeanVO.getUsername());
		overallSeoMapper.update(overallSeoDO);
		return new BaseVO();
	}
	
	@Override
	public CommonVO<OverallSeoPartVO> queryByLan(OverAllSeoLanAndPageDTO overAllSeoLanAndPageDTO) {
		OverallSeoDO overallSeoDO = new OverallSeoDO();
		BeanUtils.copyProperties(overAllSeoLanAndPageDTO, overallSeoDO);
		OverallSeoPartVO overallSeoPartVO = new OverallSeoPartVO();
		CommonVO<OverallSeoPartVO> commonVO = new CommonVO<OverallSeoPartVO>();
		List<OverallSeoDO> overallSeoDOList= overallSeoMapper.queryAll(overallSeoDO);
		if (overallSeoDOList != null && overallSeoDOList.size() > 0) {
			OverallSeoDO overallSeo= overallSeoMapper.queryAll(overallSeoDO).get(0);
			if(overAllSeoLanAndPageDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
				overallSeoPartVO.setSeoTitle(overallSeo.getSeoTitleCn());
				overallSeoPartVO.setSeoKeyword(overallSeo.getSeoKeywordCn());
				overallSeoPartVO.setSeoDescription(overallSeo.getSeoDescriptionCn());
			}
	        if(overAllSeoLanAndPageDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
	        	overallSeoPartVO.setSeoTitle(overallSeo.getSeoTitleEn());
	        	overallSeoPartVO.setSeoKeyword(overallSeo.getSeoKeywordEn());
				overallSeoPartVO.setSeoDescription(overallSeo.getSeoDescriptionEn());
			}
		}
		
		commonVO.setVoT(overallSeoPartVO);
		return commonVO;
	}
}
