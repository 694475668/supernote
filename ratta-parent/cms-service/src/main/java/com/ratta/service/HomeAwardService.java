package com.ratta.service;

import com.ratta.dto.*;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.HomeAwardBeanVO;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-14 9:25
 */
@SuppressWarnings("rawtypes")
public interface HomeAwardService {
    /**
     * 查询所有轮播图
     *
     * @param homeAwardQueryDTO
     * @return
     */
    CommonListVO listHomeAward(HomeAwardQueryDTO homeAwardQueryDTO, String line);

    /**
     * 根据id查询轮播图信息
     *
     * @param id
     * @return
     */
    HomeAwardBeanVO getHomeAwardById(Long id);

    /**
     * 添加轮播图
     *
     * @param homeAwardDTO
     * @param userId
     * @return
     */
    BaseVO insertHomeAward(HomeAwardDTO homeAwardDTO, String userId);

    /**
     * 修改轮播图
     *
     * @param homeAwardDTO
     * @param userId
     * @return
     */
    BaseVO updateHomeAward(HomeAwardDTO homeAwardDTO, String userId);

    /**
     * 删除轮播图
     *
     * @param id
     * @return
     */
    BaseVO delHomeAward(Long id);

    /**
     * 上下线轮播图
     *
     * @param lineDTO
     * @param id
     * @param userId
     * @return
     */
    BaseVO HomeAwardLine(LineDTO lineDTO, Long id, String userId);

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
