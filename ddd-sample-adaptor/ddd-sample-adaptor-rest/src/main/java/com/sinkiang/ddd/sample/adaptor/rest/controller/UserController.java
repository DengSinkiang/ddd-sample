package com.sinkiang.ddd.sample.adaptor.rest.controller;

import com.sinkiang.ddd.sample.adaptor.rest.assembler.UserDTOAssembler;
import com.sinkiang.ddd.sample.adaptor.rest.dto.UserReqDTO;
import com.sinkiang.ddd.sample.application.UserAppService;
import com.sinkiang.ddd.sample.common.util.ResultUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dengxj
 * @date 2022/7/20 10:13
 */

@RestController
@RequestMapping(value = "/rest/user/v1")
public class UserController {

    private final UserAppService userAppService;

    public UserController(UserAppService userAppService) {
        this.userAppService = userAppService;
    }

    @PostMapping(value = "/createUser")
    public ResultUtils<Boolean> createUser(@RequestBody @Validated UserReqDTO userReqDTO) {
        return ResultUtils.success(userAppService.createUser(UserDTOAssembler.toDomain(userReqDTO)));
    }
}
