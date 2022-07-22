package com.sinkiang.ddd.sample.adaptor.rest.assembler;

import com.sinkiang.ddd.sample.adaptor.rest.dto.UserReqDTO;
import com.sinkiang.ddd.sample.domain.model.User;
import org.springframework.beans.BeanUtils;

public class UserDTOAssembler {

    public static User toDomain(UserReqDTO userDTO) {
        User user = new User();
        BeanUtils.copyProperties(userDTO, user);
        return user;
    }

}
