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
public class FirmwareInfoLineDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 数据ID
     */
    private Long id;

    /**
     * 类型
     */
    private String type;

    /**
     * 版本号
     */
    private String version;
    /**
     * 修改点
     */
    private String modifyPoint;
    /**
     * 影响范围
     */
    private String impactScope;
    
    /**
     * 版本目的
     */
    private String versionPurpose;
    /**
     * 升级范围
     */
    private String upgrageScope;
    /**
     * 文件地址
     */
    private String fileUrl;
    /**
     * 合入标志(Y：合入 ,N：不是合入) 
     */
    private String mergeFlag;
    /**
     * 固件大版本ID
     */
    private String firmId;
    /**
     * 打包时间
     */
    private Date packagingTime;
}
