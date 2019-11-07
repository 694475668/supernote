package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.FirmwareManualDO;

/**
 * @author page
 */
@Mapper
public interface FirmwareManualMapper {
	/**
	 * 
	 * <p>query</p>
	 * <p>查询固件说明书</p>
	 * @param firmwareManualDO 查询参数
	 * @return 固件说明书列表
	 */
	List<FirmwareManualDO> query(FirmwareManualDO firmwareManualDO);
	
	
	/**
	 * 
	 * <p>save</p>
	 * <p>添加固件说明书信息</p>
	 * @param firmwareManualDO 固件说明书信息
	 * @return 数据库影响行数
	 */
	int save(FirmwareManualDO firmwareManualDO);
	
	/**
	 * 
	 * <p>update</p>
	 * <p>更新固件说明书信息</p>
	 * @param firmwareManualDO 固件说明书
	 * @return 数据库影响行数
	 */
	int update(FirmwareManualDO firmwareManualDO);
	
	/**
	 * 
	 * <p>delete</p>
	 * <p>删除固件说明书信息</p>
	 * @param id 固件说明书id
	 * @return 数据库影响行数
	 */
	int delete(@Param("id") int id);
	
	/**
	 * 
	 * <p>query</p>
	 * <p>根据id查询固件说明书信息</p>
	 * @param id id
	 * @return 固件说明书信息
	 */
	FirmwareManualDO queryById(@Param("id") int id);
	
	
	/**
	 * 通过条件查询固件说明书
	 * @param type
	 * @param seq
	 * @param line
	 * @return
	 */
	FirmwareManualDO queryBySeqAndLine(@Param("type") String type, @Param("seq") Integer seq,
			@Param("line") String line);
	/**
	 * 
	 * <p>queryByParam</p>
	 * <p>通过条件查询固件说明书</p>
	 * @param type 类型
	 * @param seq 序号
	 * @return 固件说明书信息
	 */
	FirmwareManualDO queryByParam(String type,int seq);
	
	/**
	 * 
	 * <p>updateLine</p>
	 * <p>修改固件说明书上线下线</p>
	 * @param firmwareManualDO 固件说明书
	 * @return 数据库影响行数
	 */
	int updateLine(FirmwareManualDO firmwareManualDO);
	
	/**
	 * 
	 * <p>updateSeq</p>
	 * <p>修改固件说明书序号</p>
	 * @param firmwareManualDO 固件说明书
	 * @return 数据库影响行数
	 */
	int updateSeq(FirmwareManualDO firmwareManualDO);
	
	/**
	 * 
	 * <p>updateAllSeq</p>
	 * <p>修改固件说明书序号</p>
	 * @param oldSeq 原有序号
	 * @param newSeq 新序号
	 * @param type 类型
	 * @return 数据库影响行数
	 */
	int updateAllSeq(int oldSeq, int newSeq, String type, String line);
	
	/**
	 * 
	 * <p>queryByTypeAndLan</p>
	 * <p>根据语言和类型查询固件说明书列表信息</p>
	 * @param type 类型
	 * @return 固件说明书列表
	 */
	List<FirmwareManualDO> queryByTypeAndLan(@Param("type") String type,@Param("line") String line);
	
	/**
	 * 
	 * <p>queryMaxSeq</p>
	 * <p>查询最大的排序</p>
	 * @param type 类型
	 * @return 最大序号
	 */
	int queryMaxSeq(String type);
	
}
