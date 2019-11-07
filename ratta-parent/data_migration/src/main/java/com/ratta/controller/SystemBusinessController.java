package com.ratta.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ratta.service.SystemBusinessMataMigrationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

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
 * Title:程序的奥秘 Description: Company:
 * 
 * @author: 刘 明
 * @date:Created in 2019年7月22日 下午12:14:21
 * @version :
 */
@RestController
@Api(description = "系统业务库迁移")
@RequestMapping("/system/business")
public class SystemBusinessController {

	@Autowired
	private SystemBusinessMataMigrationService systemBusinessMataMigrationService;

	@GetMapping("/implement")
	@ApiOperation(value = "系统业务迁移数据执行接口")
	public String dataMataMigration() {
		return systemBusinessMataMigrationService.dataMataMigration();
	}
}
