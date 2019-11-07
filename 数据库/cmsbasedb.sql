/*
 Navicat Premium Data Transfer

 Source Server         : dev
 Source Server Type    : MariaDB
 Source Server Version : 100219
 Source Host           : 10.20.22.31:3306
 Source Schema         : cmsbasedb

 Target Server Type    : MariaDB
 Target Server Version : 100219
 File Encoding         : 65001

 Date: 07/11/2019 15:52:32
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
) ENGINE = InnoDB AUTO_INCREMENT = 262 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_dictionary
-- ----------------------------
INSERT INTO `t_dictionary` VALUES (210, 'PART_SERIES', '100001', '金属之心笔系列', 'Supernote Pen Heart of Metal Series', 'admin', '2019-10-24 16:29:47', '配件系列');
INSERT INTO `t_dictionary` VALUES (211, 'PART_SERIES', '100002', '高密度塑壳笔系列', 'High density plastic shell pen series', 'admin', '2019-10-17 14:12:01', '配件系列');
INSERT INTO `t_dictionary` VALUES (212, 'PART_SERIES', '200001', '超级笔记专用笔芯', 'Super note refill', 'admin', '2019-10-17 14:13:11', '配件系列');
INSERT INTO `t_dictionary` VALUES (213, 'PART_SERIES', '300001', '斜十字纹真皮封套系列', 'Diagonal cross grain leather cover series', 'admin', '2019-10-19 00:42:57', '配件系列');
INSERT INTO `t_dictionary` VALUES (214, 'PART_SERIES', '300002', '帆布封套系列', 'Canvas cover series', 'admin', '2019-10-17 14:14:43', '配件系列');
INSERT INTO `t_dictionary` VALUES (215, 'PART_SERIES', '300003', '杜邦纸封套系列', 'DuPont paper case series', 'admin', '2019-10-24 16:42:34', '配件系列');
INSERT INTO `t_dictionary` VALUES (226, 'PRODUCT_TYPE', 'SN-A5', 'Supernote A5', 'Supernote A5', 'admin', '2019-10-18 17:05:52', '产品型号');
INSERT INTO `t_dictionary` VALUES (227, 'PRODUCT_TYPE', 'SN-A6', 'Supernote A6 Agile', 'Supernote A6 Agile', 'admin', '2019-10-18 17:05:55', '产品型号');
INSERT INTO `t_dictionary` VALUES (229, 'TRESOURCETYPE_ID', '0', '菜单', 'menu', 'admin', '2019-10-16 14:29:11', '资源类型');
INSERT INTO `t_dictionary` VALUES (230, 'TRESOURCETYPE_ID', '1', '功能', 'function', 'admin', '2019-10-16 14:31:01', '资源类型');
INSERT INTO `t_dictionary` VALUES (232, 'PART', '100001A', '钢琴黑', 'Piano Black', 'admin', '2019-10-24 16:45:00', '金属之心');
INSERT INTO `t_dictionary` VALUES (233, 'PART', '300001D', '斜十字纹真皮封套-A6-黑色', '斜十字纹真皮封套-A6-黑色', 'admin', '2019-10-18 23:01:42', '斜十字纹真皮封套系列-A6');
INSERT INTO `t_dictionary` VALUES (234, 'PART', '100001B', '陶瓷白', 'Ceramic White', 'admin', '2019-10-24 16:45:15', '金属之心');
INSERT INTO `t_dictionary` VALUES (235, 'PART', '300001C', '斜十字纹真皮封套-A5-黑色', '斜十字纹真皮封套-A5-黑色', 'admin', '2019-10-18 17:30:03', '斜十字纹真皮封套系列-A5');
INSERT INTO `t_dictionary` VALUES (236, 'PART', '100001C', '秋枫橙', 'Maple Orange', 'admin', '2019-10-24 16:45:28', '金属之心');
INSERT INTO `t_dictionary` VALUES (237, 'PART', '300001B', '斜十字纹真皮封套-A5-粉色', '斜十字纹真皮封套-A5-粉色', 'admin', '2019-10-18 17:29:58', '斜十字纹真皮封套系列-A5');
INSERT INTO `t_dictionary` VALUES (238, 'PART', '100001D', '贵族蓝', 'Royal Blue', 'admin', '2019-10-24 16:45:40', '金属之心');
INSERT INTO `t_dictionary` VALUES (239, 'PART', '300001A', '斜十字纹真皮封套-A5-蓝色', '斜十字纹真皮封套-A5-蓝色', 'admin', '2019-10-18 17:24:11', '斜十字纹真皮封套系列-A5');
INSERT INTO `t_dictionary` VALUES (240, 'PART', '100001E', '珊瑚红', 'Coral Red', 'admin', '2019-10-24 16:45:52', '金属之心');
INSERT INTO `t_dictionary` VALUES (241, 'PART', '200001A', '专用笔芯', 'EN-专用笔芯', 'admin', '2019-10-18 17:24:36', '超级笔记专用笔芯');
INSERT INTO `t_dictionary` VALUES (242, 'PART', '100001F', '波特酒红', 'Port Wine Red', 'admin', '2019-10-24 16:46:06', '金属之心');
INSERT INTO `t_dictionary` VALUES (243, 'PART', '100001G', '匹兹堡灰', 'Pittsburg Cloud', 'admin', '2019-10-24 16:46:18', '金属之心');
INSERT INTO `t_dictionary` VALUES (244, 'PART', '300002B', '帆布封套系列-黑色', '帆布封套系列-黑色', 'admin', '2019-10-18 17:25:28', '帆布封套系列');
INSERT INTO `t_dictionary` VALUES (245, 'PART', '100001H', '鱼子酱灰', 'Caviar Grey', 'admin', '2019-10-24 16:46:29', '金属之心');
INSERT INTO `t_dictionary` VALUES (246, 'PART', '100002C', '金色', '金色', 'admin', '2019-10-18 22:10:55', '高密度塑壳笔');
INSERT INTO `t_dictionary` VALUES (247, 'PART', '100002B', '米白色', '米白色', 'admin', '2019-10-18 22:10:45', '高密度塑壳笔');
INSERT INTO `t_dictionary` VALUES (248, 'PART', '100001I', '森林绿', 'Forest Green', 'admin', '2019-10-24 16:46:42', '金属之心');
INSERT INTO `t_dictionary` VALUES (249, 'PART', '100002A', '深灰', '深灰', 'admin', '2019-10-18 22:10:37', '高密度塑壳笔');
INSERT INTO `t_dictionary` VALUES (250, 'PART', '300002A', '帆布封套系列-深蓝', '帆布封套系列-深蓝', 'admin', '2019-10-18 17:26:28', '帆布封套系列');
INSERT INTO `t_dictionary` VALUES (251, 'PART', '100001J', '酒红波点', 'Polka Dots: Port Wine Red', 'admin', '2019-10-24 16:46:54', '金属之心');
INSERT INTO `t_dictionary` VALUES (252, 'PART', '100001K', '蓝色波点', 'Polka Dots: Peony Blue', 'admin', '2019-10-24 16:47:10', '金属之心');
INSERT INTO `t_dictionary` VALUES (253, 'PART', '300003A', '杜邦纸封套系列-A6-灰白', 'EN-杜邦纸封套系列-A6-灰白', 'admin', '2019-10-18 17:29:48', '杜邦纸封套系列');
INSERT INTO `t_dictionary` VALUES (254, 'PART', '300003B', '杜邦纸封套系列-A6-黑色', 'EN-杜邦纸封套系列-A6-黑色', 'admin', '2019-10-18 17:31:45', '杜邦纸封套系列');
INSERT INTO `t_dictionary` VALUES (255, 'PAGE_SERIAL', '1', '首页', '首页', 'admin', '2019-10-29 15:26:05', '页面编号');
INSERT INTO `t_dictionary` VALUES (256, 'PAGE_SERIAL', '2', 'FAQ', 'FAQ', 'admin', '2019-11-05 11:00:51', '页面编号');
INSERT INTO `t_dictionary` VALUES (257, 'PAGE_SERIAL', '3', '故障排除', '故障排除', 'admin', '2019-11-05 11:01:17', '页面编号');
INSERT INTO `t_dictionary` VALUES (258, 'PAGE_SERIAL', '4', '说明书下载', '说明书下载', 'admin', '2019-11-05 11:01:31', '页面编号');
INSERT INTO `t_dictionary` VALUES (259, 'PAGE_SERIAL', '5', '关于页', '关于页', 'admin', '2019-10-29 15:28:59', '页面编号');
INSERT INTO `t_dictionary` VALUES (260, 'SYSTEM_ID', '4000001', '好封套', 'good fengtao', 'admin', '2019-10-31 16:52:23', '配件系列');
INSERT INTO `t_dictionary` VALUES (261, 'PAGE_SERIAL', '6', '固件下载', '固件下载', 'admin', '2019-11-05 11:01:57', '页面编号');

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
) ENGINE = InnoDB AUTO_INCREMENT = 248 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_pwd_his
-- ----------------------------
INSERT INTO `t_pwd_his` VALUES (245, 'admin', '0192023a7bbd73250516f069df18b500', '2019-09-24 16:42:55');
INSERT INTO `t_pwd_his` VALUES (247, 'liuming', 'e22cd2d97412a9bc3af16a71ba04be29', '2019-10-16 10:25:12');

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
  `tresourcetype_id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '0-菜单，1-功能',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建用户',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改用户',
  INDEX `idx_resource_id`(`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_resource
-- ----------------------------
INSERT INTO `t_resource` VALUES ('xtgl', '系统管理', NULL, 100, NULL, NULL, '0', '2019-09-19 15:25:45', 'admin', NULL, NULL);
INSERT INTO `t_resource` VALUES ('zygl', '资源管理', '管理系统中所有的菜单或功能', 100, NULL, 'xtgl', '0', NULL, NULL, NULL, NULL);
INSERT INTO `t_resource` VALUES ('9f96eaca-b44f-4600-86cf-6a370b339f44', '角色管理', '角色管理', 101, NULL, 'xtgl', '0', '2019-09-24 09:40:39', 'admin', '2019-09-24 09:40:43', NULL);
INSERT INTO `t_resource` VALUES ('yhgl', '系统用户管理', NULL, 102, NULL, 'xtgl', '0', '2019-09-19 15:29:13', 'admin', NULL, NULL);
INSERT INTO `t_resource` VALUES ('zyglEditPage', '编辑资源', NULL, 102, '/resource/update', 'zygl', '1', '2019-09-19 15:31:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('42eef4cc-2f5d-438f-9bd6-7256241f460c', '删除资源', '', 106, '/resource/delete', 'zygl', '1', '2019-09-19 15:31:49', 'admin', '2019-09-16 14:32:19', 'admin');
INSERT INTO `t_resource` VALUES ('zyglAddPage', '添加资源', '', 101, '/resource/add', 'zygl', '1', '2016-08-16 14:47:49', 'admin', '2019-09-16 14:32:25', 'admin');
INSERT INTO `t_resource` VALUES ('4caff073-b379-44dd-84a0-986acb029f77', '编辑角色', '跳转到编辑角色页面', 103, '/role/update', '9f96eaca-b44f-4600-86cf-6a370b339f44', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('1d434328-fe1b-4d95-9a5c-72d3eb18ba91', '角色授权', NULL, 105, '/role/grant', '9f96eaca-b44f-4600-86cf-6a370b339f44', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('0800e85b-1885-47be-85ed-c87bf0f5b6f0', '添加角色', NULL, 102, '/role/add', '9f96eaca-b44f-4600-86cf-6a370b339f44', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('56e628a9-b834-455c-b2ca-a38f97eea66a', '删除角色', NULL, 104, '/role/delete', '9f96eaca-b44f-4600-86cf-6a370b339f44', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglAddPage', '添加用户', NULL, 101, '/system/user/add', 'yhgl', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglDelete', '删除用户', NULL, 103, '/system/user/delete', 'yhgl', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglEditPwdPage', '用户修改密码', NULL, 106, '/system/user/update/password', 'yhgl', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglGrantPage', '用户授权', NULL, 105, '/system/user/grant', 'yhgl', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('yhglEditPage', '编辑用户', NULL, 102, '/system/user/update', 'yhgl', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('271687bf-2cc9-44db-b702-674c38bce27e', '解锁', NULL, 108, '/system/user/unlock', 'yhgl', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('2537ff9e-bd5b-4f4a-961b-3ab5fc076312', '启用', NULL, 109, '/system/user/open', 'yhgl', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('996c8736-2a9a-450f-9b7c-09c657474951', '停用', NULL, 110, '/system/user/close', 'yhgl', '1', '2016-08-16 14:47:49', 'admin', '2016-08-16 14:47:49', 'admin');
INSERT INTO `t_resource` VALUES ('f6b78dc7-733b-4416-a203-a6f9812b947c', '数据字典', '', 100, '', 'xtgl', '0', '2019-10-10 18:15:45', 'admin', '2019-10-10 18:15:45', 'admin');
INSERT INTO `t_resource` VALUES ('313d69e9-8c0e-499e-b9ab-9595c7d3f7da', '添加数据字典', '', 100, '/dictionary/add', 'f6b78dc7-733b-4416-a203-a6f9812b947c', '1', '2019-10-10 18:16:56', 'admin', '2019-10-10 18:16:56', 'admin');
INSERT INTO `t_resource` VALUES ('fb51b98f-1c20-493c-b788-d7295d62f0bd', '修改数据字典', '', 100, '/dictionary/update', 'f6b78dc7-733b-4416-a203-a6f9812b947c', '1', '2019-10-10 18:17:26', 'admin', '2019-10-10 18:17:26', 'admin');
INSERT INTO `t_resource` VALUES ('2d9cd6b1-36ef-49bf-9625-79711069d1ad', '删除数据字典', '', 100, '/dictionary/delete', 'f6b78dc7-733b-4416-a203-a6f9812b947c', '1', '2019-10-10 18:18:04', 'admin', '2019-10-10 18:18:04', 'admin');
INSERT INTO `t_resource` VALUES ('861311d3-2c04-4480-88eb-fb3f925434f0', '轮播图', '', 100, '', 'd0ca962d-5ffb-43c4-812e-01b7727d8a71', '0', '2019-10-10 18:48:27', 'admin', '2019-10-10 18:48:27', 'admin');
INSERT INTO `t_resource` VALUES ('becd1fbb-73e7-44fb-bef6-f46400ffb8ac', '添加轮播图', '', 100, '/home/banner/add', '861311d3-2c04-4480-88eb-fb3f925434f0', '1', '2019-10-10 18:51:23', 'admin', '2019-10-10 18:51:23', 'admin');
INSERT INTO `t_resource` VALUES ('1304c3ef-e76a-4c5a-bc90-ba1bf752dc77', '修改轮播图', '', 100, '/home/banner/update', '861311d3-2c04-4480-88eb-fb3f925434f0', '1', '2019-10-10 19:16:33', 'admin', '2019-10-10 19:16:33', 'admin');
INSERT INTO `t_resource` VALUES ('c6f26877-af0d-4233-b852-8ac2f0c9223f', '删除轮播图', '', 100, '/home/banner/delete', '861311d3-2c04-4480-88eb-fb3f925434f0', '1', '2019-10-10 19:17:07', 'admin', '2019-10-10 19:17:07', 'admin');
INSERT INTO `t_resource` VALUES ('513d6ab7-1cc9-4b44-a2ff-be6431892264', '上线轮播图', '', 100, '/home/banner/online', '861311d3-2c04-4480-88eb-fb3f925434f0', '1', '2019-10-10 19:17:43', 'admin', '2019-10-10 19:17:43', 'admin');
INSERT INTO `t_resource` VALUES ('95e99815-1654-4582-905e-009f5dca1cc1', '下线轮播图', '', 100, '/home/banner/unline', '861311d3-2c04-4480-88eb-fb3f925434f0', '1', '2019-10-10 19:18:34', 'admin', '2019-10-10 19:18:34', 'admin');
INSERT INTO `t_resource` VALUES ('2bb446bf-02b7-4de8-a5be-44fc8d6ec52b', '荣获奖项轮播图', '', 101, '', 'd0ca962d-5ffb-43c4-812e-01b7727d8a71', '0', '2019-10-10 19:19:54', 'admin', '2019-10-10 21:33:24', 'admin');
INSERT INTO `t_resource` VALUES ('c4a61d15-d216-4c0c-8ba7-d6fbcc63deb6', '添加荣获奖项轮播图', '', 100, '/home/award/add', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b', '1', '2019-10-10 19:22:42', 'admin', '2019-10-10 19:22:42', 'admin');
INSERT INTO `t_resource` VALUES ('d322d8e3-4324-455e-b3a6-561315285be2', '修改荣获奖项轮播图', '', 100, '/home/award/update', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b', '1', '2019-10-10 19:24:08', 'admin', '2019-10-10 19:24:08', 'admin');
INSERT INTO `t_resource` VALUES ('9668f749-194b-440c-9bb8-d90e39d2259b', '删除荣誉奖项轮播图', '', 100, '/home/award/delete', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b', '1', '2019-10-10 19:24:43', 'admin', '2019-10-10 19:24:43', 'admin');
INSERT INTO `t_resource` VALUES ('a706b752-a4d3-4594-9145-55d3d8bfcd33', '上线荣获奖项轮播图', '', 100, '/home/award/online', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b', '1', '2019-10-10 19:27:39', 'admin', '2019-10-10 19:27:39', 'admin');
INSERT INTO `t_resource` VALUES ('1df0eea2-da1b-4b70-988c-055312dbd3bf', '下线荣获奖项轮播图', '', 100, '/home/award/unline', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b', '1', '2019-10-10 19:28:08', 'admin', '2019-10-10 19:28:08', 'admin');
INSERT INTO `t_resource` VALUES ('10939824-3716-4f87-aa80-f5f73ab9b6f0', '超级笔记页', '', 102, '', NULL, '0', '2019-10-10 19:33:19', 'admin', '2019-10-10 21:31:40', 'admin');
INSERT INTO `t_resource` VALUES ('085fdee2-e65a-4089-a52b-94bb814bf13e', 'Banner图', '', 100, '', '10939824-3716-4f87-aa80-f5f73ab9b6f0', '0', '2019-10-10 19:35:42', 'admin', '2019-10-10 19:35:42', 'admin');
INSERT INTO `t_resource` VALUES ('76c0d71e-ae3e-4897-95b7-e8af428c8496', '产品详情', '', 101, '', '10939824-3716-4f87-aa80-f5f73ab9b6f0', '0', '2019-10-10 19:35:59', 'admin', '2019-10-10 21:33:42', 'admin');
INSERT INTO `t_resource` VALUES ('614744e1-7476-4b1c-819b-bc781f086922', '新增Banner图', '', 100, '/product/banner/add', '085fdee2-e65a-4089-a52b-94bb814bf13e', '1', '2019-10-10 19:45:04', 'admin', '2019-10-10 19:45:04', 'admin');
INSERT INTO `t_resource` VALUES ('6721a73f-7c01-4b34-8115-d5240c80f7b1', '修改Banner图', '', 100, '/product/banner/update', '085fdee2-e65a-4089-a52b-94bb814bf13e', '1', '2019-10-10 19:45:32', 'admin', '2019-10-10 19:45:32', 'admin');
INSERT INTO `t_resource` VALUES ('ed223f68-a9d8-4d1b-9f60-963f29cbf2d8', '删除Banner图', '', 100, '/product/banner/delete', '085fdee2-e65a-4089-a52b-94bb814bf13e', '1', '2019-10-10 19:45:56', 'admin', '2019-10-10 19:45:56', 'admin');
INSERT INTO `t_resource` VALUES ('d0be9f5a-d1f9-4c4c-b954-f34334558a7a', '上线Banner图', '', 100, '/product/banner/online', '085fdee2-e65a-4089-a52b-94bb814bf13e', '1', '2019-10-10 19:46:43', 'admin', '2019-10-10 19:46:43', 'admin');
INSERT INTO `t_resource` VALUES ('056f964f-079d-4148-9f59-df4500affaec', '下线Banner图', '', 100, '/product/banner/unline', '085fdee2-e65a-4089-a52b-94bb814bf13e', '1', '2019-10-10 19:47:17', 'admin', '2019-10-10 19:47:17', 'admin');
INSERT INTO `t_resource` VALUES ('9928f0b6-e032-4a5e-a5b3-e3134880cc35', '添加产品详情', '', 100, '/product/description/add', '76c0d71e-ae3e-4897-95b7-e8af428c8496', '1', '2019-10-10 19:57:47', 'admin', '2019-10-10 19:57:47', 'admin');
INSERT INTO `t_resource` VALUES ('195db54e-940e-4153-84e4-2b20f64bd8dd', '修改产品详情', '', 100, '/product/description/update', '76c0d71e-ae3e-4897-95b7-e8af428c8496', '1', '2019-10-10 19:58:08', 'admin', '2019-10-10 19:58:08', 'admin');
INSERT INTO `t_resource` VALUES ('24b4d742-030f-4b9d-bd56-d7f13588f772', '删除产品详情', '', 100, '/product/description/delete', '76c0d71e-ae3e-4897-95b7-e8af428c8496', '1', '2019-10-10 19:58:30', 'admin', '2019-10-10 19:58:30', 'admin');
INSERT INTO `t_resource` VALUES ('d06afb34-2b48-4d9b-bc25-0b80a23783a0', '上线产品详情', '', 100, '/product/description/online', '76c0d71e-ae3e-4897-95b7-e8af428c8496', '1', '2019-10-10 19:58:56', 'admin', '2019-10-10 19:58:56', 'admin');
INSERT INTO `t_resource` VALUES ('dff139ff-bb0d-49fe-b35b-d4922466d107', '下线产品详情', '', 100, '/product/description/unline', '76c0d71e-ae3e-4897-95b7-e8af428c8496', '1', '2019-10-10 19:59:17', 'admin', '2019-10-10 19:59:17', 'admin');
INSERT INTO `t_resource` VALUES ('529d014d-2819-4894-a1a8-46901753e105', '套餐定制', '', 103, '', NULL, '0', '2019-10-10 20:00:50', 'admin', '2019-10-10 21:32:00', 'admin');
INSERT INTO `t_resource` VALUES ('1ecfc4c8-92af-4edf-8711-103162497463', '配件页', '', 104, '', NULL, '0', '2019-10-10 20:01:46', 'admin', '2019-10-10 21:32:11', 'admin');
INSERT INTO `t_resource` VALUES ('fdab41ad-098d-455c-b0cd-a52d11fab844', '服务页', '', 105, '', NULL, '0', '2019-10-10 20:02:06', 'admin', '2019-10-10 21:32:28', 'admin');
INSERT INTO `t_resource` VALUES ('72fc99ec-5799-491a-81f6-63d7832b83d5', '博客', '', 106, '', NULL, '0', '2019-10-10 20:02:16', 'admin', '2019-10-10 21:32:45', 'admin');
INSERT INTO `t_resource` VALUES ('41231e97-d408-442c-ade5-108abad4e0fc', '关于', '', 107, '', NULL, '0', '2019-10-10 20:02:24', 'admin', '2019-10-10 21:32:54', 'admin');
INSERT INTO `t_resource` VALUES ('ca24da55-af1a-4097-aaa6-65a1f13cb8d8', 'SEO', '', 108, '', NULL, '0', '2019-10-10 20:02:32', 'admin', '2019-10-10 21:33:02', 'admin');
INSERT INTO `t_resource` VALUES ('00b4ac75-272a-45b0-8a63-bf4068f480cb', '套餐定制', '', 100, '', '529d014d-2819-4894-a1a8-46901753e105', '0', '2019-10-10 20:04:40', 'admin', '2019-10-10 20:04:40', 'admin');
INSERT INTO `t_resource` VALUES ('067efc27-dbc4-4a21-baf3-7bcf97d02c47', '修改套餐定制', '', 100, '/customize/update', '00b4ac75-272a-45b0-8a63-bf4068f480cb', '1', '2019-10-10 20:06:16', 'admin', '2019-10-10 20:06:16', 'admin');
INSERT INTO `t_resource` VALUES ('2f39d8bb-0a84-492b-baa7-7edec1e6b4cf', '配件系列', '', 100, '', '1ecfc4c8-92af-4edf-8711-103162497463', '0', '2019-10-10 20:06:58', 'admin', '2019-10-10 20:06:58', 'admin');
INSERT INTO `t_resource` VALUES ('7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953', '配件信息', '', 101, '', '1ecfc4c8-92af-4edf-8711-103162497463', '0', '2019-10-10 20:07:17', 'admin', '2019-10-10 21:33:54', 'admin');
INSERT INTO `t_resource` VALUES ('6d7a8a7c-5954-4710-b725-1f3087051b2f', '添加配件系列', '', 100, '/part/series/add', '2f39d8bb-0a84-492b-baa7-7edec1e6b4cf', '1', '2019-10-10 20:31:24', 'admin', '2019-10-10 20:31:24', 'admin');
INSERT INTO `t_resource` VALUES ('c568fb5f-a641-4c94-be65-e77260f23b5a', '修改配件系列', '', 100, '/part/series/update', '2f39d8bb-0a84-492b-baa7-7edec1e6b4cf', '1', '2019-10-10 20:31:43', 'admin', '2019-10-10 20:31:43', 'admin');
INSERT INTO `t_resource` VALUES ('5ac11d53-cb52-43f2-a715-424c2d968064', '删除配件系列', '', 100, '/part/series/delete', '2f39d8bb-0a84-492b-baa7-7edec1e6b4cf', '1', '2019-10-10 20:32:07', 'admin', '2019-10-10 20:32:07', 'admin');
INSERT INTO `t_resource` VALUES ('29e28763-16d2-438a-8e85-1fea4c582650', '上线配件系列', '', 100, '/part/series/online', '2f39d8bb-0a84-492b-baa7-7edec1e6b4cf', '1', '2019-10-10 20:32:45', 'admin', '2019-10-10 20:32:45', 'admin');
INSERT INTO `t_resource` VALUES ('6b77402c-3629-40b8-a27a-8cb012da5eac', '下线配件系列', '', 100, '/part/series/unline', '2f39d8bb-0a84-492b-baa7-7edec1e6b4cf', '1', '2019-10-10 20:33:05', 'admin', '2019-10-10 20:33:05', 'admin');
INSERT INTO `t_resource` VALUES ('a1010b97-61e2-44be-a032-b18b9d3854c8', '添加配件信息', '', 100, '/part/add', '7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953', '1', '2019-10-10 20:33:49', 'admin', '2019-10-10 20:33:49', 'admin');
INSERT INTO `t_resource` VALUES ('446d1900-9c09-4fd4-a4c6-d9544d3f1505', '修改配件信息', '', 100, '/part/update', '7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953', '1', '2019-10-10 20:36:05', 'admin', '2019-10-10 20:36:05', 'admin');
INSERT INTO `t_resource` VALUES ('ddb262a7-9a11-4282-acb3-5fb20390910d', '删除配件信息', '', 100, '/part/delete', '7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953', '1', '2019-10-10 20:36:23', 'admin', '2019-10-10 20:36:23', 'admin');
INSERT INTO `t_resource` VALUES ('dfd026bb-a827-4c28-a7f9-a91e34621fb3', '上线配件信息', '', 100, '/part/online', '7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953', '1', '2019-10-10 20:36:52', 'admin', '2019-10-10 20:36:52', 'admin');
INSERT INTO `t_resource` VALUES ('256e65b9-cd0d-47fa-a750-cc0fc54c0f1e', '下线配件信息', '', 100, '/part/unline', '7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953', '1', '2019-10-10 20:37:12', 'admin', '2019-10-10 20:37:12', 'admin');
INSERT INTO `t_resource` VALUES ('a9552425-37fd-461b-9745-9bdc83daa406', 'FAQ/FMQ标题', '', 100, '', 'fdab41ad-098d-455c-b0cd-a52d11fab844', '0', '2019-10-10 20:40:00', 'admin', '2019-10-10 20:40:00', 'admin');
INSERT INTO `t_resource` VALUES ('e07d43ed-f676-445c-8dce-9388111fd1c1', 'FAQ/FMQ内容', '', 101, '', 'fdab41ad-098d-455c-b0cd-a52d11fab844', '0', '2019-10-10 20:40:31', 'admin', '2019-10-10 21:34:05', 'admin');
INSERT INTO `t_resource` VALUES ('a031f752-29f7-41dd-98d5-ea15a84c8670', '说明书/固件', '', 102, '', 'fdab41ad-098d-455c-b0cd-a52d11fab844', '0', '2019-10-10 20:40:47', 'admin', '2019-10-10 21:34:12', 'admin');
INSERT INTO `t_resource` VALUES ('405b6f8c-020b-4232-84da-7e289f209c7f', '反馈信息', '', 103, '', 'fdab41ad-098d-455c-b0cd-a52d11fab844', '0', '2019-10-10 20:41:08', 'admin', '2019-10-10 21:34:20', 'admin');
INSERT INTO `t_resource` VALUES ('b67b9633-7784-4bed-b44f-aec28afad3a8', '添加FAQ/FMQ标题', '', 100, '/faq/title/add', 'a9552425-37fd-461b-9745-9bdc83daa406', '1', '2019-10-10 20:44:23', 'admin', '2019-10-10 20:44:23', 'admin');
INSERT INTO `t_resource` VALUES ('37ef3ccc-1cdf-4da8-a262-395784581680', '修改FAQ/FMQ标题', '', 100, '/faq/title/update', 'a9552425-37fd-461b-9745-9bdc83daa406', '1', '2019-10-10 20:45:02', 'admin', '2019-10-10 20:45:02', 'admin');
INSERT INTO `t_resource` VALUES ('e1d89fd7-b48d-49d0-92f0-dbd958afd72d', '删除FAQ/FMQ标题', '', 100, '/faq/title/delete', 'a9552425-37fd-461b-9745-9bdc83daa406', '1', '2019-10-10 20:47:58', 'admin', '2019-10-10 20:47:58', 'admin');
INSERT INTO `t_resource` VALUES ('221f9028-e1cc-4c8f-8b6e-22982690849b', '上线FAQ/FMQ标题', '', 100, '/faq/title/online', 'a9552425-37fd-461b-9745-9bdc83daa406', '1', '2019-10-10 20:48:53', 'admin', '2019-10-10 20:48:53', 'admin');
INSERT INTO `t_resource` VALUES ('633a775f-a68d-455e-9670-2a1f96d8b649', '下线FAQ/FMQ标题', '', 100, '/faq/title/unline', 'a9552425-37fd-461b-9745-9bdc83daa406', '1', '2019-10-10 20:49:35', 'admin', '2019-10-10 20:49:35', 'admin');
INSERT INTO `t_resource` VALUES ('5e298172-977e-4591-af01-0df3e5da0c1b', '添加FAQ/FMQ内容', '', 100, '/faq/item/add', 'e07d43ed-f676-445c-8dce-9388111fd1c1', '1', '2019-10-10 20:52:11', 'admin', '2019-10-10 20:52:11', 'admin');
INSERT INTO `t_resource` VALUES ('632721c1-2f14-424c-8c9d-c143b8d275b0', '修改FAQ/FMQ内容', '', 100, '/faq/item/update', 'e07d43ed-f676-445c-8dce-9388111fd1c1', '1', '2019-10-10 20:52:45', 'admin', '2019-10-10 20:52:45', 'admin');
INSERT INTO `t_resource` VALUES ('5540153c-f566-49b5-a6f0-534896f38c22', '删除FAQ/FMQ内容', '', 100, '/faq/item/delete', 'e07d43ed-f676-445c-8dce-9388111fd1c1', '1', '2019-10-10 20:53:13', 'admin', '2019-10-10 20:53:13', 'admin');
INSERT INTO `t_resource` VALUES ('ede48195-ee9a-4787-93c5-9f07386ee91f', '上线FAQ/FMQ内容', '', 100, '/faq/item/online', 'e07d43ed-f676-445c-8dce-9388111fd1c1', '1', '2019-10-10 20:54:03', 'admin', '2019-10-10 20:54:03', 'admin');
INSERT INTO `t_resource` VALUES ('0913bdeb-763e-458f-b5c5-a0964d295649', '下线FAQ/FMQ内容', '', 100, '/faq/item/unline', 'e07d43ed-f676-445c-8dce-9388111fd1c1', '1', '2019-10-10 20:54:32', 'admin', '2019-10-10 20:54:32', 'admin');
INSERT INTO `t_resource` VALUES ('d3f86297-9207-4aac-8380-4a84636ba622', '添加说明书/固件', '', 100, '/firmware/manual/add', 'a031f752-29f7-41dd-98d5-ea15a84c8670', '1', '2019-10-10 20:58:40', 'admin', '2019-10-10 20:58:40', 'admin');
INSERT INTO `t_resource` VALUES ('50ee552e-d951-4fcf-8b21-a4ea57b3a8aa', '修改说明书/固件', '', 100, '/firmware/manual/update', 'a031f752-29f7-41dd-98d5-ea15a84c8670', '1', '2019-10-10 20:59:05', 'admin', '2019-10-10 20:59:05', 'admin');
INSERT INTO `t_resource` VALUES ('6abb126f-4418-4a3a-852a-fe96322127eb', '删除说明书/固件', '', 100, '/firmware/manual/delete', 'a031f752-29f7-41dd-98d5-ea15a84c8670', '1', '2019-10-10 20:59:45', 'admin', '2019-10-10 20:59:45', 'admin');
INSERT INTO `t_resource` VALUES ('c3b0d3f5-9feb-4ac1-bd2f-d4d915a086b6', '上线说明书/固件', '', 100, '/firmware/manual/online', 'a031f752-29f7-41dd-98d5-ea15a84c8670', '1', '2019-10-10 21:00:33', 'admin', '2019-10-10 21:00:33', 'admin');
INSERT INTO `t_resource` VALUES ('f37d40bf-976b-4c24-b6f4-0fede1077f36', '下线说明书/固件', '', 100, '/firmware/manual/unline', 'a031f752-29f7-41dd-98d5-ea15a84c8670', '1', '2019-10-10 21:01:12', 'admin', '2019-10-10 21:01:12', 'admin');
INSERT INTO `t_resource` VALUES ('f134fecd-d727-4b88-944c-dd2aabf79f56', '审核反馈信息', '', 100, '/feedback/record/audit', '405b6f8c-020b-4232-84da-7e289f209c7f', '1', '2019-10-10 21:03:59', 'admin', '2019-10-10 21:03:59', 'admin');
INSERT INTO `t_resource` VALUES ('d9ad47be-1651-4d95-af99-105548f8c214', '添加反馈备注', '', 100, '/feedback/record/update', '405b6f8c-020b-4232-84da-7e289f209c7f', '1', '2019-10-10 21:04:38', 'admin', '2019-10-10 21:04:38', 'admin');
INSERT INTO `t_resource` VALUES ('58f4cbc5-e227-4662-9e37-0fdf6aa28843', '博客列表', '', 100, '', '72fc99ec-5799-491a-81f6-63d7832b83d5', '0', '2019-10-10 21:11:20', 'admin', '2019-10-10 21:11:20', 'admin');
INSERT INTO `t_resource` VALUES ('2076f066-abcb-4f4f-866b-15b53bdcd524', '评论', '', 101, '', '72fc99ec-5799-491a-81f6-63d7832b83d5', '0', '2019-10-10 21:11:33', 'admin', '2019-10-10 21:34:31', 'admin');
INSERT INTO `t_resource` VALUES ('6b8f71ce-ced6-43e6-9ea3-3a8115bc58af', '全局SEO', '', 100, '', 'ca24da55-af1a-4097-aaa6-65a1f13cb8d8', '0', '2019-10-10 21:11:51', 'admin', '2019-10-10 21:11:51', 'admin');
INSERT INTO `t_resource` VALUES ('db906bef-b0ec-4f1d-b5c5-2571997e95fc', '关于', '', 100, '', '41231e97-d408-442c-ade5-108abad4e0fc', '0', '2019-10-10 21:12:03', 'admin', '2019-10-10 21:12:03', 'admin');
INSERT INTO `t_resource` VALUES ('7dc72dfb-ccf9-480a-badc-bf73286059ff', '添加全局SEO', '', 100, '/overall/seo/add', '6b8f71ce-ced6-43e6-9ea3-3a8115bc58af', '1', '2019-10-10 21:14:22', 'admin', '2019-10-10 21:14:22', 'admin');
INSERT INTO `t_resource` VALUES ('65cf8943-77e3-4607-8885-0a750a9bf5aa', '修改全局SEO', '', 100, '/overall/seo/update', '6b8f71ce-ced6-43e6-9ea3-3a8115bc58af', '1', '2019-10-10 21:14:43', 'admin', '2019-10-10 21:14:43', 'admin');
INSERT INTO `t_resource` VALUES ('62d200be-97e5-40cf-92f8-e644b6f15c15', '删除全局SEO', '', 100, '/overall/seo/delete', '6b8f71ce-ced6-43e6-9ea3-3a8115bc58af', '1', '2019-10-10 21:15:12', 'admin', '2019-10-10 21:15:12', 'admin');
INSERT INTO `t_resource` VALUES ('174e601b-d58b-4a00-92e7-d7c10698a7fa', '置顶', '', 100, '/comment/top', '2076f066-abcb-4f4f-866b-15b53bdcd524', '1', '2019-10-10 21:20:10', 'admin', '2019-10-10 21:20:10', 'admin');
INSERT INTO `t_resource` VALUES ('b80f848a-32d2-434d-abcb-242032439aa3', '审核评论', '', 100, '/comment/audit', '2076f066-abcb-4f4f-866b-15b53bdcd524', '1', '2019-10-10 21:20:37', 'admin', '2019-10-10 21:20:37', 'admin');
INSERT INTO `t_resource` VALUES ('f1a0c012-2bcc-4232-a7a7-26a0da3fe843', '添加博客', '', 100, '/blog/add', '58f4cbc5-e227-4662-9e37-0fdf6aa28843', '1', '2019-10-10 21:25:16', 'admin', '2019-10-10 21:25:16', 'admin');
INSERT INTO `t_resource` VALUES ('f64ae73f-b613-47d4-9346-8f0426a2eec2', '修改博客', '', 100, '/blog/update', '58f4cbc5-e227-4662-9e37-0fdf6aa28843', '1', '2019-10-10 21:25:35', 'admin', '2019-10-10 21:25:35', 'admin');
INSERT INTO `t_resource` VALUES ('fe312fb4-e3f1-49ed-b7b5-74f2deb37b77', '删除博客', '', 100, '/blog/delete', '58f4cbc5-e227-4662-9e37-0fdf6aa28843', '1', '2019-10-10 21:25:57', 'admin', '2019-10-10 21:25:57', 'admin');
INSERT INTO `t_resource` VALUES ('efcf2e2d-8315-4bfe-b521-fe54c1ddbf00', '上线博客', '', 100, '/blog/online', '58f4cbc5-e227-4662-9e37-0fdf6aa28843', '1', '2019-10-10 21:26:21', 'admin', '2019-10-10 21:26:21', 'admin');
INSERT INTO `t_resource` VALUES ('842dd10b-787a-40ac-8b84-10891803fb57', '下线博客', '', 100, '/blog/unline', '58f4cbc5-e227-4662-9e37-0fdf6aa28843', '1', '2019-10-10 21:26:40', 'admin', '2019-10-10 21:26:40', 'admin');
INSERT INTO `t_resource` VALUES ('2347ff36-313d-4c59-911b-d7f5ce530d38', '推荐博客', '', 100, '/blog/recommend', '58f4cbc5-e227-4662-9e37-0fdf6aa28843', '1', '2019-10-10 21:27:27', 'admin', '2019-10-10 21:27:27', 'admin');
INSERT INTO `t_resource` VALUES ('2a6ca7bb-0578-4a24-a9c5-e7afb8ca3bf3', '添加关于', '', 100, '/about/add', 'db906bef-b0ec-4f1d-b5c5-2571997e95fc', '1', '2019-10-10 21:28:37', 'admin', '2019-10-10 21:28:37', 'admin');
INSERT INTO `t_resource` VALUES ('82d5e44d-2ab5-43d5-aea8-3e80b699640f', '修改关于', '', 100, '/about/update', 'db906bef-b0ec-4f1d-b5c5-2571997e95fc', '1', '2019-10-10 21:28:55', 'admin', '2019-10-10 21:30:26', 'admin');
INSERT INTO `t_resource` VALUES ('023735c5-b341-49b7-addd-f67d4b3917e5', '删除关于', '', 100, '/about/delete', 'db906bef-b0ec-4f1d-b5c5-2571997e95fc', '1', '2019-10-10 21:29:15', 'admin', '2019-10-10 21:29:15', 'admin');
INSERT INTO `t_resource` VALUES ('d49caddf-71b9-4983-b253-1f6cd68d0adf', '上线关于', '', 100, '/about/online', 'db906bef-b0ec-4f1d-b5c5-2571997e95fc', '1', '2019-10-10 21:29:43', 'admin', '2019-10-10 21:29:43', 'admin');
INSERT INTO `t_resource` VALUES ('99fa9c50-32d9-411e-a4bd-00132c4cd3fc', '下线关于', '', 100, '/about/unline', 'db906bef-b0ec-4f1d-b5c5-2571997e95fc', '1', '2019-10-10 21:30:02', 'admin', '2019-10-10 21:30:02', 'admin');
INSERT INTO `t_resource` VALUES ('f1d7573c-c312-423a-a71c-cd3676aaa185', '启用定制', '', 100, '/customize/open', '00b4ac75-272a-45b0-8a63-bf4068f480cb', '1', '2019-10-10 21:50:21', 'admin', '2019-10-10 21:50:21', 'admin');
INSERT INTO `t_resource` VALUES ('ce6c33eb-8e91-404f-b271-aa8c6d8c7e54', '停用定制', '', 100, '/customize/close', '00b4ac75-272a-45b0-8a63-bf4068f480cb', '1', '2019-10-10 21:50:46', 'admin', '2019-10-10 21:50:46', 'admin');
INSERT INTO `t_resource` VALUES ('01d0319f-54c2-412e-ad65-708503b9c741', '新增产品页面', '', 100, '', '10939824-3716-4f87-aa80-f5f73ab9b6f0', '0', '2019-10-10 22:40:15', 'admin', '2019-10-10 22:40:15', 'admin');
INSERT INTO `t_resource` VALUES ('d8fa42bb-6669-47de-a37d-07dc5a2e3c2d', '修改产品页面', '', 100, '', '10939824-3716-4f87-aa80-f5f73ab9b6f0', '0', '2019-10-10 22:42:12', 'admin', '2019-10-10 22:42:12', 'admin');
INSERT INTO `t_resource` VALUES ('44644eee-96b1-4834-affb-387c2c452a05', '添加关于页面', '', 100, '', '41231e97-d408-442c-ade5-108abad4e0fc', '0', '2019-10-10 22:49:01', 'admin', '2019-10-10 22:49:01', 'admin');
INSERT INTO `t_resource` VALUES ('25c443d2-7197-445d-9e4e-cde44a3ab215', '修改关于页面', '', 100, '', '41231e97-d408-442c-ade5-108abad4e0fc', '0', '2019-10-10 22:49:13', 'admin', '2019-10-10 22:49:13', 'admin');
INSERT INTO `t_resource` VALUES ('790f1d77-96cf-45a1-ae73-116054bde1e5', '新增博客页面', '', 100, '', '72fc99ec-5799-491a-81f6-63d7832b83d5', '0', '2019-10-10 22:50:34', 'admin', '2019-10-10 22:50:34', 'admin');
INSERT INTO `t_resource` VALUES ('d3d2f9bb-d2d7-4418-b11d-b2c1af4f892a', '修改博客页面', '', 100, '', '72fc99ec-5799-491a-81f6-63d7832b83d5', '0', '2019-10-10 22:50:45', 'admin', '2019-10-10 22:50:45', 'admin');
INSERT INTO `t_resource` VALUES ('0a99eeed-6ad3-46d4-905a-332dc6ac2ba7', '新增内容页面', '', 100, '', 'fdab41ad-098d-455c-b0cd-a52d11fab844', '0', '2019-10-10 22:51:44', 'admin', '2019-10-10 22:51:44', 'admin');
INSERT INTO `t_resource` VALUES ('bc8ae4c3-6bb6-4df4-81be-339bda15a957', '修改内容页面', '', 100, '', 'fdab41ad-098d-455c-b0cd-a52d11fab844', '0', '2019-10-10 22:51:53', 'admin', '2019-10-10 22:51:53', 'admin');
INSERT INTO `t_resource` VALUES ('cbb71995-0292-40ce-b407-3fcd4999a347', '新增说明书/固件页面', '', 100, '', 'fdab41ad-098d-455c-b0cd-a52d11fab844', '0', '2019-10-10 22:52:53', 'admin', '2019-10-10 22:52:53', 'admin');
INSERT INTO `t_resource` VALUES ('e793a006-2bf0-444a-9cff-5ddad2e7ee23', '修改说明书/固件页面', '', 100, '', 'fdab41ad-098d-455c-b0cd-a52d11fab844', '0', '2019-10-10 22:53:06', 'admin', '2019-10-10 22:53:06', 'admin');
INSERT INTO `t_resource` VALUES ('782a5aae-757d-4e73-9d3e-ddd43fd88c3d', '新增配件信息页面', '', 100, '', '1ecfc4c8-92af-4edf-8711-103162497463', '0', '2019-10-10 22:54:54', 'admin', '2019-10-10 22:54:54', 'admin');
INSERT INTO `t_resource` VALUES ('cc5a53a3-a365-4c14-b3d2-5ed7842eeb7f', '修改配件信息页面', '', 100, '', '1ecfc4c8-92af-4edf-8711-103162497463', '0', '2019-10-10 22:55:05', 'admin', '2019-10-10 22:55:05', 'admin');
INSERT INTO `t_resource` VALUES ('ce3feae8-3781-4b68-92f5-f6717021e057', '新增配件系列页面', '', 100, '', '1ecfc4c8-92af-4edf-8711-103162497463', '0', '2019-10-10 22:55:16', 'admin', '2019-10-10 22:55:16', 'admin');
INSERT INTO `t_resource` VALUES ('8aaa516d-9a25-42d9-9cb3-f174185e29c8', '修改配件系列页面', '', 100, '', '1ecfc4c8-92af-4edf-8711-103162497463', '0', '2019-10-10 22:55:27', 'admin', '2019-10-10 22:55:27', 'admin');
INSERT INTO `t_resource` VALUES ('79cba8d4-00ec-4b73-a3ec-09779de23ec4', '排序轮播图', '', 100, '/home/banner/seq', '861311d3-2c04-4480-88eb-fb3f925434f0', '1', '2019-10-17 10:25:24', 'admin', '2019-10-17 14:23:18', 'admin');
INSERT INTO `t_resource` VALUES ('16b0bb12-385d-4e3f-abbd-7c0a2e9e40b5', '排序荣获奖项轮播图', '', 100, '/home/award/seq', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b', '1', '2019-10-17 10:27:53', 'admin', '2019-10-17 10:27:53', 'admin');
INSERT INTO `t_resource` VALUES ('55349665-079c-40c8-ad1e-7621e8b5846a', '排序产品详情', '', 100, '/product/description/seq', '76c0d71e-ae3e-4897-95b7-e8af428c8496', '1', '2019-10-17 10:29:48', 'admin', '2019-10-17 10:29:48', 'admin');
INSERT INTO `t_resource` VALUES ('7d6e1894-3797-4152-9bef-a56259121e4d', '排序配件系列', '', 100, '/part/series/seq', '2f39d8bb-0a84-492b-baa7-7edec1e6b4cf', '1', '2019-10-17 10:31:14', 'admin', '2019-10-17 10:31:14', 'admin');
INSERT INTO `t_resource` VALUES ('175339d6-39c4-4582-86b9-6143250b2953', '排序配件信息', '', 100, '/part/seq', '7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953', '1', '2019-10-17 10:31:55', 'admin', '2019-10-17 10:31:55', 'admin');
INSERT INTO `t_resource` VALUES ('6556b407-8823-4e64-b452-bb365e3df5c1', '排序FAQ/FMQ标题', '', 100, '/faq/title/seq', 'a9552425-37fd-461b-9745-9bdc83daa406', '1', '2019-10-17 10:33:02', 'admin', '2019-10-17 10:33:02', 'admin');
INSERT INTO `t_resource` VALUES ('d9945f40-2d39-421b-8d57-ed44be279cb8', '排序FAQ/FMQ内容', '', 100, '/faq/item/seq', 'e07d43ed-f676-445c-8dce-9388111fd1c1', '1', '2019-10-17 10:38:55', 'admin', '2019-10-17 10:38:55', 'admin');
INSERT INTO `t_resource` VALUES ('dbdc54c5-46a4-4d0f-b976-460a44401b8c', '排序说明书/固件', '', 100, '/firmware/manual/seq', 'a031f752-29f7-41dd-98d5-ea15a84c8670', '1', '2019-10-17 10:39:56', 'admin', '2019-10-17 10:39:56', 'admin');
INSERT INTO `t_resource` VALUES ('6fd6c1ae-3278-46c6-8db4-ccc375897df4', '排序博客列表', '', 100, '/blog/seq', '58f4cbc5-e227-4662-9e37-0fdf6aa28843', '1', '2019-10-17 10:40:41', 'admin', '2019-10-17 10:40:41', 'admin');
INSERT INTO `t_resource` VALUES ('be976bfc-d0fc-464b-aef7-126ff7f4567b', '排序关于', '', 100, '/about/seq', 'db906bef-b0ec-4f1d-b5c5-2571997e95fc', '1', '2019-10-17 10:41:17', 'admin', '2019-10-17 10:41:17', 'admin');
INSERT INTO `t_resource` VALUES ('c0e558f2-dc7a-47e4-ab5f-0835e8d6e479', '排序评论', '', 100, '/comment/seq', '2076f066-abcb-4f4f-866b-15b53bdcd524', '1', '2019-10-17 10:43:33', 'admin', '2019-10-17 10:43:33', 'admin');
INSERT INTO `t_resource` VALUES ('c3b65753-7620-425a-9caf-04ee829c8a38', '配件推荐', '', 100, '', '1ecfc4c8-92af-4edf-8711-103162497463', '0', '2019-10-21 11:10:32', 'admin', '2019-10-21 11:36:44', 'admin');
INSERT INTO `t_resource` VALUES ('79c7e356-1ec5-4d14-8870-12b62fc79b50', '上线配件推荐系列', '配件推荐系列', 100, '/part/partseriesrecommend/online', 'c3b65753-7620-425a-9caf-04ee829c8a38', '1', '2019-10-21 11:21:49', 'admin', '2019-10-21 11:21:49', 'admin');
INSERT INTO `t_resource` VALUES ('669ef1b1-3989-48c2-b802-3584efa57b72', '下线配件推荐系列', '配件推荐系列', 100, '/part/partseriesrecommend/unline', 'c3b65753-7620-425a-9caf-04ee829c8a38', '1', '2019-10-21 11:25:03', 'admin', '2019-10-21 11:25:03', 'admin');
INSERT INTO `t_resource` VALUES ('9d26a376-6651-4553-9456-bb55d0ae8298', '添加配件推荐系列', '配件推荐系列', 100, '/part/partseriesrecommend/add', 'c3b65753-7620-425a-9caf-04ee829c8a38', '1', '2019-10-21 11:25:57', 'admin', '2019-10-21 11:25:57', 'admin');
INSERT INTO `t_resource` VALUES ('4d5198a2-52a2-453f-9a59-a5bd0c65aa87', '修改配件推荐系列', '配件推荐系列', 100, '/part/partseriesrecommend/update', 'c3b65753-7620-425a-9caf-04ee829c8a38', '1', '2019-10-21 11:26:41', 'admin', '2019-10-21 11:26:41', 'admin');
INSERT INTO `t_resource` VALUES ('956f044c-4563-47b5-acc7-b0357ce7a604', '删除配件推荐系列', '配件推荐系列', 100, '/part/partseriesrecommend/delete', 'c3b65753-7620-425a-9caf-04ee829c8a38', '1', '2019-10-21 11:27:23', 'admin', '2019-10-21 11:27:23', 'admin');
INSERT INTO `t_resource` VALUES ('b956eb33-38bb-48a1-96c3-200048502464', '排序配件推荐系列', '配件推荐系列', 100, '/part/partseriesrecommend/seq', 'c3b65753-7620-425a-9caf-04ee829c8a38', '1', '2019-10-21 11:28:35', 'admin', '2019-10-21 11:28:35', 'admin');
INSERT INTO `t_resource` VALUES ('d0ca962d-5ffb-43c4-812e-01b7727d8a71', '首页', '', 101, '', NULL, '0', '2019-10-10 18:48:12', 'admin', '2019-10-10 21:31:31', 'admin');

-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role`  (
  `id` varchar(36) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT 'PK(seq_trole)',
  `name` varchar(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '角色描述',
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
INSERT INTO `t_role` VALUES ('0', '超管', NULL, '2019-09-19 15:22:18', NULL, 'admin', NULL);
INSERT INTO `t_role` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '55', '55', '2019-10-18 09:40:48', '2019-11-07 12:57:20', 'admin', 'admin');
INSERT INTO `t_role` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '首页', '12', '2019-10-16 09:05:02', '2019-10-16 11:39:06', 'admin', 'admin');
INSERT INTO `t_role` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '服务页', NULL, '2019-10-16 09:02:11', '2019-10-16 09:02:11', 'admin', 'admin');
INSERT INTO `t_role` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', '博客', NULL, '2019-10-16 09:02:16', '2019-10-16 09:02:16', 'admin', 'admin');
INSERT INTO `t_role` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '顶顶顶', NULL, '2019-10-16 09:03:51', '2019-10-16 09:03:51', 'admin', 'admin');
INSERT INTO `t_role` VALUES ('900e8b1e-737a-44a1-afda-5698d41fab93', '只有首页轮播图增改删功能', NULL, '2019-10-16 09:03:18', '2019-10-16 09:03:18', 'admin', 'admin');

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
INSERT INTO `t_role_tresource` VALUES ('0', '00b4ac75-272a-45b0-8a63-bf4068f480cb');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '00b4ac75-272a-45b0-8a63-bf4068f480cb');
INSERT INTO `t_role_tresource` VALUES ('0', '01d0319f-54c2-412e-ad65-708503b9c741');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '01d0319f-54c2-412e-ad65-708503b9c741');
INSERT INTO `t_role_tresource` VALUES ('0', '023735c5-b341-49b7-addd-f67d4b3917e5');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '023735c5-b341-49b7-addd-f67d4b3917e5');
INSERT INTO `t_role_tresource` VALUES ('0', '056f964f-079d-4148-9f59-df4500affaec');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '056f964f-079d-4148-9f59-df4500affaec');
INSERT INTO `t_role_tresource` VALUES ('0', '067efc27-dbc4-4a21-baf3-7bcf97d02c47');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '067efc27-dbc4-4a21-baf3-7bcf97d02c47');
INSERT INTO `t_role_tresource` VALUES ('0', '0800e85b-1885-47be-85ed-c87bf0f5b6f0');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '0800e85b-1885-47be-85ed-c87bf0f5b6f0');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '0800e85b-1885-47be-85ed-c87bf0f5b6f0');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '0800e85b-1885-47be-85ed-c87bf0f5b6f0');
INSERT INTO `t_role_tresource` VALUES ('0', '085fdee2-e65a-4089-a52b-94bb814bf13e');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '085fdee2-e65a-4089-a52b-94bb814bf13e');
INSERT INTO `t_role_tresource` VALUES ('0', '0913bdeb-763e-458f-b5c5-a0964d295649');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '0913bdeb-763e-458f-b5c5-a0964d295649');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '0913bdeb-763e-458f-b5c5-a0964d295649');
INSERT INTO `t_role_tresource` VALUES ('0', '0a99eeed-6ad3-46d4-905a-332dc6ac2ba7');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '0a99eeed-6ad3-46d4-905a-332dc6ac2ba7');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '0a99eeed-6ad3-46d4-905a-332dc6ac2ba7');
INSERT INTO `t_role_tresource` VALUES ('0', '10939824-3716-4f87-aa80-f5f73ab9b6f0');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '10939824-3716-4f87-aa80-f5f73ab9b6f0');
INSERT INTO `t_role_tresource` VALUES ('0', '1304c3ef-e76a-4c5a-bc90-ba1bf752dc77');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '1304c3ef-e76a-4c5a-bc90-ba1bf752dc77');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '1304c3ef-e76a-4c5a-bc90-ba1bf752dc77');
INSERT INTO `t_role_tresource` VALUES ('900e8b1e-737a-44a1-afda-5698d41fab93', '1304c3ef-e76a-4c5a-bc90-ba1bf752dc77');
INSERT INTO `t_role_tresource` VALUES ('0', '16b0bb12-385d-4e3f-abbd-7c0a2e9e40b5');
INSERT INTO `t_role_tresource` VALUES ('0', '174e601b-d58b-4a00-92e7-d7c10698a7fa');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '174e601b-d58b-4a00-92e7-d7c10698a7fa');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', '174e601b-d58b-4a00-92e7-d7c10698a7fa');
INSERT INTO `t_role_tresource` VALUES ('0', '175339d6-39c4-4582-86b9-6143250b2953');
INSERT INTO `t_role_tresource` VALUES ('0', '195db54e-940e-4153-84e4-2b20f64bd8dd');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '195db54e-940e-4153-84e4-2b20f64bd8dd');
INSERT INTO `t_role_tresource` VALUES ('0', '1d434328-fe1b-4d95-9a5c-72d3eb18ba91');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '1d434328-fe1b-4d95-9a5c-72d3eb18ba91');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '1d434328-fe1b-4d95-9a5c-72d3eb18ba91');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '1d434328-fe1b-4d95-9a5c-72d3eb18ba91');
INSERT INTO `t_role_tresource` VALUES ('0', '1df0eea2-da1b-4b70-988c-055312dbd3bf');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '1df0eea2-da1b-4b70-988c-055312dbd3bf');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '1df0eea2-da1b-4b70-988c-055312dbd3bf');
INSERT INTO `t_role_tresource` VALUES ('0', '1ecfc4c8-92af-4edf-8711-103162497463');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '1ecfc4c8-92af-4edf-8711-103162497463');
INSERT INTO `t_role_tresource` VALUES ('0', '2076f066-abcb-4f4f-866b-15b53bdcd524');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '2076f066-abcb-4f4f-866b-15b53bdcd524');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', '2076f066-abcb-4f4f-866b-15b53bdcd524');
INSERT INTO `t_role_tresource` VALUES ('0', '221f9028-e1cc-4c8f-8b6e-22982690849b');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '221f9028-e1cc-4c8f-8b6e-22982690849b');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '221f9028-e1cc-4c8f-8b6e-22982690849b');
INSERT INTO `t_role_tresource` VALUES ('0', '2347ff36-313d-4c59-911b-d7f5ce530d38');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '2347ff36-313d-4c59-911b-d7f5ce530d38');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', '2347ff36-313d-4c59-911b-d7f5ce530d38');
INSERT INTO `t_role_tresource` VALUES ('0', '24b4d742-030f-4b9d-bd56-d7f13588f772');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '24b4d742-030f-4b9d-bd56-d7f13588f772');
INSERT INTO `t_role_tresource` VALUES ('0', '2537ff9e-bd5b-4f4a-961b-3ab5fc076312');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '2537ff9e-bd5b-4f4a-961b-3ab5fc076312');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '2537ff9e-bd5b-4f4a-961b-3ab5fc076312');
INSERT INTO `t_role_tresource` VALUES ('0', '256e65b9-cd0d-47fa-a750-cc0fc54c0f1e');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '256e65b9-cd0d-47fa-a750-cc0fc54c0f1e');
INSERT INTO `t_role_tresource` VALUES ('0', '25c443d2-7197-445d-9e4e-cde44a3ab215');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '25c443d2-7197-445d-9e4e-cde44a3ab215');
INSERT INTO `t_role_tresource` VALUES ('0', '271687bf-2cc9-44db-b702-674c38bce27e');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '271687bf-2cc9-44db-b702-674c38bce27e');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '271687bf-2cc9-44db-b702-674c38bce27e');
INSERT INTO `t_role_tresource` VALUES ('0', '29e28763-16d2-438a-8e85-1fea4c582650');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '29e28763-16d2-438a-8e85-1fea4c582650');
INSERT INTO `t_role_tresource` VALUES ('0', '2a6ca7bb-0578-4a24-a9c5-e7afb8ca3bf3');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '2a6ca7bb-0578-4a24-a9c5-e7afb8ca3bf3');
INSERT INTO `t_role_tresource` VALUES ('0', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '2bb446bf-02b7-4de8-a5be-44fc8d6ec52b');
INSERT INTO `t_role_tresource` VALUES ('0', '2d9cd6b1-36ef-49bf-9625-79711069d1ad');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '2d9cd6b1-36ef-49bf-9625-79711069d1ad');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '2d9cd6b1-36ef-49bf-9625-79711069d1ad');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '2d9cd6b1-36ef-49bf-9625-79711069d1ad');
INSERT INTO `t_role_tresource` VALUES ('0', '2f39d8bb-0a84-492b-baa7-7edec1e6b4cf');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '2f39d8bb-0a84-492b-baa7-7edec1e6b4cf');
INSERT INTO `t_role_tresource` VALUES ('0', '313d69e9-8c0e-499e-b9ab-9595c7d3f7da');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '313d69e9-8c0e-499e-b9ab-9595c7d3f7da');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '313d69e9-8c0e-499e-b9ab-9595c7d3f7da');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '313d69e9-8c0e-499e-b9ab-9595c7d3f7da');
INSERT INTO `t_role_tresource` VALUES ('0', '37ef3ccc-1cdf-4da8-a262-395784581680');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '37ef3ccc-1cdf-4da8-a262-395784581680');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '37ef3ccc-1cdf-4da8-a262-395784581680');
INSERT INTO `t_role_tresource` VALUES ('0', '405b6f8c-020b-4232-84da-7e289f209c7f');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '405b6f8c-020b-4232-84da-7e289f209c7f');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '405b6f8c-020b-4232-84da-7e289f209c7f');
INSERT INTO `t_role_tresource` VALUES ('0', '41231e97-d408-442c-ade5-108abad4e0fc');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '41231e97-d408-442c-ade5-108abad4e0fc');
INSERT INTO `t_role_tresource` VALUES ('0', '42eef4cc-2f5d-438f-9bd6-7256241f460c');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '42eef4cc-2f5d-438f-9bd6-7256241f460c');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '42eef4cc-2f5d-438f-9bd6-7256241f460c');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '42eef4cc-2f5d-438f-9bd6-7256241f460c');
INSERT INTO `t_role_tresource` VALUES ('0', '44644eee-96b1-4834-affb-387c2c452a05');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '44644eee-96b1-4834-affb-387c2c452a05');
INSERT INTO `t_role_tresource` VALUES ('0', '446d1900-9c09-4fd4-a4c6-d9544d3f1505');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '446d1900-9c09-4fd4-a4c6-d9544d3f1505');
INSERT INTO `t_role_tresource` VALUES ('0', '4caff073-b379-44dd-84a0-986acb029f77');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '4caff073-b379-44dd-84a0-986acb029f77');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '4caff073-b379-44dd-84a0-986acb029f77');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '4caff073-b379-44dd-84a0-986acb029f77');
INSERT INTO `t_role_tresource` VALUES ('0', '4d5198a2-52a2-453f-9a59-a5bd0c65aa87');
INSERT INTO `t_role_tresource` VALUES ('0', '50ee552e-d951-4fcf-8b21-a4ea57b3a8aa');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '50ee552e-d951-4fcf-8b21-a4ea57b3a8aa');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '50ee552e-d951-4fcf-8b21-a4ea57b3a8aa');
INSERT INTO `t_role_tresource` VALUES ('0', '513d6ab7-1cc9-4b44-a2ff-be6431892264');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '513d6ab7-1cc9-4b44-a2ff-be6431892264');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '513d6ab7-1cc9-4b44-a2ff-be6431892264');
INSERT INTO `t_role_tresource` VALUES ('0', '529d014d-2819-4894-a1a8-46901753e105');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '529d014d-2819-4894-a1a8-46901753e105');
INSERT INTO `t_role_tresource` VALUES ('0', '55349665-079c-40c8-ad1e-7621e8b5846a');
INSERT INTO `t_role_tresource` VALUES ('0', '5540153c-f566-49b5-a6f0-534896f38c22');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '5540153c-f566-49b5-a6f0-534896f38c22');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '5540153c-f566-49b5-a6f0-534896f38c22');
INSERT INTO `t_role_tresource` VALUES ('0', '56e628a9-b834-455c-b2ca-a38f97eea66a');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '56e628a9-b834-455c-b2ca-a38f97eea66a');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '56e628a9-b834-455c-b2ca-a38f97eea66a');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '56e628a9-b834-455c-b2ca-a38f97eea66a');
INSERT INTO `t_role_tresource` VALUES ('0', '58f4cbc5-e227-4662-9e37-0fdf6aa28843');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '58f4cbc5-e227-4662-9e37-0fdf6aa28843');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', '58f4cbc5-e227-4662-9e37-0fdf6aa28843');
INSERT INTO `t_role_tresource` VALUES ('0', '5ac11d53-cb52-43f2-a715-424c2d968064');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '5ac11d53-cb52-43f2-a715-424c2d968064');
INSERT INTO `t_role_tresource` VALUES ('0', '5e298172-977e-4591-af01-0df3e5da0c1b');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '5e298172-977e-4591-af01-0df3e5da0c1b');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '5e298172-977e-4591-af01-0df3e5da0c1b');
INSERT INTO `t_role_tresource` VALUES ('0', '614744e1-7476-4b1c-819b-bc781f086922');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '614744e1-7476-4b1c-819b-bc781f086922');
INSERT INTO `t_role_tresource` VALUES ('0', '62d200be-97e5-40cf-92f8-e644b6f15c15');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '62d200be-97e5-40cf-92f8-e644b6f15c15');
INSERT INTO `t_role_tresource` VALUES ('0', '632721c1-2f14-424c-8c9d-c143b8d275b0');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '632721c1-2f14-424c-8c9d-c143b8d275b0');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '632721c1-2f14-424c-8c9d-c143b8d275b0');
INSERT INTO `t_role_tresource` VALUES ('0', '633a775f-a68d-455e-9670-2a1f96d8b649');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '633a775f-a68d-455e-9670-2a1f96d8b649');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '633a775f-a68d-455e-9670-2a1f96d8b649');
INSERT INTO `t_role_tresource` VALUES ('0', '6556b407-8823-4e64-b452-bb365e3df5c1');
INSERT INTO `t_role_tresource` VALUES ('0', '65cf8943-77e3-4607-8885-0a750a9bf5aa');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '65cf8943-77e3-4607-8885-0a750a9bf5aa');
INSERT INTO `t_role_tresource` VALUES ('0', '669ef1b1-3989-48c2-b802-3584efa57b72');
INSERT INTO `t_role_tresource` VALUES ('0', '6721a73f-7c01-4b34-8115-d5240c80f7b1');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '6721a73f-7c01-4b34-8115-d5240c80f7b1');
INSERT INTO `t_role_tresource` VALUES ('0', '6abb126f-4418-4a3a-852a-fe96322127eb');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '6abb126f-4418-4a3a-852a-fe96322127eb');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', '6abb126f-4418-4a3a-852a-fe96322127eb');
INSERT INTO `t_role_tresource` VALUES ('0', '6b77402c-3629-40b8-a27a-8cb012da5eac');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '6b77402c-3629-40b8-a27a-8cb012da5eac');
INSERT INTO `t_role_tresource` VALUES ('0', '6b8f71ce-ced6-43e6-9ea3-3a8115bc58af');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '6b8f71ce-ced6-43e6-9ea3-3a8115bc58af');
INSERT INTO `t_role_tresource` VALUES ('0', '6d7a8a7c-5954-4710-b725-1f3087051b2f');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '6d7a8a7c-5954-4710-b725-1f3087051b2f');
INSERT INTO `t_role_tresource` VALUES ('0', '6fd6c1ae-3278-46c6-8db4-ccc375897df4');
INSERT INTO `t_role_tresource` VALUES ('0', '70019c3b-1021-4d4b-9315-04ac60a64cc1');
INSERT INTO `t_role_tresource` VALUES ('0', '72fc99ec-5799-491a-81f6-63d7832b83d5');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '72fc99ec-5799-491a-81f6-63d7832b83d5');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', '72fc99ec-5799-491a-81f6-63d7832b83d5');
INSERT INTO `t_role_tresource` VALUES ('0', '76c0d71e-ae3e-4897-95b7-e8af428c8496');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '76c0d71e-ae3e-4897-95b7-e8af428c8496');
INSERT INTO `t_role_tresource` VALUES ('0', '782a5aae-757d-4e73-9d3e-ddd43fd88c3d');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '782a5aae-757d-4e73-9d3e-ddd43fd88c3d');
INSERT INTO `t_role_tresource` VALUES ('0', '790f1d77-96cf-45a1-ae73-116054bde1e5');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '790f1d77-96cf-45a1-ae73-116054bde1e5');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', '790f1d77-96cf-45a1-ae73-116054bde1e5');
INSERT INTO `t_role_tresource` VALUES ('0', '79c7e356-1ec5-4d14-8870-12b62fc79b50');
INSERT INTO `t_role_tresource` VALUES ('0', '79cba8d4-00ec-4b73-a3ec-09779de23ec4');
INSERT INTO `t_role_tresource` VALUES ('0', '7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '7c6f57d2-ee60-4de9-a52d-ac4cd0dc2953');
INSERT INTO `t_role_tresource` VALUES ('0', '7d6e1894-3797-4152-9bef-a56259121e4d');
INSERT INTO `t_role_tresource` VALUES ('0', '7dc72dfb-ccf9-480a-badc-bf73286059ff');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '7dc72dfb-ccf9-480a-badc-bf73286059ff');
INSERT INTO `t_role_tresource` VALUES ('0', '82d5e44d-2ab5-43d5-aea8-3e80b699640f');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '82d5e44d-2ab5-43d5-aea8-3e80b699640f');
INSERT INTO `t_role_tresource` VALUES ('0', '842dd10b-787a-40ac-8b84-10891803fb57');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '842dd10b-787a-40ac-8b84-10891803fb57');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', '842dd10b-787a-40ac-8b84-10891803fb57');
INSERT INTO `t_role_tresource` VALUES ('0', '861311d3-2c04-4480-88eb-fb3f925434f0');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '861311d3-2c04-4480-88eb-fb3f925434f0');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '861311d3-2c04-4480-88eb-fb3f925434f0');
INSERT INTO `t_role_tresource` VALUES ('900e8b1e-737a-44a1-afda-5698d41fab93', '861311d3-2c04-4480-88eb-fb3f925434f0');
INSERT INTO `t_role_tresource` VALUES ('0', '8aaa516d-9a25-42d9-9cb3-f174185e29c8');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '8aaa516d-9a25-42d9-9cb3-f174185e29c8');
INSERT INTO `t_role_tresource` VALUES ('0', '956f044c-4563-47b5-acc7-b0357ce7a604');
INSERT INTO `t_role_tresource` VALUES ('0', '95e99815-1654-4582-905e-009f5dca1cc1');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '95e99815-1654-4582-905e-009f5dca1cc1');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '95e99815-1654-4582-905e-009f5dca1cc1');
INSERT INTO `t_role_tresource` VALUES ('0', '9668f749-194b-440c-9bb8-d90e39d2259b');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '9668f749-194b-440c-9bb8-d90e39d2259b');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '9668f749-194b-440c-9bb8-d90e39d2259b');
INSERT INTO `t_role_tresource` VALUES ('0', '9928f0b6-e032-4a5e-a5b3-e3134880cc35');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '9928f0b6-e032-4a5e-a5b3-e3134880cc35');
INSERT INTO `t_role_tresource` VALUES ('0', '996c8736-2a9a-450f-9b7c-09c657474951');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '996c8736-2a9a-450f-9b7c-09c657474951');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '996c8736-2a9a-450f-9b7c-09c657474951');
INSERT INTO `t_role_tresource` VALUES ('0', '99fa9c50-32d9-411e-a4bd-00132c4cd3fc');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '99fa9c50-32d9-411e-a4bd-00132c4cd3fc');
INSERT INTO `t_role_tresource` VALUES ('0', '9d26a376-6651-4553-9456-bb55d0ae8298');
INSERT INTO `t_role_tresource` VALUES ('0', '9d479038-d5aa-4e15-b106-66aef639b504');
INSERT INTO `t_role_tresource` VALUES ('0', '9f96eaca-b44f-4600-86cf-6a370b339f44');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', '9f96eaca-b44f-4600-86cf-6a370b339f44');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', '9f96eaca-b44f-4600-86cf-6a370b339f44');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', '9f96eaca-b44f-4600-86cf-6a370b339f44');
INSERT INTO `t_role_tresource` VALUES ('0', 'a031f752-29f7-41dd-98d5-ea15a84c8670');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'a031f752-29f7-41dd-98d5-ea15a84c8670');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'a031f752-29f7-41dd-98d5-ea15a84c8670');
INSERT INTO `t_role_tresource` VALUES ('0', 'a1010b97-61e2-44be-a032-b18b9d3854c8');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'a1010b97-61e2-44be-a032-b18b9d3854c8');
INSERT INTO `t_role_tresource` VALUES ('0', 'a706b752-a4d3-4594-9145-55d3d8bfcd33');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'a706b752-a4d3-4594-9145-55d3d8bfcd33');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'a706b752-a4d3-4594-9145-55d3d8bfcd33');
INSERT INTO `t_role_tresource` VALUES ('0', 'a9552425-37fd-461b-9745-9bdc83daa406');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'a9552425-37fd-461b-9745-9bdc83daa406');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'a9552425-37fd-461b-9745-9bdc83daa406');
INSERT INTO `t_role_tresource` VALUES ('0', 'b67b9633-7784-4bed-b44f-aec28afad3a8');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'b67b9633-7784-4bed-b44f-aec28afad3a8');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'b67b9633-7784-4bed-b44f-aec28afad3a8');
INSERT INTO `t_role_tresource` VALUES ('0', 'b80f848a-32d2-434d-abcb-242032439aa3');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'b80f848a-32d2-434d-abcb-242032439aa3');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', 'b80f848a-32d2-434d-abcb-242032439aa3');
INSERT INTO `t_role_tresource` VALUES ('0', 'b956eb33-38bb-48a1-96c3-200048502464');
INSERT INTO `t_role_tresource` VALUES ('0', 'bc8ae4c3-6bb6-4df4-81be-339bda15a957');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'bc8ae4c3-6bb6-4df4-81be-339bda15a957');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'bc8ae4c3-6bb6-4df4-81be-339bda15a957');
INSERT INTO `t_role_tresource` VALUES ('0', 'be976bfc-d0fc-464b-aef7-126ff7f4567b');
INSERT INTO `t_role_tresource` VALUES ('0', 'becd1fbb-73e7-44fb-bef6-f46400ffb8ac');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'becd1fbb-73e7-44fb-bef6-f46400ffb8ac');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'becd1fbb-73e7-44fb-bef6-f46400ffb8ac');
INSERT INTO `t_role_tresource` VALUES ('900e8b1e-737a-44a1-afda-5698d41fab93', 'becd1fbb-73e7-44fb-bef6-f46400ffb8ac');
INSERT INTO `t_role_tresource` VALUES ('0', 'beffd39e-9f41-4773-a40b-9227b6d0b912');
INSERT INTO `t_role_tresource` VALUES ('0', 'c0e558f2-dc7a-47e4-ab5f-0835e8d6e479');
INSERT INTO `t_role_tresource` VALUES ('0', 'c3b0d3f5-9feb-4ac1-bd2f-d4d915a086b6');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'c3b0d3f5-9feb-4ac1-bd2f-d4d915a086b6');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'c3b0d3f5-9feb-4ac1-bd2f-d4d915a086b6');
INSERT INTO `t_role_tresource` VALUES ('0', 'c3b65753-7620-425a-9caf-04ee829c8a38');
INSERT INTO `t_role_tresource` VALUES ('0', 'c4a61d15-d216-4c0c-8ba7-d6fbcc63deb6');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'c4a61d15-d216-4c0c-8ba7-d6fbcc63deb6');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'c4a61d15-d216-4c0c-8ba7-d6fbcc63deb6');
INSERT INTO `t_role_tresource` VALUES ('0', 'c568fb5f-a641-4c94-be65-e77260f23b5a');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'c568fb5f-a641-4c94-be65-e77260f23b5a');
INSERT INTO `t_role_tresource` VALUES ('0', 'c6f26877-af0d-4233-b852-8ac2f0c9223f');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'c6f26877-af0d-4233-b852-8ac2f0c9223f');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'c6f26877-af0d-4233-b852-8ac2f0c9223f');
INSERT INTO `t_role_tresource` VALUES ('900e8b1e-737a-44a1-afda-5698d41fab93', 'c6f26877-af0d-4233-b852-8ac2f0c9223f');
INSERT INTO `t_role_tresource` VALUES ('0', 'ca24da55-af1a-4097-aaa6-65a1f13cb8d8');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'ca24da55-af1a-4097-aaa6-65a1f13cb8d8');
INSERT INTO `t_role_tresource` VALUES ('0', 'cbb71995-0292-40ce-b407-3fcd4999a347');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'cbb71995-0292-40ce-b407-3fcd4999a347');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'cbb71995-0292-40ce-b407-3fcd4999a347');
INSERT INTO `t_role_tresource` VALUES ('0', 'cc5a53a3-a365-4c14-b3d2-5ed7842eeb7f');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'cc5a53a3-a365-4c14-b3d2-5ed7842eeb7f');
INSERT INTO `t_role_tresource` VALUES ('0', 'ce3feae8-3781-4b68-92f5-f6717021e057');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'ce3feae8-3781-4b68-92f5-f6717021e057');
INSERT INTO `t_role_tresource` VALUES ('0', 'ce6c33eb-8e91-404f-b271-aa8c6d8c7e54');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'ce6c33eb-8e91-404f-b271-aa8c6d8c7e54');
INSERT INTO `t_role_tresource` VALUES ('0', 'd06afb34-2b48-4d9b-bc25-0b80a23783a0');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd06afb34-2b48-4d9b-bc25-0b80a23783a0');
INSERT INTO `t_role_tresource` VALUES ('0', 'd0be9f5a-d1f9-4c4c-b954-f34334558a7a');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd0be9f5a-d1f9-4c4c-b954-f34334558a7a');
INSERT INTO `t_role_tresource` VALUES ('0', 'd0ca962d-5ffb-43c4-812e-01b7727d8a71');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd0ca962d-5ffb-43c4-812e-01b7727d8a71');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'd0ca962d-5ffb-43c4-812e-01b7727d8a71');
INSERT INTO `t_role_tresource` VALUES ('900e8b1e-737a-44a1-afda-5698d41fab93', 'd0ca962d-5ffb-43c4-812e-01b7727d8a71');
INSERT INTO `t_role_tresource` VALUES ('0', 'd322d8e3-4324-455e-b3a6-561315285be2');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd322d8e3-4324-455e-b3a6-561315285be2');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'd322d8e3-4324-455e-b3a6-561315285be2');
INSERT INTO `t_role_tresource` VALUES ('0', 'd3d2f9bb-d2d7-4418-b11d-b2c1af4f892a');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd3d2f9bb-d2d7-4418-b11d-b2c1af4f892a');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', 'd3d2f9bb-d2d7-4418-b11d-b2c1af4f892a');
INSERT INTO `t_role_tresource` VALUES ('0', 'd3f86297-9207-4aac-8380-4a84636ba622');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd3f86297-9207-4aac-8380-4a84636ba622');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'd3f86297-9207-4aac-8380-4a84636ba622');
INSERT INTO `t_role_tresource` VALUES ('0', 'd49caddf-71b9-4983-b253-1f6cd68d0adf');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd49caddf-71b9-4983-b253-1f6cd68d0adf');
INSERT INTO `t_role_tresource` VALUES ('0', 'd8fa42bb-6669-47de-a37d-07dc5a2e3c2d');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd8fa42bb-6669-47de-a37d-07dc5a2e3c2d');
INSERT INTO `t_role_tresource` VALUES ('0', 'd9945f40-2d39-421b-8d57-ed44be279cb8');
INSERT INTO `t_role_tresource` VALUES ('0', 'd9ad47be-1651-4d95-af99-105548f8c214');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'd9ad47be-1651-4d95-af99-105548f8c214');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'd9ad47be-1651-4d95-af99-105548f8c214');
INSERT INTO `t_role_tresource` VALUES ('0', 'db906bef-b0ec-4f1d-b5c5-2571997e95fc');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'db906bef-b0ec-4f1d-b5c5-2571997e95fc');
INSERT INTO `t_role_tresource` VALUES ('0', 'dbdc54c5-46a4-4d0f-b976-460a44401b8c');
INSERT INTO `t_role_tresource` VALUES ('0', 'ddb262a7-9a11-4282-acb3-5fb20390910d');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'ddb262a7-9a11-4282-acb3-5fb20390910d');
INSERT INTO `t_role_tresource` VALUES ('0', 'dfd026bb-a827-4c28-a7f9-a91e34621fb3');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'dfd026bb-a827-4c28-a7f9-a91e34621fb3');
INSERT INTO `t_role_tresource` VALUES ('0', 'dff139ff-bb0d-49fe-b35b-d4922466d107');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'dff139ff-bb0d-49fe-b35b-d4922466d107');
INSERT INTO `t_role_tresource` VALUES ('0', 'e07d43ed-f676-445c-8dce-9388111fd1c1');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'e07d43ed-f676-445c-8dce-9388111fd1c1');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'e07d43ed-f676-445c-8dce-9388111fd1c1');
INSERT INTO `t_role_tresource` VALUES ('0', 'e1d89fd7-b48d-49d0-92f0-dbd958afd72d');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'e1d89fd7-b48d-49d0-92f0-dbd958afd72d');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'e1d89fd7-b48d-49d0-92f0-dbd958afd72d');
INSERT INTO `t_role_tresource` VALUES ('0', 'e793a006-2bf0-444a-9cff-5ddad2e7ee23');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'e793a006-2bf0-444a-9cff-5ddad2e7ee23');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'e793a006-2bf0-444a-9cff-5ddad2e7ee23');
INSERT INTO `t_role_tresource` VALUES ('0', 'ed223f68-a9d8-4d1b-9f60-963f29cbf2d8');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'ed223f68-a9d8-4d1b-9f60-963f29cbf2d8');
INSERT INTO `t_role_tresource` VALUES ('0', 'ede48195-ee9a-4787-93c5-9f07386ee91f');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'ede48195-ee9a-4787-93c5-9f07386ee91f');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'ede48195-ee9a-4787-93c5-9f07386ee91f');
INSERT INTO `t_role_tresource` VALUES ('0', 'efcf2e2d-8315-4bfe-b521-fe54c1ddbf00');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'efcf2e2d-8315-4bfe-b521-fe54c1ddbf00');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', 'efcf2e2d-8315-4bfe-b521-fe54c1ddbf00');
INSERT INTO `t_role_tresource` VALUES ('0', 'f134fecd-d727-4b88-944c-dd2aabf79f56');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'f134fecd-d727-4b88-944c-dd2aabf79f56');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'f134fecd-d727-4b88-944c-dd2aabf79f56');
INSERT INTO `t_role_tresource` VALUES ('0', 'f1a0c012-2bcc-4232-a7a7-26a0da3fe843');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'f1a0c012-2bcc-4232-a7a7-26a0da3fe843');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', 'f1a0c012-2bcc-4232-a7a7-26a0da3fe843');
INSERT INTO `t_role_tresource` VALUES ('0', 'f1a70b89-d393-42e1-bf7a-eedebdd914b9');
INSERT INTO `t_role_tresource` VALUES ('0', 'f1d7573c-c312-423a-a71c-cd3676aaa185');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'f1d7573c-c312-423a-a71c-cd3676aaa185');
INSERT INTO `t_role_tresource` VALUES ('0', 'f37d40bf-976b-4c24-b6f4-0fede1077f36');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'f37d40bf-976b-4c24-b6f4-0fede1077f36');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'f37d40bf-976b-4c24-b6f4-0fede1077f36');
INSERT INTO `t_role_tresource` VALUES ('0', 'f64ae73f-b613-47d4-9346-8f0426a2eec2');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'f64ae73f-b613-47d4-9346-8f0426a2eec2');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', 'f64ae73f-b613-47d4-9346-8f0426a2eec2');
INSERT INTO `t_role_tresource` VALUES ('0', 'f6b78dc7-733b-4416-a203-a6f9812b947c');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'f6b78dc7-733b-4416-a203-a6f9812b947c');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'f6b78dc7-733b-4416-a203-a6f9812b947c');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'f6b78dc7-733b-4416-a203-a6f9812b947c');
INSERT INTO `t_role_tresource` VALUES ('0', 'fb51b98f-1c20-493c-b788-d7295d62f0bd');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'fb51b98f-1c20-493c-b788-d7295d62f0bd');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'fb51b98f-1c20-493c-b788-d7295d62f0bd');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'fb51b98f-1c20-493c-b788-d7295d62f0bd');
INSERT INTO `t_role_tresource` VALUES ('0', 'fdab41ad-098d-455c-b0cd-a52d11fab844');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'fdab41ad-098d-455c-b0cd-a52d11fab844');
INSERT INTO `t_role_tresource` VALUES ('487c8358-d7ae-416b-8853-adbf22525f0b', 'fdab41ad-098d-455c-b0cd-a52d11fab844');
INSERT INTO `t_role_tresource` VALUES ('0', 'fe312fb4-e3f1-49ed-b7b5-74f2deb37b77');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'fe312fb4-e3f1-49ed-b7b5-74f2deb37b77');
INSERT INTO `t_role_tresource` VALUES ('5f8a8eb9-a40c-4b2e-bd51-953a6fce3628', 'fe312fb4-e3f1-49ed-b7b5-74f2deb37b77');
INSERT INTO `t_role_tresource` VALUES ('0', 'xtgl');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'xtgl');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'xtgl');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'xtgl');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhgl');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'yhgl');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'yhgl');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'yhgl');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglAddPage');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'yhglAddPage');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'yhglAddPage');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'yhglAddPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglDelete');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'yhglDelete');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'yhglDelete');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'yhglDelete');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglEditPage');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'yhglEditPage');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'yhglEditPage');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'yhglEditPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglEditPwdPage');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'yhglEditPwdPage');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'yhglEditPwdPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'yhglGrantPage');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'yhglGrantPage');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'yhglGrantPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'zygl');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'zygl');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'zygl');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'zygl');
INSERT INTO `t_role_tresource` VALUES ('0', 'zyglAddPage');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'zyglAddPage');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'zyglAddPage');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'zyglAddPage');
INSERT INTO `t_role_tresource` VALUES ('0', 'zyglEditPage');
INSERT INTO `t_role_tresource` VALUES ('0046ca79-2f5d-4c4d-b778-3e7d393d93a6', 'zyglEditPage');
INSERT INTO `t_role_tresource` VALUES ('083b6668-e90a-4750-ab42-65c50458b49d', 'zyglEditPage');
INSERT INTO `t_role_tresource` VALUES ('6879a979-6553-4f78-87e5-ff017749610a', 'zyglEditPage');

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
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '创建人员',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '修改时间',
  `update_user` varchar(40) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '修改人员',
  `modify_pwd` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT '0' COMMENT '是否已经修改密码:0未修改,1已修改',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_id`(`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 69 CHARACTER SET = gbk COLLATE = gbk_chinese_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES (1, 'admin', '系统管理员', NULL, NULL, NULL, '0192023a7bbd73250516f069df18b500', 0, '1', 'Y', '2019-11-07 12:57:24', '2019-09-19 15:20:17', 'admin', '2019-09-24 16:42:53', 'admin', '1');
INSERT INTO `t_user` VALUES (66, 'liuming', '刘明', '', NULL, '', 'e22cd2d97412a9bc3af16a71ba04be29', 0, '1', 'Y', '2019-11-07 12:57:39', '2019-10-16 09:37:26', 'admin', '2019-10-16 10:25:12', 'liuming', '1');
INSERT INTO `t_user` VALUES (67, 'zhangsan', 'zhangsan', '', NULL, '', '5f50afe0035cee993e797f0587aa4a81', 0, '1', 'N', NULL, '2019-10-16 09:51:53', 'admin', '2019-10-16 09:51:53', 'admin', '0');
INSERT INTO `t_user` VALUES (68, 'lisi', 'lisi', '', NULL, '', '2c7d5ff848cf1fc6b1bf1f14b88d869a', 0, '1', 'N', NULL, '2019-10-16 09:52:12', 'admin', '2019-10-16 09:52:12', 'admin', '0');

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
INSERT INTO `t_user_trole` VALUES (66, '0046ca79-2f5d-4c4d-b778-3e7d393d93a6');
INSERT INTO `t_user_trole` VALUES (67, '083b6668-e90a-4750-ab42-65c50458b49d');
INSERT INTO `t_user_trole` VALUES (68, '487c8358-d7ae-416b-8853-adbf22525f0b');

SET FOREIGN_KEY_CHECKS = 1;
