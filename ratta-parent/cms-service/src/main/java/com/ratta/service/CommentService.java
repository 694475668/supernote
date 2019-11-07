package com.ratta.service;

import com.ratta.dto.*;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-14 9:25
 */
@SuppressWarnings("rawtypes")
public interface CommentService {
    /**
     * 根据i的查询博客所属评论
     *
     * @param commentQueryDTO
     * @param id
     * @return
     */
    CommonListVO listComment(CommentQueryDTO commentQueryDTO, Integer id);

    /**
     * 添加评论
     *
     * @param commentDTO
     * @param userId
     * @return
     */
    BaseVO insertComment(CommentDTO commentDTO, String userId);


    /**
     * 修改序号
     *
     * @param commentSeqDTO
     * @param id
     * @param userId
     * @return
     */
    BaseVO updateSeq(CommentSeqDTO commentSeqDTO, Long id, String userId);

    /**
     * 审核
     *
     * @param commentAuditDTO
     * @param id
     * @param userId
     * @return
     */
    BaseVO updateAudit(CommentAuditDTO commentAuditDTO, Long id, String userId);
}
