package com.example.demo.utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Random;
import java.util.UUID;

/**
 * 公共函数库
 */
public class FuncUtil {

    public static Long getUid(HttpServletRequest request) {
        Object userId = request.getAttribute("userId");
        Long uid = Long.valueOf(String.valueOf(userId));
        return uid;
    }

    /**
     * 获得一个UUID
     * @return String UUID
     */
    public static String getUUID(){
        String uuid = UUID.randomUUID().toString();
        //去掉“-”符号
//        return uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 获得一个随机字符串
     * @return String str
     */
    public static String getRandomString(int length){
        String str="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random= new Random();
        StringBuffer sb=new StringBuffer();
        for(int i=0;i<length;i++){
            int number=random.nextInt(62);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 获取精确到秒的时间戳
     */
    public static Long getTimeStamp(){
        return Long.valueOf(System.currentTimeMillis()/1000);
    }
}
