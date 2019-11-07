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

import com.ratta.dto.FirmwareManualDTO;
import com.ratta.dto.FirmwareManualQueryDTO;
import com.ratta.dto.FirmwareManualTypeLanDTO;
import com.ratta.dto.LineDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.service.FirmwareManualService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.FirmwareManualPartVO;
import com.ratta.vo.FirmwareManualVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "固件说明书集合")
@RestController
@RequestMapping("/firmware")
public class FirmwareManualController {
	@Autowired
	private FirmwareManualService FirmwareManualService;

	@ApiOperation(value = "查询固件说明书信息")
	@ApiImplicitParam(name = "firmwareManualQueryDTO", dataType = "FirmwareManualQueryDTO")
	@PostMapping(value = "/manual/list")
	public CommonListVO<FirmwareManualVO> query(@RequestBody @Valid FirmwareManualQueryDTO firmwareManualQueryDTO) {
		return FirmwareManualService.query(firmwareManualQueryDTO);
	}
	
	@ApiOperation(value = "根据语言和类型查询固件说明书列表信息")
	@ApiImplicitParam(name = "firmwareManualTypeLanDTO", dataType = "FirmwareManualTypeLanDTO")
	@PostMapping(value = "/manual/list/type")
	public CommonListVO<FirmwareManualPartVO> queryByTypeAndLan(@RequestBody @Valid FirmwareManualTypeLanDTO firmwareManualTypeLanDTO, HttpServletRequest request) {
		return FirmwareManualService.queryByTypeAndLan(firmwareManualTypeLanDTO, request.getHeader("Line"));
	}

	@ApiOperation(value = "新增固件说明书信息")
	@ApiImplicitParam(name = "firmwareManualDTO", dataType = "FirmwareManualDTO")
	@PostMapping(value = "/manual")
	public BaseVO save(@RequestBody @Valid FirmwareManualDTO firmwareManualDTO, HttpServletRequest request) {
		return FirmwareManualService.save(firmwareManualDTO, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改固件说明书信息")
	@ApiImplicitParam(name = "firmwareManualDTO", dataType = "FirmwareManualDTO")
	@PutMapping(value = "/manual/{id}")
	public BaseVO update(@RequestBody @Valid FirmwareManualDTO firmwareManualDTO, @PathVariable int id, HttpServletRequest request) {
		return FirmwareManualService.update(firmwareManualDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "删除固件说明书信息")
	@DeleteMapping(value = "/manual/{id}")
	public BaseVO delete(@PathVariable int id) {
		return FirmwareManualService.delete(id);
	}

	@ApiOperation(value = "查询单个固件说明书信息")
	@GetMapping(value = "/manual/{id}")
	public CommonVO<FirmwareManualVO> queryById(@PathVariable int id) {
		return FirmwareManualService.queryById(id);
	}
	
	@ApiOperation(value = "修改固件说明书上线下线")
	@ApiImplicitParam(name = "lineDTO", dataType = "LineDTO")
	@PutMapping(value = "/manual/line/{id}")
	public BaseVO updateLine(@RequestBody @Valid LineDTO lineDTO, @PathVariable int id, HttpServletRequest request) {
		return FirmwareManualService.updateLine(lineDTO, id, request.getHeader("User-ID"));
	}
	
	@ApiOperation(value = "修改固件说明书序号")
	@ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
	@PutMapping(value = "/manual/seq/{id}")
	public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable int id, HttpServletRequest request) {
		return FirmwareManualService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
	}
}
