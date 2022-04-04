package com.example.demo.controller;

import com.example.demo.model.UserModel;
import com.example.demo.response.Result;
import com.example.demo.validate.LoginReqVo;
import com.example.demo.validate.RegisterReqVo;
import com.llqqww.thinkjdbc.D;
import io.swagger.annotations.*;
import org.springframework.util.DigestUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import com.example.demo.utils.JwtUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Api(tags = "用户管理")
@RequestMapping(path = "/user")
@RestController
public class User {

    @ApiOperation(value = "用户登录", notes = "用户使用用户名和密码登录", httpMethod = "POST")
    @PostMapping("login")
    public Result login(
            @ApiParam(name="传入对象",value="传入json格式",required=true)
            @Validated @RequestBody LoginReqVo loginReqVo,
            HttpServletResponse httpServletResponse) {
        String username = loginReqVo.getUsername();
        String password = loginReqVo.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        UserModel userModel = new UserModel();
        try {
            userModel  = D.M(UserModel.class).where("username=? and password=?", username, password).find();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        if (userModel != null) {
            String token = JwtUtil.createToken(userModel);
            httpServletResponse.addHeader("Access-Control-Expose-Headers", "Authorization");
            httpServletResponse.setHeader("Authorization", token);
            HashMap<String, Object> dataHashMap = new HashMap<>();
            dataHashMap.put("user", userModel);
            dataHashMap.put("token", token);
            hashMap.put("data", dataHashMap);
            return Result.ok().data(dataHashMap);
        } else {
            return Result.error().message("用户名或密码错误~");
        }
    }

    @ApiOperation(value = "用户注册", notes = "用户填写必要信息注册", httpMethod = "POST")
    @PostMapping("register")
    public Result register(
            @ApiParam(name="传入对象",value="传入json格式",required=true)
            @Validated @RequestBody RegisterReqVo registerReqVo) {
        String username = registerReqVo.getUsername();
        // 检查用户名是否注册
        UserModel userModel = new UserModel();
        try {
            userModel  = D.M(UserModel.class).where("username=?", username).find();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(userModel != null) {
            return Result.error().message("用户名已注册");
        }

        Long timestamp = this.getTimeStamp();
        String password = registerReqVo.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        UserModel newUserModel = new UserModel();
        newUserModel.setUsername(registerReqVo.getUsername());
        newUserModel.setPassword(password);
        newUserModel.setEmail("");
        newUserModel.setPhone(0L);
        newUserModel.setMoney(0.0);
        newUserModel.setName(registerReqVo.getName());
        newUserModel.setBirthday(registerReqVo.getBirthday());
        newUserModel.setGender(registerReqVo.getGender());
        newUserModel.setSolar(registerReqVo.getSolar());
        newUserModel.setCreateTime(timestamp);
        newUserModel.setUpdateTime(timestamp);
        long id = 0;
        try {
            id = D.M(newUserModel).add();
        } catch (Exception e) {
            e.printStackTrace();
        }
        if(id != 0) {
            return Result.ok().message("注册成功");
        } else {
            return Result.error().message("注册失败");
        }
    }


    @ApiOperation(value = "获取用户信息", notes = "获取用户详情，通过携带的token", httpMethod = "GET")
    @ApiResponses({
            @ApiResponse(code = 200,message = "OK",response = RegisterReqVo.class),
    })
    @GetMapping(path = "getUserInfo")
    public Result getUserInfo(HttpServletRequest request) {
//  接收header中的token，进行解析查询
        Object userId = request.getAttribute("userId");
        UserModel userModel = new UserModel();
        try {
            userModel  = D.M(UserModel.class).where("id=?", userId).find();
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (userModel != null) {
            HashMap<String, Object> hashMap = new HashMap<>();
            hashMap.put("user", userModel);
            return Result.ok().data(hashMap);
        } else {
            return Result.ok().message("获取用户数据失败");
        }

    }


    /**
     * 获取精确到秒的时间戳
     */
    public static Long getTimeStamp(){
        return Long.valueOf(System.currentTimeMillis()/1000);
    }
}