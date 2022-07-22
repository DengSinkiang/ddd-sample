package com.sinkiang.ddd.sample.adaptor.facade.impl;

import com.sinkiang.ddd.sample.adaptor.facade.UserFacade;
import com.sinkiang.ddd.sample.adaptor.facade.assembler.UserDTOAssembler;
import com.sinkiang.ddd.sample.adaptor.facade.dto.UserDTO;
import com.sinkiang.ddd.sample.application.UserAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author dengxj
 * @date 2022/7/20 10:21
 */
@Service
public class UserFacadeImpl implements UserFacade {

    @Autowired
    private UserAppService userAppService;

    @Override
    public void createUser(UserDTO userDTO) {
        userAppService.createUser(UserDTOAssembler.toDomain(userDTO));
    }
}
