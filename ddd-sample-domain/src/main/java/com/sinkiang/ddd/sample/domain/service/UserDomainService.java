package com.sinkiang.ddd.sample.domain.service;

import com.sinkiang.ddd.sample.domain.model.User;

/**
 * @author dengxj
 * @date 2022/7/20 10:35
 */
public interface UserDomainService {
    /**
     * 创建用户
     * @param user
     */
    boolean createUser(User user);
}
