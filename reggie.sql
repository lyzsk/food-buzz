CREATE DATABASE `reggie` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- reggie.address_book definition

CREATE TABLE `address_book` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `consignee` varchar(50) DEFAULT NULL COMMENT '收货人',
  `sex` tinyint DEFAULT NULL COMMENT '性别 0 女 1 男',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `province_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省级区划编号',
  `province_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '省级名称',
  `city_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市级区划编号',
  `city_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '市级名称',
  `district_code` varchar(12) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区级区划编号',
  `district_name` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '详细地址',
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '标签',
  `is_default` tinyint(1) NOT NULL DEFAULT '0' COMMENT '默认 0 否 1是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.category definition

CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` int DEFAULT NULL COMMENT '类型   1 菜品分类 2 套餐分类',
  `name` varchar(64) NOT NULL COMMENT '分类名称',
  `sort` int NOT NULL DEFAULT '0' COMMENT '顺序',
  `status` int DEFAULT NULL COMMENT '分类状态 0:禁用，1:启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_category_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.dish definition

CREATE TABLE `dish` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) NOT NULL COMMENT '菜品名称',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品价格',
  `image` varchar(200) DEFAULT NULL COMMENT '图片',
  `description` varchar(400) DEFAULT NULL COMMENT '描述信息',
  `status` int DEFAULT '1' COMMENT '0 停售 1 起售',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_dish_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.dish_flavor definition

CREATE TABLE `dish_flavor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` bigint NOT NULL COMMENT '菜品',
  `name` varchar(64) DEFAULT NULL COMMENT '口味名称',
  `value` varchar(500) DEFAULT NULL COMMENT '口味数据list',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=107 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.employee definition

CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(32) NOT NULL COMMENT '姓名',
  `username` varchar(32) NOT NULL COMMENT '用户名',
  `password` varchar(64) NOT NULL COMMENT '密码',
  `phone` varchar(11) NOT NULL COMMENT '手机号',
  `sex` varchar(2) NOT NULL COMMENT '性别',
  `id_number` varchar(18) NOT NULL COMMENT '身份证号',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 0:禁用，1:启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1605490984589774851 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.order_detail definition

CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名字',
  `image` varchar(100) DEFAULT NULL COMMENT '图片',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.orders definition

CREATE TABLE `orders` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `number` varchar(50) DEFAULT NULL COMMENT '订单号',
  `status` int NOT NULL DEFAULT '1' COMMENT '订单状态 1待付款 2待接单 3已接单 4派送中 5已完成 6已取消 7退款',
  `user_id` bigint NOT NULL COMMENT '下单用户',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `checkout_time` datetime DEFAULT NULL COMMENT '结账时间',
  `pay_method` int NOT NULL DEFAULT '1' COMMENT '支付方式 1微信,2支付宝',
  `pay_status` tinyint NOT NULL DEFAULT '0' COMMENT '支付状态 0未支付 1已支付 2退款',
  `amount` decimal(10,2) NOT NULL COMMENT '实收金额',
  `remark` varchar(100) DEFAULT NULL COMMENT '备注',
  `phone` varchar(255) DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名称',
  `consignee` varchar(255) DEFAULT NULL COMMENT '收货人',
  `cancel_reason` varchar(255) DEFAULT NULL COMMENT '订单取消原因',
  `rejection_reason` varchar(255) DEFAULT NULL COMMENT '订单拒绝原因',
  `cancel_time` datetime DEFAULT NULL COMMENT '订单取消时间',
  `estimated_delivery_time` datetime DEFAULT NULL COMMENT '预计送达时间',
  `delivery_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '配送状态  1立即送出  0选择具体时间',
  `delivery_time` datetime DEFAULT NULL COMMENT '送达时间',
  `pack_amount` int DEFAULT NULL COMMENT '打包费',
  `tableware_number` int DEFAULT NULL COMMENT '餐具数量',
  `tableware_status` tinyint(1) NOT NULL DEFAULT '1' COMMENT '餐具数量状态  1按餐量提供  0选择具体数量',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1606357097850728451 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.setmeal definition

CREATE TABLE `setmeal` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `name` varchar(64) NOT NULL COMMENT '套餐名称',
  `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `status` int DEFAULT '1' COMMENT '状态 0:停用 1:启用',
  `description` varchar(512) DEFAULT NULL COMMENT '描述信息',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_setmeal_name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.setmeal_dish definition

CREATE TABLE `setmeal_dish` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `setmeal_id` varchar(32) NOT NULL COMMENT '套餐id ',
  `dish_id` varchar(32) NOT NULL COMMENT '菜品id',
  `name` varchar(32) DEFAULT NULL COMMENT '菜品名称 （冗余字段）',
  `price` decimal(10,2) DEFAULT NULL COMMENT '菜品原价（冗余字段）',
  `copies` int DEFAULT NULL COMMENT '份数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=62 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.shopping_cart definition

CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `image` varchar(100) DEFAULT NULL COMMENT '图片',
  `user_id` bigint NOT NULL COMMENT '主键',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `setmeal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `dish_flavor` varchar(50) DEFAULT NULL COMMENT '口味',
  `number` int NOT NULL DEFAULT '1' COMMENT '数量',
  `amount` decimal(10,2) NOT NULL COMMENT '金额',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.`user` definition

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `openid` varchar(45) DEFAULT NULL COMMENT '微信用户唯一标识',
  `name` varchar(50) DEFAULT NULL COMMENT '姓名',
  `phone` varchar(100) DEFAULT NULL COMMENT '手机号',
  `sex` varchar(2) DEFAULT NULL COMMENT '性别',
  `id_number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `avatar` varchar(500) DEFAULT NULL COMMENT '头像',
  `create_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- reggie.user_info definition

CREATE TABLE `user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(50) NOT NULL COMMENT '用户名',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `update_time` date DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO reggie.address_book (user_id,consignee,sex,phone,province_code,province_name,city_code,city_name,district_code,district_name,detail,label,is_default) VALUES
	 (3,'张三',1,'18351055698',NULL,NULL,NULL,NULL,NULL,NULL,'北京清华大学','学校',0),
	 (3,'张三',0,'18355698150',NULL,NULL,NULL,NULL,NULL,NULL,'北京北京大学','学校',1),
	 (3,'张三',1,'18355698150',NULL,NULL,NULL,NULL,NULL,NULL,'北京北京大学','学校',0),
	 (2,'李四',0,'18351055698',NULL,NULL,NULL,NULL,NULL,NULL,'北京地中海大学','学校',1),
	 (5,'王五',0,'13814798573',NULL,NULL,NULL,NULL,NULL,NULL,'北京市收货地址公司','公司',1),
	 (1,'赵六',1,'13867491083',NULL,NULL,NULL,NULL,NULL,NULL,'北京市测试大学','学校',1);

	
INSERT INTO reggie.category (`type`,name,sort,status,create_time,update_time,create_user,update_user) VALUES
	 (1,'酒水饮料',10,1,'2022-06-09 22:09:18','2022-06-09 22:09:18',1,1),
	 (1,'传统主食',9,1,'2022-06-09 22:09:32','2022-06-09 22:18:53',1,1),
	 (2,'人气套餐',12,1,'2022-06-09 22:11:38','2022-06-10 11:04:40',1,1),
	 (2,'商务套餐',13,1,'2022-06-09 22:14:10','2022-06-10 11:04:48',1,1),
	 (1,'蜀味烤鱼',4,1,'2022-06-09 22:15:37','2022-06-09 22:15:37',1,1),
	 (1,'特色蒸菜',6,1,'2022-06-09 22:17:42','2022-06-09 22:17:42',1,1),
	 (1,'新鲜时蔬',7,1,'2022-06-09 22:18:12','2022-06-09 22:18:28',1,1),
	 (1,'水煮鱼',8,1,'2022-06-09 22:22:29','2022-06-09 22:23:45',1,1),
	 (1,'汤类',11,1,'2022-06-10 10:51:47','2022-06-10 10:51:47',1,1),
	 (1,'鱼类',1,1,'2022-12-21 12:06:23','2022-12-21 12:06:23',1,1);
INSERT INTO reggie.category (`type`,name,sort,status,create_time,update_time,create_user,update_user) VALUES
	 (1,'牛羊类',3,1,'2022-12-21 12:06:47','2022-12-21 13:27:17',1,1);

	
INSERT INTO reggie.dish (name,category_id,price,image,description,status,create_time,update_time,create_user,update_user) VALUES
	 ('王老吉',11,6.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/41bfcacf-7ad4-4927-8b26-df366553a94c.png','',1,'2022-06-09 22:40:47','2022-06-09 22:40:47',1,1),
	 ('北冰洋',11,4.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/4451d4be-89a2-4939-9c69-3a87151cb979.png','还是小时候的味道',1,'2022-06-10 09:18:49','2022-06-10 09:18:49',1,1),
	 ('雪花啤酒',11,4.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/bf8cbfc1-04d2-40e8-9826-061ee41ab87c.png','',1,'2022-06-10 09:22:54','2022-06-10 09:22:54',1,1),
	 ('米饭',12,2.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/76752350-2121-44d2-b477-10791c23a8ec.png','精选五常大米',1,'2022-06-10 09:30:17','2022-06-10 09:30:17',1,1),
	 ('馒头',12,1.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/475cc599-8661-4899-8f9e-121dd8ef7d02.png','优质面粉',1,'2022-06-10 09:34:28','2022-06-10 09:34:28',1,1),
	 ('老坛酸菜鱼',23,56.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/4a9cefba-6a74-467e-9fde-6e687ea725d7.png','原料：汤，草鱼，酸菜',1,'2022-06-10 09:40:51','2022-06-10 09:40:51',1,1),
	 ('经典酸菜鮰鱼',23,66.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/5260ff39-986c-4a97-8850-2ec8c7583efc.png','原料：酸菜，江团，鮰鱼',1,'2022-06-10 09:46:02','2022-06-10 09:46:02',1,1),
	 ('蜀味水煮草鱼',23,38.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/a6953d5a-4c18-4b30-9319-4926ee77261f.png','原料：草鱼，汤',1,'2022-06-10 09:48:37','2022-06-10 09:48:37',1,1),
	 ('清炒小油菜',19,18.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/3613d38e-5614-41c2-90ed-ff175bf50716.png','原料：小油菜',1,'2022-06-10 09:51:46','2022-06-10 09:51:46',1,1),
	 ('蒜蓉娃娃菜',19,18.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/4879ed66-3860-4b28-ba14-306ac025fdec.png','原料：蒜，娃娃菜',1,'2022-06-10 09:53:37','2022-06-10 09:53:37',1,1);
INSERT INTO reggie.dish (name,category_id,price,image,description,status,create_time,update_time,create_user,update_user) VALUES
	 ('清炒西兰花',19,18.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/e9ec4ba4-4b22-4fc8-9be0-4946e6aeb937.png','原料：西兰花',1,'2022-06-10 09:55:44','2022-06-10 09:55:44',1,1),
	 ('炝炒圆白菜',19,18.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/22f59feb-0d44-430e-a6cd-6a49f27453ca.png','原料：圆白菜',1,'2022-06-10 09:58:35','2022-06-10 09:58:35',1,1),
	 ('清蒸鲈鱼',23,98.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png','原料：鲈鱼',1,'2022-06-10 10:12:28','2022-06-10 10:12:28',1,1),
	 ('东坡肘子',18,138.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/a80a4b8c-c93e-4f43-ac8a-856b0d5cc451.png','原料：猪肘棒',1,'2022-06-10 10:24:03','2022-06-10 10:24:03',1,1),
	 ('梅菜扣肉',18,58.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/6080b118-e30a-4577-aab4-45042e3f88be.png','原料：猪肉，梅菜',1,'2022-06-10 10:26:03','2022-06-10 10:26:03',1,1),
	 ('剁椒鱼头',18,66.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/13da832f-ef2c-484d-8370-5934a1045a06.png','原料：鲢鱼，剁椒',1,'2022-06-10 10:28:54','2022-06-10 10:28:54',1,1),
	 ('金汤酸菜牛蛙',17,88.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/7694a5d8-7938-4e9d-8b9e-2075983a2e38.png','原料：鲜活牛蛙，酸菜',1,'2022-06-10 10:33:05','2022-06-10 10:33:05',1,1),
	 ('馋嘴牛蛙',17,88.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/7a55b845-1f2b-41fa-9486-76d187ee9ee1.png','配料：鲜活牛蛙，丝瓜，黄豆芽',1,'2022-06-10 10:37:52','2022-06-10 10:37:52',1,1),
	 ('草鱼2斤',16,68.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/b544d3ba-a1ae-4d20-a860-81cb5dec9e03.png','原料：草鱼，黄豆芽，莲藕',1,'2022-06-10 10:41:08','2022-06-10 10:41:08',1,1),
	 ('江团鱼2斤',16,119.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/a101a1e9-8f8b-47b2-afa4-1abd47ea0a87.png','配料：江团鱼，黄豆芽，莲藕',1,'2022-06-10 10:42:42','2022-06-10 10:42:42',1,1);
INSERT INTO reggie.dish (name,category_id,price,image,description,status,create_time,update_time,create_user,update_user) VALUES
	 ('鮰鱼2斤',16,72.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/8cfcc576-4b66-4a09-ac68-ad5b273c2590.png','原料：鮰鱼，黄豆芽，莲藕',1,'2022-06-10 10:43:56','2022-06-10 10:43:56',1,1),
	 ('鸡蛋汤',21,4.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/c09a0ee8-9d19-428d-81b9-746221824113.png','配料：鸡蛋，紫菜',1,'2022-06-10 10:54:25','2022-06-10 10:54:25',1,1),
	 ('平菇豆腐汤',21,6.00,'https://reggie-itcast.oss-cn-beijing.aliyuncs.com/16d0a3d6-2253-4cfc-9b49-bf7bd9eb2ad2.png','配料：豆腐，平菇',1,'2022-06-10 10:55:02','2022-06-10 10:55:02',1,1),
	 ('酸菜鱼',23,1500.00,'30837420-d54a-4297-8024-eb64574dca91.png','菜品描述',1,'2022-12-21 17:24:03','2022-12-22 12:02:57',1,1);

	
INSERT INTO reggie.dish_flavor (dish_id,name,value) VALUES
	 (10,'甜味','["无糖","少糖","半糖","多糖","全糖"]'),
	 (7,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]'),
	 (7,'温度','["热饮","常温","去冰","少冰","多冰"]'),
	 (6,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]'),
	 (6,'辣度','["不辣","微辣","中辣","重辣"]'),
	 (5,'辣度','["不辣","微辣","中辣","重辣"]'),
	 (5,'甜味','["无糖","少糖","半糖","多糖","全糖"]'),
	 (2,'甜味','["无糖","少糖","半糖","多糖","全糖"]'),
	 (4,'甜味','["无糖","少糖","半糖","多糖","全糖"]'),
	 (3,'甜味','["无糖","少糖","半糖","多糖","全糖"]');
INSERT INTO reggie.dish_flavor (dish_id,name,value) VALUES
	 (3,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]'),
	 (52,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]'),
	 (52,'辣度','["不辣","微辣","中辣","重辣"]'),
	 (51,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]'),
	 (51,'辣度','["不辣","微辣","中辣","重辣"]'),
	 (53,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]'),
	 (53,'辣度','["不辣","微辣","中辣","重辣"]'),
	 (54,'忌口','["不要葱","不要蒜","不要香菜"]'),
	 (56,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]'),
	 (57,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]');
INSERT INTO reggie.dish_flavor (dish_id,name,value) VALUES
	 (60,'忌口','["不要葱","不要蒜","不要香菜","不要辣"]'),
	 (66,'辣度','["不辣","微辣","中辣","重辣"]'),
	 (67,'辣度','["不辣","微辣","中辣","重辣"]'),
	 (65,'辣度','["不辣","微辣","中辣","重辣"]'),
	 (70,'忌口','["不要葱","不要蒜"]'),
	 (70,'温度','["热饮"]'),
	 (70,'辣度','["重辣"]');

	
	
INSERT INTO reggie.employee (name,username,password,phone,sex,id_number,status,create_time,update_time,create_user,update_user) VALUES
	 ('管理员','admin','e10adc3949ba59abbe56e057f20f883e','13812312312','1','110101199001010047',1,'2022-02-15 15:51:20','2022-02-17 09:16:20',10,1),
	 ('张三','zhangsan','e10adc3949ba59abbe56e057f20f883e','18345678910','1','123456789987654231',0,'2022-12-20 18:47:35','2022-12-20 21:06:47',1,1),
	 ('李四','lisi','e10adc3949ba59abbe56e057f20f883e','18345678109','0','183456781093219867',1,'2022-12-20 20:44:50','2022-12-20 21:08:39',1,1),
	 ('王五','wangwu','e10adc3949ba59abbe56e057f20f883e','18398765432','1','123456789123456789',1,'2022-12-20 21:27:06','2022-12-20 21:27:06',1,1),
	 ('赵溜','zhaoliu','e10adc3949ba59abbe56e057f20f883e','18343219876','1','987612345123456789',1,'2022-12-20 21:27:44','2022-12-21 14:33:34',1,1),
	 ('田七','tianqi','e10adc3949ba59abbe56e057f20f883e','18312234556','1','123456788123456788',1,'2022-12-21 09:02:45','2022-12-21 10:24:38',1,1),
	 ('周八','zhouba','e10adc3949ba59abbe56e057f20f883e','18333388891','1','987123456123498765',1,'2022-12-21 09:10:54','2022-12-21 10:06:26',1,1);

	
	
INSERT INTO reggie.order_detail (name,image,order_id,dish_id,setmeal_id,dish_flavor,`number`,amount) VALUES
	 ('梅菜扣肉','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/6080b118-e30a-4577-aab4-45042e3f88be.png',1606357097850728450,60,NULL,'不要蒜',1,0.58),
	 ('北冰洋','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/4451d4be-89a2-4939-9c69-3a87151cb979.png',1606357097850728450,47,NULL,NULL,2,0.04),
	 ('测试套餐C','c894b95c-86f9-4b20-920b-1c9215617e0a.png',1606357097850728450,NULL,35,NULL,2,12.00);

	
	
INSERT INTO reggie.orders (`number`,status,user_id,address_book_id,order_time,checkout_time,pay_method,pay_status,amount,remark,phone,address,user_name,consignee,cancel_reason,rejection_reason,cancel_time,estimated_delivery_time,delivery_status,delivery_time,pack_amount,tableware_number,tableware_status) VALUES
	 ('1606357097850728450',2,5,5,'2022-12-23 18:32:31','2022-12-23 18:32:31',1,0,24.00,'24.66','13814798573','北京市收货地址公司',NULL,'王五',NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,1);

	
INSERT INTO reggie.setmeal (category_id,name,price,status,description,image,create_time,update_time,create_user,update_user) VALUES
	 (15,'商务套餐A',20.00,1,'','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/21a5ed3a-97f6-447a-af9d-53deabfb5661.png','2022-06-10 10:58:09','2022-06-10 10:58:09',1,1),
	 (15,'商务套餐B',22.00,1,'','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/8d0075f8-9008-4390-94ca-2ca631440304.png','2022-06-10 11:00:13','2022-06-10 11:11:37',1,1),
	 (15,'商务套餐C',24.00,1,'','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/8979566b-0e17-462b-81d8-8dbace4138f4.png','2022-06-10 11:11:23','2022-06-10 11:11:23',1,1),
	 (15,'商务套餐Z',10000.00,1,'商务套餐Z','3ced043d-66d6-4d97-bcbf-3abe3a8831a2.png','2022-12-22 14:22:01','2022-12-22 14:58:38',1,1),
	 (13,'测试套餐C',1200.00,1,'测试套餐C','c894b95c-86f9-4b20-920b-1c9215617e0a.png','2022-12-22 15:47:00','2022-12-22 15:47:00',1,1),
	 (13,'测试套餐Y',1100.00,1,'测试套餐Y','734637c5-3b3f-465b-9c73-27399b119b56.png','2022-12-22 15:50:37','2022-12-22 15:50:37',1,1);

	
INSERT INTO reggie.setmeal_dish (setmeal_id,dish_id,name,price,copies) VALUES
	 ('29','54','清炒小油菜',18.00,1),
	 ('29','49','米饭',2.00,1),
	 ('29','68','鸡蛋汤',4.00,1),
	 ('31','56','清炒西兰花',18.00,1),
	 ('31','49','米饭',2.00,1),
	 ('31','68','鸡蛋汤',4.00,1),
	 ('30','69','平菇豆腐汤',6.00,1),
	 ('30','49','米饭',2.00,1),
	 ('30','55','蒜蓉娃娃菜',18.00,1),
	 ('32','70','酸菜鱼',1500.00,1);
INSERT INTO reggie.setmeal_dish (setmeal_id,dish_id,name,price,copies) VALUES
	 ('32','49','米饭',2.00,1),
	 ('32','48','雪花啤酒',4.00,1),
	 ('35','51','老坛酸菜鱼',56.00,1),
	 ('35','49','米饭',2.00,1),
	 ('35','46','王老吉',6.00,1),
	 ('37','70','酸菜鱼',1500.00,1),
	 ('37','50','馒头',1.00,1),
	 ('37','68','鸡蛋汤',4.00,1);

	
INSERT INTO reggie.shopping_cart (name,image,user_id,dish_id,setmeal_id,dish_flavor,`number`,amount,create_time) VALUES
	 ('江团鱼2斤','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/a101a1e9-8f8b-47b2-afa4-1abd47ea0a87.png',4,66,NULL,'重辣',1,1.19,'2022-12-23 15:11:17'),
	 ('清蒸鲈鱼','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png',4,58,NULL,NULL,1,0.98,'2022-12-23 15:11:19'),
	 ('酸菜鱼','30837420-d54a-4297-8024-eb64574dca91.png',1,70,NULL,'不要葱,热饮,重辣',1,15.00,'2022-12-23 21:33:48'),
	 ('清蒸鲈鱼','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/c18b5c67-3b71-466c-a75a-e63c6449f21c.png',2,58,NULL,NULL,2,0.98,'2022-12-28 22:41:34'),
	 ('蒜蓉娃娃菜','https://reggie-itcast.oss-cn-beijing.aliyuncs.com/4879ed66-3860-4b28-ba14-306ac025fdec.png',2,55,NULL,NULL,5,0.18,'2022-12-28 22:58:11'),
	 ('测试套餐Y','734637c5-3b3f-465b-9c73-27399b119b56.png',2,NULL,37,NULL,2,11.00,'2022-12-29 14:26:50');

	
INSERT INTO reggie.`user` (openid,name,phone,sex,id_number,avatar,create_time) VALUES
	 (NULL,NULL,'183987654321',NULL,NULL,NULL,NULL),
	 (NULL,NULL,'183987654312',NULL,NULL,NULL,NULL),
	 (NULL,NULL,'183987654322',NULL,NULL,NULL,NULL),
	 (NULL,NULL,'13814798573',NULL,NULL,NULL,NULL);

	
INSERT INTO reggie.`user` (openid,name,phone,sex,id_number,avatar,create_time) VALUES
	 (NULL,NULL,'183987654321',NULL,NULL,NULL,NULL),
	 (NULL,NULL,'183987654312',NULL,NULL,NULL,NULL),
	 (NULL,NULL,'183987654322',NULL,NULL,NULL,NULL),
	 (NULL,NULL,'13814798573',NULL,NULL,NULL,NULL);
