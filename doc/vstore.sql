CREATE DATABASE `vstore` CHARACTER SET 'utf8' COLLATE 'utf8_general_ci'; 
 
 

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionary`;
CREATE TABLE `sys_dictionary`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenement_id` bigint(20) NULL DEFAULT NULL COMMENT '所属租户',
  `type` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '数据类型',
  `type_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类型名称',
  `item_key` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数Key',
  `item_value` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数值',
  `order_no` int(5) NULL DEFAULT NULL COMMENT '顺序号',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态:0=停用,1=启用',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '跟新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_dictionary
-- ----------------------------

-- ----------------------------
-- Table structure for sys_employee
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee`;
CREATE TABLE `sys_employee`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenement_id` bigint(20) NULL DEFAULT NULL COMMENT '所属公司',
  `employee_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工编号',
  `employee_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工名称',
  `org_id` bigint(20) NULL DEFAULT NULL COMMENT '所属部门',
  `sex` int(11) NULL DEFAULT NULL COMMENT '性别:男=0,女=1',
  `mail` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `qq` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT 'QQ',
  `office_phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '办公电话',
  `phone` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '手机号码',
  `entry_time` datetime(0) NULL DEFAULT NULL COMMENT '入职时间',
  `birthday` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '出生日期',
  `id_card_no` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `education` int(5) NULL DEFAULT NULL COMMENT '学历:1=博士,2=研究生,3=本科,4=高中,5=其他',
  `graduate_institutions` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `graduate_date` datetime(0) NULL DEFAULT NULL COMMENT '毕业时间',
  `work_years` int(11) NULL DEFAULT NULL COMMENT '工作年限',
  `zip_code` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '邮编',
  `address` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '地址',
  `head_portrait` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '头像',
  `type` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工类型:正式工=0,临时工=1',
  `job_status` varchar(11) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '员工状态:在职=1,试用=0,离职=2',
  `remark` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态:0=停用,1=启用',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user` bigint(20) NULL DEFAULT NULL,
  `update_time` datetime(0) NULL DEFAULT NULL,
  `update_user` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '员工信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_employee
-- ----------------------------

-- ----------------------------
-- Table structure for sys_employee_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_employee_org`;
CREATE TABLE `sys_employee_org`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `employee_id` bigint(20) NULL DEFAULT NULL,
  `org_id` bigint(20) NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  `create_user` bigint(20) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_employee_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `tenement_id` bigint(20) NULL DEFAULT NULL COMMENT '所属租户',
  `menu_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单编号',
  `menu_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单名称',
  `href` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '菜单URL',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父级菜单',
  `current_seq` int(11) NULL DEFAULT 0 COMMENT '菜单当前排序',
  `image_path` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '图片路径',
  `type` int(11) NULL DEFAULT NULL COMMENT '菜单类型:菜单=1,功能=2',
  `remark` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '备注',
  `state` int(11) NULL DEFAULT 1 COMMENT '状态:0=停用,1=启用',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '跟新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '菜单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES (1, 1, 'system', '公司', '', NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (6, 1, '11', '11', '/11', NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (7, NULL, '11', '11', '/112', NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (16, NULL, '12', '211115', '2', 13, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (23, NULL, '12', '12', '12', NULL, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (29, NULL, 'menu_system', '系统管理', '/system', 1, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (30, NULL, 'menu_user', '用户管理', '/user', 29, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (31, NULL, 'menu_role', '角色管理', '/role', 29, 0, NULL, NULL, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (32, NULL, 'add', '新增', '/add', 30, 0, NULL, 2, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (33, NULL, 'delete', '删除', 'delete', 30, 0, NULL, 2, NULL, 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_menu` VALUES (34, NULL, '33', '33', '33', 29, 0, NULL, 1, NULL, 1, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenement_id` bigint(20) NULL DEFAULT NULL COMMENT '所属分公司',
  `permission_code` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '权限编码',
  `permission_name` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限名称',
  `type` int(11) NULL DEFAULT NULL COMMENT '权限类型:1=菜单权限,2=功能操作权限',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态:0=停用,1=启用',
  `menu_id` bigint(20) NULL DEFAULT NULL COMMENT '模块ID',
  `parent_id` bigint(20) NULL DEFAULT NULL COMMENT '父节点',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新人',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '跟新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES (1, 1, 'AllResource', '访问所有资源', 1, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (2, 1, 'login', '登录权限', 1, 1, NULL, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (8, NULL, 'menu_system', '系统管理', NULL, NULL, 29, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (9, NULL, 'menu_user', '用户管理', NULL, NULL, 30, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (10, NULL, 'menu_role', '角色管理', NULL, NULL, 31, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (11, NULL, 'add', '新增', NULL, NULL, 32, 10, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission` VALUES (12, NULL, 'delete', '删除', NULL, NULL, 33, 10, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_permission_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission_resource`;
CREATE TABLE `sys_permission_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `permission_id` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '权限ID',
  `resource_id` bigint(20) NOT NULL COMMENT '资源ID',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人ID',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更改人ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 8 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_permission_resource
-- ----------------------------
INSERT INTO `sys_permission_resource` VALUES (1, '1', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission_resource` VALUES (2, '2', 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission_resource` VALUES (3, '2', 3, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission_resource` VALUES (5, '8', 2, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission_resource` VALUES (6, '8', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_permission_resource` VALUES (7, '9', 3, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `resource_name` varchar(128) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '资源名称',
  `resource_url` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '资源URL',
  `module_name` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '功能模块',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES (1, '系统所有资源', '/**', '超级模块', 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (2, '登录资源', '/login/**', '超级模块', 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_resource` VALUES (3, '用户管理', '/user', '用户管理', 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tenement_id` bigint(11) NULL DEFAULT NULL COMMENT '所属公司',
  `role_code` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色编码',
  `role_name` varchar(30) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名称',
  `remark` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NULL DEFAULT NULL COMMENT '描述',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态:0=停用,1=启用',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES (1, 1, 'role_super_admin', '超级管理员', '超级管理员', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (3, 1, '122323121123123', '12123132', '121321231', 1, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role` VALUES (4, 1, '1212', '1221', '2211', 1, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  `permission_id` bigint(20) NULL DEFAULT NULL COMMENT '权限ID',
  `state` int(11) NULL DEFAULT NULL COMMENT '状态:0=停用,1=启用',
  `is_delete` int(11) NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  `create_user` bigint(20) NULL DEFAULT NULL COMMENT '创建人',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  `update_user` bigint(20) NULL DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 74 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES (2, 1, 1, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (60, 3, 1, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (61, 3, 2, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (62, 3, 8, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (63, 3, 14, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (64, 3, 9, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (67, 4, 8, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (68, 4, 14, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (69, 4, 9, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (70, 4, 10, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (71, 4, 11, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (72, 4, 12, NULL, 0, NULL, NULL, NULL, NULL);
INSERT INTO `sys_role_permission` VALUES (73, 4, 13, NULL, 0, NULL, NULL, NULL, NULL);

-- ----------------------------
-- Table structure for sys_tenement
-- ----------------------------
DROP TABLE IF EXISTS `sys_tenement`;
CREATE TABLE `sys_tenement`  (
  `id` bigint(20) NOT NULL,
  `tenement_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of sys_tenement
-- ----------------------------
INSERT INTO `sys_tenement` VALUES (1, '阿里巴巴');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `tenement_id` bigint(20) NOT NULL COMMENT '租户',
  `user_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名称',
  `login_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登录名称',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt_key` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加盐',
  `phone` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机',
  `is_manager` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '是否管理员:1=默认超级管理员,2=管理员,3=普通账号',
  `type` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号类型:1=平台,2=租户,3=客户',
  `relation_id` bigint(20) NULL DEFAULT NULL COMMENT '类型对应实体ID',
  `remark` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `first_login_date` datetime(0) NULL DEFAULT NULL COMMENT '首次登录时间',
  `last_login_date` datetime(0) NULL DEFAULT NULL COMMENT '最后一次登录时间',
  `state` int(100) NOT NULL DEFAULT 1 COMMENT '状态:0=停用,1=启用',
  `is_delete` int(100) NOT NULL DEFAULT 0 COMMENT '是否删除:1=已删,0=未删',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES (1, 1, '张三qwqw', 'zhangsan', '123456', NULL, '', '1', NULL, NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (4, 1, 'admin1', '1111', '12', NULL, '', '3', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (5, 1, 'admin12', '11111', '12', NULL, '12312312', '2', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (6, 1, 'admin11', '111', '1111', NULL, '', '2', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (7, 1, '1212', '21', '211221', NULL, '', '2', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (8, 1, '1212121212', '1212', '122', NULL, '1212', '2', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (9, 1, 'admin1212', '12', '', NULL, '121', '2', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (10, 1, '112121212', '1001', '11', NULL, '12', '2', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (14, 1, 'qwqw1212', 'qweqweqwe', 'qwe', NULL, 'qwe', '2', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (15, 1, '1212', '1212121241fasd', 'fsd', NULL, 'afsd', '2', '1', NULL, '', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (16, 1, 'qwqrw', 'reqef112', '1212', NULL, '12', '2', '1', NULL, '12', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (17, 1, '1', 'qwfwee', 'fasd', NULL, 'fsa', '2', '1', NULL, 'fs', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (18, 1, '1212ee', '1212ee', '1212', NULL, '21', '2', '1', NULL, '21', NULL, NULL, 1, 0);
INSERT INTO `sys_user` VALUES (19, 1, 'admin', '', '', NULL, '', '2', '1', NULL, '', NULL, NULL, 1, 0);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NULL DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) NULL DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '用户和角色的关联关系表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES (1, 1, 1);
INSERT INTO `sys_user_role` VALUES (2, 4, 4);

SET FOREIGN_KEY_CHECKS = 1;
