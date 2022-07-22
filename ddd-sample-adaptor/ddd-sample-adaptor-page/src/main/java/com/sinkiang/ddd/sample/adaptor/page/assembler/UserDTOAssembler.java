package com.sinkiang.ddd.sample.adaptor.page.assembler;

import com.sinkiang.ddd.sample.adaptor.page.dto.response.UserResDTO;
import com.sinkiang.ddd.sample.adaptor.page.dto.request.UserReqDTO;
import com.sinkiang.ddd.sample.infrastructure.repository.model.UserModel;
import org.springframework.beans.BeanUtils;

/**
 * @author dengxj
 * @date 2022/7/21 10:27
 */
public class UserDTOAssembler {

    public static UserModel toModel(UserReqDTO userReqDTO) {
        UserModel model = new UserModel();
        BeanUtils.copyProperties(userReqDTO, model);
        return model;
    }
    public static UserResDTO toDTO(UserModel userModel) {
        UserResDTO dto = new UserResDTO();
        BeanUtils.copyProperties(userModel, dto);
        return dto;
    }
}
