package com.ratta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.dto.AboutAllDTO;
import com.ratta.dto.AboutDTO;
import com.ratta.dto.AboutUpdateDTO;
import com.ratta.dto.LanDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.service.AboutService;
import com.ratta.vo.AboutAllVO;
import com.ratta.vo.AboutListVO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "关于页接口集合")
@RestController
public class AboutController {

	@Autowired
	private AboutService aboutService;

	@ApiOperation(value = "添加关于页数据接口")
	@PostMapping(value = "/about")
	@ApiImplicitParam(name = "aboutDTO", dataType = "AboutDTO")
	public BaseVO addAbout(@RequestBody @Valid AboutDTO aboutDTO,
			HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		return aboutService.addAbout(aboutDTO, userId);
	}

	@ApiOperation(value = "修改关于页数据接口")
	@PutMapping(value = "/about/{id}")
	@ApiImplicitParam(name = "aboutUpdateDTO", dataType = "AboutUpdateDTO")
	public BaseVO updateAbout(@PathVariable("id") Integer id,
			@RequestBody @Valid AboutUpdateDTO aboutUpdateDTO, HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		return aboutService.updateAbout(id, aboutUpdateDTO, userId);
	}

	@ApiOperation(value = "修改关于上线，预览，下线状态")
	@PutMapping(value = "/about/line/{id}")
	@ApiImplicitParam(name = "lineDTO", dataType = "LineDTO")
	public BaseVO updateAboutLine(@PathVariable("id") Integer id, @RequestBody @Valid LineDTO lineDTO,
			HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		return aboutService.updateAboutLine(id, lineDTO, userId);
	}

	@ApiOperation(value = "查询关于页数据接口(cms系统调用)")
	@PostMapping(value = "/about/all")
	@ApiImplicitParam(name = "productDescriptionAllDTO", dataType = "ProductDescriptionAllDTO")
	public CommonListVO<AboutAllVO> queryAboutAll(
			@RequestBody @Valid AboutAllDTO aboutAllDTO) {
		return aboutService.selectAboutAll(aboutAllDTO);
	}
	
	@ApiOperation(value = "查询关于页数据接口(官网调用)")
	@PostMapping(value = "/about/list")
	@ApiImplicitParam(name = "lanDTO", dataType = "LanDTO")
	public CommonListVO<AboutListVO> queryAboutList(@RequestBody @Valid LanDTO lanDTO,
			HttpServletRequest request) {
		String line = request.getHeader("Line");
		return aboutService.selectAboutList(lanDTO, line);
	}
	
	@ApiOperation(value = "修改关于页数据序号")
	@ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
	@PutMapping(value = "/about/seq/{id}")
	public BaseVO updateSeq(@PathVariable int id, @RequestBody @Valid SeqDTO seqDTO, HttpServletRequest request) {
		return aboutService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
	}
	
    @ApiOperation(value = "删除关于页数据接口")
    @DeleteMapping(value = "/about/{id}")
    public BaseVO deleteAbout(@PathVariable("id") Integer id) {
    	return aboutService.deleteAbout(id);
    }
}
