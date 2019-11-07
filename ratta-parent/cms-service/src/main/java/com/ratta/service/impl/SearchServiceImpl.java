package com.ratta.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.FirmwareManualDO;
import com.ratta.domain.SearchDO;
import com.ratta.dto.SearchDTO;
import com.ratta.mapper.FirmwareManualMapper;
import com.ratta.mapper.SearchMapper;
import com.ratta.service.SearchService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.SearchVO;

/**
 * 业务实现层
 * @author page
 */
@Service
public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchMapper searchMapper;
	@Autowired
	private FirmwareManualMapper firmwareManualMapper;

	@SuppressWarnings("unchecked")
	@Override
	public CommonListVO<SearchVO> search(SearchDTO searchDTO) {
		CommonListVO<SearchVO> commonListVO = new CommonListVO<SearchVO>();
		PageInfo<SearchDO> pageInfo  = null;
		if(searchDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
			pageInfo = PageHelper.startPage(searchDTO.getPageNo(), searchDTO.getPageSize())
					.doSelectPageInfo(() -> searchMapper.search(searchDTO.getKey(),"zh-CN"));
		}
        if(searchDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
        	pageInfo = PageHelper.startPage(searchDTO.getPageNo(), searchDTO.getPageSize())
    				.doSelectPageInfo(() -> searchMapper.search(searchDTO.getKey(),"en"));
		}
		
		BeanUtils.copyProperties(pageInfo, commonListVO);
		List<SearchVO> searchVOList = ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(),SearchVO.class);
		for (SearchVO searchVO : searchVOList) {
			if("1".equals(searchVO.getType()) || "2".equals(searchVO.getType())) {
				FirmwareManualDO firmwareManualDO  = firmwareManualMapper.queryById(searchVO.getId());
				if(searchDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
					searchVO.setUrl(firmwareManualDO.getUrlCn());
				}
	            if(searchDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
	            	searchVO.setUrl(firmwareManualDO.getUrlEn());
				}
			}
		}
		commonListVO.setVoList(searchVOList);
		return commonListVO;
	}
}
