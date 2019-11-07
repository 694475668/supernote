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

import com.ratta.dto.FaqItemDTO;
import com.ratta.dto.FaqItemQueryDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.service.FaqItemService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FaqItemVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "FAQ条目集合")
@RestController
@RequestMapping("/faq")
public class FaqItemController {
	@Autowired
	private FaqItemService faqItemService;

	@ApiOperation(value = "查询FAQ条目信息")
	@ApiImplicitParam(name = "faqItemQueryDTO", dataType = "FaqItemQueryDTO")
	@PostMapping(value = "/item/list")
	public CommonListVO<FaqItemVO> query(@RequestBody @Valid FaqItemQueryDTO faqItemQueryDTO) {
		return faqItemService.query(faqItemQueryDTO);
	}

	@ApiOperation(value = "新增FAQ条目信息")
	@ApiImplicitParam(name = "faqItemDTO", dataType = "FaqItemDTO")
	@PostMapping(value = "/item")
	public BaseVO save(@RequestBody @Valid FaqItemDTO faqItemDTO, HttpServletRequest request) {
		return faqItemService.save(faqItemDTO, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改FAQ条目信息")
	@ApiImplicitParam(name = "faqItemDTO", dataType = "FaqItemDTO")
	@PutMapping(value = "/item/{id}")
	public BaseVO update(@RequestBody @Valid FaqItemDTO faqItemDTO, @PathVariable int id, HttpServletRequest request) {
		return faqItemService.update(faqItemDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "删除FAQ条目信息")
	@DeleteMapping(value = "/item/{id}")
	public BaseVO delete(@PathVariable int id) {
		return faqItemService.delete(id);
	}

	@ApiOperation(value = "查询单个FAQ条目信息")
	@GetMapping(value = "/item/{id}")
	public CommonVO<FaqItemVO> queryById(@PathVariable int id) {
		return faqItemService.queryById(id);
	}
	
	@ApiOperation(value = "修改FAQ条目上线下线")
	@ApiImplicitParam(name = "lineDTO", dataType = "LineDTO")
	@PutMapping(value = "/item/line/{id}")
	public BaseVO updateLine(@RequestBody @Valid LineDTO lineDTO, @PathVariable int id, HttpServletRequest request) {
		return faqItemService.updateLine(lineDTO, id, request.getHeader("User-ID"));
	}
	
	@ApiOperation(value = "修改FAQ条目序号")
	@ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
	@PutMapping(value = "/item/seq/{id}")
	public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable int id, HttpServletRequest request) {
		return faqItemService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
	}
}
