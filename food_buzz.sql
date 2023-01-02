CREATE DATABASE `food_buzz` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

-- food_buzz.`user` definition

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `fullname` varchar(70) DEFAULT NULL COMMENT '全名',
  `first_name` varchar(35) DEFAULT NULL COMMENT '名字',
  `last_name` varchar(35) DEFAULT NULL COMMENT '姓氏',
  `phone_number` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '手机号',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别 0: 男 1: 女',
  `id_number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `varchar` varchar(500) DEFAULT NULL COMMENT '头像',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `status` tinyint(1) DEFAULT '1' COMMENT '用户状态 0: 禁用 1: 启用',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0: 否 1: 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

-- food_buzz.employee definition

CREATE TABLE `employee` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '用户名',
  `password` varchar(32) DEFAULT NULL COMMENT '密码',
  `fullname` varchar(70) DEFAULT NULL COMMENT '全名',
  `first_name` varchar(35) DEFAULT NULL COMMENT '名字',
  `last_name` varchar(35) DEFAULT NULL COMMENT '姓氏',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别 0: 男 1: 女',
  `id_number` varchar(18) DEFAULT NULL COMMENT '身份证号',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态 0: 禁用 1: 启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0: 否 1: 是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `employee_un` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工表';

-- food_buzz.address_book definition

CREATE TABLE `address_book` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT 'user id',
  `consignee` varchar(70) DEFAULT NULL COMMENT '收货人',
  `gender` tinyint(1) DEFAULT NULL COMMENT '性别 0: 男 1: 女',
  `phone_number` varchar(15) DEFAULT NULL COMMENT '手机号',
  `province_name` varchar(32) DEFAULT NULL COMMENT '省级名称',
  `city_name` varchar(32) DEFAULT NULL COMMENT '市级名称',
  `district_name` varchar(32) DEFAULT NULL COMMENT '区级名称',
  `detail` varchar(255) DEFAULT NULL COMMENT '详细地址',
  `postcode` varchar(8) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '邮编',
  `is_default` tinyint(1) DEFAULT '0' COMMENT '默认地址 0: 否 1: 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='送货地址表';

-- food_buzz.category definition

CREATE TABLE `category` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '分类名称',
  `type` tinyint(1) DEFAULT NULL COMMENT '分类类型 0: 菜品分类 1: 套餐分类',
  `status` tinyint(1) DEFAULT '1' COMMENT '分类状态 0: 禁用 1: 启用',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  `description` varchar(500) DEFAULT NULL COMMENT '描述信息',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0: 否 1: 是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `category_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='分类表';

-- food_buzz.dish definition

CREATE TABLE `dish` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '菜品名称',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `price` decimal(10,2) NOT NULL COMMENT '菜品价格, 0.00',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `description` varchar(500) DEFAULT NULL COMMENT '描述信息',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态 0: 停售 1: 起售',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0: 否 1: 是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dish_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜品表';

-- food_buzz.dish_flavor definition

CREATE TABLE `dish_flavor` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `dish_id` bigint NOT NULL COMMENT '菜品id',
  `name` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '口味名称',
  `value` varchar(500) DEFAULT NULL COMMENT '口味数据list',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0: 否 1: 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜品口味表';

-- food_buzz.meal_deal definition

CREATE TABLE `meal_deal` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `category_id` bigint NOT NULL COMMENT '菜品分类id',
  `name` varchar(64) NOT NULL COMMENT '套餐名称',
  `price` decimal(10,2) NOT NULL COMMENT '套餐价格',
  `status` tinyint(1) DEFAULT '1' COMMENT '状态 0: 停售 1: 起售',
  `image` varchar(255) DEFAULT NULL COMMENT '图片',
  `description` varchar(500) DEFAULT NULL COMMENT '描述信息',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `create_user` bigint DEFAULT NULL COMMENT '创建人',
  `update_user` bigint DEFAULT NULL COMMENT '修改人',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0: 否 1: 是',
  PRIMARY KEY (`id`),
  UNIQUE KEY `meal_deal_un` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='套餐表';

-- food_buzz.meal_deal_dish definition

CREATE TABLE `meal_deal_dish` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `meal_deal_id` bigint NOT NULL COMMENT '套餐id',
  `dish_id` bigint NOT NULL COMMENT '菜品id',
  `amount` int DEFAULT NULL COMMENT '菜品份数',
  `is_deleted` tinyint(1) DEFAULT '0' COMMENT '逻辑删除 0: 否 1: 是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='套餐菜品表';

-- food_buzz.shopping_cart definition

CREATE TABLE `shopping_cart` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `meal_deal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `amount` int NOT NULL DEFAULT '1' COMMENT '数量',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `dish_flavor` varchar(500) DEFAULT NULL COMMENT '口味数据list',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='购物车表';

-- food_buzz.`order` definition

CREATE TABLE `order` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_number` varchar(50) DEFAULT NULL COMMENT '订单号',
  `status` tinyint(1) DEFAULT NULL COMMENT '订单状态 0: 待付款 1: 待接单 2: 已接单 3: 派送中 4: 已完成 5: 已取消 6: 已退款',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `address_book_id` bigint NOT NULL COMMENT '地址id',
  `order_time` datetime NOT NULL COMMENT '下单时间',
  `pay_time` datetime DEFAULT NULL COMMENT '结账时间',
  `cancel_time` datetime DEFAULT NULL COMMENT '取消时间',
  `estimated_delivered_time` datetime DEFAULT NULL COMMENT '预期送达时间',
  `delivered_time` datetime DEFAULT NULL COMMENT '送达时间',
  `pay_method` tinyint(1) NOT NULL COMMENT '支付方式 0: 微信 1: 支付宝',
  `pay_status` tinyint(1) NOT NULL COMMENT '支付状态 0: 未支付 1: 已支付 2: 退款',
  `remark` varchar(255) DEFAULT NULL COMMENT '订单备注',
  `cancel_reason` varchar(255) DEFAULT NULL COMMENT '订单取消原因',
  `rejection_reason` varchar(255) DEFAULT NULL COMMENT '订单拒绝原因',
  `tableware_amount` int DEFAULT NULL COMMENT '餐具数量',
  `tableware_status` tinyint(1) DEFAULT NULL COMMENT '餐具数量状态 0: 按餐量提供 1: 根据具体数量',
  `pack_price` decimal(10,2) DEFAULT NULL COMMENT '打包费',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';

-- food_buzz.order_detail definition

CREATE TABLE `order_detail` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `order_id` bigint NOT NULL COMMENT '订单id',
  `dish_id` bigint DEFAULT NULL COMMENT '菜品id',
  `meal_deal_id` bigint DEFAULT NULL COMMENT '套餐id',
  `amount` int NOT NULL COMMENT '数量',
  `price` decimal(10,2) NOT NULL COMMENT '金额',
  `dish_flavor` varchar(500) DEFAULT NULL COMMENT '口味数据list',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单信息表';