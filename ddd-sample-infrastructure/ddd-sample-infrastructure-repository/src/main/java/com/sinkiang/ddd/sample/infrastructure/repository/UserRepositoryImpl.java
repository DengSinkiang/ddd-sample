package com.sinkiang.ddd.sample.infrastructure.repository;

import com.sinkiang.ddd.sample.domain.model.User;
import com.sinkiang.ddd.sample.domain.repository.UserRepository;
import com.sinkiang.ddd.sample.infrastructure.repository.assembler.UserAssembler;
import com.sinkiang.ddd.sample.infrastructure.repository.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author dengxj
 * @date 2022/7/20 10:31
 */
@Service
public class UserRepositoryImpl implements UserRepository {

    @Autowired
    private UserService userService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean createUser(User user) {
        return userService.save(UserAssembler.toUserModel(user));
    }
}
