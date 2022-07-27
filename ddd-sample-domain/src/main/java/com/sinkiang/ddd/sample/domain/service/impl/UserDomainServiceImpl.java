package com.sinkiang.ddd.sample.domain.service.impl;

import com.sinkiang.ddd.sample.domain.model.User;
import com.sinkiang.ddd.sample.domain.repository.UserRepository;
import com.sinkiang.ddd.sample.domain.service.UserDomainService;
import org.springframework.stereotype.Service;

/**
 * @author dengxj
 * @date 2022/7/20 10:36
 */
@Service
public class UserDomainServiceImpl implements UserDomainService {

     private final UserRepository userRepository;

    public UserDomainServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
     public boolean createUser(User user) {
          return userRepository.createUser(user);
     }
}
