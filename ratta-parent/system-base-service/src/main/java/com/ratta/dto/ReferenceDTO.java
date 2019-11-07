package com.ratta.dto;
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

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

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
public class ReferenceDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotBlank(message = "编码不能为空")
    private String serial;
    /**
     * 业务码
     */
    @ApiModelProperty(value = "业务码")
    @NotBlank(message = "业务码不能为空")
    private String name;
    /**
     * 变量值
     */
    @ApiModelProperty(value = "变量值")
    @NotBlank(message = "变量值不能为空")
    private String value;
    /**
     * 参数名
     */
    @ApiModelProperty(value = "参数名")
    @NotBlank(message = "参数名不能为空")
    private String valueCn;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
}
