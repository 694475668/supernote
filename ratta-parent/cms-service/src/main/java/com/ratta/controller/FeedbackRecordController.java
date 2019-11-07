package com.ratta.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.dto.FeedbackRecordDTO;
import com.ratta.dto.FeedbackRecordPartDTO;
import com.ratta.dto.FeedbackRecordQueryDTO;
import com.ratta.service.FeedbackRecordService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.FeedbackRecordVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;

@Api(description = "问题反馈集合")
@RestController
@RequestMapping("/feedback")
public class FeedbackRecordController {
	@Autowired
	private FeedbackRecordService feedbackRecordService;

	@ApiOperation(value = "查询问题反馈信息")
	@ApiImplicitParam(name = "feedbackRecordQueryDTO", dataType = "FeedbackRecordQueryDTO")
	@PostMapping(value = "/record/list")
	public CommonListVO<FeedbackRecordVO> query(@RequestBody @Valid FeedbackRecordQueryDTO feedbackRecordQueryDTO) {
		return feedbackRecordService.query(feedbackRecordQueryDTO);
	}

	@ApiOperation(value = "新增问题反馈信息")
	@ApiImplicitParam(name = "feedbackRecordDTO", dataType = "FeedbackRecordDTO")
	@PostMapping(value = "/record")
	public BaseVO save(@RequestBody @Valid FeedbackRecordDTO feedbackRecordDTO) {
		return feedbackRecordService.save(feedbackRecordDTO);
	}
	
	@ApiOperation(value = "修改问题反馈处理状态和备注")
	@ApiImplicitParam(name = "feedbackRecordPartDTO", dataType = "FeedbackRecordPartDTO")
	@PutMapping(value = "/record/part/{id}")
	public BaseVO updateStatus(@RequestBody @Valid FeedbackRecordPartDTO feedbackRecordPartDTO, @PathVariable int id, HttpServletRequest request) {
		return feedbackRecordService.updatePart(feedbackRecordPartDTO, id, request.getHeader("User-ID"));
	}
}
