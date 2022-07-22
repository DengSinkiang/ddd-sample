package com.sinkiang.ddd.sample.infrastructure.repository.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sinkiang.ddd.sample.common.page.PageRequest;
import com.sinkiang.ddd.sample.common.page.PageResult;
import com.sinkiang.ddd.sample.common.page.convert.PageConvert;
import com.sinkiang.ddd.sample.infrastructure.repository.mapper.UserMapper;
import com.sinkiang.ddd.sample.infrastructure.repository.model.UserModel;
import com.sinkiang.ddd.sample.infrastructure.repository.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

/**
 * @author dengxj
 * @date 2022/7/20 15:57
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserModel> implements UserService {
    @Override
    public PageResult<UserModel> listUserByPage(PageRequest<UserModel> pageRequest) {
        Page<UserModel> page = new Page<>();
        page.setCurrent(pageRequest.getCurrent());
        page.setSize(pageRequest.getPageSize());
        LambdaQueryWrapper<UserModel> queryWrapper = new LambdaQueryWrapper<>();
        if (pageRequest.getData() != null && StringUtils.isNotBlank(pageRequest.getData().getUsername())) {
            queryWrapper.eq(UserModel::getUsername, pageRequest.getData().getUsername());
        }
        Page<UserModel> userModelPage = this.page(page, queryWrapper);
        return PageConvert.convert(userModelPage.getTotal(), userModelPage.getCurrent(), userModelPage.getSize(), userModelPage.getRecords());
    }
}
