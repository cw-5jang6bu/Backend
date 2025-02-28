package com.cwave.member.common.config;

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
                registry.addMapping("/**")  // ✅ 모든 엔드포인트에 대해 CORS 허용
                        .allowedOrigins("http://localhost:3000")  // ✅ React 개발 서버 허용
                        .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // ✅ 허용할 HTTP 메서드
                        .allowCredentials(true);  // ✅ 인증 관련 요청 허용
            }
        };
    }
}




