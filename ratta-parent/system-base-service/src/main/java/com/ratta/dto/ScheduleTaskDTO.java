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
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 根据用户ID获取角色信息展示对象******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-17 13:39
 */
@Data
public class ScheduleTaskDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    @NotNull(message = "任务ID不能为空")
    private Integer id;
    /**
     * 任务描述
     */
    @ApiModelProperty(value = "任务描述")
    private String remark;
    /**
     * 克隆表达式
     */
    @ApiModelProperty(value = "克隆表达式")
    @NotBlank(message = "克隆表达式不能为空")
    private String cron;

}
