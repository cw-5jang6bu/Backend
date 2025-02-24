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
                        .allowedOrigins("*") // ✅ 모든 도메인 허용
                        .allowedMethods("*") // ✅ 모든 HTTP 메서드 허용
                        .allowedHeaders("*") // ✅ 모든 헤더 허용
                        .allowCredentials(false); // ❌ 모든 Origin을 허용할 경우, Credentials는 허용 불가
            }
        };
    }
}


