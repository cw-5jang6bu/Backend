package com.cwave.product.common.template;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.ResponseEntity;

@Getter
public class BaseResponse<T> {

    private static final String SUCCESS = "SUCCESS";
    private static final String FAIL = "FAIL";

    @JsonProperty(value = "isSuccess")
    private final boolean success;
    private final String message;
    private final T data;

    @Builder
    private BaseResponse(String message, boolean success, T data) {
        this.success = success;
        this.message = message;
        this.data = data;
    }

    /**
     * 반환 데이터가 없는 성공 메시지 템플릿
     * @return BaseResponse 객체만 반한
     * @param <T> 반환 데이터의 타입을 나타내는 제네릭 타입 파라미터
     */
    public static <T> ResponseEntity<BaseResponse<T>> ofSuccess() {
        return ResponseEntity.ok(BaseResponse.<T>builder()
            .message(SUCCESS)
            .success(true)
            .data(null)
            .build());
    }

    /**
     * 반환 데이터가 있는 성공 메시지 템플릿
     * @param data 반환 데이터
     * @return BaseResponse 객체만 반한
     * @param <T> 반환 데이터의 타입을 나타내는 제네릭 타입 파라미터
     */
    public static <T> ResponseEntity<BaseResponse<T>> ofSuccess(T data) {
        return ResponseEntity.ok(BaseResponse.<T>builder()
            .message(SUCCESS)
            .success(true)
            .data(data)
            .build());
    }

    /**
     * 예외를 반환하는 실패 메시지 템플릿
     * @return 예외를 반환하는 실패 메시지 템플릿
     * @param <T> 반환 데이터의 타입을 나타내는 제네릭 타입 파라미터
     */
    public static <T> ResponseEntity<BaseResponse<T>> ofFail() {
        return ResponseEntity.ok(BaseResponse.<T>builder()
            .message(FAIL)
            .success(true)
            .data(null)
            .build());
    }

}

