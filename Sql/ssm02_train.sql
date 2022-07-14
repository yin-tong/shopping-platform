/*
SQLyog Trial v13.1.8 (64 bit)
MySQL - 8.0.20 : Database - ssm02_train
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`ssm02_train` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `ssm02_train`;

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `id` varchar(32) NOT NULL,
  `orderTime` timestamp NULL DEFAULT NULL,
  `orderDesc` varchar(500) DEFAULT NULL,
  `payType` int DEFAULT NULL,
  `orderStatus` int DEFAULT NULL,
  `productId` varchar(32) DEFAULT NULL,
  `userId` varchar(32) DEFAULT NULL,
  `deleted` int DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `orders` */

insert  into `orders`(`id`,`orderTime`,`orderDesc`,`payType`,`orderStatus`,`productId`,`userId`,`deleted`) values 
('0110b9c4443711ecaa6b005056c00001','2021-11-13 12:05:57',NULL,1,1,'9d493ab3443611ecaa6b005056c00001','de03ff49439d11ecaa6b005056c00001',0),
('0defcb37443711ecaa6b005056c00001','2021-11-13 12:06:19',NULL,1,3,'9d493ab3443611ecaa6b005056c00001','de03ff49439d11ecaa6b005056c00001',0),
('8a697e30443811ecaa6b005056c00001','2021-11-13 12:16:57',NULL,1,3,'9d493ab3443611ecaa6b005056c00001','de03ff49439d11ecaa6b005056c00001',1),
('c88fb2b5443711ecaa6b005056c00001','2021-11-13 12:11:32',NULL,1,3,'9d493ab3443611ecaa6b005056c00001','de03ff49439d11ecaa6b005056c00001',0),
('eb0b2ee6443611ecaa6b005056c00001','2021-11-13 12:05:20',NULL,1,3,'9d493ab3443611ecaa6b005056c00001','de03ff49439d11ecaa6b005056c00001',0);

/*Table structure for table `permission` */

DROP TABLE IF EXISTS `permission`;

CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL,
  `permissionName` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `permission` */

insert  into `permission`(`id`,`permissionName`,`url`) values 
('16fcd39343a011ecaa6b005056c00001','user-findAll','/user/findAll'),
('bad55836439d11ecaa6b005056c00001','user-delete','/');

/*Table structure for table `product` */

DROP TABLE IF EXISTS `product`;

CREATE TABLE `product` (
  `id` varchar(32) NOT NULL,
  `productNum` varchar(50) NOT NULL,
  `productName` varchar(50) DEFAULT NULL,
  `cityName` varchar(50) DEFAULT NULL,
  `departureTime` timestamp NULL DEFAULT NULL,
  `productPrice` float DEFAULT NULL,
  `productDesc` varchar(500) DEFAULT NULL,
  `productstatus` int DEFAULT NULL,
  `quantity` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `product` (`id`,`productNum`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `product` */

insert  into `product`(`id`,`productNum`,`productName`,`cityName`,`departureTime`,`productPrice`,`productDesc`,`productstatus`,`quantity`) values 
('9d493ab3443611ecaa6b005056c00001','xz-001','耐克','丽水','2021-11-12 00:00:00',150,'大减价',1,120);

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `roleName` varchar(50) DEFAULT NULL,
  `roleDesc` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role` */

insert  into `role`(`id`,`roleName`,`roleDesc`) values 
('2a44debf439311ecaa6b005056c00001','ADMIN','超级管理员'),
('2a47e7a3439311ecaa6b005056c00001','USER','普通用户');

/*Table structure for table `role_permission` */

DROP TABLE IF EXISTS `role_permission`;

CREATE TABLE `role_permission` (
  `permissionId` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  PRIMARY KEY (`permissionId`,`roleId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `role_permission_ibfk_1` FOREIGN KEY (`permissionId`) REFERENCES `permission` (`id`),
  CONSTRAINT `role_permission_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `role_permission` */

/*Table structure for table `syslog` */

DROP TABLE IF EXISTS `syslog`;

CREATE TABLE `syslog` (
  `id` varchar(32) NOT NULL,
  `visitTime` timestamp NULL DEFAULT NULL,
  `username` varchar(50) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `url` varchar(50) DEFAULT NULL,
  `executionTime` int DEFAULT NULL,
  `method` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `syslog` */

insert  into `syslog`(`id`,`visitTime`,`username`,`ip`,`url`,`executionTime`,`method`) values 
('031c0305448311eca579005056c00001','2021-11-13 21:10:02','admin','0:0:0:0:0:0:0:1','/user/findAll',38,'[类名] com.ssm.controller.UserController[方法名] findAll'),
('048d55d8448311eca579005056c00001','2021-11-13 21:10:05','admin','0:0:0:0:0:0:0:1','/role/findAll',19,'[类名] com.ssm.controller.RoleController[方法名] findAll'),
('05dee6c1448311eca579005056c00001','2021-11-13 21:10:07','admin','0:0:0:0:0:0:0:1','/permission/findAll',12,'[类名] com.ssm.controller.PermissionController[方法名] findAll'),
('0718fae5448311eca579005056c00001','2021-11-13 21:10:09','admin','0:0:0:0:0:0:0:1','/sysLog/findAll',43,'[类名] com.ssm.controller.SysLogController[方法名] findAll'),
('07d90850448311eca579005056c00001','2021-11-13 21:10:10','admin','0:0:0:0:0:0:0:1','/product/findAll',31,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('08ee4e1c448311eca579005056c00001','2021-11-13 21:10:12','admin','0:0:0:0:0:0:0:1','/orders/findAll',235,'[类名] com.ssm.controller.OrdersController[方法名] findAll'),
('096b7984448611eca579005056c00001','2021-11-13 21:31:42','admin','0:0:0:0:0:0:0:1','/permission/findAll',34,'[类名] com.ssm.controller.PermissionController[方法名] findAll'),
('09c2d51b448311eca579005056c00001','2021-11-13 21:10:14','admin','0:0:0:0:0:0:0:1','/path/findAll',82,'[类名] com.ssm.controller.PathController[方法名] findAll'),
('4f4db735448311eca579005056c00001','2021-11-13 21:12:10','admin','0:0:0:0:0:0:0:1','/path/findAll',101,'[类名] com.ssm.controller.PathController[方法名] findAll'),
('8e51715b448311eca579005056c00001','2021-11-13 21:13:56','admin','0:0:0:0:0:0:0:1','/product/findAll',208,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('915e22a7448311eca579005056c00001','2021-11-13 21:14:01','admin','0:0:0:0:0:0:0:1','/product/findAll',12,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('919bfd7b444011ecaa6b005056c00001','2021-11-13 13:14:25','admin','0:0:0:0:0:0:0:1','/permission/findAll',60,'[类名] com.ssm.controller.PermissionController[方法名] findAll'),
('93c0214e448311eca579005056c00001','2021-11-13 21:14:05','admin','0:0:0:0:0:0:0:1','/product/findAll',41,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('96c7a4d2444011ecaa6b005056c00001','2021-11-13 13:14:34','admin','0:0:0:0:0:0:0:1','/sysLog/findAll',21,'[类名] com.ssm.controller.SysLogController[方法名] findAll'),
('9bb19227444211ecaa6b005056c00001','2021-11-13 13:29:01','admin','0:0:0:0:0:0:0:1','/role/findAll',23,'[类名] com.ssm.controller.RoleController[方法名] findAll'),
('9cbf42f6448511eca579005056c00001','2021-11-13 21:28:39','admin','0:0:0:0:0:0:0:1','/user/findAll',199,'[类名] com.ssm.controller.UserController[方法名] findAll'),
('9e199173448511eca579005056c00001','2021-11-13 21:28:41','admin','0:0:0:0:0:0:0:1','/role/findAll',112,'[类名] com.ssm.controller.RoleController[方法名] findAll'),
('a041bad7448511eca579005056c00001','2021-11-13 21:28:45','admin','0:0:0:0:0:0:0:1','/permission/findAll',436,'[类名] com.ssm.controller.PermissionController[方法名] findAll'),
('a07848d9444211ecaa6b005056c00001','2021-11-13 13:29:09','admin','0:0:0:0:0:0:0:1','/permission/findAll',251,'[类名] com.ssm.controller.PermissionController[方法名] findAll'),
('a273580e444211ecaa6b005056c00001','2021-11-13 13:29:13','admin','0:0:0:0:0:0:0:1','/sysLog/findAll',30,'[类名] com.ssm.controller.SysLogController[方法名] findAll'),
('a3d44e2a444211ecaa6b005056c00001','2021-11-13 13:29:15','admin','0:0:0:0:0:0:0:1','/sysLog/findAll',33,'[类名] com.ssm.controller.SysLogController[方法名] findAll'),
('a669168b444211ecaa6b005056c00001','2021-11-13 13:29:19','admin','0:0:0:0:0:0:0:1','/sysLog/findAll',28,'[类名] com.ssm.controller.SysLogController[方法名] findAll'),
('a8eefe89444211ecaa6b005056c00001','2021-11-13 13:29:23','admin','0:0:0:0:0:0:0:1','/sysLog/findAll',660,'[类名] com.ssm.controller.SysLogController[方法名] findAll'),
('a9f3f10a444211ecaa6b005056c00001','2021-11-13 13:29:25','admin','0:0:0:0:0:0:0:1','/path/findAll',63,'[类名] com.ssm.controller.PathController[方法名] findAll'),
('ab1b905c444211ecaa6b005056c00001','2021-11-13 13:29:27','admin','0:0:0:0:0:0:0:1','/product/findAll',25,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('b582f85d444211ecaa6b005056c00001','2021-11-13 13:29:44','admin','0:0:0:0:0:0:0:1','/product/findAll',117,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('bbfebde4444211ecaa6b005056c00001','2021-11-13 13:29:55','admin','0:0:0:0:0:0:0:1','/product/findAll',24,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('c1552f07444211ecaa6b005056c00001','2021-11-13 13:30:04','admin','0:0:0:0:0:0:0:1','/product/findAll',31,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('c489c6d7444211ecaa6b005056c00001','2021-11-13 13:30:09','admin','0:0:0:0:0:0:0:1','/orders/findAll',870,'[类名] com.ssm.controller.OrdersController[方法名] findAll'),
('c4a6ffd5444011ecaa6b005056c00001','2021-11-13 13:15:51','admin','0:0:0:0:0:0:0:1','/role/findAll',15,'[类名] com.ssm.controller.RoleController[方法名] findAll'),
('c85f63d4444011ecaa6b005056c00001','2021-11-13 13:15:57','admin','0:0:0:0:0:0:0:1','/role/findAll',9,'[类名] com.ssm.controller.RoleController[方法名] findAll'),
('c96d953f444011ecaa6b005056c00001','2021-11-13 13:15:59','admin','0:0:0:0:0:0:0:1','/permission/findAll',70,'[类名] com.ssm.controller.PermissionController[方法名] findAll'),
('cfa08157444211ecaa6b005056c00001','2021-11-13 13:30:28','admin','0:0:0:0:0:0:0:1','/user/findAll',385,'[类名] com.ssm.controller.UserController[方法名] findAll'),
('d3c982bc444211ecaa6b005056c00001','2021-11-13 13:30:35','admin','0:0:0:0:0:0:0:1','/product/findAll',29,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('d53a61c1444211ecaa6b005056c00001','2021-11-13 13:30:38','admin','0:0:0:0:0:0:0:1','/product/findAll',26,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('d7afdbad444211ecaa6b005056c00001','2021-11-13 13:30:42','admin','0:0:0:0:0:0:0:1','/product/findAll',293,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('da077f80444211ecaa6b005056c00001','2021-11-13 13:30:46','admin','0:0:0:0:0:0:0:1','/product/findAll',24,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('db19474c444211ecaa6b005056c00001','2021-11-13 13:30:48','admin','0:0:0:0:0:0:0:1','/product/findAll',27,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('eab6a574447e11eca579005056c00001','2021-11-13 20:40:43','admin','0:0:0:0:0:0:0:1','/permission/findAll',77,'[类名] com.ssm.controller.PermissionController[方法名] findAll'),
('ec25cb36447e11eca579005056c00001','2021-11-13 20:40:46','admin','0:0:0:0:0:0:0:1','/sysLog/findAll',75,'[类名] com.ssm.controller.SysLogController[方法名] findAll'),
('f173d681444211ecaa6b005056c00001','2021-11-13 13:31:25','user','0:0:0:0:0:0:0:1','/path/findAll',46,'[类名] com.ssm.controller.PathController[方法名] findAll'),
('f311bb44444211ecaa6b005056c00001','2021-11-13 13:31:28','user','0:0:0:0:0:0:0:1','/product/findAll',14,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('f493b200444211ecaa6b005056c00001','2021-11-13 13:31:30','user','0:0:0:0:0:0:0:1','/product/findAll',25,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('f80d6c8f444211ecaa6b005056c00001','2021-11-13 13:31:36','user','0:0:0:0:0:0:0:1','/product/findAll',33,'[类名] com.ssm.controller.ProductController[方法名] findAll'),
('fb5dfc4d444211ecaa6b005056c00001','2021-11-13 13:31:42','user','0:0:0:0:0:0:0:1','/orders/findAll',117,'[类名] com.ssm.controller.OrdersController[方法名] findAll'),
('fd96f5a3444211ecaa6b005056c00001','2021-11-13 13:31:45','user','0:0:0:0:0:0:0:1','/product/findAll',15,'[类名] com.ssm.controller.ProductController[方法名] findAll');

/*Table structure for table `users` */

DROP TABLE IF EXISTS `users`;

CREATE TABLE `users` (
  `id` varchar(32) NOT NULL,
  `email` varchar(50) NOT NULL,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phoneNum` varchar(20) DEFAULT NULL,
  `status` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users` */

insert  into `users`(`id`,`email`,`username`,`password`,`phoneNum`,`status`) values 
('887880a2439211ecaa6b005056c00001','123@qq.com','admin','$2a$10$iGEVg0umZMt2Dan1geoE2.nWOvniTQ3v2Ce/X7SXKYVyqWGy/Vq4q','17856659858',1),
('de03ff49439d11ecaa6b005056c00001','555qq.com','user','$2a$10$rfrOMhO/LEAE67UEn3wSmOXf3OEcmnZ1vyhkzOyOxS6I3zVjvvJEW','12365',1);

/*Table structure for table `users_role` */

DROP TABLE IF EXISTS `users_role`;

CREATE TABLE `users_role` (
  `userId` varchar(32) NOT NULL,
  `roleId` varchar(32) NOT NULL,
  PRIMARY KEY (`userId`,`roleId`),
  KEY `roleId` (`roleId`),
  CONSTRAINT `users_role_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `users` (`id`),
  CONSTRAINT `users_role_ibfk_2` FOREIGN KEY (`roleId`) REFERENCES `role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `users_role` */

insert  into `users_role`(`userId`,`roleId`) values 
('887880a2439211ecaa6b005056c00001','2a44debf439311ecaa6b005056c00001'),
('de03ff49439d11ecaa6b005056c00001','2a47e7a3439311ecaa6b005056c00001');

/* Trigger structure for table `orders` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `orders_id_trigger` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `orders_id_trigger` BEFORE INSERT ON `orders` FOR EACH ROW BEGIN
   SET new.id=REPLACE(UUID(),'-','');
END */$$


DELIMITER ;

/* Trigger structure for table `permission` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `permission_id_trigger` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `permission_id_trigger` BEFORE INSERT ON `permission` FOR EACH ROW BEGIN
   SET new.id=REPLACE(UUID(),'-','');
END */$$


DELIMITER ;

/* Trigger structure for table `product` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `product_id_trigger` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `product_id_trigger` BEFORE INSERT ON `product` FOR EACH ROW BEGIN
   SET new.id=REPLACE(UUID(),'-','');
END */$$


DELIMITER ;

/* Trigger structure for table `role` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `role_id_trigger` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `role_id_trigger` BEFORE INSERT ON `role` FOR EACH ROW BEGIN
   SET new.id=REPLACE(UUID(),'-','');
END */$$


DELIMITER ;

/* Trigger structure for table `syslog` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `syslog_id_trigger` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `syslog_id_trigger` BEFORE INSERT ON `syslog` FOR EACH ROW BEGIN
   SET new.id=REPLACE(UUID(),'-','');
END */$$


DELIMITER ;

/* Trigger structure for table `users` */

DELIMITER $$

/*!50003 DROP TRIGGER*//*!50032 IF EXISTS */ /*!50003 `users_id_trigger` */$$

/*!50003 CREATE */ /*!50017 DEFINER = 'root'@'localhost' */ /*!50003 TRIGGER `users_id_trigger` BEFORE INSERT ON `users` FOR EACH ROW BEGIN
   SET new.id=REPLACE(UUID(),'-','');
END */$$


DELIMITER ;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
