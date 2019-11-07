package com.ratta.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ratta.constants.Constant;
import com.ratta.domain.CommentDO;
import com.ratta.dto.CommentAuditDTO;
import com.ratta.dto.CommentDTO;
import com.ratta.dto.CommentQueryDTO;
import com.ratta.dto.CommentSeqDTO;
import com.ratta.enumeration.ErrorCodeEnum;
import com.ratta.mapper.CommentMapper;
import com.ratta.service.CommentService;
import com.ratta.util.ObjectConvertUtil;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommentVO;
import com.ratta.vo.CommonListVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-14 9:25
 */
@Service
@SuppressWarnings("rawtypes")
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentMapper commentMapper;


    @Override
    @SuppressWarnings("unchecked")
    public CommonListVO listComment(CommentQueryDTO commentQueryDTO, Integer id) {
        CommonListVO commonListVO = new CommonListVO();

        // 这句代码相当于分页语句的拼接,如果是mysql会拼接limit
        if (!"".equals(commentQueryDTO.getSortField())) {
            PageHelper.startPage(commentQueryDTO.getPageNo(), commentQueryDTO.getPageSize(), commentQueryDTO.getSortField() + " " + commentQueryDTO.getSortRules());
        } else {
            PageHelper.startPage(commentQueryDTO.getPageNo(), commentQueryDTO.getPageSize());
        }

        List<CommentDO> commentDOList = commentMapper.listComment(id, commentQueryDTO.getLanguage().toUpperCase(), commentQueryDTO.getAudit(), commentQueryDTO.getRoofPlacement(), commentQueryDTO.getTitleId());
        PageInfo<CommentDO> uInfo = new PageInfo<>(commentDOList);
        //总页数
        commonListVO.setPages(uInfo.getPages());
        //总数据
        commonListVO.setTotal(uInfo.getTotal());

        List<CommentVO> commentVOList = new ArrayList<>();
        CommentVO commentVO = null;

        if (Constant.CN_EN.equals(commentQueryDTO.getLanguage().toUpperCase())) {
            for (CommentDO commentDO : commentDOList) {
                commentVO = new CommentVO();
                BeanUtils.copyProperties(commentDO, commentVO);
                commentVO.setUserName(commentDO.getUserName());
                commentVO.setContent(commentDO.getContent());
                commentVOList.add(commentVO);
            }
        } else {
            commentVOList = ObjectConvertUtil.convertInstance().objectConvert(commentDOList, CommentDO.class);
        }
        commonListVO.setVoList(commentVOList);
        return commonListVO;
    }

    @Override
    public BaseVO insertComment(CommentDTO commentDTO, String userId) {
        int num = new Double(Math.random() * 100000).intValue();
        CommentDO commentDO = new CommentDO();
        BeanUtils.copyProperties(commentDTO, commentDO);
        commentDO.setUserName("tourist【" + num + "】");
        commentDO.setCreateTime(new Date());
        commentDO.setUpdateTime(new Date());
        int row = commentMapper.insertComment(commentDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }

    @Override
    public BaseVO updateSeq(CommentSeqDTO commentSeqDTO, Long id, String userId) {
        CommentDO comment = commentMapper.getCommentById(id);
        CommentDO commentDO = null;
        if (comment == null) {
            return new BaseVO(false, ErrorCodeEnum.E1441.getKey(), ErrorCodeEnum.E1441.getValue());
        }
        if (commentSeqDTO.getSeq() == comment.getSeq()) {
            return new BaseVO();
        }

        if (comment.getRoofPlacement().equals("1")) {
            commentDO = new CommentDO();
            commentDO.setId(id);
            commentDO.setRoofPlacement("0");
            commentMapper.updateComment(commentDO);
        }

        //同步修改整体排序
        commentMapper.updateAllSeq(comment.getSeq(), commentSeqDTO.getSeq(), commentSeqDTO.getBlogId());
        commentDO = new CommentDO();
        commentDO.setSeq(commentSeqDTO.getSeq());
        commentDO.setId(id);
        commentDO.setUpdateTime(new Date());
        commentMapper.updateComment(commentDO);
        return new BaseVO();
    }

    @Override
    public BaseVO updateAudit(CommentAuditDTO commentAuditDTO, Long id, String userId) {
        CommentDO commentDO = new CommentDO();
        BeanUtils.copyProperties(commentAuditDTO, commentDO);
        commentDO.setId(id);
        commentDO.setUpdateTime(new Date());
        int row = commentMapper.updateComment(commentDO);
        if (row > 0) {
            return new BaseVO();
        }
        return new BaseVO(false, ErrorCodeEnum.E1421.getKey(), ErrorCodeEnum.E1421.getValue());
    }
}
