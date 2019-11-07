package com.ratta.service;
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

import com.ratta.dto.DictionaryDTO;
import com.ratta.dto.DictionaryQueryDTO;
import com.ratta.dto.DictionaryVagueDTO;
import com.ratta.vo.*;

import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:字典服务  ******
 * ******Company: ******
 *
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/9 0009 18:17
 */
public interface DictionaryService {
    /**
     * 根据名称查询数据字典
     *
     * @param name
     * @param language
     * @return
     */
    List<DictionarysVO> getResourceTypeList(String name, String language);


    /**
     * <p>query</p>
     * <p>查询数据字典信息</p>
     *
     * @param dictionaryVagueDTO
     * @return
     */
    CommonListVO<DictionaryVagueVO> query(DictionaryVagueDTO dictionaryVagueDTO);

    /**
     * <p>save</p>
     * <p>添加数据字典信息</p>
     *
     * @param dictionaryDTO 数据字典信息
     * @return
     */
    BaseVO saveDictionary(DictionaryDTO dictionaryDTO, String userId);

    /**
     * <p>save</p>
     * <p>修改字典信息</p>
     *
     * @param dictionaryDTO 数据字典信息
     * @return
     */
    BaseVO updateDictionary(DictionaryDTO dictionaryDTO, Long id, String userId);

    /**
     * <p>save</p>
     * <p>删除字典信息</p>
     *
     * @param id
     * @return
     */
    BaseVO deleteDictionary(Long id);

    /**
     * 根据ID查询信息
     *
     * @param id
     * @return
     */
    DictionaryVO queryDictionaryById(Long id);

    /**
     * 根据数据名称和数据值进行查询
     *
     * @param dictionaryQueryDTO
     * @return
     */
    DictionaryListVO queryDictionaryByNameAndValue(DictionaryQueryDTO dictionaryQueryDTO);
}
