package com.sinkiang.ddd.sample.common.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
@Getter
public class SkException extends RuntimeException {

    private Integer status = 500;

    public SkException(String msg) {
        super(msg);
    }

    public SkException(HttpStatus status, String msg) {
        super(msg);
        this.status = status.value();
    }
}

