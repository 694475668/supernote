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

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * ******Title:程序的奥秘 ******
 * ******Description:  数据字典传输对象Dictionary ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-10 15:39
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class DictionaryBeanVO extends BaseVO {
    private static final long serialVersionUID = 1L;

    /**
     * 数据ID
     */
    @ApiModelProperty(value = "数据ID")
    private Long id;

    /**
     * 数据名称
     */
    @ApiModelProperty(value = "数据名称")
    private String name;

    /**
     * 数据值
     */
    @ApiModelProperty(value = "数据值")
    private String value;

    /**
     * 中文数据含义
     */
    @ApiModelProperty(value = "中文数据含义")
    private String value_cn;

    /**
     * 英文数据含义
     */
    @ApiModelProperty(value = "英文数据含义")
    private String value_en;

    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
