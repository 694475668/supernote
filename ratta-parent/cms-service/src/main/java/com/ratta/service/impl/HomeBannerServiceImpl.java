package com.ratta.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.HomeBannerDO;
import com.ratta.dto.*;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.HomeBannerMapper;
import com.ratta.service.HomeBannerService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class HomeBannerServiceImpl implements HomeBannerService {

    @Autowired
    private HomeBannerMapper homeBannerMapper;

    @Autowired
    private SystemBaseUtil systemBaseUtil;

    @Override
    public CommonListVO<HomeBannerOutVO> listOutHomeBanner(TypeLanDTO typeLanDTO, String line) {
        CommonListVO<HomeBannerOutVO> commonListVO = new CommonListVO<HomeBannerOutVO>();
        List<HomeBannerDO> homeBannerDOList = homeBannerMapper.listHomeBanner(typeLanDTO.getLanguage().toUpperCase(),
                "0", typeLanDTO.getType(), line);
        List<HomeBannerOutVO> homeBannerOutVOList = new ArrayList<HomeBannerOutVO>();
        for (HomeBannerDO homeBannerDO : homeBannerDOList) {
            HomeBannerOutVO homeBannerOutVO = new HomeBannerOutVO();
            BeanUtils.copyProperties(homeBannerDO, homeBannerOutVO);
            if (homeBannerDO.getButtonInnerOut().equals("0")) {
                String[] productArray = homeBannerDO.getProductId().split(",");
                for (String product : productArray) {
                    homeBannerOutVO.setButtonType(product.substring(0, product.indexOf("-")));
                    homeBannerOutVO.setProductId(product.substring(product.indexOf("-") + 1));
                }
            }
            homeBannerOutVOList.add(homeBannerOutVO);
        }

        commonListVO.setVoList(homeBannerOutVOList);
        return commonListVO;
    }

    @Override
    @SuppressWarnings("unchecked")
    public CommonListVO<HomeBannerVO> listHomeBanner(HomeBannerQueryDTO homeBannerLanguageDO) {
        CommonListVO commonListVO = new CommonListVO();

        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit
        if (!"".equals(homeBannerLanguageDO.getSortField())) {
            PageHelper.startPage(homeBannerLanguageDO.getPageNo(), homeBannerLanguageDO.getPageSize(),
                    homeBannerLanguageDO.getSortField() + " " + homeBannerLanguageDO.getSortRules());
        } else {
            PageHelper.startPage(homeBannerLanguageDO.getPageNo(), homeBannerLanguageDO.getPageSize());
        }

        List<HomeBannerDO> homeBannerDOList = homeBannerMapper.listHomeBanner(
                homeBannerLanguageDO.getLanguage().toUpperCase(), homeBannerLanguageDO.getLine(),
                homeBannerLanguageDO.getType(), "");
        PageInfo<HomeBannerDO> uInfo = new PageInfo<>(homeBannerDOList);
        // 总页数
        commonListVO.setPages(uInfo.getPages());
        // 总数据
        commonListVO.setTotal(uInfo.getTotal());

        List<HomeBannerVO> homeBannerVOList = new ArrayList<>();
        HomeBannerVO homeBannerVO = null;

        if (Constant.CN.equals(homeBannerLanguageDO.getLanguage().toUpperCase())) {
            for (HomeBannerDO homeBannerDO : homeBannerDOList) {
                homeBannerVO = new HomeBannerVO();
                BeanUtils.copyProperties(homeBannerDO, homeBannerVO);
                homeBannerVO.setName(homeBannerDO.getNameCn());
                homeBannerVO.setDescription(homeBannerDO.getDescriptionCn());
                homeBannerVO.setButtonName(homeBannerDO.getButtonNameCn());
                homeBannerVOList.add(homeBannerVO);
            }
        } else if (Constant.EN.equals(homeBannerLanguageDO.getLanguage().toUpperCase())) {
            for (HomeBannerDO homeBannerDO : homeBannerDOList) {
                homeBannerVO = new HomeBannerVO();
                BeanUtils.copyProperties(homeBannerDO, homeBannerVO);
                homeBannerVO.setName(homeBannerDO.getNameEn());
                homeBannerVO.setDescription(homeBannerDO.getDescriptionEn());
                homeBannerVO.setButtonName(homeBannerDO.getButtonNameEn());
                homeBannerVOList.add(homeBannerVO);
            }
        } else {
            homeBannerVOList = ObjectConvertUtil.convertInstance().objectConvert(homeBannerDOList, HomeBannerDO.class);
        }
        commonListVO.setVoList(homeBannerVOList);
        return commonListVO;
    }

    @Override
    public HomeBannerBeanVO getHomeBannerById(Long id) {
        HomeBannerDO homeBannerDO = homeBannerMapper.getHomeBannerById(id);
        HomeBannerBeanVO homeBannerBeanVO = new HomeBannerBeanVO();
        BeanUtils.copyProperties(homeBannerDO, homeBannerBeanVO);
        return homeBannerBeanVO;
    }

    @Override
    public BaseVO insertHomeBanner(HomeBannerDTO homeBannerDTO, String userId) {
        HomeBannerDO homeBannerDO = new HomeBannerDO();
        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        BeanUtils.copyProperties(homeBannerDTO, homeBannerDO);
        if (homeBannerDTO.getSeq() == null) {
            int maxSeq = homeBannerMapper.queryMaxSeq(homeBannerDTO.getType());
            homeBannerDO.setSeq(maxSeq + 1);
        }

        homeBannerDO.setCreateUser(userBeanVO.getUsername());
        homeBannerDO.setUpdateUser(userBeanVO.getUsername());
        homeBannerDO.setCreateTime(new Date());
        homeBannerDO.setUpdateTime(new Date());
        int row = homeBannerMapper.insertHomeBanner(homeBannerDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    public BaseVO updateHomeBanner(HomeBannerDTO homeBannerDTO, String userId) {
        HomeBannerDO homeBannerDO = new HomeBannerDO();
        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        BeanUtils.copyProperties(homeBannerDTO, homeBannerDO);
        homeBannerDO.setUpdateTime(new Date());
        homeBannerDO.setUpdateUser(userBeanVO.getUsername());
        int row = homeBannerMapper.updateHomeBanner(homeBannerDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    public BaseVO delHomeBanner(Long id) {
        int row = homeBannerMapper.delHomeBanner(id);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    public BaseVO HomeBannerLine(LineDTO lineDTO, Long id, String userId) {
        BaseVO baseVO = new BaseVO();
        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        HomeBannerDO homeBannerDO = homeBannerMapper.getHomeBannerById(id);
        // 如果是将状态改为上线
            if (lineDTO.getLine().equals("0")) {
            // 如果原来状态为预览或者下线
            if (homeBannerDO.getLine().equals("1") || homeBannerDO.getLine().equals("2")) {
                HomeBannerDO homeBannerDOQ = homeBannerMapper.queryBySeqAndLine(homeBannerDO.getType(),
                        homeBannerDO.getSeq(), "0");
                // 如果原来上线状态没有这个序号
                if (homeBannerDOQ == null) {
                    update(id, "0", userBeanVO);
                }
                // 如果原来上线有这个序号
                if (homeBannerDOQ != null) {
                    update(homeBannerDOQ.getId(), "2", userBeanVO);
                    update(id, "0", userBeanVO);
                }
            }
            // 如果原来状态为上线状态
            if (homeBannerDO.getLine().equals("0")) {
                return baseVO;
            }
        }
        // 如果是将状态改为预览
        if (lineDTO.getLine().equals("1")) {
            // 如果原来状态为上线或者下线
            if (homeBannerDO.getLine().equals("0") || homeBannerDO.getLine().equals("2")) {
                HomeBannerDO homeBannerDOQ = homeBannerMapper.queryBySeqAndLine(homeBannerDO.getType(),
                        homeBannerDO.getSeq(), "1");
                // 如果原来预览状态没有这个序号
                if (homeBannerDOQ == null) {
                    update(id, "1", userBeanVO);
                }
                // 如果原来预览有这个序号
                if (homeBannerDOQ != null) {
                    update(homeBannerDOQ.getId(), "2", userBeanVO);
                    update(id, "1", userBeanVO);
                }
            }
            // 如果原来状态为预览状态
            if (homeBannerDO.getLine().equals("0")) {
                return baseVO;
            }
        }
        // 如果是将状态改为下线
        if (lineDTO.getLine().equals("2")) {
            // 如果原来状态为上线或者预览
            if (homeBannerDO.getLine().equals("0") || homeBannerDO.getLine().equals("1")) {
                update(id, "2", userBeanVO);
            }
            // 如果原来状态为下线状态
            if (homeBannerDO.getLine().equals("2")) {
                return baseVO;
            }
        }
        return baseVO;
    }

    private void update(Long id, String line, UserBeanVO userBeanVO) {
        HomeBannerDO homeBannerDO = new HomeBannerDO();
        homeBannerDO.setId(Long.valueOf(id));
        homeBannerDO.setLine(line);
        homeBannerDO.setUpdateTime(new Date());
        homeBannerDO.setUpdateUser(userBeanVO.getUsername());
        homeBannerMapper.updateHomeBanner(homeBannerDO);
    }

    @Override
    public BaseVO updateSeq(SeqDTO seqDTO, Long id, String userId) {
        HomeBannerDO homeBanner = homeBannerMapper.getHomeBannerById(id);
        if (homeBanner == null) {
            return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
        }
        if (seqDTO.getSeq() == homeBanner.getSeq()) {
            return new BaseVO();
        }

        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        HomeBannerDO homeBannerDO = new HomeBannerDO();
        BeanUtils.copyProperties(seqDTO, homeBannerDO);
        homeBannerDO.setId(id);
        homeBannerDO.setUpdateTime(new Date());
        homeBannerDO.setUpdateUser(userBeanVO.getUsername());
        if (!"2".equals(homeBanner.getLine())) {
            // 同步修改整体排序
            homeBannerMapper.updateAllSeq(homeBanner.getSeq(), seqDTO.getSeq(), homeBanner.getType(), homeBanner.getLine());
        }
        homeBannerMapper.updateHomeBanner(homeBannerDO);
        return new BaseVO();
    }

}
