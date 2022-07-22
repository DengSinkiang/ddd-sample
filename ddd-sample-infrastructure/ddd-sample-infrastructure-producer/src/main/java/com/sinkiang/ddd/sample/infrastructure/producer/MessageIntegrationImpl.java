package com.sinkiang.ddd.sample.infrastructure.producer;

import com.sinkiang.ddd.sample.domain.integration.MessageIntegration;
import com.sinkiang.ddd.sample.domain.model.User;
import org.springframework.stereotype.Service;

/**
 * @author dengxj
 * @date 2022/7/21 15:48
 */
@Service
public class MessageIntegrationImpl implements MessageIntegration {

    @Override
    public void sendUserMsg(User user) {
        // TODO 发送消息
    }
}
