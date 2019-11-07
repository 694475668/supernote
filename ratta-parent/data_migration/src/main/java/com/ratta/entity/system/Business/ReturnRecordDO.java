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
public class ReturnRecordDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 数据ID
     */
    private Long id;

    /**
     * 设备序列号
     */
    private String equipmentNumber;

    /**
     * 1：退货  2：换货
     */
    private String type;
    /**
     * 换货时，被换货的设备号
     */
    private String exchangeEquipmentNumber;
    /**
     * 退换货编号
     */
    private String serialNumber;
    
    /**
     * 退换货时间
     */
    private String returnTime;
    /**
     * 业务处理人
     */
    private String businessHandler;
    /**
     * 操作人
     */
    private String opUser;
    /**
     * 操作时间
     */
    private Date opTime;
    /**
     * 设备健康状态（0：正常  1：维修中 2：报废）
     */
    private String healthyState;
    /**
     * 备注
     */
    private String remark;
}
