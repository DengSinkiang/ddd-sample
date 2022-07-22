package com.sinkiang.ddd.sample.common.util;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sinkiang.ddd.sample.common.enums.CommonEnum;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @description: API 返回结果封装工具类
 * @author: sinkiang
 * @date: 2020/4/13 9:45
 */
@Data
public class ResultUtils<T> {

    /**
     * 状态码
     */
    private int code;
    /**
     * 信息
     */
    private String message;
    /**
     * 结果
     */
    private T data;
    /**
     * 时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;

    protected ResultUtils() {
    }

    private ResultUtils(int code, String message, LocalDateTime timestamp, T data) {
        this.code = code;
        this.message = message;
        this.timestamp = timestamp;
        this.data = data;

    }

    /**
     * 成功返回结果
     *
     * @param data 获取的数据
     */
    public static <T> ResultUtils<T> success(T data) {
        return new ResultUtils<>(CommonEnum.SUCCESS.getKey(), CommonEnum.SUCCESS.getValue(), LocalDateTime.now(), data);
    }

    /**
     * 成功返回结果
     *
     * @param data    获取的数据
     * @param message 提示信息
     */
    public static <T> ResultUtils<T> success(T data, String message) {
        return new ResultUtils<>(CommonEnum.SUCCESS.getKey(), message, LocalDateTime.now(), data);
    }

    /**
     * 失败返回结果
     *
     * @param errorCode 错误码
     * @param message   错误信息
     */
    private static <T> ResultUtils<T> failed(int errorCode, String message) {
        return new ResultUtils<>(errorCode, message, LocalDateTime.now(), null);
    }

    /**
     * 失败返回结果
     *
     * @param message 提示信息
     */
    public static <T> ResultUtils<T> failed(String message) {
        return new ResultUtils<>(CommonEnum.FAILED.getKey(), message, LocalDateTime.now(), null);
    }

    /**
     * 失败返回结果
     */
    public static <T> ResultUtils<T> failed() {
        return failed(CommonEnum.FAILED.getKey(), CommonEnum.FAILED.getValue());
    }
}
