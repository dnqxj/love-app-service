<!--
 * @Author: He Peng
 * @Date: 2022-04-18 08:43:00
 * @LastEditors: He Peng
 * @LastEditTime: 2022-04-18 09:20:04
 * @Descripttion:
-->

# love-app-service

小橙遇爱 APP 服务端

## 直接使用接口

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

## 项目启动

### 复制项目

```
git clone https://github.com/dnqxj/love-app-service.git
```

### 创建项目配置文件(本地)

本地实例文件为本地连接测试数据库配置

cp src/main/resources/application.properties.example src/main/resources/application.properties

i、将该配置文件内容写入到 src/main/resources/application.properties

ii、并修改 静态资源位置 为本机位置

### 启动服务

### api 文档位置

1、本地启动后

http://localhost:port/swagger-ui.html

2、线上文档位置

http://app.orangemust.com/swagger-ui.html

## 日志

[2022-3-27] 一个 demo 版本的上传

[2022-4-09] 处理上传文件的路径和返回

[2022-4-10] 完成记账模块开发和 API 文档编写
