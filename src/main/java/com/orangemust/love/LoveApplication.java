package com.orangemust.love;

import com.llqqww.thinkjdbc.D;
import java.util.ResourceBundle;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LoveApplication {

    public static void main(String[] args) {
        databaseInit();
        SpringApplication.run(LoveApplication.class, args);
    }

    /**
     * 数据库连接初始化
     */
    public static void databaseInit() {
        try {
            ResourceBundle resourceBundle = ResourceBundle.getBundle("application");
            final String JDBC_URL = resourceBundle.getString("jdbcUrl");
            final String DB_USER = resourceBundle.getString("dataSource.user");
            final String DB_PASSWORD = resourceBundle.getString("dataSource.password");
            D.setDbConfig(JDBC_URL, DB_USER, DB_PASSWORD);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
