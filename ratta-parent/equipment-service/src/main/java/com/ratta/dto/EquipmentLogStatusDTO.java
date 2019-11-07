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
 * ******Description:******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-06-26 18:51
 */
@Data
public class EquipmentLogStatusDTO implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * 设备日志
     */
    @ApiModelProperty(value = "设备日志")
    @NotNull(message = "设备日志不能为空")
    private Long id;
    /**
     * 日志名
     */
    @ApiModelProperty(value = "日志名")
    @NotBlank(message = "日志名不能为空")
    private String logName;

    /**
     * 是否下载成功
     */
    @ApiModelProperty(value = "是否下载成功状态 true为成功,false为失败")
    @NotBlank(message = "下载状态不能为空")
    private String flag;
}
