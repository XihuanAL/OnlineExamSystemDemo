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

 Date: 02/05/2022 21:51:45
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
  `paper_score` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 9 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam
-- ----------------------------
INSERT INTO `t_exam` VALUES (2, 36, '期末考试', NULL);
INSERT INTO `t_exam` VALUES (3, 36, '期末考试', NULL);
INSERT INTO `t_exam` VALUES (6, 36, '月考', NULL);
INSERT INTO `t_exam` VALUES (7, 36, '撒大苏打', NULL);
INSERT INTO `t_exam` VALUES (8, 36, 'test', 11);

-- ----------------------------
-- Table structure for t_exam_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_answer`;
CREATE TABLE `t_exam_answer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_id` int NULL DEFAULT NULL,
  `user_score` int NULL DEFAULT NULL,
  `paper_score` int NULL DEFAULT NULL,
  `creater_id` int NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam_answer
-- ----------------------------
INSERT INTO `t_exam_answer` VALUES (2, 6, 11, NULL, 2, '2022-05-02 16:31:00');
INSERT INTO `t_exam_answer` VALUES (4, 8, 11, 11, 2, '2022-05-02 16:36:54');
INSERT INTO `t_exam_answer` VALUES (5, 3, 4, NULL, 2, '2022-05-02 18:32:00');

-- ----------------------------
-- Table structure for t_exam_question
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_question`;
CREATE TABLE `t_exam_question`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_id` int NOT NULL,
  `question_id` int NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam_question
-- ----------------------------
INSERT INTO `t_exam_question` VALUES (1, 1, 3);
INSERT INTO `t_exam_question` VALUES (2, 1, 6);
INSERT INTO `t_exam_question` VALUES (3, 3, 8);
INSERT INTO `t_exam_question` VALUES (4, 3, 9);
INSERT INTO `t_exam_question` VALUES (21, 6, 8);
INSERT INTO `t_exam_question` VALUES (22, 6, 9);
INSERT INTO `t_exam_question` VALUES (23, 6, 10);
INSERT INTO `t_exam_question` VALUES (24, 6, 11);
INSERT INTO `t_exam_question` VALUES (25, 7, 11);
INSERT INTO `t_exam_question` VALUES (26, 8, 8);
INSERT INTO `t_exam_question` VALUES (27, 8, 9);
INSERT INTO `t_exam_question` VALUES (28, 8, 10);
INSERT INTO `t_exam_question` VALUES (29, 8, 11);
INSERT INTO `t_exam_question` VALUES (30, 2, 8);
INSERT INTO `t_exam_question` VALUES (31, 2, 9);
INSERT INTO `t_exam_question` VALUES (32, 2, 10);
INSERT INTO `t_exam_question` VALUES (33, 2, 11);

-- ----------------------------
-- Table structure for t_exam_question_answer
-- ----------------------------
DROP TABLE IF EXISTS `t_exam_question_answer`;
CREATE TABLE `t_exam_question_answer`  (
  `id` int NOT NULL AUTO_INCREMENT,
  `exam_answer_id` int NULL DEFAULT NULL,
  `question_id` int NULL DEFAULT NULL,
  `question_answer` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NULL DEFAULT NULL,
  `do_right` int NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_exam_question_answer
-- ----------------------------
INSERT INTO `t_exam_question_answer` VALUES (1, 2, 8, '23', 1);
INSERT INTO `t_exam_question_answer` VALUES (2, 2, 9, '3', 1);
INSERT INTO `t_exam_question_answer` VALUES (3, 2, 10, '5', 1);
INSERT INTO `t_exam_question_answer` VALUES (4, 2, 11, '2', 1);
INSERT INTO `t_exam_question_answer` VALUES (5, 3, 8, '23', 1);
INSERT INTO `t_exam_question_answer` VALUES (6, 3, 9, '3', 1);
INSERT INTO `t_exam_question_answer` VALUES (7, 3, 10, '5', 1);
INSERT INTO `t_exam_question_answer` VALUES (8, 3, 11, '2', 1);
INSERT INTO `t_exam_question_answer` VALUES (9, 4, 8, '23', 1);
INSERT INTO `t_exam_question_answer` VALUES (10, 4, 9, '3', 1);
INSERT INTO `t_exam_question_answer` VALUES (11, 4, 10, '5', 1);
INSERT INTO `t_exam_question_answer` VALUES (12, 4, 11, '2', 1);
INSERT INTO `t_exam_question_answer` VALUES (13, 5, 8, '23', 1);
INSERT INTO `t_exam_question_answer` VALUES (14, 5, 9, '3', 1);

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
