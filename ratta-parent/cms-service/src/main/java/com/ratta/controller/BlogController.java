package com.ratta.controller;

import com.ratta.dto.*;
import com.ratta.service.BlogService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.BlogVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.RecommendBlogVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * ******Title:<p> 程序的奥秘 </p>******
 * ******Description:<p> </p>******
 * ******Company: <p> 雷塔智能科技有限公司 <p> ******
 *
 * @version :
 * @author: 《刘明》 【QQ 694475668】
 * @date:Created in 2019/9/24 19:58
 */
@Api(description = "微博列表接口集合")
@RestController
@SuppressWarnings("rawtypes")
public class BlogController {

    @Autowired
    private BlogService blogService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "获取微博列表接口(官网)")
    @PostMapping("/manage/blog/website")
    public CommonListVO listBlog(@RequestBody BlogQueryDTO blogQueryDTO) {
        return blogService.listBlog(blogQueryDTO, request.getHeader("Line"));
    }

    @ApiOperation(value = "获取微博列表接口(cms)")
    @PostMapping("/manage/blog/query")
    public CommonListVO listBlogCms(@RequestBody BlogQueryDTO blogQueryDTO) {
        return blogService.listBlog(blogQueryDTO, "");
    }

    @ApiOperation(value = "根据ID获取微博列表接口")
    @GetMapping("/manage/blog/{id}")
    public BlogVO getBlogById(@PathVariable("id") Long id) {
        return blogService.getBlogById(id);
    }

    @ApiOperation(value = "添加微博接口")
    @PostMapping("/manage/blog")
    public BaseVO insertBlog(@RequestBody BlogDTO blogDTO) {
        return blogService.insertBlog(blogDTO, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "官网添加点赞数")
    @PutMapping("/manage/blog/website/{id}")
    BaseVO updateThumbsCount(@PathVariable("id") Integer id) {
        return blogService.updateThumbsCount(id);
    }

    @ApiOperation(value = "修改微博接口")
    @PutMapping("/manage/blog")
    public BaseVO updateBlog(@RequestBody BlogDTO blogDTO) {
        return blogService.updateBlog(blogDTO, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "删除微博接口")
    @DeleteMapping("/manage/blog/{id}")
    public BaseVO deleteBlog(@PathVariable("id") Long id) {
        return blogService.delBlog(id);
    }

    @ApiOperation(value = "微博上下线接口")
    @PutMapping("/manage/blog/{id}")
    public BaseVO homeBlog(@RequestBody LineDTO lineDTO, @PathVariable("id") Long id) {
        return blogService.blogLine(lineDTO, id, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "官网通过微博id查询内容以及推荐博客接口")
    @PostMapping("/manage/blog/{id}")
    public RecommendBlogVO recommendBlog(@RequestBody HomeBannerQueryDTO commonLanguageDTO, @PathVariable("id") Long id) {
        return blogService.recommendBlogVOList(commonLanguageDTO, id, request.getHeader("Line"));
    }

    @ApiOperation(value = "修改博客序号接口")
    @ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
    @PutMapping(value = "/manage/blog/seq/{id}")
    public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable Long id) {
        return blogService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "修改博客推荐接口")
    @ApiImplicitParam(name = "recommendDTO", dataType = "RecommendDTO")
    @PutMapping(value = "/manage/blog/recommend")
    public BaseVO updateRecommend(@RequestBody @Valid RecommendDTO recommendDTO) {
        return blogService.updateRecommend(recommendDTO, request.getHeader("User-ID"));
    }
}
