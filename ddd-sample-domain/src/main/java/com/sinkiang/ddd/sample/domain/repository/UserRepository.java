package com.sinkiang.ddd.sample.domain.repository;

import com.sinkiang.ddd.sample.domain.model.User;

/**
 * @author dengxj
 * @date 2022/7/20 10:34
 */
public interface UserRepository {

    /**
     * 新建用户
     * @param user
     * @return
     */
    boolean createUser(User user);

}
