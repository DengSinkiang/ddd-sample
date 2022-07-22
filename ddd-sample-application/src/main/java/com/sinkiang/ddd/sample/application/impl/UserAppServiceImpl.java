package com.sinkiang.ddd.sample.application.impl;

import com.sinkiang.ddd.sample.application.UserAppService;
import com.sinkiang.ddd.sample.domain.model.User;
import com.sinkiang.ddd.sample.domain.service.UserDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dengxj
 * @date 2022/7/20 10:21
 */
@Service
public class UserAppServiceImpl implements UserAppService {

    @Autowired
    private UserDomainService userDomainService;

    @Override
    public boolean createUser(User user) {
        return userDomainService.createUser(user);
    }
}
