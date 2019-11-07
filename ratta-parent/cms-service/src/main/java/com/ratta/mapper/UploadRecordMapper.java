package com.ratta.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.ratta.domain.UploadRecordDO;

@Mapper
public interface UploadRecordMapper {

	/**
	 * 新增上传记录
	 * 
	 * @param uploadRecordDO
	 * @return
	 */
	int insert(UploadRecordDO uploadRecordDO);

	/**
	 * 查询上传记录列表
	 * 
	 * @return
	 */
	List<UploadRecordDO> query();

	/**
	 * 删除
	 * 
	 * @param innerName
	 * @return
	 */
	int delete(@Param("innerName") String innerName);
}
