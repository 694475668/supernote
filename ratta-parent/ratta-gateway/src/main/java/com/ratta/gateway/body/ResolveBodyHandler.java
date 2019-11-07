package com.ratta.gateway.body;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import com.ratta.gateway.filter.RequestFilter;
import lombok.extern.slf4j.Slf4j;

@EnableAutoConfiguration
@Configuration
@RefreshScope
@Slf4j
public class ResolveBodyHandler {

	@Autowired
	private RequestFilter requestFilter;

	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {

		/*
		 * route2是post请求，Content-Type是application/x-www-form-urlencoded，readbody为String.class
		 * route3 是post请求，Content-Type是application/json,readbody为Object.class
		 * 
		 * uri: url应该配置服务部署的地址，但由于我们是微服务，有多个地址，且这里无法配置多个，所以就配置一个通用的ip(http://127.0.0.1),好让项目可以正常启动。
		 * 		当进行路由时，找不到127.0.0.1里面的服务的话，就会自动去nacos的配置文件里面获取设置好的路由规则。
		 */
		RouteLocatorBuilder.Builder routes = builder.routes();
		RouteLocatorBuilder.Builder serviceProvider = routes
				.route("route1", r -> r.header(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_FORM_URLENCODED_VALUE ).and()
						.method(HttpMethod.POST).and().readBody(String.class, readBody -> {
							return true;
						}).and().path("/*").filters(f -> {
							f.filter(requestFilter);
							return f;
						}).uri("http://127.0.0.1"))
				.route("route2", r -> r.header(HttpHeaders.CONTENT_TYPE , MediaType.APPLICATION_JSON_VALUE ).and()
						.method(HttpMethod.POST).and().readBody(Object.class, readBody -> {
							return true;
						}).and().path("/*").filters(f -> {
							f.filter(requestFilter);
							return f;
						}).uri("http://127.0.0.1"));
		RouteLocator routeLocator = serviceProvider.build();
		log.info("custom RouteLocator is loading ... {}", routeLocator);
		return routeLocator;
	}
}
