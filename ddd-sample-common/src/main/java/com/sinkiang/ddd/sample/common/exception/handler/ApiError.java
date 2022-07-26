package com.sinkiang.ddd.sample.common.exception.handler;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@Data
class ApiError {

    private int code = HttpStatus.BAD_REQUEST.value();
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
    private String message;

    private ApiError() {
        timestamp = LocalDateTime.now();
    }

    static ApiError error(String message) {
        ApiError apiError = new ApiError();
        apiError.setMessage(message);
        return apiError;
    }

    static ApiError error(int code, String message) {
        ApiError apiError = new ApiError();
        apiError.setCode(code);
        apiError.setMessage(message);
        return apiError;
    }
}
