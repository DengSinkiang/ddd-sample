package com.sinkiang.ddd.sample.application;

import com.sinkiang.ddd.sample.domain.model.User;

/**
 * @author dengxj
 * @date 2022/7/20 10:21
 */
public interface UserAppService {
    /**
     * xxx
     * @param user
     * @return
     */
    boolean createUser(User user);
}
