/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : app_orangemust

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2022-04-15 23:56:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_album
-- ----------------------------
DROP TABLE IF EXISTS `t_album`;
CREATE TABLE `t_album` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `name` varchar(255) NOT NULL,
  `details` varchar(255) NOT NULL,
  `resources_uuid` varchar(255) NOT NULL,
  `create_time` bigint(13) NOT NULL,
  `update_time` bigint(13) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_album
-- ----------------------------
INSERT INTO `t_album` VALUES ('10', '2', '出游', '出游描述', 'fbc07da0-872f-42f3-bea7-091da52eb48b', '1649496825', '1649496825');
INSERT INTO `t_album` VALUES ('9', '2', '出游', '出游描述', 'fbc07da0-872f-42f3-bea7-091da52eb48b', '1649496042', '1649496042');
INSERT INTO `t_album` VALUES ('11', '2', '出游', '出游描述', 'fbc07da0-872f-42f3-bea7-091da52eb48b', '1649496826', '1649496826');
INSERT INTO `t_album` VALUES ('12', '2', '出游', '出游描述', 'fbc07da0-872f-42f3-bea7-091da52eb48b', '1649496828', '1649496828');

-- ----------------------------
-- Table structure for t_author
-- ----------------------------
DROP TABLE IF EXISTS `t_author`;
CREATE TABLE `t_author` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `real_name` varchar(32) NOT NULL COMMENT '用户名称',
  `nick_name` varchar(32) NOT NULL COMMENT '用户匿名',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_author
-- ----------------------------
INSERT INTO `t_author` VALUES ('2', '莫言', '疯子');
INSERT INTO `t_author` VALUES ('3', '莫言', '疯子');

-- ----------------------------
-- Table structure for t_bookeep
-- ----------------------------
DROP TABLE IF EXISTS `t_bookeep`;
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
) ENGINE=MyISAM AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bookeep
-- ----------------------------
INSERT INTO `t_bookeep` VALUES ('5', '2', 'expenditure', '234.00', '餐饮5', '吃炸鸡5', '2022', '4', '10', '1647436932', '1647436932');
INSERT INTO `t_bookeep` VALUES ('6', '2', 'expenditure', '100.01', '看电影', '和妹妹去看电影', '2022', '4', '10', '1647436932', '1649601101');
INSERT INTO `t_bookeep` VALUES ('7', '2', 'income', '232.00', '餐饮7', '吃炸鸡7', '2022', '4', '10', '1647436932', '1647436932');
INSERT INTO `t_bookeep` VALUES ('11', '2', 'expenditure', '100.01', '看电影', '和妹妹去看电影', '2022', '4', '9', '1649605429', '1649605429');
INSERT INTO `t_bookeep` VALUES ('10', '2', 'expenditure', '100.01', '看电影', '和妹妹去看电影', '2022', '4', '9', '1649605428', '1649605428');
INSERT INTO `t_bookeep` VALUES ('12', '2', 'expenditure', '100.01', '看电影', '和妹妹去看电影', '2022', '4', '10', '1649605464', '1649605464');

-- ----------------------------
-- Table structure for t_date_reminder
-- ----------------------------
DROP TABLE IF EXISTS `t_date_reminder`;
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

-- ----------------------------
-- Records of t_date_reminder
-- ----------------------------
INSERT INTO `t_date_reminder` VALUES ('1', '2', '0', '欧阳111', '1998-08-13', '2022-03-23', '1648219841', '1649500033');
INSERT INTO `t_date_reminder` VALUES ('2', '2', '0', '张月', '1996-07-13', '2022-03-24', '0', '0');
INSERT INTO `t_date_reminder` VALUES ('3', '2', '0', '贺宗涛', '1995-04-28', '2022-02-23', '0', '0');
INSERT INTO `t_date_reminder` VALUES ('7', '2', '0', '欧阳', '1998-08-12', '2022-03-25', '1648219841', '1648219841');
INSERT INTO `t_date_reminder` VALUES ('8', '2', '1', '3243', '2022-03-25', '2022-03-25', '1648223685', '1648223685');
INSERT INTO `t_date_reminder` VALUES ('9', '2', '1', 'dgdg', '1995-03-25', '2022-03-22', '1648223831', '1648223831');
INSERT INTO `t_date_reminder` VALUES ('10', '2', '1', 'love', '2010-03-25', '2030-01-01', '1648224307', '1648224307');
INSERT INTO `t_date_reminder` VALUES ('11', '2', '1', 'love', '2010-03-25', '2030-01-01', '1648224406', '1648224406');
INSERT INTO `t_date_reminder` VALUES ('12', '2', '1', 'gdd', '2022-03-25', '2022-03-25', '1648224707', '1648224707');
INSERT INTO `t_date_reminder` VALUES ('13', '2', '1', 'tfu', '2022-03-26', '2022-03-26', '1648263515', '1648263515');
INSERT INTO `t_date_reminder` VALUES ('14', '2', '0', '欧阳', '1998-08-12', '2022-03-25', '1649498294', '1649498294');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
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

-- ----------------------------
-- Records of t_order
-- ----------------------------
INSERT INTO `t_order` VALUES ('1', '1', '11', '12.31', '10', '123.10', 'success', 'success', '0', '0');

-- ----------------------------
-- Table structure for t_resources
-- ----------------------------
DROP TABLE IF EXISTS `t_resources`;
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
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_resources
-- ----------------------------
INSERT INTO `t_resources` VALUES ('9', '2', '5b4f98d4-3d33-49e6-b5ab-95e5d61f2bb5', 'ltwzjxk2qdl.jpg', '4d0f94ceaca3ff8caf283ad2f06b000d.jpg', '/upload/2022/04/09/4d0f94ceaca3ff8caf283ad2f06b000d.jpg', 'static/upload/2022/04/09/4d0f94ceaca3ff8caf283ad2f06b000d.jpg', '.jpg', '1649490630');
INSERT INTO `t_resources` VALUES ('8', '2', 'fbc07da0-872f-42f3-bea7-091da52eb48b', 'n3trob0mdcw.jpg', '20f31ff023a2f6f31074d8a336d5e831.jpg', '/upload/2022/04/09/20f31ff023a2f6f31074d8a336d5e831.jpg', 'static/upload/2022/04/09/20f31ff023a2f6f31074d8a336d5e831.jpg', '.jpg', '1649489028');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
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

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'dgjj', '独孤九剑', 'zhangsan@outlook.com', 'e10adc3949ba59abbe56e057f20f883e', '0', '5000.00', '0', '', '0', '', '', '0', '2022-03-15', '1649056831', '1649056831');
INSERT INTO `t_user` VALUES ('2', 'xsyl', '萧十一郎', 'lishi@outlook.com', 'e10adc3949ba59abbe56e057f20f883e', '0', '5000.00', '0', '', '0', '', '', '0', '2022-03-15', '1649691228', '1649691228');
INSERT INTO `t_user` VALUES ('3', 'dnqxz', '独孤九剑', '', 'e10adc3949ba59abbe56e057f20f883e', '0', '0.00', '1', '2000-01-01', '1', null, null, null, null, '1649691228', '1649691228');
INSERT INTO `t_user` VALUES ('4', 'dnqxz1', '独孤九剑', '', 'e10adc3949ba59abbe56e057f20f883e', '0', '0.00', '1', '2000-01-01', '1', null, null, null, null, '1649691228', '1649691228');
INSERT INTO `t_user` VALUES ('5', 'dnqxz2', '独孤九剑', '', 'e10adc3949ba59abbe56e057f20f883e', '0', '0.00', '1', '2000-01-01', '1', null, null, null, null, '1649691228', '1649691228');
