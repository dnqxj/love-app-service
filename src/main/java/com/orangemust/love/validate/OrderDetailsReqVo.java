package com.orangemust.love.validate;

import javax.validation.constraints.NotBlank;

public class OrderDetailsReqVo {
    @NotBlank // 此注解表明字段不能为null,也不能为空字符串
    private String token;
    @NotBlank // 此注解表明字段不能为null,也不能为空字符串
    private String orderCode;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getOrderCode() {
        return orderCode;
    }

    public void setOrderCode(String orderCode) {
        this.orderCode = orderCode;
    }
}