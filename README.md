# test_app_java
测试flutter app后端

## api文档位置
本地启动后
http://localhost:8080/swagger-ui.html

安装
------
数据库配置文件位置：/src/main/java/com.example.demo/DemoApplication

1、创建mysql数据库
name: app_orangemust
username: app_orangemust
password: CkLGfTMTn8e4z2WZ
2、将根目录的sql文件导入数据库

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

线上运行地址
------
app.orangemust.com
/hello 可测试，将返回"Hello World"

日志
--------------
2022-3-27 一个demo版本的上传
2022-4-09 处理上传文件的路径和返回
2022-4-10 完成记账模块开发和API文档编写