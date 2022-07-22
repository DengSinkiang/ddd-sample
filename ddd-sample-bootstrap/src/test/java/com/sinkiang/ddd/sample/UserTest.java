package com.sinkiang.ddd.sample;

import com.alibaba.fastjson.JSON;
import com.sinkiang.ddd.sample.adaptor.page.dto.request.UserReqDTO;
import com.sinkiang.ddd.sample.bootstrap.DddSampleApplication;
import com.sinkiang.ddd.sample.common.page.PageRequest;
import com.sinkiang.ddd.sample.domain.model.User;
import com.sinkiang.ddd.sample.domain.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

/**
 * @author dengxj
 * @date 2022/7/20 17:16
 */
@SpringBootTest(classes = DddSampleApplication.class)
public class UserTest {

    @Resource
    private UserRepository userRepository;

    @Test
    public void testSaveUser() {
        User user = new User();
        user.setUsername("dengxinjiang");
        user.setAge(27);
        user.setEmail("dengxj@qq.com");
        user.setAddress("杭州市");
        userRepository.createUser(user);
    }
    @Test
    public void testUser() {
        PageRequest<UserReqDTO> pageRequest  = new PageRequest<>();
        pageRequest.setCurrent(1L);
        pageRequest.setPageSize(10L);
        UserReqDTO userReqDTO = new UserReqDTO();
        userReqDTO.setUsername("dengxinjiang");
        pageRequest.setData(userReqDTO);
        System.out.println(JSON.toJSONString(pageRequest));
    }

}
