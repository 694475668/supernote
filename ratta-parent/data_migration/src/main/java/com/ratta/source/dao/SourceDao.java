package com.ratta.source.dao;

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

import com.ratta.entity.equipment.EquipmentDO;
import com.ratta.entity.equipment.EquipmentLogDO;
import com.ratta.entity.equipment.EquipmentTypeDO;
import com.ratta.entity.equipment.UserEquipmentDO;
import com.ratta.entity.file.FileActionDO;
import com.ratta.entity.file.FileHisSyncDO;
import com.ratta.entity.file.RecycleFileDO;
import com.ratta.entity.file.UserFileDO;
import com.ratta.entity.system.Business.*;
import com.ratta.entity.system.DictionaryDO;
import com.ratta.entity.system.ReferenceDO;
import com.ratta.entity.system.ScheduleLogDO;
import com.ratta.entity.system.ScheduleTaskDO;
import com.ratta.entity.user.*;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Title:程序的奥秘 Description 源dao: Company:
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019年7月22日 下午2:24:56
 */
public interface SourceDao {
	/**
	 * 查询源库 数据字典记录
	 *
	 * @return
	 */
	@Select("SELECT id,name,value,value_cn valueCn,value_en valueEn,op_user opUser,op_time opTime,remark FROM t_dictionary")
	List<DictionaryDO> queryDictionaryList();

	/**
	 * 查询源库 参数
	 *
	 * @return
	 */
	@Select("SELECT ID,SERIAL,NAME,VALUE,VALUE_CN valueCn,OP_USER opUser,OP_TIME opTime,REMARK FROM t_m_reference")
	List<ReferenceDO> queryReferenceList();

	/**
	 * 查询源库 调度任务
	 *
	 * @return
	 */
	@Select("SELECT ID,NAME,REMARK,CRON,STATUS,CREATED createTime,CREATEDBY createUser,UPDATED updateTime,UPDATEDBY updateUser,BZCODE bzCode FROM t_m_schedule_task")
	List<ScheduleTaskDO> queryScheduleTaskList();

	/**
	 * 查询源库 调度日志记录
	 *
	 * @return
	 */
	@Select("SELECT ID,KSRQ,JSRQ,TASK_ID taskId,RESULT FROM t_m_schedule_log")
	List<ScheduleLogDO> queryScheduleLogList();

	/**
	 * 查询源库 设备记录
	 *
	 * @return
	 */
	@Select("SELECT ID,EQUIPMENT_NO equipmentNumber ,FIRMWARE_VERSION firmwareVersion,UPDATE_STATUS updateStatus,REMARK remark,OP_TIME createTime,OP_TIME updateTime FROM t_m_stock WHERE STATE='Y'")
	List<EquipmentDO> queryEquipmentList();

	/**
	 * 查询源库 设备日志记录
	 *
	 * @return
	 */
	@Select("SELECT id,equipment_no equipmentNumber,log_name logName,type,firmware_version firmwareVersion,create_time createTime,remark,is_download isDownload,flag from t_equipment_log")
	List<EquipmentLogDO> queryEquipmentLogList();

	/**
	 * 查询源库 用户设备记录
	 *
	 * @return
	 */
	@Select("SELECT ID,EQUIPMENT_NUMBER equipmentNumber,USER_ID userId,NAME,STATUS,CREATE_TIME createTime FROM t_user_equipment")
	List<UserEquipmentDO> queryUserEquipmentList();

	/**
	 * 查询源库 登录操作表
	 *
	 * @return
	 */
	@Select("SELECT id,user_id userId,country_code countryCode,area_code areaCode,count,create_time createTime,last_update_time updateTime FROM t_commonly_area")
	List<CommonlyAreaDO> queryCommonlyAreaList();

	/**
	 * 查询源库 登录设备操作表
	 *
	 * @return
	 */
	@Select("SELECT id,user_id userId,equipment_no equipmentNumber,create_time createTime,last_update_time updateTime  FROM t_commonly_equipment")
	List<CommonlyEquipmentDO> queryCommonlyEquipmentList();

	/**
	 * 查询源库
	 *
	 * @return
	 */
	@Select("SELECT ID,USER_ID userId,LOGIN_METHOD loginMethod,IP,BROWSER,EQUIPMENT,CREATE_TIME createTime,LAST_UPDATE_TIME updateTime FROM t_login_record")
	List<LoginRecordDO> queryLoginRecordList();

	/**
	 * @return
	 */
	@Select("SELECT ID,USER_ID userId,OPERATE_RECORD operateRecord,IP,CREATE_TIME createTime,LAST_UPDATE_TIME updateTime FROM t_sensitive_record")
	List<SensitiveRecordDO> querySensitiveRecordList();

	/**
	 * 查询源库 用户操作记录
	 *
	 * @return
	 */
	@Select("SELECT USER_ID userId,USER_NAME userName,COUNTRY_CODE countryCode,TELEPHONE,EMAIL,WECHAT_NO wechatNo,SEX,BIRTHDAY ,PERSONALSIGN personalSign,HOBBY,EDUCATION,JOB,AVATARS_URL avatarsUrl,address,PASSWORD,CREATE_TIME createTime,LAST_UPDATE_TIME updateTime,ISNORMAL isNormal FROM t_user")
	List<UserDO> queryUserList();

	/**
	 * 查询源库 app修复点
	 * 
	 * @return
	 */
	@Select("select t.id,t.app_id appId,t.fix_point fixPoint,t.lan,t.op_user opUser,t.op_time opTime from t_app_fixpoint t")
	List<AppFixPointDO> queryAppFixPointList();

	/**
	 * 查询源库 根据条件查询app版本
	 * 
	 * @return
	 */
	@Select("select t.app_name appName,t.app_version appVersion from t_app_version t where t.id = #{id}")
	AppVersionDO queryVersionList(Long id);

	/**
	 * 查询源库 app版本
	 * 
	 * @return
	 */
	@Select("select t.id,t.file_name fileName,t.app_name appName,t.app_version appVersion,t.package_name packageName,t.url,t.update_flag updateFlag,"
			+ "t.op_user opUser,t.op_time opTime,"
			+ "t.md5,t.version_no versionNo,t.deploy_flag deployFlag,t.auditing_flag auditingFlag,t.app_environment appEnvironment from t_app_version t")
	List<AppVersionDO> queryAllVersionList();

	/**
	 * 查询源库 app灰度测试表
	 * 
	 * @return
	 */
	@Select("select t.id,t.app_id appId,t.user_id userId,t.is_history isHistory,t.create_time createTime,t.update_time updateTime from t_app_gray_deploy t")
	List<AppGrayDeployDO> queryAppGrayDeployList();

	/**
	 * 查询源库 经销商表
	 * 
	 * @return
	 */
	@Select("select t.id,t.dealers_name dealersName,t.dealers_type dealersType,t.contact,t.phone,t.address,t.application_time applicationTime,t.salesman,t.remark from t_m_dealers t")
	List<DealerDO> queryDealerList();

	/**
	 * 查询源库 固件设备型号表
	 * 
	 * @return
	 */
	@Select("select t.id,t.equipment_model equipmentModel,t.upload_time uploadTime,t.firmware_version firmwareVersion from t_m_firmware_euip t")
	List<FirmwareEquipDO> queryFirmwareEquipList();

	/**
	 * 查询源库 固件修复点表
	 * 
	 * @return
	 */
	@Select("select t.ID,t.LAN,t.FIX_POINT fixPoint,t.FIRMWARE_VERSION firmwareVersion,t.OP_USER opUser,t.OP_TIME opTime from t_firmware_fixpoint t")
	List<FirmwareFixPointDO> queryFirmwareFixPointList();

	/**
	 * 查询源库 固件信息表
	 * 
	 * @return
	 */
	@Select("select t.ID,t.AREA,t.CUSTOM,t.CONFIGURATION,t.VERSION firmwareVersion,t.STATUS,t.MODIFY_POINT modifyPoint,t.IMPACT_SCOPE impactScope,t.VERSION_PURPOSE versionPurpose,"
			+ "t.UPGRAGE_SCOPE upgrageScope,t.PACKAGING_TIME packagingTime,"
			+ "t.AUDIT_FLAG auditFlag,t.AUDIT_TIME auditTime,t.AUDIT_INFO auditInfo,t.AUDIT_USER auditUser,t.OP_USER opUser,t.OP_TIME opTime from t_fireware_info t")
	List<FirmwareInfoDO> queryFirmwareInfoList();

	/**
	 * 查询源库 固件信息子类表
	 * 
	 * @return
	 */
	@Select("select t.ID,t.TYPE,t.VERSION,t.MODIFY_POINT modifyPoint,t.IMPACT_SCOPE impactScope,t.VERSION_PURPOSE versionPurpose,"
			+ "t.UPGRAGE_SCOPE upgrageScope,t.FILE_URL fileUrl,t.MERGE_FLAG mergeFlag,"
			+ "t.FIRM_ID firmId,t.PACKAGING_TIME packagingTime from t_fireware_info_line t")
	List<FirmwareInfoLineDO> queryFirmwareInfoLineList();

	/**
	 * 查询源库 固件压缩表
	 * 
	 * @return
	 */
	@Select("select t.ID,t.VERSION firmwareVersion,t.APP_FLAG appFlag,t.SYSTEM_FLAG systemFlag,t.LIB_APP_FLAG libAppFlag,"
			+ "t.LIB_SYSTEM_FLAG libSystemFlag,t.URL,t.MD5,t.SECRET,t.OP_USER opUser,t.OP_TIME opTime from t_firmware_zip t")
	List<FirmwareZipDO> queryFirmwareZipList();

	/**
	 * 查询源库 退换货表
	 * 
	 * @return
	 */
	@Select("select t.ID,t.EQUIPMENT_NO equipmentNumber,t.TYPE,t.EXCHANGE_EQUIPMENTNO exchangeEquipmentNumber,t.SERIAL_NUMBER serialNumber,"
			+ "t.RETURN_TIME returnTime,t.BUSINESS_HANDLER businessHandler,t.OP_USER opUser,t.OP_TIME opTime,"
			+ "t.HEALTHY_STATE healthyState,t.REMARK from t_m_return_record t")
	List<ReturnRecordDO> queryReturnRecordList();

	/**
	 * 查询源库 固件任务表
	 * 
	 * @return
	 */
	@Select("select t.ID,t.FIRMWARE_VERSION firmwareVersion,t.TYPE,t.EQUIPMENT_NO equipmentNumber,t.EQUIPMENT_PURPOSE equipmentPurpose,t.EQUIPMENT_MODEL equipmentModel,"
			+ "t.IS_HISTORY isHistory,t.STATUS,t.REVOKE_USER revokeUser,t.REVOKE_TIME revokeTime,t.DEPLOY_USER createUser,"
			+ "t.DEPLOY_TIME createTime from t_m_firmware_task t where t.type != #{type}")
	List<FirmwareTaskDO> queryFirmwareTaskList(String type);

	/**
	 * 查询源库 库存表
	 * 
	 * @return
	 */
	@Select("select t.ID,t.EQUIPMENT_NO equipmentNumber,t.EQUIPMENT_MODEL equipmentModel,t.FIRMWARE_VERSION firmwareVersion,t.FLAG,t.STATE,"
			+ "t.BATCH_NO batchNo,t.HEALTHY_STATE healthyState,t.REMARK,t.INOUT_ID inOutId from t_m_stock t")
	List<StockDO> queryStockList();

	/**
	 * 查询源库 出入库表
	 * 
	 * @return
	 */
	@Select("select t.EQUIPMENT_PURPOSE equipmentPurpose,t.DEALERS_ID dealerId,t.OP_USER opUser,t.OP_TIME opTime from t_m_inout t where t.id = #{id}")
	InOutDO queryInOutList(Long id);

	/**
	 * 查询源库 反馈记录表
	 * 
	 * @return
	 */
	@Select("select t.id,t.user_id userId,t.type_id typeId,t.description,t.contact,t.create_time createTime from t_feedback_record t")
	List<FeedbackRecordDO> queryFeedbackRecordList();

	/**
	 * 查询源库 根据条件查询用户设备表
	 * 
	 * @return
	 */
	@Select("SELECT ID,EQUIPMENT_NUMBER equipmentNumber,USER_ID userId,NAME,STATUS,CREATE_TIME createTime FROM t_user_equipment where USER_ID = #{userId}")
	List<UserEquipmentDO> queryEquipmentNumberList(Long userId);

	/**
	 * 查询源库 反馈类型表
	 * 
	 * @return
	 */
	@Select("select t.type_id typeId,t.value_en valueEn,t.value_ja valueJa,t.status,t.value_cn valueCn,t.op_user opUser,t.op_time opTime from t_feedback_type t")
	List<FeedbackTypeDO> queryFeedbackTypeList();

	/**
	 * 查询源库 文件表
	 * 
	 * @return
	 */
	@Select("select id, user_id userId, directory_id directoryId,file_name fileName,inner_name innerName,size,isactive,md5,isfolder,create_time createTime,last_update_time lastUpdateTime,modify_time modifyTime from t_user_file where USER_ID = #{userId}")
	List<UserFileDO> queryUserFileList(Long userId);

	/**
	 * 查询源库 回收站表
	 * 
	 * @return
	 */
	@Select("select file_id fileId, user_id userId,file_name fileName,size,isfolder,last_update_time lastUpdateTime from t_recycle_file where USER_ID = #{userId}")
	List<RecycleFileDO> queryRecycleFileList(Long userId);

	/**
	 * 查询源库 回收站表
	 * 
	 * @return
	 */
	@Select("select id id,file_id fileId, user_id userId,name,new_name newName,path,new_path newPath,md5,url,size,isfolder,action,create_time createTime,last_update_time lastUpdateTime from t_file_action where USER_ID = #{userId}")
	List<FileActionDO> queryFileActionList(Long userId);

	/**
	 * 查询源库 历史表
	 * 
	 * @return
	 */
	@Select("select id id, user_id userId,equipment_no equipmentNo,directory_id directoryId,file_name fileName,inner_name innerName,size,md5,isfolder,create_time createTime,last_update_time lastUpdateTime,sync_time syncTime,modify_time modifyTime from t_file_his_sync where USER_ID = #{userId}")
	List<FileHisSyncDO> queryFileHisList(Long userId);

	/**
	 * 查询已用容量
	 * 
	 * @return
	 */
	@Select("select sum(size) from t_user_file where USER_ID = #{userId}")
	Long queryUsedCapacity(Long userId);

	/**
	 * 查询总容量
	 * 
	 * @return
	 */
	@Select("select total_capacity from t_user where USER_ID = #{userId}")
	Long queryTotalCapacity(Long userId);
	
	/**
	 * 查询设备型号
	 * 
	 * @return
	 */
	@Select("select ID,TYPE,OP_USER opUser,OP_TIME opTime from t_m_equiptype")
	List<EquipmentTypeDO> queryEquipmentType();
}
