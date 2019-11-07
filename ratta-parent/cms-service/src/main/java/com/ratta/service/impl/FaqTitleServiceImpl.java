package com.ratta.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.FaqItemDO;
import com.ratta.domain.FaqTitleDO;
import com.ratta.dto.FaqTitleDTO;
import com.ratta.dto.FaqTitleQueryDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.dto.TypeLanDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.FaqItemMapper;
import com.ratta.mapper.FaqTitleMapper;
import com.ratta.service.FaqTitleService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FaqItemPartVO;
import com.ratta.vo.FaqTitleItemVO;
import com.ratta.vo.FaqTitlePartVO;
import com.ratta.vo.FaqTitleVO;
import com.ratta.vo.UserBeanVO;

import lombok.extern.slf4j.Slf4j;

/**
 * 业务实现层
 * 
 * @author page
 */
@Service
@Slf4j
public class FaqTitleServiceImpl implements FaqTitleService {

	@Autowired
	private FaqTitleMapper faqTitleMapper;
	@Autowired
	private FaqItemMapper faqItemMapper;
	@Autowired
	private SystemBaseUtil systemBaseUtil;

	@Override
	@SuppressWarnings("unchecked")
	public CommonListVO<FaqTitleVO> query(FaqTitleQueryDTO faqTitleQueryDTO) {
		FaqTitleDO faqTitleDO = new FaqTitleDO();
		BeanUtils.copyProperties(faqTitleQueryDTO, faqTitleDO);
		PageInfo<FaqTitleDO> pageInfo = PageHelper
				.startPage(faqTitleQueryDTO.getPageNo(), faqTitleQueryDTO.getPageSize()).setOrderBy("type,line,seq asc")
				.doSelectPageInfo(() -> faqTitleMapper.query(faqTitleDO));

		CommonListVO<FaqTitleVO> commonListVO = new CommonListVO<FaqTitleVO>();
		BeanUtils.copyProperties(pageInfo, commonListVO);

		commonListVO.setVoList(ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), FaqTitleVO.class));
		return commonListVO;
	}

	@Override
	@SuppressWarnings("unchecked")
	public CommonNoPageListVO<FaqTitlePartVO> queryByType(String type) {
		CommonNoPageListVO<FaqTitlePartVO> commonListVO = new CommonNoPageListVO<FaqTitlePartVO>();
		commonListVO.setVoList(ObjectConvertUtil.convertInstance().objectConvert(faqTitleMapper.queryByType(type, ""),
				FaqTitlePartVO.class));
		return commonListVO;
	}

	@Override
	public CommonNoPageListVO<FaqTitleItemVO> queryByTypeAndLan(TypeLanDTO typeLanDTO,String line) {
		CommonNoPageListVO<FaqTitleItemVO> commonListVO = new CommonNoPageListVO<FaqTitleItemVO>();
		List<FaqTitleItemVO> faqTitleItemVOList = new ArrayList<FaqTitleItemVO>();
		List<FaqTitleDO> faqTitleDOList = faqTitleMapper.queryByType(typeLanDTO.getType(), line);
		for (FaqTitleDO faqTitleDO : faqTitleDOList) {
			FaqTitleItemVO faqTitleItemVO = new FaqTitleItemVO();
			if (typeLanDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
				faqTitleItemVO.setTitle(faqTitleDO.getTitleCn());
			}
			if (typeLanDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
				faqTitleItemVO.setTitle(faqTitleDO.getTitleEn());
			}
			// 查询标题子类的条目信息
			List<FaqItemDO> faqItemDOList = faqItemMapper.queryAllNoPage(faqTitleDO.getId(), line);
			List<FaqItemPartVO> faqItemPartVOList = new ArrayList<FaqItemPartVO>();
			for (FaqItemDO faqItemDO : faqItemDOList) {
				FaqItemPartVO faqItemPartVO = new FaqItemPartVO();
				if (typeLanDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
					faqItemPartVO.setQuestion(faqItemDO.getQuestionCn());
					faqItemPartVO.setAnswer(faqItemDO.getAnswerCn());
				}
				if (typeLanDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
					faqItemPartVO.setQuestion(faqItemDO.getQuestionEn());
					faqItemPartVO.setAnswer(faqItemDO.getAnswerEn());
				}
				faqItemPartVOList.add(faqItemPartVO);
			}
			faqTitleItemVO.setItemList(faqItemPartVOList);

			faqTitleItemVOList.add(faqTitleItemVO);
		}
		commonListVO.setVoList(faqTitleItemVOList);
		return commonListVO;
	}

	@Override
	public BaseVO save(FaqTitleDTO faqTitleDTO, String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FaqTitleDO faqTitleDO = new FaqTitleDO();
		BeanUtils.copyProperties(faqTitleDTO, faqTitleDO);
		if (faqTitleDTO.getSeq() == null) {
			int maxSeq = faqTitleMapper.queryMaxSeq(faqTitleDTO.getType());
			faqTitleDO.setSeq(maxSeq + 1);
		}

		faqTitleDO.setCreateTime(new Date());
		faqTitleDO.setCreateUser(userBeanVO.getUsername());
		faqTitleDO.setUpdateTime(new Date());
		faqTitleDO.setUpdateUser(userBeanVO.getUsername());
		faqTitleMapper.save(faqTitleDO);
		return new BaseVO();
	}

	@Override
	public BaseVO delete(int id) {
		List<FaqItemDO> faqItemDOList = faqItemMapper.queryAllNoPage(id, "");
		if (faqItemDOList != null && faqItemDOList.size() > 0) {
			log.error("该标题下存在标题内容，请先删除标题内容再删除标题！");
			return new BaseVO(false, ErrorCodeEnum.E1404.getKey(), ErrorCodeEnum.E1404.getValue());
		}
		faqTitleMapper.delete(id);
		return new BaseVO();
	}

	@Override
	public CommonVO<FaqTitleVO> queryById(int id) {
		FaqTitleDO faqTitleDO = faqTitleMapper.queryById(id);
		CommonVO<FaqTitleVO> commonVO = new CommonVO<FaqTitleVO>();
		if (faqTitleDO == null) {
			commonVO.setSuccess(false);
			commonVO.setErrorCode(ErrorCodeEnum.E1441.getKey());
			commonVO.setErrorMsg(ErrorCodeEnum.E1441.getValue());
			return commonVO;
		}
		FaqTitleVO faqTitleVO = new FaqTitleVO();
		BeanUtils.copyProperties(faqTitleDO, faqTitleVO);
		commonVO.setVoT(faqTitleVO);
		return commonVO;
	}

	@Override
	public BaseVO update(FaqTitleDTO faqTitleDTO, int id, String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FaqTitleDO faqTitleDO = new FaqTitleDO();
		BeanUtils.copyProperties(faqTitleDTO, faqTitleDO);
		faqTitleDO.setId(id);
		faqTitleDO.setUpdateTime(new Date());
		faqTitleDO.setUpdateUser(userBeanVO.getUsername());
		faqTitleMapper.update(faqTitleDO);
		return new BaseVO();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateLine(LineDTO lineDTO, int id, String userId) {
		BaseVO baseVO = new BaseVO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FaqTitleDO faqTitleDO = faqTitleMapper.queryById(id);
		// 如果是将状态改为上线
		if (lineDTO.getLine().equals("0")) {
			// 如果原来状态为预览或者下线
			if (faqTitleDO.getLine().equals("1") || faqTitleDO.getLine().equals("2")) {
				FaqTitleDO faqTitleDOQ = faqTitleMapper.queryBySeqAndLine(faqTitleDO.getType(), faqTitleDO.getSeq(),
						"0");
				// 如果原来上线状态没有这个序号
				if (faqTitleDOQ == null) {
					update(id, "0", userBeanVO);
				}
				// 如果原来上线有这个序号
				if (faqTitleDOQ != null) {
					update(faqTitleDOQ.getId(), "2", userBeanVO);
					update(id, "0", userBeanVO);
				}
			}
			// 如果原来状态为上线状态
			if (faqTitleDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为预览
		if (lineDTO.getLine().equals("1")) {
			// 如果原来状态为上线或者下线
			if (faqTitleDO.getLine().equals("0") || faqTitleDO.getLine().equals("2")) {
				FaqTitleDO faqTitleDOQ = faqTitleMapper.queryBySeqAndLine(faqTitleDO.getType(), faqTitleDO.getSeq(),
						"1");
				// 如果原来预览状态没有这个序号
				if (faqTitleDOQ == null) {
					update(id, "1", userBeanVO);
				}
				// 如果原来预览有这个序号
				if (faqTitleDOQ != null) {
					update(faqTitleDOQ.getId(), "2", userBeanVO);
					update(id, "1", userBeanVO);
				}
			}
			// 如果原来状态为预览状态
			if (faqTitleDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为下线
		if (lineDTO.getLine().equals("2")) {
			// 如果原来状态为上线或者预览
			if (faqTitleDO.getLine().equals("0") || faqTitleDO.getLine().equals("1")) {
				update(id, "2", userBeanVO);
			}
			// 如果原来状态为下线状态
			if (faqTitleDO.getLine().equals("2")) {
				return baseVO;
			}
		}
		return baseVO;
	}

	private void update(int id, String line, UserBeanVO userBeanVO) {
		FaqTitleDO faqTitleDO = new FaqTitleDO();
		faqTitleDO.setId(id);
		faqTitleDO.setLine(line);
		faqTitleDO.setUpdateTime(new Date());
		faqTitleDO.setUpdateUser(userBeanVO.getUsername());
		faqTitleMapper.updateLine(faqTitleDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateSeq(SeqDTO seqDTO, int id, String userId) {
		FaqTitleDO faqTitle = faqTitleMapper.queryById(id);
		if (faqTitle == null) {
			return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
		}

		if (seqDTO.getSeq() == faqTitle.getSeq()) {
			return new BaseVO();
		}

		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FaqTitleDO faqTitleDO = new FaqTitleDO();
		BeanUtils.copyProperties(seqDTO, faqTitleDO);
		faqTitleDO.setId(id);
		faqTitleDO.setUpdateTime(new Date());
		faqTitleDO.setUpdateUser(userBeanVO.getUsername());

		if (!"2".equals(faqTitle.getLine())) {
			faqTitleMapper.updateAllSeq(faqTitle.getSeq(), seqDTO.getSeq(), faqTitle.getType(), faqTitle.getLine());
		}
		faqTitleMapper.updateSeq(faqTitleDO);
		return new BaseVO();
	}
}
