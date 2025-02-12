package com.cwave.product.config;

import com.cwave.product.domain.Member;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "member", url = "http://localhost:8081")
public interface MemberClient {
    @GetMapping("/members/{id}")
    Member getMemberById(@PathVariable("id") Long id);
}
