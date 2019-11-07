package com.ratta.service.impl;

import java.util.Date;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.domain.FaqItemDO;
import com.ratta.dto.FaqItemDTO;
import com.ratta.dto.FaqItemQueryDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.FaqItemMapper;
import com.ratta.service.FaqItemService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FaqItemVO;
import com.ratta.vo.UserBeanVO;

/**
 * 业务实现层
 * 
 * @author page
 */
@Service
public class FaqItemServiceImpl implements FaqItemService {

	@Autowired
	private FaqItemMapper faqItemMapper;
	@Autowired
	private SystemBaseUtil systemBaseUtil;

	@Override
	@SuppressWarnings("unchecked")
	public CommonListVO<FaqItemVO> query(FaqItemQueryDTO faqItemQueryDTO) {
		FaqItemDO faqItemDO = new FaqItemDO();
		BeanUtils.copyProperties(faqItemQueryDTO, faqItemDO);
		PageInfo<FaqItemDO> pageInfo = PageHelper.startPage(faqItemQueryDTO.getPageNo(), faqItemQueryDTO.getPageSize())
				.setOrderBy("s.type,s.title_cn,t.line,t.seq asc").doSelectPageInfo(() -> faqItemMapper.query(faqItemDO));

		CommonListVO<FaqItemVO> commonListVO = new CommonListVO<FaqItemVO>();
		BeanUtils.copyProperties(pageInfo, commonListVO);

		commonListVO.setVoList(ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), FaqItemVO.class));
		return commonListVO;
	}

	@Override
	public BaseVO save(FaqItemDTO faqItemDTO, String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FaqItemDO faqItemDO = new FaqItemDO();
		BeanUtils.copyProperties(faqItemDTO, faqItemDO);
		if (faqItemDTO.getSeq() == null) {
			int maxSeq = faqItemMapper.queryMaxSeq(faqItemDTO.getFaqTitleId());
			faqItemDO.setSeq(maxSeq + 1);
		}

		faqItemDO.setCreateTime(new Date());
		faqItemDO.setCreateUser(userBeanVO.getUsername());
		faqItemDO.setUpdateTime(new Date());
		faqItemDO.setUpdateUser(userBeanVO.getUsername());
		faqItemMapper.save(faqItemDO);
		return new BaseVO();
	}

	@Override
	public BaseVO delete(int id) {
		faqItemMapper.delete(id);
		return new BaseVO();
	}

	@Override
	public CommonVO<FaqItemVO> queryById(int id) {
		FaqItemDO faqItemDO = faqItemMapper.queryById(id);
		CommonVO<FaqItemVO> commonVO = new CommonVO<FaqItemVO>();
		if (faqItemDO == null) {
			commonVO.setSuccess(false);
			commonVO.setErrorCode(ErrorCodeEnum.E1441.getKey());
			commonVO.setErrorMsg(ErrorCodeEnum.E1441.getValue());
			return commonVO;
		}
		FaqItemVO faqItemVO = new FaqItemVO();
		BeanUtils.copyProperties(faqItemDO, faqItemVO);
		commonVO.setVoT(faqItemVO);
		return commonVO;
	}

	@Override
	public BaseVO update(FaqItemDTO faqItemDTO, int id, String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FaqItemDO faqItemDO = new FaqItemDO();
		BeanUtils.copyProperties(faqItemDTO, faqItemDO);
		faqItemDO.setId(id);
		faqItemDO.setUpdateTime(new Date());
		faqItemDO.setUpdateUser(userBeanVO.getUsername());
		faqItemMapper.update(faqItemDO);
		return new BaseVO();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateLine(LineDTO lineDTO, int id, String userId) {
		BaseVO baseVO = new BaseVO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FaqItemDO faqItemDO = faqItemMapper.queryById(id);
		// 如果是将状态改为上线
		if (lineDTO.getLine().equals("0")) {
			// 如果原来状态为预览或者下线
			if (faqItemDO.getLine().equals("1") || faqItemDO.getLine().equals("2")) {
				FaqItemDO faqItemDOQ = faqItemMapper.queryBySeqAndLine(faqItemDO.getFaqTitleId(), faqItemDO.getSeq(),
						"0");
				// 如果原来上线状态没有这个序号
				if (faqItemDOQ == null) {
					update(id, "0", userBeanVO);
				}
				// 如果原来上线有这个序号
				if (faqItemDOQ != null) {
					update(faqItemDOQ.getId(), "2", userBeanVO);
					update(id, "0", userBeanVO);
				}
			}
			// 如果原来状态为上线状态
			if (faqItemDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为预览
		if (lineDTO.getLine().equals("1")) {
			// 如果原来状态为上线或者下线
			if (faqItemDO.getLine().equals("0") || faqItemDO.getLine().equals("2")) {
				FaqItemDO faqItemDOQ = faqItemMapper.queryBySeqAndLine(faqItemDO.getFaqTitleId(), faqItemDO.getSeq(),
						"1");
				// 如果原来预览状态没有这个序号
				if (faqItemDOQ == null) {
					update(id, "1", userBeanVO);
				}
				// 如果原来预览有这个序号
				if (faqItemDOQ != null) {
					update(faqItemDOQ.getId(), "2", userBeanVO);
					update(id, "1", userBeanVO);
				}
			}
			// 如果原来状态为预览状态
			if (faqItemDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为下线
		if (lineDTO.getLine().equals("2")) {
			// 如果原来状态为上线或者预览
			if (faqItemDO.getLine().equals("0") || faqItemDO.getLine().equals("1")) {
				update(id, "2", userBeanVO);
			}
			// 如果原来状态为下线状态
			if (faqItemDO.getLine().equals("2")) {
				return baseVO;
			}
		}
		return baseVO;
	}

	private void update(int id, String line, UserBeanVO userBeanVO) {
		FaqItemDO faqItemDO = new FaqItemDO();
		faqItemDO.setId(id);
		faqItemDO.setLine(line);
		faqItemDO.setUpdateTime(new Date());
		faqItemDO.setUpdateUser(userBeanVO.getUsername());
		faqItemMapper.updateLine(faqItemDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateSeq(SeqDTO seqDTO, int id, String userId) {
		FaqItemDO faqItem = faqItemMapper.queryById(id);
		if (faqItem == null) {
			return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
		}

		if (seqDTO.getSeq() == faqItem.getSeq()) {
			return new BaseVO();
		}

		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FaqItemDO faqItemDO = new FaqItemDO();
		BeanUtils.copyProperties(seqDTO, faqItemDO);
		faqItemDO.setId(id);
		faqItemDO.setUpdateTime(new Date());
		faqItemDO.setUpdateUser(userBeanVO.getUsername());
		if(!"2".equals(faqItem.getLine())) {
			// 同步修改整体排序
			faqItemMapper.updateAllSeq(faqItem.getSeq(), seqDTO.getSeq(), faqItem.getFaqTitleId(),faqItem.getLine());
		}
		faqItemMapper.updateSeq(faqItemDO);
		return new BaseVO();
	}
}
