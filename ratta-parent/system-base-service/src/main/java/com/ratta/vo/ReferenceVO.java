package com.ratta.vo;
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

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 参考表对象Reference******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:39
 */
@Data
public class ReferenceVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 参数id
     */
    private Long id;
    /**
     * serial
     */
    private String serial;
    /**
     * 参数名
     */
    private String name;
    /**
     * 参数值
     */
    private String value;
    /**
     * 中文简体参数值说明
     */
    private String valueCn;
    /**
     * 修改人员
     */
    private String opUser;
    /**
     * 修改时间
     */
    private Date opTime;
    /**
     * 备注
     */
    private String remark;

}
