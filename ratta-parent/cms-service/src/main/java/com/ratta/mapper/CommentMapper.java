package com.ratta.mapper;
/*
                                           _ooOoo_
                                          o8888888o
                                          88" . "88
                                          (| -_- |)
                                          O\  =  /O
                                       ____/`---'\____
                                     .'  \\|     |//  `.
                                    /  \\|||  :  |||//  \
                                   /  _||||| -:- |||||-  \
                                   |   | \\\  -  /// |   |
                                   | \_|  ''\---/''  |   |
                                   \  .-\__  `-`  ___/-. /
                                 ___`. .'  /--.--\  `. . __
                              ."" '<  `.___\_<|>_/___.'  >'"".
                             | | :  `- \`.;`\ _ /`;.`/ - ` : | |
                             \  \ `-.   \_ __\ /__ _/   .-` /  /
                        ======`-.____`-.___\_____/___.-`____.-'======
                                           `=---='
                        ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^
                                 佛祖保佑       永无BUG
*/

import com.ratta.domain.CommentDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:54
 */
@Mapper
public interface CommentMapper {
    /**
     * 根据i的查询博客所属评论
     *
     * @param language
     * @param blogId
     * @param line
     * @param language
     * @param roofPlacement
     * @param audit
     * @param id
     * @return
     */
    List<CommentDO> listComment(@Param("blogId") Integer blogId, @Param("language") String language, @Param("audit") String audit, @Param("roofPlacement") String roofPlacement, @Param("id") String id);


    /**
     * 添加评论
     *
     * @param commentDO
     * @return
     */
    int insertComment(CommentDO commentDO);

    /**
     * 修改评论
     *
     * @param commentDO
     * @return
     */
    int updateComment(CommentDO commentDO);

    /**
     *
     * @return
     */
    List<CommentDO> queryCommentCount();

    /**
     * 修改序号
     *
     * @param oldSeq
     * @param newSeq
     * @param blogId
     * @return
     */
    int updateAllSeq(@Param("oldSeq") int oldSeq, @Param("newSeq") int newSeq, @Param("blogId") Integer blogId);

    /**
     * 根据id查询评论信息
     *
     * @param id
     * @return
     */
    CommentDO getCommentById(@Param("id") Long id);

    /**
     * 删除评论
     *
     * @param blogId
     * @return
     */
    int delCommentByBlogId(@Param("blogId") Long blogId);
}
