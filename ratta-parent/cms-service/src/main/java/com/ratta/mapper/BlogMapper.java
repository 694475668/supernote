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

import com.ratta.domain.BlogDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ******Title:程序的奥秘 ****** ******Description: ****** ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:54
 */
@Mapper
public interface BlogMapper {
    /**
     * 查询推荐博客而且还需要过滤掉本身
     *
     * @param language
     * @param id
     * @param webLine
     * @return
     */
    List<BlogDO> recommendBlog(@Param("language") String language, @Param("id") Long id, @Param("webLine") String webLine);

    /**
     * 官网通过语言和id查询内容信息
     *
     * @param blogDO
     * @return
     */
    BlogDO getBlogByIdForWebsite(BlogDO blogDO);

    /**
     * 查询所有博客
     *
     * @return
     */
    List<BlogDO> query();

    /**
     * @return
     */
    BlogDO queryBySeqAndLine(@Param("seq") Integer seq, @Param("line") String line);

    /**
     * 查询所有博客
     *
     * @param language
     * @param line
     * @param recommendFlag
     * @param id
     * @param webLine
     * @return
     */
    List<BlogDO> listBlog(@Param("language") String language, @Param("line") String line,
                          @Param("recommendFlag") String recommendFlag, @Param("id") String id, @Param("webLine") String webLine);

    /**
     * 修改点赞数
     *
     * @param id
     * @return
     */
    int updateThumbsCount(@Param("id") Integer id);

    /**
     * 修改浏览量
     *
     * @param id
     * @return
     */
    int updateViewCount(@Param("id") Integer id);

    /**
     * 判断序号是否重复
     *
     * @param seq
     * @return
     */
    BlogDO seqIsExist(@Param("seq") Integer seq);

    /**
     * 根据id查询博客信息
     *
     * @param id
     * @return
     */
    BlogDO getBlogById(@Param("id") Long id);

    /**
     * 新增博客
     *
     * @param blogDO
     * @return
     */
    int insertBlog(BlogDO blogDO);

    /**
     * 修改博客
     *
     * @param blogDO
     * @return
     */
    int updateBlog(BlogDO blogDO);

    /**
     * 删除博客
     *
     * @param id
     * @return
     */
    int delBlog(@Param("id") Long id);

    /**
     * 修改序号
     *
     * @param oldSeq
     * @param newSeq
     * @return
     */
    int updateAllSeq(int oldSeq, int newSeq, String line);

    /**
     * <p>
     * queryMaxSeq
     * </p>
     * <p>
     * 查询最大的排序
     * </p>
     *
     * @return 最大序号
     */
    int queryMaxSeq();

}
