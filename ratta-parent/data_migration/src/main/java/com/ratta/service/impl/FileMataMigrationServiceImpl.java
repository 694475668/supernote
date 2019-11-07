package com.ratta.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.ratta.entity.file.CapacityDO;
import com.ratta.entity.file.FileActionDO;
import com.ratta.entity.file.FileHisSyncDO;
import com.ratta.entity.file.RecycleFileDO;
import com.ratta.entity.file.UserFileDO;
import com.ratta.entity.user.UserDO;
import com.ratta.service.FileMataMigrationService;
import com.ratta.source.dao.SourceDao;
import com.ratta.target.file.dao.UserFileTargetDao;

/**
 * @author: 刘 明
 * @date:Created in 2019年7月23日 下午2:00:43
 * @version :
 */
@Service
//指定DataSource1Config里面的事务管理机制
@Transactional(transactionManager = "test6TransactionManager", rollbackFor = Exception.class)
public class FileMataMigrationServiceImpl implements FileMataMigrationService {
	/**
	 * 源数据源
	 */
	@Autowired
	private SourceDao sourceDo;

	@Autowired
	private UserFileTargetDao userFileTargetDao;

	@Override
	public String dataMataMigration() {

		// 查询出所有的用户
		List<UserDO> userList = sourceDo.queryUserList();

		for (UserDO userDO : userList) {
			Long mod = userDO.getUserId() % 15;

			// 文件表
			List<UserFileDO> userFileDOList = sourceDo.queryUserFileList(userDO.getUserId());
			for (UserFileDO userFileDO : userFileDOList) {
				userFileDO.setMod(mod);
				if (StringUtils.isEmpty(userFileDO.getInnerName())) {
					userFileDO.setInnerName("");
				}
				if (StringUtils.isEmpty(userFileDO.getMd5())) {
					userFileDO.setMd5("");
				}
				userFileTargetDao.insertUserFile(userFileDO);
			}

			// 回收站表
			List<RecycleFileDO> recycleFileDOList = sourceDo.queryRecycleFileList(userDO.getUserId());
			for (RecycleFileDO recycleFileDO : recycleFileDOList) {
				recycleFileDO.setMod(mod);
				recycleFileDO.setCreateTime(recycleFileDO.getLastUpdateTime());
				userFileTargetDao.insertRecycleFile(recycleFileDO);
			}

			// 文件操作表
			List<FileActionDO> fileActionDOList = sourceDo.queryFileActionList(userDO.getUserId());
			for (FileActionDO fileActionDO : fileActionDOList) {
				fileActionDO.setMod(mod);
				if (StringUtils.isEmpty(fileActionDO.getNewName())) {
					fileActionDO.setNewName("");
				}
				if (StringUtils.isEmpty(fileActionDO.getNewPath())) {
					fileActionDO.setNewPath("");
				}
				if (StringUtils.isEmpty(fileActionDO.getMd5())) {
					fileActionDO.setMd5("");
				}
				if (StringUtils.isEmpty(fileActionDO.getUrl())) {
					fileActionDO.setUrl("");
				}
				userFileTargetDao.insertFileAction(fileActionDO);
			}

			// 文件历史表
			List<FileHisSyncDO> fileHisSyncDODOList = sourceDo.queryFileHisList(userDO.getUserId());
			for (FileHisSyncDO fileHisSyncDO : fileHisSyncDODOList) {
				fileHisSyncDO.setMod(mod);
				if (StringUtils.isEmpty(fileHisSyncDO.getInnerName())) {
					fileHisSyncDO.setInnerName("");
				}
				if (StringUtils.isEmpty(fileHisSyncDO.getMd5())) {
					fileHisSyncDO.setMd5("");
				}
				fileHisSyncDO.setFileId(fileHisSyncDO.getId());
				userFileTargetDao.insertFileHisSync(fileHisSyncDO);
			}

			// 容量表
			Long usedCapacity = sourceDo.queryUsedCapacity(userDO.getUserId());
			Long totalCapacity = sourceDo.queryTotalCapacity(userDO.getUserId());
			CapacityDO CapacityDO = new CapacityDO();
			CapacityDO.setUserId(userDO.getUserId());
			if (usedCapacity == null) {
				CapacityDO.setUsedCapacity(0L);
			} else {
				CapacityDO.setUsedCapacity(usedCapacity);
			}
			CapacityDO.setTotalCapacity(totalCapacity);
			CapacityDO.setCreateTime(new Date());
			CapacityDO.setUpdateTime(new Date());
			userFileTargetDao.insertCapacity(CapacityDO);

		}

		return "success";
	}
}
