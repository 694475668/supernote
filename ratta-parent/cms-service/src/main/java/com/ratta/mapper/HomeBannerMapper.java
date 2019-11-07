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

import com.ratta.domain.HomeBannerDO;
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
public interface HomeBannerMapper {
    /**
     * 查询所有轮播图
     *
     * @param language
     * @param line
     * @param type
     * @param webLine
     * @return
     */
    List<HomeBannerDO> listHomeBanner(@Param("language") String language, @Param("line") String line,
                                      @Param("type") String type, @Param("webLine") String webLine);

    /**
     * 根据id查询轮播图信息
     *
     * @param id
     * @return
     */
    HomeBannerDO getHomeBannerById(@Param("id") Long id);

    /**
     * @param type
     * @param seq
     * @param line
     * @return
     */
    HomeBannerDO queryBySeqAndLine(@Param("type") String type, @Param("seq") Integer seq, @Param("line") String line);

    /**
     * 查询所有数据
     *
     * @return
     */
    List<HomeBannerDO> query();

    /**
     * 判断序号是否重复
     *
     * @param seq
     * @param type
     * @return
     */
    HomeBannerDO seqIsExist(@Param("seq") Integer seq, @Param("type") String type);

    /**
     * 新增轮播图
     *
     * @param homeBannerDO
     * @return
     */
    int insertHomeBanner(HomeBannerDO homeBannerDO);

    /**
     * 修改轮播图
     *
     * @param homeBannerDO
     * @return
     */
    int updateHomeBanner(HomeBannerDO homeBannerDO);

    /**
     * 删除轮播图
     *
     * @param id
     * @return
     */
    int delHomeBanner(@Param("id") Long id);

    /**
     * 修改轮播图序号
     *
     * @param oldSeq
     * @param newSeq
     * @param type
     * @return
     */
    int updateAllSeq(int oldSeq, int newSeq, String type, String line);

    /**
     * <p>
     * queryMaxSeq
     * </p>
     * <p>
     * 查询最大的排序
     * </p>
     *
     * @param type 类型
     * @return 最大序号
     */
    int queryMaxSeq(String type);

}
