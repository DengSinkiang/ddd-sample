package com.sinkiang.ddd.sample.infrastructure.repository.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sinkiang.ddd.sample.common.page.PageRequest;
import com.sinkiang.ddd.sample.common.page.PageResult;
import com.sinkiang.ddd.sample.infrastructure.repository.model.UserModel;

/**
 * @author dengxj
 * @date 2022/7/20 15:56
 */
public interface UserService extends IService<UserModel> {
    /**
     * xxx
     * @param pageRequest
     * @return
     */
    PageResult<UserModel> listUserByPage(PageRequest<UserModel> pageRequest);
}
