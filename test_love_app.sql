-- MySQL dump 10.13  Distrib 5.7.40, for Linux (x86_64)
--
-- Host: localhost    Database: test_love_app
-- ------------------------------------------------------
-- Server version	5.7.40-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `t_album`
--

DROP TABLE IF EXISTS `t_album`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_album` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `details` varchar(255) NOT NULL,
  `resources_uuid` varchar(255) NOT NULL,
  `create_time` bigint(13) NOT NULL,
  `update_time` bigint(13) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_album`
--

LOCK TABLES `t_album` WRITE;
/*!40000 ALTER TABLE `t_album` DISABLE KEYS */;
INSERT INTO `t_album` VALUES (18,2,'皮卡丘','哈哈哈哈','92c1733e-5224-4f71-b381-57b9b72ba1d7',1650204933,1650204933),(19,2,'大西北','大西北牛肉面','280197e2-9f5c-4563-a05e-e68d39fdb9d8',1650205437,1650205437),(20,2,'阳春三月','桃花','8878547d-674f-4fdc-b2ea-2bfcd9a96a46',1650206225,1650206225),(21,2,'ugg','tghu','549eb4a2-e649-4d9e-b68e-9f0d656f85ce',1650276661,1650276661),(22,2,'cat','maomi','75ed6674-41ab-492c-985d-e79a6e3e0ba8',1650614863,1650614863),(23,2,'cat','maomi','f50d68bc-163e-4a37-9867-f96823e8148c',1650614865,1650614865),(24,2,'323','2322','f791d7f9-3c3e-485d-8469-a762e8077eb2',1650952257,1650952257),(25,3,'gff','ccc','937e3a1d-9d7d-4460-9b9d-6116c8e7fabf',1661303999,1661303999),(26,3,'首页轮播','描述','28ed3f9e-8ed1-417c-9eb1-6a55bcc73b47',1661395200,1661395200),(27,3,'轮播图片','love','47fd9673-dc57-4cd6-a31c-8c76a13b255d',1661395432,1661395432);
/*!40000 ALTER TABLE `t_album` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_author`
--

DROP TABLE IF EXISTS `t_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_author` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `real_name` varchar(32) NOT NULL COMMENT '用户名称',
  `nick_name` varchar(32) NOT NULL COMMENT '用户匿名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_author`
--

LOCK TABLES `t_author` WRITE;
/*!40000 ALTER TABLE `t_author` DISABLE KEYS */;
INSERT INTO `t_author` VALUES (2,'莫言','疯子'),(3,'莫言','疯子');
/*!40000 ALTER TABLE `t_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_bookeep`
--

DROP TABLE IF EXISTS `t_bookeep`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_bookeep` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `type` enum('expenditure','income') NOT NULL DEFAULT 'expenditure' COMMENT 'type(支出: expenditure/收入: income)',
  `total` decimal(10,2) NOT NULL COMMENT '金额',
  `classify` varchar(255) NOT NULL COMMENT '分类',
  `details` varchar(255) DEFAULT NULL,
  `year` int(2) NOT NULL COMMENT '年',
  `month` int(2) NOT NULL COMMENT '月',
  `day` int(2) NOT NULL COMMENT '日',
  `create_time` bigint(11) NOT NULL,
  `update_time` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_bookeep`
--

LOCK TABLES `t_bookeep` WRITE;
/*!40000 ALTER TABLE `t_bookeep` DISABLE KEYS */;
INSERT INTO `t_bookeep` VALUES (5,2,'expenditure',234.00,'餐饮5','吃炸鸡5',2022,2,1,1647436932,1647436932),(6,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,2,1,1647436932,1649601101),(7,2,'income',232.00,'餐饮7','吃炸鸡7',2022,2,10,1647436932,1647436932),(11,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,3,9,1649605429,1649605429),(10,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,3,9,1649605428,1649605428),(12,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,3,5,1649605464,1649605464),(13,2,'expenditure',234.00,'餐饮5','吃炸鸡5',2022,3,5,1647436932,1647436932),(14,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,4,10,1647436932,1649601101),(15,2,'income',232.00,'餐饮7','吃炸鸡7',2022,4,10,1647436932,1647436932),(16,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,4,9,1649605429,1649605429),(17,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,4,9,1649605428,1649605428),(18,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,4,10,1649605464,1649605464),(19,2,'expenditure',100.01,'看电影','和妹妹去看电影',2022,4,10,1650093761,1650093761),(20,2,'expenditure',90.00,'餐饮','xssdfsfdfs',2022,4,16,1650095976,1650095976),(21,2,'income',254.00,'工资','546546',2022,4,16,1650096015,1650096015),(22,2,'expenditure',32432.00,'房租','342432',2022,4,16,1650097338,1650097338),(23,2,'expenditure',23432.00,'餐饮','32432',2022,4,16,1650098488,1650098488),(24,2,'income',250.00,'工资','天天头发',2022,4,17,1650205479,1650205479),(25,2,'income',250.00,'工资','天天头发',2022,4,17,1650205479,1650205479),(26,2,'expenditure',1400.00,'房租','',2022,4,22,1650613499,1650613499),(27,2,'income',100.00,'工资','',2022,4,26,1650952106,1650952106),(28,3,'income',25.00,'工资','hhh',2022,8,25,1661395320,1661395320);
/*!40000 ALTER TABLE `t_bookeep` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_date_reminder`
--

DROP TABLE IF EXISTS `t_date_reminder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_date_reminder` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `type` int(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `message_day` date NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_date_reminder`
--

LOCK TABLES `t_date_reminder` WRITE;
/*!40000 ALTER TABLE `t_date_reminder` DISABLE KEYS */;
INSERT INTO `t_date_reminder` VALUES (1,2,0,'欧阳111','1998-08-13','2022-03-23',1648219841,1649500033),(2,2,0,'张月','1996-07-13','2022-03-24',0,0),(3,2,0,'贺宗涛','1995-04-28','2022-02-23',0,0),(7,2,0,'欧阳','1998-08-12','2022-03-25',1648219841,1648219841),(8,2,1,'3243','2022-03-25','2022-03-25',1648223685,1648223685),(9,2,1,'dgdg','1995-03-25','2022-03-22',1648223831,1648223831),(10,2,1,'love','2010-03-25','2030-01-01',1648224307,1648224307),(11,2,1,'love','2010-03-25','2030-01-01',1648224406,1648224406),(12,2,1,'gdd','2022-03-25','2022-03-25',1648224707,1648224707),(13,2,1,'tfu','2022-03-26','2022-03-26',1648263515,1648263515),(14,2,0,'欧阳','1998-08-12','2022-03-25',1649498294,1649498294);
/*!40000 ALTER TABLE `t_date_reminder` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_options`
--

DROP TABLE IF EXISTS `t_options`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_options` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `type` varchar(255) NOT NULL,
  `label` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_options`
--

LOCK TABLES `t_options` WRITE;
/*!40000 ALTER TABLE `t_options` DISABLE KEYS */;
INSERT INTO `t_options` VALUES (1,'bookeep_type','收入','income'),(2,'bookeep_type','支出','expenditure'),(3,'income','工资','工资'),(4,'income','股票','股票'),(5,'income','彩票','彩票'),(6,'income','分红','分红'),(7,'expenditure','餐饮','餐饮'),(8,'expenditure','恋爱','恋爱'),(9,'expenditure','房租','房租'),(10,'expenditure','水电','水电');
/*!40000 ALTER TABLE `t_options` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_order`
--

DROP TABLE IF EXISTS `t_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` bigint(20) NOT NULL,
  `gid` bigint(20) NOT NULL,
  `price` decimal(10,2) NOT NULL,
  `count` bigint(20) NOT NULL,
  `total` decimal(10,2) NOT NULL,
  `pay_status` varchar(255) NOT NULL,
  `order_status` varchar(255) NOT NULL,
  `create_time` bigint(13) NOT NULL,
  `update_time` bigint(13) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_order`
--

LOCK TABLES `t_order` WRITE;
/*!40000 ALTER TABLE `t_order` DISABLE KEYS */;
INSERT INTO `t_order` VALUES (1,1,11,12.31,10,123.10,'success','success',0,0);
/*!40000 ALTER TABLE `t_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_resources`
--

DROP TABLE IF EXISTS `t_resources`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_resources` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `uuid` varchar(255) NOT NULL,
  `original_name` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  `url_path` varchar(255) NOT NULL,
  `path` varchar(255) NOT NULL,
  `ext` varchar(255) NOT NULL,
  `create_time` bigint(13) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_resources`
--

LOCK TABLES `t_resources` WRITE;
/*!40000 ALTER TABLE `t_resources` DISABLE KEYS */;
INSERT INTO `t_resources` VALUES (33,2,'af0b1c5d-55cd-4b0d-806f-02552853bccb','img_1650204919799.jpg','4d910bd4a447e31dca3afab9e4270496.jpg','/upload/2022/04/17/4d910bd4a447e31dca3afab9e4270496.jpg','static/upload/2022/04/17/4d910bd4a447e31dca3afab9e4270496.jpg','.jpg',1650204921),(34,2,'92c1733e-5224-4f71-b381-57b9b72ba1d7','img_1650204926620.jpg','5886fa9eed8a9ba38e27e4600c793ff6.jpg','/upload/2022/04/17/5886fa9eed8a9ba38e27e4600c793ff6.jpg','static/upload/2022/04/17/5886fa9eed8a9ba38e27e4600c793ff6.jpg','.jpg',1650204928),(35,2,'280197e2-9f5c-4563-a05e-e68d39fdb9d8','img_1650205415576.jpg','264609e8205dd25fbb0f909d64708744.jpg','/upload/2022/04/17/264609e8205dd25fbb0f909d64708744.jpg','static/upload/2022/04/17/264609e8205dd25fbb0f909d64708744.jpg','.jpg',1650205417),(36,2,'8878547d-674f-4fdc-b2ea-2bfcd9a96a46','img_1650206212142.jpg','9b249da36dd9cc6ef0ee2c5e8770bf35.jpg','/upload/2022/04/17/9b249da36dd9cc6ef0ee2c5e8770bf35.jpg','static/upload/2022/04/17/9b249da36dd9cc6ef0ee2c5e8770bf35.jpg','.jpg',1650206214),(37,2,'549eb4a2-e649-4d9e-b68e-9f0d656f85ce','img_1650276658873.jpg','4a6407390ce51474e41097c604fccf19.jpg','/upload/2022/04/18/4a6407390ce51474e41097c604fccf19.jpg','static/upload/2022/04/18/4a6407390ce51474e41097c604fccf19.jpg','.jpg',1650276660),(38,2,'f50d68bc-163e-4a37-9867-f96823e8148c','img_1650614802827.jpg','e3e9d7bafc3c381d3bc35231d7ac1d72.jpg','/upload/2022/04/22/e3e9d7bafc3c381d3bc35231d7ac1d72.jpg','static/upload/2022/04/22/e3e9d7bafc3c381d3bc35231d7ac1d72.jpg','.jpg',1650614806),(39,2,'75ed6674-41ab-492c-985d-e79a6e3e0ba8','img_1650614847081.jpg','352a5950ac1e8197a35d18dec566fe9f.jpg','/upload/2022/04/22/352a5950ac1e8197a35d18dec566fe9f.jpg','static/upload/2022/04/22/352a5950ac1e8197a35d18dec566fe9f.jpg','.jpg',1650614861),(40,2,'35a6fe52-470f-4067-8481-b28bf94115d1','img_1650782900827.jpg','b2189b96f0e1263acc5e77ef561180bb.jpg','/upload/2022/04/24/b2189b96f0e1263acc5e77ef561180bb.jpg','static/upload/2022/04/24/b2189b96f0e1263acc5e77ef561180bb.jpg','.jpg',1650782904),(41,2,'a0327d21-edac-4fb5-a567-65d79a7e202a','img_1650786082687.jpg','664fc495ac1d8fc32f272bfe561286cd.jpg','/upload/2022/04/24/664fc495ac1d8fc32f272bfe561286cd.jpg','static/upload/2022/04/24/664fc495ac1d8fc32f272bfe561286cd.jpg','.jpg',1650786086),(42,2,'92925d9b-8c3f-43fd-bff4-97bf9b41203e','img_1650786894100.jpg','3465095995e419089dc87bef737de3bd.jpg','/upload/2022/04/24/3465095995e419089dc87bef737de3bd.jpg','static/upload/2022/04/24/3465095995e419089dc87bef737de3bd.jpg','.jpg',1650786903),(43,2,'f359fdc8-d78d-417f-a754-4f1269be3fc3','img_1650941513079.jpg','6c879f7ca63e7b077eee2149471021c4.jpg','/upload/2022/04/26/6c879f7ca63e7b077eee2149471021c4.jpg','static/upload/2022/04/26/6c879f7ca63e7b077eee2149471021c4.jpg','.jpg',1650941516),(44,2,'f791d7f9-3c3e-485d-8469-a762e8077eb2','img_1650952241620.jpg','2372fc3b8396b48d43c486ff3bef4a15.jpg','/upload/2022/04/26/2372fc3b8396b48d43c486ff3bef4a15.jpg','static/upload/2022/04/26/2372fc3b8396b48d43c486ff3bef4a15.jpg','.jpg',1650952245),(45,2,'5525edfe-ceb2-4461-952f-1f066746feee','img_1651024779447.jpg','e30fb99619cf2713a5e8a479c2ccf735.jpg','/upload/2022/04/27/e30fb99619cf2713a5e8a479c2ccf735.jpg','static/upload/2022/04/27/e30fb99619cf2713a5e8a479c2ccf735.jpg','.jpg',1651024779),(46,2,'ffe7e055-2243-45b6-a1f3-a7d5210a3503','img_1651190799623.jpg','377ede0ae705ed50a35efb85718de310.jpg','/upload/2022/04/29/377ede0ae705ed50a35efb85718de310.jpg','static/upload/2022/04/29/377ede0ae705ed50a35efb85718de310.jpg','.jpg',1651190799),(47,3,'937e3a1d-9d7d-4460-9b9d-6116c8e7fabf','img_1661303997605.jpg','1c8af677102cb4999e325f8ae5ade79e.jpg','/upload/2022/08/24/1c8af677102cb4999e325f8ae5ade79e.jpg','static/upload/2022/08/24/1c8af677102cb4999e325f8ae5ade79e.jpg','.jpg',1661303998),(48,3,'e6279009-02fc-4e40-925b-55ddae0a7ca0','img_1661304079396.jpg','ee294dfea7b6b7f604e6b5647b490658.jpg','/upload/2022/08/24/ee294dfea7b6b7f604e6b5647b490658.jpg','static/upload/2022/08/24/ee294dfea7b6b7f604e6b5647b490658.jpg','.jpg',1661304079),(49,3,'28ed3f9e-8ed1-417c-9eb1-6a55bcc73b47','img_1661395188555.jpg','050d9491bed6d6ad00beec0f31ae41e0.jpg','/upload/2022/08/25/050d9491bed6d6ad00beec0f31ae41e0.jpg','static/upload/2022/08/25/050d9491bed6d6ad00beec0f31ae41e0.jpg','.jpg',1661395188),(50,3,'47fd9673-dc57-4cd6-a31c-8c76a13b255d','img_1661395422358.jpg','34ee2aea01944e59c7447a66542c5e74.jpg','/upload/2022/08/25/34ee2aea01944e59c7447a66542c5e74.jpg','static/upload/2022/08/25/34ee2aea01944e59c7447a66542c5e74.jpg','.jpg',1661395422);
/*!40000 ALTER TABLE `t_resources` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `t_user`
--

DROP TABLE IF EXISTS `t_user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `email` varchar(32) NOT NULL COMMENT '邮箱',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `phone` bigint(20) DEFAULT NULL,
  `money` decimal(10,2) NOT NULL,
  `gender` int(1) NOT NULL COMMENT '性别',
  `birthday` varchar(10) NOT NULL COMMENT '生日',
  `solar` int(1) NOT NULL COMMENT '出生日期属性（0=阳历，1=阴历）',
  `love_date` varchar(0) DEFAULT NULL COMMENT '恋爱日期',
  `vip_date` varchar(0) DEFAULT NULL,
  `love_uid` bigint(20) DEFAULT NULL COMMENT '对象ID',
  `date` varchar(20) DEFAULT NULL,
  `create_time` bigint(12) NOT NULL COMMENT '创建时间',
  `update_time` bigint(12) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `t_user`
--

LOCK TABLES `t_user` WRITE;
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` VALUES (1,'dgjj','独孤九剑','zhangsan@outlook.com','e10adc3949ba59abbe56e057f20f883e',0,5000.00,0,'',0,'','',0,'2022-03-15',1649056831,1649056831),(2,'xsyl','萧十一郎','lishi@outlook.com','e10adc3949ba59abbe56e057f20f883e',0,5000.00,0,'',0,'','',0,'2022-03-15',1649691228,1649691228),(3,'dnqxz','独孤九剑','','e10adc3949ba59abbe56e057f20f883e',0,0.00,1,'2000-01-01',1,NULL,NULL,NULL,NULL,1649691228,1649691228),(4,'dnqxz1','独孤九剑','','e10adc3949ba59abbe56e057f20f883e',0,0.00,1,'2000-01-01',1,NULL,NULL,NULL,NULL,1649691228,1649691228),(5,'dnqxz2','独孤九剑','','e10adc3949ba59abbe56e057f20f883e',0,0.00,1,'2000-01-01',1,NULL,NULL,NULL,NULL,1649691228,1649691228);
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'test_love_app'
--

--
-- Dumping routines for database 'test_love_app'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-09-14 23:39:34
