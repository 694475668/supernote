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

import com.ratta.dto.LineDTO;
import com.ratta.dto.PartSeriesDTO;
import com.ratta.dto.LanguageDTO;
import com.ratta.dto.PartSeriesQueryDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.service.PartSeriesService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonNoPageListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.PartSeriesOutVO;
import com.ratta.vo.PartSeriesVO;
import com.ratta.vo.RecommendBoxVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "配件系列接口集合")
@RestController
public class PartSeriesController {

	@Autowired
	private PartSeriesService partSeriesService;

	@ApiOperation(value = "查询配件系列列表(cms系统)")
	@ApiImplicitParam(name = "partSeriesQueryDTO", dataType = "PartSeriesQueryDTO")
	@PostMapping(value = "/part/series/list")
	public CommonListVO<PartSeriesVO> queryInner(@RequestBody @Valid PartSeriesQueryDTO partSeriesQueryDTO) {
		return partSeriesService.queryInner(partSeriesQueryDTO);
	}

	@ApiOperation(value = "查询配件系列列表(官网)")
	@ApiImplicitParam(name = "languageDTO", dataType = "LanguageDTO")
	@PostMapping(value = "/part/series/out/list")
	public CommonNoPageListVO<PartSeriesOutVO> queryOut(@RequestBody @Valid LanguageDTO languageDTO,
			HttpServletRequest request) {
		return partSeriesService.queryOut(languageDTO, request.getHeader("Line"));
	}

	@ApiOperation(value = "查询单个配件系列信息(官网)")
	@PostMapping(value = "/part/series/out/{serialNumber}")
	public CommonVO<PartSeriesOutVO> queryOutById(@PathVariable String serialNumber, @RequestBody @Valid LanguageDTO languageDTO,
			HttpServletRequest request) {
		return partSeriesService.queryOutById(serialNumber, languageDTO, request.getHeader("Line"));
	}

	@ApiOperation(value = "查询推荐页面下拉框")
	@PostMapping(value = "/part/series/recommend/box/list")
	public CommonNoPageListVO<RecommendBoxVO> queryRecommendBox() {
		return partSeriesService.queryRecommendBox();
	}

	@ApiOperation(value = "查询单个配件系列信息(cms)")
	@GetMapping(value = "/part/series/{id}")
	public CommonVO<PartSeriesVO> queryById(@PathVariable int id) {
		return partSeriesService.queryById(id);
	}

	@ApiOperation(value = "新增配件系列")
	@ApiImplicitParam(name = "partSeriesDTO", dataType = "PartSeriesDTO")
	@PostMapping(value = "/part/series")
	public BaseVO save(@RequestBody @Valid PartSeriesDTO partSeriesDTO, HttpServletRequest request) {
		return partSeriesService.add(partSeriesDTO, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改配件系列")
	@ApiImplicitParam(name = "partSeriesDTO", dataType = "PartSeriesDTO")
	@PutMapping(value = "/part/series/{id}")
	public BaseVO update(@RequestBody @Valid PartSeriesDTO partSeriesDTO, @PathVariable int id,
			HttpServletRequest request) {
		return partSeriesService.update(partSeriesDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "删除配件系列")
	@DeleteMapping(value = "/part/series/{id}")
	public BaseVO delete(@PathVariable int id) {
		return partSeriesService.delete(id);
	}

	@ApiOperation(value = "修改配件系列上线，预览，下线状态")
	@ApiImplicitParam(name = "lineDTO", dataType = "LineDTO")
	@PutMapping(value = "/part/series/line/{id}")
	public BaseVO updateLine(@RequestBody @Valid LineDTO lineDTO, @PathVariable int id, HttpServletRequest request) {
		return partSeriesService.updateLine(lineDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改配件系列序号")
	@ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
	@PutMapping(value = "/part/series/seq/{id}")
	public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable int id, HttpServletRequest request) {
		return partSeriesService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
	}
}
