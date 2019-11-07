package com.ratta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.service.FileMataMigrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * 
 * @author: gxy
 * @date:Created in 2019年7月22日 下午12:14:21
 * @version :
 */
@RestController
@Api(description = "文件库迁移")
@RequestMapping("/file")
public class FileController {

	@Autowired
	private FileMataMigrationService fileMataMigrationService;

	@GetMapping("/implement")
	@ApiOperation(value = "文件库迁移数据执行接口")
	public String dataMataMigration() {
		return fileMataMigrationService.dataMataMigration();
	}
}
