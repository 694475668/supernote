package com.ratta.util;

import java.lang.annotation.*;

/**
 * @author 刘明
 * 标记为主键(唯一标识符)
 */
@Target({ElementType.PARAMETER, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PK {

}
