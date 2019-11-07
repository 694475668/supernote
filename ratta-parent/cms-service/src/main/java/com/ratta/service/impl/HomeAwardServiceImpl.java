package com.ratta.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.HomeAwardDO;
import com.ratta.dto.HomeAwardDTO;
import com.ratta.dto.HomeAwardQueryDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.HomeAwardMapper;
import com.ratta.service.HomeAwardService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ******Title:程序的奥秘 ****** ******Description: ****** ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-14 9:25
 */
@Service
@SuppressWarnings("rawtypes")
public class HomeAwardServiceImpl implements HomeAwardService {

    @Autowired
    private HomeAwardMapper homeAwardMapper;

    @Autowired
    private SystemBaseUtil systemBaseUtil;

    @Override
    @SuppressWarnings("unchecked")
    public CommonListVO listHomeAward(HomeAwardQueryDTO homeAwardQueryDTO, String line) {
        CommonListVO commonListVO = new CommonListVO();

        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit
        if (!"".equals(homeAwardQueryDTO.getSortField())) {
            PageHelper.startPage(homeAwardQueryDTO.getPageNo(), homeAwardQueryDTO.getPageSize(),
                    homeAwardQueryDTO.getSortField() + " " + homeAwardQueryDTO.getSortRules());
        } else {
            PageHelper.startPage(homeAwardQueryDTO.getPageNo(), homeAwardQueryDTO.getPageSize());
        }

        List<HomeAwardDO> homeAwardDOList = homeAwardMapper.listHomeAward(homeAwardQueryDTO.getLanguage().toUpperCase(),
                homeAwardQueryDTO.getLine(), line);
        PageInfo<HomeAwardDO> uInfo = new PageInfo<>(homeAwardDOList);
        // 总页数
        commonListVO.setPages(uInfo.getPages());
        // 总数据
        commonListVO.setTotal(uInfo.getTotal());

        List<HomeAwardVO> homeAwardVOList = new ArrayList<>();
        HomeAwardVO homeAwardVO = null;

        if (Constant.CN.equals(homeAwardQueryDTO.getLanguage().toUpperCase())) {
            for (HomeAwardDO homeAwardDO : homeAwardDOList) {
                homeAwardVO = new HomeAwardVO();
                BeanUtils.copyProperties(homeAwardDO, homeAwardVO);
                homeAwardVO.setName(homeAwardDO.getNameCn());
                homeAwardVO.setDescription(homeAwardDO.getDescriptionCn());
                homeAwardVO.setMechanism(homeAwardDO.getMechanismCn());
                homeAwardVO.setUrlMobile(homeAwardDO.getUrlMobileCn());
                homeAwardVO.setUrlPc(homeAwardDO.getUrlPcCn());
                homeAwardVOList.add(homeAwardVO);
            }
        } else if (Constant.EN.equals(homeAwardQueryDTO.getLanguage().toUpperCase())) {
            for (HomeAwardDO homeAwardDO : homeAwardDOList) {
                homeAwardVO = new HomeAwardVO();
                BeanUtils.copyProperties(homeAwardDO, homeAwardVO);
                homeAwardVO.setName(homeAwardDO.getNameEn());
                homeAwardVO.setDescription(homeAwardDO.getDescriptionEn());
                homeAwardVO.setMechanism(homeAwardDO.getMechanismEn());
                homeAwardVO.setUrlMobile(homeAwardDO.getUrlMobileEn());
                homeAwardVO.setUrlPc(homeAwardDO.getUrlPcEn());
                homeAwardVOList.add(homeAwardVO);
            }
        } else {
            homeAwardVOList = ObjectConvertUtil.convertInstance().objectConvert(homeAwardDOList, HomeAwardDO.class);
        }
        commonListVO.setVoList(homeAwardVOList);
        return commonListVO;
    }

    @Override
    public HomeAwardBeanVO getHomeAwardById(Long id) {
        HomeAwardDO homeAwardDO = homeAwardMapper.getHomeAwardById(id);
        HomeAwardBeanVO homeAwardBeanVO = new HomeAwardBeanVO();
        BeanUtils.copyProperties(homeAwardDO, homeAwardBeanVO);
        return homeAwardBeanVO;
    }

    @Override
    public BaseVO insertHomeAward(HomeAwardDTO homeAwardDTO, String userId) {
        HomeAwardDO homeAwardDO = new HomeAwardDO();
        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        BeanUtils.copyProperties(homeAwardDTO, homeAwardDO);
        if (homeAwardDTO.getSeq() == null) {
            int maxSeq = homeAwardMapper.queryMaxSeq();
            homeAwardDO.setSeq(maxSeq + 1);
        }
        homeAwardDO.setCreateUser(userBeanVO.getUsername());
        homeAwardDO.setUpdateUser(userBeanVO.getUsername());
        homeAwardDO.setCreateTime(new Date());
        homeAwardDO.setUpdateTime(new Date());
        int row = homeAwardMapper.insertHomeAward(homeAwardDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    public BaseVO updateHomeAward(HomeAwardDTO homeAwardDTO, String userId) {
        HomeAwardDO homeAwardDO = new HomeAwardDO();
        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        BeanUtils.copyProperties(homeAwardDTO, homeAwardDO);
        homeAwardDO.setUpdateTime(new Date());
        homeAwardDO.setUpdateUser(userBeanVO.getUsername());
        int row = homeAwardMapper.updateHomeAward(homeAwardDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    public BaseVO delHomeAward(Long id) {
        int row = homeAwardMapper.delHomeAward(id);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO HomeAwardLine(LineDTO lineDTO, Long id, String userId) {
        BaseVO baseVO = new BaseVO();
        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        HomeAwardDO homeAwardDO = homeAwardMapper.getHomeAwardById(id);
        // 如果是将状态改为上线
        if (lineDTO.getLine().equals("0")) {
            // 如果原来状态为预览或者下线
            if (homeAwardDO.getLine().equals("1") || homeAwardDO.getLine().equals("2")) {
                HomeAwardDO homeAwardDOQ = homeAwardMapper.queryBySeqAndLine(homeAwardDO.getSeq(), "0");
                // 如果原来上线状态没有这个序号
                if (homeAwardDOQ == null) {
                    update(id, "0", userBeanVO);
                }
                // 如果原来上线有这个序号
                if (homeAwardDOQ != null) {
                    update(homeAwardDOQ.getId(), "2", userBeanVO);
                    update(id, "0", userBeanVO);
                }
            }
            // 如果原来状态为上线状态
            if (homeAwardDO.getLine().equals("0")) {
                return baseVO;
            }
        }
        // 如果是将状态改为预览
        if (lineDTO.getLine().equals("1")) {
            // 如果原来状态为上线或者下线
            if (homeAwardDO.getLine().equals("0") || homeAwardDO.getLine().equals("2")) {
                HomeAwardDO homeAwardDOQ = homeAwardMapper.queryBySeqAndLine(homeAwardDO.getSeq(), "1");
                // 如果原来预览状态没有这个序号
                if (homeAwardDOQ == null) {
                    update(id, "1", userBeanVO);
                }
                // 如果原来预览有这个序号
                if (homeAwardDOQ != null) {
                    update(homeAwardDOQ.getId(), "2", userBeanVO);
                    update(id, "1", userBeanVO);
                }
            }
            // 如果原来状态为预览状态
            if (homeAwardDO.getLine().equals("0")) {
                return baseVO;
            }
        }
        // 如果是将状态改为下线
        if (lineDTO.getLine().equals("2")) {
            // 如果原来状态为上线或者预览
            if (homeAwardDO.getLine().equals("0") || homeAwardDO.getLine().equals("1")) {
                update(id, "2", userBeanVO);
            }
            // 如果原来状态为下线状态
            if (homeAwardDO.getLine().equals("2")) {
                return baseVO;
            }
        }
        return baseVO;
    }

    private void update(Long id, String line, UserBeanVO userBeanVO) {
        HomeAwardDO homeAwardDO = new HomeAwardDO();
        homeAwardDO.setId(Long.valueOf(id));
        homeAwardDO.setLine(line);
        homeAwardDO.setUpdateTime(new Date());
        homeAwardDO.setUpdateUser(userBeanVO.getUsername());
        homeAwardMapper.updateHomeAward(homeAwardDO);
    }

    @Override
    public BaseVO updateSeq(SeqDTO seqDTO, Long id, String userId) {
        HomeAwardDO homeAward = homeAwardMapper.getHomeAwardById(id);
        if (homeAward == null) {
            return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
        }
        if (seqDTO.getSeq() == homeAward.getSeq()) {
            return new BaseVO();
        }

        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        HomeAwardDO homeAwardDO = new HomeAwardDO();
        BeanUtils.copyProperties(seqDTO, homeAwardDO);
        homeAwardDO.setId(id);
        homeAwardDO.setUpdateTime(new Date());
        homeAwardDO.setUpdateUser(userBeanVO.getUsername());
        if (!"2".equals(homeAward.getLine())) {
            // 同步修改整体排序
            homeAwardMapper.updateAllSeq(homeAward.getSeq(), seqDTO.getSeq(), homeAward.getLine());
        }
        homeAwardMapper.updateHomeAward(homeAwardDO);
        return new BaseVO();
    }
}
