package com.sinkiang.ddd.sample.infrastructure.repository.assembler;

import com.sinkiang.ddd.sample.domain.model.User;
import com.sinkiang.ddd.sample.infrastructure.repository.model.UserModel;
import org.mapstruct.Mapper;

/**
 * mapstruct 进行数据转换
 * @author dengxj
 * @date 2022/7/20 17:03
 */

@Mapper
public interface UserAssembler {

    UserModel toUserModel(User user);
}
