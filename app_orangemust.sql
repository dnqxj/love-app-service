/*
Navicat MySQL Data Transfer

Source Server         : app_orangemust
Source Server Version : 50650
Source Host           : 123.60.133.167:3306
Source Database       : app_orangemust

Target Server Type    : MYSQL
Target Server Version : 50650
File Encoding         : 65001

Date: 2022-03-27 20:46:15
*/

SET FOREIGN_KEY_CHECKS=0;

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
-- Table structure for t_bookkeeping
-- ----------------------------
DROP TABLE IF EXISTS `t_bookkeeping`;
CREATE TABLE `t_bookkeeping` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `income` decimal(10,2) NOT NULL,
  `expenditure` decimal(10,2) NOT NULL,
  `name` varchar(255) NOT NULL,
  `desc` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `create_time` bigint(11) NOT NULL,
  `update_time` bigint(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_bookkeeping
-- ----------------------------
INSERT INTO `t_bookkeeping` VALUES ('5', '1', '186.00', '234.00', '餐饮5', '吃炸鸡5', '2022-03-14', '1647436932', '1647436932');
INSERT INTO `t_bookkeeping` VALUES ('6', '1', '288.00', '567.00', '餐饮6', '吃炸鸡6', '2022-03-15', '1647436932', '1647436932');
INSERT INTO `t_bookkeeping` VALUES ('7', '1', '7342.00', '232.00', '餐饮7', '吃炸鸡7', '2022-03-16', '1647436932', '1647436932');

-- ----------------------------
-- Table structure for t_love_date
-- ----------------------------
DROP TABLE IF EXISTS `t_love_date`;
CREATE TABLE `t_love_date` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `uid` int(10) NOT NULL,
  `type` int(1) NOT NULL,
  `name` varchar(255) NOT NULL,
  `date` date NOT NULL,
  `message_day` date NOT NULL,
  `create_time` bigint(20) NOT NULL,
  `update_time` bigint(20) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_love_date
-- ----------------------------
INSERT INTO `t_love_date` VALUES ('1', '1', '0', '陈新', '1995-03-24', '2022-03-07', '0', '0');
INSERT INTO `t_love_date` VALUES ('2', '1', '0', '张月', '1996-07-13', '2022-03-24', '0', '0');
INSERT INTO `t_love_date` VALUES ('3', '1', '0', '贺宗涛', '1995-04-28', '2022-02-23', '0', '0');
INSERT INTO `t_love_date` VALUES ('7', '1', '0', '欧阳', '1998-08-12', '2022-03-25', '1648219841', '1648219841');
INSERT INTO `t_love_date` VALUES ('8', '1', '1', '3243', '2022-03-25', '2022-03-25', '1648223685', '1648223685');
INSERT INTO `t_love_date` VALUES ('9', '1', '1', 'dgdg', '1995-03-25', '2022-03-22', '1648223831', '1648223831');
INSERT INTO `t_love_date` VALUES ('10', '1', '1', 'love', '2010-03-25', '2030-01-01', '1648224307', '1648224307');
INSERT INTO `t_love_date` VALUES ('11', '1', '1', 'love', '2010-03-25', '2030-01-01', '1648224406', '1648224406');
INSERT INTO `t_love_date` VALUES ('12', '1', '1', 'gdd', '2022-03-25', '2022-03-25', '1648224707', '1648224707');
INSERT INTO `t_love_date` VALUES ('13', '1', '1', 'tfu', '2022-03-26', '2022-03-26', '1648263515', '1648263515');

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
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
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `username` varchar(32) NOT NULL COMMENT '用户',
  `name` varchar(255) NOT NULL COMMENT '姓名',
  `email` varchar(32) NOT NULL COMMENT '邮箱',
  `password` varchar(32) NOT NULL COMMENT '密码',
  `phone` bigint(20) NOT NULL,
  `money` decimal(10,2) NOT NULL,
  `gender` int(1) NOT NULL COMMENT '性别',
  `birthday` varchar(0) NOT NULL COMMENT '生日',
  `solar` int(1) NOT NULL COMMENT '出生日期属性（0=阳历，1=阴历）',
  `love_date` varchar(0) NOT NULL COMMENT '恋爱日期',
  `vip_date` varchar(0) NOT NULL,
  `love_uid` bigint(20) NOT NULL COMMENT '对象ID',
  `date` varchar(20) NOT NULL,
  `create_time` bigint(12) NOT NULL COMMENT '创建时间',
  `update_time` bigint(12) NOT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'dgjj', '独孤九剑', 'zhangsan@outlook.com', 'e10adc3949ba59abbe56e057f20f883e', '0', '5000.00', '0', '', '0', '', '', '0', '2022-03-15', '1645174831395', '1645175613675');
INSERT INTO `t_user` VALUES ('2', 'xsyl', '萧十一郎', 'lishi@outlook.com', 'e10adc3949ba59abbe56e057f20f883e', '0', '5000.00', '0', '', '0', '', '', '0', '2022-03-15', '1645175031537', '0');
