/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 5.7.13-log : Database - shop
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`shop` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `shop`;

/*Table structure for table `t_about` */

DROP TABLE IF EXISTS `t_about`;

CREATE TABLE `t_about` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `content` text COMMENT '内容',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_about` */

insert  into `t_about`(`id`,`content`) values (1,'<p align=\"center\"><span style=\"color:#00b050\"><span style=\"font-size:18px\"><em><strong></strong></em></span></span></p><pre id=\"best-content-1517305107\" accuse=\"aContent\" class=\"best-text mb-10\" style=\"margin-top: 10px; padding: 0px; font-family: &#39;PingFang SC&#39;, &#39;Lantinghei SC&#39;, &#39;Microsoft YaHei&#39;, arial, 宋体, sans-serif, tahoma; white-space: pre-wrap; font-size: 16px; line-height: 29px; min-height: 55px; background-color: rgb(255, 255, 255);\">新春超市连续5年跻身中国民营企业500强前50位，自1986年创业以来，始终以建设温馨、和谐的家园，提升消费者的居家生活品味为己任。截至目前，已在北京、上海、天津、南京、长沙、重庆、成都、南昌、合肥、石家庄、沈阳、包头等80个城市开办了100家商场，市场总规模达550万平方米。2008年销售总额突破235亿元，成为中国家居业的第一品牌。2007年荣获“国内影响力品牌领<br style=\"content: &quot;&quot;; display: block; width: 700px; height: 0px; margin-top: 20px; margin-bottom: 20px;\"/>袖大奖”、“家居家装行业影响力品牌领袖大奖”。</pre>');

/*Table structure for table `t_add` */

DROP TABLE IF EXISTS `t_add`;

CREATE TABLE `t_add` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `time` int(11) DEFAULT NULL COMMENT '用户访问次数',
  `money` double DEFAULT NULL COMMENT '总成交额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

/*Data for the table `t_add` */

insert  into `t_add`(`id`,`time`,`money`) values (1,380,3180);

/*Table structure for table `t_buy` */

DROP TABLE IF EXISTS `t_buy`;

CREATE TABLE `t_buy` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '下单用户id',
  `state` int(11) DEFAULT NULL COMMENT '订单状态 0:取消订单 1：订单正常 2：订单完成',
  `time` datetime DEFAULT NULL COMMENT '下单时间',
  `money` double DEFAULT NULL COMMENT '下单金额',
  `note` varchar(200) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `t_buy` */

insert  into `t_buy`(`id`,`user_id`,`state`,`time`,`money`,`note`) values (1,1,2,'2016-09-10 00:00:00',555,NULL),(2,1,2,'2016-09-12 20:02:43',777,NULL),(3,1,1,'2016-09-12 21:53:12',123,NULL),(4,1,1,'2016-09-12 21:54:38',555,NULL),(5,1,2,'2016-09-12 22:24:05',123,NULL),(6,1,0,'2016-09-13 17:20:50',1047,NULL),(7,1,1,'2017-01-18 21:01:47',0,NULL),(8,1,1,'2017-01-18 21:04:16',0,NULL),(9,1,1,'2017-01-18 21:12:40',222,NULL),(10,1,1,'2017-01-18 21:14:56',555,NULL),(11,1,1,'2017-01-18 21:15:46',191,NULL),(12,1,1,'2017-01-18 21:16:21',555,NULL),(13,1,1,'2017-01-18 21:17:10',555,NULL),(14,1,1,'2017-05-02 09:19:05',39.6,NULL),(15,1,1,'2017-05-02 09:19:13',27.9,NULL);

/*Table structure for table `t_card` */

DROP TABLE IF EXISTS `t_card`;

CREATE TABLE `t_card` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) DEFAULT NULL COMMENT '用户id',
  `goods_id` int(10) DEFAULT NULL COMMENT '商品id',
  `goods_name` varchar(50) DEFAULT NULL COMMENT '商品名',
  `goods_price` double DEFAULT NULL COMMENT '商品价格',
  `cardNumber` int(11) DEFAULT NULL COMMENT '加入购物车数量',
  `state` int(1) DEFAULT NULL COMMENT '订单状态',
  `goods_url` varchar(100) DEFAULT NULL COMMENT '图片地址',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

/*Data for the table `t_card` */

insert  into `t_card`(`id`,`user_id`,`goods_id`,`goods_name`,`goods_price`,`cardNumber`,`state`,`goods_url`) values (4,1,14,'美仑达 四川蒲江青见柑橘 5斤装 自营水果',37.9,1,1,'../file/goods/a369a5b703eb460ea5d94d95a20a60b4.jpg'),(5,1,15,'佳农 越南进口红心火龙果 3个装 中果 总重约1.1kg 新鲜时令多汁味美',29.8,1,1,'../file/goods/49c27ed722eb494aa14adb97df1e4522.jpg'),(6,1,11,'爱奇果 陕西眉县 猕猴桃 12粒装 单果90-110g',27.9,1,1,'../file/goods/679a4811982c41ccac08b069c5723ebf.jpg'),(7,1,17,'宏辉果蔬 苹果 烟台红富士 12枚75mm 单果约170-190g 总重4.2斤',23.9,1,1,'../file/goods/cf51980d8e604f43849cbcdfad8c3fc2.jpg');

/*Table structure for table `t_goods` */

DROP TABLE IF EXISTS `t_goods`;

CREATE TABLE `t_goods` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '商品名',
  `price` double DEFAULT NULL COMMENT '商品价格',
  `url` varchar(500) DEFAULT NULL COMMENT '商品上传地址',
  `state` int(1) DEFAULT NULL COMMENT '0:禁用  1：下架  2：启用',
  `createtime` date DEFAULT NULL COMMENT '添加时间',
  `num` int(100) DEFAULT '0' COMMENT '加入订单次数',
  `top` int(11) DEFAULT NULL COMMENT '商品类别',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

/*Data for the table `t_goods` */

insert  into `t_goods`(`id`,`name`,`price`,`url`,`state`,`createtime`,`num`,`top`) values (1,'1',1,'',0,'2016-09-09',0,0),(2,'西麦早餐谷物无添加蔗糖膳食纤维即食纯燕麦片',19.8,'../file/goods/668db8ebd6f44a18bf8fe811173f82c4.png',2,'2016-09-09',6,0),(3,'三只松鼠 坚果炒货 零食奶油味 碧根果225g/袋',19.8,'../file/goods/a35a063a6c734b318b8f251979e210c3.png',2,'2016-09-09',2,0),(4,'杨生记休闲零食泡面搭档卤蛋30g/袋单只装',1.5,'../file/goods/e274b4352e844c3a87272e3becfd4244.png',2,'2016-09-09',5,0),(5,'重庆特产 有友泡椒凤爪 休闲零食 山椒味180g*3袋',22.5,'../file/goods/33b6dff044bf431a870fef9c6ae3c159.png',2,'2016-09-09',1,0),(6,'百草味 肉干肉脯 休闲零食 靖江猪肉脯 精制猪肉脯200g/袋',18.9,'../file/goods/e3fdb81d592c449986d8eff1d2d05807.png',2,'2016-09-09',0,0),(7,'蒙牛 特仑苏 醇纤调制乳 250ml*12 礼盒装',50,'../file/goods/ce97cf4537354bbea47838bb28f54f79.png',2,'2016-09-09',0,0),(8,'三元 黄小蕉 香蕉牛奶饮品200ml*12礼盒装',29.9,'../file/goods/f8a4576c60a845f0b75f595e9722e8f6.png',2,'2016-09-13',0,0),(9,'爱奇果 陕西眉县 猕猴桃 12粒装 单果90-110g 自营水果',28.7,'../file/goods/dc9cfaf608604b83ac057496126044d1.jpg',0,'2017-05-02',8,0),(10,'爱奇果 陕西眉县 猕猴桃 12粒装 单果90-110g 自营水果',28.7,'../file/goods/5c51302149a04924ad5a9cabef288eee.jpg',0,'2017-05-02',8,0),(11,'爱奇果 陕西眉县 猕猴桃 12粒装 单果90-110g',27.9,'../file/goods/679a4811982c41ccac08b069c5723ebf.jpg',2,'2017-05-02',8,0),(12,'1',1,'../file/goods/85762656350a4431a981e58a9bcb9c65.png',0,'2017-05-02',8,0),(13,'爱奇果 陕西眉县 猕猴桃 12粒装 单果90-110g 自营水果',27.9,'../file/goods/ba40d0aba59c472ab81e9dbaa856dc44.jpg',0,'2017-05-02',9,0),(14,'美仑达 四川蒲江青见柑橘 5斤装 自营水果',37.9,'../file/goods/a369a5b703eb460ea5d94d95a20a60b4.jpg',2,'2017-05-02',11,0),(15,'佳农 越南进口红心火龙果 3个装 中果 总重约1.1kg 新鲜时令多汁味美',29.8,'../file/goods/49c27ed722eb494aa14adb97df1e4522.jpg',2,'2017-05-02',10,0),(16,'展卉 泰国进口龙眼桂圆 特级果1kg装 自营水果',30.6,'../file/goods/9996662727e6461789ddc5fa0d623855.jpg',2,'2017-05-02',6,0),(17,'宏辉果蔬 苹果 烟台红富士 12枚75mm 单果约170-190g 总重4.2斤',23.9,'../file/goods/cf51980d8e604f43849cbcdfad8c3fc2.jpg',2,'2017-05-02',8,0),(18,'133',13,'../file/goods/ad36ab37933d4330be1c8673da1c4bd4.jpg',2,'2017-05-04',0,0),(19,'23',23,'',2,'2017-05-04',0,0),(20,'33',33,'',2,'2017-05-04',0,2),(21,'43',431,'../file/goods/394df1d5eea74bde8a48c0aec187919b.jpg',2,'2017-05-04',0,1);

/*Table structure for table `t_log` */

DROP TABLE IF EXISTS `t_log`;

CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL COMMENT '登录用户id',
  `username` varchar(50) DEFAULT NULL COMMENT '登录用户用户名',
  `name` varchar(50) DEFAULT NULL COMMENT '登录用户名字',
  `time` datetime DEFAULT NULL COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=68 DEFAULT CHARSET=utf8;

/*Data for the table `t_log` */

insert  into `t_log`(`id`,`user_id`,`username`,`name`,`time`) values (1,1,'pb','pb','2016-09-12 00:00:00'),(2,1,'1','1','2016-09-12 00:00:00'),(3,3,'3','3','2016-09-12 00:00:00'),(4,4,'4','4','2016-09-12 00:00:00'),(5,5,'5','5','2016-09-12 00:00:00'),(6,6,'6','6','2016-09-12 00:00:00'),(7,7,'7','7','2016-09-12 00:00:00'),(8,8,'8','8','2016-09-12 00:00:00'),(9,9,'9','9','2016-09-12 00:00:00'),(10,1,'1','1','2016-09-12 00:00:00'),(11,2,'2016-09-12','1','2016-09-12 00:00:00'),(12,1,'1','1','2016-09-12 00:00:00'),(13,1,'1','1','2016-09-12 00:00:00'),(14,1,'admin','彭斌','2016-09-11 00:00:00'),(15,1,'admin','彭斌','2016-09-11 00:00:00'),(16,1,'admin','彭斌','2016-09-11 00:00:00'),(17,1,'admin','彭斌','2016-09-11 17:06:59'),(18,1,'admin','彭斌','2016-09-11 17:08:35'),(19,1,'admin','彭斌','2016-09-12 09:05:25'),(20,1,'admin','彭斌','2016-09-12 15:25:58'),(21,1,'admin','彭斌','2016-09-12 18:47:56'),(22,1,'admin','彭斌','2016-09-12 19:51:05'),(23,1,'admin','彭斌','2016-09-12 20:02:57'),(24,1,'admin','彭斌','2016-09-12 22:28:00'),(25,1,'admin','彭斌','2016-09-12 23:33:34'),(26,1,'admin','彭斌','2016-09-13 09:40:28'),(27,1,'admin','彭斌','2016-09-13 16:09:09'),(28,1,'admin','彭斌','2016-09-13 17:21:24'),(29,1,'admin','彭斌','2016-09-13 18:20:22'),(30,1,'admin','彭斌','2016-09-13 18:22:16'),(31,1,'admin','彭斌','2016-09-13 18:25:15'),(32,1,'admin','彭斌','2016-09-13 18:32:24'),(33,1,'admin','彭斌','2016-09-28 10:22:58'),(34,1,'admin','彭斌','2016-09-28 10:24:57'),(35,1,'admin','彭斌','2016-10-11 10:28:54'),(36,1,'admin','彭斌','2017-01-16 22:48:52'),(37,1,'admin','彭斌','2017-01-17 15:15:47'),(38,1,'admin','彭斌','2017-01-17 15:26:57'),(39,1,'admin','彭斌','2017-01-17 16:04:57'),(40,1,'admin','彭斌','2017-01-17 16:33:23'),(41,1,'admin','彭斌','2017-01-17 16:34:51'),(42,1,'admin','彭斌','2017-01-17 16:40:54'),(43,1,'admin','彭斌','2017-01-17 16:50:09'),(44,1,'admin','彭斌','2017-01-17 20:14:46'),(45,1,'admin','彭斌','2017-01-17 20:55:41'),(46,1,'admin','彭斌','2017-01-17 21:30:57'),(47,1,'admin','彭斌','2017-01-18 21:17:46'),(48,1,'admin','彭斌','2017-01-18 21:33:06'),(49,1,'admin','彭斌','2017-01-19 16:30:10'),(50,1,'admin','彭斌','2017-01-19 17:05:09'),(51,1,'admin','彭斌','2017-01-19 20:15:40'),(52,1,'admin','彭斌','2017-01-19 21:26:46'),(53,1,'admin','彭斌','2017-03-16 10:16:27'),(54,1,'admin','彭斌','2017-03-16 16:30:20'),(55,1,'admin','彭斌','2017-03-31 12:25:03'),(56,1,'admin','彭斌','2017-03-31 12:29:03'),(57,1,'admin','彭斌','2017-04-20 12:08:55'),(58,1,'admin','彭斌','2017-04-20 13:50:36'),(59,1,'admin','彭斌','2017-05-02 09:10:27'),(60,1,'admin','彭斌','2017-05-02 09:12:22'),(61,1,'admin','彭斌','2017-05-02 09:14:24'),(62,1,'admin','彭斌','2017-05-02 09:17:03'),(63,1,'admin','彭斌','2017-05-02 09:17:52'),(64,1,'admin','彭斌','2017-05-02 09:41:53'),(65,1,'admin','彭斌','2017-05-02 09:42:45'),(66,1,'admin','彭斌','2017-05-04 09:13:28'),(67,1,'admin','彭斌','2017-05-04 13:30:54');

/*Table structure for table `t_news` */

DROP TABLE IF EXISTS `t_news`;

CREATE TABLE `t_news` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) DEFAULT NULL COMMENT '新闻标题',
  `content` text COMMENT '新闻内容',
  `time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

/*Data for the table `t_news` */

insert  into `t_news`(`id`,`title`,`content`,`time`) values (2,'厉害了！日本超市普及无人收银：效率暴增','<p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\">4月19日消息，据国外媒体报道，<strong>日本7-11和全家、罗森等5家大型便利店连锁企业将在2025年之前，在日本国内全部店铺引进消费者自己结账的自助收银柜台（self-checkout&nbsp;registers）。</strong></p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\">日本便利店计划利用能够统一读取购物筐中商品信息的IC标签，在方便顾客结账的同时，还能与产品制造商和物流企业共享有关销售状况的信息。</p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\"><strong>IC标签厚度在1毫米以下，嵌入商品的包装。利用被称为RFID（无线射频识别）的技术，可以写入商品信息并借助机器读取信息内容。</strong></p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\">在便利店购物的消费者只需将商品放在购物筐和袋子中，然后放到装有专用机器的收银台上即可完成结账。</p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\">日本5家便利店连锁通过使用共同的IC标签，无需应对供货企业不同的标准。<strong>日本经济产业省期待各公司旗下的超市和药妆店等也能扩大引进这一方式。</strong></p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\">标签还能写入商品制造的日期、工厂和保质期等信息。可以远程浏览，制造企业能随时掌握商品的销售状况。</p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\"><strong>日本经济产业省将在2017年内启动由便利店、IT企业、食品企业和物流业者等参加的协会。各便利店连锁将在2018年以后，从东京圈等城市地区的店铺开始推进自助收银化。</strong></p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\">目前的难点在于IC标签的生产成本。现在为每个10～20日元左右，在经销数十日元商品的便利店成为引进的障碍。日本经济产业省为了促进技术开发和量产化，将通过对标签开发企业的补贴等举措促进普及。</p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\"><strong>日媒称，日本还力争借助此举化解日趋严重的劳动力短缺，同时提升流通行业的工作效率。</strong></p><p style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\">另外，据之外日媒报道，为了实现人工智能（AI）的产业化，日本政府制定了路线图。计划分3个阶段推进利用AI大幅提高制造业、物流等行业。在因网购市场扩大而人手短缺的快递等物流行业，将活用卡车的自动驾驶和小型无人机，“力争到2030年实现完全无人化”。</p><p align=\"center\" style=\"margin-top: 14px; margin-bottom: 14px; word-break: normal; word-wrap: break-word; font-size: 16px; color: rgb(51, 51, 51); white-space: normal; font-stretch: normal !important; line-height: 30px !important;\"><span style=\"font-size: 0px; line-height: 0; height: 0px; display: block; clear: both;\"></span><img id=\"content-first-img\" src=\"http://uppic.fd.zol-img.com.cn/t_s500x2000/g5/M00/0D/05/ChMkJlj24pGIDN9RAAIATaM1UXMAAbxkAGKid8AAgBl044.jpg\" class=\"subscibe-img\" style=\"display: block; margin-right: auto; margin-left: auto;\"/></p><p><br/></p>','2017-01-19 00:00:00'),(3,'六男子“闪电手”晃晕超市收银员 连续行骗','<p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; font-size: 16px; line-height: 28px; white-space: normal; text-indent: 2em;\">（原标题：六男子“闪电手”晃晕超市收银员）</p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; font-size: 16px; line-height: 28px; white-space: normal; text-indent: 2em;\"><strong>中安在线讯</strong>据安徽商报消息，4月17日下午，合肥市东二环路大润发超市，收银员小霞和小玲在十分钟内，分别被三名男顾客骗得295元和292元。警方通过监控视频发现，6名男子均挑选了5元内商品，买单递出百元钞票，收银员接钞后按规定验钞并打开钱箱时，男子忙称有零钱，递出零钱的同时快速从收银员手中抽回百元钞。因速度太快，收银员错认为百元钞票已被放入钱箱，按收银机提示的余额找给男子90多元。警方称，6名男子利用了收银员重复性<a g=\"desktop\" target=\"_blank\" key=\"12\" href=\"http://time.qq.com/baike/deskclean/20170207.htm?pgv_ref=guanjianews_tips&from=guanjia\" class=\"nameStar gj-nameStar gj-keyword\" style=\"outline: 0px; color: rgb(81, 112, 166); border-bottom-width: 1px; border-bottom-style: dotted; border-bottom-color: rgb(83, 109, 166);\">工作</a>中的惯性和疲态。这伙人或潜入其他超市行骗。</p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; font-size: 16px; line-height: 28px; white-space: normal; text-indent: 2em;\">近日，大学生小霞和同学小玲到大润发超市当实习收银员，由于高强度的工作节奏，两人甚至感到有些麻木。 17日傍晚下班时，小霞和小玲核对收钱箱里的账目，发现分别少了295元和292元。两人发现，当日下午，有六名男顾客，三人一组来到她俩的账台边，以先递整钞后给零钱的方式行骗。</p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; font-size: 16px; line-height: 28px; white-space: normal; text-indent: 2em;\">小霞说，17日16:30许，一男子来买单，该男子拿了一瓶2.5元的饮料，她用机器扫码时，男子递过一张百元钞票。小霞接钱后，顺手把钱放入验钞机，紧接着收钱箱自动弹开。此时，该男子又递给她2.5元零钱，说“有零钱给你”。“同时，男子快速抽回还在我手里的百元钞票。”小霞说，因为速度太快，她产生了错觉，认为百元钞票已经被她放入了钱箱。而此时，收银机却提示应找零97.5元。小霞随即从钱箱内拿出相应钱款给了男子。</p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; font-size: 16px; line-height: 28px; white-space: normal; text-indent: 2em;\">辖区警方查看该超市监控后称，当日陆续采取这一手法作案的共六名男子，他们买单的商品都未超5元。小霞在10分钟内，接连被三名男子以相同方式行骗。位于8号收银台的小玲，也在十分钟内被其他三名男子骗了钱。小霞称，收银结账是一项重复性高的工作，借助机器提示收钱找钱，时间一长，精神上会有依赖感。</p><p class=\"text\" style=\"margin-top: 0px; margin-bottom: 28px; word-wrap: break-word; color: rgb(0, 0, 0); font-family: &#39;Microsoft Yahei&#39;, Helvetica, sans-serif; font-size: 16px; line-height: 28px; white-space: normal; text-indent: 2em;\">合肥警方称，涉案六男子除了出手快，也利用收银员重复性找零工作中的惯性和疲态。不排除这伙人潜入其他超市用相同手法作案，值得警惕。警方目前正调查处理此事</p><p><br/></p>','2017-01-19 00:00:00');

/*Table structure for table `t_order` */

DROP TABLE IF EXISTS `t_order`;

CREATE TABLE `t_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `buy_id` int(10) DEFAULT NULL COMMENT '订单id',
  `goods_id` int(10) DEFAULT NULL COMMENT '商品id',
  `time` datetime DEFAULT NULL COMMENT '订单生成时间',
  `state` int(1) DEFAULT NULL COMMENT '订单状态0取消1正常2完成',
  `note` varchar(100) DEFAULT NULL COMMENT '备注',
  `number` int(11) DEFAULT NULL COMMENT '商品数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

/*Data for the table `t_order` */

insert  into `t_order`(`id`,`buy_id`,`goods_id`,`time`,`state`,`note`,`number`) values (1,1,2,'2016-09-09 00:00:00',1,NULL,2),(2,2,5,'2016-09-10 00:00:00',1,NULL,1),(3,5,5,'2016-09-09 00:00:00',1,NULL,1),(4,5,3,'2016-09-09 00:00:00',1,NULL,1),(5,5,1,'2016-09-12 16:20:27',1,NULL,2),(6,5,3,'2016-09-12 16:20:27',1,NULL,2),(7,5,6,'2016-09-12 16:20:27',1,NULL,1),(8,5,1,'2016-09-12 16:23:09',1,NULL,2),(9,5,3,'2016-09-12 16:23:09',1,NULL,2),(10,5,6,'2016-09-12 16:23:09',1,NULL,1),(11,5,2,'2016-09-12 19:50:28',1,NULL,1),(12,5,3,'2016-09-12 19:50:28',1,NULL,1),(13,5,4,'2016-09-12 19:50:28',1,NULL,1),(14,1,6,'2016-09-12 19:50:28',1,NULL,1),(15,1,7,'2016-09-12 19:50:28',1,NULL,1),(16,1,2,'2016-09-12 19:59:07',1,NULL,1),(17,4,3,'2016-09-12 19:59:12',2,NULL,1),(18,4,4,'2016-09-12 20:01:04',2,NULL,1),(19,4,3,'2016-09-12 20:01:06',2,NULL,1),(20,4,4,'2016-09-12 20:02:30',2,NULL,1),(21,1,3,'2016-09-12 20:02:34',2,NULL,1),(22,1,2,'2016-09-12 21:53:12',1,NULL,1),(23,1,4,'2016-09-12 21:54:05',1,NULL,1),(24,1,2,'2016-09-12 22:24:05',1,NULL,1),(25,1,4,'2016-09-13 17:20:50',1,NULL,1),(26,1,2,'2016-09-13 17:20:50',1,NULL,4),(27,9,3,'2017-01-18 21:12:40',1,NULL,1),(28,10,4,'2017-01-18 21:14:56',1,NULL,1),(29,11,5,'2017-01-18 21:15:46',1,NULL,1),(30,12,4,'2017-01-18 21:16:21',1,NULL,1),(31,13,4,'2017-01-18 21:17:10',1,NULL,1),(32,14,3,'2017-05-02 09:19:05',1,NULL,1),(33,14,2,'2017-05-02 09:19:05',1,NULL,1),(34,15,11,'2017-05-02 09:19:13',1,NULL,1);

/*Table structure for table `t_user` */

DROP TABLE IF EXISTS `t_user`;

CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL COMMENT '用户名',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `address` varchar(100) DEFAULT NULL COMMENT '家庭住址',
  `tel` varchar(20) DEFAULT NULL COMMENT '手机号',
  `name` varchar(50) DEFAULT NULL COMMENT '昵称',
  `register` datetime DEFAULT NULL COMMENT '注册时间',
  `money` int(20) DEFAULT NULL COMMENT '订单总金额',
  `type` int(1) DEFAULT NULL COMMENT '1:普通用户 2：管理员',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

/*Data for the table `t_user` */

insert  into `t_user`(`id`,`username`,`password`,`address`,`tel`,`name`,`register`,`money`,`type`) values (1,'admin','123456','江苏溧阳','13622116321','彭斌','2016-09-09 00:00:00',50,2),(2,'555','123','salksal','15622222222','pb','2016-09-09 00:00:00',22,1),(3,'1@qq.com','1','1','1','1','2016-09-12 00:00:00',NULL,1),(4,'419995258@qq.com','pb19950516','liyang','15620942728','pb','2016-09-13 00:00:00',NULL,1),(5,'365719444@qq.com','1234567890','太原','13622116321','lala','2017-03-29 00:00:00',NULL,1),(6,'363333333@qq.com','123456789',NULL,'','slkjdlkas','2017-03-29 00:00:00',NULL,1),(7,'1111111111@qq.com','111111',NULL,NULL,'111111','2017-03-29 00:00:00',NULL,1),(8,'1234567890@qq.com','1234567890','哈哈','13622116321','哈哈','2017-03-29 00:00:00',NULL,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
