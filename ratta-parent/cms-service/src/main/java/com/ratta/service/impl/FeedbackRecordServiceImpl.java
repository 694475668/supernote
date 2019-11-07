package com.ratta.service.impl;

import java.util.Date;

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
import com.ratta.domain.FeedbackRecordDO;
import com.ratta.dto.FeedbackRecordDTO;
import com.ratta.dto.FeedbackRecordPartDTO;
import com.ratta.dto.FeedbackRecordQueryDTO;
import com.ratta.dto.SendEmailDTO;
import com.ratta.mapper.FeedbackRecordMapper;
import com.ratta.service.FeedbackRecordService;
import com.ratta.stream.CmsChannel;
import com.ratta.util.EmailContentUtil;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.FeedbackRecordVO;
import com.ratta.vo.UserBeanVO;

/**
 * 业务实现层
 * 
 * @author page
 */
@Service
@RefreshScope
@EnableBinding(CmsChannel.class)
public class FeedbackRecordServiceImpl implements FeedbackRecordService {

	@Autowired
	private FeedbackRecordMapper feedbackRecordMapper;
	@Autowired
	private SystemBaseUtil systemBaseUtil;
	@Resource(name = CmsChannel.BASE_OUTPUT)
	private MessageChannel sendMessage;
	@Value("${serviceEmail}")
	private String serviceEmail;
	@Value("${email.logo.url}")
	private String email_logo_url;
	@Value("${email.logo.id}")
	private String srcId;
	
	private static final String SEND = "send";
	private static final String EMAIL = "email";
	private static final String subject = "Supernote反馈提醒";
	
	@Override
	@SuppressWarnings("unchecked")
	public CommonListVO<FeedbackRecordVO> query(FeedbackRecordQueryDTO feedbackRecordQueryDTO) {
		FeedbackRecordDO feedbackRecordDO = new FeedbackRecordDO();
		BeanUtils.copyProperties(feedbackRecordQueryDTO, feedbackRecordDO);
		PageInfo<FeedbackRecordDO> pageInfo = PageHelper.startPage(feedbackRecordQueryDTO.getPageNo(), feedbackRecordQueryDTO.getPageSize())
				.setOrderBy("update_time desc")
				.doSelectPageInfo(() -> feedbackRecordMapper.query(feedbackRecordDO));

		CommonListVO<FeedbackRecordVO> commonListVO = new CommonListVO<FeedbackRecordVO>();
		BeanUtils.copyProperties(pageInfo, commonListVO);

		commonListVO.setVoList(ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(),FeedbackRecordVO.class));
		return commonListVO;
	}

	@SuppressWarnings("static-access")
	@Override
	public BaseVO save(FeedbackRecordDTO feedbackRecordDTO) {
		FeedbackRecordDO feedbackRecordDO = new FeedbackRecordDO();
		BeanUtils.copyProperties(feedbackRecordDTO, feedbackRecordDO);
		feedbackRecordDO.setCreateTime(new Date());
		feedbackRecordMapper.save(feedbackRecordDO);
		
		EmailContentUtil content = new EmailContentUtil();
		String text = content.emailFeedback(srcId,feedbackRecordDTO);
		SendEmailDTO sendEmailDTO = new SendEmailDTO(serviceEmail, subject, text, email_logo_url, srcId);
		sendMessage.send(MessageBuilder.withPayload(sendEmailDTO).setHeader(EMAIL, SEND).build());
		return new BaseVO();
	}

	@Override
	public BaseVO updatePart(FeedbackRecordPartDTO feedbackRecordPartDTO, int id,String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FeedbackRecordDO feedbackRecordDO = new FeedbackRecordDO();
		feedbackRecordDO.setId(id);
		feedbackRecordDO.setRemark(feedbackRecordPartDTO.getRemark());
		feedbackRecordDO.setStatus(feedbackRecordPartDTO.getStatus());
		feedbackRecordDO.setUpdateTime(new Date());
		feedbackRecordDO.setUpdateUser(userBeanVO.getUsername());
		feedbackRecordMapper.updatePart(feedbackRecordDO);
		return new BaseVO();
	}
}
