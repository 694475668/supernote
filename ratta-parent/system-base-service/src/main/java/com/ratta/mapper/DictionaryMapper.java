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

import com.ratta.domain.DictionaryDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 数据字段Maper映射接口******
 * ****** Company: ******
 *
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/9 0009 18:19
 */
public interface DictionaryMapper {
    /**
     * 根据数据名称查询数据字典
     *
     * @param name
     * @param language
     * @return
     */
    List<DictionaryDO> findDictionaryByName(@Param("name") String name, @Param("language") String language);

    /**
     * <p>query</p>
     * <p>查询数据字典</p>
     *
     * @param name         查询参数
     * @param valueMeaning 查询参数
     * @return 数据字典列表
     */
    List<DictionaryDO> query(@Param("name") String name, @Param("valueMeaning") String valueMeaning);

    /**
     * 添加数据字典信息
     *
     * @param dictionaryDO
     * @return
     */
    int addDictionary(DictionaryDO dictionaryDO);

    /**
     * 修改数据字典信息
     *
     * @param dictionaryDO
     * @return
     */
    int updateDictionary(DictionaryDO dictionaryDO);

    /**
     * 删除数据字典信息
     *
     * @param id
     * @return
     */
    int delDictionary(@Param("id") Long id);

    /**
     * 根据ID查询信息
     *
     * @param id
     * @return
     */
    DictionaryDO queryDictionaryById(@Param("id") Long id);

    /**
     * <p>query</p>
     * <p>根据数据名称和数据值进行查询</p>
     *
     * @param name
     * @param value
     * @return 数据字典信息
     */
    List<DictionaryDO> queryByPamram(@Param("name") String name, @Param("value") String value);

    /**
     * 校验参数
     *
     * @param name
     * @param value
     * @return
     */
    List<DictionaryDO> checkPamram(@Param("name") String name, @Param("value") String value);


}
