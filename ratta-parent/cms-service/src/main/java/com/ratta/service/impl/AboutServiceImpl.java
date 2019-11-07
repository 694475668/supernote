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
import com.ratta.domain.AboutDO;
import com.ratta.dto.AboutAllDTO;
import com.ratta.dto.AboutDTO;
import com.ratta.dto.AboutUpdateDTO;
import com.ratta.dto.LanDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.AboutMapper;
import com.ratta.service.AboutService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.AboutAllVO;
import com.ratta.vo.AboutInfoVO;
import com.ratta.vo.AboutListVO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.UserBeanVO;

@Service
public class AboutServiceImpl implements AboutService {

	@Autowired
	private SystemBaseUtil systemBaseUtil;
	@Autowired
	private AboutMapper aboutMapper;
	private static final String ORDERBY = "line,seq asc";

	@Override
	public BaseVO addAbout(AboutDTO aboutDTO, String userId) {
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		AboutDO aboutDO = new AboutDO();
		BeanUtils.copyProperties(aboutDTO, aboutDO);

		if (aboutDTO.getSeq() == null) {
			int maxSeq = aboutMapper.queryMaxSeq();
			aboutDO.setSeq(maxSeq + 1);
		}

		aboutDO.setCreateUser(userBeanVO.getUsername());
		aboutDO.setUpdateUser(userBeanVO.getUsername());
		aboutMapper.insert(aboutDO);
		return new BaseVO();
	}

	@Override
	public BaseVO updateAbout(Integer id, AboutUpdateDTO aboutUpdateDTO, String userId) {
		BaseVO baseVO = new BaseVO();
		AboutDO aboutDO = new AboutDO();
		BeanUtils.copyProperties(aboutUpdateDTO, aboutDO);
		// 修改首页banner图信息
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		aboutDO.setCreateUser(userBeanVO.getUsername());
		aboutDO.setUpdateUser(userBeanVO.getUsername());
		aboutDO.setId(id);
		aboutMapper.update(aboutDO);
		return baseVO;
	}

	@Override
	@Transactional(rollbackFor = Exception.class)
	public BaseVO updateAboutLine(Integer id, LineDTO lineDTO, String userId) {
		BaseVO baseVO = new BaseVO();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.valueOf(userId));
		AboutDO aboutDO = aboutMapper.queryById(id);
		// 如果是将状态改为上线
		if (lineDTO.getLine().equals("0")) {
			// 如果原来状态为预览或者下线
			if (aboutDO.getLine().equals("1") || aboutDO.getLine().equals("2")) {
				AboutDO aboutDOQ = aboutMapper.queryBySeqAndLine(aboutDO.getSeq(), "0");
				// 如果原来上线状态没有这个序号
				if (aboutDOQ == null) {
					aboutMapper.updateLine(id, "0", userBeanVO.getUsername());
				}
				// 如果原来上线有这个序号
				if (aboutDOQ != null) {
					aboutMapper.updateLine(aboutDOQ.getId(), "2", userBeanVO.getUsername());
					aboutMapper.updateLine(id, "0", userBeanVO.getUsername());
				}
			}
			// 如果原来状态为上线状态
			if (aboutDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为预览
		if (lineDTO.getLine().equals("1")) {
			// 如果原来状态为上线或者下线
			if (aboutDO.getLine().equals("0") || aboutDO.getLine().equals("2")) {
				AboutDO aboutDOQ = aboutMapper.queryBySeqAndLine(aboutDO.getSeq(), "1");
				// 如果原来预览状态没有这个序号
				if (aboutDOQ == null) {
					aboutMapper.updateLine(id, "1", userBeanVO.getUsername());
				}
				// 如果原来预览有这个序号
				if (aboutDOQ != null) {
					aboutMapper.updateLine(aboutDOQ.getId(), "2", userBeanVO.getUsername());
					aboutMapper.updateLine(id, "1", userBeanVO.getUsername());
				}
			}
			// 如果原来状态为预览状态
			if (aboutDO.getLine().equals("0")) {
				return baseVO;
			}
		}
		// 如果是将状态改为下线
		if (lineDTO.getLine().equals("2")) {
			// 如果原来状态为上线或者预览
			if (aboutDO.getLine().equals("0") || aboutDO.getLine().equals("1")) {
				aboutMapper.updateLine(id, "2", userBeanVO.getUsername());
			}
			// 如果原来状态为下线状态
			if (aboutDO.getLine().equals("2")) {
				return baseVO;
			}
		}
		return baseVO;
	}

	@SuppressWarnings("unchecked")
	@Override
	public CommonListVO<AboutAllVO> selectAboutAll(AboutAllDTO aboutAllDTO) {
		CommonListVO<AboutAllVO> aboutList = new CommonListVO<AboutAllVO>();
		Integer pageNo = Integer.valueOf(aboutAllDTO.getPageNo());
		Integer pageSize = Integer.valueOf(aboutAllDTO.getPageSize());
		PageInfo<AboutDO> pageInfo = PageHelper.startPage(pageNo, pageSize).setOrderBy(ORDERBY)
				.doSelectPageInfo(() -> this.aboutMapper.select(aboutAllDTO.getId(), aboutAllDTO.getLine(), null));
		BeanUtils.copyProperties(pageInfo, aboutList);
		aboutList.setVoList(ObjectConvertUtil.convertInstance().objectConvert(pageInfo.getList(), AboutAllVO.class));
		return aboutList;
	}

	@Override
	public CommonListVO<AboutListVO> selectAboutList(LanDTO lanDTO, String line) {
		CommonListVO<AboutListVO> aboutListVOList = new CommonListVO<AboutListVO>();
		List<AboutListVO> aboutList = new ArrayList<AboutListVO>();
		List<AboutDO> aboutDOList = aboutMapper.queryList(line);
		for (AboutDO aboutDO : aboutDOList) {
			AboutListVO aboutListVO = new AboutListVO();
			AboutInfoVO aboutInfoVO = new AboutInfoVO();
			BeanUtils.copyProperties(aboutDO, aboutInfoVO);
			if (Constant.CN.equals(lanDTO.getLanguage().toUpperCase())) {
				aboutInfoVO.setTitle(aboutDO.getTitleCn());
				aboutInfoVO.setDescriptionPc(aboutDO.getDescriptionPcCn());
				aboutInfoVO.setDescriptionMobile(aboutDO.getDescriptionMobileCn());
				aboutInfoVO.setTitle(aboutDO.getTitleCn());
			}
			if (Constant.EN.equals(lanDTO.getLanguage().toUpperCase())) {
				aboutInfoVO.setTitle(aboutDO.getTitleEn());
				aboutInfoVO.setDescriptionPc(aboutDO.getDescriptionPcEn());
				aboutInfoVO.setDescriptionMobile(aboutDO.getDescriptionMobileEn());
				aboutInfoVO.setTitle(aboutDO.getTitleEn());
			}
			aboutListVO.setTitle(aboutInfoVO.getTitle());
			aboutListVO.setAboutInfo(aboutInfoVO);
			aboutList.add(aboutListVO);
		}
		aboutListVOList.setVoList(aboutList);
		return aboutListVOList;
	}

	@Override
	public BaseVO updateSeq(SeqDTO seqDTO, int id, String userId) {
		List<AboutDO> about = aboutMapper.select(String.valueOf(id), null, null);
		if (about.size() < 0) {
			return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
		}
		if (seqDTO.getSeq() == about.get(0).getSeq()) {
			return new BaseVO();
		}
		
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		AboutDO aboutDO = new AboutDO();
		BeanUtils.copyProperties(seqDTO, aboutDO);
		aboutDO.setId(id);
		aboutDO.setUpdateTime(new Date());
		aboutDO.setUpdateUser(userBeanVO.getUsername());
		if(!"2".equals(about.get(0).getLine())) {
			// 同步修改整体排序
			aboutMapper.updateAllSeq(about.get(0).getSeq(), seqDTO.getSeq(),about.get(0).getLine());
		}
		aboutMapper.updateSeq(aboutDO);
		return new BaseVO();
	}

	@Override
	public BaseVO deleteAbout(Integer id) {
		// 根据id删除产品页banner图信息
		aboutMapper.deleteById(id);
		return new BaseVO();
	}

}
