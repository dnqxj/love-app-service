package com.orangemust.love.validate;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@ApiModel(value = "用户注册", description = "请求参数类")
public class RegisterReqVo {

    @ApiModelProperty(value = "用户名", example = "dgjj", required = true, notes = "用户名")
    @NotBlank(message = "用户名不能为空") // 此注解表明字段不能为null,也不能为空字符串
    private String username;

    @ApiModelProperty(value = "密码", example = "123456", required = true, notes = "密码")
    @NotBlank(message = "密码不能为空") // 此注解表明字段不能为null,也不能为空字符串
    private String password;

    @ApiModelProperty(value = "中文姓名", example = "独孤九剑", required = true, notes = "中文姓名")
    @NotBlank(message = "姓名不能为空")
    private String name;

    @ApiModelProperty(value = "性别", example = "1", required = true, notes = "性别（1：男，0：女")
    @NotNull(message = "性别不能为空")
    private Integer gender;

    @ApiModelProperty(value = "生日", example = "2000-01-01", required = true, notes = "生日")
    @NotBlank(message = "生日不能为空")
    private String birthday;

    @ApiModelProperty(value = "阳历", example = "日期类型", required = true, notes = "阳历：1，阴历：0")
    @NotNull(message = "日期类型不能为空")
    private Integer solar;
}
