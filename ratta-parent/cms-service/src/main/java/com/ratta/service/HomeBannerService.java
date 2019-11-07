package com.ratta.service;

import com.ratta.dto.HomeBannerDTO;
import com.ratta.dto.HomeBannerQueryDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.dto.TypeLanDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.HomeBannerBeanVO;
import com.ratta.vo.HomeBannerOutVO;
import com.ratta.vo.HomeBannerVO;

/**
 * ******Title:程序的奥秘 ****** ******Description: ****** ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-14 9:25
 */
public interface HomeBannerService {

    /**
     * 查询所有轮播图(官网)
     *
     * @param typeLanDTO
     * @return
     */
    CommonListVO<HomeBannerOutVO> listOutHomeBanner(TypeLanDTO typeLanDTO, String line);

    /**
     * 查询所有轮播图
     *
     * @param homeBannerLanguageDO
     * @return
     */
    CommonListVO<HomeBannerVO> listHomeBanner(HomeBannerQueryDTO homeBannerLanguageDO);

    /**
     * 根据id查询轮播图信息
     *
     * @param id
     * @return
     */
    HomeBannerBeanVO getHomeBannerById(Long id);

    /**
     * 添加轮播图
     *
     * @param homeBannerDTO
     * @param userId
     * @return
     */
    BaseVO insertHomeBanner(HomeBannerDTO homeBannerDTO, String userId);

    /**
     * 修改轮播图
     *
     * @param homeBannerDTO
     * @param userId
     * @return
     */
    BaseVO updateHomeBanner(HomeBannerDTO homeBannerDTO, String userId);

    /**
     * 删除轮播图
     *
     * @param id
     * @return
     */
    BaseVO delHomeBanner(Long id);

    /**
     * 上下线轮播图
     *
     * @param lineDTO
     * @param id
     * @param userId
     * @return
     */
    BaseVO HomeBannerLine(LineDTO lineDTO, Long id, String userId);

    /**
     * 修改序号
     *
     * @param seqDTO
     * @param id
     * @param userId
     * @return
     */
    BaseVO updateSeq(SeqDTO seqDTO, Long id, String userId);
}
