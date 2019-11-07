package com.ratta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * Title:程序的奥秘 Description: Company:
 *
 * @version :
 * @author: 刘 明
 * @date:Created in 2019年7月22日 下午12:13:19
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket ProductApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(productApiInfo()).select()
				.apis(RequestHandlerSelectors.basePackage("com.ratta.controller"))
				.apis(RequestHandlerSelectors.withClassAnnotation(Api.class))
				.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class)).paths(PathSelectors.any())
				.build();
	}

	@SuppressWarnings("deprecation")
	private ApiInfo productApiInfo() {
		ApiInfo apiInfo = new ApiInfo("《雷塔智能科技有限公司》数据迁移接口文档", "合作双赢", "1.0.0", "http://127.0.0.1:9595/doc.html", "刘明",
				"license", "https://supernote.com.cn/");
		return apiInfo;
	}
}
