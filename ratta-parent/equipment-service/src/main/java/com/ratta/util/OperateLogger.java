package com.ratta.util;

import java.lang.annotation.*;

/**
 * @author 刘明 操作日志注解
 */
@Target({ ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface OperateLogger {

	/**
	 * 描叙信息
	 * 
	 * @return
	 */
	String content() default "无描述信息";

	/**
	 * C:增加 R：查询 U：修改 D：删除 LOGIN_IN 登录, LOGIN_OU 登出 ,IMPORT 导入
	 * 
	 * @author TroyLiu
	 *
	 */
	enum OperationType {
		C, R, U, D, LOGIN_IN, LOGIN_OU, IMPORT, Batch_Operation
	}

	OperationType operationType() default OperationType.R;

	String table() default "";

}
