package com.cwave.member.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())  // ✅ CSRF 보호 비활성화 (API 통신을 위해)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(new AntPathRequestMatcher("/h2-console/**")).permitAll()  // ✅ H2 Console 허용
                        .requestMatchers(new AntPathRequestMatcher("/auth/**")).permitAll()  // ✅ 로그인 관련 요청 허용
                        .requestMatchers(new AntPathRequestMatcher("/coupons/**")).permitAll()  // ✅ 쿠폰 API는 인증 필요 없음
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}



