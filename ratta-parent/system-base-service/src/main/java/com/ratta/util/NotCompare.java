package com.ratta.util;

import java.lang.annotation.*;

/**
 * @author 刘明
 * 标记为不比较
 */
@Target({ElementType.PARAMETER,ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface NotCompare {

}
