/*
Navicat MySQL Data Transfer

Source Server         : localhost3307
Source Server Version : 50711
Source Host           : localhost:3307
Source Database       : bootma

Target Server Type    : MYSQL
Target Server Version : 50711
File Encoding         : 65001

Date: 2020-02-01 19:41:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '上级部门ID，一级部门为0',
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `del_flag` tinyint(4) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COMMENT='部门管理';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '研发部', '1', '1');
INSERT INTO `sys_dept` VALUES ('8', '6', '研发二部', '2', '1');
INSERT INTO `sys_dept` VALUES ('9', '0', '销售部', '2', '1');
INSERT INTO `sys_dept` VALUES ('10', '9', '销售一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('13', '0', '测试部', '5', '1');
INSERT INTO `sys_dept` VALUES ('14', '13', '测试一部', '1', '1');
INSERT INTO `sys_dept` VALUES ('15', '13', '测试二部', '2', '1');
INSERT INTO `sys_dept` VALUES ('17', '6', '研发一部', '1', '1');

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `name` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '标签名',
  `value` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '数据值',
  `type` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '类型',
  `description` varchar(100) COLLATE utf8_bin DEFAULT NULL COMMENT '描述',
  `sort` decimal(10,0) DEFAULT NULL COMMENT '排序（升序）',
  `parent_id` bigint(64) DEFAULT '0' COMMENT '父级编号',
  `create_by` int(64) DEFAULT NULL COMMENT '创建者',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` bigint(64) DEFAULT NULL COMMENT '更新者',
  `update_date` datetime DEFAULT NULL COMMENT '更新时间',
  `remarks` varchar(255) COLLATE utf8_bin DEFAULT NULL COMMENT '备注信息',
  `del_flag` char(1) COLLATE utf8_bin DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_dict_value` (`value`),
  KEY `sys_dict_label` (`name`),
  KEY `sys_dict_del_flag` (`del_flag`)
) ENGINE=InnoDB AUTO_INCREMENT=127 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='字典表';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('25', '综合部', '1', 'sys_office_common', '快捷通用部门', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('26', '开发部', '2', 'sys_office_common', '快捷通用部门', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('27', '人力部', '3', 'sys_office_common', '快捷通用部门', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('28', '一级', '1', 'sys_office_grade', '机构等级', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('29', '二级', '2', 'sys_office_grade', '机构等级', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('30', '三级', '3', 'sys_office_grade', '机构等级', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('31', '四级', '4', 'sys_office_grade', '机构等级', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('45', '文章模型', 'article', 'cms_module', '栏目模型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('46', '图片模型', 'picture', 'cms_module', '栏目模型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('47', '下载模型', 'download', 'cms_module', '栏目模型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('48', '链接模型', 'link', 'cms_module', '栏目模型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('49', '专题模型', 'special', 'cms_module', '栏目模型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('50', '默认展现方式', '0', 'cms_show_modes', '展现方式', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('51', '首栏目内容列表', '1', 'cms_show_modes', '展现方式', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('52', '栏目第一条内容', '2', 'cms_show_modes', '展现方式', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('53', '发布', '0', 'cms_del_flag', '内容状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('54', '删除', '1', 'cms_del_flag', '内容状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('55', '审核', '2', 'cms_del_flag', '内容状态', '15', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('56', '首页焦点图', '1', 'cms_posid', '推荐位', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('57', '栏目页文章推荐', '2', 'cms_posid', '推荐位', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('58', '咨询', '1', 'cms_guestbook', '留言板分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('59', '建议', '2', 'cms_guestbook', '留言板分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('60', '投诉', '3', 'cms_guestbook', '留言板分类', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('61', '其它', '4', 'cms_guestbook', '留言板分类', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('62', '公休', '1', 'oa_leave_type', '请假类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('63', '病假', '2', 'oa_leave_type', '请假类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('64', '事假', '3', 'oa_leave_type', '请假类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('65', '调休', '4', 'oa_leave_type', '请假类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('66', '婚假', '5', 'oa_leave_type', '请假类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('69', '请假流程', 'leave', 'act_type', '流程类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('70', '审批测试流程', 'test_audit', 'act_type', '流程类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('71', '分类1', '1', 'act_category', '流程分类', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('72', '分类2', '2', 'act_category', '流程分类', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO `sys_dict` VALUES ('113', '删除', '0', 'del_flag', '删除标记', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('118', '关于', 'about', 'blog_type', '博客类型', null, null, null, null, null, null, '全url是:/blog/open/page/about', '');
INSERT INTO `sys_dict` VALUES ('119', '交流', 'communication', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('120', '文章', 'article', 'blog_type', '博客类型', null, null, null, null, null, null, '', '');
INSERT INTO `sys_dict` VALUES ('121', '游泳', 'swim', 'hobby', '爱好', null, null, null, null, null, null, null, null);
INSERT INTO `sys_dict` VALUES ('122', '看书', 'readBook', 'hobby', '爱好', null, null, null, null, null, null, null, null);
INSERT INTO `sys_dict` VALUES ('123', '跑步', 'run', 'hobby', '爱好', null, null, null, null, null, null, null, null);
INSERT INTO `sys_dict` VALUES ('124', '唱歌', 'sing', 'hobby', '爱好', null, null, null, null, null, null, null, null);
INSERT INTO `sys_dict` VALUES ('125', '看电影', 'watchMovie', 'hobby', '爱好', null, null, null, null, null, null, null, null);
INSERT INTO `sys_dict` VALUES ('126', '打篮球', 'playBall', 'hobby', '爱好', null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_file
-- ----------------------------
DROP TABLE IF EXISTS `sys_file`;
CREATE TABLE `sys_file` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '文件类型',
  `url` varchar(200) DEFAULT NULL COMMENT 'URL地址',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=152 DEFAULT CHARSET=utf8 COMMENT='文件上传';

-- ----------------------------
-- Records of sys_file
-- ----------------------------
INSERT INTO `sys_file` VALUES ('151', '0', '/files/95591cb3-e40b-4dca-840c-b07814b486f1.png', '2019-10-27 22:08:25');

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `operation` varchar(50) DEFAULT NULL COMMENT '用户操作',
  `time` varchar(11) DEFAULT NULL COMMENT '响应时间',
  `method` varchar(200) DEFAULT NULL COMMENT '请求方法',
  `params` varchar(1000) DEFAULT NULL COMMENT '请求参数',
  `ip` varchar(64) DEFAULT NULL COMMENT 'IP地址',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=354 DEFAULT CHARSET=utf8 COMMENT='系统日志';

-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '1', 'admin', '首页', '101毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 09:56:40');
INSERT INTO `sys_log` VALUES ('2', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 09:56:43');
INSERT INTO `sys_log` VALUES ('3', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 09:56:44');
INSERT INTO `sys_log` VALUES ('4', '1', 'admin', '首页', '365毫秒', 'cn.maaa.system.controller.LoginController.index()', null, null, '2020-02-01 09:57:13');
INSERT INTO `sys_log` VALUES ('5', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 09:57:16');
INSERT INTO `sys_log` VALUES ('6', '1', 'admin', '日志列表', '17毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 09:57:16');
INSERT INTO `sys_log` VALUES ('7', '-1', '获取用户信息为空', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, '127.0.0.1', '2020-02-01 09:57:27');
INSERT INTO `sys_log` VALUES ('8', '-1', '获取用户信息为空', '用户列表', '11毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 09:57:27');
INSERT INTO `sys_log` VALUES ('9', '1', 'admin', '编辑用户', '30毫秒', 'cn.maaa.system.controller.UserController.edit()', '[136]', null, '2020-02-01 09:57:29');
INSERT INTO `sys_log` VALUES ('10', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 09:57:34');
INSERT INTO `sys_log` VALUES ('11', '1', 'admin', '日志列表', '16毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 09:57:34');
INSERT INTO `sys_log` VALUES ('12', '1', 'admin', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, '127.0.0.1', '2020-02-01 09:57:51');
INSERT INTO `sys_log` VALUES ('13', '1', 'admin', '部门列表', '5毫秒', 'cn.maaa.system.controller.DeptController.list()', null, null, '2020-02-01 09:57:51');
INSERT INTO `sys_log` VALUES ('14', '1', 'admin', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, null, '2020-02-01 09:57:54');
INSERT INTO `sys_log` VALUES ('15', '1', 'admin', '角色列表', '9毫秒', 'cn.maaa.system.controller.RoleController.list()', null, null, '2020-02-01 09:57:55');
INSERT INTO `sys_log` VALUES ('16', '1', 'admin', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, null, '2020-02-01 09:57:55');
INSERT INTO `sys_log` VALUES ('17', '-1', '获取用户信息为空', '用户列表', '10毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 09:57:56');
INSERT INTO `sys_log` VALUES ('18', '-1', '获取用户信息为空', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, null, '2020-02-01 09:57:56');
INSERT INTO `sys_log` VALUES ('19', '1', 'admin', '菜单列表', '7毫秒', 'cn.maaa.system.controller.MenuController.list()', null, null, '2020-02-01 09:57:56');
INSERT INTO `sys_log` VALUES ('20', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 09:58:01');
INSERT INTO `sys_log` VALUES ('21', '1', 'admin', '日志列表', '14毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 09:58:01');
INSERT INTO `sys_log` VALUES ('22', '1', 'admin', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, null, '2020-02-01 09:59:03');
INSERT INTO `sys_log` VALUES ('23', '1', 'admin', '用户列表', '16毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 09:59:04');
INSERT INTO `sys_log` VALUES ('24', '1', 'admin', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 09:59:08');
INSERT INTO `sys_log` VALUES ('25', '1', 'admin', '用户列表', '10毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 09:59:13');
INSERT INTO `sys_log` VALUES ('26', '1', 'admin', '用户列表', '8毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 09:59:13');
INSERT INTO `sys_log` VALUES ('27', '-1', '获取用户信息为空', '用户列表', '13毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 09:59:13');
INSERT INTO `sys_log` VALUES ('28', '-1', '获取用户信息为空', '用户列表', '13毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 09:59:13');
INSERT INTO `sys_log` VALUES ('29', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 09:59:15');
INSERT INTO `sys_log` VALUES ('30', '1', 'admin', '用户列表', '11毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:00:50');
INSERT INTO `sys_log` VALUES ('31', '1', 'admin', '用户列表', '11毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:00:51');
INSERT INTO `sys_log` VALUES ('32', '1', 'admin', '用户列表', '10毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:00:51');
INSERT INTO `sys_log` VALUES ('33', '1', 'admin', '用户列表', '11毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:00:51');
INSERT INTO `sys_log` VALUES ('34', '1', 'admin', '用户列表', '11毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:00:51');
INSERT INTO `sys_log` VALUES ('35', '1', 'admin', '用户列表', '17毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:00:51');
INSERT INTO `sys_log` VALUES ('36', '1', 'admin', '用户列表', '11毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:00:52');
INSERT INTO `sys_log` VALUES ('37', '-1', '获取用户信息为空', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:00:54');
INSERT INTO `sys_log` VALUES ('38', '-1', '获取用户信息为空', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:07');
INSERT INTO `sys_log` VALUES ('39', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:01:11');
INSERT INTO `sys_log` VALUES ('40', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:14');
INSERT INTO `sys_log` VALUES ('41', '1', 'admin', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:21');
INSERT INTO `sys_log` VALUES ('42', '1', 'admin', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:22');
INSERT INTO `sys_log` VALUES ('43', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:22');
INSERT INTO `sys_log` VALUES ('44', '1', 'admin', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:23');
INSERT INTO `sys_log` VALUES ('45', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:23');
INSERT INTO `sys_log` VALUES ('46', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:23');
INSERT INTO `sys_log` VALUES ('47', '-1', '获取用户信息为空', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:23');
INSERT INTO `sys_log` VALUES ('48', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:24');
INSERT INTO `sys_log` VALUES ('49', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:01:24');
INSERT INTO `sys_log` VALUES ('50', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:24');
INSERT INTO `sys_log` VALUES ('51', '1', 'admin', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:01:24');
INSERT INTO `sys_log` VALUES ('52', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:33:06');
INSERT INTO `sys_log` VALUES ('53', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:33:07');
INSERT INTO `sys_log` VALUES ('54', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:33:07');
INSERT INTO `sys_log` VALUES ('55', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:33:09');
INSERT INTO `sys_log` VALUES ('56', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:33:10');
INSERT INTO `sys_log` VALUES ('57', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:33:15');
INSERT INTO `sys_log` VALUES ('58', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:33:15');
INSERT INTO `sys_log` VALUES ('59', '-1', '获取用户信息为空', '用户登录', '6毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', '127.0.0.1', '2020-02-01 10:33:16');
INSERT INTO `sys_log` VALUES ('60', '-1', '获取用户信息为空', '首页', '160毫秒', 'cn.maaa.system.controller.LoginController.index()', null, null, '2020-02-01 10:33:17');
INSERT INTO `sys_log` VALUES ('61', '-1', '获取用户信息为空', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 10:33:19');
INSERT INTO `sys_log` VALUES ('62', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:19');
INSERT INTO `sys_log` VALUES ('63', '-1', '获取用户信息为空', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:33:23');
INSERT INTO `sys_log` VALUES ('64', '-1', '获取用户信息为空', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:26');
INSERT INTO `sys_log` VALUES ('65', '-1', '获取用户信息为空', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:28');
INSERT INTO `sys_log` VALUES ('66', '-1', '获取用户信息为空', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:30');
INSERT INTO `sys_log` VALUES ('67', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:33:31');
INSERT INTO `sys_log` VALUES ('68', '-1', '获取用户信息为空', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:32');
INSERT INTO `sys_log` VALUES ('69', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:32');
INSERT INTO `sys_log` VALUES ('70', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:33');
INSERT INTO `sys_log` VALUES ('71', '-1', '获取用户信息为空', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 10:33:35');
INSERT INTO `sys_log` VALUES ('72', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:35');
INSERT INTO `sys_log` VALUES ('73', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:36');
INSERT INTO `sys_log` VALUES ('74', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:37');
INSERT INTO `sys_log` VALUES ('75', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:37');
INSERT INTO `sys_log` VALUES ('76', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:38');
INSERT INTO `sys_log` VALUES ('77', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:38');
INSERT INTO `sys_log` VALUES ('78', '-1', '获取用户信息为空', '日志列表', '6毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:38');
INSERT INTO `sys_log` VALUES ('79', '-1', '获取用户信息为空', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:38');
INSERT INTO `sys_log` VALUES ('80', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:38');
INSERT INTO `sys_log` VALUES ('81', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:39');
INSERT INTO `sys_log` VALUES ('82', '-1', '获取用户信息为空', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:39');
INSERT INTO `sys_log` VALUES ('83', '-1', '获取用户信息为空', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:39');
INSERT INTO `sys_log` VALUES ('84', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:39');
INSERT INTO `sys_log` VALUES ('85', '-1', '获取用户信息为空', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:39');
INSERT INTO `sys_log` VALUES ('86', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:39');
INSERT INTO `sys_log` VALUES ('87', '-1', '获取用户信息为空', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:40');
INSERT INTO `sys_log` VALUES ('88', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:40');
INSERT INTO `sys_log` VALUES ('89', '-1', '获取用户信息为空', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, null, '2020-02-01 10:33:42');
INSERT INTO `sys_log` VALUES ('90', '-1', '获取用户信息为空', '角色列表', '37毫秒', 'cn.maaa.system.controller.RoleController.list()', null, null, '2020-02-01 10:33:42');
INSERT INTO `sys_log` VALUES ('91', '-1', '获取用户信息为空', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, null, '2020-02-01 10:33:44');
INSERT INTO `sys_log` VALUES ('92', '-1', '获取用户信息为空', '部门列表', '6毫秒', 'cn.maaa.system.controller.DeptController.list()', null, null, '2020-02-01 10:33:44');
INSERT INTO `sys_log` VALUES ('93', '-1', '获取用户信息为空', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, null, '2020-02-01 10:33:45');
INSERT INTO `sys_log` VALUES ('94', '-1', '获取用户信息为空', '菜单列表', '7毫秒', 'cn.maaa.system.controller.MenuController.list()', null, null, '2020-02-01 10:33:45');
INSERT INTO `sys_log` VALUES ('95', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:48');
INSERT INTO `sys_log` VALUES ('96', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:33:50');
INSERT INTO `sys_log` VALUES ('97', '-1', '获取用户信息为空', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, null, '2020-02-01 10:33:55');
INSERT INTO `sys_log` VALUES ('98', '-1', '获取用户信息为空', '用户列表', '10毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:33:55');
INSERT INTO `sys_log` VALUES ('99', '-1', '获取用户信息为空', '日志页面', '1毫秒', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 10:34:04');
INSERT INTO `sys_log` VALUES ('100', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:04');
INSERT INTO `sys_log` VALUES ('101', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:06');
INSERT INTO `sys_log` VALUES ('102', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:07');
INSERT INTO `sys_log` VALUES ('103', '-1', '获取用户信息为空', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:07');
INSERT INTO `sys_log` VALUES ('104', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:07');
INSERT INTO `sys_log` VALUES ('105', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:08');
INSERT INTO `sys_log` VALUES ('106', '-1', '获取用户信息为空', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:08');
INSERT INTO `sys_log` VALUES ('107', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:08');
INSERT INTO `sys_log` VALUES ('108', '-1', '获取用户信息为空', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:09');
INSERT INTO `sys_log` VALUES ('109', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:09');
INSERT INTO `sys_log` VALUES ('110', '-1', '获取用户信息为空', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:09');
INSERT INTO `sys_log` VALUES ('111', '-1', '获取用户信息为空', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:10');
INSERT INTO `sys_log` VALUES ('112', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:10');
INSERT INTO `sys_log` VALUES ('113', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:10');
INSERT INTO `sys_log` VALUES ('114', '-1', '获取用户信息为空', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:11');
INSERT INTO `sys_log` VALUES ('115', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:12');
INSERT INTO `sys_log` VALUES ('116', '-1', '获取用户信息为空', '退出登录', '2毫秒', 'cn.maaa.system.controller.LoginController.logout()', null, null, '2020-02-01 10:34:13');
INSERT INTO `sys_log` VALUES ('117', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:34:13');
INSERT INTO `sys_log` VALUES ('118', '-1', '获取用户信息为空', '用户登录', '5毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', null, '2020-02-01 10:34:14');
INSERT INTO `sys_log` VALUES ('119', '-1', '获取用户信息为空', '首页', '108毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 10:34:14');
INSERT INTO `sys_log` VALUES ('120', '-1', '获取用户信息为空', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 10:34:16');
INSERT INTO `sys_log` VALUES ('121', '-1', '获取用户信息为空', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:16');
INSERT INTO `sys_log` VALUES ('122', '-1', '获取用户信息为空', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, null, '2020-02-01 10:34:19');
INSERT INTO `sys_log` VALUES ('123', '-1', '获取用户信息为空', '部门列表', '4毫秒', 'cn.maaa.system.controller.DeptController.list()', null, null, '2020-02-01 10:34:20');
INSERT INTO `sys_log` VALUES ('124', '-1', '获取用户信息为空', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, '127.0.0.1', '2020-02-01 10:34:21');
INSERT INTO `sys_log` VALUES ('125', '-1', '获取用户信息为空', '角色列表', '4毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 10:34:21');
INSERT INTO `sys_log` VALUES ('126', '-1', '获取用户信息为空', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, '127.0.0.1', '2020-02-01 10:34:21');
INSERT INTO `sys_log` VALUES ('127', '-1', '获取用户信息为空', '用户列表', '8毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:34:21');
INSERT INTO `sys_log` VALUES ('128', '-1', '获取用户信息为空', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, null, '2020-02-01 10:34:22');
INSERT INTO `sys_log` VALUES ('129', '-1', '获取用户信息为空', '菜单列表', '6毫秒', 'cn.maaa.system.controller.MenuController.list()', null, null, '2020-02-01 10:34:22');
INSERT INTO `sys_log` VALUES ('130', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:34:26');
INSERT INTO `sys_log` VALUES ('131', '-1', '获取用户信息为空', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:35:54');
INSERT INTO `sys_log` VALUES ('132', '-1', '获取用户信息为空', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:35:55');
INSERT INTO `sys_log` VALUES ('133', '-1', '获取用户信息为空', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:36:16');
INSERT INTO `sys_log` VALUES ('134', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:36:25');
INSERT INTO `sys_log` VALUES ('135', '-1', '获取用户信息为空', '主页前言', '0', 'cn.maaa.system.controller.LoginController.main()', null, null, '2020-02-01 10:39:29');
INSERT INTO `sys_log` VALUES ('136', '-1', '获取用户信息为空', '首页', '271毫秒', 'cn.maaa.system.controller.LoginController.index()', null, null, '2020-02-01 10:39:37');
INSERT INTO `sys_log` VALUES ('137', '-1', '获取用户信息为空', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, null, '2020-02-01 10:39:40');
INSERT INTO `sys_log` VALUES ('138', '-1', '获取用户信息为空', '部门列表', '5毫秒', 'cn.maaa.system.controller.DeptController.list()', null, null, '2020-02-01 10:39:41');
INSERT INTO `sys_log` VALUES ('139', '-1', '获取用户信息为空', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, null, '2020-02-01 10:39:43');
INSERT INTO `sys_log` VALUES ('140', '-1', '获取用户信息为空', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:39:43');
INSERT INTO `sys_log` VALUES ('141', '-1', '获取用户信息为空', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, null, '2020-02-01 10:39:45');
INSERT INTO `sys_log` VALUES ('142', '-1', '获取用户信息为空', '用户列表', '12毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:39:46');
INSERT INTO `sys_log` VALUES ('143', '-1', '获取用户信息为空', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, null, '2020-02-01 10:39:47');
INSERT INTO `sys_log` VALUES ('144', '-1', '获取用户信息为空', '菜单列表', '6毫秒', 'cn.maaa.system.controller.MenuController.list()', null, null, '2020-02-01 10:39:47');
INSERT INTO `sys_log` VALUES ('145', '-1', '获取用户信息为空', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, null, '2020-02-01 10:39:50');
INSERT INTO `sys_log` VALUES ('146', '-1', '获取用户信息为空', '角色列表', '3毫秒', 'cn.maaa.system.controller.RoleController.list()', null, null, '2020-02-01 10:39:50');
INSERT INTO `sys_log` VALUES ('147', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:39:55');
INSERT INTO `sys_log` VALUES ('148', '-1', '获取用户信息为空', '退出登录', '3毫秒', 'cn.maaa.system.controller.LoginController.logout()', null, null, '2020-02-01 10:39:56');
INSERT INTO `sys_log` VALUES ('149', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:39:56');
INSERT INTO `sys_log` VALUES ('150', '-1', '获取用户信息为空', '用户登录', '6毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', null, '2020-02-01 10:39:58');
INSERT INTO `sys_log` VALUES ('151', '-1', '获取用户信息为空', '首页', '113毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 10:39:58');
INSERT INTO `sys_log` VALUES ('152', '-1', '获取用户信息为空', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, '127.0.0.1', '2020-02-01 10:40:00');
INSERT INTO `sys_log` VALUES ('153', '-1', '获取用户信息为空', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:40:01');
INSERT INTO `sys_log` VALUES ('154', '-1', '获取用户信息为空', '登录页面', '3毫秒', 'cn.maaa.system.controller.LoginController.login()', null, '127.0.0.1', '2020-02-01 10:41:18');
INSERT INTO `sys_log` VALUES ('155', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, null, '2020-02-01 10:41:19');
INSERT INTO `sys_log` VALUES ('156', '1', 'admin', '用户登录', '32毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', '127.0.0.1', '2020-02-01 10:41:20');
INSERT INTO `sys_log` VALUES ('157', '1', 'admin', '首页', '329毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 10:41:21');
INSERT INTO `sys_log` VALUES ('158', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, '127.0.0.1', '2020-02-01 10:41:23');
INSERT INTO `sys_log` VALUES ('159', '1', 'admin', '日志列表', '127毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:24');
INSERT INTO `sys_log` VALUES ('160', '1', 'admin', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, '127.0.0.1', '2020-02-01 10:41:27');
INSERT INTO `sys_log` VALUES ('161', '1', 'admin', '部门列表', '45毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:41:27');
INSERT INTO `sys_log` VALUES ('162', '1', 'admin', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, '127.0.0.1', '2020-02-01 10:41:28');
INSERT INTO `sys_log` VALUES ('163', '1', 'admin', '角色列表', '16毫秒', 'cn.maaa.system.controller.RoleController.list()', null, null, '2020-02-01 10:41:29');
INSERT INTO `sys_log` VALUES ('164', '-1', '获取用户信息为空', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, null, '2020-02-01 10:41:29');
INSERT INTO `sys_log` VALUES ('165', '-1', '获取用户信息为空', '用户列表', '60毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', null, '2020-02-01 10:41:30');
INSERT INTO `sys_log` VALUES ('166', '1', 'admin', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, null, '2020-02-01 10:41:31');
INSERT INTO `sys_log` VALUES ('167', '1', 'admin', '菜单列表', '14毫秒', 'cn.maaa.system.controller.MenuController.list()', null, null, '2020-02-01 10:41:31');
INSERT INTO `sys_log` VALUES ('168', '1', 'admin', '主页前言', '0', 'cn.maaa.system.controller.LoginController.main()', null, null, '2020-02-01 10:41:32');
INSERT INTO `sys_log` VALUES ('169', '1', 'admin', '日志列表', '24毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:36');
INSERT INTO `sys_log` VALUES ('170', '1', 'admin', '日志列表', '26毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:41');
INSERT INTO `sys_log` VALUES ('171', '1', 'admin', '日志列表', '14毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:42');
INSERT INTO `sys_log` VALUES ('172', '1', 'admin', '日志列表', '18毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:42');
INSERT INTO `sys_log` VALUES ('173', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:42');
INSERT INTO `sys_log` VALUES ('174', '-1', '获取用户信息为空', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:42');
INSERT INTO `sys_log` VALUES ('175', '-1', '获取用户信息为空', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:43');
INSERT INTO `sys_log` VALUES ('176', '1', 'admin', '日志列表', '14毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:43');
INSERT INTO `sys_log` VALUES ('177', '1', 'admin', '日志列表', '14毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:43');
INSERT INTO `sys_log` VALUES ('178', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:43');
INSERT INTO `sys_log` VALUES ('179', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:44');
INSERT INTO `sys_log` VALUES ('180', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:44');
INSERT INTO `sys_log` VALUES ('181', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:44');
INSERT INTO `sys_log` VALUES ('182', '1', 'admin', '日志列表', '17毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:45');
INSERT INTO `sys_log` VALUES ('183', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:45');
INSERT INTO `sys_log` VALUES ('184', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:45');
INSERT INTO `sys_log` VALUES ('185', '-1', '获取用户信息为空', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:45');
INSERT INTO `sys_log` VALUES ('186', '1', 'admin', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:45');
INSERT INTO `sys_log` VALUES ('187', '1', 'admin', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:45');
INSERT INTO `sys_log` VALUES ('188', '1', 'admin', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:46');
INSERT INTO `sys_log` VALUES ('189', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:46');
INSERT INTO `sys_log` VALUES ('190', '1', 'admin', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:46');
INSERT INTO `sys_log` VALUES ('191', '1', 'admin', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:46');
INSERT INTO `sys_log` VALUES ('192', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:46');
INSERT INTO `sys_log` VALUES ('193', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:46');
INSERT INTO `sys_log` VALUES ('194', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:47');
INSERT INTO `sys_log` VALUES ('195', '-1', '获取用户信息为空', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:47');
INSERT INTO `sys_log` VALUES ('196', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:48');
INSERT INTO `sys_log` VALUES ('197', '1', 'admin', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:53');
INSERT INTO `sys_log` VALUES ('198', '1', 'admin', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', null, '2020-02-01 10:41:54');
INSERT INTO `sys_log` VALUES ('199', '-1', '获取用户信息为空', '登录页面', '3毫秒', 'cn.maaa.system.controller.LoginController.login()', null, '127.0.0.1', '2020-02-01 10:42:49');
INSERT INTO `sys_log` VALUES ('200', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, '127.0.0.1', '2020-02-01 10:42:50');
INSERT INTO `sys_log` VALUES ('201', '1', 'admin', '用户登录', '104毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', '127.0.0.1', '2020-02-01 10:42:51');
INSERT INTO `sys_log` VALUES ('202', '1', 'admin', '首页', '245毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 10:42:52');
INSERT INTO `sys_log` VALUES ('203', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, '127.0.0.1', '2020-02-01 10:42:55');
INSERT INTO `sys_log` VALUES ('204', '1', 'admin', '日志列表', '114毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:42:56');
INSERT INTO `sys_log` VALUES ('205', '1', 'admin', '日志列表', '23毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:42:58');
INSERT INTO `sys_log` VALUES ('206', '1', 'admin', '日志列表', '25毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:42:58');
INSERT INTO `sys_log` VALUES ('207', '1', 'admin', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:42:59');
INSERT INTO `sys_log` VALUES ('208', '1', 'admin', '日志列表', '16毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:42:59');
INSERT INTO `sys_log` VALUES ('209', '1', 'admin', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:00');
INSERT INTO `sys_log` VALUES ('210', '1', 'admin', '日志列表', '16毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:00');
INSERT INTO `sys_log` VALUES ('211', '1', 'admin', '日志列表', '31毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:00');
INSERT INTO `sys_log` VALUES ('212', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:01');
INSERT INTO `sys_log` VALUES ('213', '1', 'admin', '日志列表', '17毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:01');
INSERT INTO `sys_log` VALUES ('214', '1', 'admin', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:01');
INSERT INTO `sys_log` VALUES ('215', '1', 'admin', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:01');
INSERT INTO `sys_log` VALUES ('216', '1', 'admin', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:01');
INSERT INTO `sys_log` VALUES ('217', '1', 'admin', '日志列表', '14毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:02');
INSERT INTO `sys_log` VALUES ('218', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:02');
INSERT INTO `sys_log` VALUES ('219', '1', 'admin', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:02');
INSERT INTO `sys_log` VALUES ('220', '1', 'admin', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:02');
INSERT INTO `sys_log` VALUES ('221', '1', 'admin', '日志列表', '17毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:03');
INSERT INTO `sys_log` VALUES ('222', '1', 'admin', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:03');
INSERT INTO `sys_log` VALUES ('223', '1', 'admin', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, '127.0.0.1', '2020-02-01 10:43:05');
INSERT INTO `sys_log` VALUES ('224', '1', 'admin', '部门列表', '27毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:05');
INSERT INTO `sys_log` VALUES ('225', '1', 'admin', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, '127.0.0.1', '2020-02-01 10:43:06');
INSERT INTO `sys_log` VALUES ('226', '1', 'admin', '角色列表', '17毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 10:43:06');
INSERT INTO `sys_log` VALUES ('227', '1', 'admin', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, '127.0.0.1', '2020-02-01 10:43:07');
INSERT INTO `sys_log` VALUES ('228', '1', 'admin', '用户列表', '56毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:07');
INSERT INTO `sys_log` VALUES ('229', '1', 'admin', '菜单页面', '1毫秒', 'cn.maaa.system.controller.MenuController.menu()', null, '127.0.0.1', '2020-02-01 10:43:07');
INSERT INTO `sys_log` VALUES ('230', '1', 'admin', '菜单列表', '8毫秒', 'cn.maaa.system.controller.MenuController.list()', null, '127.0.0.1', '2020-02-01 10:43:08');
INSERT INTO `sys_log` VALUES ('231', '1', 'admin', '主页前言', '0', 'cn.maaa.system.controller.LoginController.main()', null, '127.0.0.1', '2020-02-01 10:43:09');
INSERT INTO `sys_log` VALUES ('232', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:13');
INSERT INTO `sys_log` VALUES ('233', '1', 'admin', '日志列表', '14毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:16');
INSERT INTO `sys_log` VALUES ('234', '1', 'admin', '日志列表', '19毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:16');
INSERT INTO `sys_log` VALUES ('235', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:17');
INSERT INTO `sys_log` VALUES ('236', '1', 'admin', '日志列表', '10毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:17');
INSERT INTO `sys_log` VALUES ('237', '1', 'admin', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:17');
INSERT INTO `sys_log` VALUES ('238', '1', 'admin', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:18');
INSERT INTO `sys_log` VALUES ('239', '1', 'admin', '日志列表', '13毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:18');
INSERT INTO `sys_log` VALUES ('240', '1', 'admin', '日志列表', '15毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:18');
INSERT INTO `sys_log` VALUES ('241', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:21');
INSERT INTO `sys_log` VALUES ('242', '1', 'admin', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:21');
INSERT INTO `sys_log` VALUES ('243', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:22');
INSERT INTO `sys_log` VALUES ('244', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:28');
INSERT INTO `sys_log` VALUES ('245', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:29');
INSERT INTO `sys_log` VALUES ('246', '1', 'admin', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:29');
INSERT INTO `sys_log` VALUES ('247', '1', 'admin', '日志列表', '8毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:29');
INSERT INTO `sys_log` VALUES ('248', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:29');
INSERT INTO `sys_log` VALUES ('249', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:30');
INSERT INTO `sys_log` VALUES ('250', '1', 'admin', '日志列表', '14毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:31');
INSERT INTO `sys_log` VALUES ('251', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:31');
INSERT INTO `sys_log` VALUES ('252', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:31');
INSERT INTO `sys_log` VALUES ('253', '-1', '获取用户信息为空', '退出登录', '9毫秒', 'cn.maaa.system.controller.LoginController.logout()', null, '127.0.0.1', '2020-02-01 10:43:37');
INSERT INTO `sys_log` VALUES ('254', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, '127.0.0.1', '2020-02-01 10:43:37');
INSERT INTO `sys_log` VALUES ('255', '1', 'admin', '用户登录', '6毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', '127.0.0.1', '2020-02-01 10:43:39');
INSERT INTO `sys_log` VALUES ('256', '1', 'admin', '首页', '117毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 10:43:39');
INSERT INTO `sys_log` VALUES ('257', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, '127.0.0.1', '2020-02-01 10:43:41');
INSERT INTO `sys_log` VALUES ('258', '1', 'admin', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:41');
INSERT INTO `sys_log` VALUES ('259', '1', 'admin', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, '127.0.0.1', '2020-02-01 10:43:45');
INSERT INTO `sys_log` VALUES ('260', '1', 'admin', '部门列表', '5毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:45');
INSERT INTO `sys_log` VALUES ('261', '1', 'admin', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, '127.0.0.1', '2020-02-01 10:43:46');
INSERT INTO `sys_log` VALUES ('262', '1', 'admin', '角色列表', '4毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 10:43:46');
INSERT INTO `sys_log` VALUES ('263', '1', 'admin', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, '127.0.0.1', '2020-02-01 10:43:46');
INSERT INTO `sys_log` VALUES ('264', '1', 'admin', '用户列表', '8毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:47');
INSERT INTO `sys_log` VALUES ('265', '1', 'admin', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, '127.0.0.1', '2020-02-01 10:43:47');
INSERT INTO `sys_log` VALUES ('266', '1', 'admin', '菜单列表', '6毫秒', 'cn.maaa.system.controller.MenuController.list()', null, '127.0.0.1', '2020-02-01 10:43:47');
INSERT INTO `sys_log` VALUES ('267', '1', 'admin', '部门列表', '4毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:49');
INSERT INTO `sys_log` VALUES ('268', '1', 'admin', '部门列表', '3毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:50');
INSERT INTO `sys_log` VALUES ('269', '1', 'admin', '部门列表', '5毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:50');
INSERT INTO `sys_log` VALUES ('270', '1', 'admin', '部门列表', '3毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:50');
INSERT INTO `sys_log` VALUES ('271', '1', 'admin', '部门列表', '5毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:50');
INSERT INTO `sys_log` VALUES ('272', '1', 'admin', '部门列表', '6毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:50');
INSERT INTO `sys_log` VALUES ('273', '1', 'admin', '部门列表', '4毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:50');
INSERT INTO `sys_log` VALUES ('274', '1', 'admin', '部门列表', '3毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:51');
INSERT INTO `sys_log` VALUES ('275', '1', 'admin', '部门列表', '3毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 10:43:51');
INSERT INTO `sys_log` VALUES ('276', '1', 'admin', '日志列表', '12毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 10:43:54');
INSERT INTO `sys_log` VALUES ('277', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, '127.0.0.1', '2020-02-01 11:33:33');
INSERT INTO `sys_log` VALUES ('278', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, '127.0.0.1', '2020-02-01 11:33:33');
INSERT INTO `sys_log` VALUES ('279', '1', 'admin', '用户登录', '7毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', '127.0.0.1', '2020-02-01 11:33:34');
INSERT INTO `sys_log` VALUES ('280', '1', 'admin', '首页', '167毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 11:33:35');
INSERT INTO `sys_log` VALUES ('281', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, '127.0.0.1', '2020-02-01 11:33:37');
INSERT INTO `sys_log` VALUES ('282', '1', 'admin', '日志列表', '22毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:33:37');
INSERT INTO `sys_log` VALUES ('283', '1', 'admin', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, '127.0.0.1', '2020-02-01 11:33:41');
INSERT INTO `sys_log` VALUES ('284', '1', 'admin', '部门列表', '5毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 11:33:41');
INSERT INTO `sys_log` VALUES ('285', '1', 'admin', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, '127.0.0.1', '2020-02-01 11:33:42');
INSERT INTO `sys_log` VALUES ('286', '1', 'admin', '用户列表', '7毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:33:43');
INSERT INTO `sys_log` VALUES ('287', '1', 'admin', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, '127.0.0.1', '2020-02-01 11:33:43');
INSERT INTO `sys_log` VALUES ('288', '1', 'admin', '角色列表', '2毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 11:33:43');
INSERT INTO `sys_log` VALUES ('289', '1', 'admin', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, '127.0.0.1', '2020-02-01 11:33:43');
INSERT INTO `sys_log` VALUES ('290', '1', 'admin', '菜单列表', '5毫秒', 'cn.maaa.system.controller.MenuController.list()', null, '127.0.0.1', '2020-02-01 11:33:44');
INSERT INTO `sys_log` VALUES ('291', '1', 'admin', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:33:47');
INSERT INTO `sys_log` VALUES ('292', '1', 'admin', '日志列表', '11毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:33:48');
INSERT INTO `sys_log` VALUES ('293', '1', 'admin', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:33:48');
INSERT INTO `sys_log` VALUES ('294', '1', 'admin', '日志列表', '7毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:33:49');
INSERT INTO `sys_log` VALUES ('295', '1', 'admin', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:33:49');
INSERT INTO `sys_log` VALUES ('296', '1', 'admin', '编辑角色', '6毫秒', 'cn.maaa.system.controller.RoleController.edit()', '[1]', '127.0.0.1', '2020-02-01 11:34:29');
INSERT INTO `sys_log` VALUES ('297', '1', 'admin', '保存角色', '268毫秒', 'cn.maaa.system.controller.RoleController.save()', '[{\"id\":1,\"remark\":\"拥有最高权限\",\"roleName\":\"超级用户角色\"}]', '127.0.0.1', '2020-02-01 11:34:36');
INSERT INTO `sys_log` VALUES ('298', '1', 'admin', '角色列表', '3毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 11:34:36');
INSERT INTO `sys_log` VALUES ('299', '-1', '获取用户信息为空', '退出登录', '4毫秒', 'cn.maaa.system.controller.LoginController.logout()', null, '127.0.0.1', '2020-02-01 11:34:39');
INSERT INTO `sys_log` VALUES ('300', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, '127.0.0.1', '2020-02-01 11:34:39');
INSERT INTO `sys_log` VALUES ('301', '1', 'admin', '用户登录', '9毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', '127.0.0.1', '2020-02-01 11:34:40');
INSERT INTO `sys_log` VALUES ('302', '1', 'admin', '首页', '138毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 11:34:41');
INSERT INTO `sys_log` VALUES ('303', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, '127.0.0.1', '2020-02-01 11:34:43');
INSERT INTO `sys_log` VALUES ('304', '1', 'admin', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, '127.0.0.1', '2020-02-01 11:34:47');
INSERT INTO `sys_log` VALUES ('305', '1', 'admin', '角色列表', '3毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 11:34:47');
INSERT INTO `sys_log` VALUES ('306', '1', 'admin', '编辑角色', '4毫秒', 'cn.maaa.system.controller.RoleController.edit()', '[1]', '127.0.0.1', '2020-02-01 11:34:49');
INSERT INTO `sys_log` VALUES ('307', '1', 'admin', '保存角色', '160毫秒', 'cn.maaa.system.controller.RoleController.save()', '[{\"id\":1,\"remark\":\"拥有最高权限\",\"roleName\":\"超级用户角色\"}]', '127.0.0.1', '2020-02-01 11:34:53');
INSERT INTO `sys_log` VALUES ('308', '1', 'admin', '角色列表', '3毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 11:34:53');
INSERT INTO `sys_log` VALUES ('309', '-1', '获取用户信息为空', '退出登录', '2毫秒', 'cn.maaa.system.controller.LoginController.logout()', null, '127.0.0.1', '2020-02-01 11:34:56');
INSERT INTO `sys_log` VALUES ('310', '-1', '获取用户信息为空', '登录页面', '0', 'cn.maaa.system.controller.LoginController.login()', null, '127.0.0.1', '2020-02-01 11:34:56');
INSERT INTO `sys_log` VALUES ('311', '1', 'admin', '用户登录', '7毫秒', 'cn.maaa.system.controller.LoginController.ajaxLogin()', '[\"admin\",\"111111\"]', '127.0.0.1', '2020-02-01 11:34:57');
INSERT INTO `sys_log` VALUES ('312', '1', 'admin', '首页', '107毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 11:34:57');
INSERT INTO `sys_log` VALUES ('313', '1', 'admin', '日志页面', '0', 'cn.maaa.system.controller.LogController.log()', null, '127.0.0.1', '2020-02-01 11:34:59');
INSERT INTO `sys_log` VALUES ('314', '1', 'admin', '日志列表', '9毫秒', 'cn.maaa.system.controller.LogController.list()', '[{\"operation\":\"\",\"username\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:34:59');
INSERT INTO `sys_log` VALUES ('315', '1', 'admin', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, '127.0.0.1', '2020-02-01 11:36:28');
INSERT INTO `sys_log` VALUES ('316', '1', 'admin', '部门列表', '4毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 11:36:28');
INSERT INTO `sys_log` VALUES ('317', '1', 'admin', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, '127.0.0.1', '2020-02-01 11:36:35');
INSERT INTO `sys_log` VALUES ('318', '1', 'admin', '用户列表', '70毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:36:36');
INSERT INTO `sys_log` VALUES ('319', '1', 'admin', '用户列表', '9毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"deptId\":8,\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:36:37');
INSERT INTO `sys_log` VALUES ('320', '1', 'admin', '用户列表', '9毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"deptId\":17,\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:36:38');
INSERT INTO `sys_log` VALUES ('321', '1', 'admin', '用户列表', '8毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"deptId\":10,\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:36:40');
INSERT INTO `sys_log` VALUES ('322', '1', 'admin', '用户列表', '7毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"deptId\":14,\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:36:41');
INSERT INTO `sys_log` VALUES ('323', '1', 'admin', '用户列表', '8毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"deptId\":15,\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:36:42');
INSERT INTO `sys_log` VALUES ('324', '1', 'admin', '用户列表', '7毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:36:43');
INSERT INTO `sys_log` VALUES ('325', '1', 'admin', '编辑用户', '22毫秒', 'cn.maaa.system.controller.UserController.edit()', '[136]', '127.0.0.1', '2020-02-01 11:36:57');
INSERT INTO `sys_log` VALUES ('326', '1', 'admin', '编辑用户', '17毫秒', 'cn.maaa.system.controller.UserController.edit()', '[1]', '127.0.0.1', '2020-02-01 11:37:04');
INSERT INTO `sys_log` VALUES ('327', '1', 'admin', '编辑用户', '14毫秒', 'cn.maaa.system.controller.UserController.edit()', '[2]', '127.0.0.1', '2020-02-01 11:37:08');
INSERT INTO `sys_log` VALUES ('328', '1', 'admin', '编辑用户', '14毫秒', 'cn.maaa.system.controller.UserController.edit()', '[137]', '127.0.0.1', '2020-02-01 11:37:12');
INSERT INTO `sys_log` VALUES ('329', '1', 'admin', '编辑用户', '16毫秒', 'cn.maaa.system.controller.UserController.edit()', '[1]', '127.0.0.1', '2020-02-01 11:37:28');
INSERT INTO `sys_log` VALUES ('330', '1', 'admin', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, '127.0.0.1', '2020-02-01 11:37:31');
INSERT INTO `sys_log` VALUES ('331', '1', 'admin', '角色列表', '6毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 11:37:31');
INSERT INTO `sys_log` VALUES ('332', '1', 'admin', '添加角色', '0', 'cn.maaa.system.controller.RoleController.add()', null, '127.0.0.1', '2020-02-01 11:37:36');
INSERT INTO `sys_log` VALUES ('333', '1', 'admin', '编辑角色', '7毫秒', 'cn.maaa.system.controller.RoleController.edit()', '[1]', '127.0.0.1', '2020-02-01 11:37:47');
INSERT INTO `sys_log` VALUES ('334', '1', 'admin', '编辑部门', '9毫秒', 'cn.maaa.system.controller.DeptController.edit()', '[8]', '127.0.0.1', '2020-02-01 11:37:55');
INSERT INTO `sys_log` VALUES ('335', '1', 'admin', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, '127.0.0.1', '2020-02-01 11:38:01');
INSERT INTO `sys_log` VALUES ('336', '1', 'admin', '菜单列表', '6毫秒', 'cn.maaa.system.controller.MenuController.list()', null, '127.0.0.1', '2020-02-01 11:38:01');
INSERT INTO `sys_log` VALUES ('337', '1', 'admin', '主页前言', '0', 'cn.maaa.system.controller.LoginController.main()', null, '127.0.0.1', '2020-02-01 11:38:17');
INSERT INTO `sys_log` VALUES ('338', '1', 'admin', '首页', '93毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 11:38:22');
INSERT INTO `sys_log` VALUES ('339', '1', 'admin', '主页前言', '0', 'cn.maaa.system.controller.LoginController.main()', null, '127.0.0.1', '2020-02-01 11:38:25');
INSERT INTO `sys_log` VALUES ('340', '1', 'admin', '首页', '117毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 11:38:39');
INSERT INTO `sys_log` VALUES ('341', '1', 'admin', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, '127.0.0.1', '2020-02-01 11:38:48');
INSERT INTO `sys_log` VALUES ('342', '1', 'admin', '部门列表', '4毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 11:38:49');
INSERT INTO `sys_log` VALUES ('343', '1', 'admin', '主页前言', '0', 'cn.maaa.system.controller.LoginController.main()', null, '127.0.0.1', '2020-02-01 11:39:17');
INSERT INTO `sys_log` VALUES ('344', '1', 'admin', '首页', '382毫秒', 'cn.maaa.system.controller.LoginController.index()', null, '127.0.0.1', '2020-02-01 11:39:49');
INSERT INTO `sys_log` VALUES ('345', '1', 'admin', '个人信息页面', '68毫秒', 'cn.maaa.system.controller.UserController.personal()', null, '127.0.0.1', '2020-02-01 11:40:29');
INSERT INTO `sys_log` VALUES ('346', '1', 'admin', '部门页面', '0', 'cn.maaa.system.controller.DeptController.dept()', null, '127.0.0.1', '2020-02-01 11:40:44');
INSERT INTO `sys_log` VALUES ('347', '1', 'admin', '部门列表', '4毫秒', 'cn.maaa.system.controller.DeptController.list()', null, '127.0.0.1', '2020-02-01 11:40:44');
INSERT INTO `sys_log` VALUES ('348', '1', 'admin', '角色页面', '0', 'cn.maaa.system.controller.RoleController.role()', null, '127.0.0.1', '2020-02-01 11:40:44');
INSERT INTO `sys_log` VALUES ('349', '1', 'admin', '角色列表', '3毫秒', 'cn.maaa.system.controller.RoleController.list()', null, '127.0.0.1', '2020-02-01 11:40:45');
INSERT INTO `sys_log` VALUES ('350', '1', 'admin', '用户页面', '0', 'cn.maaa.system.controller.UserController.user()', null, '127.0.0.1', '2020-02-01 11:40:45');
INSERT INTO `sys_log` VALUES ('351', '1', 'admin', '用户列表', '6毫秒', 'cn.maaa.system.controller.UserController.list()', '[{\"name\":\"\"},0,10]', '127.0.0.1', '2020-02-01 11:40:45');
INSERT INTO `sys_log` VALUES ('352', '1', 'admin', '菜单页面', '0', 'cn.maaa.system.controller.MenuController.menu()', null, '127.0.0.1', '2020-02-01 11:40:45');
INSERT INTO `sys_log` VALUES ('353', '1', 'admin', '菜单列表', '5毫秒', 'cn.maaa.system.controller.MenuController.list()', null, '127.0.0.1', '2020-02-01 11:40:45');

-- ----------------------------
-- Table structure for sys_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父菜单ID，一级菜单为0',
  `name` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `url` varchar(200) DEFAULT NULL COMMENT '菜单URL',
  `perms` varchar(500) DEFAULT NULL COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type` int(11) DEFAULT NULL COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon` varchar(50) DEFAULT NULL COMMENT '菜单图标',
  `order_num` int(11) DEFAULT NULL COMMENT '排序',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1270 DEFAULT CHARSET=utf8 COMMENT='菜单管理';

-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1000', '0', '基础管理', '', '', '0', 'fa fa-bars', '0', '2017-08-09 22:49:47', null);
INSERT INTO `sys_menu` VALUES ('1001', '0', '系统管理', null, null, '0', 'fa fa-desktop', '1', '2017-08-09 23:06:55', '2017-08-14 14:13:43');
INSERT INTO `sys_menu` VALUES ('1232', '1000', '部门管理', '/sys/dept', 'sys:dept', '1', null, null, '2020-02-01 08:52:00', '2020-02-01 08:52:00');
INSERT INTO `sys_menu` VALUES ('1233', '1000', '角色管理', '/sys/role', 'sys:role', '1', null, null, '2020-02-01 08:52:00', '2020-02-01 08:52:00');
INSERT INTO `sys_menu` VALUES ('1234', '1000', '用户管理', '/sys/user', 'sys:user', '1', null, null, '2020-02-01 08:52:00', '2020-02-01 08:52:00');
INSERT INTO `sys_menu` VALUES ('1235', '1000', '菜单管理', '/sys/menu', 'sys:menu', '1', null, null, '2020-02-01 08:52:00', '2020-02-01 08:52:00');
INSERT INTO `sys_menu` VALUES ('1236', '1001', '日志管理', '/common/log', 'common:log', '1', null, null, '2020-02-01 08:52:00', '2020-02-01 08:52:00');
INSERT INTO `sys_menu` VALUES ('1237', '1232', '添加部门', '/sys/dept/add/{pId}', 'sys:dept:add:{pId}', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1238', '1232', '删除部门', '/sys/dept/remove', 'sys:dept:remove', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1239', '1232', '部门列表', '/sys/dept/list', 'sys:dept:list', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1240', '1232', '保存部门', '/sys/dept/save', 'sys:dept:save', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1241', '1232', '编辑部门', '/sys/dept/edit/{id}', 'sys:dept:edit:{id}', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1242', '1232', '部门树', '/sys/dept/treeView', 'sys:dept:treeView', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1243', '1233', '添加角色', '/sys/role/add', 'sys:role:add', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1244', '1233', '删除角色', '/sys/role/remove', 'sys:role:remove', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1245', '1233', '角色列表', '/sys/role/list', 'sys:role:list', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1246', '1233', '保存角色', '/sys/role/save', 'sys:role:save', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1247', '1233', '批量删除角色', '/sys/role/batchRemove', 'sys:role:batchRemove', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1248', '1233', '编辑角色', '/sys/role/edit/{id}', 'sys:role:edit:{id}', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1249', '1234', '添加用户', '/sys/user/add', 'sys:user:add', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1250', '1234', '删除用户', '/sys/user/remove', 'sys:user:remove', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1251', '1234', '用户列表', '/sys/user/list', 'sys:user:list', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1252', '1234', '保存用户', '/sys/user/save', 'sys:user:save', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1253', '1234', '批量删除用户', '/sys/user/batchRemove', 'sys:user:batchRemove', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1254', '1234', '编辑用户', '/sys/user/edit/{id}', 'sys:user:edit:{id}', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1255', '1234', '管理员重置密码', '/sys/user/adminResetPwd', 'sys:user:adminResetPwd', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1256', '1234', '上传图片', '/sys/user/uploadImg', 'sys:user:uploadImg', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1257', '1234', '个人信息页面', '/sys/user/personal', 'sys:user:personal', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1258', '1234', '更新个人信息', '/sys/user/updatePeronal', 'sys:user:updatePeronal', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1259', '1234', '重置密码页面', '/sys/user/resetPwd/{id}', 'sys:user:resetPwd:{id}', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1260', '1234', '重置密码', '/sys/user/resetPwd', 'sys:user:resetPwd', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1261', '1234', 'bootdo用户列表', '/sys/user/bootdoList', 'sys:user:bootdoList', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1262', '1235', '添加菜单', '/sys/menu/add/{pId}', 'sys:menu:add:{pId}', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1263', '1235', '删除菜单', '/sys/menu/remove', 'sys:menu:remove', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1264', '1235', '菜单列表', '/sys/menu/list', 'sys:menu:list', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1265', '1235', '保存菜单', '/sys/menu/save', 'sys:menu:save', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1266', '1235', '编辑菜单', '/sys/menu/edit/{id}', 'sys:menu:edit:{id}', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1267', '1236', '删除日志', '/common/log/remove', 'common:log:remove', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1268', '1236', '日志列表', '/common/log/list', 'common:log:list', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');
INSERT INTO `sys_menu` VALUES ('1269', '1236', '批量删除日志', '/common/log/batchRemove', 'common:log:batchRemove', '2', null, null, '2020-02-01 08:52:00', '2020-02-01 10:42:37');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(100) DEFAULT NULL COMMENT '角色名称',
  `role_sign` varchar(100) DEFAULT NULL COMMENT '角色标识',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级用户角色', 'admin', '拥有最高权限', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59');

-- ----------------------------
-- Table structure for sys_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=545 DEFAULT CHARSET=utf8 COMMENT='角色与菜单对应关系';

-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('504', '1', '1237');
INSERT INTO `sys_role_menu` VALUES ('505', '1', '1238');
INSERT INTO `sys_role_menu` VALUES ('506', '1', '1239');
INSERT INTO `sys_role_menu` VALUES ('507', '1', '1240');
INSERT INTO `sys_role_menu` VALUES ('508', '1', '1241');
INSERT INTO `sys_role_menu` VALUES ('509', '1', '1242');
INSERT INTO `sys_role_menu` VALUES ('510', '1', '1243');
INSERT INTO `sys_role_menu` VALUES ('511', '1', '1244');
INSERT INTO `sys_role_menu` VALUES ('512', '1', '1245');
INSERT INTO `sys_role_menu` VALUES ('513', '1', '1246');
INSERT INTO `sys_role_menu` VALUES ('514', '1', '1247');
INSERT INTO `sys_role_menu` VALUES ('515', '1', '1248');
INSERT INTO `sys_role_menu` VALUES ('516', '1', '1249');
INSERT INTO `sys_role_menu` VALUES ('517', '1', '1250');
INSERT INTO `sys_role_menu` VALUES ('518', '1', '1251');
INSERT INTO `sys_role_menu` VALUES ('519', '1', '1252');
INSERT INTO `sys_role_menu` VALUES ('520', '1', '1253');
INSERT INTO `sys_role_menu` VALUES ('521', '1', '1254');
INSERT INTO `sys_role_menu` VALUES ('522', '1', '1255');
INSERT INTO `sys_role_menu` VALUES ('523', '1', '1256');
INSERT INTO `sys_role_menu` VALUES ('524', '1', '1257');
INSERT INTO `sys_role_menu` VALUES ('525', '1', '1258');
INSERT INTO `sys_role_menu` VALUES ('526', '1', '1259');
INSERT INTO `sys_role_menu` VALUES ('527', '1', '1260');
INSERT INTO `sys_role_menu` VALUES ('528', '1', '1261');
INSERT INTO `sys_role_menu` VALUES ('529', '1', '1262');
INSERT INTO `sys_role_menu` VALUES ('530', '1', '1263');
INSERT INTO `sys_role_menu` VALUES ('531', '1', '1264');
INSERT INTO `sys_role_menu` VALUES ('532', '1', '1265');
INSERT INTO `sys_role_menu` VALUES ('533', '1', '1266');
INSERT INTO `sys_role_menu` VALUES ('534', '1', '1267');
INSERT INTO `sys_role_menu` VALUES ('535', '1', '1269');
INSERT INTO `sys_role_menu` VALUES ('536', '1', '1232');
INSERT INTO `sys_role_menu` VALUES ('537', '1', '1233');
INSERT INTO `sys_role_menu` VALUES ('538', '1', '1234');
INSERT INTO `sys_role_menu` VALUES ('539', '1', '1235');
INSERT INTO `sys_role_menu` VALUES ('540', '1', '1000');
INSERT INTO `sys_role_menu` VALUES ('541', '1', '1268');
INSERT INTO `sys_role_menu` VALUES ('542', '1', '1236');
INSERT INTO `sys_role_menu` VALUES ('543', '1', '1001');
INSERT INTO `sys_role_menu` VALUES ('544', '1', '-1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `name` varchar(100) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `dept_id` bigint(20) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL COMMENT '邮箱',
  `mobile` varchar(100) DEFAULT NULL COMMENT '手机号',
  `status` tinyint(255) DEFAULT NULL COMMENT '状态 0:禁用，1:正常',
  `user_id_create` bigint(255) DEFAULT NULL COMMENT '创建用户id',
  `gmt_create` datetime DEFAULT NULL COMMENT '创建时间',
  `gmt_modified` datetime DEFAULT NULL COMMENT '修改时间',
  `sex` bigint(32) DEFAULT NULL COMMENT '性别',
  `birth` datetime DEFAULT NULL COMMENT '出身日期',
  `pic_id` bigint(32) DEFAULT NULL,
  `live_address` varchar(500) DEFAULT NULL COMMENT '现居住地',
  `hobby` varchar(255) DEFAULT NULL COMMENT '爱好',
  `province` varchar(255) DEFAULT NULL COMMENT '省份',
  `city` varchar(255) DEFAULT NULL COMMENT '所在城市',
  `district` varchar(255) DEFAULT NULL COMMENT '所在地区',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=138 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', '21e7a79cd2575611c011a9ca17d1b3e4', '6', 'admin@example.com', '17699999999', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00', '96', '2017-12-14 00:00:00', '151', 'ccc', '121;123;124;125;', '北京市', '北京市市辖区', '东城区');
INSERT INTO `sys_user` VALUES ('2', 'test', '临时用户', '60cd54a928cbbcbb6e7b5595bab46a9e', '13', 'test@bootdo.com', null, '1', '1', '2017-08-14 13:43:05', '2017-08-14 21:15:36', null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('136', '沈伟', '沈伟', '49e6595ea39e99d3fd99e73cdad52c80', null, 'shenw@bootma.com', null, '1', null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `sys_user` VALUES ('137', 'jiangxn', '蒋献能', 'cb5e111c4457ca5a626a6f939cf40950', '17', 'jiangxn@bootma.com', null, '1', null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户ID',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='用户与角色对应关系';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '1');
INSERT INTO `sys_user_role` VALUES ('2', '136', '1');
