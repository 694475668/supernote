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
import com.ratta.dto.ProductDTO;
import com.ratta.dto.ProductDescriptionAllDTO;
import com.ratta.dto.ProductDescriptionDTO;
import com.ratta.dto.ProductDescriptionUpdateDTO;
import com.ratta.dto.SeqDTO;
import com.ratta.service.ProductDescriptionService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.ProductDescriptionAllVO;
import com.ratta.vo.ProductDescriptionListVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "产品页详情接口集合")
@RestController
public class ProductDescriptionController {

	@Autowired
	private ProductDescriptionService productDescriptionService;

	@ApiOperation(value = "添加产品页详情接口")
	@PostMapping(value = "/product/description")
	@ApiImplicitParam(name = "productDescriptionDTO", dataType = "ProductDescriptionDTO")
	public BaseVO addProductDescription(@RequestBody @Valid ProductDescriptionDTO productDescriptionDTO,
			HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		return productDescriptionService.addProductDescription(productDescriptionDTO, userId);
	}

	@ApiOperation(value = "修改产品页详情图接口")
	@PutMapping(value = "/product/description/{id}")
	@ApiImplicitParam(name = "productDescriptionUpdateDTO", dataType = "ProductDescriptionUpdateDTO")
	public BaseVO updateProductDescription(@PathVariable("id") Integer id,
			@RequestBody @Valid ProductDescriptionUpdateDTO productDescriptionUpdateDTO, HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		return productDescriptionService.updateProductDescription(id, productDescriptionUpdateDTO, userId);
	}

	@ApiOperation(value = "上线、下线产品页详情接口")
	@PutMapping(value = "/product/description/line/{id}")
	@ApiImplicitParam(name = "lineDTO", dataType = "LineDTO")
	public BaseVO updateProductDescriptionLine(@PathVariable("id") Integer id, @RequestBody @Valid LineDTO lineDTO,
			HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		return productDescriptionService.updateProductDescriptionLine(id, lineDTO, userId);
	}

	@ApiOperation(value = "查询产品页详情接口(cms系统调用)")
	@PostMapping(value = "/product/description/all")
	@ApiImplicitParam(name = "productDescriptionAllDTO", dataType = "ProductDescriptionAllDTO")
	public CommonListVO<ProductDescriptionAllVO> queryProductBannerAll(
			@RequestBody @Valid ProductDescriptionAllDTO productDescriptionAllDTO) {
		return productDescriptionService.selectProductDescriptionAll(productDescriptionAllDTO);
	}
	
	@ApiOperation(value = "查询产品页详情接口(官网调用)")
	@PostMapping(value = "/product/description/list")
	@ApiImplicitParam(name = "productDescriptionListDTO", dataType = "ProductDescriptionListDTO")
	public CommonListVO<ProductDescriptionListVO> queryProductBannerList(@RequestBody @Valid ProductDTO productDTO,
			HttpServletRequest request) {
		String line = request.getHeader("Line");
		return productDescriptionService.selectProductDescriptionList(productDTO, line);
	}
	
	@ApiOperation(value = "修改产品页详情序号")
	@ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
	@PutMapping(value = "/product/description/seq/{id}")
	public BaseVO updateSeq(@RequestBody @Valid SeqDTO seqDTO, @PathVariable int id, HttpServletRequest request) {
		return productDescriptionService.updateSeq(seqDTO, id, request.getHeader("User-ID"));
	}
	
    @ApiOperation(value = "删除产品页产品页详情接口")
    @DeleteMapping(value = "/product/description/{id}")
    public BaseVO deleteProductDescription(@PathVariable("id") Integer id) {
    	return productDescriptionService.deleteProductDescription(id);
    }
}
