package com.ratta.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ratta.service.OssService;
import com.ratta.service.PictureUploadService;
import com.ratta.util.GenerateUtil;
import com.ratta.util.UfileUtil;
import com.ratta.vo.CommonVO;
import com.ratta.vo.OssUploadVO;
import com.ratta.vo.PictureUploadVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(description = "图片上传接口")
@RestController
public class PictureUploadController {

	@Value("${bucketName}")
	private String bucketName;

	@Value("${filePath}")
	private String filePath;

	@Autowired
	private PictureUploadService pictureUploadService;

	@Autowired
	OssService ossService;

	@ApiOperation(value = "图片上传接口")
	@PostMapping(value = "/picture/upload")
	public CommonVO<PictureUploadVO> addUploadRecord(@RequestParam("file") MultipartFile file,
			HttpServletRequest request) {
		String userId = request.getHeader("User-ID");
		String innerName = GenerateUtil.getInnerName(file.getOriginalFilename());
		OssUploadVO ossUploadVO = ossService.getUploadInfo();
		File dest = new File(filePath + innerName);
		try {
			file.transferTo(dest);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		UfileUtil.upload(dest, ossUploadVO.getPublicKey(), ossUploadVO.getPrivateKey(), ossUploadVO.getRegion(),
				ossUploadVO.getProxySuffix(), innerName, bucketName);
		dest.delete();
		return pictureUploadService.addRecord(innerName, userId);
	}
}
