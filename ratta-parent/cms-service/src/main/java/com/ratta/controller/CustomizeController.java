package com.ratta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.dto.CustomizeAllDTO;
import com.ratta.dto.CustomizeDTO;
import com.ratta.dto.CustomizeRemarkDTO;
import com.ratta.service.CustomizeService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CustomizeAllVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "套餐定制接口集合")
@RestController
public class CustomizeController {

	@Autowired
	private CustomizeService customizeService;
	
	@ApiOperation(value = "定制套餐接口")
	@PostMapping(value = "/customize")
	@ApiImplicitParam(name = "customizeDTO", dataType = "CustomizeDTO")
	public BaseVO addCustomize(@RequestBody @Valid CustomizeDTO customizeDTO) {
		return customizeService.addCustomize(customizeDTO);
	}
	
	@ApiOperation(value = "备注套餐定制接口")
	@PutMapping(value = "/customize/remark/{id}")
	@ApiImplicitParam(name = "customizeRemarkDTO", dataType = "CustomizeRemarkDTO")
	public BaseVO updateCustomizeRemark(@PathVariable("id") Integer id,
			@RequestBody @Valid CustomizeRemarkDTO customizeRemarkDTO, HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		return customizeService.updateCustomizeRemark(id, customizeRemarkDTO, userId);
	}
	
	@ApiOperation(value = "查询套餐定制数据集合")
	@PostMapping(value = "/customize/all")
	@ApiImplicitParam(name = "customizeAllDTO", dataType = "CustomizeAllDTO")
	public CommonListVO<CustomizeAllVO> queryCustomizeAll(
			@RequestBody @Valid CustomizeAllDTO customizeAllDTO) {
		return customizeService.selectCustomizeAll(customizeAllDTO);
	}
}
