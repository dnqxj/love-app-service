package com.example.demo.validate;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class LoginReqVo {
    @NotBlank(message = "用户名不能为空") // 此注解表明字段不能为null,也不能为空字符串
    private String username;
    @NotBlank(message = "密码不能为空") // 此注解表明字段不能为null,也不能为空字符串
    private String password;
}