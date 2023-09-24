package com.orangemust.core.utils;

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
     *
     * @return String UUID
     */
    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        // 去掉“-”符号
        // return uuid.replaceAll("-", "");
        return uuid;
    }

    /**
     * 获取精确到秒的时间戳
     */
    public static Long getTimeStamp() {
        return Long.valueOf(System.currentTimeMillis() / 1000);
    }

    /**
     * 获取精确到秒的时间戳
     */
    public static Double twoDouble(Double value) {
        long l1 = Math.round(value * 100); // 四舍五入
        double ret = l1 / 100.0; // 注意：使用 100.0 而不是 100
        return ret;
    }
}
