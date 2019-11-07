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

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 分页传递对象DTO******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-18 16:56
 */
@Data
public class PageDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 起始页
     */
    @ApiModelProperty(value = "起始页", required = true)
    @NotNull(message = "起始页不能为空")
    private Integer pageNo;
    /**
     * 页大小
     */
    @ApiModelProperty(value = "页大小", required = true)
    @NotNull(message = "页大小不能为空")
    private Integer pageSize;

    /**
     * 排序字段
     */
    @ApiModelProperty(value = "排序字段")
    private String sortField;

    /**
     * 排序规则
     */
    @ApiModelProperty(value = "排序规则")
    private String sortRules;
}
