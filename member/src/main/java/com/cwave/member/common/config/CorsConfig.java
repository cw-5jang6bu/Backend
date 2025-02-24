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
                registry.addMapping("/**") // ✅ 모든 엔드포인트에 대해 CORS 허용
//                        .allowedOrigins("http://localhost:3000") // ✅ 올바른 React 앱 주소
                        .allowedMethods("*") // ✅ 모든 HTTP 메서드 허용
                        .allowedHeaders("*") // ✅ 모든 헤더 허용
                        .allowCredentials(true) // ✅ 인증 정보 포함 (필요 시)
                        .allowedOriginPatterns("*"); // 모든 출처 허용 (Spring Boot 3.x 이상)
            }
        };
    }
}


