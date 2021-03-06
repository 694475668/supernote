package com.ratta.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gxy
 */
@Profile({"local", "dev", "test", "default"})
@Configuration
@EnableSwagger2
public class Swagger2Config {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        ParameterBuilder tokenPar1 = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        //开发阶段不通过网关直接使用userId
        tokenPar1.name("User-ID").description("用户id").modelRef(new ModelRef("long")).parameterType("header")
                .required(false).build();
        tokenPar.name("Line").description("上线预览标识").modelRef(new ModelRef("String")).parameterType("header").required(false).build();
        pars.add((Parameter) tokenPar.build());
        pars.add((Parameter) tokenPar1.build());
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
                .apis(RequestHandlerSelectors.basePackage("com.ratta.controller")).paths(PathSelectors.any()).build()
                .globalOperationParameters(pars).apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Cms-Service").description("cms服务API接口文档").version("1.0").build();
    }
}