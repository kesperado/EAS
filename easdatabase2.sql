/*
Navicat MySQL Data Transfer

Source Server         : knox
Source Server Version : 50534
Source Host           : localhost:3306
Source Database       : easdatabase2

Target Server Type    : MYSQL
Target Server Version : 50534
File Encoding         : 65001

Date: 2015-06-17 11:14:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `course`
-- ----------------------------
DROP TABLE IF EXISTS `course`;
CREATE TABLE `course` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `credit` int(11) NOT NULL,
  `time` varchar(20) DEFAULT NULL,
  `classroom` varchar(20) DEFAULT NULL,
  `memberlimit` int(11) DEFAULT NULL,
  `outline` text,
  `textbook` varchar(50) DEFAULT NULL,
  `referencebook` varchar(50) DEFAULT NULL,
  `testtime` varchar(20) DEFAULT NULL,
  `grade` varchar(20) NOT NULL,
  `targetdepartment` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `coursetype` varchar(20) NOT NULL,
  `canselect` varchar(20) DEFAULT 'No',
  `canquit` varchar(20) DEFAULT 'No',
  PRIMARY KEY (`id`),
  KEY `department_name_fk3` (`targetdepartment`),
  KEY `department_name_fk4` (`department`),
  CONSTRAINT `department_name_fk3` FOREIGN KEY (`targetdepartment`) REFERENCES `department` (`name`) ON UPDATE CASCADE,
  CONSTRAINT `department_name_fk4` FOREIGN KEY (`department`) REFERENCES `department` (`name`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of course
-- ----------------------------
INSERT INTO `course` VALUES ('100001', '软件工程与计算二', '4', '星期一1-2节', '仙2 308', '200', '大纲', 'C++', 'java', '', '本科二年级', '软件学院', '软件学院', '专业指选课', 'No', 'No');
INSERT INTO `course` VALUES ('100003', '编程入门', '2', '星期二1-2节', '仙1 212', '100', null, null, null, null, '本科一年级   ', '无', '软件学院', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100004', '编程进阶', '2', '星期二3-4', '仙2 315', '100', null, null, null, null, '本科二年级   ', '无', '软件学院', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100005', '羽毛球', '2', '星期一5-6节', '羽毛球馆', '30', null, null, null, null, '无', '无', '软件学院', '体育课', 'No', 'No');
INSERT INTO `course` VALUES ('100006', '篮球', '1', '星期一5-6节', '篮球场', '30', null, null, null, null, '无', '无', '软件学院', '体育课', 'No', 'No');
INSERT INTO `course` VALUES ('100007', '足球', '1', '星期一5-6节', '足球场', '30', null, null, null, null, '无', '无', '软件学院', '体育课', 'No', 'No');
INSERT INTO `course` VALUES ('100008', 'WEB趣谈', '2', '星期一7-8节', '仙一201', '30', null, null, null, null, '无', '无', '软件学院', '通识课', 'No', 'No');
INSERT INTO `course` VALUES ('100009', '软件的未来走向', '2', '星期二7-8节', '仙一203', '30', null, null, null, null, '无', '无', '软件学院', '研讨课', 'No', 'No');
INSERT INTO `course` VALUES ('100010', '微积分', '2', '星期四1-2节', '仙二304', '100', null, null, null, null, '本科一年级   ', '软件学院', '数学系', '公共必修课', 'No', 'No');
INSERT INTO `course` VALUES ('100011', '线性代数', '2', '星期四5-6节', '仙二304', '100', null, null, null, null, '本科二年级   ', '软件学院', '数学系', '公共必修课', 'No', 'No');
INSERT INTO `course` VALUES ('100012', '软件开发基础', '2', '星期二3-4节', '逸夫楼A101', '30', null, null, null, null, '无', '无', '软件学院', '通识课', 'No', 'No');
INSERT INTO `course` VALUES ('100013', '软件开发进阶', '2', '星期二3-4节', '逸夫楼A102', '30', null, null, null, null, '无', '无', '软件学院', '通识课', 'No', 'No');
INSERT INTO `course` VALUES ('100014', '为什么需要软件', '1', '星期三3-4节', '逸夫楼A103', '30', null, null, null, null, '无', '无', '软件学院', '研讨课', 'No', 'No');
INSERT INTO `course` VALUES ('100015', '如何正确使用软件', '2', '星期三3-4节', '逸夫楼A104', '30', null, null, null, null, '无', '无', '软件学院', '研讨课', 'No', 'No');
INSERT INTO `course` VALUES ('100016', '宏观经济学', '2', '星期一3-4节', '仙二303', '100', null, null, null, null, '本科二年级   ', '无', '商学院', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100017', '微观经济学', '2', '星期二3-4节', '仙二304', '30', null, null, null, null, '本科二年级   ', '无', '商学院', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100018', '中文浅谈', '2', '星期四3-4节', '文院楼101', '50', '', '', '', '', '本科二年级   ', '无', '文学院', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100019', '西文演变', '2', '星期四5-6节', '文院楼102', '50', null, null, null, null, '本科二年级   ', '无', '文学院', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100020', 'test1', '2', '星期五1-2节', 'loc1', '50', '', '', '', '', '本科二年级   ', '无', '物理系', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100021', 'test2', '2', '星期五3-4节', 'loc2', '50', null, null, null, null, '本科二年级   ', '无', '物理系', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100022', 'java ee', '3', '星期一1-2节', '教102', '120', null, null, null, null, '无', '软件学院', '软件学院', '专业选修课', 'No', 'No');
INSERT INTO `course` VALUES ('100023', '管理信息系统', '3', '星期一3-4节', '教202', '120', null, null, null, null, '本科三年级', '软件学院', '软件学院', '专业指选课', 'No', 'No');
INSERT INTO `course` VALUES ('100024', '高级数据库', '3', '星期二1-2节', '教201', '120', null, null, null, null, '本科三年级', '软件学院', '软件学院', '专业指选课', 'No', 'No');
INSERT INTO `course` VALUES ('100025', '人机交互', '3', '星期三1-2节', '教102', '200', null, null, null, null, '本科三年级', '软件学院', '软件学院', '专业指选课', 'No', 'No');

-- ----------------------------
-- Table structure for `courseassess`
-- ----------------------------
DROP TABLE IF EXISTS `courseassess`;
CREATE TABLE `courseassess` (
  `courseid` varchar(20) NOT NULL,
  `score` int(10) NOT NULL,
  KEY `course_assess` (`courseid`),
  CONSTRAINT `course_assess` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of courseassess
-- ----------------------------
INSERT INTO `courseassess` VALUES ('100001', '4');
INSERT INTO `courseassess` VALUES ('100001', '5');
INSERT INTO `courseassess` VALUES ('100001', '3');
INSERT INTO `courseassess` VALUES ('100001', '4');
INSERT INTO `courseassess` VALUES ('100001', '3');
INSERT INTO `courseassess` VALUES ('100009', '3');
INSERT INTO `courseassess` VALUES ('100005', '3');
INSERT INTO `courseassess` VALUES ('100005', '4');
INSERT INTO `courseassess` VALUES ('100006', '3');
INSERT INTO `courseassess` VALUES ('100006', '4');
INSERT INTO `courseassess` VALUES ('100006', '4');
INSERT INTO `courseassess` VALUES ('100007', '4');
INSERT INTO `courseassess` VALUES ('100007', '4');
INSERT INTO `courseassess` VALUES ('100008', '5');
INSERT INTO `courseassess` VALUES ('100009', '5');
INSERT INTO `courseassess` VALUES ('100009', '3');
INSERT INTO `courseassess` VALUES ('100013', '2');
INSERT INTO `courseassess` VALUES ('100014', '5');
INSERT INTO `courseassess` VALUES ('100015', '4');
INSERT INTO `courseassess` VALUES ('100022', '3');
INSERT INTO `courseassess` VALUES ('100022', '4');

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `name` varchar(20) NOT NULL,
  `plan` text,
  `inadmit` int(11) DEFAULT NULL,
  `outadmit` int(11) DEFAULT NULL,
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('体育部', null, null, null);
INSERT INTO `department` VALUES ('化学系', null, null, null);
INSERT INTO `department` VALUES ('商学院', '======商学院教学计划===========', '20', '30');
INSERT INTO `department` VALUES ('天文系', null, null, null);
INSERT INTO `department` VALUES ('数学系', null, null, null);
INSERT INTO `department` VALUES ('文学院', '======文学院教学计划=========', '20', '40');
INSERT INTO `department` VALUES ('无', null, null, null);
INSERT INTO `department` VALUES ('物理系', '=================', '20', '40');
INSERT INTO `department` VALUES ('现代工程学院', null, null, null);
INSERT INTO `department` VALUES ('软件学院', '============软件学院教学计划============\n软件产业作为信息产业的核心是国民经济信息化的基础，已经涉足工业、农业、商业、金融、科教文卫、国防和百姓生活等各个\n领域。采用先进的工程化方法进行软件开发和生产是实现软件产业化的关键技术手段。因此，为积极促进我国软件产业发展，加\n速我国信息化建设，增强其国际竞争力，急需培养大批软件工程领域的实用型、复合型软件工程技术人才和软件工程管理人才。\n为促进南京大学软件工程专业本科生在入学、培养、毕业和学位授予等环节的规范化，确保培养质量，根据教育部有关要求，依\n据南京大学有关本科生培养的规定，特制定本方案。\n本方案作为南京大学培养软件工程专业本科生的指导性文件，规定其培养目标、方向和要求，以及培养对象、方式及学习年限，\n并就其课程设置、课程修读和学位论文要求等给出指导性意见。\n', '20', '40');

-- ----------------------------
-- Table structure for `preselectionrecord`
-- ----------------------------
DROP TABLE IF EXISTS `preselectionrecord`;
CREATE TABLE `preselectionrecord` (
  `courseid` varchar(20) NOT NULL,
  `studentid` varchar(20) NOT NULL,
  `rank` varchar(20) NOT NULL,
  `memberlimit` int(11) NOT NULL,
  KEY `course_id_fk2` (`courseid`),
  KEY `student_id_fk2` (`studentid`),
  CONSTRAINT `course_id_fk2` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_id_fk2` FOREIGN KEY (`studentid`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of preselectionrecord
-- ----------------------------

-- ----------------------------
-- Table structure for `selectionrecord`
-- ----------------------------
DROP TABLE IF EXISTS `selectionrecord`;
CREATE TABLE `selectionrecord` (
  `courseid` varchar(20) NOT NULL,
  `studentid` varchar(20) NOT NULL,
  `score` int(11) DEFAULT NULL,
  KEY `course_id_fk1` (`courseid`),
  KEY `student_id_fk1` (`studentid`),
  CONSTRAINT `course_id_fk1` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_id_fk1` FOREIGN KEY (`studentid`) REFERENCES `student` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of selectionrecord
-- ----------------------------
INSERT INTO `selectionrecord` VALUES ('100001', '120001', '67');
INSERT INTO `selectionrecord` VALUES ('100001', '120002', '77');
INSERT INTO `selectionrecord` VALUES ('100001', '120003', '87');
INSERT INTO `selectionrecord` VALUES ('100001', '120004', '76');
INSERT INTO `selectionrecord` VALUES ('100010', '120005', '0');
INSERT INTO `selectionrecord` VALUES ('100010', '120006', '0');
INSERT INTO `selectionrecord` VALUES ('100010', '120007', '0');
INSERT INTO `selectionrecord` VALUES ('100011', '120001', '88');
INSERT INTO `selectionrecord` VALUES ('100011', '120002', '66');
INSERT INTO `selectionrecord` VALUES ('100011', '120003', '0');
INSERT INTO `selectionrecord` VALUES ('100011', '120004', '67');
INSERT INTO `selectionrecord` VALUES ('100005', '120001', '88');
INSERT INTO `selectionrecord` VALUES ('100018', '120001', '99');
INSERT INTO `selectionrecord` VALUES ('100008', '120001', '90');
INSERT INTO `selectionrecord` VALUES ('100013', '120001', '89');
INSERT INTO `selectionrecord` VALUES ('100009', '120001', '78');
INSERT INTO `selectionrecord` VALUES ('100008', '120004', '88');
INSERT INTO `selectionrecord` VALUES ('100005', '120008', '0');
INSERT INTO `selectionrecord` VALUES ('100005', '120009', '77');
INSERT INTO `selectionrecord` VALUES ('100001', '120008', '70');
INSERT INTO `selectionrecord` VALUES ('100001', '120009', '90');
INSERT INTO `selectionrecord` VALUES ('100001', '120005', '0');
INSERT INTO `selectionrecord` VALUES ('100001', '120006', '81');
INSERT INTO `selectionrecord` VALUES ('100004', '120001', '77');
INSERT INTO `selectionrecord` VALUES ('100004', '120003', '57');
INSERT INTO `selectionrecord` VALUES ('100006', '120002', '67');
INSERT INTO `selectionrecord` VALUES ('100006', '120003', '77');
INSERT INTO `selectionrecord` VALUES ('100006', '120004', '87');
INSERT INTO `selectionrecord` VALUES ('100006', '120005', '57');
INSERT INTO `selectionrecord` VALUES ('100007', '120002', '87');
INSERT INTO `selectionrecord` VALUES ('100007', '120003', '97');
INSERT INTO `selectionrecord` VALUES ('100012', '120005', '77');
INSERT INTO `selectionrecord` VALUES ('100012', '120008', '57');
INSERT INTO `selectionrecord` VALUES ('100014', '120003', '77');
INSERT INTO `selectionrecord` VALUES ('100014', '120005', '87');
INSERT INTO `selectionrecord` VALUES ('100014', '120009', '99');
INSERT INTO `selectionrecord` VALUES ('100014', '120008', '59');
INSERT INTO `selectionrecord` VALUES ('100015', '120007', '55');
INSERT INTO `selectionrecord` VALUES ('100022', '120002', '88');
INSERT INTO `selectionrecord` VALUES ('100015', '120002', '77');
INSERT INTO `selectionrecord` VALUES ('100015', '120008', '69');
INSERT INTO `selectionrecord` VALUES ('100015', '120009', '62');
INSERT INTO `selectionrecord` VALUES ('100015', '120003', '87');
INSERT INTO `selectionrecord` VALUES ('100015', '120004', '98');

-- ----------------------------
-- Table structure for `student`
-- ----------------------------
DROP TABLE IF EXISTS `student`;
CREATE TABLE `student` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `grade` varchar(20) NOT NULL,
  `department` varchar(20) NOT NULL,
  `age` varchar(20) DEFAULT NULL,
  `hometown` varchar(20) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `department_name_fk2` (`department`),
  CONSTRAINT `department_name_fk2` FOREIGN KEY (`department`) REFERENCES `department` (`name`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of student
-- ----------------------------
INSERT INTO `student` VALUES ('120001', '李坚', '120001', '本科二年级', '软件学院', '20', '宁波', '13000000');
INSERT INTO `student` VALUES ('120002', '李豪俊', '120002', '本科二年级', '软件学院', '20', '南通', '南通');
INSERT INTO `student` VALUES ('120003', '任羡纲', '120003', '本科二年级', '软件学院', null, null, null);
INSERT INTO `student` VALUES ('120004', '王萍', '120004', '本科二年级', '软件学院', null, null, null);
INSERT INTO `student` VALUES ('120005', 'a0001', '120005', '本科一年级   ', '数学系', null, null, null);
INSERT INTO `student` VALUES ('120006', 'a0002', '120006', '本科一年级   ', '数学系', null, null, null);
INSERT INTO `student` VALUES ('120007', 'a0003', '120007', '本科一年级   ', '数学系', null, null, null);
INSERT INTO `student` VALUES ('120008', 'b0001', '120008', '本科三年级', '文学院', null, null, null);
INSERT INTO `student` VALUES ('120009', 'c0001', '120009', '本科二年级', '商学院', null, null, null);

-- ----------------------------
-- Table structure for `systeminfo`
-- ----------------------------
DROP TABLE IF EXISTS `systeminfo`;
CREATE TABLE `systeminfo` (
  `item` varchar(20) DEFAULT NULL,
  `content` text
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systeminfo
-- ----------------------------
INSERT INTO `systeminfo` VALUES ('structure', '整体框架V1.0');
INSERT INTO `systeminfo` VALUES ('status', '2');

-- ----------------------------
-- Table structure for `systemnotice`
-- ----------------------------
DROP TABLE IF EXISTS `systemnotice`;
CREATE TABLE `systemnotice` (
  `userid` varchar(20) DEFAULT NULL,
  `notice` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of systemnotice
-- ----------------------------
INSERT INTO `systemnotice` VALUES ('1000', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1001', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1002', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1003', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1004', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1005', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1006', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1007', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1008', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1009', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1010', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1011', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1012', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1013', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1014', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('120001', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('120002', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('120003', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('120004', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('120005', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('120006', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('120007', '选课结束，如若对课程还有意见请咨询教务处老师');
INSERT INTO `systemnotice` VALUES ('1000', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1001', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1002', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1003', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1004', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1005', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1006', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1007', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1008', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1009', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1010', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1011', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1012', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1013', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('1014', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120001', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120002', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120003', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120004', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120005', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120006', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120007', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120008', '即将开始新的学期');
INSERT INTO `systemnotice` VALUES ('120009', '即将开始新的学期');

-- ----------------------------
-- Table structure for `teachcourse`
-- ----------------------------
DROP TABLE IF EXISTS `teachcourse`;
CREATE TABLE `teachcourse` (
  `courseid` varchar(20) NOT NULL,
  `teacherid` varchar(20) NOT NULL,
  KEY `course_id_fk3` (`courseid`),
  KEY `teacher_id_fk` (`teacherid`),
  CONSTRAINT `course_id_fk3` FOREIGN KEY (`courseid`) REFERENCES `course` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `teacher_id_fk` FOREIGN KEY (`teacherid`) REFERENCES `teacher` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teachcourse
-- ----------------------------
INSERT INTO `teachcourse` VALUES ('100003', '1002');
INSERT INTO `teachcourse` VALUES ('100001', '1002');
INSERT INTO `teachcourse` VALUES ('100001', '1003');
INSERT INTO `teachcourse` VALUES ('100004', '1004');
INSERT INTO `teachcourse` VALUES ('100008', '1002');
INSERT INTO `teachcourse` VALUES ('100009', '1004');
INSERT INTO `teachcourse` VALUES ('100012', '1003');
INSERT INTO `teachcourse` VALUES ('100013', '1002');
INSERT INTO `teachcourse` VALUES ('100014', '1003');
INSERT INTO `teachcourse` VALUES ('100015', '1004');
INSERT INTO `teachcourse` VALUES ('100010', '1006');
INSERT INTO `teachcourse` VALUES ('100011', '1006');
INSERT INTO `teachcourse` VALUES ('100016', '1010');
INSERT INTO `teachcourse` VALUES ('100017', '1010');
INSERT INTO `teachcourse` VALUES ('100018', '1012');
INSERT INTO `teachcourse` VALUES ('100019', '1012');
INSERT INTO `teachcourse` VALUES ('100020', '1014');
INSERT INTO `teachcourse` VALUES ('100021', '1014');
INSERT INTO `teachcourse` VALUES ('100004', '1003');

-- ----------------------------
-- Table structure for `teacher`
-- ----------------------------
DROP TABLE IF EXISTS `teacher`;
CREATE TABLE `teacher` (
  `id` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `teachertype` varchar(20) NOT NULL,
  `department` varchar(20) DEFAULT NULL,
  `age` varchar(20) DEFAULT NULL,
  `hometown` varchar(20) DEFAULT NULL,
  `contact` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `department_name_fk1` (`department`),
  CONSTRAINT `department_name_fk1` FOREIGN KEY (`department`) REFERENCES `department` (`name`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of teacher
-- ----------------------------
INSERT INTO `teacher` VALUES ('1000', '陈骏', '1234', '教务处老师', '无', '20', '南京', '1300000');
INSERT INTO `teacher` VALUES ('1001', '王东霞', '1001', '院系教务老师', '软件学院', '30', 'JiangSu', '');
INSERT INTO `teacher` VALUES ('1002', '刘钦', '1002', '任课老师', '软件学院', '30', '', '');
INSERT INTO `teacher` VALUES ('1003', '丁二玉', '1003', '任课老师', '软件学院', null, null, null);
INSERT INTO `teacher` VALUES ('1004', '邵栋', '1004', '任课老师', '软件学院', null, null, null);
INSERT INTO `teacher` VALUES ('1005', '数院教', '1005', '院系教务老师', '数学系', null, null, null);
INSERT INTO `teacher` VALUES ('1006', '邵宏', '1006', '任课老师', '数学系', null, null, null);
INSERT INTO `teacher` VALUES ('1007', '体教1', '1007', '任课老师', '体育部', null, null, null);
INSERT INTO `teacher` VALUES ('1008', '体教2', '1008', '任课老师', '体育部', null, null, null);
INSERT INTO `teacher` VALUES ('1009', '商院教', '1009', '院系教务老师', '商学院', null, null, null);
INSERT INTO `teacher` VALUES ('1010', '商任课', '1010', '任课老师', '商学院', null, null, null);
INSERT INTO `teacher` VALUES ('1011', '文教务', '1011', '院系教务老师', '文学院', null, null, null);
INSERT INTO `teacher` VALUES ('1012', '文任课', '1012', '任课老师', '文学院', null, null, null);
INSERT INTO `teacher` VALUES ('1013', '物理教务', '1013', '院系教务老师', '物理系', null, null, null);
INSERT INTO `teacher` VALUES ('1014', '物理任课', '1014', '任课老师', '物理系', null, null, null);
