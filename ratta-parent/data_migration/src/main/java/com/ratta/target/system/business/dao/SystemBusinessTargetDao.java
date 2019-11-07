package com.ratta.target.system.business.dao;


import org.apache.ibatis.annotations.Insert;

import com.ratta.entity.system.Business.AppFixPointDO;
import com.ratta.entity.system.Business.AppGrayDeployDO;
import com.ratta.entity.system.Business.AppVersionDO;
import com.ratta.entity.system.Business.DealerDO;
import com.ratta.entity.system.Business.FeedbackRecordDO;
import com.ratta.entity.system.Business.FeedbackTypeDO;
import com.ratta.entity.system.Business.FirmwareEquipDO;
import com.ratta.entity.system.Business.FirmwareFixPointDO;
import com.ratta.entity.system.Business.FirmwareInfoDO;
import com.ratta.entity.system.Business.FirmwareInfoLineDO;
import com.ratta.entity.system.Business.FirmwareTaskDO;
import com.ratta.entity.system.Business.FirmwareZipDO;
import com.ratta.entity.system.Business.ReturnRecordDO;
import com.ratta.entity.system.Business.StockDO;

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
/**
 * Title:程序的奥秘 Description 目标dao: Company:
 * 
 * @author: 刘 明
 * @date:Created in 2019年7月22日 下午2:24:56
 * @version :
 */
public interface SystemBusinessTargetDao {
	/**
	 * 添加app修复点到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_app_fixpoint VALUES(#{id},#{appName},#{lan},#{fixPoint},#{opUser},#{opTime},#{appVersion})")
	int insertAppFixPointList(AppFixPointDO appFixPointDO);
	
	/**
	 * 添加app版本到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_app_version VALUES"
			+ "(#{id},#{fileName},#{appName},#{appVersion},#{packageName},#{url},#{updateFlag},#{opUser},#{opTime},#{md5},#{versionNo},#{deployFlag},"
			+ "#{auditingFlag},#{appEnvironment})")
	int insertAppVersionList(AppVersionDO appVersionDO);
	
	/**
	 * 添加app灰度测试到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_app_gray_deploy VALUES(#{id},#{appId},#{userId},#{isHistory},#{createTime},#{updateTime})")
	int insertAppGrayDeployList(AppGrayDeployDO appGrayDeployDO);
	
	/**
	 * 添加app灰度测试到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_dealer VALUES(#{id},#{dealersName},#{dealersType},#{contact},#{phone},#{address},#{applicationTime},#{salesman},#{remark})")
	int insertDealerList(DealerDO dealerDO);
	
	/**
	 * 添加固件设备型号到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_firmware_euip VALUES(#{id},#{equipmentModel},#{uploadTime},#{firmwareVersion})")
	int insertFirmwareEquipList(FirmwareEquipDO firmwareEquipDO);
	
	/**
	 * 添加固件修复点到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_firmware_fixpoint VALUES(#{id},#{lan},#{fixPoint},#{firmwareVersion},#{opUser},#{opTime})")
	int insertFirmwareFixPointList(FirmwareFixPointDO firmwareFixPointDO);
	
	/**
	 * 添加固件信息到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_firmware_info VALUES(#{id},#{area},#{custom},#{configuration},#{firmwareVersion},#{status},#{modifyPoint},#{impactScope},#{versionPurpose},#{upgrageScope},#{packagingTime}"
			+ ",'1',#{auditFlag},#{auditTime},#{auditInfo},#{auditUser},#{opUser},#{opTime})")
	int insertFirmwareInfoList(FirmwareInfoDO firmwareInfoDO);
	
	/**
	 * 添加固件信息子类到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_firmware_info_line VALUES(#{id},#{type},#{version},#{modifyPoint},#{impactScope},#{versionPurpose},#{upgrageScope},#{fileUrl},#{mergeFlag},#{firmId},#{packagingTime})")
	int insertFirmwareInfoLineList(FirmwareInfoLineDO firmwareInfoLineDO);
	
	/**
	 * 添加固件压缩到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_firmware_zip VALUES(#{id},#{firmwareVersion},#{appFlag},#{systemFlag},#{libAppFlag},#{libSystemFlag},#{url},#{md5},#{secret},#{opUser},#{opTime})")
	int insertFirmwareZipList(FirmwareZipDO firmwareZipDO);
	
	/**
	 * 添加退换货到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_return_record VALUES(#{id},#{equipmentNumber},#{type},#{exchangeEquipmentNumber},#{serialNumber},#{returnTime},#{businessHandler},#{opUser},#{opTime},#{healthyState},#{remark})")
	int insertReturnRecordList(ReturnRecordDO returnRecordDO);
	
	/**
	 * 添加固件任务到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_firmware_task VALUES(#{id},#{firmwareVersion},#{type},#{equipmentNumber},#{equipmentPurpose},#{equipmentModel},#{isHistory},#{status},'1',"
			+ "#{revokeUser},#{revokeTime},#{createUser},#{createTime})")
	int insertFirmwareTaskList(FirmwareTaskDO firmwareTaskDO);
	
	/**
	 * 添加库存到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_stock VALUES(#{id},#{equipmentModel},#{equipmentNumber},#{equipmentPurpose},#{firmwareVersion},#{dealerId},#{flag},#{state},"
			+ "#{batchNo},#{taskId},#{healthyState},#{remark},#{opTime},#{opUser})")
	int insertStockList(StockDO stockDO);
	
	/**
	 * 添加反馈记录到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_feedback_record VALUES(#{id},#{userId},#{typeId},#{description},#{contact},#{createTime},#{equipmentNumber})")
	int insertFeedbackRecordList(FeedbackRecordDO feedbackRecordDO);
	
	/**
	 * 添加反馈类型到新库
	 * 
	 * @return
	 */
	@Insert("INSERT INTO t_feedback_type VALUES(#{typeId},#{valueCn},#{valueEn},#{valueJa},#{status},#{opUser},#{opTime})")
	int insertFeedbackTypeList(FeedbackTypeDO feedbackTypeDO);
}
