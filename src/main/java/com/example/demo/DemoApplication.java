package com.example.demo;

import com.llqqww.thinkjdbc.D;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {

    public static void main(String[] args) {
        try {
            // 线上
            D.setDbConfig("jdbc:mysql://127.0.0.1:3306/app_orangemust?useUnicode=true&characterEncoding=UTF-8&useSSL=false","app_orangemust","CkLGfTMTn8e4z2WZ");
            // 测试
//            D.setDbConfig("jdbc:mysql://127.0.0.1:3306/app_orangemust?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(DemoApplication.class, args);
    }

}
