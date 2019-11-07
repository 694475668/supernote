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

import com.ratta.domain.ReferenceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 参数映射接口******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:54
 */
public interface ReferenceMapper {
    /**
     * <p>queryByParamCode</p>
     * <p>根据业务码查询参数信息</p>
     *
     * @param serial 参数编码
     * @param name   业务码
     * @return 參數信息
     */
    List<ReferenceDO> queryByParamCode(@Param("serial") String serial, @Param("name") String name);

    /**
     * 根据参数查询参数信息
     *
     * @param serial
     * @param name
     * @return
     */
    ReferenceDO queryByParam(@Param("serial") String serial, @Param("name") String name);

    /**
     * <p>query</p>
     * <p>根据查询条件查询系统参数详细信息</p>
     *
     * @param name 查询条件
     * @return 系统参数详细信息列表
     */
    List<ReferenceDO> query(@Param("name") String name);

    /**
     * <p>save</p>
     * <p>添加系统参数明细信息</p>
     *
     * @param referenceDO 系统参数明细信息
     * @return 数据库影响行数
     */
    int saveReference(ReferenceDO referenceDO);

    /**
     * <p>queryById</p>
     * <p>根据id查询系统参数详细信息</p>
     *
     * @param id 系统参数详细信息id
     * @return 系统参数详细信息
     */
    ReferenceDO queryById(@Param("id") Long id);

    /**
     * <p>update</p>
     * <p>更新系统参数详细信息</p>
     *
     * @param referenceDO 系统参数详细信息
     * @return 数据库影响行数
     */
    int updateReference(ReferenceDO referenceDO);

    /**
     * <p>delete</p>
     * <p>删除参数详细信息</p>
     *
     * @param id
     * @return 数据库影响行数
     */
    int deleteReference(@Param("id") Long id);

    /**
     * 查询参数
     *
     * @return
     */
    List<ReferenceDO> queryParam();
}
