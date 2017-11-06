/*
MySQL Backup
Source Server Version: 5.5.28
Source Database: db_weixin
Date: 2017/3/30 星期四 10:40:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
--  Table structure for `sys_app_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_app_user`;
CREATE TABLE `sys_app_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `PHONE` varchar(100) DEFAULT NULL,
  `SFID` varchar(100) DEFAULT NULL,
  `START_TIME` varchar(100) DEFAULT NULL,
  `END_TIME` varchar(100) DEFAULT NULL,
  `YEARS` int(10) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_dictionaries`
-- ----------------------------
DROP TABLE IF EXISTS `sys_dictionaries`;
CREATE TABLE `sys_dictionaries` (
  `ZD_ID` varchar(100) NOT NULL,
  `NAME` varchar(100) DEFAULT NULL,
  `BIANMA` varchar(100) DEFAULT NULL,
  `ORDY_BY` int(10) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `JB` int(10) DEFAULT NULL,
  `P_BM` varchar(1000) DEFAULT NULL,
  PRIMARY KEY (`ZD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_gl_qx`
-- ----------------------------
DROP TABLE IF EXISTS `sys_gl_qx`;
CREATE TABLE `sys_gl_qx` (
  `GL_ID` varchar(100) NOT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `FX_QX` int(10) DEFAULT NULL,
  `FW_QX` int(10) DEFAULT NULL,
  `QX1` int(10) DEFAULT NULL,
  `QX2` int(10) DEFAULT NULL,
  `QX3` int(10) DEFAULT NULL,
  `QX4` int(10) DEFAULT NULL,
  PRIMARY KEY (`GL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `MENU_ID` int(11) NOT NULL,
  `MENU_NAME` varchar(255) DEFAULT NULL,
  `MENU_URL` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `MENU_ORDER` varchar(100) DEFAULT NULL,
  `MENU_ICON` varchar(30) DEFAULT NULL,
  `MENU_TYPE` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `ROLE_ID` varchar(100) NOT NULL,
  `ROLE_NAME` varchar(100) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `PARENT_ID` varchar(100) DEFAULT NULL,
  `ADD_QX` varchar(255) DEFAULT NULL,
  `DEL_QX` varchar(255) DEFAULT NULL,
  `EDIT_QX` varchar(255) DEFAULT NULL,
  `CHA_QX` varchar(255) DEFAULT NULL,
  `QX_ID` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `USER_ID` varchar(100) NOT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `RIGHTS` varchar(255) DEFAULT NULL,
  `ROLE_ID` varchar(100) DEFAULT NULL,
  `LAST_LOGIN` varchar(255) DEFAULT NULL,
  `IP` varchar(100) DEFAULT NULL,
  `STATUS` varchar(32) DEFAULT NULL,
  `BZ` varchar(255) DEFAULT NULL,
  `SKIN` varchar(100) DEFAULT NULL,
  `EMAIL` varchar(32) DEFAULT NULL,
  `NUMBER` varchar(100) DEFAULT NULL,
  `PHONE` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `sys_user_qx`
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_qx`;
CREATE TABLE `sys_user_qx` (
  `U_ID` varchar(100) NOT NULL,
  `C1` int(10) DEFAULT NULL,
  `C2` int(10) DEFAULT NULL,
  `C3` int(10) DEFAULT NULL,
  `C4` int(10) DEFAULT NULL,
  `Q1` int(10) DEFAULT NULL,
  `Q2` int(10) DEFAULT NULL,
  `Q3` int(10) DEFAULT NULL,
  `Q4` int(10) DEFAULT NULL,
  PRIMARY KEY (`U_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_account`
-- ----------------------------
DROP TABLE IF EXISTS `tb_account`;
CREATE TABLE `tb_account` (
  `ACCOUNT_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `MEMID` varchar(255) DEFAULT NULL COMMENT '会员ID',
  `TYPE` int(11) DEFAULT '0' COMMENT '操作类型1充值2消费',
  `VALUE` varchar(255) DEFAULT NULL COMMENT '金额',
  `ADDTIME` varchar(255) DEFAULT NULL COMMENT '操作时间',
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户记录';

-- ----------------------------
--  Table structure for `tb_category`
-- ----------------------------
DROP TABLE IF EXISTS `tb_category`;
CREATE TABLE `tb_category` (
  `CATEGORY_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '商品类别ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '类别名称',
  `IMGURL` text COMMENT '图片',
  `DETAIL` text COMMENT '类别描述',
  `STATUS` int(11) DEFAULT '0' COMMENT '状态',
  `INSTIME` varchar(255) DEFAULT NULL COMMENT '插入时间',
  PRIMARY KEY (`CATEGORY_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COMMENT='会员';

-- ----------------------------
--  Table structure for `tb_collection`
-- ----------------------------
DROP TABLE IF EXISTS `tb_collection`;
CREATE TABLE `tb_collection` (
  `COLLECTION_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '收藏ID',
  `TYPE` int(11) DEFAULT '0' COMMENT '类型',
  `MEMID` varchar(255) DEFAULT NULL COMMENT '会员ID',
  `ID` varchar(255) DEFAULT NULL COMMENT '目标ID',
  `ADDTIME` varchar(255) DEFAULT NULL COMMENT '收藏时间',
  PRIMARY KEY (`COLLECTION_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8 COMMENT='图片';

-- ----------------------------
--  Table structure for `tb_comimages`
-- ----------------------------
DROP TABLE IF EXISTS `tb_comimages`;
CREATE TABLE `tb_comimages` (
  `COMIMAGES_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `COMID` varchar(255) DEFAULT NULL COMMENT '商家ID',
  `IMGURL` varchar(255) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`COMIMAGES_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='图片';

-- ----------------------------
--  Table structure for `tb_company`
-- ----------------------------
DROP TABLE IF EXISTS `tb_company`;
CREATE TABLE `tb_company` (
  `COMPANY_ID` varchar(100) NOT NULL COMMENT '商家ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `LONGITUDE` varchar(255) DEFAULT NULL COMMENT '经度',
  `LATITUDE` varchar(255) DEFAULT NULL COMMENT '纬度',
  `TYPE` int(11) DEFAULT '0' COMMENT '商家类型',
  `SALESTIME` varchar(255) DEFAULT NULL COMMENT '营业时间',
  `TELEPHONE` varchar(255) DEFAULT NULL COMMENT '电话号码',
  `WEBSITE` varchar(255) DEFAULT NULL COMMENT '公司网址',
  `LOGOURL` varchar(255) DEFAULT NULL COMMENT 'logo地址',
  `ADDRESS` varchar(500) DEFAULT NULL COMMENT '商家地址',
  `DESCRIBES` varchar(500) DEFAULT NULL COMMENT '一句话的描述',
  `INTRODUCE` text COMMENT '商家介绍',
  `CONFIGURE` varchar(255) DEFAULT NULL COMMENT '商家配置',
  `ISHOME` int(11) DEFAULT '0' COMMENT '首页推广0否1是',
  `ISREC` int(11) DEFAULT '0' COMMENT '是否推荐0否1是',
  `STATUS` int(11) DEFAULT '0' COMMENT '商家状态0冻结1正常',
  `ADDUSER` varchar(255) DEFAULT NULL COMMENT '添加人',
  `ADDTIME` varchar(255) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`COMPANY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家';

-- ----------------------------
--  Table structure for `tb_express_company`
-- ----------------------------
DROP TABLE IF EXISTS `tb_express_company`;
CREATE TABLE `tb_express_company` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `addTime` datetime DEFAULT NULL,
  `deleteStatus` bit(1) NOT NULL,
  `company_mark` varchar(255) DEFAULT NULL,
  `company_name` varchar(255) DEFAULT NULL,
  `company_status` int(11) DEFAULT '0',
  `company_sequence` int(11) DEFAULT '0',
  `company_type` varchar(255) DEFAULT 'EXPRESS',
  `company_telephone` varchar(13) DEFAULT NULL COMMENT '快递公司电话号码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_good`
-- ----------------------------
DROP TABLE IF EXISTS `tb_good`;
CREATE TABLE `tb_good` (
  `GOOD_ID` varchar(100) NOT NULL COMMENT '商品ID',
  `COMID` varchar(100) DEFAULT NULL COMMENT '商家ID',
  `NAME` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `TYPE` int(11) DEFAULT '0' COMMENT '商品类型',
  `IMGURL` varchar(255) DEFAULT NULL COMMENT '商品首页图片',
  `ORIGINALPRICE` varchar(255) DEFAULT NULL COMMENT '商品原价',
  `DISCOUNTPRICE` varchar(255) DEFAULT NULL COMMENT '商品折扣价',
  `PACKAGE` text COMMENT '商品套餐',
  `BUYNOTICE` text COMMENT '购买须知',
  `DESCRIBES` text COMMENT '一句话的描述',
  `DETAIL` varchar(500) DEFAULT NULL COMMENT '一句话的简介',
  `ISHOME` int(11) DEFAULT '0' COMMENT '是否首页推荐',
  `ISREC` int(11) DEFAULT '0' COMMENT '是否推荐',
  `ISHOT` int(11) DEFAULT '0',
  `STATUS` int(11) DEFAULT '0' COMMENT '商品状态0下架1上架',
  `ADDUSER` varchar(255) DEFAULT NULL COMMENT '添加人',
  `ADDTIME` varchar(255) DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`GOOD_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='商家';

-- ----------------------------
--  Table structure for `tb_goodcart`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goodcart`;
CREATE TABLE `tb_goodcart` (
  `CART_ID` varchar(100) NOT NULL,
  `COUNT` int(11) NOT NULL COMMENT '数量',
  `SPEC_INFO` longtext COMMENT '规格组合信息',
  `GOOD_ID` varchar(100) DEFAULT NULL COMMENT '商品 -- 外键',
  `of_id` varchar(100) DEFAULT NULL COMMENT '订单 -- 外键',
  `cart_type` varchar(255) DEFAULT NULL COMMENT '购物车类型',
  `USER_ID` varchar(100) DEFAULT NULL COMMENT '外键 -- 会员',
  `STORE_ID` varchar(100) DEFAULT NULL COMMENT '外键 -- 店铺',
  `PRICE` varchar(12) DEFAULT NULL COMMENT '单价',
  PRIMARY KEY (`CART_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_goodimages`
-- ----------------------------
DROP TABLE IF EXISTS `tb_goodimages`;
CREATE TABLE `tb_goodimages` (
  `GOODIMAGES_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '图片ID',
  `GOODID` varchar(255) DEFAULT NULL COMMENT '商品ID',
  `IMGURL` varchar(255) DEFAULT NULL COMMENT '图片',
  PRIMARY KEY (`GOODIMAGES_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='图片';

-- ----------------------------
--  Table structure for `tb_join`
-- ----------------------------
DROP TABLE IF EXISTS `tb_join`;
CREATE TABLE `tb_join` (
  `JOIN_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '申请记录ID',
  `TYPE` varchar(255) DEFAULT NULL COMMENT '商家类型',
  `NAME` varchar(255) DEFAULT NULL COMMENT '申请人',
  `MOBILE` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `SHOP` varchar(255) DEFAULT NULL COMMENT '商家名称',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '商家地址',
  `DETAIL` varchar(1000) DEFAULT NULL COMMENT '商家描述',
  `NUMBER` int(11) DEFAULT '0' COMMENT '团队人数',
  `SHOPNUM` int(11) DEFAULT '0' COMMENT '线下门店数',
  `IDIMGURL` varchar(255) DEFAULT NULL COMMENT '身份证图片地址',
  `LICENSE` varchar(255) DEFAULT NULL COMMENT '营业执照图片',
  `APPLYTIME` varchar(255) DEFAULT NULL COMMENT '申请时间',
  PRIMARY KEY (`JOIN_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='图片';

-- ----------------------------
--  Table structure for `tb_member`
-- ----------------------------
DROP TABLE IF EXISTS `tb_member`;
CREATE TABLE `tb_member` (
  `MEMBER_ID` varchar(100) NOT NULL COMMENT '会员ID',
  `OPENID` varchar(255) DEFAULT NULL COMMENT '微信ID',
  `SOURCE` int(11) DEFAULT '0' COMMENT '注册来源',
  `PASSWORD` varchar(255) DEFAULT NULL COMMENT '密码',
  `NICKNAME` varchar(255) DEFAULT NULL COMMENT '昵称',
  `NAME` varchar(255) DEFAULT NULL COMMENT '姓名',
  `PHONE` varchar(255) DEFAULT NULL COMMENT '手机号码',
  `SEX` varchar(255) DEFAULT NULL COMMENT '性别',
  `CITY` varchar(255) DEFAULT NULL COMMENT '城市',
  `PROVINCE` varchar(255) DEFAULT NULL COMMENT '省',
  `COUNTRY` varchar(255) DEFAULT NULL COMMENT '国家',
  `HEADIMGURL` varchar(255) DEFAULT NULL COMMENT '头像地址',
  `INTEGRAL` varchar(255) DEFAULT NULL COMMENT '积分',
  `BALANCE` varchar(255) DEFAULT NULL COMMENT '余额',
  `SUBSCRIBE_TIME` varchar(255) DEFAULT NULL COMMENT '关注时间',
  PRIMARY KEY (`MEMBER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员';

-- ----------------------------
--  Table structure for `tb_member_address`
-- ----------------------------
DROP TABLE IF EXISTS `tb_member_address`;
CREATE TABLE `tb_member_address` (
  `ADDRESS_ID` varchar(100) NOT NULL,
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `MEMBER_ID` varchar(100) DEFAULT NULL COMMENT '绑定member',
  `ADDRESS` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `SET_DEFAULT` bigint(1) DEFAULT NULL COMMENT '是否为默认收货地址',
  `ZIP_CODE` int(8) DEFAULT NULL COMMENT '邮编',
  `CONTACTS` varchar(20) DEFAULT NULL COMMENT '联系人',
  `MOBILE_PHONE` varchar(20) DEFAULT NULL COMMENT '移动电话',
  `LANDLINE_TELEPHONE` varchar(20) DEFAULT NULL COMMENT '固定电话',
  PRIMARY KEY (`ADDRESS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_order`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order`;
CREATE TABLE `tb_order` (
  `ORDER_ID` varchar(100) NOT NULL COMMENT '订单ID',
  `ORDERNO` varchar(255) DEFAULT NULL COMMENT '订单号',
  `COMID` varchar(255) DEFAULT NULL COMMENT '商家ID',
  `MEMID` varchar(255) DEFAULT NULL,
  `order_total_price` decimal(12,2) DEFAULT '0.00' COMMENT '订单总金额',
  `pay_status` int(11) DEFAULT '0' COMMENT '0未支付1支付',
  `addTime` datetime DEFAULT NULL COMMENT '下单时间',
  `payTime` datetime DEFAULT NULL COMMENT '支付时间',
  `discount` double DEFAULT '0' COMMENT '优惠金额',
  `REMARKS` varchar(500) DEFAULT NULL COMMENT '备注',
  `out_order_id` varchar(255) DEFAULT NULL COMMENT '订单支付交易号',
  `evaluateStatus` bigint(1) DEFAULT NULL COMMENT '是否评价',
  `finishTime` datetime DEFAULT NULL COMMENT '订单完成时间',
  `updateTime` datetime DEFAULT NULL COMMENT '更新时间',
  `order_type` int(11) DEFAULT NULL COMMENT '订单类型--0普通商品订单',
  `order_status` int(11) DEFAULT NULL COMMENT '订单状态 [0 已取消  10 待付款  20 待发货 30 待收货  40 已完成  50  已结束 60 买家申请退款]',
  `ec_id` varchar(100) DEFAULT NULL COMMENT '快递公司 -- 外键',
  `shipCode` varchar(255) DEFAULT NULL COMMENT '物流单号',
  `shipTime` datetime DEFAULT NULL COMMENT '发货时间',
  `ship_price` decimal(12,2) DEFAULT NULL COMMENT '运费',
  `transport` varchar(255) DEFAULT NULL COMMENT '运输方式',
  `invoice` varchar(255) DEFAULT NULL COMMENT '发票',
  `invoiceType` int(11) DEFAULT NULL COMMENT '发票类型 0 个人  1 单位',
  `refund_price` decimal(12,2) DEFAULT NULL COMMENT '退款金额',
  `refund_type` varchar(255) DEFAULT NULL COMMENT '退款类型',
  `return_shipCode` varchar(255) DEFAULT NULL COMMENT '退款物流单号',
  `return_ec_id` varchar(100) DEFAULT NULL COMMENT '退款物流公司',
  `return_content` longtext COMMENT '退款内容',
  `return_shipTime` datetime DEFAULT NULL COMMENT '退款时间',
  `refund_state` int(2) DEFAULT NULL COMMENT '退款状态  1.正常 2.退款中 3.退款成功  4.退款被拒绝',
  `contacts` varchar(255) DEFAULT NULL COMMENT '收货人姓名',
  `mobile_phone` varchar(20) DEFAULT NULL COMMENT '收货人手机',
  `landline_telephone` varchar(20) DEFAULT NULL COMMENT '收货人座机',
  `receipt_address` varchar(255) DEFAULT NULL COMMENT '收货地址',
  `zip_code` int(8) DEFAULT NULL COMMENT '邮编',
  PRIMARY KEY (`ORDER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='会员';

-- ----------------------------
--  Table structure for `tb_order_good`
-- ----------------------------
DROP TABLE IF EXISTS `tb_order_good`;
CREATE TABLE `tb_order_good` (
  `id` varchar(100) NOT NULL,
  `addTime` datetime DEFAULT NULL,
  `order_id` varchar(100) DEFAULT NULL COMMENT '外键 -- 订单',
  `good_id` varchar(100) DEFAULT NULL COMMENT '外键 -- 商品',
  `good_name` varchar(255) DEFAULT NULL COMMENT '商品名称',
  `good_type` int(2) DEFAULT NULL COMMENT '商品类型',
  `good_num` int(5) DEFAULT '0' COMMENT '商品数量',
  `good_price` decimal(12,2) DEFAULT '0.00' COMMENT '商品价格',
  `good_pay_price` decimal(12,2) DEFAULT '0.00' COMMENT '商品实际成交价格',
  `refund_state` int(2) DEFAULT NULL COMMENT '退款状态  1.正常 2.退款中 3.退款成功  4.退款被拒绝 ',
  `refund_apply_type` int(2) DEFAULT NULL COMMENT '申请类型:1为退款, 2为退货退款,默认为1',
  `refund_back_count` int(11) DEFAULT '0' COMMENT '退款撤销次数',
  `evaluateStatus` bit(1) DEFAULT NULL COMMENT '是否评价',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_pictures`
-- ----------------------------
DROP TABLE IF EXISTS `tb_pictures`;
CREATE TABLE `tb_pictures` (
  `PICTURES_ID` varchar(100) NOT NULL,
  `TITLE` varchar(255) DEFAULT NULL COMMENT '标题',
  `LINK` varchar(500) DEFAULT NULL COMMENT '链接地址',
  `NAME` varchar(255) DEFAULT NULL COMMENT '文件名',
  `PATH` varchar(255) DEFAULT NULL COMMENT '路径',
  `CREATETIME` varchar(255) DEFAULT NULL COMMENT '创建时间',
  `MASTER_ID` varchar(255) DEFAULT NULL COMMENT '属于',
  `STATUS` int(11) DEFAULT '0' COMMENT '0下架1上架',
  `BZ` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`PICTURES_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_user`
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `subscribeText` varchar(255) DEFAULT NULL,
  `sendText` varchar(255) DEFAULT NULL,
  `picUrl` varchar(255) DEFAULT NULL,
  `voiceUrl` varchar(255) DEFAULT NULL,
  `videoUrl` varchar(255) DEFAULT NULL,
  `musicPicUrl` varchar(255) DEFAULT NULL,
  `musicTitle` varchar(255) DEFAULT NULL,
  `musicDescription` varchar(255) DEFAULT NULL,
  `newsArticleCount` int(11) DEFAULT NULL,
  `newsPicUrl` varchar(255) DEFAULT NULL,
  `newsDescription` varchar(255) DEFAULT NULL,
  `newsUrl` varchar(255) DEFAULT NULL,
  `menuText` varchar(255) DEFAULT NULL,
  `menuOne` varchar(255) DEFAULT NULL,
  `menuOneUrl` varchar(255) DEFAULT NULL,
  `menuTwo` varchar(255) DEFAULT NULL,
  `menuThree` varchar(255) DEFAULT NULL,
  `menuThreeUrl` varchar(255) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
--  Table structure for `tb_withdrawals`
-- ----------------------------
DROP TABLE IF EXISTS `tb_withdrawals`;
CREATE TABLE `tb_withdrawals` (
  `WITHDRAWALS_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录ID',
  `APPLYNO` varchar(255) DEFAULT NULL COMMENT '申请单号',
  `MEMID` varchar(255) DEFAULT NULL COMMENT '会员ID',
  `BANK` varchar(255) DEFAULT NULL COMMENT '开户行',
  `CARDNO` varchar(255) DEFAULT NULL COMMENT '银行卡号',
  `NAME` varchar(255) DEFAULT NULL COMMENT '开户名称',
  `TELEPHONE` varchar(255) DEFAULT NULL COMMENT '预留手机号',
  `VALUE` int(11) DEFAULT '0' COMMENT '金额',
  `STATUS` int(11) DEFAULT '0' COMMENT '状态',
  `APPLYTIME` varchar(255) DEFAULT NULL COMMENT '操作时间',
  `APPLYIP` varchar(255) DEFAULT NULL COMMENT '申请IP',
  PRIMARY KEY (`WITHDRAWALS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='账户记录';

-- ----------------------------
--  Records 
-- ----------------------------
INSERT INTO `sys_app_user` VALUES ('04762c0b28b643939455c7800c2e2412','dsfsd','f1290186a5d0b1ceab27f4e77c0c5d68','w','','55896f5ce3c0494fa6850775a4e29ff6','','','1','','18766666666','','','','0','001','18766666666@qq.com');
INSERT INTO `sys_gl_qx` VALUES ('1','2','1','1','1','1','1','1'), ('2','1','0','0','1','1','1','1'), ('55896f5ce3c0494fa6850775a4e29ff6','7','0','0','1','0','0','0'), ('605b0e19e54349d2b8cca96ece966da5','1','0','0','1','1','0','0'), ('68f23fc0caee475bae8d52244dea8444','7','0','0','1','1','0','0'), ('7dfd8d1f7b6245d283217b7e63eec9b2','1','1','1','1','0','0','0'), ('ac66961adaa2426da4470c72ffeec117','1','1','0','1','1','0','0'), ('b0c77c29dfa140dc9b14a29c056f824f','7','1','0','1','1','0','0'), ('e74f713314154c35bd7fc98897859fe3','6','1','1','1','1','0','0'), ('f944a9df72634249bbcb8cb73b0c9b86','7','1','1','1','1','0','0');
INSERT INTO `sys_menu` VALUES ('1','系统管理','#','0','1','icon-desktop','2'), ('2','组织管理','role.do','1','2',NULL,'2'), ('5','系统用户','user/listUsers.do','1','3',NULL,'2'), ('7','图片管理','pictures/list.do','30','1',NULL,'2'), ('21','会员管理','#','0','2','icon-comments','2'), ('23','会员列表','member/list.do','21','2',NULL,'2'), ('24','商家管理','#','0','3','icon-list','2'), ('25','商家列表','company/list.do','24','1',NULL,'2'), ('26','类别列表','category/list.do','24','2',NULL,'2'), ('27','商品列表','good/list.do','24','3',NULL,'2'), ('28','加盟管理','#','0','5','icon-edit','2'), ('29','加盟列表','join/list.do','28','1',NULL,'2'), ('30','图片管理','#','0','6','icon-picture','2'), ('31','菜单管理','menu.do','1','3',NULL,'2'), ('32','基础数据','#','0','7','icon-book','2'), ('33','数据字典','dictionaries.do?PARENT_ID=0','32','1',NULL,'2'), ('34','账户记录','account/list.do','21','2',NULL,'2'), ('35','订单管理','#','0','4','icon-file','2'), ('36','订单列表','order/list.do','35','1',NULL,'2'), ('37','提现申请','withdrawals/list.do','21','3',NULL,'2');
INSERT INTO `sys_role` VALUES ('1','系统管理员','274871615654','0','1','1','1','1','1'), ('2','超级管理员','274871615654','1','246','50','50','38','2'), ('4','用户组','118','0','0','0','0','0',NULL), ('55896f5ce3c0494fa6850775a4e29ff6','特级会员','498','7','0','0','0','0','55896f5ce3c0494fa6850775a4e29ff6'), ('6','客户组','18','0','1','1','1','1',NULL), ('68f23fc0caee475bae8d52244dea8444','中级会员','498','7','0','0','0','0','68f23fc0caee475bae8d52244dea8444'), ('7','会员组','498','0','0','0','0','1',NULL), ('7dfd8d1f7b6245d283217b7e63eec9b2','管理员B','274871615654','1','246','0','0','0','7dfd8d1f7b6245d283217b7e63eec9b2'), ('ac66961adaa2426da4470c72ffeec117','管理员A','274871615654','1','54','54','0','246','ac66961adaa2426da4470c72ffeec117'), ('b0c77c29dfa140dc9b14a29c056f824f','高级会员','498','7','0','0','0','0','b0c77c29dfa140dc9b14a29c056f824f'), ('e74f713314154c35bd7fc98897859fe3','黄金客户','18','6','1','1','1','1','e74f713314154c35bd7fc98897859fe3'), ('f944a9df72634249bbcb8cb73b0c9b86','初级会员','498','7','1','1','1','1','f944a9df72634249bbcb8cb73b0c9b86');
INSERT INTO `sys_user` VALUES ('0b3f2ab1896b47c097a81d322697446a','zhangsan','c4ca4238a0b923820dcc509a6f75849b','张三','','2','2015-01-02 12:04:51','127.0.0.1','0','小张','default','wwwwq@qq.com','1101','18788888888'), ('1','admin','dd94709528bb1c83d08f3088d4043f4742891f4f','系统管理员','1133671055321055258374707980945218933803269864762743594642571294','1','2016-12-31 11:40:49','127.0.0.1','0','最高统治者','default','admin@main.com','001','18788888888'), ('681464038b004566b3c437bcc6555d63','lj','c115f71e0ba5886e466c32ce77a8329deaabdbdf','李四','','ac66961adaa2426da4470c72ffeec117','2016-11-15 16:59:50','112.67.195.215','0','杨永明是管理员','default','734562051@qq.com','1102','18898218580');
INSERT INTO `sys_user_qx` VALUES ('1','1','0','0','0','0','0','0','0'), ('2','1','1','1','1','1','1','1','1'), ('55896f5ce3c0494fa6850775a4e29ff6','0','0','0','0','0','0','0','0'), ('605b0e19e54349d2b8cca96ece966da5','0','0','0','0','0','0','0','0'), ('68f23fc0caee475bae8d52244dea8444','0','0','0','0','0','0','0','0'), ('7dfd8d1f7b6245d283217b7e63eec9b2','0','0','0','0','0','0','0','0'), ('ac66961adaa2426da4470c72ffeec117','0','0','0','0','0','0','0','0'), ('b0c77c29dfa140dc9b14a29c056f824f','0','0','0','0','0','0','0','0'), ('e74f713314154c35bd7fc98897859fe3','0','0','0','0','0','0','0','0'), ('f944a9df72634249bbcb8cb73b0c9b86','0','0','0','0','0','0','0','0');
INSERT INTO `tb_category` VALUES ('1','附近商超','uploadFiles/uploadImgs/category/20161110/743d1e29e9ef4329a21cfc8283ae30d8.png','<p>附近超市</p>','1','2016-08-11 11:21:45'), ('2','特色农产品','uploadFiles/uploadImgs/category/20161110/6ff8ce1ad50f4ef9be419b9c9a251950.png','<p>海南特色农产品</p>','1','2016-08-18 18:06:06'), ('3','酒店','uploadFiles/uploadImgs/category/20161110/aaa6411cdfa24d4480a5a189cd9e35d1.png','<p>酒店</p>','0','2016-08-18 18:06:26'), ('4','娱乐','uploadFiles/uploadImgs/category/20161110/804a06e20c8547e1a5e216e63932bd8a.png','<p>娱乐</p>','0','2016-08-18 18:06:44'), ('5','KTV','uploadFiles/uploadImgs/category/20161110/ff6399b5af304377a3014f957838a2b7.png','<p>KTV<br/></p>','0','2016-08-18 18:07:11'), ('6','旅游','uploadFiles/uploadImgs/category/20161110/42188f0bebe1421498886e8323bdd3be.png','<p>旅游</p>','0','2016-08-18 18:07:31'), ('7','丽人','uploadFiles/uploadImgs/category/20161110/27f5aa9a34924d2aa9aab5894b3060ed.png','<p>丽人<br/></p>','0','2016-08-18 18:07:47');
INSERT INTO `tb_collection` VALUES ('24','2','454e96febf0c40e48590e3ef16492c60','5b448c47782b437b998765e537087eea','2016-08-23 18:14:53'), ('25','2','7926cc04aecc4c0f9bb5eb6f9de070c9','5b448c47782b437b998765e537087eea','2016-09-09 15:51:41'), ('26','2','7926cc04aecc4c0f9bb5eb6f9de070c9','5b448c47782b437b998765e537087eea','2016-09-17 20:20:34'), ('27','2','23c35b808f9043d9afc048e9740f948c','9969cda29d6949c88d3a8550fbbd2708','2016-11-11 17:36:46'), ('28','2','23c35b808f9043d9afc048e9740f948c','9969cda29d6949c88d3a8550fbbd2708','2016-11-11 17:36:50'), ('29','2','052000f99ad849499558e84b92f53663','9969cda29d6949c88d3a8550fbbd2708','2016-11-14 13:04:50'), ('30','2','052000f99ad849499558e84b92f53663','0331105199cd4fac92996d4c01312f91','2016-11-23 14:12:40'), ('31','2','052000f99ad849499558e84b92f53663','0331105199cd4fac92996d4c01312f91','2016-11-23 14:21:43'), ('32','2','9742236b0159477b93fe09602e393eb4','1d2791cf7e6b4c07be8f7a35668a71eb','2016-12-06 15:05:31'), ('33','2','4889a9ad2212469aa46a2eba82793a6c','1d2791cf7e6b4c07be8f7a35668a71eb','2016-12-09 15:25:26'), ('34','2','4889a9ad2212469aa46a2eba82793a6c','1d2791cf7e6b4c07be8f7a35668a71eb','2016-12-09 16:01:14'), ('35','2','4889a9ad2212469aa46a2eba82793a6c','1d2791cf7e6b4c07be8f7a35668a71eb','2016-12-09 16:06:27'), ('36','2','4889a9ad2212469aa46a2eba82793a6c','1d2791cf7e6b4c07be8f7a35668a71eb','2016-12-09 16:40:52'), ('37','2','4889a9ad2212469aa46a2eba82793a6c','1d2791cf7e6b4c07be8f7a35668a71eb','2016-12-09 16:43:55'), ('38','2','201ad0fde9ad4b6180ba4c9d8d441bbf','b01b44794be04d499bfe956151ebdbc7','2016-12-20 11:19:58'), ('39','2','201ad0fde9ad4b6180ba4c9d8d441bbf','b01b44794be04d499bfe956151ebdbc7','2016-12-20 11:20:02'), ('40','2','201ad0fde9ad4b6180ba4c9d8d441bbf','b01b44794be04d499bfe956151ebdbc7','2016-12-22 17:46:04');
INSERT INTO `tb_comimages` VALUES ('7','52844591b9354cb3b05ebcdc1fe1db7f','uploadFiles/uploadImgs/company/20160818/e37429fe5dbe4b79938916c4373ea0a1.jpg'), ('11','eeab2c132382430abc4484f3927e81c2','uploadFiles/uploadImgs/company/20160908/0697a63bd25442feafe35a9169f13665.jpg'), ('12','eeab2c132382430abc4484f3927e81c2','uploadFiles/uploadImgs/company/20160908/95d4cfbbbbb5460080288cf1c814fa2c.jpg'), ('13','eeab2c132382430abc4484f3927e81c2','uploadFiles/uploadImgs/company/20160908/1adf1c2fc21f44a5844f6ea0b41249e9.jpg'), ('14','a4d343925f484bdabde7895302a2a604','uploadFiles/uploadImgs/company/20160908/1784f4015c144cd1a3598ecd33f8aa27.jpg'), ('15','a4d343925f484bdabde7895302a2a604','uploadFiles/uploadImgs/company/20160908/d21c7c34b4d54deeb6d14ae40bf5fc2b.jpg'), ('16','a4d343925f484bdabde7895302a2a604','uploadFiles/uploadImgs/company/20160908/cd993d73b0994eeea3e3695b05bf12a0.jpg'), ('17','82707cea054f4de38d888356387052e1','uploadFiles/uploadImgs/company/20161206/4a91c9f184de4ffcadc16606d81863ef.jpg'), ('18','b0cc1e30fd6a46ab9306a24a91f77ea9','uploadFiles/uploadImgs/company/20161206/a7b698c840794ba7a9535af880f78862.jpg'), ('19','b0cc1e30fd6a46ab9306a24a91f77ea9','uploadFiles/uploadImgs/company/20161206/401654ffb57a40789c0285434eb2e41a.jpg'), ('20','e98da1ba007b41b8a196a16b76853e94','uploadFiles/uploadImgs/company/20161206/0e0dad8beba44122b4972cad8270eeb5.jpg'), ('21','e98da1ba007b41b8a196a16b76853e94','uploadFiles/uploadImgs/company/20161206/7fe77065276042df90193ab537241411.jpg');
INSERT INTO `tb_company` VALUES ('88347583f4944fa6bdeb5dd67aa76eda','寿司店','20.017378','110.349229','1','8:00-12:00','15501890101','www.0898.com','uploadFiles/uploadImgs/company_logo/20161123/2481f510f8854461943f565cada403f0.jpg','海口市琼山大道18号','寿司很好吃','<p>很好吃</p>','空调','1','1','1','系统管理员','2016-11-23 11:09:20'), ('a2b28b38bc5348cbaf56beec3aafaf8a','红鱼店','19.993938','110.418842','1','全天','0898-56548975','www.0898yzzx.com','','海口市美兰区琼山大道18号','美食','<p>红鱼很美味，快点来品尝</p>','这里是商家配置','1','1','1','系统管理员','2016-11-11 15:32:13'), ('b0cc1e30fd6a46ab9306a24a91f77ea9','麦当劳','114.027830','22.608150','1','23','17176667667','http://a.com','uploadFiles/uploadImgs/company_logo/20161206/ae943616d7d141fe8ef315f59f1b2364.jpg','广东省深圳市宝安区深圳北站高铁候车大厅商业城2楼2-17','上地理课肌肤','<p>水淀粉</p>','玩儿','1','1','1','系统管理员','2016-12-06 11:37:30'), ('e98da1ba007b41b8a196a16b76853e94','肯德基','114.030200','22.609320','1','24小时','18129978776','http://a.com','uploadFiles/uploadImgs/company_logo/20161206/789cb3a37c7f481eb0d62fc0c67b80d5.jpg','广东省深圳市宝安区深圳北站高铁候车厅2楼','是来看大家疯狂世界的风看见了是对肌肤','<p>水淀粉&nbsp;</p>','水淀粉','1','1','1','系统管理员','2016-12-06 10:54:18');
INSERT INTO `tb_express_company` VALUES ('1','2013-08-12 11:49:16','\0','shunfeng','顺丰速递','0','0','EXPRESS','95338'), ('2','2013-08-12 11:51:18','\0','shentong','申通快递','0','1','EXPRESS','95543'), ('3','2013-08-12 11:52:55','\0','tiantian','天天快递','0','2','EXPRESS','4001888888'), ('4','2013-08-12 11:53:27','\0','youzhengguonei','包裹/平邮/挂号信','0','4','POST','11185'), ('6','2013-08-12 11:55:31','\0','bangsongwuliu','邦送物流','0','5','EXPRESS','13430440099'), ('7','2013-08-12 12:01:12','\0','cces','希伊艾斯（CCES）','0','6','EXPRESS',''), ('8','2013-08-12 12:01:37','\0','coe','中国东方（COE）','0','7','EXPRESS',''), ('9','2013-08-12 12:02:10','\0','city100','城市100','0','8','EXPRESS','4008200088'), ('10','2013-08-12 12:02:27','\0','chuanxiwuliu','传喜物流','0','9','EXPRESS','4007775656'), ('11','2013-08-12 12:02:46','\0','datianwuliu','大田物流','0','10','EXPRESS','4006261166'), ('12','2013-08-12 12:03:02','\0','debangwuliu','德邦物流','0','11','EXPRESS','95353'), ('13','2013-08-12 12:03:19','\0','dsukuaidi','D速快递','0','12','EXPRESS','053188636363'), ('14','2013-08-12 12:03:37','\0','disifang','递四方','0','13','EXPRESS','075529771100'), ('15','2013-08-12 12:04:02','\0','ems','EMS','0','14','EMS','11183'), ('16','2013-08-12 12:04:20','\0','feikangda','飞康达物流','0','15','EXPRESS','4006802311'), ('17','2013-08-12 12:04:36','\0','feikuaida','飞快达','0','16','EXPRESS','4007166666'), ('18','2013-08-12 12:04:53','\0','rufengda','凡客如风达','0','17','EXPRESS','4000106660'), ('19','2013-08-12 12:05:12','\0','fengxingtianxia','风行天下','0','18','EXPRESS',''), ('20','2013-08-12 12:05:26','\0','feibaokuaidi','飞豹快递','0','19','EXPRESS','02552433090'), ('21','2013-08-12 12:05:59','\0','ganzhongnengda','港中能达','0','20','EXPRESS','02160829858'), ('22','2013-08-12 12:06:21','\0','guotongkuaidi','国通快递','0','21','EXPRESS','4001111123'), ('23','2013-08-12 12:06:43','\0','guangdongyouzhengwuliu','广东邮政','0','22','EXPRESS',''), ('24','2013-08-12 12:07:07','\0','gls','GLS','0','23','EXPRESS',''), ('25','2013-08-12 12:07:33','\0','gongsuda','共速达','0','24','EXPRESS',''), ('26','2013-08-12 12:08:05','\0','huitongkuaidi','汇通快运','0','25','EXPRESS','4009565656'), ('27','2013-08-12 12:08:31','\0','huiqiangkuaidi','汇强快递','0','26','EXPRESS','4000000177'), ('28','2013-08-12 12:08:51','\0','tiandihuayu','华宇物流','0','27','EXPRESS','4008086666'), ('29','2016-06-12 17:25:39','\0','zhongtong','中通快递','0','20','','95311');
INSERT INTO `tb_good` VALUES ('1a25e51239d64994af1608e6bc58d05e','e98da1ba007b41b8a196a16b76853e94','foiasjdflksaj','1','uploadFiles/uploadImgs/good_logo/20161223/44a979c9cbbc4826904723884681ebbe.jpg','123','111','<p>asdfnksajf&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</p>','<p>adfnaskjdf</p>','<p>ahfkdhsdakljf</p>','afdasdf','1','0','1','1','系统管理员','2016-12-23 16:42:14'), ('5bc2e873613a4081ae1566da0c13d7cb','e98da1ba007b41b8a196a16b76853e94','31312','1','uploadFiles/uploadImgs/good_logo/20161222/c6e39e94808e46c1bd4c03e4685e40f3.jpg','11','1','<p>1</p>','<p>2</p>','<p>3</p>','1231','1','1','0','1','系统管理员','2016-12-22 17:57:28'), ('b01b44794be04d499bfe956151ebdbc7','b0cc1e30fd6a46ab9306a24a91f77ea9','烤全翅','1','uploadFiles/uploadImgs/good_logo/20161216/a3fbb390afd8497da06631aa2067d3c0.jpg','110','90','<p>无&nbsp;&nbsp;&nbsp;&nbsp;</p>','<p>无</p>','<p>无</p>','无','1','0','0','1','系统管理员','2016-12-16 16:46:30');
INSERT INTO `tb_goodcart` VALUES ('2a890696f6db403c9d52e8da162c855a','5',NULL,'1a25e51239d64994af1608e6bc58d05e',NULL,'1','201ad0fde9ad4b6180ba4c9d8d441bbf','e98da1ba007b41b8a196a16b76853e94','111'), ('398c76a579854e01b79449851dd165a1','2',NULL,'b01b44794be04d499bfe956151ebdbc7',NULL,'1','4889a9ad2212469aa46a2eba82793a6c','e98da1ba007b41b8a196a16b76853e94','90'), ('5c6ce40d9ea14be3829f437881d195c6','1',NULL,'b01b44794be04d499bfe956151ebdbc7',NULL,'1','201ad0fde9ad4b6180ba4c9d8d441bbf','b0cc1e30fd6a46ab9306a24a91f77ea9','90'), ('a64778246a464578ad4240164ab52b2c','5',NULL,'5bc2e873613a4081ae1566da0c13d7cb',NULL,'1','201ad0fde9ad4b6180ba4c9d8d441bbf','e98da1ba007b41b8a196a16b76853e94','1');
INSERT INTO `tb_goodimages` VALUES ('15',NULL,'uploadFiles/uploadImgs/good/20160818/4c49c7701310489493c76f9ac61e650f.jpg'), ('18','5b448c47782b437b998765e537087eea','uploadFiles/uploadImgs/good/20160908/85a26e18f83f417d8757f3227a28b758.jpg'), ('19','5b448c47782b437b998765e537087eea','uploadFiles/uploadImgs/good/20160908/58828e67fa0d49769405629b6fcb5f61.jpg'), ('20','5b448c47782b437b998765e537087eea','uploadFiles/uploadImgs/good/20160908/9c0be2acb8564a799767e70dd410bae4.jpg'), ('23','b01b44794be04d499bfe956151ebdbc7','uploadFiles/uploadImgs/good/20161216/78191a03c50d4d14aebdff6d090f45ab.jpg'), ('24','b01b44794be04d499bfe956151ebdbc7','uploadFiles/uploadImgs/good/20161216/fdbeefb9b73a411ea648a19b0e14a77e.jpg'), ('25','5bc2e873613a4081ae1566da0c13d7cb','uploadFiles/uploadImgs/good/20161222/81a88868f14f485087c817c2c9720427.png'), ('26','1a25e51239d64994af1608e6bc58d05e','uploadFiles/uploadImgs/good/20161226/097e3651165748a7b9d6b1795f3cfce5.jpg');
INSERT INTO `tb_join` VALUES ('22','餐饮业','梁森','13503705555','肯德基','深圳市','时尚大方接受旅客大幅减少','2','3',NULL,NULL,'2016-08-25 23:33:43');
INSERT INTO `tb_member` VALUES ('201ad0fde9ad4b6180ba4c9d8d441bbf','oo0OTwTF6XAc2rM6qg59dQJkBWJ8','1','e10adc3949ba59abbe56e057f20f883e','人生漫步',NULL,'15572115225','1','海口','海南','中国','http://wx.qlogo.cn/mmopen/eKNgra9PyaY4lE9555SOPgeEw02drMtIWGMZicMGw5XhlJRfmP4KZtA9oBAQhDp1vgJw0vkKH3XTnLtiampX3JhoAPicJ8ahsUL/0','0','0','2016-12-08 10:19:15'), ('4889a9ad2212469aa46a2eba82793a6c','oo0OTwfAiQuw-f0Zvf3Szvl5Etas','1','e10adc3949ba59abbe56e057f20f883e','梁森',NULL,'18129947873','1','深圳','广东','中国','http://wx.qlogo.cn/mmopen/LPLYlyQ5GAqgdnxsU81YQrxFXJAuibo8dyThBv7oKcN6T1hHt0l5S0w6gsl9G3Z8FqPHH9zmkQfHCsVqlW7A0QGThgK2I4Oab/0','0','0','2016-12-07 23:09:19');
INSERT INTO `tb_member_address` VALUES ('18c33b98f2f943e4ad5749188225087e','2016-12-29 17:26:56','201ad0fde9ad4b6180ba4c9d8d441bbf','海南省海口市琼山区琼山大道86号江东电子商务园','1','571200','王八蛋','15572115225','0898-36800789'), ('baf527d60a2d4de48a4c062d71df6114','2016-12-29 17:47:11','201ad0fde9ad4b6180ba4c9d8d441bbf','卡死看风景啊哈大富科技啦技术的弗兰克静安寺','0','570000','李全蛋','188898226000','0898-36800789');
INSERT INTO `tb_order` VALUES ('4430976235c04bb3ad7aff10077027cb','1000001335663197','e98da1ba007b41b8a196a16b76853e94',NULL,'111.00','3','2016-12-27 11:17:33',NULL,'0','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('578390a8ae264ff7a51f83217cc0561b','1000001769855520','e98da1ba007b41b8a196a16b76853e94',NULL,'90.00','3','2016-12-20 14:51:09',NULL,'0','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('91cc116b41254d1fa6cfaa950fdfdaf8','1000001696016146','e98da1ba007b41b8a196a16b76853e94',NULL,'111.00','3','2016-12-27 11:49:49',NULL,'0','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('e8348f050feb43fd8f9b83c0118f3791','1000000602775661','e98da1ba007b41b8a196a16b76853e94',NULL,'111.00','3','2016-12-27 16:04:40',NULL,'0','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL), ('eda0c30ab5aa4945a40a44380b632797','1000001126859549','e98da1ba007b41b8a196a16b76853e94',NULL,'90.00','3','2016-12-20 14:50:23',NULL,'0','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL);
INSERT INTO `tb_pictures` VALUES ('78e9ab80374d4a208a809745d3c36837','图片',NULL,'c57b4a443c49430e895d3001a1d34353.jpg','uploadFiles/uploadImgs/banner/20161216/c57b4a443c49430e895d3001a1d34353.jpg','2016-12-16 17:17:25','1','1','图片管理处上传'), ('818cc3793404424a98e0516207329c85','图片',NULL,'090b3656c049468ea8a2c0ec7b034502.jpg','uploadFiles/uploadImgs/banner/20161216/090b3656c049468ea8a2c0ec7b034502.jpg','2016-12-16 17:17:25','1','1','图片管理处上传'), ('eff4fc32deea47288b539c41dbc41490','图片',NULL,'8ec3240882b54c3e8b106a3f7f922f04.jpg','uploadFiles/uploadImgs/banner/20161216/8ec3240882b54c3e8b106a3f7f922f04.jpg','2016-12-16 17:17:26','1','1','图片管理处上传');
