package com.ratta.logback;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;

/**
 * @author yll
 *
 */
public class UserIdLogConfig extends ClassicConverter  {

	@Override
	public String convert(ILoggingEvent event) {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
		if(attributes == null) {
			return "";
		}
		HttpServletRequest request = attributes.getRequest();
		String userId = request.getHeader("User-ID");
		if(userId == null) {
			return "";
		}
		return userId;
	}
	
}
