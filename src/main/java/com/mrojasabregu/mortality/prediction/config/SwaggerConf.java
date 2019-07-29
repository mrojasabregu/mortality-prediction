package com.mrojasabregu.mortality.prediction.config;


import com.google.common.collect.Sets;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Configuration
@EnableSwagger2
public class SwaggerConf {

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("mortality-prediction-1.0")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrojasabregu.mortality.prediction.controller"))
                .paths(PathSelectors.ant("/**"))
                .build()
                .produces(Sets.newHashSet(APPLICATION_JSON_VALUE))
                .consumes(Sets.newHashSet(APPLICATION_JSON_VALUE))
                .apiInfo(apiEndPointsInfo());
    }

    private ApiInfo apiEndPointsInfo() {
        return new ApiInfoBuilder().title("Mortality prediction")
                .description("Mortality prediction REST API")
                .contact(new Contact("Matias Rojas", "", "ar.rojas.matias@gmail.com"))
                .license("Apache 2.0")
                .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html")
                .version("1.0.0")
                .build();
    }
}
