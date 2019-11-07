package com.ratta.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.service.ProductCustomizeService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.ProductCustomizeVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "产品页套餐定制是否展示查询接口")
@RestController
public class ProductCustomizeController {

	@Autowired
	private ProductCustomizeService productCustomizeService;
	
    @ApiOperation(value = "产品页套餐定制是否展示查询接口（官网调用）")
    @GetMapping(value = "/product/customize")
    public ProductCustomizeVO queryProductCustomize() {
    	return productCustomizeService.selectProductCustomize();
    }
    
	@ApiOperation(value = "产品页套餐定制启用、停用接口")
	@PutMapping(value = "/product/customize/{flag}")
	public BaseVO updateProductCustomizeFlag(@PathVariable("flag") String flag,
			HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		return productCustomizeService.updateProductCustomizeFlag(flag, userId);
	}
}
