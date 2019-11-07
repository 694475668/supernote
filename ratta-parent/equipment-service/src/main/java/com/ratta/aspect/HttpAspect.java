package com.ratta.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 * @author gxy
 *
 */
@Aspect
@Component
@Slf4j
public class HttpAspect {

	@Pointcut("execution(public * com.ratta.controller.*.*(..))")
	public void log() {
	}

	@Before("log()")
	public void doBefore(JoinPoint joinPoint) {

		// 记录http请求
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		HttpServletRequest request = attributes.getRequest();
		String userId = request.getHeader("User-ID");
		Object[] args = joinPoint.getArgs();
		log.info("userid:{} , url:{}" ,userId ,request.getRequestURI());
		log.info("请求报文:{}", args);
	}

	@AfterReturning(pointcut = "log()", returning = "object")
	public void doAfterReturing(Object object) {
		if (object != null) {
			log.info("返回报文：{}", object.toString());
		} else {
			log.info("返回报文：{}", "");
		}
	}
}
