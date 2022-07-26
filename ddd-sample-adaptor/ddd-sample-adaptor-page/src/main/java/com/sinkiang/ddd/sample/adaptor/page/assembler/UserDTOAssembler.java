package com.sinkiang.ddd.sample.adaptor.page.assembler;

import com.sinkiang.ddd.sample.adaptor.page.dto.response.UserResDTO;
import com.sinkiang.ddd.sample.adaptor.page.dto.request.UserReqDTO;
import com.sinkiang.ddd.sample.common.util.CopierUtils;
import com.sinkiang.ddd.sample.infrastructure.repository.model.UserModel;

/**
 * @author dengxj
 * @date 2022/7/21 10:27
 */
public class UserDTOAssembler {

    public static UserModel toModel(UserReqDTO userReqDTO) {
        return CopierUtils.copyProperties(userReqDTO, UserModel.class);
    }
    public static UserResDTO toDTO(UserModel userModel) {
        return CopierUtils.copyProperties(userModel, UserResDTO.class);
    }
}
