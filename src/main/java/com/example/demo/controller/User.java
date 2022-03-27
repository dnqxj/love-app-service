package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.validate.LoginReqVo;
import com.example.demo.validate.RegisterReqVo;
import com.llqqww.thinkjdbc.D;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@RequestMapping(path = "/user")
@RestController
public class User {


    @PostMapping("login")
    public HashMap<String, Object> login(@Validated @RequestBody LoginReqVo loginReqVo) {
        String username = loginReqVo.getUsername();
        String password = loginReqVo.getPassword();

        UserModel userModel = new UserModel();
        try {
            userModel  = D.M(UserModel.class).where("username=?", username).find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        if (userModel != null) {
            hashMap.put("status", "success");
            hashMap.put("message", "登录成功");

            HashMap<String, Object> dataHashMap = new HashMap<>();
            dataHashMap.put("user", userModel);
            dataHashMap.put("token", "qwertyuiop1234567890");
            hashMap.put("data", dataHashMap);
        } else {
            hashMap.put("status", "error");
            hashMap.put("message", "未找到登录用户~");
        }
        return hashMap;
    }

    @PostMapping("register")
    public HashMap<String, Object> register(@Validated @RequestBody RegisterReqVo registerReqVo) {
        String username = registerReqVo.getUsername();
        String password = registerReqVo.getPassword();

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        hashMap.put("message", "注册成功");
        return hashMap;
    }


    @PostMapping("checkToken")
    public HashMap<String, Object> checkToken() {
//        接收header中的token，进行解析查询
        String username = "xsyl";
        UserModel userModel = new UserModel();
        try {
            userModel  = D.M(UserModel.class).where("username=?", username).find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        if (userModel != null) {
            hashMap.put("status", "success");
            hashMap.put("message", "获取成功");
            hashMap.put("data", userModel);
        } else {
            hashMap.put("status", "error");
            hashMap.put("message", "未找到登录用户~");
        }
        return hashMap;
    }
}
