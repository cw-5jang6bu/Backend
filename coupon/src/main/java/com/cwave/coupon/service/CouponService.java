package com.cwave.coupon.service;

import com.cwave.coupon.dto.CouponResponseDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
@RequiredArgsConstructor
public class CouponService {
    private final RedisTemplate<String, Object> redisTemplate;
    private final ObjectMapper objectMapper; // JSON 변환을 위한 Jackson

    private static final String RECEIVED_COUPONS = "received_coupons"; // ✅ 중복 발급 확인용 Set
    private static final String MEMBER_COUPON = "member_coupon"; // ✅ 쿠폰 저장용 Hash
    private static final String COUPON_TIMEZONE = "coupon:%s:timezone"; // ✅ 쿠폰 만료 정보 저장용 String

    /**
     * 특정 회원의 쿠폰 발급 상태 확인
     */
    public CouponResponseDto checkCouponStatus(String memberId) {
        log.info("Check coupon data2: " + memberId);
        // ✅ 이미 쿠폰을 받은 적이 있는지 확인
        Boolean hasReceived = redisTemplate.opsForSet().isMember(RECEIVED_COUPONS, memberId);
        log.info("Check coupon data3: " + hasReceived);
        if (Boolean.FALSE.equals(hasReceived)) { // ❌ 기존: TRUE일 때만 조회해야 함
            return new CouponResponseDto(memberId, false, "쿠폰을 발급받은 적이 없습니다.", null);
        }
        log.info("Check coupon data4: " + memberId);

        String redisKey = String.valueOf(memberId);
        log.info("Check coupon data5: " + redisKey);
        // ✅ member_coupon에서 memberId에 대한 정보 가져오기
        Object couponDataObj = redisTemplate.opsForHash().get(MEMBER_COUPON, redisKey);
        log.info("Check coupon data6: {}", couponDataObj);

        if (couponDataObj == null) {
            return new CouponResponseDto(memberId, false, "쿠폰 정보를 찾을 수 없습니다.", null);
        }

        try {
            // ✅ Object -> JSON String 변환 (Redis에서 올바른 String을 가져올 수 있도록 처리)
            String couponDataJson;
            if (couponDataObj instanceof String) {
                couponDataJson = (String) couponDataObj;
            } else {
                couponDataJson = objectMapper.writeValueAsString(couponDataObj); // Object를 JSON 문자열로 변환
            }

            log.info("Parsed coupon JSON: " + couponDataJson);

            // ✅ JSON을 Map<String, Object>으로 변환
            Map<String, Object> couponInfo = objectMapper.readValue(couponDataJson, new TypeReference<Map<String, Object>>() {
            });

            // ✅ 쿠폰 ID 존재 여부 확인
            if (!couponInfo.containsKey("coupon_id")) {
                return new CouponResponseDto(memberId, false, "쿠폰 정보를 찾을 수 없습니다.", null);
            }

            boolean used = Boolean.TRUE.equals(couponInfo.get("used"));
            String couponId = String.valueOf(couponInfo.get("coupon_id"));

            // ✅ 쿠폰 만료 정보 가져오기
            String timezoneKey = String.format(COUPON_TIMEZONE, couponId);
            Object timezoneObj = redisTemplate.opsForValue().get(timezoneKey);
            String timezone = timezoneObj != null ? timezoneObj.toString() : null;

            return new CouponResponseDto(
                    memberId,
                    !used,
                    used ? "쿠폰이 이미 사용되었습니다." : "쿠폰이 정상적으로 발급되었습니다.",
                    timezone
            );

        } catch (JsonProcessingException e) {
            throw new RuntimeException("쿠폰 데이터 파싱 중 오류 발생", e);
        }

    }
}

