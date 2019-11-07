package com.ratta.util;

import java.lang.annotation.*;

/**
 * @author 刘明
 * 标记为实体
 */
@Target({ElementType.TYPE,ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Model {
	String table() default "" ;
}
