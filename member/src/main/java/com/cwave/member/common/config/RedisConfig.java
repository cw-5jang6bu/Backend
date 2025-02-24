package com.cwave.member.common.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        // return new LettuceConnectionFactory("dev-elasticache-cluster.lbkn6z.ng.0001.apn2.cache.amazonaws.com", 6379); // elasticache 연결
        return new LettuceConnectionFactory("localhost", 6379); // application.yml에서 설정된 Redis로 진행
    }

    @Bean
    public RedisTemplate<String, Boolean> redisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Boolean> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // ✅ Key Serializer: 문자열 형태로 저장
        template.setKeySerializer(new StringRedisSerializer());

        // ✅ Value Serializer: JSON 형태로 저장 (Boolean 값을 변환 가능)
        template.setValueSerializer(new GenericJackson2JsonRedisSerializer());

        template.afterPropertiesSet();
        return template;
    }
}