package com.sinkiang.ddd.sample.adaptor.facade.assembler;

import com.sinkiang.ddd.sample.adaptor.facade.dto.UserDTO;
import com.sinkiang.ddd.sample.common.util.CopierUtils;
import com.sinkiang.ddd.sample.domain.model.User;

/**
 * @author dengxj
 * @date 2022/7/20 14:57
 */
public class UserDTOAssembler {

    public static User toDomain(UserDTO userDTO) {
        return CopierUtils.copyProperties(userDTO, User.class);
    }

}
