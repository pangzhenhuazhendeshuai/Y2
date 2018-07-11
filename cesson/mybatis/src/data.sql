DROP DATABASE IF EXISTS `bookmanage`;
/*创建超市订单管理系统数据库*/
CREATE DATABASE `bookmanage`;
USE `bookmanage`;


/*创建地址信息表*/
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `id` BIGINT(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `ISBN` VARCHAR(15) COLLATE utf8_unicode_ci COMMENT '出版商一套书定的编号',
  `name` VARCHAR(50) COLLATE utf8_unicode_ci COMMENT '书名',
  `price` FLOAT(2) COLLATE utf8_unicode_ci COMMENT '价格',
  `discount` DOUBLE COLLATE utf8_unicode_ci COMMENT '折扣',
  `publisher` VARCHAR(20) DEFAULT NULL COMMENT '出版社',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

INSERT INTO `book`(`ISBN`,`name`,`price`,`discount`,`publisher`) VALUES('BILL2016_001','使用SSM框架开发企业级应用','125.00','1','电子工业出版社'),
('BILL2016_002','基于SSH框架的企业级应用开发','130.00','1','电子工业出版社'),
('BILL2016_003','基于Hadoop生态系统的大数据解决方案','150.00','1','电子工业出版社');

SELECT * FROM `book`;
