/*
MySQL Data Transfer
Source Host: localhost
Source Database: helper
Target Host: localhost
Target Database: helper
Date: 2016/8/15 0:48:29
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for addressinfo
-- ----------------------------
CREATE TABLE `addressinfo` (
  `id` int(11) NOT NULL auto_increment,
  `loginname` varchar(50) NOT NULL,
  `consignee` varchar(20) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `area` varchar(100) default NULL,
  `address` varchar(100) NOT NULL,
  `postcode` varchar(10) default NULL,
  `isdefault` varchar(10) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for appinfo
-- ----------------------------
CREATE TABLE `appinfo` (
  `id` int(11) NOT NULL auto_increment,
  `versionname` varchar(20) NOT NULL,
  `versiondescribe` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for bankcard
-- ----------------------------
CREATE TABLE `bankcard` (
  `id` int(11) NOT NULL auto_increment,
  `cardid` varchar(20) NOT NULL,
  `name` varchar(10) NOT NULL,
  `identitycard` varchar(20) NOT NULL,
  `password` varchar(10) NOT NULL,
  `money` double(11,2) NOT NULL,
  `banktype` varchar(10) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for comment
-- ----------------------------
CREATE TABLE `comment` (
  `id` int(11) NOT NULL auto_increment,
  `loginname` varchar(50) NOT NULL,
  `qyid` int(11) NOT NULL,
  `content` varchar(255) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for commodity
-- ----------------------------
CREATE TABLE `commodity` (
  `id` int(11) NOT NULL auto_increment,
  `shopid` int(11) NOT NULL,
  `name` varchar(100) default NULL,
  `pic` varchar(100) default NULL,
  `price` double(11,2) default NULL,
  `salednum` int(11) default NULL,
  `stocknum` int(11) default NULL,
  `love` int(11) default NULL,
  `description` varchar(255) default NULL,
  `color` varchar(30) default NULL,
  `version` varchar(50) default NULL,
  `tag` varchar(50) default NULL,
  `evaluation` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
CREATE TABLE `feedback` (
  `id` int(11) NOT NULL auto_increment,
  `content` varchar(255) NOT NULL,
  `authorname` varchar(50) NOT NULL,
  `contact` varchar(100) NOT NULL,
  `submittime` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for foodinfo
-- ----------------------------
CREATE TABLE `foodinfo` (
  `id` int(11) NOT NULL auto_increment,
  `shopid` int(11) NOT NULL,
  `name` varchar(100) default NULL,
  `pic` varchar(100) default NULL,
  `price` double(11,2) default NULL,
  `salednum` int(11) default NULL,
  `love` int(11) default NULL,
  `description` varchar(255) default NULL,
  `tag` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for foodshop
-- ----------------------------
CREATE TABLE `foodshop` (
  `id` int(11) NOT NULL auto_increment,
  `shopname` varchar(100) NOT NULL,
  `pic` varchar(100) default NULL,
  `starnum` float(11,2) default NULL,
  `salednum` int(11) default NULL,
  `startingprice` int(11) default NULL,
  `deliverprice` int(11) default NULL,
  `delivertime` int(11) default NULL,
  `type` varchar(50) NOT NULL,
  `city` varchar(20) NOT NULL,
  `dispatchtime` varchar(100) default NULL,
  `contact` varchar(50) default NULL,
  `address` varchar(100) default NULL,
  `serviceprovider` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for helperpay
-- ----------------------------
CREATE TABLE `helperpay` (
  `id` int(11) NOT NULL auto_increment,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) default NULL,
  `money` double(11,2) default NULL,
  `bankcardlist` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for orderinfo
-- ----------------------------
CREATE TABLE `orderinfo` (
  `id` int(11) NOT NULL auto_increment,
  `orderid` varchar(50) NOT NULL,
  `submittime` varchar(50) NOT NULL,
  `paystatus` varchar(10) NOT NULL,
  `shopid` int(11) NOT NULL,
  `totalprice` double(11,2) default NULL,
  `deliveraddress` varchar(100) default NULL,
  `deliverinfo` varchar(100) default NULL,
  `loginname` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pollingservice
-- ----------------------------
CREATE TABLE `pollingservice` (
  `id` int(11) NOT NULL auto_increment,
  `usercode` varchar(100) NOT NULL,
  `pushtype` varchar(50) NOT NULL,
  `pushtime` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for promotion
-- ----------------------------
CREATE TABLE `promotion` (
  `id` int(11) NOT NULL auto_increment,
  `shopid` int(11) NOT NULL,
  `firstorder` varchar(100) default NULL,
  `fullreduction` varchar(100) default NULL,
  `discount` varchar(100) default NULL,
  `vouchers` varchar(100) default NULL,
  `preorder` varchar(100) default NULL,
  `fullofgifts` varchar(100) default NULL,
  `freedistribution` varchar(100) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pushmessage
-- ----------------------------
CREATE TABLE `pushmessage` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `message` varchar(255) default NULL,
  `url` varchar(255) default NULL,
  `pushmode` varchar(20) default NULL,
  `createtime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for qiangyu
-- ----------------------------
CREATE TABLE `qiangyu` (
  `id` int(11) NOT NULL auto_increment,
  `authorname` varchar(50) NOT NULL,
  `content` varchar(255) NOT NULL,
  `pic` varchar(100) default NULL,
  `love` int(11) default NULL,
  `createtime` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for repairinfo
-- ----------------------------
CREATE TABLE `repairinfo` (
  `id` int(11) NOT NULL auto_increment,
  `authorname` varchar(50) NOT NULL,
  `address` varchar(150) default NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(255) default NULL,
  `piclist` varchar(255) default NULL,
  `createtime` varchar(50) default NULL,
  `price` double(11,2) default NULL,
  `repairtype` varchar(30) NOT NULL,
  `kind` varchar(50) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shopinfo
-- ----------------------------
CREATE TABLE `shopinfo` (
  `id` int(11) NOT NULL auto_increment,
  `shopname` varchar(100) NOT NULL,
  `pic` varchar(100) default NULL,
  `promotion` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `city` varchar(20) default NULL,
  `type` varchar(50) default NULL,
  `mainbusiness` varchar(255) default NULL,
  `contact` varchar(50) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
CREATE TABLE `userinfo` (
  `loginname` varchar(50) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `password` varchar(50) default NULL,
  `headphoto` varchar(200) default NULL,
  `nickname` varchar(50) default NULL,
  `sex` varchar(5) default NULL,
  `signature` varchar(100) default NULL,
  `realname` varchar(20) default NULL,
  `identitycard` varchar(20) default NULL,
  `ischeck` varchar(10) default NULL,
  PRIMARY KEY  (`loginname`,`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for visitinfo
-- ----------------------------
CREATE TABLE `visitinfo` (
  `id` int(11) NOT NULL auto_increment,
  `authorname` varchar(50) NOT NULL,
  `address` varchar(150) default NULL,
  `title` varchar(100) NOT NULL,
  `description` varchar(255) default NULL,
  `piclist` varchar(255) default NULL,
  `love` int(11) default NULL,
  `createtime` varchar(50) default NULL,
  `price` double(11,2) default NULL,
  `visittype` varchar(30) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `addressinfo` VALUES ('1', 'xuexiang', '陈雅玮', '15197292809', '湖南省湘潭市雨湖区雨湖路街道', '北二环路湘潭大学区体育馆东100米盖天彩钢夹芯板厂', '411105', '');
INSERT INTO `addressinfo` VALUES ('2', 'xuexiang', '田春凤', '18914743827', '江苏省南京市雨花台区雨花街道', '雨花新村东大门门卫处锦才五金建材经营部', '210012', 'yes');
INSERT INTO `addressinfo` VALUES ('3', 'xuexiang', '薛井才', '13951742868', '江苏南京雨花台区', '韩府新苑2幢403室', '210012', '');
INSERT INTO `addressinfo` VALUES ('4', 'xuexin', '薛欣', '13813843913', '江苏南京雨花台区', '雨花新村', '210012', 'yes');
INSERT INTO `addressinfo` VALUES ('5', 'xuexin', '陈雅玮', '13813843913', '江苏盐城市辖区', '娱乐村', '210012', '');
INSERT INTO `addressinfo` VALUES ('6', 'test', '薛井才', '13951742868', '江苏南京雨花台区', '雨花新村二村二十幢', '210012', 'yes');
INSERT INTO `addressinfo` VALUES ('8', 'test', '田春风', '18914743827', '江苏南京雨花台区', '铁心桥韩府新苑', '210012', '');
INSERT INTO `addressinfo` VALUES ('9', 'tianbin', '田斌', '13801591447', '江苏南京雨花台区', '…………………………………………', '210012', 'yes');
INSERT INTO `appinfo` VALUES ('1', '1.0', '最初的版本，只有主页面和设置界面');
INSERT INTO `appinfo` VALUES ('2', '1.1', '增加了版本更新的功能');
INSERT INTO `appinfo` VALUES ('3', '1.2', '测试版本');
INSERT INTO `appinfo` VALUES ('4', '1.3', '增加了维修模块、外卖模块、上门服务模块。');
INSERT INTO `bankcard` VALUES ('1', '1234567890', '薛翔', '330521199408063811', 'xuexiang', '1000000.00', 'ccb');
INSERT INTO `bankcard` VALUES ('2', '1212121212', '薛欣', '330521198809193823', 'xuexin', '123456.00', 'icbc');
INSERT INTO `comment` VALUES ('1', 'xuexiang', '1', '测试');
INSERT INTO `comment` VALUES ('2', 'xuexin', '28', '这个可以的');
INSERT INTO `comment` VALUES ('3', 'xuexin', '28', '我就是来玩玩的！');
INSERT INTO `comment` VALUES ('4', 'xuexin', '28', '哈哈哈');
INSERT INTO `comment` VALUES ('5', 'xuexin', '25', '哈哈');
INSERT INTO `comment` VALUES ('6', 'xuexiang', '7', '任性');
INSERT INTO `comment` VALUES ('7', 'xuexiang', '6', '哈哈');
INSERT INTO `comment` VALUES ('8', 'xuexiang', '30', '你好啊，交个朋友吧！');
INSERT INTO `comment` VALUES ('9', 'xuexin', '31', '好努力！');
INSERT INTO `comment` VALUES ('10', 'xuexin', '34', 'Hao');
INSERT INTO `comment` VALUES ('11', 'xuexin', '30', '确实啊！');
INSERT INTO `comment` VALUES ('12', 'xuexin', '34', '切~~');
INSERT INTO `comment` VALUES ('13', 'xuexin', '43', '很努力哦！');
INSERT INTO `feedback` VALUES ('2', '测试！', 'xuexiang', '15251329565', '2016-02-19 16:58:02');
INSERT INTO `feedback` VALUES ('3', '太卡，不好用！', 'xuexiang', '342475930', '2016-02-19 17:00:36');
INSERT INTO `feedback` VALUES ('4', '不好用啊！', 'xuexiang', '15251329565', '2016-04-12 22:08:09');
INSERT INTO `foodinfo` VALUES ('1', '1', '小份黄焖鸡米饭（不辣）', 'huangmenji_bula.jpg', '17.00', '180', '45', null, '黄焖鸡米饭小份');
INSERT INTO `foodinfo` VALUES ('2', '1', '小份黄焖鸡米饭（微辣）', 'huangmenji_weila.jpg', '17.00', '22', '6', null, '黄焖鸡米饭小份');
INSERT INTO `foodinfo` VALUES ('3', '1', '大份黄焖鸡米饭（不辣）', 'huangmenji_bula.jpg', '22.00', '244', '23', null, '黄焖鸡米饭大份');
INSERT INTO `foodinfo` VALUES ('4', '1', '王老吉', 'wanglaoji.jpg', '4.50', '20', '4', null, '饮料');
INSERT INTO `foodinfo` VALUES ('5', '1', '雪碧（听）', 'xuebi.jpg', '3.50', '34', '43', null, '饮料');
INSERT INTO `foodinfo` VALUES ('6', '1', '大份黄焖鸡米饭（微辣）', 'huangmenji_weila.jpg', '22.00', '242', '23', null, '黄焖鸡米饭大份');
INSERT INTO `foodinfo` VALUES ('7', '1', '可乐', 'kele.jpg', '3.50', '233', '123', null, '饮料');
INSERT INTO `foodinfo` VALUES ('8', '1', '阿萨姆奶茶', 'ashamunaicha.jpg', '5.00', '234', '234', null, '饮料');
INSERT INTO `foodinfo` VALUES ('9', '2', '脆皮大鸡排原味', 'dajipai_yuanwei.jpg', '12.00', '55', '22', null, '豪大大鸡排');
INSERT INTO `foodinfo` VALUES ('10', '2', '香酥大鸡排原味', 'dajipai_yuanwei.jpg', '12.00', '78', '34', null, '豪大大鸡排');
INSERT INTO `foodinfo` VALUES ('11', '2', '脆皮大鸡排梅子味', 'dajipai_meizi.jpg', '12.00', '80', '34', null, '豪大大鸡排');
INSERT INTO `foodinfo` VALUES ('12', '2', '香酥大鸡排孜然味', 'dajipai_ziran.jpg', '12.00', '34', '21', null, '豪大大鸡排');
INSERT INTO `foodinfo` VALUES ('13', '2', '轰炸大鱿鱼', 'hongzhadayouyu.jpg', '16.00', '33', '67', null, '缤纷美食');
INSERT INTO `foodinfo` VALUES ('14', '2', '藤椒鸡腿', 'zhajitui.jpg', '8.00', '45', '22', null, '缤纷美食');
INSERT INTO `foodinfo` VALUES ('15', '2', '薯条', 'shutiao.jpg', '10.00', '23', '11', null, '缤纷美食');
INSERT INTO `foodinfo` VALUES ('16', '2', '黑椒鸡块', 'heijiaojikuai.jpg', '8.00', '4', '1', null, '缤纷美食');
INSERT INTO `foodinfo` VALUES ('17', '2', '香辣鸡腿汉堡', 'xianglajituihanbao.jpg', '11.00', '23', '14', null, '汉堡');
INSERT INTO `foodinfo` VALUES ('18', '2', '新奥尔良烤鸡腿汉堡', 'xinaoerliangkaojituihanbao.jpg', '15.00', '144', '68', null, '汉堡');
INSERT INTO `foodinfo` VALUES ('19', '2', '香草奶茶', 'xiangcaonaicha.jpg', '7.00', '6', '2', null, '奶茶');
INSERT INTO `foodinfo` VALUES ('20', '2', '红豆奶茶', 'hongdounaicha.jpg', '7.00', '22', '5', null, '奶茶');
INSERT INTO `foodinfo` VALUES ('21', '2', '香芋奶茶', 'xiangyunaicha.jpg', '7.00', '34', '23', null, '奶茶');
INSERT INTO `foodinfo` VALUES ('22', '2', '菠萝派', 'boluopai.jpg', '8.00', '12', '5', null, '派系列');
INSERT INTO `foodinfo` VALUES ('23', '2', '香芋派', 'xiangyupai.jpg', '8.00', '2', '0', null, '派系列');
INSERT INTO `foodinfo` VALUES ('24', '2', '红豆派', 'hongdoupai.jpg', '8.00', '32', '13', null, '派系列');
INSERT INTO `foodshop` VALUES ('1', '杨铭宇黄焖鸡（铁心桥）', 'huangmenji.jpg', '4.40', '1223', '13', '2', '34', 'deliciousfood', '南京', '09:30-13:30,15:30-19:30', '13584010177', '南京市雨花台区玉兰路99号1幢1183室', '美团专送');
INSERT INTO `foodshop` VALUES ('2', '豪大大鸡排', 'haodajipai.jpg', '3.90', '227', '28', '0', '30', 'deliciousfood', '南京', '08:45-22:30', '13813852081', '春江新城新河苑一期', '商家');
INSERT INTO `foodshop` VALUES ('3', '世纪华联超市', 'shijihualian.jpg', '4.40', '361', '118', '8', '19', 'supermarket', '南京', '07:00-23:45', '18551803733', '南京市雨花台区铁心桥街道春江佳园C23、C24号商业房', '商家');
INSERT INTO `foodshop` VALUES ('4', '金马水果店（新鲜特价）', 'jinmashuiguo.jpg', '4.30', '432', '20', '8', '43', 'freshfruits', '南京', '08:30-22:00', '13776695576', '铁心桥春江新城春江路54-13号', '商家');
INSERT INTO `helperpay` VALUES ('1', 'xuexiang', 'xuexiang', '0.00', '1234567890');
INSERT INTO `helperpay` VALUES ('2', 'xuexin', 'xuexin', '0.00', '1212121212');
INSERT INTO `pollingservice` VALUES ('1', 'a767022ceb47db3f', 'reportprint', '2016-02-19|2016-02-19');
INSERT INTO `pollingservice` VALUES ('2', 'a767022ceb47db3f', 'notification', '2016-05-13|2016-05-13');
INSERT INTO `pollingservice` VALUES ('3', 'cc914e3dc24fe89d', 'notification', '2016-05-03|2016-05-03');
INSERT INTO `promotion` VALUES ('1', '1', '新用户立减13元', '满25减8', null, '实际支付1元返4元商家代金券(在线支付专享)', null, '下单即赠送随机礼物', '满50元免运费(在线支付专享)');
INSERT INTO `promotion` VALUES ('2', '2', '新用户立减14元(在线支付专享)', '满21减4;满35减5;满50减8', '指定商品打8折', '', '下单多减1元,限09:00-11:00', null, '');
INSERT INTO `promotion` VALUES ('3', '3', '新用户立减15元', '', '折扣商品5折起(在线支付专享)', '实际支付38元返5元商家代金券', null, null, null);
INSERT INTO `promotion` VALUES ('4', '4', null, '满28减1;满58减2;满198减5(在线支付专享)', null, null, null, '满45元赠送水果拼盘一份', '满128元免配送费(在线支付专享)');
INSERT INTO `pushmessage` VALUES ('1', '我的轮询服务', '轮询服务文本推送', null, 'txt', '2016-02-19|2016-02-19');
INSERT INTO `pushmessage` VALUES ('2', '我的轮询服务', '轮询服务网页推送', 'http://www.baidu.com', 'web', '2016-02-19|2016-02-19');
INSERT INTO `pushmessage` VALUES ('3', '乐居轮询服务', '好消息！乐居外卖大放送啦！~~', null, 'txt', '2016-05-13|2016-05-13');
INSERT INTO `pushmessage` VALUES ('4', '消息推送', '轮询服务网页推送', 'http://www.baidu.com', 'web', '2016-05-13|2016-05-13');
INSERT INTO `qiangyu` VALUES ('39', 'xuexiang', '这个姑娘好美！', 'xuexiang152513295651457149998447.jpeg', '0', '03-05 11:53');
INSERT INTO `qiangyu` VALUES ('41', 'xuexiang', '今天天气不错！', '', '1', '03-05 11:54');
INSERT INTO `qiangyu` VALUES ('42', 'xuexiang', '鲍翠在写代码！', 'xuexiang152513295651457150066624.jpeg', '1', '03-05 11:54');
INSERT INTO `qiangyu` VALUES ('43', 'xuexiang', '今天加班写毕业设计', 'xuexiang152513295651457150111682.jpeg', '0', '03-05 11:55');
INSERT INTO `qiangyu` VALUES ('44', 'xuexin', '好骚啊，这是谁啊？', 'xuexin138138439131457150201256.jpeg', '0', '03-05 11:57');
INSERT INTO `qiangyu` VALUES ('45', 'xuexin', '今天下雨', '', '0', '03-05 11:57');
INSERT INTO `qiangyu` VALUES ('46', 'xuexin', '每天努力工作', 'xuexin138138439131457150296100.jpeg', '0', '03-05 11:58');
INSERT INTO `qiangyu` VALUES ('47', 'xuexin', '看看还有没有别人在', '', '0', '03-05 11:58');
INSERT INTO `qiangyu` VALUES ('48', 'xuexiang', '加油测试', '', '0', '03-05 11:59');
INSERT INTO `qiangyu` VALUES ('49', 'xuexiang', '继续测试', '', '0', '03-05 11:59');
INSERT INTO `qiangyu` VALUES ('50', 'xuexiang', '天呐！', '', '0', '03-05 12:00');
INSERT INTO `qiangyu` VALUES ('51', 'xuexiang', '就问帅不帅！', 'xuexiang152513295651457150437477.jpeg', '0', '03-05 12:01');
INSERT INTO `qiangyu` VALUES ('52', 'xuexiang', '今天晚上要加班了，55555', '', '0', '03-05 12:03');
INSERT INTO `qiangyu` VALUES ('53', 'xuexiang', '我来测试一下！', '', '0', '03-07 22:33');
INSERT INTO `qiangyu` VALUES ('54', 'xuexiang', '我来设计一下', '', '0', '03-07 22:36');
INSERT INTO `qiangyu` VALUES ('55', 'xuexiang', '测试', '', '0', '03-07 22:41');
INSERT INTO `qiangyu` VALUES ('56', 'xuexiang', '111111111', '', '0', '03-07 22:42');
INSERT INTO `qiangyu` VALUES ('57', 'xuexiang', '2222', '', '0', '03-07 22:45');
INSERT INTO `qiangyu` VALUES ('58', 'xuexiang', '我试试看效果如何！', '', '0', '03-09 13:24');
INSERT INTO `qiangyu` VALUES ('59', 'xuexiang', '性感女神', 'xuexiang152513295651457501108242.jpeg', '0', '03-09 13:25');
INSERT INTO `qiangyu` VALUES ('60', 'xuexiang', '今天心情不错~~', '', '0', '04-07 22:23');
INSERT INTO `qiangyu` VALUES ('61', 'xuexiang', '发个玩玩~~', 'xuexiang152513295651463058361474.jpeg', '0', '05-12 21:06');
INSERT INTO `repairinfo` VALUES ('1', 'xuexiang', '南京雨花台区', '专业维修', '修不好不要钱', null, '2016-3-30', '200.00', 'plumber', 'provider');
INSERT INTO `repairinfo` VALUES ('2', 'xuexiang', '韩府新苑2幢403室', '我家灯坏了', '急需维修，地点在雨花新村！', '', '03-31 09:53', '58.00', 'plumber', 'customer');
INSERT INTO `repairinfo` VALUES ('3', 'xuexiang', '韩府新苑2幢403室', '专业维修各类家电', '详情请拨打我的手机', 'xuexiang1459389665181.JPEG', '03-31 10:01', '111.00', 'plumber', 'provider');
INSERT INTO `repairinfo` VALUES ('4', 'xuexiang', '韩府新苑2幢403室', '家里需要重新刷一下墙', '怎么办呢？', '', '03-31 10:04', '800.00', 'painter', 'customer');
INSERT INTO `repairinfo` VALUES ('5', 'xuexiang', '韩府新苑2幢403室', '专业贴瓷砖一百年', '贴得不好不要钱！', 'xuexiang1449191349674.jpg', '03-31 10:13', '200.00', 'mason', 'provider');
INSERT INTO `repairinfo` VALUES ('6', 'xuexiang', '韩府新苑2幢403室', '我会通下水道', '有需要的找我', '', '03-31 10:48', '500.00', 'plumber', 'provider');
INSERT INTO `repairinfo` VALUES ('7', 'xuexiang', '北二环路湘潭大学区体育馆东100米盖天彩钢夹芯板厂', '我家客厅灯坏了！', '南京市雨花台区', '', '04-07 22:22', '100.00', 'plumber', 'customer');
INSERT INTO `repairinfo` VALUES ('8', 'xuexiang', '雨花新村东大门门卫处锦才五金建材经营部', '我家灯坏了', '短短的', '', '05-18 15:02', '58.00', 'plumber', 'customer');
INSERT INTO `userinfo` VALUES ('test', '15251329565', 'test', null, null, '男', null, null, null, null);
INSERT INTO `userinfo` VALUES ('tianbin', '13801591447', 'tianbin', 'headphototianbin13801591447.jpeg', '田斌', '男', '3345743', null, null, null);
INSERT INTO `userinfo` VALUES ('xue', '15251329565', 'xuexiang', null, null, '男', null, null, null, null);
INSERT INTO `userinfo` VALUES ('xuexiang', '15251329565', 'xuexiang', 'headphotoxuexiang15251329565.jpeg', '剑影杀', '男', '我是天才！', '薛翔', '330521199408063811', 'yes');
INSERT INTO `userinfo` VALUES ('xuexiangjys', '15251329565', 'xuexiang', null, null, '男', null, null, null, null);
INSERT INTO `userinfo` VALUES ('xuexin', '13813843913', 'xuexin', 'headphotoxuexin13813843913.jpeg', '小花花', '女', '', '薛欣', '330521198809193823', 'yes');
INSERT INTO `visitinfo` VALUES ('1', 'xuexiang', null, '专业家教', '好好学习天天向上！小学初中语数外家教！', '', '0', '03-08 22:16', '20.00', 'privateteacher');
INSERT INTO `visitinfo` VALUES ('4', 'xuexiang', null, '烦烦烦', '人多的', 'bki-20120930200911-403011807.jpg;m2w690hq92lt_large_bGik_2b0b00002ef71190.jpg;123.jpg;m2w690hq92lt_large_b9zq_5fbf0000b7a2125d.jpg', '0', '03-09 14:37', '54.00', 'privateteacher');
INSERT INTO `visitinfo` VALUES ('5', 'xuexiang', null, '美女服务', '美女服务', 'image_1456049128874.jpg;image_1443452758466.jpg;董璇写真化身足球宝贝诱人曲线(5)_1456190043764.jpg;image_1456048565578.jpg;151010161003.jpg', '0', '03-09 14:39', '998.00', 'massage');
INSERT INTO `visitinfo` VALUES ('6', 'xuexiang', '', '我是职业大厨', '专业提供各种家常小炒！', 'temp.jpg;-1705863838.jpg', '0', '03-17 23:17', '100.00', 'cook');
INSERT INTO `visitinfo` VALUES ('7', 'xuexiang', '北二环路湘潭大学区体育馆东100米盖天彩钢夹芯板厂', '测试', '测试', '', '0', '03-17 23:21', '11.00', 'laundry');
INSERT INTO `visitinfo` VALUES ('8', 'xuexiang', '', 'Ffd', 'Dddd', '', '0', '03-17 23:26', '555.00', 'privateteacher');
INSERT INTO `visitinfo` VALUES ('9', 'xuexiang', '雨花新村东大门门卫处锦才五金建材经营部', '测试一下', '测试', '', '0', '03-17 23:28', '12.00', 'cook');
INSERT INTO `visitinfo` VALUES ('10', 'xuexiang', '韩府新苑2幢403室', '专业一对一辅导', '小学、初中、高中。', 'xuexiang1458371647856.JPEG', '0', '03-19 15:15', '500.00', 'privateteacher');
INSERT INTO `visitinfo` VALUES ('11', 'xuexin', '雨花新村', '专业家教，成绩没有显著提升不收钱！', '价格随意，高兴就行！', 'xuexin1458371826594.JPEG;xuexin1458371921774.JPEG', '0', '03-19 15:48', '1000.00', 'privateteacher');
INSERT INTO `visitinfo` VALUES ('12', 'test', '雨花新村二村二十幢', '美女按摩,包玩包爽！', '不舒服不收钱', 'xuexin1458371826594.JPEG;xuexin1458371921774.JPEG', '0', '03-19 16:01', '200.00', 'massage');
INSERT INTO `visitinfo` VALUES ('13', 'xuexiang', '北二环路湘潭大学区体育馆东100米盖天彩钢夹芯板厂', '求包养', '管吃管喝就行', '', '0', '04-07 22:24', '150.00', 'massage');
INSERT INTO `visitinfo` VALUES ('14', 'xuexiang', '北二环路湘潭大学区体育馆东100米盖天彩钢夹芯板厂', '测试一下', '哈哈哈哈', '', '0', '04-12 00:12', '50.00', 'laundry');
INSERT INTO `visitinfo` VALUES ('15', 'test', '雨花新村二村二十幢', '专业宝马、奔驰保养', '有需要的及时联系！', 'image_1456049128874.jpg;image_1456048565578.jpg', '0', '05-07 15:04', '2000.00', 'keep_car');
