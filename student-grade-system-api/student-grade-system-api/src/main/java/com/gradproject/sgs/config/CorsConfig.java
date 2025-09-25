package com.gradproject.sgs.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // 允许 /api/ 下的所有请求
                registry.addMapping("/api/**")
                        // 允许来自 Vue 开发服务器的请求
                        .allowedOrigins("http://localhost:8081")
                        // 允许的请求方法
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                        // 允许所有请求头
                        .allowedHeaders("*")
                        // 是否允许携带 Cookie
                        .allowCredentials(true);
            }
        };
    }
}