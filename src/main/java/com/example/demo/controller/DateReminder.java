package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.model.LoveDateModel;
import com.llqqww.thinkjdbc.D;
import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Api(tags = "日期提醒")
@RestController
@RequestMapping("/love")
public class DateReminder {

    @GetMapping(path = "date_list")
    public HashMap<String, Object> dateList() {
        List<LoveDateModel> list = new ArrayList<>();
        try {
            int uid = 1;
            list = D.M(LoveDateModel.class).where("uid=?", uid).select();
            System.out.println(list);
        } catch(Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        hashMap.put("message", "获取成功");
        hashMap.put("data", list);
        return hashMap;
    }

    @PostMapping(path = "add_date")
    public HashMap<String, Object> addDate(@Validated @RequestBody LoveDateModel loveDateModel){
        Long timeStamp = getSecondTimestamp(new Date());
        loveDateModel.setCreateTime(timeStamp);
        loveDateModel.setUpdateTime(timeStamp);
        System.out.println(JSON.toJSONString(loveDateModel));
//        插入数据库
        try {
            D.M(loveDateModel).add();
        } catch (Exception e) {
            e.printStackTrace();
        }

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        hashMap.put("message", "新增成功");
        return hashMap;
    }

    /**
     * 获取精确到秒的时间戳
     * @param date
     * @return
     */
    public static Long getSecondTimestamp(Date date){
        if (null == date) {
            return 0L;
        }
        String timestamp = String.valueOf(date.getTime()/1000);
        return Long.valueOf(timestamp);
    }
}
