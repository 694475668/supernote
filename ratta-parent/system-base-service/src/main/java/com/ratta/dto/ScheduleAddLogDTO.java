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

import java.io.Serializable;

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
public class ScheduleAddLogDTO implements Serializable {
    private static final long serialVersionUID = 1L;

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
    /**
     * 任务ID
     */
    @ApiModelProperty(value = "任务ID")
    private Long taskId;
    /**
     * 执行结果(0成功,1失败)
     */
    @ApiModelProperty(value = "执行结果(0成功,1失败)")
    private String result;
}
