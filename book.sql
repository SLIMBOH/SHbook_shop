/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : book

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 06/11/2021 22:51:19
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_book
-- ----------------------------
DROP TABLE IF EXISTS `t_book`;
CREATE TABLE `t_book` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `author` varchar(100) DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `sales` int DEFAULT NULL,
  `stock` int DEFAULT NULL,
  `img_path` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_book
-- ----------------------------
BEGIN;
INSERT INTO `t_book` VALUES (1, 'java从入门到放弃', '国哥', 80.00, 10000, 8, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (2, '数据结构与算法', '严敏君', 78.50, 8, 11, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (5, 'C++编程思想', '刚哥', 45.50, 17, 92, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (8, 'Java编程思想', '阳哥', 99.50, 50, 33, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (9, 'JavaScript从入门到精通', '婷姐', 9.90, 85, 95, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (10, 'cocos2d-x游戏编程入门', '国哥', 49.00, 52, 62, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (11, 'C语言程序设计', '谭浩强', 28.00, 52, 74, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (12, 'Lua语言程序设计', '雷丰阳', 51.50, 48, 82, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (15, '操作系统原理', '刘优', 133.05, 122, 188, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (16, '数据结构 java版', '封大神', 173.15, 21, 81, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (17, 'UNIX高级环境编程', '乐天', 99.15, 210, 810, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (18, 'javaScript高级编程', '国哥', 69.15, 210, 810, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (19, '大话设计模式', '国哥', 89.15, 20, 10, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (20, '人月神话', '刚哥', 88.15, 20, 80, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (21, '天使の囀り', '1911', 5000.00, 200000, 0, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (22, 'Dark Souls', 'FromSoftware', 59.99, 5000000, 10000, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (24, 'Bloodborne', 'FromSoftware', 59.99, 3000000, 50, 'static/img/default.jpg');
INSERT INTO `t_book` VALUES (27, 'test2', 'sun', 59.99, 5000000, 10000, 'static/img/default.jpg');
COMMIT;

-- ----------------------------
-- Table structure for t_order
-- ----------------------------
DROP TABLE IF EXISTS `t_order`;
CREATE TABLE `t_order` (
  `order_id` varchar(50) NOT NULL,
  `create_time` datetime DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `status` int DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`order_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `t_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `t_user` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_order
-- ----------------------------
BEGIN;
INSERT INTO `t_order` VALUES ('16308337228832', '2021-09-05 18:22:03', 136.50, 1, 2);
INSERT INTO `t_order` VALUES ('16308371846692', '2021-09-05 19:19:45', 145.00, 1, 2);
COMMIT;

-- ----------------------------
-- Table structure for t_order_item
-- ----------------------------
DROP TABLE IF EXISTS `t_order_item`;
CREATE TABLE `t_order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `count` int DEFAULT NULL,
  `price` decimal(11,2) DEFAULT NULL,
  `total_price` decimal(11,2) DEFAULT NULL,
  `order_id` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `order_id` (`order_id`),
  CONSTRAINT `t_order_item_ibfk_1` FOREIGN KEY (`order_id`) REFERENCES `t_order` (`order_id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_order_item
-- ----------------------------
BEGIN;
INSERT INTO `t_order_item` VALUES (1, 'C++编程思想', 3, 45.50, 136.50, '16308337228832');
INSERT INTO `t_order_item` VALUES (2, 'C++编程思想', 1, 45.50, 45.50, '16308371846692');
INSERT INTO `t_order_item` VALUES (3, 'Java编程思想', 1, 99.50, 99.50, '16308371846692');
COMMIT;

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(32) NOT NULL,
  `email` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of t_user
-- ----------------------------
BEGIN;
INSERT INTO `t_user` VALUES (1, 'admin', 'admin', 'admin@suhao.com');
INSERT INTO `t_user` VALUES (2, 'suhao', 'suhao1234', 'suhao@gmail.com');
INSERT INTO `t_user` VALUES (3, 'tom', 'tom1234', 'tom@gmail.com');
INSERT INTO `t_user` VALUES (4, 'codeTest', 'codeTest', 'suhao0710@gmail.com');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
