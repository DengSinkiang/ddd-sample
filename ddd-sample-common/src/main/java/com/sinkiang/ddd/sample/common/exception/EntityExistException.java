package com.sinkiang.ddd.sample.common.exception;

import org.springframework.util.StringUtils;

/**
 * @author sinkiang
 * @date 2022/4/7 10:32
 */
public class EntityExistException extends RuntimeException {

    public EntityExistException(Object o, String field, String val) {
        super(EntityExistException.generateMessage(o.getClass().getSimpleName(), field, val));
    }

    private static String generateMessage(String entity, String field, String val) {
        return StringUtils.capitalize(entity)
                + " with " + field + " " + val + " existed";
    }
}
