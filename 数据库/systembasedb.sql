/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MariaDB
 Source Server Version : 100219
 Source Host           : 10.20.22.31:3306
 Source Schema         : systembasedb

 Target Server Type    : MariaDB
 Target Server Version : 100219
 File Encoding         : 65001

 Date: 07/11/2019 15:52:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `t_dictionary`;
CREATE TABLE `t_dictionary`  (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据名称',
  `value` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '数据值',
  `value_cn` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '中文含义',
  `value_en` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '英文含义',
  `op_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '创建人',
  `op_time` datetime(0) NOT NULL COMMENT '创建时间',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 212 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
INSERT INTO `t_dictionary` VALUES (1, 'U_SENSITTIVE_OPER', '01', '找回密码', 'retrieve password', 'admin', '2018-01-09 19:35:50', '敏感操作');
INSERT INTO `t_dictionary` VALUES (2, 'U_SENSITTIVE_OPER', '02', '修改密码', 'change password', 'admin', '2018-01-11 13:57:51', '敏感操作');
INSERT INTO `t_dictionary` VALUES (3, 'U_SENSITTIVE_OPER', '03', '修改手机号', 'Modify phone number', 'admin', '2018-01-09 19:35:44', '敏感操作');
INSERT INTO `t_dictionary` VALUES (4, 'U_SENSITTIVE_OPER', '04', '修改邮箱', 'Modify email', 'admin', '2018-01-11 15:30:43', '敏感操作');
INSERT INTO `t_dictionary` VALUES (5, 'U_SENSITTIVE_OPER', '05', '锁定设备', 'lock device', 'admin', '2018-01-09 19:35:37', '敏感操作');
INSERT INTO `t_dictionary` VALUES (6, 'U_SENSITTIVE_OPER', '06', '解锁设备', 'unlock device', 'admin', '2018-01-09 19:35:30', '敏感操作');
INSERT INTO `t_dictionary` VALUES (11, 'LOGIN_METHOD', '1', '手机号', 'Telephone', 'admin', '2018-12-14 09:39:39', '登录方式');
INSERT INTO `t_dictionary` VALUES (12, 'LOGIN_METHOD', '2', '邮箱', 'Email', 'yu', '2019-06-25 09:23:26', '登录方式');
INSERT INTO `t_dictionary` VALUES (13, 'EQUIPMENT', '1', '浏览器', 'PC client', 'admin', '2019-07-18 20:51:08', '登录设备1');
INSERT INTO `t_dictionary` VALUES (14, 'EQUIPMENT', '2', 'PC客户端', 'browser', 'admin', '2018-03-16 09:41:45', '登录设备');
INSERT INTO `t_dictionary` VALUES (15, 'EQUIPMENT', '3', '终端', 'terminal', 'admin', '2018-01-10 17:24:45', '登录设备');
INSERT INTO `t_dictionary` VALUES (16, 'USER_STATUS', 'Y', '正常', 'normal', 'admin', '2018-01-10 18:21:01', '用户状态');
INSERT INTO `t_dictionary` VALUES (17, 'USER_STATUS', 'N', '未绑定设备', 'Unbound equipment', 'admin', '2018-01-10 18:23:54', '用户状态');
INSERT INTO `t_dictionary` VALUES (18, 'USER_STATUS', 'F', '冻结', 'freeze', 'admin', '2018-01-10 18:24:45', '用户状态');
INSERT INTO `t_dictionary` VALUES (19, 'EQUIPMENT_CONFIG', '0', '32G', '32G', 'admin', '2018-09-17 11:25:31', '配置');
INSERT INTO `t_dictionary` VALUES (20, 'EQUIPMENT_CONFIG', '1', '64G', '64G', 'frank', '2019-04-12 15:11:14', '配置');
INSERT INTO `t_dictionary` VALUES (21, 'FIRMWARE_TYPE', '01', '通用型', 'universal', 'admin', '2018-01-19 14:29:02', '固件类型');
INSERT INTO `t_dictionary` VALUES (22, 'FIRMWARE_TYPE', '02', '测试型', 'test', 'admin', '2018-01-19 14:29:56', '固件类型');
INSERT INTO `t_dictionary` VALUES (23, 'FIRMWARE_TYPE', '03', '专业型', 'special', 'admin', '2018-01-19 14:30:58', '固件类型');
INSERT INTO `t_dictionary` VALUES (24, 'FIRMWARE_STATUS', '0', '未审核', 'Not audit', 'admin', '2018-01-20 15:50:34', '固件状态');
INSERT INTO `t_dictionary` VALUES (25, 'FIRMWARE_STATUS', '1', '审核测试中', 'Testing ', 'admin', '2018-01-20 15:50:35', '固件状态');
INSERT INTO `t_dictionary` VALUES (26, 'FIRMWARE_STATUS', '2', '已审核', 'The approved', 'admin', '2018-01-23 15:07:39', '固件状态');
INSERT INTO `t_dictionary` VALUES (27, 'FIRM__RELEASE_STATUS', '1', '未发布', 'no release', 'admin', '2018-01-23 15:50:01', '固件发布状态');
INSERT INTO `t_dictionary` VALUES (28, 'FIRM__RELEASE_STATUS', '2', '已发布', 'release', 'admin', '2018-01-23 15:50:02', '固件发布状态');
INSERT INTO `t_dictionary` VALUES (29, 'EQUIP_FIRM_UPDATE_STATUS', '0', '初始版本', 'begin version', 'admin', '2018-01-25 15:30:02', '设备固件更新状态');
INSERT INTO `t_dictionary` VALUES (30, 'EQUIP_FIRM_UPDATE_STATUS', '1', '未更新', 'no update', 'admin', '2018-01-25 15:30:28', '设备固件更新状态');
INSERT INTO `t_dictionary` VALUES (31, 'EQUIP_FIRM_UPDATE_STATUS', '2', '已更新', 'updated', 'admin', '2018-01-25 15:30:58', '设备固件更新状态');
INSERT INTO `t_dictionary` VALUES (32, 'FIRMWARETASK_FINISH_FLAG', '1', '完成', 'finish', 'admin', '2018-01-29 14:56:01', '固件任务完成标识');
INSERT INTO `t_dictionary` VALUES (33, 'FIRMWARETASK_FINISH_FLAG', '2', '未完成', 'no_finish', 'admin', '2018-01-29 14:56:35', '固件任务完成标识');
INSERT INTO `t_dictionary` VALUES (34, 'VERSION_PURPOSE', '1', 'rd', 'rd', 'admin', '2018-01-29 14:56:35', '版本用途');
INSERT INTO `t_dictionary` VALUES (35, 'VERSION_PURPOSE', '2', 'release', 'release', 'admin', '2018-01-30 11:31:56', '版本用途');
INSERT INTO `t_dictionary` VALUES (36, 'AREA', '0', '大陆', 'china', 'admin', '2018-01-10 22:35:49', '区域');
INSERT INTO `t_dictionary` VALUES (37, 'AREA', '1', '北美', 'American', 'admin', '2018-01-10 22:35:49', '区域');
INSERT INTO `t_dictionary` VALUES (38, 'AREA', '2', '欧洲', 'European', 'admin', '2018-01-25 22:35:49', '区域');
INSERT INTO `t_dictionary` VALUES (39, 'CUSTOM', '0', '无定制', 'no custom', 'admin', '2018-01-25 22:35:49', '定制');
INSERT INTO `t_dictionary` VALUES (40, 'CUSTOM', '1', '企业定制版', 'Enterprise customized edition', 'admin', '2018-01-25 22:35:49', '定制');
INSERT INTO `t_dictionary` VALUES (41, 'CUSTOM', '2', '学校定制版', 'School edition', 'admin', '2018-01-25 22:35:49', '定制');
INSERT INTO `t_dictionary` VALUES (42, 'U_SENSITTIVE_OPER', '07', '异地登录', 'Remote Login', 'admin', '2018-03-23 14:25:44', '敏感操作');
INSERT INTO `t_dictionary` VALUES (43, 'EQUIPMENT_PURPOSE', '0', '测试设备', '', 'admin', '2018-03-23 14:25:44', '设备用途');
INSERT INTO `t_dictionary` VALUES (44, 'EQUIPMENT_PURPOSE', '1', '售卖设备', '', 'admin', '2018-03-23 14:25:44', '设备用途');
INSERT INTO `t_dictionary` VALUES (45, 'LAN', '0', 'CN', 'CN', 'admin', '2018-03-23 14:25:44', '语言');
INSERT INTO `t_dictionary` VALUES (46, 'LAN', '1', 'EN', 'EN', 'admin', '2018-03-23 14:25:44', '语言');
INSERT INTO `t_dictionary` VALUES (47, 'VERSION_PURPOSE', '3', 'test', 'test', 'admin', '2018-07-19 11:07:51', '版本用途');
INSERT INTO `t_dictionary` VALUES (48, 'VERSION_PURPOSE', '4', 'beta', 'beta', 'admin', '2018-07-19 11:08:15', '版本用途');
INSERT INTO `t_dictionary` VALUES (49, 'VERSION_PURPOSE', '5', 'demo', 'demo', 'admin', '2018-07-19 11:08:34', '版本用途');
INSERT INTO `t_dictionary` VALUES (50, 'VERSION_PURPOSE', '6', 'temp', 'temp', 'admin', '2018-07-19 11:10:24', '版本用途');
INSERT INTO `t_dictionary` VALUES (51, 'EQUIPMENT_HEALTHY_STATE', '0', '正常', 'normal', 'hqq', '2018-10-19 14:44:57', '设备健康状态');
INSERT INTO `t_dictionary` VALUES (52, 'EQUIPMENT_HEALTHY_STATE', '1', '维修中', 'maintenance', 'hqq', '2018-10-19 14:45:13', '设备健康状态');
INSERT INTO `t_dictionary` VALUES (53, 'EQUIPMENT_HEALTHY_STATE', '2', '报废', 'scrap', 'hqq', '2018-10-19 14:45:31', '设备健康状态');
INSERT INTO `t_dictionary` VALUES (54, 'DEALERS_TYPE', '1', '线上代理', 'Online broker', 'admin', '2019-02-21 10:24:11', '经销商类别');
INSERT INTO `t_dictionary` VALUES (55, 'DEALERS_TYPE', '2', '线下代理', 'Offline agent', 'admin', '2019-02-21 10:24:53', '经销商类别');
INSERT INTO `t_dictionary` VALUES (56, 'DEALERS_TYPE', '3', '海外代理', 'Overseas agent', 'admin', '2019-02-21 10:25:21', '经销商类别');
INSERT INTO `t_dictionary` VALUES (57, 'LAN', '2', 'HK', 'HK', 'hqq', '2019-05-13 21:56:11', '语言');
INSERT INTO `t_dictionary` VALUES (58, 'LAN', '3', 'JP', 'JP', 'hqq', '2019-05-13 21:56:47', '语言');
INSERT INTO `t_dictionary` VALUES (59, 'VERSION_PURPOSE', '7', 'root', 'root', 'frank', '2019-06-05 17:41:01', '版本用途');
INSERT INTO `t_dictionary` VALUES (151, 'EQUIPMENT_MODEL', '58', 'SN100-03-2018', 'SN100-03-2018', 'admin', '2018-05-21 17:22:26', '设备型号');
INSERT INTO `t_dictionary` VALUES (152, 'EQUIPMENT_MODEL', '68', 'SN100-A5基础版', 'SN100-A5基础版', 'hqq', '2018-08-14 19:12:39', '设备型号');
INSERT INTO `t_dictionary` VALUES (153, 'EQUIPMENT_MODEL', '73', 'SN100-Test', 'SN100-Test', 'frank', '2018-10-22 14:25:57', '设备型号');
INSERT INTO `t_dictionary` VALUES (154, 'EQUIPMENT_MODEL', '74', 'SN100-Test-Backups', 'SN100-Test-Backups', 'frank', '2018-10-22 15:11:00', '设备型号');
INSERT INTO `t_dictionary` VALUES (155, 'EQUIPMENT_MODEL', '75', 'SN078-A6基础版', 'SN078-A6基础版', 'frank', '2019-04-09 15:41:45', '设备型号');
INSERT INTO `t_dictionary` VALUES (156, 'EQUIPMENT_MODEL', '81', 'SN100-Test2', 'SN100-Test2', 'frank', '2019-06-03 14:48:43', '设备型号');
INSERT INTO `t_dictionary` VALUES (157, 'EQUIPMENT_MODEL', '82', 'SN100-Test3', 'SN100-Test3', 'frank', '2019-06-05 15:45:23', '设备型号');
INSERT INTO `t_dictionary` VALUES (158, 'TRESOURCETYPE_ID', '0', '菜单', 'menu', 'admin', '2019-07-22 09:12:15', '资源类型');
INSERT INTO `t_dictionary` VALUES (159, 'TRESOURCETYPE_ID', '1', '功能', 'function', 'admin', '2019-07-22 09:12:15', '资源类型');
INSERT INTO `t_dictionary` VALUES (160, 'EQUIPMENT_MODEL', '129', '222电放费', '333', 'admin', '2019-08-26 15:46:56', '设备型号');
INSERT INTO `t_dictionary` VALUES (161, '44', '44', '44', '666', 'admin', '2019-09-11 14:02:43', '22');
INSERT INTO `t_dictionary` VALUES (162, '4444444', '4444', '2', '3', 'admin', '2019-08-30 14:51:36', '1');
INSERT INTO `t_dictionary` VALUES (165, '13', '123', '123', '123', 'admin', '2019-09-16 14:07:52', '123');
INSERT INTO `t_dictionary` VALUES (166, '13', '123', '123', '123', 'admin', '2019-09-16 14:07:52', '123');
INSERT INTO `t_dictionary` VALUES (167, '123', '123', '123', '123', 'admin', '2019-09-16 14:10:26', '123');
INSERT INTO `t_dictionary` VALUES (168, '000', '444', '654', '45646', 'admin', '2019-09-16 14:11:01', '456');
INSERT INTO `t_dictionary` VALUES (169, '45454', '4444', '45454', '45454545', 'admin', '2019-09-16 14:12:55', '4545');
INSERT INTO `t_dictionary` VALUES (170, '77777', '7777', '7777777', '7777777', 'admin', '2019-09-16 14:13:17', '77777');
INSERT INTO `t_dictionary` VALUES (171, '99999', '99999', '99', '9999', 'admin', '2019-09-16 14:14:48', '999');
INSERT INTO `t_dictionary` VALUES (172, '888', '8888', '88', '8888', 'admin', '2019-09-16 14:15:06', '88');
INSERT INTO `t_dictionary` VALUES (173, '555', '5555', '5555', '555555', 'admin', '2019-09-16 14:18:17', '5555');
INSERT INTO `t_dictionary` VALUES (174, '5656', '5656', '5656', '5656', 'admin', '2019-09-16 14:19:58', '5656');
INSERT INTO `t_dictionary` VALUES (175, 'w34dr', '234', 'retr7uy', 'drr776', 'admin', '2019-09-16 14:21:03', 'redt');
INSERT INTO `t_dictionary` VALUES (176, 'gggjg', 'fuu', 'tfftft', 'dyt7t', 'admin', '2019-09-16 14:23:15', 'rr757r');
INSERT INTO `t_dictionary` VALUES (177, 'ertery', 'tryrty', 'eryery', 'eryery', 'admin', '2019-09-16 14:23:46', 'reyery');
INSERT INTO `t_dictionary` VALUES (178, 'wewre', 'sdfs', 'sdgsg', 'w35325', 'admin', '2019-09-16 14:25:03', 'wetewsd');
INSERT INTO `t_dictionary` VALUES (179, 'qwrqwr', '234', 'asrar', 'asaf', 'admin', '2019-09-16 14:33:08', 'asfaf');
INSERT INTO `t_dictionary` VALUES (180, 'qwrqr', 'ewqrwq', 'asfa235', '235235', 'admin', '2019-09-16 14:34:05', 'qwrqw');
INSERT INTO `t_dictionary` VALUES (181, 'wet', 'wetw', 'wet', 'wetwt', 'admin', '2019-09-16 14:34:22', 'wet');
INSERT INTO `t_dictionary` VALUES (182, '阿斯蒂芬是', '是多少', '问题问题', '我问他问题', 'admin', '2019-09-16 14:35:40', '问问他');
INSERT INTO `t_dictionary` VALUES (183, 'wetwt', 'srtwe', 'ewtw', 'wet', 'admin', '2019-09-16 14:35:51', 'etw');
INSERT INTO `t_dictionary` VALUES (184, 'werwe', 'sdfwe', 'werwe', 'werwer', 'admin', '2019-09-16 14:37:09', 'werew');
INSERT INTO `t_dictionary` VALUES (185, 'esdfad', '234', 'dsfsw3', 'sdfsfsf', 'admin', '2019-09-16 14:37:56', 'asfaf');
INSERT INTO `t_dictionary` VALUES (186, 'sfasf', 'ewra', 'fas', 'safasfa', 'admin', '2019-09-16 14:38:51', 'asfasf');
INSERT INTO `t_dictionary` VALUES (187, 'rqwrqwrqwwq', '23423wq', 'qwrqwr', 'qwrqr', 'admin', '2019-09-16 14:39:05', 'rqwrqw');
INSERT INTO `t_dictionary` VALUES (188, 'wetwtwt', 'werwet', 'wetwet', 'wetwet', 'admin', '2019-09-16 14:40:18', 'wetwet');
INSERT INTO `t_dictionary` VALUES (189, 'asfaf', 'afasf', 'asfaf', 'asfaf', 'admin', '2019-09-16 14:41:42', 'asfaf');
INSERT INTO `t_dictionary` VALUES (190, '009u989', '89808', 'aafsdff', 'adfsadfsadf', 'admin', '2019-09-16 14:44:54', 'akldoad');
INSERT INTO `t_dictionary` VALUES (196, '213', '123', '123', '123', 'admin', '2019-09-16 15:09:24', '123');
INSERT INTO `t_dictionary` VALUES (197, '123123', '123', '123', '123', 'admin', '2019-09-16 15:10:01', '213123');
INSERT INTO `t_dictionary` VALUES (198, '123123123123', '123123123', '123123', '123123', 'admin', '2019-09-16 15:10:30', '123123');
INSERT INTO `t_dictionary` VALUES (199, '123123', '3123123', '123213c', 'cwedwdf', 'admin', '2019-09-16 15:10:51', '123123');
INSERT INTO `t_dictionary` VALUES (200, '123', '12312', '123', '123', 'admin', '2019-09-16 15:16:11', '123');
INSERT INTO `t_dictionary` VALUES (201, '12312', '123123', '1123123', '123123', 'admin', '2019-09-16 15:16:41', '123123');
INSERT INTO `t_dictionary` VALUES (202, '1313', '123123', '3131', '313131', 'admin', '2019-09-16 15:18:35', '1231');
INSERT INTO `t_dictionary` VALUES (203, 'a\'sa', 'ad', 'a\'s\'da\'d', 'a\'s\'da\'d', 'admin', '2019-09-16 15:24:44', 'a\'da\'d');
INSERT INTO `t_dictionary` VALUES (204, '123', '132', 'ad', 'adads', 'admin', '2019-09-16 15:36:12', 'aas');
INSERT INTO `t_dictionary` VALUES (205, 'asdzxz', '909090', 'xczc', 'zczczx', 'admin', '2019-09-16 15:44:45', 'zcz');
INSERT INTO `t_dictionary` VALUES (206, '45646', '4644', '45646', '46464646', 'admin', '2019-09-16 15:45:04', '45646');
INSERT INTO `t_dictionary` VALUES (207, 'ooh090', '9u90u09u0', 'kihoi', 'iohoih5', 'admin', '2019-09-18 15:40:26', 'oihh');
INSERT INTO `t_dictionary` VALUES (210, 'SYSTEM_ID', '0', 'Supernote管理系统', 'Supernote management system', 'admin', '2019-11-04 14:31:31', '系统');
INSERT INTO `t_dictionary` VALUES (211, 'SYSTEM_ID', '1', 'CMS管理系统', 'CMS management system', 'admin', '2019-11-04 14:32:51', '系统');

-- ----------------------------
-- Table structure for t_pwd_his
-- ----------------------------
DROP TABLE IF EXISTS `t_pwd_his`;
CREATE TABLE `t_pwd_his`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键 seq_pwd_his',
  `username` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '密码',
  `op_time` datetime(0) NULL DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 247 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pwd_his
-- ----------------------------
INSERT INTO `t_pwd_his` VALUES (245, 'liuming', 'e22cd2d97412a9bc3af16a71ba04be29', '2019-11-05 17:40:05');
INSERT INTO `t_pwd_his` VALUES (246, 'xiaoshan', 'e22cd2d97412a9bc3af16a71ba04be29', '2019-11-07 15:15:14');

-- ----------------------------
-- Table structure for t_reference
-- ----------------------------
DROP TABLE IF EXISTS `t_reference`;
CREATE TABLE `t_reference`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT 'PK(seq_reference)\r\n',
  `serial` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '序号',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数值',
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '参数值',
  `value_cn` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '中文简体参数值\r说明',
  `op_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '修改人员\r\n',
  `op_time` datetime(0) NOT NULL COMMENT '修改时间\r\n',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE INDEX `SERIAL,NAME`(`serial`, `name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 99 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_reference
-- ----------------------------
INSERT INTO `t_reference` VALUES (1, '01', 'VERCODE_LIMIT', '2', '用户登录错误4次输验证码', 'frank', '2018-12-20 17:07:27', NULL);
INSERT INTO `t_reference` VALUES (2, '01', 'MAX_ERR_COUNTS', '6', '错6次锁定用户', 'tianlingjun', '2018-12-11 09:52:10', NULL);
INSERT INTO `t_reference` VALUES (3, '01', 'LOCK_TIME', '5', '锁定用户时长5分钟', 'tianlingjun', '2018-12-11 10:05:17', NULL);
INSERT INTO `t_reference` VALUES (4, '01', 'UPLOAD_MAX', '20', '单次允许最大上传的文件个数', 'tianlingjun', '2018-12-13 11:35:45', NULL);
INSERT INTO `t_reference` VALUES (5, '01', 'COPY_MAX', '20', '单次允许最大复制的文件个数', 'frank', '2018-12-27 13:42:40', NULL);
INSERT INTO `t_reference` VALUES (8, '01', 'CLEAN_DAY', '30', '数据清理天数', 'yu', '2019-06-25 10:18:11', NULL);
INSERT INTO `t_reference` VALUES (9, '01', 'SAME_PASS_COUNTS', '2', '用户改密不可与最近N次密码相同', 'frank', '2018-12-10 17:48:14', NULL);
INSERT INTO `t_reference` VALUES (10, '01', 'PASSWORD_COUNT', '7', '用户登录最大错误次数', 'frank', '2018-12-27 13:43:17', NULL);
INSERT INTO `t_reference` VALUES (11, '01', 'SYNC_FOLDER_NAME', 'SUPONOTEs', '同步文件夹名称', 'admin', '2019-08-30 19:31:51', '');
INSERT INTO `t_reference` VALUES (12, '01', 'TOKEN_TIME', '1', 'value天之内免登录', 'frank', '2018-12-13 17:58:05', NULL);
INSERT INTO `t_reference` VALUES (13, '01', 'FILE_TYPE', 'jpg', 'image/jpeg', 'admin', '2017-10-18 10:15:22', '1');
INSERT INTO `t_reference` VALUES (14, '02', 'FILE_TYPE', 'png', 'image/png', 'admin', '2017-10-18 10:15:25', '1');
INSERT INTO `t_reference` VALUES (15, '03', 'FILE_TYPE', 'gif', 'image/gif', 'admin', '2017-10-18 10:15:29', '1');
INSERT INTO `t_reference` VALUES (16, '04', 'FILE_TYPE', 'bmp', 'image/bmp', 'admin', '2017-10-18 10:15:33', '1');
INSERT INTO `t_reference` VALUES (17, '05', 'FILE_TYPE', 'jpeg', 'image/jpeg', 'admin', '2017-10-18 10:15:38', '1');
INSERT INTO `t_reference` VALUES (18, '06', 'FILE_TYPE', 'psd', 'application/octet-stream', 'admin', '2017-10-18 10:15:41', '1');
INSERT INTO `t_reference` VALUES (19, '07', 'FILE_TYPE', 'tga', 'application/octet-stream', 'admin', '2017-10-18 10:15:44', '1');
INSERT INTO `t_reference` VALUES (20, '08', 'FILE_TYPE', 'tif', 'image/tiff', 'admin', '2017-10-18 10:15:48', '1');
INSERT INTO `t_reference` VALUES (21, '09', 'FILE_TYPE', 'ppt', 'application/octet-stream', 'admin', '2017-10-18 10:15:51', '2');
INSERT INTO `t_reference` VALUES (22, '10', 'FILE_TYPE', 'doc', 'application/octet-stream', 'admin', '2017-10-18 10:15:55', '2');
INSERT INTO `t_reference` VALUES (23, '11', 'FILE_TYPE', 'txt', 'text/plain', 'admin', '2017-10-18 10:15:59', '2');
INSERT INTO `t_reference` VALUES (24, '12', 'FILE_TYPE', 'xls', 'application/octet-stream', 'admin', '2017-10-18 10:16:05', '2');
INSERT INTO `t_reference` VALUES (25, '13', 'FILE_TYPE', 'pdf', 'application/pdf', 'admin', '2017-10-18 10:16:08', '2');
INSERT INTO `t_reference` VALUES (26, '14', 'FILE_TYPE', 'chm', 'application/octet-stream', 'admin', '2017-10-18 10:16:12', '2');
INSERT INTO `t_reference` VALUES (27, '15', 'FILE_TYPE', 'rtf', 'application/octet-stream', 'admin', '2017-10-18 10:16:19', '2');
INSERT INTO `t_reference` VALUES (28, '16', 'FILE_TYPE', 'pptx', 'application/octet-stream', 'admin', '2017-10-18 10:16:23', '2');
INSERT INTO `t_reference` VALUES (29, '17', 'FILE_TYPE', 'docx', 'application/octet-stream', 'admin', '2017-10-18 10:16:26', '2');
INSERT INTO `t_reference` VALUES (30, '18', 'FILE_TYPE', 'xlsx', 'application/octet-stream', 'admin', '2017-10-18 10:16:29', '2');
INSERT INTO `t_reference` VALUES (31, '01', 'FILE_MAX', '1073741824', '上传文件最大512M', 'frank', '2018-12-26 11:02:51', NULL);
INSERT INTO `t_reference` VALUES (32, '19', 'FILE_TYPE', 'zip', 'application/x-zip-compressed', 'admin', '2017-11-16 10:58:11', '0');
INSERT INTO `t_reference` VALUES (33, '20', 'FILE_TYPE', 'rar', 'application/octet-stream', 'admin', '2017-11-16 10:58:35', '0');
INSERT INTO `t_reference` VALUES (34, '21', 'FILE_TYPE', 'apk', 'application/vnd.android.package-archive', 'admin', '2017-11-16 10:58:35', '0');
INSERT INTO `t_reference` VALUES (35, '22', 'FILE_TYPE', 'gz', 'application/x-gzip', 'admin', '2017-11-16 10:58:35', '0');
INSERT INTO `t_reference` VALUES (36, '23', 'FILE_TYPE', 'tar.gz', 'application/octet-stream', 'admin', '2017-11-16 10:58:35', '0');
INSERT INTO `t_reference` VALUES (37, '24', 'FILE_TYPE', 'zz', 'application/octet-stream', 'admin', '2017-11-16 10:58:35', '0');
INSERT INTO `t_reference` VALUES (40, '01', 'PAGE_NUMBER', '500', '分页展示数据个数', 'tianlingjun', '2018-12-13 09:54:42', NULL);
INSERT INTO `t_reference` VALUES (41, '01', 'VERSION', '4.5', ' 参数版本号', 'tianlingjun', '2018-12-10 15:26:39', NULL);
INSERT INTO `t_reference` VALUES (42, '01', 'CLIENT_URL', '1', '客户端下载地址', 'hqq', '2018-11-22 19:08:20', NULL);
INSERT INTO `t_reference` VALUES (43, '01', 'DOWNLOAD_MAX', '512', '下载文件最大256M', 'frank', '2018-12-26 10:59:54', NULL);
INSERT INTO `t_reference` VALUES (45, '01', 'VALIDCODE_TIME', '2', '测试时由30分钟改为了2分钟', 'tianlingjun', '2018-12-11 09:45:43', NULL);
INSERT INTO `t_reference` VALUES (46, '01', 'RANDOM_LOCK_TIME', '60', '接口随机数锁定时长为60秒', 'admin', '2017-12-26 16:16:51', NULL);
INSERT INTO `t_reference` VALUES (47, '01', 'AIIOWABLE_TIME_LAG', '180', '接口请求允许的最大时间差为180秒', 'admin', '2017-12-26 16:16:50', NULL);
INSERT INTO `t_reference` VALUES (50, '01', 'TASK_PRIORITY', 'A', '锁定设备', 'admin', '2018-01-11 10:06:15', NULL);
INSERT INTO `t_reference` VALUES (51, '02', 'TASK_PRIORITY', 'A', '解锁设备', 'admin', '2018-01-11 10:06:29', NULL);
INSERT INTO `t_reference` VALUES (52, '03', 'TASK_PRIORITY', 'C', '固件更新', 'frank', '2019-01-14 14:44:30', NULL);
INSERT INTO `t_reference` VALUES (55, '01', 'SECRET_MANAGE', '123456789', '固件包解压缩口令(至少9位)', 'frank', '2018-08-19 10:44:18', NULL);
INSERT INTO `t_reference` VALUES (56, '01', 'LOGIN_RECORD_CLEAN', '30', 'SN100用户登录清理天数', 'frank', '2019-07-05 10:21:52', NULL);
INSERT INTO `t_reference` VALUES (61, '1', 'TEST', 'release', 'release', 'hqq', '2018-07-19 17:50:13', NULL);
INSERT INTO `t_reference` VALUES (62, '25', 'FILE_TYPE', 'epub', 'application/epub+zip', 'admin', '2018-08-23 15:26:19', '0');
INSERT INTO `t_reference` VALUES (63, '26', 'FILE_TYPE', 'read', 'application/octet-stream', 'admin', '2018-08-23 15:26:21', '0');
INSERT INTO `t_reference` VALUES (64, '27', 'FILE_TYPE', 'note', 'application/octet-stream', 'admin', '2018-08-23 15:26:28', '0');
INSERT INTO `t_reference` VALUES (65, '01', 'VALIDCODE_TPLID_EN', '236658', '发送验证码短信模板id（英文）', 'hqq', '2018-11-28 11:33:08', NULL);
INSERT INTO `t_reference` VALUES (66, '01', 'LOCK_EQUIPMENT_TPLID_EN', '188936', '锁设备解锁设备短信模板id(英文）', 'admin', '2018-09-06 20:31:14', NULL);
INSERT INTO `t_reference` VALUES (67, '01', 'LOGIN_TPLID_EN', '236658', '异地登录短信模板id(英文)', 'frank', '2018-12-13 16:07:42', NULL);
INSERT INTO `t_reference` VALUES (68, '01', 'VALIDCODE_TPLID', '63063', '发送验证码短信模板id', 'frank', '2018-12-13 15:03:33', NULL);
INSERT INTO `t_reference` VALUES (69, '01', 'LOCK_EQUIPMENT_TPLID', '188880', '锁设备解锁设备短信模板id', 'admin', '2018-09-06 20:31:14', NULL);
INSERT INTO `t_reference` VALUES (70, '01', 'LOGIN_TPLID', '188873', '异地登录短信模板id', 'frank', '2018-12-13 15:36:01', NULL);
INSERT INTO `t_reference` VALUES (71, '01', 'LOCK_CLOUD', '60', '云盘锁定时间60分钟(同步时的锁定)', 'frank', '2019-02-26 15:28:01', NULL);
INSERT INTO `t_reference` VALUES (73, '01', 'CHECK_FIXPOINT_VERTYPE', 'bata', 'beta', 'admin', '2018-09-12 21:02:54', NULL);
INSERT INTO `t_reference` VALUES (74, '02', 'CHECK_FIXPOINT_VERTYPE', 'release', 'release', 'admin', '2019-08-27 14:14:03', '');
INSERT INTO `t_reference` VALUES (75, '01', 'EQUIPMENTLOG_CLEAN_DAY', '30', '清理天数', 'frank', '2018-12-14 11:29:37', NULL);
INSERT INTO `t_reference` VALUES (76, '01', 'EMAIL_CODE_TIME', '31', '邮箱验证码有效时间是30分钟', 'admin', '2019-08-30 19:05:36', '');
INSERT INTO `t_reference` VALUES (81, '28', 'FILE_TYPE', 'mark', 'application/octet-stream', 'admin', '2019-07-26 09:26:41', '0');
INSERT INTO `t_reference` VALUES (88, '01', 'DOWNLOAD_MAX_NUMBER', '3', '下载最大条数', 'admin', '2019-08-23 09:26:41', NULL);

-- ----------------------------
-- Table structure for t_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_resource`;
CREATE TABLE `t_resource`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源ID',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源描述',
  `seq` int(10) NULL DEFAULT NULL COMMENT '资源序号',
  `url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源地址',
  `pid` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '父级ID',
  `system_id` int(10) NULL DEFAULT NULL COMMENT '系统列别',
  `tresourcetype_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '0-菜单，1-功能 引用数据字典表',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_resource_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('01b8f9a9-6886-4b8c-b4e1-eee9ed41fbe2', '模板下载', '', 100, '/dealer/model/download', '7948296f-7b57-4721-8ab2-d8e1cb6329d4', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('02b46b4d-f5db-4f37-b6ab-4f452964f0a8', '编辑经销商', '', 100, '/dealer/update', '7948296f-7b57-4721-8ab2-d8e1cb6329d4', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('030fe366-ad4f-442f-b197-7769e907c53c', '设备撤销', '', 100, '/stock/undo', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('03e8cba3-02aa-4655-81e7-3957293d6486', '经销商导出', '', 100, '/dealer/export', '7948296f-7b57-4721-8ab2-d8e1cb6329d4', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('0439b13e-60ac-49c0-995d-c9df95f59fca', '审核', '', 100, '/app/version/auditing', 'ddeb9c3f-3280-4d91-9f3a-c26ef0f59036', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('0800e85b-1885-47be-85ed-c87bf0f5b6f0', '添加角色', NULL, 102, '/role/add', '9f96eaca-b44f-4600-86cf-6a370b339f44', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('0beac010-8677-4704-8c2a-b8c1b7f8b2a7', '编辑任务', '编辑调度任务，一次编辑一个。', 100, '/schedule/update', '1c032c62-af6a-49b8-883d-6cb69c72987f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('0e1952ea-9d08-4397-bc1f-7139c14ba352', '登录记录', '', 101, '', '8cd280dd-d1e8-48fd-a348-db733b16cbc1', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('1304c3ef-e76a-4c5a-bc90-ba1bf752dc77', '修改轮播图', '', 100, '/home/banner/update', '861311d3-2c04-4480-88eb-fb3f925434f0', 1, '1', '2019-10-10 19:16:33', 'admin', '2019-10-10 19:16:33', 'admin');
INSERT INTO `t_resource` VALUES ('1355be5e-81fa-4ec2-85c8-272df24ef6b8', '参数管理', '', 101, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('148cd942-713f-4039-b560-5df0bf14f05b', '固件管理', '', 105, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('15ff29c8-ef83-4b7d-afdd-90b12ae644c8', '批入模板下载', '', 100, '/stock/model/download/in', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('17607501-4fba-47cf-83c3-fac95908f64c', '固件版本删除', '', 100, '/firmware/delete', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('192ef25e-o987-4erd-9576-857a9b82bf80', '修改跳过类型', NULL, 100, '', NULL, 0, '1', NULL, NULL, NULL, NULL);
INSERT INTO `t_resource` VALUES ('1c032c62-af6a-49b8-883d-6cb69c72987f', '调度任务', '', 103, '', 'xtgl', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('1d434328-fe1b-4d95-9a5c-72d3eb18ba91', '角色授权', NULL, 105, '/role/grant', '9f96eaca-b44f-4600-86cf-6a370b339f44', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('1e709f15-067c-4e57-a1ae-723aaf1feca1', '反馈管理', '', 107, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('1f86c896-e4d3-4528-a980-af59c4a75b42', 'app版本上传删除', '', 100, '/app/version/delete', 'ddeb9c3f-3280-4d91-9f3a-c26ef0f59036', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('21499450-77d1-4cfe-9160-12b75f0bbfb3', '修改邮箱', '', 100, '/snuse/update/email', '939d7f32-bae4-4f32-93b4-df5e45f0544f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('217039b0-23b2-4528-82c7-520ce5c5142b', '经销商导入', '', 100, '/dealer/import', '7948296f-7b57-4721-8ab2-d8e1cb6329d4', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('2537ff9e-bd5b-4f4a-961b-3ab5fc076312', '启用', NULL, 109, '/system/user/open', 'yhgl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('26c48551-b6ac-4d48-a05e-640794cb7414', '业务管理', '', 103, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('271687bf-2cc9-44db-b702-674c38bce27e', '解锁', NULL, 108, '/system/user/unlock', 'yhgl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('336b2fd2-1aa2-43a8-8853-1feae5fbe462', '反馈问题类型添加', '', 100, '/feedback/type/add', 'a2983396-144e-42d2-beec-ee8d651b987a', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('37ec88e8-7455-4a2a-8500-cd7c6fae5ec3', '刷机', '', 100, '/firmware/task/flash', 'c32ff894-77cf-4d69-bd10-5c3d88a74beb', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('38c00e40-bc47-42f9-88b2-3a4cf4492547', '添加数据字典', '', 100, '/dictionary/add', '9b6a910d-31e2-473a-a98b-0a2a03966f1e', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('3935887b-156c-41bd-925c-0f68c32b2293', '灰度', '', 100, '/app/version/gray', 'ddeb9c3f-3280-4d91-9f3a-c26ef0f59036', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('3c2020c3-9cf1-4fd6-96d4-3222a73d6205', '设备入库', '', 100, '/stock/in', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('3cb9ced0-dc81-45c4-a85c-c33941591911', '删除数据字典', '', 100, '/dictionary/delete', '9b6a910d-31e2-473a-a98b-0a2a03966f1e', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('3d2020d3-9df1-4fd6-96d4-3222d73d6205', '设备用途', '', 100, '/stock/purpose', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2019-09-04 11:16:22', 'admin', '2019-09-04 16:24:51', 'admin');
INSERT INTO `t_resource` VALUES ('3e939f0b-2e52-4e23-8f82-a774dfa72876', '换货', '', 100, '/goods/exchange', 'c1c9d1f8-1a02-4be1-8484-ab49b582d0d2', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('42eef4cc-2f5d-438f-9bd6-7256241f460c', '删除资源', '', 106, '/resource/delete', 'zygl', 0, '1', '2016-08-16 14:47:49', 'admin', '2019-09-16 14:32:19', 'admin');
INSERT INTO `t_resource` VALUES ('4408e22c-fa95-4c47-96d4-693b3f3ff32d', '删除', '', 100, '/equipment/log/delete', '6d85d431-9f20-4a97-bc4a-e85a389ed8c7', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('47a23d76-2244-4a29-b236-33ad2207fdb9', '设备管理', '', 104, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('4a17dae2-1bb1-48a4-a090-c339ed886f36', '固件上传', '', 100, '/firmware/upload', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('4caff073-b379-44dd-84a0-986acb029f77', '编辑角色', '跳转到编辑角色页面', 103, '/role/update', '9f96eaca-b44f-4600-86cf-6a370b339f44', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('50f8cb18-d3cc-46ec-9fa0-11c79d742bbc', '设备日志审阅', '', 100, '/equipment/log/passed', '6d85d431-9f20-4a97-bc4a-e85a389ed8c7', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('513d6ab7-1cc9-4b44-a2ff-be6431892264', '上线轮播图', '', 100, '/home/banner/online', '861311d3-2c04-4480-88eb-fb3f925434f0', 1, '1', '2019-10-10 19:17:43', 'admin', '2019-10-10 19:17:43', 'admin');
INSERT INTO `t_resource` VALUES ('56e628a9-b834-455c-b2ca-a38f97eea66a', '删除角色', NULL, 104, '/role/delete', '9f96eaca-b44f-4600-86cf-6a370b339f44', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('57dc18d2-6b5d-4978-af05-366ff20b6717', '下载', '', 100, '/app/version/download', 'ddeb9c3f-3280-4d91-9f3a-c26ef0f59036', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('592e825e-0b40-4e9d-9576-857a9b82bf80', '修改固件设备型号', '', 100, '/firmware/update/firmEquip', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('5a70e5d3-e920-4c63-adbe-12949aae5aaf', '添加系统参数', NULL, 102, '/reference/add', 'ab7e9df5-a095-4bc6-a5fa-f5d567a34af1', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('5af40a37-8198-4a5b-aebc-e12e3b066370', '添加经销商', '', 100, '/dealer/add', '7948296f-7b57-4721-8ab2-d8e1cb6329d4', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('5f264f75-fa71-4100-97ca-938d4b585c63', '发布', '', 100, '/firmware/task/deploy', 'c32ff894-77cf-4d69-bd10-5c3d88a74beb', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('62c2954e-3686-499e-99db-8dae9a38a8dd', '撤销', '', 100, '/firmware/task/undo', 'c32ff894-77cf-4d69-bd10-5c3d88a74beb', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('665f6ac3-a634-491f-aa9f-3446c373f29c', '用户解冻', '', 100, '/snuser/Thaw', '939d7f32-bae4-4f32-93b4-df5e45f0544f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('66afc37c-2a55-4a48-b45b-e7d907995c27', '停止任务', '停止调度任务，可同时停止多个任务。', 100, '/schedule/close', '1c032c62-af6a-49b8-883d-6cb69c72987f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('6d85d431-9f20-4a97-bc4a-e85a389ed8c7', '终端日志管理', '', 100, '', 'd3740be8-fb45-42dc-bba6-ea889ca35b5d', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('7576073e-4a09-4d79-bbb7-d3d48ae6e74f', '导出', '', 100, '/stock/export', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('7948296f-7b57-4721-8ab2-d8e1cb6329d4', '经销商信息', '', 101, '', '26c48551-b6ac-4d48-a05e-640794cb7414', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('7ab1ac8a-cf4e-41d3-b09d-b2914f76d35d', '修改系统参数', NULL, 104, '/reference/update', 'ab7e9df5-a095-4bc6-a5fa-f5d567a34af1', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('81a64d80-32f0-4f65-90f1-1ee48d98fd2e', 'app版本修复点', '', 100, '', '8bfb232c-aa3e-4838-bf30-aa6f3fa086e6', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('848d8279-8689-4fe6-9338-8470df0bb809', 'app版本修复点修改', '', 100, '/app/fixPoint/update', '81a64d80-32f0-4f65-90f1-1ee48d98fd2e', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('861311d3-2c04-4480-88eb-fb3f925434f0', '轮播图', '', 100, '', 'd0ca962d-5ffb-43c4-812e-01b7727d8a71', 1, '0', '2019-10-10 18:48:27', 'admin', '2019-10-10 18:48:27', 'admin');
INSERT INTO `t_resource` VALUES ('8a048526-403c-46cc-8545-927b2642bb5d', '反馈问题类型修改', '', 100, '/feedback/type/update', 'a2983396-144e-42d2-beec-ee8d651b987a', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('8bfb232c-aa3e-4838-bf30-aa6f3fa086e6', '应用管理', '', 110, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('8cd280dd-d1e8-48fd-a348-db733b16cbc1', '用户管理', '', 102, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('909cf093-af63-4590-a402-ca4ed48470f5', '固件版本子类详情', '', 100, '/firmware/detailLine', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('91aa3145-abb8-4d53-b20f-d04e9f0d40c4', 'app版本上传', '', 100, '/app/version/upload', 'ddeb9c3f-3280-4d91-9f3a-c26ef0f59036', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('92da7803-5a15-4620-a7da-e92485a709b1', '固件版本修复点', '', 101, '', '148cd942-713f-4039-b560-5df0bf14f05b', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('939d7f32-bae4-4f32-93b4-df5e45f0544f', '用户信息', 'SN100用户管理', 100, '', '8cd280dd-d1e8-48fd-a348-db733b16cbc1', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('942cf5ff-1f12-4f2b-abb2-aeb95d9b8296', '修改数据字典', '', 100, '/dictionary/update', '9b6a910d-31e2-473a-a98b-0a2a03966f1e', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('95c24d6b-4d05-49cc-accc-d97cab901bdb', '启动任务', '启用调度任务，可同时启动多个任务。', 100, '/schedule/open', '1c032c62-af6a-49b8-883d-6cb69c72987f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('95e99815-1654-4582-905e-009f5dca1cc1', '下线轮播图', '', 100, '/home/banner/unline', '861311d3-2c04-4480-88eb-fb3f925434f0', 1, '1', '2019-10-10 19:18:34', 'admin', '2019-10-10 19:18:34', 'admin');
INSERT INTO `t_resource` VALUES ('99416a15-e4e0-4600-920b-9f2390660ffe', '健康状态', '', 100, '/stock/healthy/state', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('996c8736-2a9a-450f-9b7c-09c657474951', '停用', NULL, 110, '/system/user/close', 'yhgl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('9b6a910d-31e2-473a-a98b-0a2a03966f1e', '数据字典管理', '', 100, '', '1355be5e-81fa-4ec2-85c8-272df24ef6b8', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('9c1214c8-7a47-45f9-853a-c7af4ef70924', '批量撤销', '', 100, '/stock/batch/undo', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('9ec8d21d-c5f2-4a27-a798-5cce346b1f41', '固件版本信息详情', '', 100, '/firmware/detail', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('9f96eaca-b44f-4600-86cf-6a370b339f44', '角色管理', '角色管理', 101, '', 'xtgl', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('a2983396-144e-42d2-beec-ee8d651b987a', '反馈问题类型', '', 100, '', '1e709f15-067c-4e57-a1ae-723aaf1feca1', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('a2d1aeda-fa85-478c-8856-c81f7b75d477', '下载', '', 100, '/equipment/log/download', '6d85d431-9f20-4a97-bc4a-e85a389ed8c7', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('aad47f62-61d8-4a37-9396-8b82232663bd', '反馈问题类型删除', '', 100, '/feedback/type/delete', 'a2983396-144e-42d2-beec-ee8d651b987a', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('ab7e9df5-a095-4bc6-a5fa-f5d567a34af1', '参数配置', '', 101, '', '1355be5e-81fa-4ec2-85c8-272df24ef6b8', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('b0b1ce52-c962-46a3-af02-af45f3a4d840', '设备信息', '', 103, '', '47a23d76-2244-4a29-b236-33ad2207fdb9', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('b11fb59b-4cee-4608-b69a-c2bfa7299d43', '修改手机号', '', 100, '/snuser/update/telephone', '939d7f32-bae4-4f32-93b4-df5e45f0544f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('b1513e6b-b7a8-48aa-8205-56f9ad5db04d', '审核固件', '', 100, '/firmware/audit', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('b5157a74-6d59-4c5a-8a21-eed6ab6239f5', '反馈问题记录', '', 108, '', '1e709f15-067c-4e57-a1ae-723aaf1feca1', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('becd1fbb-73e7-44fb-bef6-f46400ffb8ac', '添加轮播图', '', 100, '/home/banner/add', '861311d3-2c04-4480-88eb-fb3f925434f0', 1, '1', '2019-10-10 18:51:23', 'admin', '2019-10-10 18:51:23', 'admin');
INSERT INTO `t_resource` VALUES ('becf9e67-9a31-41a1-b79d-a8b59cca7886', '批量出库', '', 100, '/stock/batch/out', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c1c9d1f8-1a02-4be1-8484-ab49b582d0d2', '退换货管理', '', 102, '', '47a23d76-2244-4a29-b236-33ad2207fdb9', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c32ff894-77cf-4d69-bd10-5c3d88a74beb', '固件版本发布', '', 104, '', '148cd942-713f-4039-b560-5df0bf14f05b', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c3f44ce1-d811-44ce-adbb-dde1b0a1567a', '修改修复点', '', 100, '/firmware/fixPoint/update', '92da7803-5a15-4620-a7da-e92485a709b1', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c403e1e7-3274-47b8-a00a-a98c87b0d403', '审核测试删除', '', 100, '/firmware/auditTest/delete', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c4777cad-d608-49a6-bb6d-af56c2002fd4', '撤销', '', 100, '/app/version/undo', 'ddeb9c3f-3280-4d91-9f3a-c26ef0f59036', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', '库存信息', '', 101, '', '47a23d76-2244-4a29-b236-33ad2207fdb9', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c5604a8f-2c10-4b97-ae3a-4e5ccf0080e6', '批出模板下载', NULL, 100, '/stock/model/download/out', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c5604a8f-2c10-4b97-ae3a-4e5ccf0080e7', '批撤模板下载', NULL, 100, '/stock/model/download/undo', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c57d88a3-7f2d-400f-a998-d0e2db8c01db', '反馈记录导出', '', 100, '/feedback/record/export', 'b5157a74-6d59-4c5a-8a21-eed6ab6239f5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c6f26877-af0d-4233-b852-8ac2f0c9223f', '删除轮播图', '', 100, '/home/banner/delete', '861311d3-2c04-4480-88eb-fb3f925434f0', 1, '1', '2019-10-10 19:17:07', 'admin', '2019-10-10 19:17:07', 'admin');
INSERT INTO `t_resource` VALUES ('c7ad2283-25fa-44d5-889a-b0cf60097895', '用户冻结', '', 100, '/snuser/Freeze', '939d7f32-bae4-4f32-93b4-df5e45f0544f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('c96410e4-dfda-4b67-a3ad-82a8630c41be', '删除系统参数', NULL, 106, '/reference/delete', 'ab7e9df5-a095-4bc6-a5fa-f5d567a34af1', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('d0ca962d-5ffb-43c4-812e-01b7727d8a71', '首页', '', 101, '', NULL, 1, '0', '2019-10-10 18:48:12', 'admin', '2019-10-10 21:31:31', 'admin');
INSERT INTO `t_resource` VALUES ('d2516358-dbb7-493c-a37e-55f84820e068', '设备出库', '', 100, '/stock/out', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('d3740be8-fb45-42dc-bba6-ea889ca35b5d', '终端管理', '', 106, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('d5487f53-5245-42d4-ad93-6d575fdb838b', 'app版本修复点添加', '', 100, '/app/fixPoint/add', '81a64d80-32f0-4f65-90f1-1ee48d98fd2e', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('d62c8818-5241-4f5e-ac6d-c38739f6accb', '批量入库', '', 100, '/stock/batch/in', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('d75377f3-5856-4d4b-810c-a9ea87d86320', '备注详情', '', 100, '/equipment/log/detail', '6d85d431-9f20-4a97-bc4a-e85a389ed8c7', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('ddeb9c3f-3280-4d91-9f3a-c26ef0f59036', 'app版本信息', '', 112, '', '8bfb232c-aa3e-4838-bf30-aa6f3fa086e6', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('de12f964-3e15-41d6-8c63-c2a62ad89a91', '固件修复点添加', '', 100, '/firmware/fixPoint/add', '92da7803-5a15-4620-a7da-e92485a709b1', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('e4994757-e509-4ffa-9812-8e0a83832278', '删除经销商', '', 100, '/dealer/delete', '7948296f-7b57-4721-8ab2-d8e1cb6329d4', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('ebb8446e-30df-4300-9bf0-de5b3bd8c9bb', '发布', '', 100, '/app/version/deploy', 'ddeb9c3f-3280-4d91-9f3a-c26ef0f59036', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('f181a80a-8115-49a7-9167-786490ec3344', '退货', '', 100, '/goods/return', 'c1c9d1f8-1a02-4be1-8484-ab49b582d0d2', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('f2c189b7-bcbe-484d-a11e-de0aee2a27f3', '固件版本信息', '', 100, '', '148cd942-713f-4039-b560-5df0bf14f05b', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('f311d496-765d-4fc2-94d2-647c92836e54', '详情', '', 100, '/snuser/detail', '939d7f32-bae4-4f32-93b4-df5e45f0544f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('f4333fd7-03b7-4e81-9c06-23b3a9558cca', '审核测试', '', 100, '/firmware/auditTest', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('f91d912a-10eb-43f5-b72b-3fafd9b1cb23', '设备日志备注', '', 100, '/equipment/log/remark', '6d85d431-9f20-4a97-bc4a-e85a389ed8c7', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('fede90c6-1af9-4a59-8737-b38a3edcd0e4', '执行任务', '执行调度任务，一次只能执行一个调度任务。', 100, '/schedule/execute', '1c032c62-af6a-49b8-883d-6cb69c72987f', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('xtgl', '系统管理', '', 100, '', NULL, 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhgl', '系统用户管理', '', 102, '', 'xtgl', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglAddPage', '添加用户', NULL, 101, '/system/user/add', 'yhgl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglDelete', '删除用户', NULL, 103, '/system/user/delete', 'yhgl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglEditPage', '编辑用户', NULL, 102, '/system/user/update', 'yhgl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglEditPwdPage', '用户修改密码', NULL, 106, '/system/user/update/password', 'yhgl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglGrantPage', '用户授权', NULL, 105, '/system/user/grant', 'yhgl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('zygl', '资源管理', '管理系统中所有的菜单或功能', 100, '', 'xtgl', 0, '0', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('zyglAddPage', '添加资源', '', 101, '/resource/add', 'zygl', 0, '0', '2016-08-16 14:47:49', 'admin', '2019-11-07 10:21:20', 'admin');
INSERT INTO `t_resource` VALUES ('zyglEditPage', '编辑资源', NULL, 102, '/resource/update', 'zygl', 0, '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK(seq_trole)',
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
  `pid` varchar(36) CHARACTER SET gbk COLLATE gbk_chinese_ci NULL DEFAULT NULL COMMENT '父级角色',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `create_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人员',
  `modify_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人员',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('0', '超管', NULL, NULL, '2016-08-18 10:13:30', '2016-08-18 10:13:30', 'admin', 'admin');
INSERT INTO `t_role` VALUES ('1a127e2c-53c2-425a-a05b-85c1cff45ba4', '只有角色管理权限', '只有角色管理权限', '0', '2019-11-07 15:33:09', '2019-11-07 15:33:09', 'admin', 'admin');
INSERT INTO `t_role` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', '只有系统用户管理权限', '只有系统用户管理权限', '0', '2019-11-07 15:33:26', '2019-11-07 15:33:26', 'admin', 'admin');

-- ----------------------------
-- Table structure for t_role_tresource
-- ----------------------------
DROP TABLE IF EXISTS `t_role_tresource`;
CREATE TABLE `t_role_tresource`  (
  `trole_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  `tresource_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源id',
  PRIMARY KEY (`tresource_id`, `trole_id`) USING BTREE,
  INDEX `TROLE_ID`(`trole_id`) USING BTREE,
  INDEX `idx_roleresource_roldid`(`trole_id`) USING BTREE,
  INDEX `idx_roleresource_resourceid`(`tresource_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_role_tresource
-- ----------------------------
INSERT INTO `t_role_tresource` VALUES ('0', '01009c3f-72c1-4f91-9251-608ec67f1128');
INSERT INTO `t_role_tresource` VALUES ('0', '01b8f9a9-6886-4b8c-b4e1-eee9ed41fbe2');
INSERT INTO `t_role_tresource` VALUES ('0', '02b46b4d-f5db-4f37-b6ab-4f452964f0a8');
INSERT INTO `t_role_tresource` VALUES ('0', '030fe366-ad4f-442f-b197-7769e907c53c');
INSERT INTO `t_role_tresource` VALUES ('0', '03e8cba3-02aa-4655-81e7-3957293d6486');
INSERT INTO `t_role_tresource` VALUES ('0', '0439b13e-60ac-49c0-995d-c9df95f59fca');
INSERT INTO `t_role_tresource` VALUES ('0', '0800e85b-1885-47be-85ed-c87bf0f5b6f0');
INSERT INTO `t_role_tresource` VALUES ('1a127e2c-53c2-425a-a05b-85c1cff45ba4', '0800e85b-1885-47be-85ed-c87bf0f5b6f0');
INSERT INTO `t_role_tresource` VALUES ('0', '0beac010-8677-4704-8c2a-b8c1b7f8b2a7');
INSERT INTO `t_role_tresource` VALUES ('0', '0e1952ea-9d08-4397-bc1f-7139c14ba352');
INSERT INTO `t_role_tresource` VALUES ('0', '1355be5e-81fa-4ec2-85c8-272df24ef6b8');
INSERT INTO `t_role_tresource` VALUES ('0', '148cd942-713f-4039-b560-5df0bf14f05b');
INSERT INTO `t_role_tresource` VALUES ('0', '15ff29c8-ef83-4b7d-afdd-90b12ae644c8');
INSERT INTO `t_role_tresource` VALUES ('0', '17607501-4fba-47cf-83c3-fac95908f64c');
INSERT INTO `t_role_tresource` VALUES ('0', '192ef25e-o987-4erd-9576-857a9b82bf80');
INSERT INTO `t_role_tresource` VALUES ('0', '1ad09e9c-ad2e-45dd-868d-e8464f5824cf');
INSERT INTO `t_role_tresource` VALUES ('0', '1c032c62-af6a-49b8-883d-6cb69c72987f');
INSERT INTO `t_role_tresource` VALUES ('0', '1d434328-fe1b-4d95-9a5c-72d3eb18ba91');
INSERT INTO `t_role_tresource` VALUES ('1a127e2c-53c2-425a-a05b-85c1cff45ba4', '1d434328-fe1b-4d95-9a5c-72d3eb18ba91');
INSERT INTO `t_role_tresource` VALUES ('0', '1e709f15-067c-4e57-a1ae-723aaf1feca1');
INSERT INTO `t_role_tresource` VALUES ('0', '1f86c896-e4d3-4528-a980-af59c4a75b42');
INSERT INTO `t_role_tresource` VALUES ('0', '21499450-77d1-4cfe-9160-12b75f0bbfb3');
INSERT INTO `t_role_tresource` VALUES ('0', '217039b0-23b2-4528-82c7-520ce5c5142b');
INSERT INTO `t_role_tresource` VALUES ('0', '2537ff9e-bd5b-4f4a-961b-3ab5fc076312');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', '2537ff9e-bd5b-4f4a-961b-3ab5fc076312');
INSERT INTO `t_role_tresource` VALUES ('0', '2669c77e-6960-4e15-bcdc-7c52e422b4f8');
INSERT INTO `t_role_tresource` VALUES ('0', '26c48551-b6ac-4d48-a05e-640794cb7414');
INSERT INTO `t_role_tresource` VALUES ('0', '271687bf-2cc9-44db-b702-674c38bce27e');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', '271687bf-2cc9-44db-b702-674c38bce27e');
INSERT INTO `t_role_tresource` VALUES ('0', '336b2fd2-1aa2-43a8-8853-1feae5fbe462');
INSERT INTO `t_role_tresource` VALUES ('0', '37ec88e8-7455-4a2a-8500-cd7c6fae5ec3');
INSERT INTO `t_role_tresource` VALUES ('0', '38c00e40-bc47-42f9-88b2-3a4cf4492547');
INSERT INTO `t_role_tresource` VALUES ('0', '3935887b-156c-41bd-925c-0f68c32b2293');
INSERT INTO `t_role_tresource` VALUES ('0', '3c2020c3-9cf1-4fd6-96d4-3222a73d6205');
INSERT INTO `t_role_tresource` VALUES ('0', '3cb9ced0-dc81-45c4-a85c-c33941591911');
INSERT INTO `t_role_tresource` VALUES ('0', '3e939f0b-2e52-4e23-8f82-a774dfa72876');
INSERT INTO `t_role_tresource` VALUES ('0', '42eef4cc-2f5d-438f-9bd6-7256241f460c');
INSERT INTO `t_role_tresource` VALUES ('0', '4408e22c-fa95-4c47-96d4-693b3f3ff32d');
INSERT INTO `t_role_tresource` VALUES ('0', '441b4ed3-5724-4443-8c35-e60cfc1e7898');
INSERT INTO `t_role_tresource` VALUES ('0', '464dc704-1d7b-402f-907f-b803a42e6bfb');
INSERT INTO `t_role_tresource` VALUES ('0', '47a23d76-2244-4a29-b236-33ad2207fdb9');
INSERT INTO `t_role_tresource` VALUES ('0', '4a17dae2-1bb1-48a4-a090-c339ed886f36');
INSERT INTO `t_role_tresource` VALUES ('0', '4caff073-b379-44dd-84a0-986acb029f77');
INSERT INTO `t_role_tresource` VALUES ('1a127e2c-53c2-425a-a05b-85c1cff45ba4', '4caff073-b379-44dd-84a0-986acb029f77');
INSERT INTO `t_role_tresource` VALUES ('0', '50f8cb18-d3cc-46ec-9fa0-11c79d742bbc');
INSERT INTO `t_role_tresource` VALUES ('0', '56bf9eb1-3e7f-4726-b3be-c918e9d6ba86');
INSERT INTO `t_role_tresource` VALUES ('0', '56cfbe56-bf2f-4ff2-b1de-d413b5e5a867');
INSERT INTO `t_role_tresource` VALUES ('0', '56e628a9-b834-455c-b2ca-a38f97eea66a');
INSERT INTO `t_role_tresource` VALUES ('1a127e2c-53c2-425a-a05b-85c1cff45ba4', '56e628a9-b834-455c-b2ca-a38f97eea66a');
INSERT INTO `t_role_tresource` VALUES ('0', '57dc18d2-6b5d-4978-af05-366ff20b6717');
INSERT INTO `t_role_tresource` VALUES ('0', '592e825e-0b40-4e9d-9576-857a9b82bf80');
INSERT INTO `t_role_tresource` VALUES ('0', '5a70e5d3-e920-4c63-adbe-12949aae5aaf');
INSERT INTO `t_role_tresource` VALUES ('0', '5af40a37-8198-4a5b-aebc-e12e3b066370');
INSERT INTO `t_role_tresource` VALUES ('0', '5f264f75-fa71-4100-97ca-938d4b585c63');
INSERT INTO `t_role_tresource` VALUES ('0', '62c2954e-3686-499e-99db-8dae9a38a8dd');
INSERT INTO `t_role_tresource` VALUES ('0', '665f6ac3-a634-491f-aa9f-3446c373f29c');
INSERT INTO `t_role_tresource` VALUES ('0', '66afc37c-2a55-4a48-b45b-e7d907995c27');
INSERT INTO `t_role_tresource` VALUES ('0', '6d0b300b-7032-4b90-8a9c-6d5101a4fc19');
INSERT INTO `t_role_tresource` VALUES ('0', '6d85d431-9f20-4a97-bc4a-e85a389ed8c7');
INSERT INTO `t_role_tresource` VALUES ('0', '7576073e-4a09-4d79-bbb7-d3d48ae6e74f');
INSERT INTO `t_role_tresource` VALUES ('0', '7948296f-7b57-4721-8ab2-d8e1cb6329d4');
INSERT INTO `t_role_tresource` VALUES ('0', '7ab1ac8a-cf4e-41d3-b09d-b2914f76d35d');
INSERT INTO `t_role_tresource` VALUES ('0', '81a64d80-32f0-4f65-90f1-1ee48d98fd2e');
INSERT INTO `t_role_tresource` VALUES ('0', '848d8279-8689-4fe6-9338-8470df0bb809');
INSERT INTO `t_role_tresource` VALUES ('0', '8a048526-403c-46cc-8545-927b2642bb5d');
INSERT INTO `t_role_tresource` VALUES ('0', '8bfb232c-aa3e-4838-bf30-aa6f3fa086e6');
INSERT INTO `t_role_tresource` VALUES ('0', '8cd280dd-d1e8-48fd-a348-db733b16cbc1');
INSERT INTO `t_role_tresource` VALUES ('0', '909cf093-af63-4590-a402-ca4ed48470f5');
INSERT INTO `t_role_tresource` VALUES ('0', '91aa3145-abb8-4d53-b20f-d04e9f0d40c4');
INSERT INTO `t_role_tresource` VALUES ('0', '92da7803-5a15-4620-a7da-e92485a709b1');
INSERT INTO `t_role_tresource` VALUES ('0', '939d7f32-bae4-4f32-93b4-df5e45f0544f');
INSERT INTO `t_role_tresource` VALUES ('0', '942cf5ff-1f12-4f2b-abb2-aeb95d9b8296');
INSERT INTO `t_role_tresource` VALUES ('0', '95c24d6b-4d05-49cc-accc-d97cab901bdb');
INSERT INTO `t_role_tresource` VALUES ('0', '99416a15-e4e0-4600-920b-9f2390660ffe');
INSERT INTO `t_role_tresource` VALUES ('0', '996c8736-2a9a-450f-9b7c-09c657474951');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', '996c8736-2a9a-450f-9b7c-09c657474951');
INSERT INTO `t_role_tresource` VALUES ('0', '9b6a910d-31e2-473a-a98b-0a2a03966f1e');
INSERT INTO `t_role_tresource` VALUES ('0', '9c1214c8-7a47-45f9-853a-c7af4ef70924');
INSERT INTO `t_role_tresource` VALUES ('0', '9ec8d21d-c5f2-4a27-a798-5cce346b1f41');
INSERT INTO `t_role_tresource` VALUES ('0', '9f96eaca-b44f-4600-86cf-6a370b339f44');
INSERT INTO `t_role_tresource` VALUES ('1a127e2c-53c2-425a-a05b-85c1cff45ba4', '9f96eaca-b44f-4600-86cf-6a370b339f44');
INSERT INTO `t_role_tresource` VALUES ('0', 'a2983396-144e-42d2-beec-ee8d651b987a');
INSERT INTO `t_role_tresource` VALUES ('0', 'a2d1aeda-fa85-478c-8856-c81f7b75d477');
INSERT INTO `t_role_tresource` VALUES ('0', 'a925d771-4f30-4831-ad3b-abccb6630377');
INSERT INTO `t_role_tresource` VALUES ('0', 'aad47f62-61d8-4a37-9396-8b82232663bd');
INSERT INTO `t_role_tresource` VALUES ('0', 'ab7e9df5-a095-4bc6-a5fa-f5d567a34af1');
INSERT INTO `t_role_tresource` VALUES ('0', 'b0b1ce52-c962-46a3-af02-af45f3a4d840');
INSERT INTO `t_role_tresource` VALUES ('0', 'b11fb59b-4cee-4608-b69a-c2bfa7299d43');
INSERT INTO `t_role_tresource` VALUES ('0', 'b1513e6b-b7a8-48aa-8205-56f9ad5db04d');
INSERT INTO `t_role_tresource` VALUES ('0', 'b46f87ff-085c-42be-9b42-04ddc47d3018');
INSERT INTO `t_role_tresource` VALUES ('0', 'b5157a74-6d59-4c5a-8a21-eed6ab6239f5');
INSERT INTO `t_role_tresource` VALUES ('0', 'becf9e67-9a31-41a1-b79d-a8b59cca7886');
INSERT INTO `t_role_tresource` VALUES ('0', 'c1c9d1f8-1a02-4be1-8484-ab49b582d0d2');
INSERT INTO `t_role_tresource` VALUES ('0', 'c32ff894-77cf-4d69-bd10-5c3d88a74beb');
INSERT INTO `t_role_tresource` VALUES ('0', 'c3f44ce1-d811-44ce-adbb-dde1b0a1567a');
INSERT INTO `t_role_tresource` VALUES ('0', 'c403e1e7-3274-47b8-a00a-a98c87b0d403');
INSERT INTO `t_role_tresource` VALUES ('0', 'c4777cad-d608-49a6-bb6d-af56c2002fd4');
INSERT INTO `t_role_tresource` VALUES ('0', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e5');
INSERT INTO `t_role_tresource` VALUES ('0', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e6');
INSERT INTO `t_role_tresource` VALUES ('0', 'c5604a8f-2c10-4b97-ae3a-4e5ccf0080e7');
INSERT INTO `t_role_tresource` VALUES ('0', 'c57d88a3-7f2d-400f-a998-d0e2db8c01db');
INSERT INTO `t_role_tresource` VALUES ('0', 'c7398894-4df1-4c17-98ea-43e92f1b76b1');
INSERT INTO `t_role_tresource` VALUES ('0', 'c7ad2283-25fa-44d5-889a-b0cf60097895');
INSERT INTO `t_role_tresource` VALUES ('0', 'c96410e4-dfda-4b67-a3ad-82a8630c41be');
INSERT INTO `t_role_tresource` VALUES ('0', 'd2516358-dbb7-493c-a37e-55f84820e068');
INSERT INTO `t_role_tresource` VALUES ('0', 'd3740be8-fb45-42dc-bba6-ea889ca35b5d');
INSERT INTO `t_role_tresource` VALUES ('0', 'd5487f53-5245-42d4-ad93-6d575fdb838b');
INSERT INTO `t_role_tresource` VALUES ('0', 'd62c8818-5241-4f5e-ac6d-c38739f6accb');
INSERT INTO `t_role_tresource` VALUES ('0', 'd75377f3-5856-4d4b-810c-a9ea87d86320');
INSERT INTO `t_role_tresource` VALUES ('0', 'ddeb9c3f-3280-4d91-9f3a-c26ef0f59036');
INSERT INTO `t_role_tresource` VALUES ('0', 'de12f964-3e15-41d6-8c63-c2a62ad89a91');
INSERT INTO `t_role_tresource` VALUES ('0', 'e2a7854d-8a3c-453b-907a-1dcc8096c164');
INSERT INTO `t_role_tresource` VALUES ('0', 'e4994757-e509-4ffa-9812-8e0a83832278');
INSERT INTO `t_role_tresource` VALUES ('0', 'ebb8446e-30df-4300-9bf0-de5b3bd8c9bb');
INSERT INTO `t_role_tresource` VALUES ('0', 'f181a80a-8115-49a7-9167-786490ec3344');
INSERT INTO `t_role_tresource` VALUES ('0', 'f2c189b7-bcbe-484d-a11e-de0aee2a27f3');
INSERT INTO `t_role_tresource` VALUES ('0', 'f311d496-765d-4fc2-94d2-647c92836e54');
INSERT INTO `t_role_tresource` VALUES ('0', 'f4333fd7-03b7-4e81-9c06-23b3a9558cca');
INSERT INTO `t_role_tresource` VALUES ('0', 'f91d912a-10eb-43f5-b72b-3fafd9b1cb23');
INSERT INTO `t_role_tresource` VALUES ('0', 'fede90c6-1af9-4a59-8737-b38a3edcd0e4');
INSERT INTO `t_role_tresource` VALUES ('0', 'ff1487ae-c13a-45d6-8d2f-216382fb46c0');
INSERT INTO `t_role_tresource` VALUES ('0', 'xtgl');
INSERT INTO `t_role_tresource` VALUES ('1a127e2c-53c2-425a-a05b-85c1cff45ba4', 'xtgl');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', 'xtgl');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhgl');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', 'yhgl');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglAddPage');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', 'yhglAddPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglDelete');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', 'yhglDelete');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglEditPage');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', 'yhglEditPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglEditPwdPage');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', 'yhglEditPwdPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglGrantPage');
INSERT INTO `t_role_tresource` VALUES ('6aa07aa4-e49a-4554-be5e-79fe4243ecea', 'yhglGrantPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'zygl');
INSERT INTO `t_role_tresource` VALUES ('0', 'zyglAddPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'zyglEditPage');

-- ----------------------------
-- Table structure for t_schedule_log
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_log`;
CREATE TABLE `t_schedule_log`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT 'seq_schedule_log',
  `ksrq` datetime(0) NULL DEFAULT NULL COMMENT '开始执行时间',
  `jsrq` datetime(0) NULL DEFAULT NULL COMMENT '结束执行时间',
  `task_id` int(65) NULL DEFAULT NULL COMMENT '任务ID',
  `result` varchar(4) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '执行结果(0成功,1失败)',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1516 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule_log
-- ----------------------------
INSERT INTO `t_schedule_log` VALUES (1135, '2019-08-20 01:00:00', '2019-08-20 01:00:00', 6, '0');
INSERT INTO `t_schedule_log` VALUES (1136, '2019-08-20 04:00:00', '2019-08-20 04:00:00', 5, '0');
INSERT INTO `t_schedule_log` VALUES (1137, '2019-08-21 01:00:00', '2019-08-21 01:00:00', 6, '0');
INSERT INTO `t_schedule_log` VALUES (1138, '2019-08-21 04:00:00', '2019-08-21 04:00:00', 5, '0');
INSERT INTO `t_schedule_log` VALUES (1139, '2019-08-22 01:00:00', '2019-08-22 01:00:00', 6, '0');
INSERT INTO `t_schedule_log` VALUES (1140, '2019-08-22 04:00:00', '2019-08-22 04:00:00', 5, '0');
INSERT INTO `t_schedule_log` VALUES (1141, '2019-08-23 01:00:00', '2019-08-23 01:00:00', 6, '0');
INSERT INTO `t_schedule_log` VALUES (1142, '2019-08-23 02:00:00', '2019-08-23 02:00:00', 3, '0');
INSERT INTO `t_schedule_log` VALUES (1143, '2019-08-23 02:00:07', '2019-08-23 02:00:07', 3, '0');
INSERT INTO `t_schedule_log` VALUES (1144, '2019-08-23 03:00:00', '2019-08-23 03:00:00', 4, '0');
INSERT INTO `t_schedule_log` VALUES (1145, '2019-08-23 04:00:00', '2019-08-23 04:00:00', 5, '0');
INSERT INTO `t_schedule_log` VALUES (1146, '2019-08-24 01:00:00', '2019-08-24 01:00:00', 6, '0');
INSERT INTO `t_schedule_log` VALUES (1147, '2019-08-24 02:00:00', '2019-08-24 02:00:00', 3, '0');
INSERT INTO `t_schedule_log` VALUES (1148, '2019-08-24 02:00:07', '2019-08-24 02:00:07', 3, '0');
INSERT INTO `t_schedule_log` VALUES (1149, '2019-08-24 03:00:00', '2019-08-24 03:00:00', 4, '0');
INSERT INTO `t_schedule_log` VALUES (1150, '2019-08-24 04:00:00', '2019-08-24 04:00:00', 5, '0');
INSERT INTO `t_schedule_log` VALUES (1151, '2019-08-25 01:00:00', '2019-08-25 01:00:00', 6, '0');
INSERT INTO `t_schedule_log` VALUES (1152, '2019-08-25 02:00:00', '2019-08-25 02:00:00', 3, '0');
INSERT INTO `t_schedule_log` VALUES (1153, '2019-08-25 02:00:07', '2019-08-25 02:00:07', 3, '0');
INSERT INTO `t_schedule_log` VALUES (1154, '2019-08-25 03:00:00', '2019-08-25 03:00:00', 4, '0');
INSERT INTO `t_schedule_log` VALUES (1155, '2019-08-25 04:00:00', '2019-08-25 04:00:00', 5, '0');
INSERT INTO `t_schedule_log` VALUES (1156, '2019-08-26 01:00:00', '2019-08-26 01:00:00', 6, '0');
INSERT INTO `t_schedule_log` VALUES (1157, '2019-08-26 02:00:00', '2019-08-26 02:00:00', 3, '0');
INSERT INTO `t_schedule_log` VALUES (1158, '2019-08-26 02:00:07', '2019-08-26 02:00:07', 3, '0');
INSERT INTO `t_schedule_log` VALUES (1159, '2019-08-26 03:00:00', '2019-08-26 03:00:00', 4, '0');
INSERT INTO `t_schedule_log` VALUES (1160, '2019-08-26 04:00:00', '2019-08-26 04:00:00', 5, '0');
INSERT INTO `t_schedule_log` VALUES (1227, '2019-08-27 08:57:50', '2019-08-27 08:57:50', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1228, '2019-08-27 08:58:00', '2019-08-27 08:58:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1229, '2019-08-27 08:58:10', '2019-08-27 08:58:10', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1230, '2019-08-27 08:58:20', '2019-08-27 08:58:20', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1231, '2019-08-27 08:58:30', '2019-08-27 08:58:30', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1232, '2019-08-27 08:58:40', '2019-08-27 08:58:40', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1233, '2019-08-27 08:58:50', '2019-08-27 08:58:50', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1234, '2019-08-27 08:59:00', '2019-08-27 08:59:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1235, '2019-08-27 08:59:10', '2019-08-27 08:59:10', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1236, '2019-08-27 08:59:20', '2019-08-27 08:59:20', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1237, '2019-08-27 08:59:26', '2019-08-27 08:59:26', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1238, '2019-08-27 08:59:30', '2019-08-27 08:59:30', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1239, '2019-08-27 09:00:10', '2019-08-27 09:00:10', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1240, '2019-08-27 09:00:20', '2019-08-27 09:00:20', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1241, '2019-08-27 09:00:30', '2019-08-27 09:00:30', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1242, '2019-08-27 09:00:40', '2019-08-27 09:00:40', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1243, '2019-08-27 09:00:50', '2019-08-27 09:00:50', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1244, '2019-08-27 09:01:00', '2019-08-27 09:01:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1245, '2019-08-27 09:01:10', '2019-08-27 09:01:10', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1246, '2019-08-27 09:01:20', '2019-08-27 09:01:20', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1247, '2019-08-27 09:01:30', '2019-08-27 09:01:30', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1248, '2019-08-27 09:01:31', '2019-08-27 09:01:31', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1249, '2019-08-27 09:01:49', '2019-08-27 09:01:49', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1250, '2019-08-27 09:01:50', '2019-08-27 09:01:50', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1251, '2019-08-27 09:01:51', '2019-08-27 09:01:51', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1252, '2019-08-27 09:01:52', '2019-08-27 09:01:52', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1253, '2019-08-27 09:01:53', '2019-08-27 09:01:53', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1254, '2019-08-27 09:01:54', '2019-08-27 09:01:54', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1255, '2019-08-27 09:01:55', '2019-08-27 09:01:55', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1256, '2019-08-27 09:01:56', '2019-08-27 09:01:56', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1257, '2019-08-27 09:01:57', '2019-08-27 09:01:57', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1258, '2019-08-27 09:01:58', '2019-08-27 09:01:58', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1259, '2019-08-27 09:01:59', '2019-08-27 09:01:59', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1260, '2019-08-27 09:02:00', '2019-08-27 09:02:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1261, '2019-08-27 09:02:01', '2019-08-27 09:02:01', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1262, '2019-08-27 09:02:02', '2019-08-27 09:02:02', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1263, '2019-08-27 09:02:03', '2019-08-27 09:02:03', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1264, '2019-08-27 09:02:04', '2019-08-27 09:02:04', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1265, '2019-08-27 09:02:05', '2019-08-27 09:02:05', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1266, '2019-08-27 09:02:06', '2019-08-27 09:02:06', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1267, '2019-08-27 09:02:07', '2019-08-27 09:02:07', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1268, '2019-08-27 09:02:08', '2019-08-27 09:02:08', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1269, '2019-08-27 09:02:09', '2019-08-27 09:02:09', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1270, '2019-08-27 09:03:11', '2019-08-27 09:03:11', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1271, '2019-08-27 09:03:12', '2019-08-27 09:03:12', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1272, '2019-08-27 09:03:13', '2019-08-27 09:03:13', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1273, '2019-08-27 09:03:14', '2019-08-27 09:03:14', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1274, '2019-08-27 09:03:15', '2019-08-27 09:03:15', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1275, '2019-08-27 09:03:16', '2019-08-27 09:03:16', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1276, '2019-08-27 09:03:17', '2019-08-27 09:03:17', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1277, '2019-08-27 09:03:18', '2019-08-27 09:03:18', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1278, '2019-08-27 09:03:19', '2019-08-27 09:03:19', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1279, '2019-08-27 09:03:20', '2019-08-27 09:03:20', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1280, '2019-08-27 09:03:21', '2019-08-27 09:03:21', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1281, '2019-08-27 09:03:22', '2019-08-27 09:03:22', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1282, '2019-08-27 09:03:23', '2019-08-27 09:03:23', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1283, '2019-08-27 09:03:24', '2019-08-27 09:03:24', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1284, '2019-08-27 09:03:25', '2019-08-27 09:03:25', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1285, '2019-08-27 09:03:26', '2019-08-27 09:03:26', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1286, '2019-08-27 09:03:27', '2019-08-27 09:03:27', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1287, '2019-08-27 09:03:28', '2019-08-27 09:03:28', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1288, '2019-08-27 09:03:29', '2019-08-27 09:03:29', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1289, '2019-08-27 09:04:20', '2019-08-27 09:04:20', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1290, '2019-08-27 09:04:25', '2019-08-27 09:04:25', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1291, '2019-08-27 09:04:30', '2019-08-27 09:04:30', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1292, '2019-08-27 09:04:35', '2019-08-27 09:04:35', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1293, '2019-08-27 09:04:40', '2019-08-27 09:04:40', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1294, '2019-08-27 09:04:45', '2019-08-27 09:04:45', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1295, '2019-08-27 09:04:50', '2019-08-27 09:04:50', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1296, '2019-08-27 09:04:55', '2019-08-27 09:04:55', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1297, '2019-08-27 09:05:00', '2019-08-27 09:05:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1298, '2019-08-27 09:05:05', '2019-08-27 09:05:05', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1299, '2019-08-27 09:06:00', '2019-08-27 09:06:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1300, '2019-08-27 09:07:00', '2019-08-27 09:07:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1301, '2019-08-27 09:08:00', '2019-08-27 09:08:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1302, '2019-08-27 09:08:44', '2019-08-27 09:08:44', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1303, '2019-08-27 09:10:00', '2019-08-27 09:10:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1304, '2019-08-30 11:23:00', '2019-08-30 11:23:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1305, '2019-08-30 11:24:00', '2019-08-30 11:24:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1306, '2019-08-30 11:25:00', '2019-08-30 11:25:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1307, '2019-08-30 11:26:00', '2019-08-30 11:26:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1308, '2019-08-30 11:27:00', '2019-08-30 11:27:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1309, '2019-08-30 11:28:00', '2019-08-30 11:28:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1310, '2019-08-30 11:29:00', '2019-08-30 11:29:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1311, '2019-08-30 11:30:00', '2019-08-30 11:30:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1312, '2019-08-30 11:31:00', '2019-08-30 11:31:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1313, '2019-08-30 11:32:00', '2019-08-30 11:32:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1314, '2019-08-30 11:33:00', '2019-08-30 11:33:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1315, '2019-08-30 11:34:00', '2019-08-30 11:34:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1316, '2019-08-30 11:35:00', '2019-08-30 11:35:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1317, '2019-08-30 11:36:00', '2019-08-30 11:36:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1318, '2019-08-30 11:37:00', '2019-08-30 11:37:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1319, '2019-08-30 11:38:00', '2019-08-30 11:38:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1320, '2019-08-30 11:39:00', '2019-08-30 11:39:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1321, '2019-08-30 11:40:00', '2019-08-30 11:40:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1322, '2019-08-30 11:41:00', '2019-08-30 11:41:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1323, '2019-08-30 11:42:00', '2019-08-30 11:42:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1324, '2019-08-30 11:43:00', '2019-08-30 11:43:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1325, '2019-08-30 11:44:00', '2019-08-30 11:44:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1326, '2019-08-30 11:45:00', '2019-08-30 11:45:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1327, '2019-08-30 11:46:00', '2019-08-30 11:46:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1328, '2019-08-30 11:47:00', '2019-08-30 11:47:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1329, '2019-08-30 11:48:00', '2019-08-30 11:48:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1330, '2019-08-30 11:49:00', '2019-08-30 11:49:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1331, '2019-08-30 11:50:00', '2019-08-30 11:50:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1332, '2019-08-30 11:51:00', '2019-08-30 11:51:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1333, '2019-08-30 11:52:00', '2019-08-30 11:52:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1334, '2019-08-30 11:53:00', '2019-08-30 11:53:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1335, '2019-08-30 11:54:00', '2019-08-30 11:54:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1336, '2019-08-30 11:55:00', '2019-08-30 11:55:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1337, '2019-08-30 11:56:00', '2019-08-30 11:56:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1338, '2019-08-30 11:57:00', '2019-08-30 11:57:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1339, '2019-08-30 11:58:00', '2019-08-30 11:58:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1340, '2019-08-30 11:59:00', '2019-08-30 11:59:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1341, '2019-08-30 12:00:00', '2019-08-30 12:00:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1342, '2019-08-30 12:01:00', '2019-08-30 12:01:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1343, '2019-08-30 12:02:00', '2019-08-30 12:02:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1344, '2019-08-30 12:03:00', '2019-08-30 12:03:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1345, '2019-08-30 12:04:00', '2019-08-30 12:04:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1346, '2019-08-30 12:05:00', '2019-08-30 12:05:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1347, '2019-08-30 12:06:00', '2019-08-30 12:06:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1348, '2019-08-30 12:07:00', '2019-08-30 12:07:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1349, '2019-08-30 13:32:00', '2019-08-30 13:32:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1350, '2019-08-30 13:33:00', '2019-08-30 13:33:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1351, '2019-08-30 13:34:00', '2019-08-30 13:34:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1352, '2019-08-30 13:35:00', '2019-08-30 13:35:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1353, '2019-08-30 13:36:00', '2019-08-30 13:36:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1354, '2019-08-30 19:31:00', '2019-08-30 19:31:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1355, '2019-09-06 15:09:25', '2019-09-06 15:09:25', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1356, '2019-09-06 15:09:30', '2019-09-06 15:09:30', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1357, '2019-09-06 15:09:35', '2019-09-06 15:09:35', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1358, '2019-09-06 15:09:40', '2019-09-06 15:09:40', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1359, '2019-09-06 15:09:45', '2019-09-06 15:09:45', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1360, '2019-09-06 15:09:50', '2019-09-06 15:09:50', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1361, '2019-09-06 15:09:55', '2019-09-06 15:09:55', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1362, '2019-09-06 15:10:00', '2019-09-06 15:10:00', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1363, '2019-09-06 15:10:05', '2019-09-06 15:10:05', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1364, '2019-09-06 15:10:10', '2019-09-06 15:10:10', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1365, '2019-09-06 15:10:15', '2019-09-06 15:10:15', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1366, '2019-09-06 15:10:20', '2019-09-06 15:10:20', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1367, '2019-09-06 15:10:25', '2019-09-06 15:10:25', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1368, '2019-09-06 15:10:30', '2019-09-06 15:10:30', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1369, '2019-09-06 15:10:35', '2019-09-06 15:10:35', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1370, '2019-09-06 15:10:40', '2019-09-06 15:10:40', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1371, '2019-09-06 15:10:45', '2019-09-06 15:10:45', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1372, '2019-09-06 15:10:50', '2019-09-06 15:10:50', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1373, '2019-09-06 15:10:55', '2019-09-06 15:10:55', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1374, '2019-09-06 15:11:00', '2019-09-06 15:11:00', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1375, '2019-09-06 15:11:05', '2019-09-06 15:11:05', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1376, '2019-09-06 15:11:10', '2019-09-06 15:11:10', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1377, '2019-09-06 15:11:15', '2019-09-06 15:11:15', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1378, '2019-09-06 15:11:20', '2019-09-06 15:11:20', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1379, '2019-09-06 15:11:25', '2019-09-06 15:11:25', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1380, '2019-09-06 15:11:30', '2019-09-06 15:11:30', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1381, '2019-09-06 15:11:35', '2019-09-06 15:11:35', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1382, '2019-09-06 15:11:40', '2019-09-06 15:11:40', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1383, '2019-09-06 15:11:45', '2019-09-08 15:11:45', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1384, '2019-09-06 15:11:50', '2019-09-08 15:11:50', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1385, '2019-09-06 15:11:55', '2019-09-08 15:11:55', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1386, '2019-09-06 15:12:00', '2019-09-08 15:12:00', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1387, '2019-09-06 15:12:05', '2019-09-06 15:12:05', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1388, '2019-09-06 15:12:10', '2019-09-06 15:12:10', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1389, '2019-09-06 15:12:15', '2019-09-06 15:12:15', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1390, '2019-09-06 15:12:20', '2019-09-06 15:12:20', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1391, '2019-09-06 15:12:25', '2019-09-06 15:12:25', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1392, '2019-09-06 15:12:30', '2019-09-06 15:12:30', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1393, '2019-09-06 15:12:35', '2019-09-06 15:12:35', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1394, '2019-09-06 15:12:40', '2019-09-06 15:12:40', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1395, '2019-09-06 15:12:45', '2019-09-06 15:12:45', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1396, '2019-09-06 15:12:50', '2019-09-06 15:12:50', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1397, '2019-09-06 15:12:55', '2019-09-06 15:12:55', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1398, '2019-09-06 15:13:00', '2019-09-06 15:13:00', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1399, '2019-09-06 15:13:05', '2019-09-06 15:13:05', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1400, '2019-09-11 16:49:35', '2019-09-11 16:49:35', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1401, '2019-09-11 16:49:35', '2019-09-11 16:49:35', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1402, '2019-09-11 16:49:37', '2019-09-11 16:49:37', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1403, '2019-09-11 16:49:40', '2019-09-11 16:49:40', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1404, '2019-09-11 16:49:40', '2019-09-11 16:49:40', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1405, '2019-09-11 16:49:40', '2019-09-11 16:49:40', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1406, '2019-09-11 16:49:45', '2019-09-11 16:49:45', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1407, '2019-09-11 16:49:45', '2019-09-11 16:49:45', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1408, '2019-09-11 16:49:50', '2019-09-11 16:49:50', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1409, '2019-09-11 16:49:50', '2019-09-11 16:49:50', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1410, '2019-09-11 16:49:55', '2019-09-11 16:49:55', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1411, '2019-09-11 16:49:55', '2019-09-11 16:49:55', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1412, '2019-09-11 16:49:56', '2019-09-11 16:49:56', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1413, '2019-09-11 16:50:00', '2019-09-11 16:50:00', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1414, '2019-09-11 16:50:00', '2019-09-11 16:50:00', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1415, '2019-09-11 16:50:02', '2019-09-11 16:50:02', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1416, '2019-09-11 16:50:05', '2019-09-11 16:50:05', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1417, '2019-09-11 16:50:05', '2019-09-11 16:50:05', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1418, '2019-09-11 16:50:10', '2019-09-11 16:50:10', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1419, '2019-09-11 16:50:10', '2019-09-11 16:50:10', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1420, '2019-09-11 16:50:15', '2019-09-11 16:50:15', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1421, '2019-09-11 16:50:15', '2019-09-11 16:50:15', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1422, '2019-09-11 16:50:20', '2019-09-11 16:50:20', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1423, '2019-09-11 16:50:20', '2019-09-11 16:50:20', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1424, '2019-09-11 16:50:25', '2019-09-11 16:50:25', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1425, '2019-09-11 16:50:25', '2019-09-11 16:50:25', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1426, '2019-09-11 16:50:30', '2019-09-11 16:50:30', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1427, '2019-09-11 16:50:30', '2019-09-11 16:50:30', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1428, '2019-09-11 16:50:33', '2019-09-11 16:50:33', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1429, '2019-09-11 16:50:35', '2019-09-11 16:50:35', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1430, '2019-09-11 16:50:35', '2019-09-11 16:50:35', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1431, '2019-09-11 16:50:40', '2019-09-11 16:50:40', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1432, '2019-09-11 16:50:40', '2019-09-11 16:50:40', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1433, '2019-09-11 16:50:45', '2019-09-11 16:50:45', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1434, '2019-09-11 16:50:45', '2019-09-11 16:50:45', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1435, '2019-09-11 16:50:47', '2019-09-11 16:50:47', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1436, '2019-09-11 16:50:50', '2019-09-11 16:50:50', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1437, '2019-09-11 16:50:50', '2019-09-11 16:50:50', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1438, '2019-09-11 16:50:56', '2019-09-11 16:50:56', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1439, '2019-09-11 16:50:57', '2019-09-11 16:50:57', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1440, '2019-09-11 16:51:08', '2019-09-11 16:51:08', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1441, '2019-09-11 16:51:13', '2019-09-11 16:51:13', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1442, '2019-09-11 16:51:18', '2019-09-11 16:51:18', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1443, '2019-09-11 16:51:19', '2019-09-11 16:51:19', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1444, '2019-09-11 16:51:19', '2019-09-11 16:51:19', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1445, '2019-09-11 16:51:24', '2019-09-11 16:51:24', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1446, '2019-09-16 16:30:11', '2019-09-16 16:30:12', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1447, '2019-09-16 16:30:11', '2019-09-16 16:30:12', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1448, '2019-09-16 16:30:12', '2019-09-16 16:30:12', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1449, '2019-09-16 16:30:12', '2019-09-16 16:30:12', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1450, '2019-09-16 16:30:12', '2019-09-16 16:30:12', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1451, '2019-09-16 16:30:12', '2019-09-16 16:30:12', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1452, '2019-09-16 16:30:13', '2019-09-16 16:30:13', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1453, '2019-09-16 16:30:13', '2019-09-16 16:30:13', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1454, '2019-09-16 16:30:13', '2019-09-16 16:30:13', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1455, '2019-09-16 16:30:13', '2019-09-16 16:30:13', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1456, '2019-09-16 16:30:13', '2019-09-16 16:30:13', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1457, '2019-09-16 16:30:14', '2019-09-16 16:30:14', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1458, '2019-09-16 16:30:22', '2019-09-16 16:30:22', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1459, '2019-09-16 16:30:22', '2019-09-16 16:30:22', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1460, '2019-09-16 16:30:23', '2019-09-16 16:30:23', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1461, '2019-09-16 16:30:23', '2019-09-16 16:30:23', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1462, '2019-09-16 16:30:23', '2019-09-16 16:30:23', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1463, '2019-09-16 16:30:23', '2019-09-16 16:30:23', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1464, '2019-09-16 16:30:28', '2019-09-16 16:30:28', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1465, '2019-09-16 16:30:28', '2019-09-16 16:30:28', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1466, '2019-09-16 16:30:28', '2019-09-16 16:30:28', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1467, '2019-09-16 16:30:28', '2019-09-16 16:30:28', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1468, '2019-09-16 16:30:28', '2019-09-16 16:30:28', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1469, '2019-09-16 16:30:28', '2019-09-16 16:30:28', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1470, '2019-09-16 16:30:29', '2019-09-16 16:30:29', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1471, '2019-09-16 16:30:29', '2019-09-16 16:30:29', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1472, '2019-09-16 16:30:29', '2019-09-16 16:30:29', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1473, '2019-09-16 17:37:31', '2019-09-16 17:37:31', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1474, '2019-09-16 17:37:32', '2019-09-16 17:37:32', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1475, '2019-09-16 17:37:32', '2019-09-16 17:37:32', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1476, '2019-09-16 17:37:32', '2019-09-16 17:37:32', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1477, '2019-09-16 17:37:32', '2019-09-16 17:37:32', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1478, '2019-09-18 10:27:23', '2019-09-18 10:27:23', 3, '1');
INSERT INTO `t_schedule_log` VALUES (1479, '2019-09-18 10:27:26', '2019-09-18 10:27:26', 6, '1');
INSERT INTO `t_schedule_log` VALUES (1480, '2019-10-12 17:16:54', '2019-10-12 17:16:58', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1481, '2019-10-12 17:17:21', '2019-10-12 17:17:26', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1482, '2019-10-12 17:19:09', '2019-10-12 17:19:09', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1483, '2019-10-12 17:19:11', '2019-10-12 17:19:11', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1484, '2019-10-12 17:19:26', '2019-10-12 17:19:26', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1485, '2019-10-12 17:19:35', '2019-10-12 17:19:35', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1486, '2019-10-12 17:21:02', '2019-10-12 17:21:02', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1487, '2019-10-12 17:21:14', '2019-10-12 17:21:14', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1488, '2019-10-12 17:21:20', '2019-10-12 17:21:20', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1489, '2019-10-12 17:21:23', '2019-10-12 17:21:23', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1490, '2019-10-12 17:21:24', '2019-10-12 17:21:24', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1491, '2019-10-12 17:21:25', '2019-10-12 17:21:25', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1492, '2019-10-12 17:26:14', '2019-10-12 17:26:14', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1493, '2019-10-12 17:26:16', '2019-10-12 17:26:16', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1494, '2019-10-12 17:26:38', '2019-10-12 17:26:38', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1495, '2019-10-12 17:26:38', '2019-10-12 17:26:38', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1496, '2019-10-12 17:26:39', '2019-10-12 17:26:39', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1497, '2019-10-12 17:26:41', '2019-10-12 17:26:41', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1498, '2019-10-12 17:26:42', '2019-10-12 17:26:42', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1499, '2019-10-12 17:27:31', '2019-10-12 17:27:31', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1500, '2019-10-12 17:27:32', '2019-10-12 17:27:32', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1501, '2019-10-12 17:31:30', '2019-10-12 17:31:30', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1502, '2019-10-12 17:31:31', '2019-10-12 17:31:31', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1503, '2019-10-12 17:32:06', '2019-10-12 17:32:06', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1504, '2019-10-30 09:14:18', '2019-10-30 09:14:18', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1505, '2019-10-30 09:14:30', '2019-10-30 09:14:30', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1506, '2019-10-30 09:14:31', '2019-10-30 09:14:31', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1507, '2019-10-30 09:14:31', '2019-10-30 09:14:31', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1508, '2019-10-30 09:14:31', '2019-10-30 09:14:31', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1509, '2019-10-30 09:14:32', '2019-10-30 09:14:32', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1510, '2019-10-30 09:14:32', '2019-10-30 09:14:32', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1511, '2019-10-30 09:15:43', '2019-10-30 09:15:43', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1512, '2019-10-30 09:16:26', '2019-10-30 09:16:26', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1513, '2019-10-31 00:00:00', '2019-10-31 00:00:00', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1514, '2019-10-31 16:24:11', '2019-10-31 16:24:11', 7, '1');
INSERT INTO `t_schedule_log` VALUES (1515, '2019-10-31 16:24:26', '2019-10-31 16:24:26', 7, '1');

-- ----------------------------
-- Table structure for t_schedule_task
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_task`;
CREATE TABLE `t_schedule_task`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT '主键(seq_schedule_task)',
  `name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '任务名称',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务描述',
  `cron` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '克隆表达式',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '任务状态，0 启用，1 停用',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人员',
  `bzcode` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '业务码',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule_task
-- ----------------------------
INSERT INTO `t_schedule_task` VALUES (3, '数据清理', '目前只清理操作审计记录和调度任务记录', '0 0 0 1/1 * ? *', '1', '2016-05-04 13:18:01', 'admin', '2019-09-18 15:19:36', 'admin', 'database_clean');
INSERT INTO `t_schedule_task` VALUES (6, '设备日志清理', '设备日志清理', '0 0 0 1/1 * ? *', '1', '2018-10-23 13:18:01', 'admin', '2019-09-18 10:27:23', 'admin', 'equipmentLog_clean');
INSERT INTO `t_schedule_task` VALUES (7, 'cms文件清理', 'cms文件清理', '0 0 0 1/1 * ? *', '0', '2019-10-12 15:40:35', 'admin', '2019-10-30 09:13:38', 'admin', 'cms_clean');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user`  (
  `id` int(65) NOT NULL AUTO_INCREMENT COMMENT 'pk',
  `username` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '姓名',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `address` varchar(120) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '联系地址',
  `email` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '电子邮箱',
  `pwd` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '登录密码',
  `counts` int(65) NULL DEFAULT 0 COMMENT '登录密码错误次数',
  `status` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '状态1：正常，0：锁定，2：停用',
  `is_active` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '有效标识（Y：有效，N：无效）',
  `last_login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `pid` int(10) NULL DEFAULT NULL COMMENT '父级用户',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人员',
  `modify_pwd` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否已经修改密码:0未修改,1已修改',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 68 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '系统管理员', NULL, NULL, NULL, '0192023a7bbd73250516f069df18b500', 0, '1', 'Y', '2019-11-07 15:34:57', NULL, '2016-05-04 13:48:25', 'admin', '2019-09-01 10:39:22', 'admin', '1');
INSERT INTO `t_user` VALUES (67, 'liuming', 'Liu Ming', '17378114408', NULL, '694475668@qq.com', 'e22cd2d97412a9bc3af16a71ba04be29', 0, '1', 'N', NULL, 1, '2019-11-07 15:34:25', 'admin', '2019-11-07 15:34:25', 'admin', '0');

-- ----------------------------
-- Table structure for t_user_trole
-- ----------------------------
DROP TABLE IF EXISTS `t_user_trole`;
CREATE TABLE `t_user_trole`  (
  `tuser_id` int(65) NOT NULL COMMENT '用户id',
  `trole_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色id',
  PRIMARY KEY (`trole_id`, `tuser_id`) USING BTREE,
  INDEX `idx_roleuser_roldid`(`trole_id`) USING BTREE,
  INDEX `idx_roleuser_userid`(`tuser_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user_trole
-- ----------------------------
INSERT INTO `t_user_trole` VALUES (1, '0');
INSERT INTO `t_user_trole` VALUES (67, '1a127e2c-53c2-425a-a05b-85c1cff45ba4');

SET FOREIGN_KEY_CHECKS = 1;
