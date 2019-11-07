package com.ratta.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.domain.CustomizeDO;
import com.ratta.dto.CustomizeAllDTO;
import com.ratta.dto.CustomizeDTO;
import com.ratta.dto.CustomizeRemarkDTO;
import com.ratta.dto.SendEmailDTO;
import com.ratta.mapper.CustomizeMapper;
import com.ratta.service.CustomizeService;
import com.ratta.stream.CmsChannel;
import com.ratta.util.EmailContentUtil;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CustomizeAllVO;
import com.ratta.vo.DictionaryVO;
import com.ratta.vo.UserBeanVO;

@Service
@RefreshScope
@EnableBinding(CmsChannel.class)
public class CustomizeServiceImpl implements CustomizeService {

	@Autowired
	private SystemBaseUtil systemBaseUtil;
	@Autowired
	private CustomizeMapper customizeMapper;

	@Resource(name = CmsChannel.BASE_OUTPUT)
	private MessageChannel sendMessage;

	@Value("${marketEmail}")
	private String marketEmail;
	@Value("${email.logo.url}")
	private String email_logo_url;
	@Value("${email.logo.id}")
	private String srcId;

	private static final String ORDERBY = "create_time desc";
	private static final String SEND = "send";
	private static final String EMAIL = "email";
	private static final String subject = "套餐定制提醒";

	@SuppressWarnings("static-access")
	@Override
	public BaseVO addCustomize(CustomizeDTO customizeDTO) {
		CustomizeDO customizeDO = new CustomizeDO();
		BeanUtils.copyProperties(customizeDTO, customizeDO);
		customizeMapper.insert(customizeDO);
		
		EmailContentUtil content = new EmailContentUtil();
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
		for (DictionaryVO dictionaryVO : dictionaryVOList) {
			if (customizeDTO.getProduct().equals(dictionaryVO.getValue())) {
				customizeDTO.setProduct(dictionaryVO.getValueCn());
			}
		}
		String text = content.emailCustomize(srcId,customizeDTO);
		SendEmailDTO sendEmailDTO = new SendEmailDTO(marketEmail, subject, text, email_logo_url, srcId);
		sendMessage.send(MessageBuilder.withPayload(sendEmailDTO).setHeader(EMAIL, SEND).build());
		return new BaseVO();
	}

	@Override
	public BaseVO updateCustomizeRemark(Integer id, CustomizeRemarkDTO customizeRemarkDTO, String userId) {
		BaseVO baseVO = new BaseVO();
		CustomizeDO customizeDO = new CustomizeDO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		customizeDO.setFlag(customizeRemarkDTO.getFlag());
		customizeDO.setRemark(customizeRemarkDTO.getRemark());
		customizeDO.setRemarkUser(userBeanVO.getUsername());
		customizeDO.setId(id);
		customizeMapper.update(customizeDO);
		return baseVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommonListVO<CustomizeAllVO> selectCustomizeAll(CustomizeAllDTO customizeAllDTO) {
		CommonListVO<CustomizeAllVO> customizeList = new CommonListVO<CustomizeAllVO>();
		Integer pageNo = Integer.valueOf(customizeAllDTO.getPageNo());
		Integer pageSize = Integer.valueOf(customizeAllDTO.getPageSize());
		PageInfo<CustomizeDO> pageInfo = PageHelper.startPage(pageNo, pageSize).setOrderBy(ORDERBY)
				.doSelectPageInfo(() -> this.customizeMapper.select(customizeAllDTO.getId(),customizeAllDTO.getProduct(),
						customizeAllDTO.getUserName(), customizeAllDTO.getTelephone(),customizeAllDTO.getEmail(),customizeAllDTO.getFlag()));
		BeanUtils.copyProperties(pageInfo, customizeList);

		customizeList
				.setVoList(ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), CustomizeAllVO.class));
		List<DictionaryVO> dictionaryVOList = systemBaseUtil.queryDictionaryNameAndValue("PRODUCT_TYPE");
		for (CustomizeAllVO customizeAllVO : customizeList.getVoList()) {
			for (DictionaryVO dictionaryVO : dictionaryVOList) {
				if (customizeAllVO.getProduct().equals(dictionaryVO.getValue())) {
					customizeAllVO.setProduct(dictionaryVO.getValueCn());
				}
			}
		}
		return customizeList;
	}
}
