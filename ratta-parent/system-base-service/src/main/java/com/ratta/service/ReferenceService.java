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

import java.util.List;

import com.ratta.dto.ReferenceDTO;
import com.ratta.dto.ReferenceQueryDTO;
import com.ratta.dto.ReferenceVaguaDTO;
import com.ratta.vo.BaseVO;
import com.ratta.vo.CommonListVO;
import com.ratta.vo.ReferenceListVO;
import com.ratta.vo.ReferenceRespVO;
import com.ratta.vo.ReferenceVO;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version V1.0:
 * @author: 刘 明
 * @date:Created in 2019/6/22 0022 9:12
 */
public interface ReferenceService {
    /**
     * queryParam<br>
     * 根据条件查询系统参数<br>
     *
     * @param serial 参数编码
     * @param name   参数名称
     * @return 参数子类
     */
    List<ReferenceVO> queryParam(String serial, String name);

    /**
     * <p>query</p>
     * <p>根据查询条件查询系统参数详细信息</p>
     *
     * @param referenceVaguaDTO 查询条件
     * @return 数据模型
     */
    CommonListVO<ReferenceVO> query(ReferenceVaguaDTO referenceVaguaDTO);

    /**
     * <p>addReference</p>
     * <p>添加系统参数详细信息</p>
     *
     * @param referenceDTO 系统参数详细信息
     * @param userId       操作员
     * @return
     */
    BaseVO addReference(ReferenceDTO referenceDTO, String userId);

    /**
     * <p>queryById</p>
     * <p>根据id查询系统参数详细信息</p>
     *
     * @param id 系统参数详细信息id
     * @return 系统参数详细信息
     */
    ReferenceVO queryById(Long id);

    /**
     * <p>update</p>
     * <p>更新系统参数详细信息</p>
     *
     * @param referenceDTO 系统参数详细信息
     * @param id
     * @param userId
     * @return 参数详细返回枚举
     */
    BaseVO updateReference(ReferenceDTO referenceDTO, Long id, String userId);

    /**
     * <p>deleteReference</p>
     * <p>根据id删除系统参数详细信息</p>
     *
     * @param id 参数明细id
     * @return
     */
    BaseVO deleteReference(Long id);

    /**
     * 根据编码和业务码查询参数信息
     *
     * @param referenceQueryDTO
     * @return
     */
    ReferenceListVO queryReferenceByParam(ReferenceQueryDTO referenceQueryDTO);

    /**
     * 查询参数接口
     *
     * @return
     */
    ReferenceRespVO getReference();
}
