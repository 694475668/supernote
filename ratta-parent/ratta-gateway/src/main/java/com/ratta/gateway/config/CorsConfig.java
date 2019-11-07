package com.ratta.gateway.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;
import org.springframework.web.util.pattern.PathPatternParser;

/**
 * ******Title:程序的奥秘 ******
 * ******Description:<p> 刘明 因为是跨域请求，前端会首先发送一个options的嗅探请求，
 * 一次真正的请求 所以会出现两个403
 * 结果发现根源在Webflux上边，由于gateway使用的是webflux，而不是springmvc，
 * 所以需要先关闭webflux的cors，再从gateway的filter里边设置cors就行了。</p>******
 * ******Company: <p> 雷塔智能科技有限公司 </p> ******
 *
 * @version 1.0:
 * @author: 刘 明  [QQ 694475668]
 * @date:Created in 2019-07-09 17:41
 */

@Configuration
public class CorsConfig {
	@Value("${cors.method}")
	private String method;
	@Value("${cors.header}")
	private String header;
	@Value("${cors.origin}")
	private String origin;
	
    @Bean
    public CorsWebFilter corsFilter() {
    	CorsConfiguration config = new CorsConfiguration();
    	config.addAllowedMethod(method);
        config.setAllowCredentials(true);
        config.addAllowedHeader(header);
    	String [] origins = origin.split(",");
    	for (String string : origins) {
    	    config.addAllowedOrigin(string);
		}
       
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource(new PathPatternParser());
        source.registerCorsConfiguration("/**", config);

        return new CorsWebFilter(source);
    }
}