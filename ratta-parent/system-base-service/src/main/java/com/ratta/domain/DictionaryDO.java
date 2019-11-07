package com.ratta.domain;
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
 * ******Description:  数据字典表对象Dictionary ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-10 15:39
 */
@Data
public class DictionaryDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 数据ID
     */
    private Long id;

    /**
     * 数据名称
     */
    private String name;

    /**
     * 数据值
     */
    private String value;

    /**
     * 数据含义
     */
    private String valueMeaning;

    private String systemValue;

    private String systemValueCn;
    /**
     * 中文数据含义
     */
    private String valueCn;

    /**
     * 英文数据含义
     */
    private String valueEn;

    /**
     * 创建人
     */
    private String opUser;

    /**
     * 创建时间
     */
    private Date opTime;

    /**
     * 备注
     */
    private String remark;
}
