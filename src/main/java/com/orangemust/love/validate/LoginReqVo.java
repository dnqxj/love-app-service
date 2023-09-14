package com.orangemust.love.validate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ApiModel(value = "用户登录", description = "请求参数类")
public class LoginReqVo {

    @ApiModelProperty(example = "xsyl", required = true, notes = "用户名")
    @NotBlank(message = "用户名不能为空") // 此注解表明字段不能为null,也不能为空字符串
    private String username;

    @ApiModelProperty(example = "123456", required = true, notes = "密码")
    @NotBlank(message = "密码不能为空") // 此注解表明字段不能为null,也不能为空字符串
    private String password;
}