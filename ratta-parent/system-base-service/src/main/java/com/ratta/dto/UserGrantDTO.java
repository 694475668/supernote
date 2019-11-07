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

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 用户授权DTO******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-20 14:21
 */
@Data
public class UserGrantDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * 批量操作ID
     */
    @ApiModelProperty(value = "用户id列表", required = true)
    @NotEmpty(message = "id列表不能为空")
    private String[] userIdList;

    @ApiModelProperty(value = "角色id列表", required = true)
    @NotEmpty(message = "id列表不能为空")
    private String[] roleIdList;
}
