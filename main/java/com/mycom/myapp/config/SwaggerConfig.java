package com.mycom.myapp.config;

import org.springdoc.core.customizers.OpenApiCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.PathItem;
import io.swagger.v3.oas.models.Paths;
import io.swagger.v3.oas.models.info.Info;

/**
 * SwaggerConfig 클래스는 Spring Boot 애플리케이션에 Swagger를 설정하는 구성 클래스를 제공합니다.
 */
@Configuration
public class SwaggerConfig {

    /**
     * OpenAPI 인스턴스를 빈으로 등록하여 Swagger UI의 기본 설정을 제공합니다.
     *
     * @return OpenAPI 객체
     */
    @Bean
    OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components()) // OpenAPI 구성 요소를 초기화합니다.
                .info(apiInfo()); // API에 대한 정보를 설정합니다.
    }

    /**
     * API의 기본 정보를 설정합니다.
     *
     * @return Info 객체
     */
    private Info apiInfo() {
        return new Info()
                .title("주문테이블 API") // API의 제목을 설정합니다.
                .description("REST API 로 구현된 주문테이블") // API의 설명을 설정합니다.
                .version("v0.91"); // API의 버전을 설정합니다.
    }
    
    /**
     * OpenAPI 객체를 사용자 정의하여 특정 경로(path)만 Swagger UI에 표시되도록 설정합니다.
     *
     * @return OpenApiCustomizer 객체
     */
    @Bean
    OpenApiCustomizer getEndpointsCustomizer() {
        return openApi -> {
            Paths paths = new Paths(); // 새로운 Paths 객체를 생성하여 경로를 저장합니다.
            
            // OpenAPI의 모든 경로를 순회합니다.
            openApi.getPaths().forEach((path, pathItem) -> {
                PathItem newPathItem = new PathItem(); // 새로운 PathItem 객체를 생성합니다.
                
                // GET 메소드가 있는 PathItem만 필터링하여 새로운 Paths에 추가합니다.
                if (pathItem.getGet() != null) {
                    newPathItem.setGet(pathItem.getGet()); // GET 메소드를 설정합니다.
                    paths.addPathItem(path, newPathItem); // 새로운 PathItem을 Paths에 추가합니다.
                }
            });
            openApi.setPaths(paths); // 사용자 정의된 경로를 OpenAPI 객체에 설정합니다.
        };
    }
}
