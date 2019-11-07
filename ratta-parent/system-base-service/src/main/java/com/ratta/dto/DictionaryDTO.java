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
 * ******Description:  数据字典传输对象Dictionary ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-10 15:39
 */
@Data
public class DictionaryDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 编码
     */
    @ApiModelProperty(value = "编码")
    @NotBlank(message = "编码不能为空")
    private String value;

    /**
     * 业务码
     */
    @ApiModelProperty(value = "业务码")
    @NotBlank(message = "业务码不能为空")
    private String name;

    /**
     * 业务码名称
     */
    @ApiModelProperty(value = "业务码名称")
    @NotBlank(message = "业务码名称不能为空")
    private String remark;

    /**
     * 中文编码名称
     */
    @ApiModelProperty(value = "中文编码名称")
    @NotBlank(message = "中文编码名称不能为空")
    private String valueCn;

    /**
     * 英文编码名称
     */
    @ApiModelProperty(value = "英文编码名称")
    @NotBlank(message = "英文编码名称不能为空")
    private String valueEn;

}
