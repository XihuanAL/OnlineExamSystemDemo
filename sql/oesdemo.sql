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

 Date: 19/04/2022 14:26:26
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
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam
-- ----------------------------
INSERT INTO `t_exam` VALUES (1, 7, '期中考试');
INSERT INTO `t_exam` VALUES (2, 36, '期末考试');
INSERT INTO `t_exam` VALUES (3, 36, '期末考试');
INSERT INTO `t_exam` VALUES (4, 36, '期末考试2');

-- ----------------------------
-- Table structure for t_exam_question
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_question`;
CREATE TABLE `t_exam_question`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_id` int NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam_question
-- ----------------------------
INSERT INTO `t_exam_question` VALUES (1, 1, 3);
INSERT INTO `t_exam_question` VALUES (2, 1, 6);
INSERT INTO `t_exam_question` VALUES (3, 3, 8);
INSERT INTO `t_exam_question` VALUES (4, 3, 9);
INSERT INTO `t_exam_question` VALUES (5, 4, 8);
INSERT INTO `t_exam_question` VALUES (6, 4, 9);
INSERT INTO `t_exam_question` VALUES (7, 4, 10);
INSERT INTO `t_exam_question` VALUES (8, 4, 11);

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
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES (3, 10, '3', '4', '6', 5, '填空题');
INSERT INTO `t_question` VALUES (6, 7, '一心__意', NULL, '一', 5, '填空题');
INSERT INTO `t_question` VALUES (7, 27, '撒旦', NULL, '撒', 1, '填空题');
INSERT INTO `t_question` VALUES (8, 36, '21', NULL, '23', 1, '填空题');
INSERT INTO `t_question` VALUES (9, 36, '1+2', NULL, '3', 3, '填空题');
INSERT INTO `t_question` VALUES (10, 36, '1+4', NULL, '5', 5, '填空题');
INSERT INTO `t_question` VALUES (11, 36, '1+1', NULL, '2', 2, '填空题');
INSERT INTO `t_question` VALUES (13, 26, '123', NULL, '21312', 3, '填空题');

-- ----------------------------
-- Table structure for t_student
-- ----------------------------
DROP TABLE IF EXISTS `t_student`;
CREATE TABLE `t_student`  (
  `id` int NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_student
-- ----------------------------
INSERT INTO `t_student` VALUES (1, 'zhangsan', '123', '张三');

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_subject
-- ----------------------------
INSERT INTO `t_subject` VALUES (7, '数学', '高一');
INSERT INTO `t_subject` VALUES (10, '数学', '高二');
INSERT INTO `t_subject` VALUES (16, '语文', '高一');
INSERT INTO `t_subject` VALUES (26, '数学', '一年级');
INSERT INTO `t_subject` VALUES (27, '化学', '高一');
INSERT INTO `t_subject` VALUES (29, 'asd1', '撒旦');
INSERT INTO `t_subject` VALUES (32, '语文', '一年级');
INSERT INTO `t_subject` VALUES (33, '英语', '高一');
INSERT INTO `t_subject` VALUES (34, '语文', '高一');
INSERT INTO `t_subject` VALUES (36, '数学', '高三');

SET FOREIGN_KEY_CHECKS = 1;
