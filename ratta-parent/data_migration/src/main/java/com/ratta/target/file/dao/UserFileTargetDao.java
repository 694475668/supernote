package com.ratta.target.file.dao;

import org.apache.ibatis.annotations.Insert;

import com.ratta.entity.file.CapacityDO;
import com.ratta.entity.file.FileActionDO;
import com.ratta.entity.file.FileHisSyncDO;
import com.ratta.entity.file.RecycleFileDO;
import com.ratta.entity.file.UserFileDO;

/**
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019年7月23日 上午9:28:41
 */
public interface UserFileTargetDao {
	/**
	 * 添加源库设备记录到新库
	 *
	 * @param UserFileDO
	 * @return
	 */
	@Insert("INSERT INTO t_user_file_${mod} values (#{id},#{userId},#{directoryId},#{fileName},#{innerName},#{size},#{md5},#{isactive},#{isfolder},#{createTime},#{lastUpdateTime},#{modifyTime})")
	int insertUserFile(UserFileDO userFileDO);

	/**
	 * 添加源库设备记录到新库
	 *
	 * @param RecycleFileDO
	 * @return
	 */
	@Insert("INSERT INTO t_recycle_file_${mod} values (#{fileId},#{userId},#{fileName},#{size},#{isfolder},#{createTime},#{lastUpdateTime})")
	int insertRecycleFile(RecycleFileDO recycleFileDO);

	/**
	 * 添加源库设备记录到新库
	 *
	 * @param FileActionDO
	 * @return
	 */
	@Insert("INSERT INTO t_file_action_${mod} values (#{id},#{userId},#{fileId},#{name},#{newName},#{path},#{newPath},#{md5},#{url},#{isfolder},#{size},#{action},#{createTime},#{lastUpdateTime})")
	int insertFileAction(FileActionDO fileActionDO);

	/**
	 * 
	 * @param userFileDO
	 * @return
	 */
	@Insert("INSERT INTO t_file_his_sync_${mod} values (#{id},#{fileId},#{userId},#{equipmentNo},#{directoryId},#{fileName},#{innerName},#{size},#{md5},#{isfolder},#{createTime},#{syncTime},#{modifyTime})")
	int insertFileHisSync(FileHisSyncDO fileHisSyncDO);

	/**
	 * 
	 * @param capacityDO
	 * @return
	 */
	@Insert("INSERT INTO t_capacity values (#{id},#{userId},#{usedCapacity},#{totalCapacity},#{createTime},#{updateTime})")
	int insertCapacity(CapacityDO capacityDO);
}
