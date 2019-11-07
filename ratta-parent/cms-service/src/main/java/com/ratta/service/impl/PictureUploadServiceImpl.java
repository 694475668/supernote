package com.ratta.service.impl;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ratta.domain.UploadRecordDO;
import com.ratta.dto.OssDownloadUrlDTO;
import com.ratta.mapper.UploadRecordMapper;
import com.ratta.service.OssService;
import com.ratta.service.PictureUploadService;
import com.ratta.util.SystemBaseUtil;
import com.ratta.vo.CommonVO;
import com.ratta.vo.OssUrlVO;
import com.ratta.vo.PictureUploadVO;
import com.ratta.vo.UserBeanVO;

@Service
public class PictureUploadServiceImpl implements PictureUploadService {

	@Value("${bucketName}")
	private String bucketName;

	@Autowired
	private SystemBaseUtil systemBaseUtil;

	@Autowired
	private UploadRecordMapper uploadRecordMapper;

	@Autowired
	private OssService ossService;

	@Override
	public CommonVO<PictureUploadVO> addRecord(String innerName, String userId) {
		CommonVO<PictureUploadVO> commonVO = new CommonVO<PictureUploadVO>();
		UserBeanVO userBeanVO = systemBaseUtil.querySystemUser(Long.parseLong(userId));
		UploadRecordDO uploadRecordDO = new UploadRecordDO();
		uploadRecordDO.setCreateUser(userBeanVO.getUsername());
		uploadRecordDO.setInnerName(innerName);
		uploadRecordDO.setCreateTime(new Date());
		uploadRecordMapper.insert(uploadRecordDO);
		OssUrlVO ossUrlVO = ossService.getFileDownloadUrl(new OssDownloadUrlDTO(bucketName, innerName, true, 0));
		PictureUploadVO pictureUploadVO = new PictureUploadVO();
		pictureUploadVO.setDownloadUrl(ossUrlVO.getUrl());
		commonVO.setVoT(pictureUploadVO);
		return commonVO;
	}

}
