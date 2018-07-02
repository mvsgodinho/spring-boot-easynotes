package com.experian.buid.easynotes.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.experian.buid.easynotes.AppConstants;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author Marcos Godinho
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {
	
    @Bean
    public Docket productApi() {
    	// @formatter:off
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage(AppConstants.CONTROLLER_PKG))
                .build()
                .apiInfo(metaData())
                ;
        // @formatter:on
    }
    
    private ApiInfo metaData() {
    	// @formatter:off
        return new ApiInfoBuilder()
                .title(AppConstants.Api.TITLE)
                .description(AppConstants.Api.DESCRIPTION)
                .version(AppConstants.Api.VERSION)
                .build();
        // @formatter:on
    }

    

}
