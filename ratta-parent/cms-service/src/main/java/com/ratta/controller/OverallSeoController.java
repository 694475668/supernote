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

import com.ratta.dto.OverAllSeoLanAndPageDTO;
import com.ratta.dto.OverAllSeoQueryDTO;
import com.ratta.dto.OverallSeoDTO;
import com.ratta.service.OverallSeoService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.CommonVO;
import com.ratta.vo.OverallSeoPartVO;
import com.ratta.vo.OverallSeoVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "整体SEO集合")
@RestController
@RequestMapping("/seo")
public class OverallSeoController {
	@Autowired
	private OverallSeoService overallSeoService;

	@ApiOperation(value = "查询整体SEO信息")
	@ApiImplicitParam(name = "overAllSeoQueryDTO", dataType = "OverAllSeoQueryDTO")
	@PostMapping(value = "/overall/list")
	public CommonListVO<OverallSeoVO> query(@RequestBody @Valid OverAllSeoQueryDTO overAllSeoQueryDTO) {
		return overallSeoService.query(overAllSeoQueryDTO);
	}

	@ApiOperation(value = "新增整体SEO信息")
	@ApiImplicitParam(name = "overallSeoDTO", dataType = "OverallSeoDTO")
	@PostMapping(value = "/overall")
	public BaseVO save(@RequestBody @Valid OverallSeoDTO overallSeoDTO, HttpServletRequest request) {
		return overallSeoService.save(overallSeoDTO, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "修改整体SEO信息")
	@ApiImplicitParam(name = "overallSeoDTO", dataType = "OverallSeoDTO")
	@PutMapping(value = "/overall/{id}")
	public BaseVO update(@RequestBody @Valid OverallSeoDTO overallSeoDTO, @PathVariable int id, HttpServletRequest request) {
		return overallSeoService.update(overallSeoDTO, id, request.getHeader("User-ID"));
	}

	@ApiOperation(value = "删除整体SEO信息")
	@DeleteMapping(value = "/overall/{id}")
	public BaseVO delete(@PathVariable int id) {
		return overallSeoService.delete(id);
	}

	@ApiOperation(value = "查询单个整体SEO信息")
	@GetMapping(value = "/overall/{id}")
	public CommonVO<OverallSeoVO> queryById(@PathVariable int id) {
		return overallSeoService.queryById(id);
	}
	
	@ApiOperation(value = "根据语言查询整体SEO信息")
	@ApiImplicitParam(name = "overAllSeoLanAndPageDTO", dataType = "OverAllSeoLanAndPageDTO")
	@PostMapping(value = "/overall/lan")
	public CommonVO<OverallSeoPartVO> queryByLan(@RequestBody @Valid OverAllSeoLanAndPageDTO overAllSeoLanAndPageDTO) {
		return overallSeoService.queryByLan(overAllSeoLanAndPageDTO);
	}
}
