package com.ratta.controller;

import com.ratta.dto.*;
import com.ratta.service.HomeBannerService;
import com.ratta.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * ******Title:
 * <p>
 * 程序的奥秘
 * </p>
 * ****** ******Description:
 * <p>
 * </p>
 * ****** ******Company:
 * <p>
 * 雷塔智能科技有限公司
 * <p>
 * ******
 *
 * @version :
 * @author: 《刘明》 【QQ 694475668】
 * @date:Created in 2019/9/24 19:58
 */
@Api(description = "首页banner接口集合")
@RestController
public class HomeBannerController {

    @Autowired
    private HomeBannerService homeBannerService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "获取轮播图接口(官网)")
    @PostMapping("/manage/homeBanner/website")
    public CommonListVO<HomeBannerOutVO> listHomeAward(@RequestBody TypeLanDTO typeLanDTO) {
        return homeBannerService.listOutHomeBanner(typeLanDTO, request.getHeader("Line"));
    }

    @ApiOperation(value = "获取轮播图接口(cms)")
    @PostMapping("/manage/homeBanner/query")
    public CommonListVO<HomeBannerVO> listHomeAwardCms(@RequestBody HomeBannerQueryDTO homeBannerLanguageDTO) {
        return homeBannerService.listHomeBanner(homeBannerLanguageDTO);
    }

    @ApiOperation(value = "根据ID获取轮播图接口")
    @GetMapping("/manage/homeBanner/{id}")
    public HomeBannerBeanVO getHomeBannerById(@PathVariable("id") Long id) {
        return homeBannerService.getHomeBannerById(id);
    }

    @ApiOperation(value = "添加轮播图接口")
    @PostMapping("/manage/homeBanner")
    public BaseVO insertHomeBanner(@RequestBody HomeBannerDTO homeBannerDTO) {
        return homeBannerService.insertHomeBanner(homeBannerDTO, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "修改轮播图接口")
    @PutMapping("/manage/homeBanner")
    public BaseVO updateHomeBanner(@RequestBody HomeBannerDTO homeBannerDTO) {
        return homeBannerService.updateHomeBanner(homeBannerDTO, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "删除轮播图接口")
    @DeleteMapping("/manage/homeBanner/{id}")
    public BaseVO deleteHomeBanner(@PathVariable("id") Long id) {
        return homeBannerService.delHomeBanner(id);
    }

    @ApiOperation(value = "轮播图上下线接口")
    @PutMapping("/manage/homeBanner/{id}")
    public BaseVO HomeBannerLine(@RequestBody LineDTO lineDTO, @PathVariable("id") Long id) {
        return homeBannerService.HomeBannerLine(lineDTO, id, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "修改轮播图序号")
    @ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
    @PutMapping(value = "/manage/homeBanner/seq/{id}")
    public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable Long id, HttpServletRequest request) {
        return homeBannerService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
    }
}
