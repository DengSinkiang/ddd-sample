package com.sinkiang.ddd.sample.domain.integration;

import com.sinkiang.ddd.sample.domain.model.User;

/**
 * @author dengxj
 * @date 2022/7/21 15:49
 */
public interface MessageIntegration {
    void sendUserMsg(User user);
}
