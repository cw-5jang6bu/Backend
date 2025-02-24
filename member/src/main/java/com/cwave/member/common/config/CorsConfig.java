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
//                        .allowedOrigins("http://ab7332c421fb240e58251da541621cbc-1437135698.ap-northeast-2.elb.amazonaws.com")
                        .allowedOrigins("http://localhost:3000")
                        .allowedMethods("*") // ✅ 모든 HTTP 메서드 허용
                        .allowedHeaders("*") // ✅ 모든 헤더 허용
                        .allowCredentials(true); // ✅ 인증 정보 포함 (필요 시)
            }
        };
    }
}


