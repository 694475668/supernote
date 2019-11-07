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
import org.springframework.web.bind.annotation.RestController;

import com.ratta.dto.LanguageDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.PartDTO;
import com.ratta.dto.PartLanguageDTO;
import com.ratta.dto.PartQueryDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.service.PartService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.PartCustomizeVO;
import com.ratta.vo.PartHomeVO;
import com.ratta.vo.PartOutVO;
import com.ratta.vo.PartVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "配件接口集合")
@RestController
public class PartController {

	@Autowired
	private PartService partService;

	@ApiOperation(value = "查询配件列表(cms系统)")
	@ApiImplicitParam(name = "partQueryDTO", dataType = "PartQueryDTO")
	@PostMapping(value = "/part/list")
	public CommonListVO<PartVO> queryInner(@RequestBody @Valid PartQueryDTO partQueryDTO) {
		return partService.queryInner(partQueryDTO);
	}

	@ApiOperation(value = "查询配件列表(首页列表)")
	@ApiImplicitParam(name = "languageDTO", dataType = "LanguageDTO")
	@PostMapping(value = "/part/home/list")
	public CommonNoPageListVO<PartHomeVO> queryPartHome(@RequestBody @Valid LanguageDTO languageDTO,
			HttpServletRequest request) {
		return partService.queryPartHome(languageDTO, request.getHeader("Line"));
	}

	@ApiOperation(value = "查询配件列表(官网产品页选配列表)")
	@ApiImplicitParam(name = "partLanguageDTO", dataType = "PartLanguageDTO")
	@PostMapping(value = "/part/customize/list")
	public CommonVO<PartCustomizeVO> queryPartCustomize(@RequestBody @Valid PartLanguageDTO partLanguageDTO,
			HttpServletRequest request) {
		return partService.queryPartCustomize(partLanguageDTO, request.getHeader("Line"));
	}

	@ApiOperation(value = "查询配件列表(官网配件页列表)")
	@ApiImplicitParam(name = "languageDTO", dataType = "LanguageDTO")
	@PostMapping(value = "/part/out/list/{serialNumber}")
	public CommonNoPageListVO<PartOutVO> queryPartOut(@RequestBody @Valid LanguageDTO languageDTO,
			@PathVariable String serialNumber, HttpServletRequest request) {
		return partService.queryPartOut(languageDTO, serialNumber, request.getHeader("Line"));
	}

	@ApiOperation(value = "查询单个配件信息")
	@GetMapping(value = "/part/{id}")
	public CommonVO<PartVO> queryById(@PathVariable int id) {
		return partService.queryById(id);
	}

	@ApiOperation(value = "新增配件")
	@ApiImplicitParam(name = "partDTO", dataType = "PartDTO")
	@PostMapping(value = "/part")
	public BaseVO save(@RequestBody @Valid PartDTO partDTO, HttpServletRequest request) {
		return partService.add(partDTO, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改配件")
	@ApiImplicitParam(name = "partDTO", dataType = "PartDTO")
	@PutMapping(value = "/part/{id}")
	public BaseVO update(@RequestBody @Valid PartDTO partDTO, @PathVariable int id, HttpServletRequest request) {
		return partService.update(partDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "删除配件")
	@DeleteMapping(value = "/part/{id}")
	public BaseVO delete(@PathVariable int id) {
		return partService.delete(id);
	}

	@ApiOperation(value = "修改配件上线下线")
	@ApiImplicitParam(name = "lineDTO", dataType = "LineDTO")
	@PutMapping(value = "/part/line/{id}")
	public BaseVO updateLine(@RequestBody @Valid LineDTO lineDTO, @PathVariable int id, HttpServletRequest request) {
		return partService.updateLine(lineDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改配件序号")
	@ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
	@PutMapping(value = "/part/seq/{id}")
	public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable int id, HttpServletRequest request) {
		return partService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
	}
}
