package com.chadoll.apigatewayservice.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// Config 클래스를 활용한 경로 설정
//@Configuration
public class CustomRoute {

//    @Bean
    public RouteLocator mstRoute(RouteLocatorBuilder builder) {

        return builder.routes()
                .route("user", r -> r.path("/user/**")
                        .uri("http://localhost:8081"))
                .route("community", r -> r.path("/community/**")
                        .uri("http://localhost:8082"))
                .build();
    }
}
