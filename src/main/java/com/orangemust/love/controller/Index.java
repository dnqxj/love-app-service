package com.orangemust.love.controller;

import com.alibaba.fastjson.JSON;
import com.llqqww.thinkjdbc.D;
import com.orangemust.love.model.OrderModel;
import com.orangemust.love.validate.OrderDetailsReqVo;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Index {

    @GetMapping(path = "hello")
    public String say(String name) {
        return "Hello World";
    }
}