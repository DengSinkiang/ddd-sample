package com.sinkiang.ddd.sample.infrastructure.repository.assembler;

import com.sinkiang.ddd.sample.domain.model.User;
import com.sinkiang.ddd.sample.infrastructure.repository.model.UserModel;
import org.springframework.beans.BeanUtils;

/**
 * @author dengxj
 * @date 2022/7/20 17:03
 */
public class UserAssembler {

    public static UserModel toUserModel(User user) {
        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(user, userModel);
        return userModel;
    }
}
