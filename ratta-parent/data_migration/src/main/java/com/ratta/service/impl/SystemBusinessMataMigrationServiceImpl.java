package com.ratta.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.entity.equipment.UserEquipmentDO;
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
import com.ratta.entity.system.Business.InOutDO;
import com.ratta.entity.system.Business.ReturnRecordDO;
import com.ratta.entity.system.Business.StockDO;
import com.ratta.service.SystemBusinessMataMigrationService;
import com.ratta.source.dao.SourceDao;
import com.ratta.target.system.business.dao.SystemBusinessTargetDao;

import lombok.extern.slf4j.Slf4j;

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
 * Title:程序的奥秘 Description: Company:
 * 
 * @author: 刘 明
 * @date:Created in 2019年7月22日 下午2:19:58
 * @version :
 */
@Service
@Slf4j
public class SystemBusinessMataMigrationServiceImpl implements SystemBusinessMataMigrationService {
	/**
	 * 源数据源
	 */
	@Autowired
	private SourceDao sourceDo;
	/**
	 * 目标数据源
	 */
	@Autowired
	private SystemBusinessTargetDao systemBusinessTargetDao;

	@Override
	@Transactional(transactionManager="test5TransactionManager",rollbackFor = Exception.class)
	public String dataMataMigration() {
		// 迁移app修复点表
		List<AppFixPointDO> appFixPointDOList = sourceDo.queryAppFixPointList();
		for (AppFixPointDO appFixPointDO : appFixPointDOList) {
			AppVersionDO appVersionDO = sourceDo.queryVersionList(appFixPointDO.getAppId());
			appFixPointDO.setAppName(appVersionDO.getAppName());
			appFixPointDO.setAppVersion(appVersionDO.getAppVersion());
			systemBusinessTargetDao.insertAppFixPointList(appFixPointDO);
		}
		log.info("迁移app修复点表成功！");
		
		// 迁移app版本表
		List<AppVersionDO> appVersionDOList = sourceDo.queryAllVersionList();
		for (AppVersionDO appVersionDO : appVersionDOList) {
			systemBusinessTargetDao.insertAppVersionList(appVersionDO);
		}
		log.info("迁移app版本表成功！");
		
		// 迁移app灰度测试表
		List<AppGrayDeployDO> appGrayDeployDOList = sourceDo.queryAppGrayDeployList();
		for (AppGrayDeployDO appGrayDeployDO : appGrayDeployDOList) {
			systemBusinessTargetDao.insertAppGrayDeployList(appGrayDeployDO);
		}
		log.info("迁移app灰度测试表成功！");
		
		// 迁移经销商表
		List<DealerDO> dealerDOList = sourceDo.queryDealerList();
		for (DealerDO dealerDO : dealerDOList) {
			systemBusinessTargetDao.insertDealerList(dealerDO);
		}
		log.info("迁移经销商表成功！");
		
		// 迁移固件设备型号表
		List<FirmwareEquipDO> firmwareEquipDOList = sourceDo.queryFirmwareEquipList();
		for (FirmwareEquipDO firmwareEquipDO : firmwareEquipDOList) {
			systemBusinessTargetDao.insertFirmwareEquipList(firmwareEquipDO);
		}
		log.info("迁移固件设备型号表成功！");
		
		// 迁移固件修复点表
		List<FirmwareFixPointDO> firmwareFixPointDOList = sourceDo.queryFirmwareFixPointList();
		for (FirmwareFixPointDO firmwareFixPointDO : firmwareFixPointDOList) {
			systemBusinessTargetDao.insertFirmwareFixPointList(firmwareFixPointDO);
		}
		log.info("迁移固件修复点表成功！");
		
		// 迁移固件信息表
		List<FirmwareInfoDO> firmwareInfoDOList = sourceDo.queryFirmwareInfoList();
		for (FirmwareInfoDO firmwareInfoDO : firmwareInfoDOList) {
			systemBusinessTargetDao.insertFirmwareInfoList(firmwareInfoDO);
		}
		log.info("迁移固件信息表成功！");
		
		// 迁移固件信息子类表
		List<FirmwareInfoLineDO> firmwareInfoLineDOList = sourceDo.queryFirmwareInfoLineList();
		for (FirmwareInfoLineDO firmwareInfoLineDO : firmwareInfoLineDOList) {
			systemBusinessTargetDao.insertFirmwareInfoLineList(firmwareInfoLineDO);
		}
		log.info("迁移固件信息子类表成功！");
		
		// 迁移固件压缩表
		List<FirmwareZipDO> firmwareZipDOList = sourceDo.queryFirmwareZipList();
		for (FirmwareZipDO firmwareZipDO : firmwareZipDOList) {
			systemBusinessTargetDao.insertFirmwareZipList(firmwareZipDO);
		}
		log.info("迁移固件压缩表成功！");
		
		// 迁移固件任务表
		List<FirmwareTaskDO> firmwareTaskDOList = sourceDo.queryFirmwareTaskList("1");
		for (FirmwareTaskDO firmwareTaskDO : firmwareTaskDOList) {
			firmwareTaskDO.setIsHistory("1");
			systemBusinessTargetDao.insertFirmwareTaskList(firmwareTaskDO);
		}
		log.info("迁移固件任务表成功！");
		
		// 迁移库存表
		List<StockDO> stockDOList = sourceDo.queryStockList();
		for (StockDO stockDO : stockDOList) {
			InOutDO inOutDO = sourceDo.queryInOutList(stockDO.getInOutId());
			stockDO.setDealerId(inOutDO.getDealerId());
			stockDO.setEquipmentPurpose(inOutDO.getEquipmentPurpose());
			stockDO.setOpTime(inOutDO.getOpTime());
			stockDO.setOpUser(inOutDO.getOpUser());
			stockDO.setTaskId(0L);
			systemBusinessTargetDao.insertStockList(stockDO);
		}
		log.info("迁移库存表成功！");
		
		// 迁移反馈记录表
		List<FeedbackRecordDO> feedbackRecordDOList = sourceDo.queryFeedbackRecordList();
		for (FeedbackRecordDO feedbackRecordDO : feedbackRecordDOList) {
			List<String> list = new ArrayList<String>();
			List<UserEquipmentDO>  userEquipmentNUmberList= sourceDo.queryEquipmentNumberList(feedbackRecordDO.getUserId());
			for (UserEquipmentDO userEquipmentDO : userEquipmentNUmberList) {
				list.add(userEquipmentDO.getEquipmentNumber());
			}
			feedbackRecordDO.setEquipmentNumber(list.toString());
			systemBusinessTargetDao.insertFeedbackRecordList(feedbackRecordDO);
		}
		log.info("迁移反馈记录表成功！");
		
		// 迁移反馈类型表
		List<FeedbackTypeDO> feedbackTypeDOList = sourceDo.queryFeedbackTypeList();
		for (FeedbackTypeDO feedbackTypeDO : feedbackTypeDOList) {
			systemBusinessTargetDao.insertFeedbackTypeList(feedbackTypeDO);
		}
		log.info("迁移反馈类型表成功！");
		
		// 迁移退换货表
		List<ReturnRecordDO> returnRecordDOList = sourceDo.queryReturnRecordList();
		for (ReturnRecordDO returnRecordDO : returnRecordDOList) {
			systemBusinessTargetDao.insertReturnRecordList(returnRecordDO);
		}
		log.info("迁移退换货表成功！");
				
		return "success";
	}
}
