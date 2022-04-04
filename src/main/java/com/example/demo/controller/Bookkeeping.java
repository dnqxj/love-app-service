package com.example.demo.controller;

import com.example.demo.model.BookkeepingModel;
import com.example.demo.model.KeepItemModel;
import com.example.demo.model.UserModel;
import com.llqqww.thinkjdbc.D;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Array;
import java.util.*;

@Api(tags = "记账模块")
@RestController
@RequestMapping("/bookkeeping")
public class Bookkeeping {

    @GetMapping(path = "/item")
    public HashMap<String, Object> item() {
//        默认用户1
        int uid = 1;
        List<BookkeepingModel> list = new ArrayList<>();
        try {
            list = D.M(BookkeepingModel.class).where("uid=?", uid).select();
        } catch (Exception e) {
            e.printStackTrace();
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        if (list.size() > 0) {
            List newList = new ArrayList<>();
            KeepItemModel keepItemModel = new KeepItemModel();

            keepItemModel.setName("工资");
            keepItemModel.setDesc("发工资咯");
            keepItemModel.setAction(1); // 支出
            keepItemModel.setMoney(2100.34);
            keepItemModel.setDate("2022-03-15");
            List<KeepItemModel> keepItemModelList = new ArrayList<KeepItemModel>();
            keepItemModelList.add(keepItemModel);
            keepItemModelList.add(keepItemModel);
            keepItemModelList.add(keepItemModel);
            for (int i = 0; i < list.size(); i++) {
                HashMap<String, Object> newHashMap = new HashMap<>();
                newHashMap.put("dayData", list.get(i));
                newHashMap.put("children", keepItemModelList);
                newList.add(newHashMap);
            }
//            该月的收支数据
            HashMap<String, Object> monthHashMap = new HashMap<>();
            monthHashMap.put("month", 3);
            monthHashMap.put("budget", 2000);
            monthHashMap.put("income", 453);
            monthHashMap.put("expenditure", 600);
            HashMap<String, Object> resultHashMap = new HashMap<>();
            resultHashMap.put("list", newList);
            resultHashMap.put("monthData", monthHashMap);

            hashMap.put("status", "success");
            hashMap.put("message", "获取成功");
            hashMap.put("data", resultHashMap);
        } else {
            List<String> nulList = new ArrayList<String>();
            HashMap<String, Object> nullMap = new HashMap<>();
            hashMap.put("status", "success");
            hashMap.put("message", "获取失败");
            hashMap.put("data", nulList);
        }
        return hashMap;
    }

    @GetMapping(path = "/model_list")
    public HashMap<String, Object> getModelList(Integer type) {
        List list = new ArrayList();
        if (type == 0) {
            list.add("长辈");
            list.add("小孩");
            list.add("女朋友");
        } else if (type == 1) {
            list.add("餐饮");
            list.add("出行");
            list.add("社交");
        }
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        hashMap.put("message", "获取成功");
        hashMap.put("data", list);
        return hashMap;
    }

    @PostMapping(path = "/add_item")
    public HashMap<String, Object> addItem() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        hashMap.put("message", "新增成功");
        return hashMap;
    }

    @PostMapping(path = "/update_file")
    public HashMap<String, Object> updateFile() {
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        hashMap.put("message", "上传成功");
        return hashMap;
    }

    @GetMapping(path = "/get_images")
    public HashMap<String, Object> getImages() {
        HashMap<String, Object> imageHashMap = new HashMap<>();
        imageHashMap.put("id", 1);
        imageHashMap.put("name", "海边图片");
        imageHashMap.put("url", "http://www-x-kmwhjj-x-cn.img.abc188.com/uploads/avatar/8f71357b9bcf4fc615f1beb1c96fdaa3.png");
        List<Map> list = new ArrayList<>();
        list.add(imageHashMap);
        list.add(imageHashMap);
        list.add(imageHashMap);
        list.add(imageHashMap);
        list.add(imageHashMap);

        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("status", "success");
        hashMap.put("message", "获取成功");
        hashMap.put("data", list);
        return hashMap;
    }


}