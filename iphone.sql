/*
 Navicat Premium Data Transfer

 Source Server         : 1
 Source Server Type    : MySQL
 Source Server Version : 80031 (8.0.31)
 Source Host           : localhost:3306
 Source Schema         : iphone

 Target Server Type    : MySQL
 Target Server Version : 80031 (8.0.31)
 File Encoding         : 65001

 Date: 25/03/2025 15:16:31
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for project
-- ----------------------------
DROP TABLE IF EXISTS `project`;
CREATE TABLE `project`  (
  `project_id` int NOT NULL AUTO_INCREMENT,
  `project_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `version_number` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `operator_id` int NULL DEFAULT NULL,
  PRIMARY KEY (`project_id`) USING BTREE,
  INDEX `fk_operator`(`operator_id` ASC) USING BTREE,
  CONSTRAINT `fk_operator` FOREIGN KEY (`operator_id`) REFERENCES `user` (`user_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 1006 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of project
-- ----------------------------
INSERT INTO `project` VALUES (1002, 'Patrick', '1.1', '2023-11-13 07:56:20', '2025-03-01 17:25:36', 1);
INSERT INTO `project` VALUES (1003, 'Lu Jiehong', '1.2', '2011-09-02 00:50:29', '2025-03-01 17:25:37', 1);
INSERT INTO `project` VALUES (1004, '1', '1', '2025-03-01 12:41:55', '2025-03-01 17:25:38', 1);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `password` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `role` enum('user','admin') CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1, '12345', '12345', 'user', 'snydjosephine@icloud.com');
INSERT INTO `user` VALUES (2, 'Cai Lan', 'BTeTfpGxHy', 'user', 'lancai6@mail.com');
INSERT INTO `user` VALUES (3, 'Lok Sau Man', 'aejTPg9g5z', 'admin', 'sml82@gmail.com');
INSERT INTO `user` VALUES (5, 'admin', '123456', 'admin', 'greenmatth@icloud.com');
INSERT INTO `user` VALUES (6, '1234', '2', 'user', 'a@qq.com');

SET FOREIGN_KEY_CHECKS = 1;
