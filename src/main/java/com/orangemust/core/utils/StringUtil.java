package com.orangemust.core.utils;

import org.springframework.util.DigestUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class StringUtil {

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
   * 获得一个随机字符串
   *
   * @return String str
   */
  public static String getRandomString(int length) {
    String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    Random random = new Random();
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < length; i++) {
      int number = random.nextInt(62);
      sb.append(str.charAt(number));
    }
    return sb.toString();
  }

  /**
   * 获取年月日目录字符串
   */
  public static String getYMDDirectory() {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd/");
    return simpleDateFormat.format(new Date());
  }

  /**
   * 获取md5的随机数
   */
  public static String getRandomMd5() {
    long timeMillis = System.currentTimeMillis();
    return DigestUtils.md5DigestAsHex(Long.toString(timeMillis).getBytes());
  }

  /**
   * 获取文件名后缀
   */
  public static String getExt(String filename) {
    return filename.substring(filename.lastIndexOf("."));
  }

  /**
   * 判断参数是否为存在（不为空，或空字符串）
   */
  public static boolean isNotEmpty(String str) {
    if (str == null || str.trim().isEmpty()) {
      return false;
    }
    return true;
  }

}
