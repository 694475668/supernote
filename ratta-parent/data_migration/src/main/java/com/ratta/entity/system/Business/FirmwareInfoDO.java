package com.ratta.entity.system.Business;
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
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * ******Title:程序的奥秘 ****** ******Description: 数据字典表对象Dictionary ******
 * ******Company: ******
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019-06-10 15:39
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FirmwareInfoDO implements Serializable {
	private static final long serialVersionUID = 1L;
	/**
	 * 数据ID
	 */
	private Long id;

	/**
	 * 区域
	 */
	private String area;

	/**
	 * 定制
	 */
	private String custom;
	/**
	 * 配置
	 */
	private String configuration;
	/**
	 * 固件版本
	 */
	private String firmwareVersion;

	/**
	 * 状态
	 */
	private String status;
	/**
	 * 修改点
	 */
	private String modifyPoint;
	/**
	 * 影响范围
	 */
	private String impactScope;
	/**
	 * 版本目的
	 */
	private String versionPurpose;
	/**
	 * 升级范围
	 */
	private String upgrageScope;
	/**
	 * 打包时间
	 */
	private Date packagingTime;
	/**
	 * 审核结果（Y：同意 N：不同意）
	 */
	private String auditFlag;
	/**
	 * 审核时间
	 */
	private Date auditTime;
	/**
	 * 审核内容
	 */
	private String auditInfo;

	/**
	 * 审核人
	 */
	private String auditUser;
	/**
	 * 操作人
	 */
	private String opUser;
	/**
	 * 操作时间
	 */
	private Date opTime;
}
