package com.ratta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.dto.FaqTitleDTO;
import com.ratta.dto.FaqTitleQueryDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.dto.TypeLanDTO;
import com.ratta.service.FaqTitleService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FaqTitleItemVO;
import com.ratta.vo.FaqTitlePartVO;
import com.ratta.vo.FaqTitleVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "FAQ标题集合")
@RestController
@RequestMapping("/faq")
public class FaqTitleController {
	@Autowired
	private FaqTitleService faqTitleService;

	@ApiOperation(value = "查询FAQ标题信息")
	@ApiImplicitParam(name = "faqTitleQueryDTO", dataType = "FaqTitleQueryDTO")
	@PostMapping(value = "/title/list")
	public CommonListVO<FaqTitleVO> query(@RequestBody @Valid FaqTitleQueryDTO faqTitleQueryDTO) {
		return faqTitleService.query(faqTitleQueryDTO);
	}
	
	@ApiOperation(value = "根据语言和类型查询标题条目列表")
	@ApiImplicitParam(name = "typeLanDTO", dataType = "TypeLanDTO")
	@PostMapping(value = "/title/item/list")
	public CommonNoPageListVO<FaqTitleItemVO> queryByTypeAndLan(@RequestBody @Valid TypeLanDTO typeLanDTO, HttpServletRequest request) {
		return faqTitleService.queryByTypeAndLan(typeLanDTO, request.getHeader("Line"));
	}
	
	@ApiOperation(value = "通过类型查询FAQ列表信息")
	@GetMapping(value = "/title/list/{type}")
	public CommonNoPageListVO<FaqTitlePartVO> queryByType(@PathVariable String type) {
		return faqTitleService.queryByType(type);
	}

	@ApiOperation(value = "新增FAQ标题信息")
	@ApiImplicitParam(name = "faqTitleDTO", dataType = "FaqTitleDTO")
	@PostMapping(value = "/title")
	public BaseVO save(@RequestBody @Valid FaqTitleDTO faqTitleDTO, HttpServletRequest request) {
		return faqTitleService.save(faqTitleDTO, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改FAQ标题信息")
	@ApiImplicitParam(name = "faqTitleDTO", dataType = "FaqTitleDTO")
	@PutMapping(value = "/title/{id}")
	public BaseVO update(@RequestBody @Valid FaqTitleDTO faqTitleDTO, @PathVariable int id, HttpServletRequest request) {
		return faqTitleService.update(faqTitleDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "删除FAQ标题信息")
	@DeleteMapping(value = "/title/{id}")
	public BaseVO delete(@PathVariable int id) {
		return faqTitleService.delete(id);
	}

	@ApiOperation(value = "查询单个FAQ标题信息")
	@GetMapping(value = "/title/{id}")
	public CommonVO<FaqTitleVO> queryById(@PathVariable int id) {
		return faqTitleService.queryById(id);
	}
	
	@ApiOperation(value = "修改FAQ标题上线下线")
	@ApiImplicitParam(name = "lineDTO", dataType = "LineDTO")
	@PutMapping(value = "/title/line/{id}")
	public BaseVO updateLine(@RequestBody @Valid LineDTO lineDTO, @PathVariable int id, HttpServletRequest request) {
		return faqTitleService.updateLine(lineDTO, id, request.getHeader("User-ID"));
	}
	
	@ApiOperation(value = "修改FAQ标题序号")
	@ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
	@PutMapping(value = "/title/seq/{id}")
	public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable int id, HttpServletRequest request) {
		return faqTitleService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
	}
}
