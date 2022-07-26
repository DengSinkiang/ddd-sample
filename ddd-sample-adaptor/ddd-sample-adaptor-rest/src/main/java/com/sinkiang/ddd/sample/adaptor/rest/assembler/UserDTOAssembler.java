package com.sinkiang.ddd.sample.adaptor.rest.assembler;

import com.sinkiang.ddd.sample.adaptor.rest.dto.UserReqDTO;
import com.sinkiang.ddd.sample.common.util.CopierUtils;
import com.sinkiang.ddd.sample.domain.model.User;

/**
 * @author dengxj
 * @date 2022/7/20 14:57
 */
public class UserDTOAssembler {

    public static User toDomain(UserReqDTO userDTO) {
        return CopierUtils.copyProperties(userDTO, User.class);
    }

}
