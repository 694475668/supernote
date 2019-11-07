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

import com.ratta.domain.HomeAwardDO;
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
public interface HomeAwardMapper {
    /**
     * 查询所有轮播图
     *
     * @param language
     * @param line
     * @param webLine
     * @return
     */
    List<HomeAwardDO> listHomeAward(@Param("language") String language, @Param("line") String line, @Param("webLine") String webLine);

    /**
     * 根据id查询轮播图信息
     *
     * @param id
     * @return
     */
    HomeAwardDO getHomeAwardById(@Param("id") Long id);


    /**
     * 根据条件查询轮播图信息
     *
     * @return
     */
    HomeAwardDO queryBySeqAndLine(@Param("seq") Integer seq, @Param("line") String line);

    /**
     * 查询所有记录
     *
     * @return
     */
    List<HomeAwardDO> query();

    /**
     * 判断序号是否重复
     *
     * @param seq
     * @return
     */
    HomeAwardDO seqIsExist(@Param("seq") Integer seq);

    /**
     * 新增轮播图
     *
     * @param homeAwardDO
     * @return
     */
    int insertHomeAward(HomeAwardDO homeAwardDO);

    /**
     * 修改轮播图
     *
     * @param homeAwardDO
     * @return
     */
    int updateHomeAward(HomeAwardDO homeAwardDO);

    /**
     * 删除轮播图
     *
     * @param id
     * @return
     */
    int delHomeAward(@Param("id") Long id);

    /**
     * 修改轮播图序号
     *
     * @param oldSeq
     * @param newSeq
     * @return
     */
    int updateAllSeq(int oldSeq, int newSeq, String line);

    /**
     * <p>queryMaxSeq</p>
     * <p>查询最大的排序</p>
     *
     * @return 最大序号
     */
    int queryMaxSeq();

}
