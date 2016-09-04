/*
MySQL Data Transfer
Source Host: localhost
Source Database: student
Target Host: localhost
Target Database: student
Date: 2016/8/15 0:46:45
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for academy
-- ----------------------------
CREATE TABLE `academy` (
  `dno` varchar(10) NOT NULL,
  `dname` varchar(40) NOT NULL,
  `dmphead` varchar(10) NOT NULL,
  PRIMARY KEY  (`dno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for course
-- ----------------------------
CREATE TABLE `course` (
  `cno` varchar(10) NOT NULL,
  `cname` varchar(40) NOT NULL,
  `credit` int(2) NOT NULL,
  `tno` varchar(10) NOT NULL,
  PRIMARY KEY  (`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pollingservice
-- ----------------------------
CREATE TABLE `pollingservice` (
  `id` int(11) NOT NULL auto_increment,
  `usercode` varchar(100) NOT NULL,
  `pushtype` varchar(20) NOT NULL,
  `pushtime` varchar(100) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for pushmessage
-- ----------------------------
CREATE TABLE `pushmessage` (
  `id` int(11) NOT NULL auto_increment,
  `title` varchar(50) default NULL,
  `message` varchar(255) default NULL,
  `createtime` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for sc
-- ----------------------------
CREATE TABLE `sc` (
  `sno` varchar(10) NOT NULL,
  `cno` varchar(10) NOT NULL,
  `grade` int(3) default NULL,
  PRIMARY KEY  (`sno`,`cno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for shop
-- ----------------------------
CREATE TABLE `shop` (
  `id` int(11) NOT NULL auto_increment,
  `name` varchar(255) NOT NULL,
  `price` double(10,0) default NULL,
  `sale_num` int(10) default NULL,
  `address` varchar(255) default NULL,
  `pic` varchar(255) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for student
-- ----------------------------
CREATE TABLE `student` (
  `sno` varchar(10) NOT NULL,
  `sname` varchar(20) NOT NULL,
  `ssex` varchar(10) NOT NULL,
  `sage` int(4) NOT NULL,
  `dno` varchar(20) NOT NULL,
  `contact` varchar(20) NOT NULL,
  `homeaddr` varchar(40) NOT NULL,
  PRIMARY KEY  (`sno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for teacher
-- ----------------------------
CREATE TABLE `teacher` (
  `tno` varchar(10) NOT NULL,
  `tname` varchar(20) NOT NULL,
  `tpro` varchar(10) NOT NULL,
  `dno` varchar(20) NOT NULL,
  PRIMARY KEY  (`tno`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for userinfo
-- ----------------------------
CREATE TABLE `userinfo` (
  `loginname` varchar(20) NOT NULL,
  `password` varchar(20) default NULL,
  `userkind` varchar(10) default NULL,
  PRIMARY KEY  (`loginname`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- View structure for all_information
-- ----------------------------
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `all_information` AS select `course`.`cno` AS `cno`,`course`.`cname` AS `cname`,`course`.`credit` AS `credit`,`sc`.`grade` AS `grade`,`student`.`sname` AS `sname`,`student`.`sno` AS `sno`,`teacher`.`tno` AS `tno`,`teacher`.`tname` AS `tname` from (((`course` join `sc`) join `student`) join `teacher`) where ((`course`.`cno` = `sc`.`cno`) and (`sc`.`sno` = `student`.`sno`) and (`course`.`tno` = `teacher`.`tno`));

-- ----------------------------
-- Records 
-- ----------------------------
INSERT INTO `academy` VALUES ('1', '计科院', '陈建平');
INSERT INTO `academy` VALUES ('2', '生科院', '谭湘陵');
INSERT INTO `academy` VALUES ('3', '电气工程学院', '顾菊平');
INSERT INTO `academy` VALUES ('4', '商学院', '蒋乃华');
INSERT INTO `course` VALUES ('1', '数据结构', '4', '1');
INSERT INTO `course` VALUES ('2', '操作系统', '4', '2');
INSERT INTO `course` VALUES ('3', '综合布线工程', '3', '4');
INSERT INTO `course` VALUES ('4', '计算机网络安全', '3', '3');
INSERT INTO `course` VALUES ('5', 'javaweb', '3', '5');
INSERT INTO `course` VALUES ('6', '操作系统课程设计', '2', '2');
INSERT INTO `course` VALUES ('7', 'C++', '6', '7');
INSERT INTO `course` VALUES ('8', '计算机网络', '4', '9');
INSERT INTO `sc` VALUES ('1213072022', '1', '83');
INSERT INTO `sc` VALUES ('1213072022', '2', '89');
INSERT INTO `sc` VALUES ('1213072022', '3', '80');
INSERT INTO `sc` VALUES ('1213072022', '4', '84');
INSERT INTO `sc` VALUES ('1213072022', '5', '87');
INSERT INTO `sc` VALUES ('1213072022', '6', '90');
INSERT INTO `sc` VALUES ('1213072022', '7', '98');
INSERT INTO `sc` VALUES ('1213072022', '8', '79');
INSERT INTO `sc` VALUES ('1213072023', '1', '92');
INSERT INTO `sc` VALUES ('1213072024', '1', '70');
INSERT INTO `sc` VALUES ('1213072024', '2', '60');
INSERT INTO `sc` VALUES ('1213072025', '1', '80');
INSERT INTO `sc` VALUES ('1213072026', '1', '78');
INSERT INTO `shop` VALUES ('1', '连衣裙', '140', '2', '江苏南京', '1.png');
INSERT INTO `shop` VALUES ('2', '羊毛衫', '150', '3', '江苏常州', '2.png');
INSERT INTO `shop` VALUES ('3', 'xx', '213', '23', '南京', '1.png');
INSERT INTO `shop` VALUES ('4', '羊毛衫', '121', '324', '南京', '2.png');
INSERT INTO `shop` VALUES ('5', '羊毛衫', '324', '324', '南京', '1.png');
INSERT INTO `shop` VALUES ('6', '羊毛衫', '445', '2342', '江苏常州', '2.png');
INSERT INTO `student` VALUES ('1213072022', '薛翔', '男', '21', '计科院', '15251329565', '南京市雨花区');
INSERT INTO `student` VALUES ('1213072023', '沈维捷', '男', '21', '计科院', '18251301326', '南京市');
INSERT INTO `student` VALUES ('1213072024', '戴金琦', '男', '21', '计科院', '18251300293', '无锡市');
INSERT INTO `student` VALUES ('1213072025', '吴良', '男', '21', '计科院', '15162760890', '常州市');
INSERT INTO `student` VALUES ('1213072026', '钱效飞', '男', '21', '计科院', '18251300569', '苏州市');
INSERT INTO `teacher` VALUES ('1', '丁卫平', '讲师', '计科院');
INSERT INTO `teacher` VALUES ('2', '丁红', '讲师', '计科院');
INSERT INTO `teacher` VALUES ('3', '张晓峰', '副教授', '计科院');
INSERT INTO `teacher` VALUES ('4', '王则林', '副教授', '计科院');
INSERT INTO `teacher` VALUES ('5', '王春明', '副教授', '计科院');
INSERT INTO `teacher` VALUES ('6', '陈亮', '副教授', '计科院');
INSERT INTO `teacher` VALUES ('7', '潘建生', '讲师', '计科院');
INSERT INTO `teacher` VALUES ('9', '曹利', '教授', '计科院');
INSERT INTO `userinfo` VALUES ('12345', '12345', 'admin');
INSERT INTO `userinfo` VALUES ('admin', 'admin', 'admin');
INSERT INTO `userinfo` VALUES ('administration', 'admin', 'admin');
INSERT INTO `userinfo` VALUES ('administrator', '123', 'admin');
INSERT INTO `userinfo` VALUES ('test', 'test', 'admin');
INSERT INTO `userinfo` VALUES ('xue123', 'xue123', 'admin');
INSERT INTO `userinfo` VALUES ('xuexi', 'xuexi', 'student');
INSERT INTO `userinfo` VALUES ('xuexiang', 'xuexiang', 'admin');
INSERT INTO `userinfo` VALUES ('xuexin', 'xuexin', 'student');
INSERT INTO `userinfo` VALUES ('丁卫平', '123', 'teacher');
INSERT INTO `userinfo` VALUES ('丁红', '123', 'teacher');
INSERT INTO `userinfo` VALUES ('于慧敏', '123', 'student');
INSERT INTO `userinfo` VALUES ('何晓明', '123', 'student');
INSERT INTO `userinfo` VALUES ('余飞燕', '123', 'student');
INSERT INTO `userinfo` VALUES ('刘学生', '123', 'student');
INSERT INTO `userinfo` VALUES ('刘志朋', '123', 'student');
INSERT INTO `userinfo` VALUES ('刘鹏', '123', 'student');
INSERT INTO `userinfo` VALUES ('吴磬', '123', 'student');
INSERT INTO `userinfo` VALUES ('唐加培', '123', 'student');
INSERT INTO `userinfo` VALUES ('姚晖', '123', 'student');
INSERT INTO `userinfo` VALUES ('姚芳玲', '123', 'student');
INSERT INTO `userinfo` VALUES ('孙园园', '123', 'student');
INSERT INTO `userinfo` VALUES ('孙飞', '123', 'student');
INSERT INTO `userinfo` VALUES ('尤真晨', '123', 'student');
INSERT INTO `userinfo` VALUES ('张晓峰', '123', 'teacher');
INSERT INTO `userinfo` VALUES ('徐瑞东', '123', 'student');
INSERT INTO `userinfo` VALUES ('徐雅楠', '123', 'student');
INSERT INTO `userinfo` VALUES ('戴金琦', '123', 'student');
INSERT INTO `userinfo` VALUES ('施雅娟', '123', 'student');
INSERT INTO `userinfo` VALUES ('曹德晶', '123', 'student');
INSERT INTO `userinfo` VALUES ('曹读胜', '123', 'student');
INSERT INTO `userinfo` VALUES ('曹陈奇', '123', 'student');
INSERT INTO `userinfo` VALUES ('杜佳勇', '123', 'student');
INSERT INTO `userinfo` VALUES ('杨海校', '123', 'student');
INSERT INTO `userinfo` VALUES ('杨铜', '123', 'student');
INSERT INTO `userinfo` VALUES ('梁少兵', '123', 'student');
INSERT INTO `userinfo` VALUES ('沈维捷', '123', 'student');
INSERT INTO `userinfo` VALUES ('满远航', '123', 'student');
INSERT INTO `userinfo` VALUES ('王则林', '123', 'teacher');
INSERT INTO `userinfo` VALUES ('王春明', '123', 'teacher');
INSERT INTO `userinfo` VALUES ('王晨霞', '123', 'student');
INSERT INTO `userinfo` VALUES ('王蕊', '123', 'student');
INSERT INTO `userinfo` VALUES ('王赟', '123', 'student');
INSERT INTO `userinfo` VALUES ('王辉', '123', 'student');
INSERT INTO `userinfo` VALUES ('管理员', '123', 'admin');
INSERT INTO `userinfo` VALUES ('范聪聪', '123', 'student');
INSERT INTO `userinfo` VALUES ('蔡鹏程', '123', 'student');
INSERT INTO `userinfo` VALUES ('薛翔', 'xuexiang', 'student');
INSERT INTO `userinfo` VALUES ('郝力', '123', 'student');
INSERT INTO `userinfo` VALUES ('钟益新', '123', 'student');
INSERT INTO `userinfo` VALUES ('陆楠', '123', 'student');
INSERT INTO `userinfo` VALUES ('陆贝贝', '123', 'student');
INSERT INTO `userinfo` VALUES ('陈能静', '123', 'student');
INSERT INTO `userinfo` VALUES ('陈谊', '123', 'student');
INSERT INTO `userinfo` VALUES ('龚超凡', '123', 'student');
