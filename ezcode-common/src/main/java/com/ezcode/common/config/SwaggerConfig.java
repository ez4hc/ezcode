package com.ezcode.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

/**
 * @Author: hc
 * @Date: 2022/1/17 17:13
 */
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .pathMapping("/")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ezcode.server.controller"))
                .paths(PathSelectors.any())
                .build().apiInfo(new ApiInfoBuilder()
                        .title("ezcode")
                        .description("")
                        .version("1.0")
                        .contact(new Contact(
                                "ez4hc",
                                "https://www.hc4.fun",
                                "ez4hcc@gmial.com"))
                        .license("The Apache License")
                        .licenseUrl("https://github.com/ez4hc")
                        .build()
                );
    }
}
