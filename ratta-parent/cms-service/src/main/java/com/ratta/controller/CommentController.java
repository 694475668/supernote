package com.ratta.controller;

import com.ratta.dto.*;
import com.ratta.service.CommentService;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

/**
 * ******Title:<p> 程序的奥秘 </p>******
 * ******Description:<p> </p>******
 * ******Company: <p> 雷塔智能科技有限公司 <p> ******
 *
 * @version :
 * @author: 《刘明》 【QQ 694475668】
 * @date:Created in 2019/9/24 19:58
 */
@Api(description = "微博评论接口集合")
@RestController
@SuppressWarnings("rawtypes")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private HttpServletRequest request;

    @ApiOperation(value = "官网根据博客id的查询所属评论")
    @PostMapping("/manage/comment/website/{id}")
    public CommonListVO listComment(@RequestBody CommentQueryDTO commentQueryDTO, @PathVariable("id") Integer id) {
        return commentService.listComment(commentQueryDTO, id);
    }

    @ApiOperation(value = "cms查询所有的评论")
    @PostMapping("/manage/comment/query")
    public CommonListVO listCommentCms(@RequestBody CommentQueryDTO commentQueryDTO) {
        return commentService.listComment(commentQueryDTO, null);
    }


    @ApiOperation(value = "官网添加微博评论接口")
    @PostMapping("/manage/comment")
    public BaseVO insertComment(@RequestBody CommentDTO commentDTO) {
        return commentService.insertComment(commentDTO, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "评论置顶接口")
    @ApiImplicitParam(name = "seqDTO", dataType = "SeqDTO")
    @PutMapping(value = "/manage/comment/seq/{id}")
    public BaseVO updateSeq(@RequestBody @Valid CommentSeqDTO commentSeqDTO, @PathVariable Long id, HttpServletRequest request) {
        return commentService.updateSeq(commentSeqDTO, id, request.getHeader("User-ID"));
    }

    @ApiOperation(value = "评论审核接口")
    @ApiImplicitParam(name = "commentAuditDTO", dataType = "CommentAuditDTO")
    @PutMapping(value = "/manage/comment/audit/{id}")
    public BaseVO updateAudit(@RequestBody @Valid CommentAuditDTO commentAuditDTO, @PathVariable Long id, HttpServletRequest request) {
        return commentService.updateAudit(commentAuditDTO, id, request.getHeader("User-ID"));
    }
}
