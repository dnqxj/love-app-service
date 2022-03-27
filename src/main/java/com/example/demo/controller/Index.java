package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.OrderModel;
import com.example.demo.validate.OrderDetailsReqVo;
import com.llqqww.thinkjdbc.D;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/")
public class Index {

    //    public List<OrderModel> hello(@Validated @RequestBody OrderDetailsReqVo reqVo) {
//    @RequestBody  接收json时使用
    @GetMapping(path = "/hello")
    public List<OrderModel> hello(@Validated OrderDetailsReqVo reqVo) {
        System.out.println(reqVo.getOrderCode());
        System.out.println(reqVo.getToken());
        List<OrderModel> list = new ArrayList<>();
        try {
            Long num = (long) D.M(OrderModel.class).where("id>0").count();
            System.out.println("记录条数为：" + num);
            list = D.M(OrderModel.class).select();
            for(int i=0;i<list.size();i++) {
                OrderModel orderModel = list.get(i);
                System.out.println(JSON.toJSONString(orderModel));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @GetMapping(path = "say")
    public String say(String name) {
        System.out.println(name);
        return name;
    }
}