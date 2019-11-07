package com.ratta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.service.SystemMataMigrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;


/**
 * Title:程序的奥秘 Description: Company:
 * 
 * @author: 刘 明
 * @date:Created in 2019年7月22日 下午12:14:21
 * @version :
 */
@RestController
@Api(description = "系统基础库迁移")
@RequestMapping("/system/base")
public class SystemBaseController {

	@Autowired
	private SystemMataMigrationService MataMigrationService;

	@GetMapping("/implement")
	@ApiOperation(value = "系统基础库迁移数据执行接口")
	public String dataMataMigration() {
		return MataMigrationService.dataMataMigration();
	}
}
