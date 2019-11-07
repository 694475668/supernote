package com.ratta.controller;

import com.ratta.dto.*;
import com.ratta.service.HomeAwardService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.HomeAwardBeanVO;
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
@Api(description = "首页荣获奖项图接口集合")
@RestController
@SuppressWarnings("rawtypes")
public class HomeAwardController {

	@Autowired
	private HomeAwardService homeAwardService;

	@Autowired
	private HttpServletRequest request;

	@ApiOperation(value = "获取奖项轮播图接口(官网)")
	@PostMapping("/manage/homeAward/website")
	public CommonListVO listHomeAward(@RequestBody HomeAwardQueryDTO homeAwardQueryDTO) {
		return homeAwardService.listHomeAward(homeAwardQueryDTO,request.getHeader("Line"));
	}

	@ApiOperation(value = "获取奖项轮播图接口(cms)")
	@PostMapping("/manage/homeAward/query")
	public CommonListVO listHomeAwardCms(@RequestBody HomeAwardQueryDTO homeAwardQueryDTO) {
		return homeAwardService.listHomeAward(homeAwardQueryDTO,"");

	}

	@ApiOperation(value = "根据ID获取奖项轮播图接口")
	@GetMapping("/manage/homeAward/{id}")
	public HomeAwardBeanVO getHomeAwardById(@PathVariable("id") Long id) {
		return homeAwardService.getHomeAwardById(id);
	}

	@ApiOperation(value = "添加奖项轮播图接口")
	@PostMapping("/manage/homeAward")
	public BaseVO insertHomeBanner(@RequestBody @Valid HomeAwardDTO homeAwardDTO) {
		return homeAwardService.insertHomeAward(homeAwardDTO, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改奖项轮播图接口")
	@PutMapping("/manage/homeAward")
	public BaseVO updateHomeBanner(@RequestBody @Valid HomeAwardDTO homeAwardDTO) {
		return homeAwardService.updateHomeAward(homeAwardDTO, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "删除奖项轮播图接口")
	@DeleteMapping("/manage/homeAward/{id}")
	public BaseVO deleteHomeAward(@PathVariable("id") Long id) {
		return homeAwardService.delHomeAward(id);
	}

	@ApiOperation(value = "奖项轮播图上下线接口")
	@PutMapping("/manage/homeAward/{id}")
	public BaseVO homeAwardLine(@RequestBody LineDTO lineDTO, @PathVariable("id") Long id) {
		return homeAwardService.HomeAwardLine(lineDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改轮播图序号")
	@ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
	@PutMapping(value = "/manage/homeAward/seq/{id}")
	public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable Long id, HttpServletRequest request) {
		return homeAwardService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
	}
}
