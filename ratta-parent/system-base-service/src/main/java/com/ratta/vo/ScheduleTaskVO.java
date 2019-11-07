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

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

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
public class ScheduleTaskVO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private Integer id;
    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    private String name;
    /**
     * 任务描述
     */
    @ApiModelProperty(value = "任务描述")
    private String remark;
    /**
     * 克隆表达式
     */
    @ApiModelProperty(value = "克隆表达式")
    private String cron;
    /**
     * 任务状态，0 启用，1 停用
     */
    @ApiModelProperty(value = "任务状态，0 启用，1 停用")
    private String status;

}
