/*
 Navicat MySQL Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : oesdemo

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 01/05/2022 23:35:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_exam
-- ----------------------------
DROP TABLE IF EXISTS `t_exam`;
CREATE TABLE `t_exam`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int NULL DEFAULT NULL,
  `exam_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 7 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam
-- ----------------------------
INSERT INTO `t_exam` VALUES (2, 36, '期末考试');
INSERT INTO `t_exam` VALUES (3, 36, '期末考试');
INSERT INTO `t_exam` VALUES (6, 36, '月考');
INSERT INTO `t_exam` VALUES (7, 36, '撒大苏打');

-- ----------------------------
-- Table structure for t_exam_question
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_question`;
CREATE TABLE `t_exam_question`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_id` int NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam_question
-- ----------------------------
INSERT INTO `t_exam_question` VALUES (1, 1, 3);
INSERT INTO `t_exam_question` VALUES (2, 1, 6);
INSERT INTO `t_exam_question` VALUES (3, 3, 8);
INSERT INTO `t_exam_question` VALUES (4, 3, 9);
INSERT INTO `t_exam_question` VALUES (11, 2, 8);
INSERT INTO `t_exam_question` VALUES (12, 2, 9);
INSERT INTO `t_exam_question` VALUES (13, 2, 10);
INSERT INTO `t_exam_question` VALUES (14, 2, 11);
INSERT INTO `t_exam_question` VALUES (21, 6, 8);
INSERT INTO `t_exam_question` VALUES (22, 6, 9);
INSERT INTO `t_exam_question` VALUES (23, 6, 10);
INSERT INTO `t_exam_question` VALUES (24, 6, 11);
INSERT INTO `t_exam_question` VALUES (25, 7, 11);

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_id` int NULL DEFAULT NULL,
  `ques_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ans_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `correct` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `score` int NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES (8, 36, '21', NULL, '23', 1, '填空题');
INSERT INTO `t_question` VALUES (9, 36, '1+2', NULL, '3', 3, '填空题');
INSERT INTO `t_question` VALUES (10, 36, '1+4', NULL, '5', 5, '填空题');
INSERT INTO `t_question` VALUES (11, 36, '1+1', NULL, '2', 2, '填空题');

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 36 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES (32, '语文', '一年级');
INSERT INTO `t_subject` VALUES (33, '英语', '高一');
INSERT INTO `t_subject` VALUES (34, '语文', '高一');
INSERT INTO `t_subject` VALUES (36, '数学', '高三');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'zhangsan', '$2a$10$ERyqkH5MJgJqUsBUjIHm2u49gyqQbXWHe0WqPu2ReXkA8VhMZCYe6', '张三', 'ADMIN');
INSERT INTO `t_user` VALUES (2, 'lisi', '$2a$10$EveTGUpYpqDUwkbhZdIpKewLBEiPhmq4MvuaT6JdAz4a6uNWc7mla', '李四', 'STUDENT');

SET FOREIGN_KEY_CHECKS = 1;
