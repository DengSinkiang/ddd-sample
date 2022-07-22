package com.sinkiang.ddd.sample.adaptor.page.controller;

import com.sinkiang.ddd.sample.adaptor.page.assembler.UserDTOAssembler;
import com.sinkiang.ddd.sample.adaptor.page.dto.request.UserReqDTO;
import com.sinkiang.ddd.sample.adaptor.page.dto.response.UserResDTO;
import com.sinkiang.ddd.sample.common.page.PageRequest;
import com.sinkiang.ddd.sample.common.page.PageResult;
import com.sinkiang.ddd.sample.common.util.ResultUtils;
import com.sinkiang.ddd.sample.infrastructure.repository.model.UserModel;
import com.sinkiang.ddd.sample.infrastructure.repository.service.UserService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dengxj
 * @date 2022/7/20 11:01
 */

@RestController
@RequestMapping(value = "/rest/user/page/v1")
public class UserPageController {

    @Autowired
    private UserService userService;

    @PostMapping(value = "/listUserByPage")
    public ResultUtils<PageResult<UserResDTO>> listUserByPage(@RequestBody PageRequest<UserReqDTO> pageRequest) {
        PageRequest<UserModel> model = new PageRequest<>();
        BeanUtils.copyProperties(pageRequest, model);
        UserModel userModel = new UserModel();
        if (pageRequest.getData() != null) {
            model.setData(UserDTOAssembler.toModel(pageRequest.getData()));
        }
        PageResult<UserModel> userModelPageResult = userService.listUserByPage(model);
        PageResult<UserResDTO> result = new PageResult<>();
        List<UserResDTO> list = new ArrayList<>(10);
        BeanUtils.copyProperties(userModelPageResult, result);
        if (CollectionUtils.isNotEmpty(userModelPageResult.getList())) {
            userModelPageResult.getList().forEach(a -> {
                list.add(UserDTOAssembler.toDTO(a));
            });
        }
        result.setList(list);
        return ResultUtils.success(result);
    }
}
