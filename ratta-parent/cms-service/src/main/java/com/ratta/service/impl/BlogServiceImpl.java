package com.ratta.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.domain.BlogDO;
import com.ratta.domain.CommentDO;
import com.ratta.dto.*;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.BlogMapper;
import com.ratta.mapper.CommentMapper;
import com.ratta.service.BlogService;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.*;
import com.vdurmont.emoji.EmojiParser;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
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
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private SystemBaseUtil systemBaseUtil;

    @Autowired
    private CommentMapper commentMapper;

    @SuppressWarnings("unchecked")
    @Override
    public CommonListVO listBlog(BlogQueryDTO blogQueryDTO, String line) {
        // 修改博客真实评论数
        if ("".equals(blogQueryDTO.getLanguage())) {
            List<CommentDO> commentDOList = commentMapper.queryCommentCount();
            for (CommentDO commentDO : commentDOList) {
                BlogDO blogDO = new BlogDO();
                blogDO.setId(Long.valueOf(commentDO.getBlogId()));
                blogDO.setCommentsCountReal(String.valueOf(commentDO.getCount()));
                blogMapper.updateBlog(blogDO);
            }
        }
        CommonListVO commonListVO = new CommonListVO();

        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit
        if (!"".equals(blogQueryDTO.getSortField())) {
            PageHelper.startPage(blogQueryDTO.getPageNo(), blogQueryDTO.getPageSize(),
                    blogQueryDTO.getSortField() + " " + blogQueryDTO.getSortRules());
        } else {
            PageHelper.startPage(blogQueryDTO.getPageNo(), blogQueryDTO.getPageSize());
        }

        List<BlogDO> blogDOList = blogMapper.listBlog(blogQueryDTO.getLanguage().toUpperCase(), blogQueryDTO.getLine(),
                blogQueryDTO.getRecommendFlag(), blogQueryDTO.getTitleId(), line);
        PageInfo<BlogDO> uInfo = new PageInfo<>(blogDOList);
        // 总页数
        commonListVO.setPages(uInfo.getPages());
        // 总数据
        commonListVO.setTotal(uInfo.getTotal());
        List<BlogVO> blogVOList = new ArrayList<>();
        for (BlogDO blogDO : blogDOList) {
            BlogVO blogVO = new BlogVO();
            BeanUtils.copyProperties(blogDO, blogVO);
            blogVO.setContent(EmojiParser.parseToUnicode(blogDO.getContent()));
            blogVOList.add(blogVO);
        }
        commonListVO.setVoList(blogVOList);
        return commonListVO;
    }

    @Override
    public BlogVO getBlogById(Long id) {
        BlogDO blogDO = blogMapper.getBlogById(id);
        BlogVO blogVO = new BlogVO();
        BeanUtils.copyProperties(blogDO, blogVO);
        blogVO.setContent(EmojiParser.parseToUnicode(blogDO.getContent()));
        return blogVO;
    }

    @Override
    public BaseVO insertBlog(BlogDTO blogDTO, String userId) {
        if ("".equals(blogDTO.getViewCount())) {
            blogDTO.setViewCount("0");
        }
        if ("".equals(blogDTO.getCommentsCount())) {
            blogDTO.setCommentsCount("0");
        }
        if ("".equals(blogDTO.getThumbsCount())) {
            blogDTO.setThumbsCount("0");
        }
        BlogDO blogDO = new BlogDO();
        UserBeanVO userBeanVO = (UserBeanVO) redisTemplate.opsForValue().get("cmsUserKey" + userId);
        BeanUtils.copyProperties(blogDTO, blogDO);

        if (blogDTO.getSeq() == null) {
            int maxSeq = blogMapper.queryMaxSeq();
            blogDO.setSeq(maxSeq + 1);
        }

        blogDO.setCreateUser(userBeanVO.getUsername());
        blogDO.setUpdateUser(userBeanVO.getUsername());
        blogDO.setCreateTime(new Date());
        blogDO.setUpdateTime(new Date());
        blogDO.setContent(EmojiParser.parseToAliases(blogDTO.getContent()));
        int row = blogMapper.insertBlog(blogDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    public BaseVO updateBlog(BlogDTO blogDTO, String userId) {
        if ("".equals(blogDTO.getViewCount())) {
            blogDTO.setViewCount("0");
        }
        if ("".equals(blogDTO.getCommentsCount())) {
            blogDTO.setCommentsCount("0");
        }
        if ("".equals(blogDTO.getThumbsCount())) {
            blogDTO.setThumbsCount("0");
        }
        BlogDO blogDO = new BlogDO();
        UserBeanVO userBeanVO = (UserBeanVO) redisTemplate.opsForValue().get("cmsUserKey" + userId);
        BeanUtils.copyProperties(blogDTO, blogDO);
        blogDO.setUpdateTime(new Date());
        blogDO.setUpdateUser(userBeanVO.getUsername());
        blogDO.setContent(EmojiParser.parseToAliases(blogDTO.getContent()));
        int row = blogMapper.updateBlog(blogDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());

    @Override
    public BaseVO delBlog(Long id) {
        int row = blogMapper.delBlog(id);
        if (row > 0) {
            commentMapper.delCommentByBlogId(id);
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public BaseVO blogLine(LineDTO lineDTO, Long id, String userId) {
        BaseVO baseVO = new BaseVO();
        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        BlogDO blogDO = blogMapper.getBlogById(id);
        // 如果是将状态改为上线
        if (lineDTO.getLine().equals("0")) {
            // 如果原来状态为预览或者下线
            if (blogDO.getLine().equals("1") || blogDO.getLine().equals("2")) {
                BlogDO blogDOQ = blogMapper.queryBySeqAndLine(blogDO.getSeq(), "0");
                // 如果原来上线状态没有这个序号
                if (blogDOQ == null) {
                    update(id, "0", userBeanVO);
                }
                // 如果原来上线有这个序号
                if (blogDOQ != null) {
                    update(blogDOQ.getId(), "2", userBeanVO);
                    update(id, "0", userBeanVO);
                }
            }
            // 如果原来状态为上线状态
            if (blogDO.getLine().equals("0")) {
                return baseVO;
            }
        }
        // 如果是将状态改为预览
        if (lineDTO.getLine().equals("1")) {
            // 如果原来状态为上线或者下线
            if (blogDO.getLine().equals("0") || blogDO.getLine().equals("2")) {
                BlogDO blogDOQ = blogMapper.queryBySeqAndLine(blogDO.getSeq(), "1");
                // 如果原来预览状态没有这个序号
                if (blogDOQ == null) {
                    update(id, "1", userBeanVO);
                }
                // 如果原来预览有这个序号
                if (blogDOQ != null) {
                    update(blogDOQ.getId(), "2", userBeanVO);
                    update(id, "1", userBeanVO);
                }
            }
            // 如果原来状态为预览状态
            if (blogDO.getLine().equals("0")) {
                return baseVO;
            }
        }
        // 如果是将状态改为下线
        if (lineDTO.getLine().equals("2")) {
            // 如果原来状态为上线或者预览
            if (blogDO.getLine().equals("0") || blogDO.getLine().equals("1")) {
                update(id, "2", userBeanVO);
            }
            // 如果原来状态为下线状态
            if (blogDO.getLine().equals("2")) {
                return baseVO;
            }
        }
        return baseVO;
    }

    private void update(Long id, String line, UserBeanVO userBeanVO) {
        BlogDO blogDO = new BlogDO();
        blogDO.setId(Long.valueOf(id));
        blogDO.setLine(line);
        blogDO.setUpdateTime(new Date());
        blogDO.setUpdateUser(userBeanVO.getUsername());
        blogMapper.updateBlog(blogDO);
    }

    @Override
    public RecommendBlogVO recommendBlogVOList(HomeBannerQueryDTO commonLanguageDTO, Long id, String webLine) {
        // 推荐博客
        List<BlogDO> blogDOList = blogMapper.recommendBlog(commonLanguageDTO.getLanguage().toUpperCase(), id, webLine);
        // 根据id查询博客内容信息
        BlogDO blog = blogMapper.getBlogById(id);
        RecommendBlogVO recommendBlogVO = new RecommendBlogVO();
        BeanUtils.copyProperties(blog, recommendBlogVO);
        List<BlogVO> blogVOList = new ArrayList<>();
        for (BlogDO blogDO : blogDOList) {
            BlogVO blogVO = new BlogVO();
            BeanUtils.copyProperties(blogDO, blogVO);
            blogVO.setContent(EmojiParser.parseToUnicode(blogDO.getContent()));
            blogVOList.add(blogVO);
        }
        recommendBlogVO.setRecommendBlogList(blogVOList);
        blogMapper.updateViewCount(id.intValue());
        return recommendBlogVO;
    }

    @Override
    public BaseVO updateThumbsCount(Integer id) {
        int row = blogMapper.updateThumbsCount(id);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    public BaseVO updateSeq(SeqDTO seqDTO, Long id, String userId) {
        BlogDO blog = blogMapper.getBlogById(id);
        if (blog == null) {
            return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
        }
        if (seqDTO.getSeq() == (blog.getSeq())) {
            return new BaseVO();
        }

        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        BlogDO blogDO = new BlogDO();
        BeanUtils.copyProperties(seqDTO, blogDO);
        blogDO.setId(id);
        blogDO.setUpdateTime(new Date());
        blogDO.setUpdateUser(userBeanVO.getUsername());
        if (!"2".equals(blog.getLine())) {
            // 同步修改整体排序
            blogMapper.updateAllSeq(blog.getSeq(), seqDTO.getSeq(), blog.getLine());
        }
        blogMapper.updateBlog(blogDO);
        return new BaseVO();
    }

    @Override
    public BaseVO updateRecommend(RecommendDTO recommendDTO, String userId) {
        BlogDO blogDO = new BlogDO();
        UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
        BeanUtils.copyProperties(recommendDTO, blogDO);
        blogDO.setUpdateTime(new Date());
        blogDO.setUpdateUser(userBeanVO.getUsername());
        int row = blogMapper.updateBlog(blogDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }
}
