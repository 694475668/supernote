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
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-26 15:31
 */
@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
public class EquipmentLogVO {
    /**
     * 设备ID
     */
    @ApiModelProperty(value = "设备ID")
    private Long id;
    /**
     * 设备号
     */
    @ApiModelProperty(value = "设备号")
    private String equipmentNumber;
    /**
     * 日志名
     */
    @ApiModelProperty(value = "日志名")
    private String logName;
    /**
     * 日志类型(1：普通日志；2：错误日志）
     */
    @ApiModelProperty(value = "日志类型(1：普通日志；2：错误日志）")
    private String type;
    /**
     * 固件版本号
     */
    @ApiModelProperty(value = "固件版本号")
    private String firmwareVersion;
    /**
     * 日志上传时间
     */
    @ApiModelProperty(value = "日志上传时间")
    private String createTime;
    /**
     * 备注
     */
    @ApiModelProperty(value = "备注")
    private String remark;
    /**
     * 是否已下载（0：未下载 1：已下载）
     */
    @ApiModelProperty(value = "是否已下载（0：未下载 1：已下载）")
    private String isDownload;
    /**
     * 标识（0：未查看 1：已查看 2：已审阅）
     */
    @ApiModelProperty(value = "标识（0：未查看 1：已查看 2：已审阅）")
    private String flag;
}
