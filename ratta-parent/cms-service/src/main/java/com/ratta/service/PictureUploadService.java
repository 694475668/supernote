package com.ratta.service;

import com.ratta.vo.CommonVO;
import com.ratta.vo.PictureUploadVO;

/**
 * @author gxy
 *
 */
public interface PictureUploadService {

	/**
	 * 新增文件上传记录
	 * 
	 * @param aboutDTO
	 * @param userId
	 * @return
	 */
	CommonVO<PictureUploadVO> addRecord(String innerName,String userId);
}
