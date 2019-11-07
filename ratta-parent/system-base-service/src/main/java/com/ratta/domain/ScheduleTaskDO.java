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
 * ******Description: 计划任务表对象ScheduleTask******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-20 18:15
 */
@Data
public class ScheduleTaskDO implements Serializable {
    private static final long serialVersionUID = 1L;
    /**
     * 任务ID
     */
    private Integer id;
    /**
     * 任务名称
     */
    private String name;
    /**
     * 任务描述
     */
    private String remark;
    /**
     * 克隆表达式
     */
    private String cron;
    /**
     * 任务状态，0 启用，1 停用
     */
    private String status;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建人员
     */
    private String createUser;
    /**
     * 修改时间
     */
    private Date updateTime;
    /**
     * 修改人员
     */
    private String updateUser;
    /**
     * 业务码
     */
    private String bzCode;
}
