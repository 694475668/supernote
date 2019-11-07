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

import com.ratta.dto.LineDTO;
import com.ratta.dto.ProductBannerAllDTO;
import com.ratta.dto.ProductBannerDTO;
import com.ratta.dto.ProductDTO;
import com.ratta.service.ProductBannerService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.ProductBannerAllVO;
import com.ratta.vo.ProductBannerOneVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "产品页banner图接口集合")
@RestController
public class ProductBannerController {

	@Autowired
	private ProductBannerService productBannerService;
	
    @ApiOperation(value = "查询产品页banner图接口（官网调用）")
    @PostMapping(value = "/product/banner/one")
    @ApiImplicitParam(name = "productDTO", dataType = "ProductDTO")
    public ProductBannerOneVO queryProductBanner(@RequestBody @Valid ProductDTO productDTO, HttpServletRequest request) {
    	String line = request.getHeader("Line");
    	return productBannerService.selectProductBannerOne(productDTO, line);
    }

    @ApiOperation(value = "查询产品页banner图接口（cms系统调用调用）")
    @PostMapping(value = "/product/banner/all")
    @ApiImplicitParam(name = "productBannerAllDTO", dataType = "ProductBannerAllDTO")
    public CommonListVO<ProductBannerAllVO> queryProductBannerAll(@RequestBody @Valid ProductBannerAllDTO productBannerAllDTO) {
    	return productBannerService.selectProductBannerAll(productBannerAllDTO);
    }
    
    @ApiOperation(value = "添加产品页banner图接口")
    @PostMapping(value = "/product/banner")
    @ApiImplicitParam(name = "productBannerDTO", dataType = "ProductBannerDTO")
    public BaseVO addProductBanner(@RequestBody @Valid ProductBannerDTO productBannerDTO, HttpServletRequest request) {
    	String userId = request.getHeader("User-ID");
    	return productBannerService.addProductBanner(productBannerDTO, userId);
    }
    
    @ApiOperation(value = "修改产品页banner图接口")
    @PutMapping(value = "/product/banner/{id}")
    @ApiImplicitParam(name = "productBannerDTO", dataType = "ProductBannerDTO")
    public BaseVO updateProductBanner(@PathVariable("id") Integer id, @RequestBody @Valid ProductBannerDTO productBannerDTO, HttpServletRequest request) {
    	String userId = request.getHeader("User-ID");
    	return productBannerService.updateProductBanner(id, productBannerDTO, userId);
    }
    
    @ApiOperation(value = "上线、下线产品页banner图接口")
    @PutMapping(value = "/product/banner/line/{id}")
    @ApiImplicitParam(name = "lineDTO", dataType = "LineDTO")
    public BaseVO updateProductBannerLine(@PathVariable("id") Integer id, @RequestBody @Valid LineDTO lineDTO, 
    		HttpServletRequest request) {
    	String userId = request.getHeader("User-ID");
    	return productBannerService.updateProductBannerLine(id, lineDTO, userId);
    }
    
    @ApiOperation(value = "删除产品页banner图接口")
    @DeleteMapping(value = "/product/banner/{id}")
    public BaseVO deleteProductBanner(@PathVariable("id") Integer id) {
    	return productBannerService.deleteProductBanner(id);
    }
}
