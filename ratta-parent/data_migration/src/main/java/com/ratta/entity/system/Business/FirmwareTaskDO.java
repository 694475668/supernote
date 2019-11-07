package com.ratta.entity.system.Business;
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

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:  数据字典表对象Dictionary ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-10 15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirmwareTaskDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 数据ID
     */
    private Long id;

    /**
     * 固件版本
     */
    private String firmwareVersion;

    /**
     * 类型（1：审核测试  2：发布 3：刷机）
     */
    private String type;
    /**
     * 设备号
     */
    private String equipmentNumber;
    /**
     * 设备用途
     */
    private String equipmentPurpose;
    
    /**
     * 设备类型
     */
    private String equipmentModel;
    /**
     * 是否是历史记录 （0：否  1：是历史）
     */
    private String isHistory;
    /**
     * 状态（0：正常  1：撤销）
     */
    private String status;
    /**
     * 撤销人员
     */
    private String revokeUser;
    /**
     * 撤销时间
     */
    private Date revokeTime;
    /**
     * 发布人员
     */
    private String createUser;
    /**
     * 发布时间
     */
    private Date createTime;
}
