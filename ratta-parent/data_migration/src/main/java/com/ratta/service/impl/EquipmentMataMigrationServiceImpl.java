package com.ratta.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ratta.entity.equipment.EquipmentDO;
import com.ratta.entity.equipment.EquipmentLogDO;
import com.ratta.entity.equipment.UserEquipmentDO;
import com.ratta.service.EquipmentMataMigrationService;
import com.ratta.source.dao.SourceDao;
import com.ratta.target.equipment.dao.EquipmentTargetDao;

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
* Title:程序的奥秘
* Description:
* Company:  
* @author: 刘 明
* @date:Created in 2019年7月23日 下午2:00:43
* @version : 
*/
@Service
@Slf4j
//指定DataSource1Config里面的事务管理机制
@Transactional(transactionManager="test3TransactionManager",rollbackFor = Exception.class)
public class EquipmentMataMigrationServiceImpl implements EquipmentMataMigrationService {
	/**
	 * 源数据源
	 */
	@Autowired
	private SourceDao sourceDo;

	@Autowired
	private EquipmentTargetDao equipmentTargetDao;
	
	@Override
	public String dataMataMigration() {
		// 迁移设备表
		List<EquipmentDO> queryEquipmentList = sourceDo.queryEquipmentList();
		for (EquipmentDO equipmentDO : queryEquipmentList) {
			equipmentTargetDao.insertEquipmentList(equipmentDO);
		}
		log.info("迁移表    【t_equipment】  成功");
		// 迁移设备日志表
		List<EquipmentLogDO> queryEquipmentLogList = sourceDo.queryEquipmentLogList();
		for (EquipmentLogDO equipmentLogDO : queryEquipmentLogList) {
			equipmentTargetDao.insertEquipmentLogList(equipmentLogDO);
		}
		
		log.info("迁移表    【t_equipment_log】  成功");
		// 迁移用户设备表
		List<UserEquipmentDO> queryUserEquipmentList = sourceDo.queryUserEquipmentList();
		for (UserEquipmentDO userEquipmentDO : queryUserEquipmentList) {
			equipmentTargetDao.insertUserEquipmentList(userEquipmentDO);
		}
		
		log.info("迁移表    【t_user_equipment】  成功");
		return "success";
	}
}
