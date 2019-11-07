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

import com.ratta.dto.*;
import com.ratta.vo.*;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-26 15:25
 */
public interface EquipmentLogService {

    /**
     * <p>query</p>
     * <p>查询设备日志信息</p>
     *
     * @param equipmentLogQueryDTO 查询参数
     * @return 数据模型
     */
    CommonListVO<EquipmentLogVO> findAll(EquipmentLogQueryDTO equipmentLogQueryDTO);

    /**
     * <p>delete</p>
     * <p>根据id查询删除设备日志信息</p>
     *
     * @param equipmentLogDeleteDTO
     * @return
     */
    BaseVO deleteEquipmentLog(EquipmentLogDTO equipmentLogDeleteDTO);

    /**
     * 添加备注
     *
     * @param equipmentLogAddRemarkDTO
     * @return
     */
    BaseVO addRemark(EquipmentLogAddRemarkDTO equipmentLogAddRemarkDTO);

    /**
     * 审阅
     *
     * @param equipmentLogAddRemarkDTO
     * @return
     */
    BaseVO passed(EquipmentLogAddRemarkDTO equipmentLogAddRemarkDTO);

    /**
     * 下载完成后更新掉下载状态
     *
     * @param equipmentLogStatusDTO
     * @return
     */
    BaseVO updateIsDownload(EquipmentLogStatusDTO equipmentLogStatusDTO);

    /**
     * 下载文件
     *
     * @param equipmentLogDTO
     */
    EquipmentLogDownLoadUrlVO download(EquipmentLogDTO equipmentLogDTO);

    /**
     * 根据Id查询备注信息
     *
     * @param id
     * @return
     */
    EquipmentLogVO getEquipmentLog(Long id);


    /**
     * 上传请求地址
     *
     * @param equipmentLogDTO
     * @return
     */
    EquipmentLogUpLoadUrlVO apply(EquipmentLogUpLoadApplyDTO equipmentLogUpLoadApplyDTO);

    /**
     * 添加日志信息
     *
     * @return
     */
    BaseVO finish(EquipmentLogUpLoadFinishDTO equipmentLogUpLoadFinishDTO);

}
