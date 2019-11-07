package com.ratta.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.dto.SearchDTO;
import com.ratta.service.SearchService;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.SearchVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "搜索集合")
@RestController
public class SearchController {
	@Autowired
	private SearchService searchService;

	@ApiOperation(value = "搜索")
	@ApiImplicitParam(name = "searchDTO", dataType = "SearchDTO")
	@PostMapping(value = "/search")
	public CommonListVO<SearchVO> search(@RequestBody @Valid SearchDTO searchDTO) {
		return searchService.search(searchDTO);
	}
}
