package com.sinkiang.ddd.sample.adaptor.facade.assembler;

import com.sinkiang.ddd.sample.adaptor.facade.dto.UserDTO;
import com.sinkiang.ddd.sample.domain.model.User;
import org.springframework.beans.BeanUtils;

public class UserDTOAssembler {

    public static User toDomain(UserDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
