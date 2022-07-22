package com.sinkiang.ddd.sample.adaptor.facade;

import com.sinkiang.ddd.sample.adaptor.facade.dto.UserDTO;

/**
 * @author dengxj
 * @date 2022/7/20 10:21
 */
public interface UserFacade {

    void createUser(UserDTO userDTO);
}
