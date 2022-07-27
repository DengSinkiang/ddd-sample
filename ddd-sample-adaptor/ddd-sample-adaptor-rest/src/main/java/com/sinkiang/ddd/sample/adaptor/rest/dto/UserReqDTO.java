package com.sinkiang.ddd.sample.adaptor.rest.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author dengxj
 * @date 2022/7/21 14:51
 */
public class UserReqDTO {

    @NotBlank(message = "用户名不能为空")
    private String username;
    private String email;
    private Integer age;
    private String address;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "UserReqDTO{" +
                "username='" + username + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                ", address='" + address + '\'' +
                '}';
    }
}
