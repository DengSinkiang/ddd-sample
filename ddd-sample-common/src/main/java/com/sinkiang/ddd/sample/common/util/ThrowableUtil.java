package com.sinkiang.ddd.sample.common.util;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @description: 异常工具
 * @author: sinkiang
 * @date: 2020/3/27 16:14
 */
public class ThrowableUtil {

    /**
     * 获取堆栈信息
     */
    public static String getStackTrace(Throwable throwable) {
        StringWriter sw = new StringWriter();
        try (PrintWriter pw = new PrintWriter(sw)) {
            throwable.printStackTrace(pw);
            return sw.toString();
        }
    }

}
