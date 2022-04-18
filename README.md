<!--
 * @Author: He Peng
 * @Date: 2022-04-18 08:43:00
 * @LastEditors: He Peng
 * @LastEditTime: 2022-04-18 08:54:15
 * @Descripttion:
-->

# test_app_java

flutter 恋爱 app，后端代码

# 直接使用接口

1、线上文档位置

http://app.orangemust.com/swagger-ui.html

2、线上服务器

- 域名：app.orangemust.com
- IP： 123.60.133.167
- 端口：8085，80

## 项目功能结构

- 用户管理
  - 登录
  - 注册
  - 用户基本信息
- 记账模块
  - 账务信息（按月搜索查询）
  - 新增账务
  - 删除账务
- 相册模块
  - 相册列表
  - 添加图片
  - 删除图片
- 日期提醒模块
  - 日期提醒列表
  - 日期新增
  - 日期删除

## api 文档位置

1、本地启动后

http://localhost:8080/swagger-ui.html

2、线上文档位置

http://app.orangemust.com/swagger-ui.html

## 本地安装

1、创建 mysql 数据库

name: app_orangemust

username: root

password: root

2、将根目录的 sql 文件导入数据库

数据库配置文件位置：/src/main/java/com.example.demo/DemoApplication

```java
    public static void main(String[] args) {
        try {
            // 测试
            D.setDbConfig("jdbc:mysql://127.0.0.1:3306/app_orangemust?useUnicode=true&characterEncoding=UTF-8&useSSL=false","root","root");
        } catch (Exception e) {
            e.printStackTrace();
        }
        SpringApplication.run(DemoApplication.class, args);
    }
```

## 日志

2022-3-27 一个 demo 版本的上传

2022-4-09 处理上传文件的路径和返回

2022-4-10 完成记账模块开发和 API 文档编写
