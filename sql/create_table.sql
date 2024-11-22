# 数据库初始化

-- 创建库
create database if not exists my_db;

-- 切换库
use my_db;

-- 用户表
create table if not exists user
(
    id           bigint auto_increment comment 'id' primary key,
    userAccount  varchar(256)                           not null comment '账号',
    userPassword varchar(512)                           not null comment '密码',
    unionId      varchar(256)                           null comment '微信开放平台id',
    mpOpenId     varchar(256)                           null comment '公众号openId',
    userName     varchar(256)                           null comment '用户昵称',
    userAvatar   varchar(1024)                          null comment '用户头像',
    userProfile  varchar(512)                           null comment '用户简介',
    userRole     varchar(256) default 'user'            not null comment '用户角色：user/admin/ban',
    createTime   datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime   datetime     default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete     tinyint      default 0                 not null comment '是否删除',
    index idx_unionId (unionId)
) comment '用户' collate = utf8mb4_unicode_ci;

-- 帖子表
create table if not exists post
(
    id         bigint auto_increment comment 'id' primary key,
    title      varchar(512)                       null comment '标题',
    content    text                               null comment '内容',
    tags       varchar(1024)                      null comment '标签列表（json 数组）',
    thumbNum   int      default 0                 not null comment '点赞数',
    favourNum  int      default 0                 not null comment '收藏数',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除',
    index idx_userId (userId)
) comment '帖子' collate = utf8mb4_unicode_ci;

-- 帖子点赞表（硬删除）
create table if not exists post_thumb
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子点赞';

-- 帖子收藏表（硬删除）
create table if not exists post_favour
(
    id         bigint auto_increment comment 'id' primary key,
    postId     bigint                             not null comment '帖子 id',
    userId     bigint                             not null comment '创建用户 id',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    index idx_postId (postId),
    index idx_userId (userId)
) comment '帖子收藏';

-- (预约表)id 景区 预约须知
-- （预约时间表）id 景区id 日期 时间段 剩余数
-- （预约信息） id 预约时间表id 用户id   姓名 证件类别 证件号


-- 预约表
create table if not exists reservations
(
    id         bigint auto_increment comment 'id' primary key,
    scenicName      bigint                         null comment '景区名称',
    instructions    text                               null comment '预约须知',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '预约表' collate = utf8mb4_unicode_ci;

-- 预约时间表
create table if not exists reservations_time
(
    id         bigint auto_increment comment 'id' primary key,
    reservationsId      bigint                         null comment '预约表 id',
    stock      bigint                         null comment '库存数量',
    instructions    text                               null comment '预约须知',
    openDateTime datetime default CURRENT_TIMESTAMP not null comment '开放日期',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '预约时间表' collate = utf8mb4_unicode_ci;

-- 预约用户信息表
create table if not exists reservations_time_travelers
(
    id         bigint auto_increment comment 'id' primary key,
    userId     bigint                             not null comment '创建用户 id',
    reservationsId bigint                            null comment '预约表 id',
    reservationsTimeId      bigint                         null comment '预约时间表 id',
    fullName      varchar(512)                       null comment '姓名',
    idNumber     varchar(512)                       null comment '身份证',
    phoneNumber varchar(512)                       null comment '手机号',
    createTime datetime default CURRENT_TIMESTAMP not null comment '创建时间',
    updateTime datetime default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间',
    isDelete   tinyint  default 0                 not null comment '是否删除'
) comment '预约用户信息表' collate = utf8mb4_unicode_ci;


-- 门票景点表  id 轮播图 景点名称 tag 开放时间 联系电话 地址 详情 价格 库存 价格
-- 订单表  id orderid 门票景点id 订单状态
-- 门票
create table if not exists TicketScenic
(
    id              bigint auto_increment comment 'id' primary key,
    carouselImages  varchar(1024)          null comment '轮播图（JSON数组或逗号分隔的字符串）',
    scenicName      varchar(255)           not null comment '景点名称',
    tags            varchar(255)           null comment '标签（逗号分隔）',
    openingHours    varchar(255)           null comment '开放时间',
    contactNumber   varchar(20)            null comment '联系电话',
    address         varchar(255)           null comment '地址',
    details         text                   null comment '详情',
    price           decimal(10, 2)         not null comment '价格',
    stock           int                    not null comment '库存数量'
) comment '门票景点表' collate = utf8mb4_unicode_ci;

create table if not exists Orders
(
    id             bigint auto_increment comment 'id' primary key,
    orderId        varchar(50)            not null comment '订单号',
    userId         bigint                 not null comment '创建用户 id',
    ticketScenicId bigint                 not null comment '门票景点id',
    orderStatus    varchar(50)            not null comment '订单状态',
    purchaseDate   datetime default CURRENT_TIMESTAMP not null comment '购买时间',
    quantity       int                    not null comment '购买数量',
    totalPrice     decimal(10, 2)         not null comment '总价格'
) comment '订单表' collate = utf8mb4_unicode_ci;

create table if not exists QrCodeVerification
(
    id                bigint auto_increment comment 'id' primary key,
    orderId           varchar(50)           not null comment '订单号',
    qrCode            varchar(255)          not null comment '核销二维码',
    isVerified        tinyint  default 0    not null comment '是否已核销',
    verificationDate  datetime default CURRENT_TIMESTAMP              null comment '核销时间'
) comment '二维码核销记录表' collate = utf8mb4_unicode_ci;
