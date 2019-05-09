#
# Structure for table "device"
#

DROP TABLE IF EXISTS `device`;
CREATE TABLE `device` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `sn` varchar(50) DEFAULT '' COMMENT '设备',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_status` varchar(1) DEFAULT 'F' COMMENT '是否已删除（T：是，F：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='设备表';

#
# Data for table "device"
#

INSERT INTO `device` VALUES 
(1,'ee37bd34012f4bceb08eb18a5ef09ec7','2017-10-25 09:08:45','2017-10-30 17:59:41','F'),
(2,'cb303425bb42460eac3ad01dd6930233','2017-10-26 10:49:28','2017-11-18 09:54:15','F'),
(3,'4e83977ef8974b70b112ed678148d518','2017-10-26 14:57:45','2017-11-08 13:28:52','F'),
(4,'66c404df378f41b09a7a5a240f9e993f','2017-10-26 15:23:42','2017-10-26 15:23:42','F'),
(5,'1198d9778f18429b9ba36be8ccf16126','2017-11-18 13:37:07','2017-11-18 13:37:11','F');

#
# Structure for table "sys_permission"
#

DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `menu_code` varchar(50) DEFAULT '' COMMENT '归属菜单,前端判断并展示菜单使用,',
  `menu_name` varchar(50) DEFAULT '' COMMENT '菜单的中文释义',
  `permission_code` varchar(50) DEFAULT '' COMMENT '权限的代码/通配符,对应代码中@RequiresPermissions 的value',
  `permission_name` varchar(50) DEFAULT '' COMMENT '本权限的中文释义',
  `required_permission` varchar(1) DEFAULT 'F' COMMENT '是否本菜单必选权限（T：是，F：否）',
  `delete_status` varchar(1) DEFAULT 'F' COMMENT '是否已删除（T：是，F：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='权限表';

#
# Data for table "sys_permission"
#

INSERT INTO `sys_permission` VALUES 
(101,'device','设备管理','device:list','设备列表','T','F'),
(102,'device','设备管理','device:add','新增设备','F','F'),
(103,'device','设备管理','device:update','修改设备','F','F'),
(104,'device','设备管理','device:delete','删除设备','F','F'),
(601,'user','用户管理','user:list','用户列表','T','F'),
(602,'user','用户管理','user:add','新增用户','F','F'),
(603,'user','用户管理','user:update','修改用户','F','F'),
(604,'user','用户管理','user:delete','删除用户','F','F'),
(701,'role','角色管理','role:list','角色列表','T','F'),
(702,'role','角色管理','role:add','新增角色','F','F'),
(703,'role','角色管理','role:update','修改角色','F','F'),
(704,'role','角色管理','role:delete','删除角色','F','F'),
(801,'permission','权限管理','permission:list','权限列表','T','F'),
(802,'permission','权限管理','permission:add','新增权限','F','F'),
(803,'permission','权限管理','permission:update','修改权限','F','F'),
(804,'permission','权限管理','permission:delete','删除权限','F','F');

#
# Structure for table "sys_role"
#

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_status` varchar(1) DEFAULT 'F' COMMENT '是否已删除（T：是，F：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=COMPACT COMMENT='角色表';

#
# Data for table "sys_role"
#

INSERT INTO `sys_role` VALUES 
(1,'管理员','2017-11-22 16:24:34','2017-11-22 16:24:52','F'),
(2,'作家','2017-11-22 16:24:34','2017-11-22 16:24:52','F'),
(3,'程序员','2017-11-22 16:28:47','2017-11-22 16:28:47','F');

#
# Structure for table "sys_role_permission"
#

DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `role_id` int(11) DEFAULT NULL COMMENT '角色id',
  `permission_id` int(11) DEFAULT NULL COMMENT '权限id',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `delete_status` varchar(1) DEFAULT 'F' COMMENT '是否已删除（T：是，F：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色权限表';

#
# Data for table "sys_role_permission"
#

INSERT INTO `sys_role_permission` VALUES 
(1,2,101,'2017-11-22 16:26:21','2017-11-22 16:26:32','F'),
(2,2,102,'2017-11-22 16:26:21','2017-11-22 16:26:32','F'),
(5,2,602,'2017-11-22 16:28:28','2017-11-22 16:28:28','F'),
(6,2,601,'2017-11-22 16:28:28','2017-11-22 16:28:28','F'),
(7,2,603,'2017-11-22 16:28:28','2017-11-22 16:28:28','F'),
(8,2,703,'2017-11-22 16:28:28','2017-11-22 16:28:28','F'),
(9,2,701,'2017-11-22 16:28:28','2017-11-22 16:28:28','F'),
(10,2,702,'2017-11-22 16:28:28','2017-11-22 16:28:28','F'),
(11,2,704,'2017-11-22 16:28:31','2017-11-22 16:28:31','F'),
(12,2,103,'2017-11-22 16:28:31','2017-11-22 16:28:31','F'),
(13,3,601,'2017-11-22 16:28:47','2017-11-22 16:28:47','F'),
(14,3,701,'2017-11-22 16:28:47','2017-11-22 16:28:47','F'),
(15,3,702,'2017-11-22 16:35:01','2017-11-22 16:35:01','F'),
(16,3,704,'2017-11-22 16:35:01','2017-11-22 16:35:01','F'),
(17,3,102,'2017-11-22 16:35:01','2017-11-22 16:35:01','F'),
(18,3,101,'2017-11-22 16:35:01','2017-11-22 16:35:01','F'),
(19,3,603,'2017-11-22 16:35:01','2017-11-22 16:35:01','F');

#
# Structure for table "sys_user"
#

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `salt` varchar(50) DEFAULT NULL COMMENT '盐',
  `nickname` varchar(50) DEFAULT NULL COMMENT '昵称',
  `role_id` int(11) DEFAULT '0' COMMENT '角色ID',
  `create_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `delete_status` varchar(1) DEFAULT 'F' COMMENT '是否已删除（T：是，F：否）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

#
# Data for table "sys_user"
#

INSERT INTO `sys_user` VALUES 
(1,'admin','7F4DABEDA70AF45040642ADAA6CF6E78','e95266279ea946cba66ee78cea6ffa70','超级用户',1,'2017-10-30 11:52:38','2017-11-17 23:51:40','F'),
(2,'user','6785EBAA9A5D424BF66304A590BA78B0','61866c31e66d487eaaf792f296aefa99','莎士比亚',2,'2017-10-30 16:13:02','2017-11-18 02:48:24','F'),
(3,'aaa','A79659E70F758C9BE46F4DDD9BB31C34','48f84e8c75914628951d666a50e2b800','abba',1,'2017-11-15 14:02:56','2017-11-17 23:51:42','F'),
(4,'test','266969BA891D55C67AD35A058D5366A7','f94e6ee0d3714daebe3f44195fe6783d','就看看列表',3,'2017-11-22 16:29:41','2017-11-22 16:29:41','F');
