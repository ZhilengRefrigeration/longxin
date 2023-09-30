

# 一、数据集概述

本数据集仅用于《B2-基于龙芯平台的电商用户大数据分析》的数据分析部分功能的开发，数据集经过脱敏处理，包含了用户行为、商品信息、用户信息共三部分数据。

本数据集包含了如下文件：

| **文件名称**     | **说明**     | **包含特征**                                    |
| ---------------- | ------------ | ----------------------------------------------- |
| **sku**      | 商品信息数据 | 商品ID、价格                                    |
| **user_act** | 用户行为数据 | 行为日期、行为时间、   用户ID、行为类型、商品ID |
| **user**     | 用户信息数据 | 用户ID、所在城市、生日、年龄                    |
| **user_login**     | 用户登录信息 | 用户登录需要的用户名，密码                    |


# 二、字段介绍

**user_act**

该文件包含了约160万随机用户产生的用户行为（行为包括浏览、下单、关注、评论、加购、咨询客服、投诉），即数据集的每一行表示一条用户行为，由模块ID、用户ID、商品ID、
 行为类型、行为日期、行为时间组成，并以逗号分隔。关于数据集中每一列的详细描述如下：

 

| **字段名**   | **类型** | **字段含义**                                                 |
| ------------ | -------- | ------------------------------------------------------------ |
| **act_date** | str      | 行为发**生的**日期（年/月/日）                               |
| **act_time** | str      | 行为发生的时间（时:分:秒）                                   |
| **act_user** | int      | 序列化的用户唯一标识（数据已脱敏）                           |
| **act_type** | int      | 行为类型：   1.浏览；2.下单；3.关注；4.评论；   5.加入购物车；6.咨询客服；7.投诉； |
| **act_sku**  | int      | 序列化的商品唯一标识（数据已脱敏）                           |

浏览后下单的用户和商品

| 106241 | 136181 | 1    | 1    |
| ------ | ------ | ---- | ---- |
| 106241 | 136181 | 2    | 0    |

 创建临时表

```sql
CREATE TABLE `act_type_1_user` (
  `act_user` INT(11) NULL DEFAULT NULL,
  `act_sku` INT(11) NULL DEFAULT NULL,
  `act_count` INT(11) NULL DEFAULT NULL
) COLLATE = 'utf8_bin' ENGINE = InnoDB AUTO_INCREMENT = 2;
```

```sql
//插入目标用户（浏览+下单）
INSERT INTO act_type_1_user select act_user,act_sku,count(*) as act_count from user_act2 group by act_user,act_sku HAVING count(*) =2;

//插入只浏览的用户
INSERT INTO act_type_1_user select act_user,act_sku,count(*) as act_count from user_act2 group by act_user,act_sku HAVING count(*) =1;
```

```sql
//其他sql 草稿
-- INSERT INTO user_act2 
-- SELECT distinct * FROM user_act where act_type in (1,2);

-- select DISTINCT act_type from user_act2;

-- 同一个用户，同一个商品，具备2种行为的记录
-- select act_user,act_sku,count(*) as 目标用户 from user_act2 group by act_user,act_sku HAVING count(*) =2

-- 保存到新表中
INSERT INTO act_type_1_user select act_user,act_sku,count(*) as act_count from user_act2 group by act_user,act_sku HAVING count(*) =1;

```

计算用户浏览商品的数量

和浏览+下单的数量

```sql
-- 计算用户浏览商品数量 88079
-- select count(*) from act_type_1_user;

-- 计算用户浏览和下单商品的数量 450
select count(*) from act_type_2_user;
```



 

**user**

该文件包含了大约160万随机用户的用户信息，数据已脱敏。包含了用户的用户ID、用户、用户所在城市、用户的生日和用户性别。

| **字段名**   | **类型** | **字段含义**                             |
| ------------ | -------- | ---------------------------------------- |
| **id**       | int      | 序列化的用户唯一标识（数据已脱敏）。     |
| **address**  | str      | 用户所在城市，精确到所在的市、县。       |
| **birthday** | str      | 用户生日（年/月/日）                     |
| **gender**   | str      | 用户性别：  女性为female；  男性为male。 |

 

**sku**

该文件包含了大约37万商品信息，数据已脱敏。包含了商品ID、商品价格。

| **字段名** | **类型** | **字段含义**                       |
| ---------- | -------- | ---------------------------------- |
| **id**     | int      | 序列化的商品唯一标识（数据已脱敏） |
| **price**  | float    | 商品价格，单位为元。               |



**user_login**

该文件包含了用户登录需要的信息

| **字段名** | **类型** | **字段含义**                       |
| ---------- | -------- | ---------------------------------- |
| **userId**     | int      | 用户唯一标识 |
| **userName**  | varchar    | 用户名  |
| **password**  | varchar    | 密码  |



 

数据集大小说明如下：

| **维度**         | **数量**   |
| ---------------- | ---------- |
| **用户数量**     | 1,608,708  |
| **商品数量**     | 379,458    |
| **所有行为数量** | 37,207,644 |

 
