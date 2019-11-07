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

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

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
public class AppVersionDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 数据ID
     */
    private Long id;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 包名
     */
    private String packageName;
    /**
     * app名称
     */
    private String appName;
    /**
     * app版本
     */
    private String appVersion;

    /**
     * app下载地址
     */
    private String url;

    /**
     * 更新标识
     */
    private String updateFlag;

    /**
     * 操作人
     */
    private String opUser;
    
    /**
     * 创建时间
     */
    private Date opTime;
    
    /**
     * MD5
     */
    private String md5;
    
    /**
     * 内部版本号
     */
    private String versionNo;
    
    /**
     * APP版本发布标识：1：待发布  2：灰度  3：发布  4：不发布
     */
    private String deployFlag;
    
    /**
     * 审核标识 1：待审核  2：通过  3：不通过
     */
    private String auditingFlag;
    /**
     * app连接环境
     */
    private String appEnvironment;
    
}
