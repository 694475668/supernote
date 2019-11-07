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
public class FirmwareEquipDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 数据ID
     */
    private Long id;

    /**
     * 设备型号
     */
    private Long equipmentModel;

    /**
     *上传时间
     */
    private Date uploadTime;
    /**
     * 固件版本
     */
    private String firmwareVersion;
}
