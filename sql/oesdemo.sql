/*
 Navicat Premium Data Transfer

 Source Server         : localhost
 Source Server Type    : MySQL
 Source Server Version : 80026
 Source Host           : localhost:3306
 Source Schema         : oesdemo

 Target Server Type    : MySQL
 Target Server Version : 80026
 File Encoding         : 65001

 Date: 04/05/2022 19:32:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_exam
-- ----------------------------
DROP TABLE IF EXISTS `t_exam`;
CREATE TABLE `t_exam`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `subject_id` int(0) NULL DEFAULT NULL,
  `exam_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `paper_score` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam
-- ----------------------------
INSERT INTO `t_exam` VALUES (3, 36, '期末考试', 3);
INSERT INTO `t_exam` VALUES (6, 36, '月考', 10);

-- ----------------------------
-- Table structure for t_exam_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_answer`;
CREATE TABLE `t_exam_answer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_id` int(0) NULL DEFAULT NULL,
  `user_score` int(0) NULL DEFAULT NULL,
  `paper_score` int(0) NULL DEFAULT NULL,
  `creater_id` int(0) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0),
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam_answer
-- ----------------------------
INSERT INTO `t_exam_answer` VALUES (7, 6, 10, 10, 2, '2022-05-04 17:07:14');

-- ----------------------------
-- Table structure for t_exam_question
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_question`;
CREATE TABLE `t_exam_question`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_id` int(0) NOT NULL,
  `question_id` int(0) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam_question
-- ----------------------------
INSERT INTO `t_exam_question` VALUES (35, 3, 9);
INSERT INTO `t_exam_question` VALUES (36, 6, 9);
INSERT INTO `t_exam_question` VALUES (37, 6, 10);
INSERT INTO `t_exam_question` VALUES (38, 6, 11);

-- ----------------------------
-- Table structure for t_exam_question_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_question_answer`;
CREATE TABLE `t_exam_question_answer`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `exam_answer_id` int(0) NULL DEFAULT NULL,
  `question_id` int(0) NULL DEFAULT NULL,
  `question_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `do_right` int(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam_question_answer
-- ----------------------------
INSERT INTO `t_exam_question_answer` VALUES (16, 7, 9, '3', 1);
INSERT INTO `t_exam_question_answer` VALUES (17, 7, 10, '5', 1);
INSERT INTO `t_exam_question_answer` VALUES (18, 7, 11, '2', 1);

-- ----------------------------
-- Table structure for t_question
-- ----------------------------
DROP TABLE IF EXISTS `t_question`;
CREATE TABLE `t_question`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `subject_id` int(0) NULL DEFAULT NULL,
  `ques_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `ans_content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `correct` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `score` int(0) NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_question
-- ----------------------------
INSERT INTO `t_question` VALUES (9, 36, '1+2', NULL, '3', 3, '填空题');
INSERT INTO `t_question` VALUES (10, 36, '1+4', NULL, '5', 5, '填空题');
INSERT INTO `t_question` VALUES (11, 36, '1+1', NULL, '2', 2, '填空题');
INSERT INTO `t_question` VALUES (19, 36, '100+100', NULL, '200', 5, '填空题');

-- ----------------------------
-- Table structure for t_subject
-- ----------------------------
DROP TABLE IF EXISTS `t_subject`;
CREATE TABLE `t_subject`  (
  `id` int(0) NOT NULL AUTO_INCREMENT,
  `subject_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `grade_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 37 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

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
  `id` int(0) NOT NULL,
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `real_name` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `role` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '$2a$10$OhPxIoW64AAgR3S1bcLKuugiQRP2nYntXtz5QwrI5kFVmxCcTH1li', '张三', 'ADMIN');
INSERT INTO `t_user` VALUES (2, 'student', '$2a$10$SzD4qpCuy3zY04LdOegXUuI7iViHoWBRBqMKvMvncWCMg0JCVR9bi', '李四', 'STUDENT');

SET FOREIGN_KEY_CHECKS = 1;
