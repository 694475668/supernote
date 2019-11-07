package com.ratta.service;

import com.ratta.dto.*;
import com.ratta.vo.BaseVO;
import com.ratta.vo.BlogVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.RecommendBlogVO;

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
public interface BlogService {
    /**
     * 查询所有博客
     *
     * @param blogQueryDTO
     * @return
     */
    CommonListVO listBlog(BlogQueryDTO blogQueryDTO, String line);

    /**
     * 根据id查询博客信息
     *
     * @param id
     * @return
     */
    BlogVO getBlogById(Long id);

    /**
     * 添加博客
     *
     * @param blogDTO
     * @param userId
     * @return
     */
    BaseVO insertBlog(BlogDTO blogDTO, String userId);

    /**
     * 修改博客
     *
     * @param blogDTO
     * @param userId
     * @return
     */
    BaseVO updateBlog(BlogDTO blogDTO, String userId);

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    BaseVO delBlog(Long id);

    /**
     * 上下线博客
     *
     * @param lineDTO
     * @param userId
     * @param id
     * @return
     */
    BaseVO blogLine(LineDTO lineDTO, Long id, String userId);


    /**
     * 官网通过微博id查询内容以及推荐博客
     *
     * @param commonLanguageDTO
     * @param id
     * @param webLine
     * @return
     */
    RecommendBlogVO recommendBlogVOList(HomeBannerQueryDTO commonLanguageDTO, Long id, String webLine);

    /**
     * 修改点赞数
     *
     * @param id
     * @return
     */
    BaseVO updateThumbsCount(Integer id);

    /**
     * 修改序号
     *
     * @param seqDTO
     * @param id
     * @param userId
     * @return
     */
    BaseVO updateSeq(SeqDTO seqDTO, Long id, String userId);

    /**
     * 修改推荐标识
     *
     * @param recommendDTO
     * @param userId
     * @return
     */
    BaseVO updateRecommend(RecommendDTO recommendDTO, String userId);
}
