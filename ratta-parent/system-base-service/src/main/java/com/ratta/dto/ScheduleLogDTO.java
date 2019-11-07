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
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * ******Title:程序的奥秘 ******
 * ******Description: 计划日志表传输对象ScheduleLogDTO******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-20 18:15
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class ScheduleLogDTO extends PageDTO {
    private static final long serialVersionUID = 1L;
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private String taskId;
    /**
     * 开始执行时间
     */
    @ApiModelProperty(value = "开始执行时间")
    private String ksrq;
    /**
     * 结束执行时间
     */
    @ApiModelProperty(value = "结束执行时间")
    private String jsrq;
}
