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
import com.ratta.domain.FirmwareManualDO;
import com.ratta.dto.FirmwareManualDTO;
import com.ratta.dto.FirmwareManualQueryDTO;
import com.ratta.dto.FirmwareManualTypeLanDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.FirmwareManualMapper;
import com.ratta.service.FirmwareManualService;
import com.ratta.util.ConvertByteUtil;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FirmwareManualPartVO;
import com.ratta.vo.FirmwareManualVO;
import com.ratta.vo.UserBeanVO;

/**
 * 业务实现层
 * 
 * @author page
 */
@Service
public class FirmwareManualServiceImpl implements FirmwareManualService {

	@Autowired
	private FirmwareManualMapper firmwareManualMapper;
	@Autowired
	private SystemBaseUtil systemBaseUtil;

	@Override
	@SuppressWarnings("unchecked")
	public CommonListVO<FirmwareManualVO> query(FirmwareManualQueryDTO firmwareManualQueryDTO) {
		FirmwareManualDO firmwareManualDO = new FirmwareManualDO();
		BeanUtils.copyProperties(firmwareManualQueryDTO, firmwareManualDO);
		PageInfo<FirmwareManualDO> pageInfo = PageHelper
				.startPage(firmwareManualQueryDTO.getPageNo(), firmwareManualQueryDTO.getPageSize())
				.setOrderBy("type,line,seq asc").doSelectPageInfo(() -> firmwareManualMapper.query(firmwareManualDO));
		CommonListVO<FirmwareManualVO> commonListVO = new CommonListVO<FirmwareManualVO>();
		BeanUtils.copyProperties(pageInfo, commonListVO);
		commonListVO.setVoList(
				ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), FirmwareManualVO.class));
		return commonListVO;
	}

	@Override
	public CommonListVO<FirmwareManualPartVO> queryByTypeAndLan(FirmwareManualTypeLanDTO firmwareManualTypeLanDTO,String line) {
		List<FirmwareManualPartVO> firmwareManualPartVOList = new ArrayList<FirmwareManualPartVO>();
		PageInfo<FirmwareManualDO> pageInfo = PageHelper
				.startPage(firmwareManualTypeLanDTO.getPageNo(), firmwareManualTypeLanDTO.getPageSize())
				.setOrderBy("seq asc")
				.doSelectPageInfo(() -> firmwareManualMapper.queryByTypeAndLan(firmwareManualTypeLanDTO.getType(),line));

		CommonListVO<FirmwareManualPartVO> commonListVO = new CommonListVO<FirmwareManualPartVO>();
		BeanUtils.copyProperties(pageInfo, commonListVO);

		List<FirmwareManualDO> firmwareManualDOList = pageInfo.getList();
		for (FirmwareManualDO firmwareManualDO : firmwareManualDOList) {
			FirmwareManualPartVO firmwareManualPartVO = new FirmwareManualPartVO();
			if (firmwareManualTypeLanDTO.getLanguage().toUpperCase().equals(Constant.CN)) {
				firmwareManualPartVO.setTitle(firmwareManualDO.getTitleCn());
				firmwareManualPartVO.setDescription(firmwareManualDO.getDescriptionCn());
				firmwareManualPartVO.setUrl(firmwareManualDO.getUrlCn());
				firmwareManualPartVO.setVersion(firmwareManualDO.getVersionCn());
				firmwareManualPartVO.setSize(firmwareManualDO.getSizeCn());
			}
			if (firmwareManualTypeLanDTO.getLanguage().toUpperCase().equals(Constant.EN)) {
				firmwareManualPartVO.setTitle(firmwareManualDO.getTitleEn());
				firmwareManualPartVO.setDescription(firmwareManualDO.getDescriptionEn());
				firmwareManualPartVO.setUrl(firmwareManualDO.getUrlEn());
				firmwareManualPartVO.setVersion(firmwareManualDO.getVersionEn());
				firmwareManualPartVO.setSize(firmwareManualDO.getSizeEn());
			}
			
			firmwareManualPartVO.setThumbnailUrl(firmwareManualDO.getThumbnailUrl());
			firmwareManualPartVO.setUpdateTime(firmwareManualDO.getUpdateTime());
			firmwareManualPartVOList.add(firmwareManualPartVO);
		}
		commonListVO.setVoList(firmwareManualPartVOList);
		return commonListVO;
	}

	@Override
	public BaseVO save(FirmwareManualDTO firmwareManualDTO, String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FirmwareManualDO firmwareManualDO = new FirmwareManualDO();
		BeanUtils.copyProperties(firmwareManualDTO, firmwareManualDO);

		if (firmwareManualDTO.getSeq() == null) {
			int maxSeq = firmwareManualMapper.queryMaxSeq(firmwareManualDTO.getType());
			firmwareManualDO.setSeq(maxSeq + 1);
		}

		firmwareManualDO.setCreateTime(new Date());
		firmwareManualDO.setSizeCn(ConvertByteUtil.getPrintSize(Long.parseLong(firmwareManualDTO.getSizeCn())));
		firmwareManualDO.setSizeEn(ConvertByteUtil.getPrintSize(Long.parseLong(firmwareManualDTO.getSizeEn())));
		firmwareManualDO.setCreateUser(userBeanVO.getUsername());
		firmwareManualDO.setUpdateTime(new Date());
		firmwareManualDO.setUpdateUser(userBeanVO.getUsername());
		firmwareManualMapper.save(firmwareManualDO);
		return new BaseVO();
	}

	@Override
	public BaseVO delete(int id) {
		firmwareManualMapper.delete(id);
		return new BaseVO();
	}

	@Override
	public CommonVO<FirmwareManualVO> queryById(int id) {
		FirmwareManualDO firmwareManualDO = firmwareManualMapper.queryById(id);
		CommonVO<FirmwareManualVO> commonVO = new CommonVO<FirmwareManualVO>();
		if (firmwareManualDO == null) {
			commonVO.setSuccess(false);
			commonVO.setErrorCode(ErrorCodeEnum.E1441.getKey());
			commonVO.setErrorMsg(ErrorCodeEnum.E1441.getValue());
			return commonVO;
		}

		FirmwareManualVO firmwareManualVO = new FirmwareManualVO();
		BeanUtils.copyProperties(firmwareManualDO, firmwareManualVO);
		commonVO.setVoT(firmwareManualVO);
		return commonVO;
	}

	@Override
	public BaseVO update(FirmwareManualDTO firmwareManualDTO, int id, String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FirmwareManualDO firmwareManualDO = new FirmwareManualDO();
		BeanUtils.copyProperties(firmwareManualDTO, firmwareManualDO);
		firmwareManualDO.setId(id);
		firmwareManualDO.setUpdateTime(new Date());
		firmwareManualDO.setUpdateUser(userBeanVO.getUsername());
		firmwareManualMapper.update(firmwareManualDO);
		return new BaseVO();
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateLine(LineDTO lineDTO, int id, String userId) {
		BaseVO baseVO = new BaseVO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FirmwareManualDO firmwareManualDO = firmwareManualMapper.queryById(id);
		// 如果是将状态改为上线
		if (lineDTO.getLine().equals("0")) {
			// 如果原来状态为预览或者下线
			if (firmwareManualDO.getLine().equals("1") || firmwareManualDO.getLine().equals("2")) {
				FirmwareManualDO firmwareManualDOQ = firmwareManualMapper.queryBySeqAndLine(firmwareManualDO.getType(),
						firmwareManualDO.getSeq(), "0");
				// 如果原来上线状态没有这个序号
				if (firmwareManualDOQ == null) {
					update(id, "0", userBeanVO);
				}
				// 如果原来上线有这个序号
				if (firmwareManualDOQ != null) {
					update(firmwareManualDOQ.getId(), "2", userBeanVO);
					update(id, "0", userBeanVO);
				}
			}
			// 如果原来状态为上线状态
			if (firmwareManualDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为预览
		if (lineDTO.getLine().equals("1")) {
			// 如果原来状态为上线或者下线
			if (firmwareManualDO.getLine().equals("0") || firmwareManualDO.getLine().equals("2")) {
				FirmwareManualDO firmwareManualDOQ = firmwareManualMapper.queryBySeqAndLine(firmwareManualDO.getType(),
						firmwareManualDO.getSeq(), "1");
				// 如果原来预览状态没有这个序号
				if (firmwareManualDOQ == null) {
					update(id, "1", userBeanVO);
				}
				// 如果原来预览有这个序号
				if (firmwareManualDOQ != null) {
					update(firmwareManualDOQ.getId(), "2", userBeanVO);
					update(id, "1", userBeanVO);
				}
			}
			// 如果原来状态为预览状态
			if (firmwareManualDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为下线
		if (lineDTO.getLine().equals("2")) {
			// 如果原来状态为上线或者预览
			if (firmwareManualDO.getLine().equals("0") || firmwareManualDO.getLine().equals("1")) {
				update(id, "2", userBeanVO);
			}
			// 如果原来状态为下线状态
			if (firmwareManualDO.getLine().equals("2")) {
				return baseVO;
			}
		}
		return baseVO;
	}

	private void update(int id, String line, UserBeanVO userBeanVO) {
		FirmwareManualDO firmwareManualDO = new FirmwareManualDO();
		firmwareManualDO.setId(id);
		firmwareManualDO.setLine(line);
		firmwareManualDO.setUpdateTime(new Date());
		firmwareManualDO.setUpdateUser(userBeanVO.getUsername());
		firmwareManualMapper.updateLine(firmwareManualDO);
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateSeq(SeqDTO seqDTO, int id, String userId) {
		FirmwareManualDO firmwareManual = firmwareManualMapper.queryById(id);
		if (firmwareManual == null) {
			return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
		}

		if (seqDTO.getSeq() == firmwareManual.getSeq()) {
			return new BaseVO();
		}

		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		FirmwareManualDO firmwareManualDO = new FirmwareManualDO();
		BeanUtils.copyProperties(seqDTO, firmwareManualDO);
		firmwareManualDO.setId(id);
		firmwareManualDO.setUpdateTime(new Date());
		firmwareManualDO.setUpdateUser(userBeanVO.getUsername());
		if(!"2".equals(firmwareManual.getLine())) {
			// 同步修改整体排序
			firmwareManualMapper.updateAllSeq(firmwareManual.getSeq(), seqDTO.getSeq(), firmwareManual.getType(),firmwareManual.getLine());
		}
		firmwareManualMapper.updateSeq(firmwareManualDO);
		return new BaseVO();
	}
}
