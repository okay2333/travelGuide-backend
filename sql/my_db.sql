/*
 Navicat Premium Data Transfer

 Source Server         : localhost_3306
 Source Server Type    : MySQL
 Source Server Version : 50743
 Source Host           : localhost:3306
 Source Schema         : my_db

 Target Server Type    : MySQL
 Target Server Version : 50743
 File Encoding         : 65001

 Date: 23/05/2025 22:53:14
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for article
-- ----------------------------
DROP TABLE IF EXISTS `article`;
CREATE TABLE `article`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL,
  `cover` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL,
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1863938424050937859 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of article
-- ----------------------------
INSERT INTO `article` VALUES (1863876223109808130, '泉州旅游攻略丨三日行程推荐必看收藏攻略', 'http://sr3ylefaw.hd-bkt.clouddn.com/28514a20-0043-4d9a-bc6d-a719512b7b45.jpg', '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">🏨：住宿： 优先：选择西街附近，浦西万达附近比较好哈 其次：西湖公园，泉秀路，兴贤路附近 最后：晋江阳光夜市，晋江万达附近 </span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">🦆：特产： 姜母鸭：可以选择斯丹或者张林可以邮寄的 芋头饼：庄阿姨家的比较好带 其他：本地超市：永相逢（24小时营业），中闽百汇 </span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">🍗：美食推荐这三天吃： 第一天：斯丹姜母鸭➕吴氏麻糍➕泮宫卤面➕壶见 第二天：国仔面线糊➕候阿婆肉粽➕叶叔醋肉➕莓超 第三天：歪头牛肉➕阳光夜市 </span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">🚌：出行： 第一天：全程小白公交车（一个人两块钱） 第二天：打车 第三天：打车 </span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">🌷：这三天的行程一般是早上10点出发哈～ 根据顺序来玩，时间会比较合理安排哦</span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">❗❗❗ 寺庙博物馆大部分下午17点多关门哈！！！！</span></p><p><img src=\"http://smtie74t7.hn-bkt.clouddn.com/150c32ea-e524-49cf-b69f-ba999c42f1dc\" alt=\"\" data-href=\"\" style=\"\"/></p>', '2024-12-03 17:21:35', '2024-12-03 20:50:39');
INSERT INTO `article` VALUES (1863930676768784386, '泉州旅游攻略，超全懒人包，速速拿下！！', 'http://sr3ylefaw.hd-bkt.clouddn.com/b617ad23-7091-4f38-b420-9f98c2fff136.jpg', '<p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">哈喽~各位宝子们！五一打算去哪里玩了嘛？</span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">泉州簪花也是不错选择哦~ 一、关于泉州交通（仅供参考） </span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">🚄选择高铁出行： </span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">✅要选择泉州站‼️因为比较靠近市区‼️ </span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">❎⭕️南站在晋江⭕️东站在惠安</span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">❎两个站都距离是市区大概一小时车程 ✈️选择飞机出行 </span></p><p><span style=\"color: rgb(51, 51, 51); background-color: rgb(255, 255, 255); font-size: 16px;\">✅认准晋江机场 ⭕️距离泉州市区｜20分钟 ⭕️14公里路程｜滴滴大概40+💰</span></p><p><br></p>', '2024-12-03 20:57:58', '2024-12-03 20:57:58');
INSERT INTO `article` VALUES (1863938424050937858, '谁懂啊…被自己做的泉州攻略满意得睡不着', 'http://sr3ylefaw.hd-bkt.clouddn.com/468d26af-b44c-49c6-986a-e6bfffdf610b.jpg', '<p>宝子们呀😘，听我一句劝呀，去泉州那可一定要做好攻略再去呢，不然真的超容易踩雷的呀❌！不过别担心啦，我可是熬夜给大家整理了超全的攻略哦，快存下来慢慢看吧，保准能让你们的泉州之行顺顺利利、开开心心哒🥰 - 🌈交通 🔥泉州没有地铁，出行主要是公交跟打车，古城区的出行推J乘坐小白车哦 🔥穷游姐妹可以合理利用这个方式在泉州想zu小电驴的话就找正规的zu车行，多比价 - 🌟景点介绍 📍开元寺：福建省规模最大的佛教寺院，寺内有东西两塔，是我国最高的一对石塔 📍西街：千年历史古城，曾是泉州的经济、政治、文化繁华中心，适合逛吃拍照 📍钟楼：在泉州市中心，与开元寺很近 📍关岳庙：近千年历史，庙内主关羽，泉州香火最旺的寺庙 📍蟳埔村：蟳埔女戴簪花圈，是一个充满历史韵味和文化魅力的古渔村 📍黄金海岸：天然的金黄色沙滩，沙子超细软 📍洛伽寺：海上生寺庙出自此处，庙里看海，一面佛国，一面人间，一座依海而建的寺庙 📍红塔湾：适合看日出日落的海边沙滩 📍观音山：与黄金海岸相连</p>', '2024-12-03 21:28:45', '2024-12-03 21:28:45');

-- ----------------------------
-- Table structure for comment
-- ----------------------------
DROP TABLE IF EXISTS `comment`;
CREATE TABLE `comment`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `postId` bigint(20) NULL DEFAULT NULL,
  `userId` bigint(20) NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `parentId` bigint(20) NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  `updateTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 20 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of comment
-- ----------------------------
INSERT INTO `comment` VALUES (18, 1877262489637478401, 1828339688465825795, '太好看了', NULL, '2025-01-09 16:14:14', '2025-01-09 16:14:14');
INSERT INTO `comment` VALUES (19, 1877262489637478401, 1828339688465825796, '需要预约吗', 18, '2025-01-09 16:18:37', '2025-01-09 16:18:37');

-- ----------------------------
-- Table structure for feedback
-- ----------------------------
DROP TABLE IF EXISTS `feedback`;
CREATE TABLE `feedback`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `content` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `status` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '0未处理 1已处理',
  `userId` bigint(50) NULL DEFAULT NULL,
  `fullName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `phoneNumber` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL,
  `createTime` datetime NULL DEFAULT NULL,
  `updateTime` datetime NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of feedback
-- ----------------------------
INSERT INTO `feedback` VALUES (4, '界面与数据显示异常', '显示为空白或破碎的图标，文字排版也出现错乱', '0', 1828339688465825793, '赵不实', '13234567890', '2025-01-02 16:45:04', '2025-01-02 16:45:07');
INSERT INTO `feedback` VALUES (5, '订单提交失败', '页面长时间处于加载状态，最终订单未成功提交，但系统未给出任何明确的失败原因提示', '0', 1828339688465825793, '赵不实', '13234567890', '2025-01-02 16:45:11', '2025-01-02 16:45:13');

-- ----------------------------
-- Table structure for orders
-- ----------------------------
DROP TABLE IF EXISTS `orders`;
CREATE TABLE `orders`  (
  `id` bigint(50) NOT NULL COMMENT '订单号',
  `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
  `ticketId` bigint(20) NOT NULL COMMENT '门票景点id',
  `orderStatus` tinyint(10) NOT NULL COMMENT '订单状态 0未支付 1已支付 2取消订单',
  `purchaseDate` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '购买时间',
  `quantity` int(11) NOT NULL COMMENT '购买数量',
  `totalPrice` decimal(10, 2) NOT NULL COMMENT '总价格'
) ENGINE = InnoDB CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '订单表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of orders
-- ----------------------------
INSERT INTO `orders` VALUES (1877266548524433408, 1828339688465825795, 17, 1, '2025-01-09 16:09:57', 2, 40.00);
INSERT INTO `orders` VALUES (1877269026464710656, 1828339688465825796, 17, 1, '2025-01-09 16:19:48', 1, 20.00);
INSERT INTO `orders` VALUES (1877271681702412288, 1828339688465825797, 18, 1, '2025-01-09 16:30:21', 1, 20.00);
INSERT INTO `orders` VALUES (1877271826644975616, 1828339688465825793, 21, 1, '2025-01-09 16:30:56', 1, 30.00);
INSERT INTO `orders` VALUES (1877520563723862016, 1828339688465825797, 17, 1, '2025-01-10 08:59:19', 1, 20.00);
INSERT INTO `orders` VALUES (1877524766508744704, 1828339688465825797, 18, 1, '2025-01-10 09:16:01', 1, 20.00);
INSERT INTO `orders` VALUES (1877569493383806976, 1828339688465825793, 17, 1, '2025-01-10 12:13:45', 1, 20.00);

-- ----------------------------
-- Table structure for payment
-- ----------------------------
DROP TABLE IF EXISTS `payment`;
CREATE TABLE `payment`  (
  `id` int(50) NOT NULL AUTO_INCREMENT,
  `orderId` bigint(50) NULL DEFAULT NULL,
  `paymentMethod` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `paymentStatus` tinyint(10) NULL DEFAULT NULL,
  `paymentAmount` decimal(10, 2) NULL DEFAULT NULL,
  `paymentTime` datetime NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 91 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of payment
-- ----------------------------
INSERT INTO `payment` VALUES (72, 1875809677141614592, 'wechat', 1, 20.00, '2025-01-05 15:40:52');
INSERT INTO `payment` VALUES (73, 1875811618596855808, 'wechat', 0, 40.00, '2025-01-05 15:48:35');
INSERT INTO `payment` VALUES (74, 1875813587893886976, 'wechat', 0, 20.00, '2025-01-05 15:56:24');
INSERT INTO `payment` VALUES (75, 1875809341224001536, 'wechat', 0, 20.00, '2025-01-05 15:56:56');
INSERT INTO `payment` VALUES (76, 1875809341224001536, 'wechat', 0, 20.00, '2025-01-05 15:57:02');
INSERT INTO `payment` VALUES (77, 1875814007898906624, 'wechat', 0, 40.00, '2025-01-05 15:58:05');
INSERT INTO `payment` VALUES (78, 1875814407003709440, 'wechat', 0, 100.00, '2025-01-05 15:59:40');
INSERT INTO `payment` VALUES (79, 1877215946821955584, 'wechat', 0, 1500.00, '2025-01-09 12:48:53');
INSERT INTO `payment` VALUES (80, 1877215946821955584, 'wechat', 0, 1500.00, '2025-01-09 12:49:19');
INSERT INTO `payment` VALUES (81, 1877223395012472832, 'wechat', 0, 0.00, '2025-01-09 13:18:29');
INSERT INTO `payment` VALUES (82, 1877223410527203328, 'wechat', 0, 0.00, '2025-01-09 13:18:32');
INSERT INTO `payment` VALUES (83, 1877223395012472832, 'wechat', 0, 0.00, '2025-01-09 13:32:19');
INSERT INTO `payment` VALUES (84, 1877266548524433408, 'wechat', 0, 40.00, '2025-01-09 16:09:57');
INSERT INTO `payment` VALUES (85, 1877269026464710656, 'wechat', 0, 20.00, '2025-01-09 16:19:48');
INSERT INTO `payment` VALUES (86, 1877271681702412288, 'wechat', 0, 20.00, '2025-01-09 16:30:21');
INSERT INTO `payment` VALUES (87, 1877271826644975616, 'wechat', 0, 30.00, '2025-01-09 16:30:56');
INSERT INTO `payment` VALUES (88, 1877520563723862016, 'wechat', 0, 20.00, '2025-01-10 08:59:19');
INSERT INTO `payment` VALUES (89, 1877524766508744704, 'wechat', 0, 20.00, '2025-01-10 09:16:01');
INSERT INTO `payment` VALUES (90, 1877569493383806976, 'wechat', 0, 20.00, '2025-01-10 12:13:45');

-- ----------------------------
-- Table structure for post
-- ----------------------------
DROP TABLE IF EXISTS `post`;
CREATE TABLE `post`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `covers` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '封面图片and视频（json 数组）',
  `title` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '内容',
  `tags` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签列表（json 数组）',
  `thumbNum` int(11) NOT NULL DEFAULT 0 COMMENT '点赞数',
  `favourNum` int(11) NOT NULL DEFAULT 0 COMMENT '收藏数',
  `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1877271413702909955 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '帖子' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post
-- ----------------------------
INSERT INTO `post` VALUES (1877262489637478401, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/bb0b4762-c6f9-41a6-be84-fd0a285d651c.jpg\",\"http://sr3ylefaw.hd-bkt.clouddn.com/060b1d05-113a-4cd5-ad13-43665a23f9ec.jpg\"]', '泉州！！不宣传五店市你糊涂啊！！！', '感谢本地人带路\n来泉州的话一定要留半天的时间来这里！！！\n绿植hin治愈！很适合一个人散散步！！\n而且人少景美！不像西街那么人挤人！车也没地方停\n它已经1300多年的历史了！很多宗祠、寺庙、民居、商铺都是历经了明、清、民国三个时期还得以保存的！建筑都是闽南红砖古厝！是只有泉州这里才有的！独特的建筑风格！好像《西红柿首富》也是在这里取景的！真的很值得来慢悠悠地闲逛一下午～很惬意！！', '[\"晋江\"]', 0, 4, 1828339688465825795, '2025-01-09 15:53:50', '2025-01-26 21:29:14', 0);
INSERT INTO `post` VALUES (1877262854961356802, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/f446da13-f1f3-413e-a07b-2cfa08b51f68.jpg\"]', '真的很会选位置放啊泉州！', 'Citywalk一圈 把新放的三只小蛇全打卡了～\n比吉祥物本身更妙的是选的位置\n关岳庙前的鲤鲤 状元街的丝丝 威远楼的海海\n每个都和后面的建筑完美融合了！', '[]', 0, 2, 1828339688465825795, '2025-01-09 15:55:17', '2025-01-25 20:31:51', 0);
INSERT INTO `post` VALUES (1877263123405201410, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/ed019e62-88fc-41db-9196-a367b1e286fe.jpg\",\"http://sr3ylefaw.hd-bkt.clouddn.com/d80cb642-a7dd-4cc7-ac86-366ecc5b0543.jpg\"]', '不如一起在泉州的街头走一走', '泉州之行可以说是迄今为止我最不特种兵的行程🧏🏻‍♀️\n身心放松，回到了我还没有成为牛马的时候', '[]', 0, 0, 1828339688465825795, '2025-01-09 15:56:21', '2025-01-25 20:32:07', 0);
INSERT INTO `post` VALUES (1877268388741505026, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/e16f4a7f-cc8d-41a0-96bc-e2e735ad0587.jpg\"]', '（福建泉州）🚄说走就走之动车出发⁉️巨新巨全', '找个小长假找个周末！说走就走', '[]', 0, 0, 1828339688465825796, '2025-01-09 16:17:16', '2025-01-25 22:34:56', 0);
INSERT INTO `post` VALUES (1877268656858193921, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/485adca8-d7a5-4295-98c4-a06f26a9ba8b.jpg\"]', '泉州｜遛娃不知道去哪里，那就来这里', '早上来的，人不多\n公园相对是安静的，有人散步有人晨跑有人遛娃\n最近打卡的一棵粉色异木棉也在这里（金洲旱闸进来可以看到）', '[]', 0, 0, 1828339688465825796, '2025-01-09 16:18:20', '2025-01-25 20:33:58', 0);
INSERT INTO `post` VALUES (1877271413702909954, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/5518d762-0725-4ee8-b464-f41d0ff2cb35.jpg\"]', '泉州时光隧道好魔幻', '今天途径泉州南安双溪村，走进了这个魔幻的隧道，我在这个地方看到了光，沿着轨道，散步在夕阳，好美。', '[]', 0, 0, 1828339688465825797, '2025-01-09 16:29:17', '2025-01-25 20:34:50', 0);

-- ----------------------------
-- Table structure for post_favour
-- ----------------------------
DROP TABLE IF EXISTS `post_favour`;
CREATE TABLE `post_favour`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `postId` bigint(20) NOT NULL COMMENT '帖子 id',
  `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_postId`(`postId`) USING BTREE,
  INDEX `idx_userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 55 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '帖子收藏' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_favour
-- ----------------------------
INSERT INTO `post_favour` VALUES (42, 1877262489637478401, 1828339688465825795, '2025-01-09 16:12:20', '2025-01-09 16:12:20');
INSERT INTO `post_favour` VALUES (43, 1877262489637478401, 1828339688465825796, '2025-01-09 16:18:44', '2025-01-09 16:18:44');
INSERT INTO `post_favour` VALUES (44, 1877262854961356802, 1828339688465825796, '2025-01-09 16:18:46', '2025-01-09 16:18:46');
INSERT INTO `post_favour` VALUES (46, 1877262854961356802, 1828339688465825793, '2025-01-10 11:42:53', '2025-01-10 11:42:53');
INSERT INTO `post_favour` VALUES (53, 1877262489637478401, 1828339688465825797, '2025-01-25 22:35:29', '2025-01-25 22:35:29');
INSERT INTO `post_favour` VALUES (54, 1877262489637478401, 1828339688465825793, '2025-01-26 21:29:14', '2025-01-26 21:29:14');

-- ----------------------------
-- Table structure for post_thumb
-- ----------------------------
DROP TABLE IF EXISTS `post_thumb`;
CREATE TABLE `post_thumb`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `postId` bigint(20) NOT NULL COMMENT '帖子 id',
  `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_postId`(`postId`) USING BTREE,
  INDEX `idx_userId`(`userId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci COMMENT = '帖子点赞' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of post_thumb
-- ----------------------------

-- ----------------------------
-- Table structure for qrcodeverification
-- ----------------------------
DROP TABLE IF EXISTS `qrcodeverification`;
CREATE TABLE `qrcodeverification`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `orderId` bigint(50) NOT NULL COMMENT '订单号',
  `qrCode` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '核销二维码',
  `isVerified` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否已核销',
  `verificationDate` datetime NULL DEFAULT CURRENT_TIMESTAMP COMMENT '核销时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 66 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '二维码核销记录表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of qrcodeverification
-- ----------------------------
INSERT INTO `qrcodeverification` VALUES (55, 1875814007898906624, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-05 15:58:06');
INSERT INTO `qrcodeverification` VALUES (56, 1875814407003709440, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-05 15:59:41');
INSERT INTO `qrcodeverification` VALUES (57, 1877215946821955584, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-09 12:49:22');
INSERT INTO `qrcodeverification` VALUES (58, 1877223410527203328, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-09 13:18:36');
INSERT INTO `qrcodeverification` VALUES (59, 1877223395012472832, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-09 13:32:20');
INSERT INTO `qrcodeverification` VALUES (60, 1877266548524433408, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-09 16:09:58');
INSERT INTO `qrcodeverification` VALUES (61, 1877269026464710656, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-09 16:19:49');
INSERT INTO `qrcodeverification` VALUES (62, 1877271681702412288, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-09 16:30:22');
INSERT INTO `qrcodeverification` VALUES (63, 1877271826644975616, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-09 16:30:57');
INSERT INTO `qrcodeverification` VALUES (64, 1877524766508744704, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-10 09:16:03');
INSERT INTO `qrcodeverification` VALUES (65, 1877569493383806976, 'http://sr3ylefaw.hd-bkt.clouddn.com/qrcode.png', 0, '2025-01-10 12:13:54');

-- ----------------------------
-- Table structure for reservations
-- ----------------------------
DROP TABLE IF EXISTS `reservations`;
CREATE TABLE `reservations`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `scenicId` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '景区id',
  `stock` int(255) NULL DEFAULT NULL COMMENT '库存数量',
  `openDateTime` date NULL DEFAULT NULL COMMENT '开放时间',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1843990285835657718 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '预约表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reservations
-- ----------------------------
INSERT INTO `reservations` VALUES (1843990285835657708, '102', 98, '2025-01-09', '2025-01-09 16:04:51', '2025-01-09 16:04:51', 0);
INSERT INTO `reservations` VALUES (1843990285835657709, '102', 298, '2025-01-10', '2025-01-09 16:04:58', '2025-01-09 16:04:58', 0);
INSERT INTO `reservations` VALUES (1843990285835657710, '102', 298, '2025-01-11', '2025-01-09 16:05:07', '2025-01-09 16:05:07', 0);
INSERT INTO `reservations` VALUES (1843990285835657711, '102', 300, '2025-01-12', '2025-01-09 16:05:17', '2025-01-09 16:05:17', 0);
INSERT INTO `reservations` VALUES (1843990285835657712, '105', 100, '2025-01-10', '2025-01-09 16:05:24', '2025-01-09 16:05:24', 0);
INSERT INTO `reservations` VALUES (1843990285835657713, '105', 299, '2025-01-11', '2025-01-09 16:05:31', '2025-01-09 16:05:31', 0);
INSERT INTO `reservations` VALUES (1843990285835657714, '105', 300, '2025-01-12', '2025-01-09 16:05:39', '2025-01-09 16:05:39', 0);
INSERT INTO `reservations` VALUES (1843990285835657715, '124', 299, '2025-01-10', '2025-01-09 16:05:49', '2025-01-09 16:05:49', 0);
INSERT INTO `reservations` VALUES (1843990285835657716, '124', 100, '2025-01-11', '2025-01-09 16:05:55', '2025-01-09 16:05:55', 0);
INSERT INTO `reservations` VALUES (1843990285835657717, '124', 200, '2025-01-13', '2025-01-09 16:06:03', '2025-01-09 16:06:03', 0);

-- ----------------------------
-- Table structure for reservations_travelers
-- ----------------------------
DROP TABLE IF EXISTS `reservations_travelers`;
CREATE TABLE `reservations_travelers`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userId` bigint(20) NOT NULL COMMENT '创建用户 id',
  `reservationsId` bigint(20) NULL DEFAULT NULL COMMENT '预约表 id',
  `fullName` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '姓名',
  `idNumber` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '身份证',
  `phoneNumber` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '手机号',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 39 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '预约用户信息表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of reservations_travelers
-- ----------------------------
INSERT INTO `reservations_travelers` VALUES (30, 1828339688465825795, 1843990285835657708, '张核验', '310106199007205678', '13600001234', '2025-01-09 16:08:33', '2025-01-09 16:12:58', 0);
INSERT INTO `reservations_travelers` VALUES (31, 1828339688465825795, 1843990285835657709, '李模拟', '510104198205254321', '18922223456', '2025-01-09 16:09:40', '2025-01-09 16:09:40', 0);
INSERT INTO `reservations_travelers` VALUES (32, 1828339688465825795, 1843990285835657709, '李模拟', '440305197811089876', '15811115678', '2025-01-09 16:09:40', '2025-01-09 16:09:40', 0);
INSERT INTO `reservations_travelers` VALUES (33, 1828339688465825796, 1843990285835657708, '刘虚拟', '440305197811089876', '15811115678', '2025-01-09 16:19:41', '2025-01-09 16:19:41', 0);
INSERT INTO `reservations_travelers` VALUES (34, 1828339688465825797, 1843990285835657710, '赵试验', '210202199509126543', '17733337890', '2025-01-09 16:30:03', '2025-01-09 16:30:03', 0);
INSERT INTO `reservations_travelers` VALUES (35, 1828339688465825793, 1843990285835657715, 'alice', '313333333333333333', '1333333333', '2025-01-09 23:04:10', '2025-01-09 23:04:10', 0);
INSERT INTO `reservations_travelers` VALUES (36, 1828339688465825797, 1843990285835657711, '张三', '313333333333333333', '1333333333', '2025-01-10 20:58:41', '2025-01-10 09:12:10', 1);
INSERT INTO `reservations_travelers` VALUES (37, 1828339688465825797, 1843990285835657713, '李四', '355555555555555555', '13333333333', '2025-01-10 09:07:53', '2025-01-10 09:07:53', 0);
INSERT INTO `reservations_travelers` VALUES (38, 1828339688465825793, 1843990285835657710, 'alice', '133333333', '133333', '2025-01-10 12:13:09', '2025-01-10 12:13:09', 0);

-- ----------------------------
-- Table structure for scenic
-- ----------------------------
DROP TABLE IF EXISTS `scenic`;
CREATE TABLE `scenic`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '景点id',
  `carouselImages` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '轮播图（JSON数组或逗号分隔的字符串）',
  `scenicName` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '景点名称',
  `tags` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '标签（JSON数组或逗号分隔的字符串）',
  `openingHours` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '开放时间',
  `contactNumber` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '联系电话',
  `address` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '地址',
  `details` text CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL COMMENT '详情',
  `type` int(2) NULL DEFAULT NULL COMMENT '0免费预约1购买门票',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 125 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '景点表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of scenic
-- ----------------------------
INSERT INTO `scenic` VALUES (101, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/48814825-4805-47b2-bd44-4d709c33a44e.jpg\"]', '清源山', '[\"自然景观\",\"文化遗址\"]', '08:00-18:00', '0595-12345678', '福建省泉州市丰泽区清源山', '清源山是泉州著名的风景名胜区，被誉为“闽南第一山”。', 1);
INSERT INTO `scenic` VALUES (102, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/9b0280ab-fa42-4b4d-8ee1-ebd98162e7d4.jpg\"]', '开元寺', '[\"历史古迹\",\"宗教文化\"]', '07:00-17:30', '0595-87654321', '福建省泉州市鲤城区西街176号', '开元寺是福建省规模最大的佛教寺院，拥有悠久的历史和壮丽的建筑。', 0);
INSERT INTO `scenic` VALUES (103, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/9aca0d5e-8355-41d2-bc25-ef060b743996.jpg\"]', '洛阳桥', '[\"桥梁\",\"历史文化\"]', '全天开放', '0595-11223344', '福建省泉州市惠安县洛阳镇', '洛阳桥是中国古代四大名桥之一，也是世界上最早的跨海梁式石桥。', 1);
INSERT INTO `scenic` VALUES (104, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/c8f92790-9121-4498-86d8-408bb815b3b6.jpg\"]', '德化九仙山', '[\"自然景观\",\"登山\"]', '06:00-18:00', '0595-99887766', '福建省泉州市德化县', '九仙山以其秀丽的山峰和云海景观而闻名，是徒步爱好者的天堂。', 1);
INSERT INTO `scenic` VALUES (105, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/75fef190-3e6e-49cc-b799-3d2ce8256cfb.jpg\"]', '安溪茶文化博览园', '[\"茶文化\",\"展览\"]', '08:30-17:30', '0595-88776655', '福建省泉州市安溪县', '博览园展示了安溪铁观音茶的制作工艺和文化传统。', 0);
INSERT INTO `scenic` VALUES (106, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/38dd2267-6839-4f67-9485-88776f84cf1b.jpg\"]', '永春牛姆林', '[\"自然景观\",\"森林\"]', '07:00-18:00', '0595-77665544', '福建省泉州市永春县', '牛姆林是国家级自然保护区，以原始森林和动植物资源丰富而著称。', 1);
INSERT INTO `scenic` VALUES (109, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/a8095ac9-935e-4f9b-bc3a-748d0223af19.jpg\"]', '石狮黄金海岸', '[\"海滨风光\",\"度假\"]', '全天开放', '0595-44556677', '福建省泉州市石狮市', '黄金海岸以其沙滩和海水浴场而闻名，是旅游度假的好去处。', 1);
INSERT INTO `scenic` VALUES (124, '[\"http://sr3ylefaw.hd-bkt.clouddn.com/5393e3e7-f613-4998-aa0e-8f6e81df0283.jpg\"]', '泉州天后宫', '[\"宗教文化\",\"历史古迹\",\"ceshi\"]', '08:00-17:00', '0595-55441122', '福建省泉州市鲤城区', '天后宫是祭祀海神妈祖的重要场所，具有重要的历史和文化意义。', 0);

-- ----------------------------
-- Table structure for ticket
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `scenicId` bigint(20) NULL DEFAULT NULL COMMENT '景区id',
  `price` decimal(10, 2) NOT NULL COMMENT '价格',
  `openDateTime` date NULL DEFAULT NULL COMMENT '门票日期',
  `stock` int(11) NOT NULL COMMENT '库存数量',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '门票景点表' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES (17, 101, 20.00, NULL, 195);
INSERT INTO `ticket` VALUES (18, 103, 20.00, NULL, 98);
INSERT INTO `ticket` VALUES (19, 104, 30.00, NULL, 300);
INSERT INTO `ticket` VALUES (20, 106, 30.00, NULL, 200);
INSERT INTO `ticket` VALUES (21, 109, 30.00, NULL, 99);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `userAccount` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '账号',
  `userPassword` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `unionId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '微信开放平台id',
  `mpOpenId` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '公众号openId',
  `phoneNumber` bigint(20) NULL DEFAULT NULL COMMENT '手机号',
  `userName` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户昵称',
  `userAvatar` varchar(1024) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户头像',
  `userProfile` varchar(512) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NULL DEFAULT NULL COMMENT '用户简介',
  `userRole` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci NOT NULL DEFAULT 'user' COMMENT '用户角色：user/admin/ban',
  `createTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `updateTime` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `isDelete` tinyint(4) NOT NULL DEFAULT 0 COMMENT '是否删除',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_unionId`(`unionId`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1828339688465825825 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_unicode_ci COMMENT = '用户' ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES (1828339688465825793, 'alice', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333333, '衣锦夜行', 'http://sr3ylefaw.hd-bkt.clouddn.com/cd188999-42f6-43cd-83e1-8b270582087c.jpg', '开启您的旅行之旅', 'user', '2024-08-27 15:52:05', '2025-01-25 20:22:57', 0);
INSERT INTO `user` VALUES (1828339688465825794, 'admin', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333334, 'admin', 'http://sop32w2eh.hn-bkt.clouddn.com/70f66df0-5ad9-401d-b32a-28208d8d06bd', '我管理员2222', 'admin', '2024-10-06 16:03:56', '2025-01-09 15:18:34', 0);
INSERT INTO `user` VALUES (1828339688465825795, 'bob', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333335, '洛水殇璃', 'http://sr3ylefaw.hd-bkt.clouddn.com/50eb2472-0f09-4ba3-971d-6be7208e1141.jpg', '这是用户1的简介2', 'user', '2024-11-17 17:30:00', '2025-01-25 20:23:40', 0);
INSERT INTO `user` VALUES (1828339688465825796, 'Carol', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333336, '苍岚逸尘', 'http://sr3ylefaw.hd-bkt.clouddn.com/6f50f02d-d325-4112-816f-54c8d1d0bbd3.jpg', '这是用户2的简介', 'user', '2024-11-17 17:31:00', '2025-01-25 20:33:02', 0);
INSERT INTO `user` VALUES (1828339688465825797, 'david', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333337, '星辰破风者', 'http://sr3ylefaw.hd-bkt.clouddn.com/85b47b8f-77c7-46b9-818a-85bb42957ff2.jpg', '这是用户3的简介', 'user', '2024-11-17 17:32:00', '2025-01-25 20:34:36', 0);
INSERT INTO `user` VALUES (1828339688465825798, 'Eric', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333338, '发呆的鼻涕泡', NULL, '这是用户4的简介', 'user', '2024-11-17 17:33:00', '2025-01-09 15:25:12', 0);
INSERT INTO `user` VALUES (1828339688465825799, 'Frank', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333339, '专业拆台三十年', NULL, '这是用户5的简介', 'user', '2024-11-17 17:34:00', '2025-01-09 15:25:15', 0);
INSERT INTO `user` VALUES (1828339688465825800, 'Grace', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333310, '素念', '', '我管理员666666', 'user', '2024-11-17 17:35:00', '2025-01-09 23:44:17', 0);
INSERT INTO `user` VALUES (1828339688465825801, 'Grace', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333311, '简逸', NULL, '这是用户7的简介', 'user', '2024-11-17 17:36:00', '2025-01-09 15:25:20', 0);
INSERT INTO `user` VALUES (1828339688465825802, 'user8', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333312, '月光独角兽', NULL, '这是用户8的简介', 'user', '2024-11-17 17:37:00', '2025-01-09 15:23:58', 0);
INSERT INTO `user` VALUES (1828339688465825803, 'user9', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333313, '咸鱼想翻身呀', NULL, '这是用户9的简介', 'user', '2024-11-17 17:38:00', '2025-01-09 15:24:00', 0);
INSERT INTO `user` VALUES (1828339688465825804, 'user10', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, 13333333314, '骑着扫帚去逛街', NULL, '这是用户10的简介', 'user', '2024-11-17 17:39:00', '2025-01-09 15:24:05', 0);
INSERT INTO `user` VALUES (1828339688465825805, 'user11', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, NULL, '管理员11', NULL, '这是管理员11的简介', 'admin', '2024-11-17 17:40:00', '2025-01-09 15:21:50', 0);
INSERT INTO `user` VALUES (1828339688465825806, 'user12', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, NULL, '管理员12', NULL, '这是管理员12的简介', 'admin', '2024-11-17 17:41:00', '2025-01-09 15:21:47', 0);
INSERT INTO `user` VALUES (1828339688465825807, 'user13', '9a8fe57f7440d711ca7c45d933df4730', NULL, NULL, NULL, '管理员13', NULL, '这是管理员13的简介', 'admin', '2024-11-17 17:42:00', '2025-01-09 15:21:45', 0);
INSERT INTO `user` VALUES (1828339688465825808, 'user14', '9a8fe57f7440d711ca7c45d933df4730', NULL, 'MPOPENID14', NULL, '管理员14', NULL, '这是管理员14的简介', 'admin', '2024-11-17 17:43:00', '2025-01-09 15:21:43', 0);
INSERT INTO `user` VALUES (1828339688465825809, 'user15', '9a8fe57f7440d711ca7c45d933df4730', NULL, 'MPOPENID15', NULL, '管理员15', NULL, '这是管理员15的简介', 'admin', '2024-11-17 17:44:00', '2025-01-09 15:21:42', 0);
INSERT INTO `user` VALUES (1828339688465825810, 'user16', '9a8fe57f7440d711ca7c45d933df4730', NULL, 'MPOPENID16', NULL, '管理员16', NULL, '这是管理员16的简介', 'admin', '2024-11-17 17:45:00', '2025-01-09 15:21:40', 0);
INSERT INTO `user` VALUES (1828339688465825811, 'user17', '9a8fe57f7440d711ca7c45d933df4730', NULL, 'MPOPENID17', NULL, '管理员17', NULL, '这是管理员17的简介', 'admin', '2024-11-17 17:46:00', '2025-01-09 15:21:38', 0);
INSERT INTO `user` VALUES (1828339688465825812, 'user18', '9a8fe57f7440d711ca7c45d933df4730', NULL, 'MPOPENID18', NULL, '管理员18', NULL, '这是管理员18的简介', 'admin', '2024-11-17 17:47:00', '2025-01-09 15:21:36', 0);
INSERT INTO `user` VALUES (1828339688465825813, 'user19', '9a8fe57f7440d711ca7c45d933df4730', NULL, 'MPOPENID19', NULL, '管理员19', NULL, '这是管理员19的简介', 'admin', '2024-11-17 17:48:00', '2025-01-09 15:21:33', 0);
INSERT INTO `user` VALUES (1828339688465825814, 'user20', '9a8fe57f7440d711ca7c45d933df4730', NULL, 'MPOPENID20', NULL, '管理员20', NULL, '这是管理员20的简介', 'admin', '2024-11-17 17:49:00', '2025-01-09 15:21:30', 0);
INSERT INTO `user` VALUES (1828339688465825815, 'user21', 'password21', NULL, 'MPOPENID21', NULL, '用户21', NULL, '这是用户21的简介', 'user', '2024-11-17 17:50:00', '2024-11-17 17:50:00', 0);
INSERT INTO `user` VALUES (1828339688465825816, 'user22', 'password22', NULL, 'MPOPENID22', NULL, '用户22', NULL, '这是用户22的简介', 'user', '2024-11-17 17:51:00', '2024-11-17 17:51:00', 0);
INSERT INTO `user` VALUES (1828339688465825817, 'user23', 'password23', NULL, 'MPOPENID23', NULL, '用户23', NULL, '这是用户23的简介', 'user', '2024-11-17 17:52:00', '2024-11-17 17:52:00', 0);
INSERT INTO `user` VALUES (1828339688465825818, 'user24', 'password24', NULL, 'MPOPENID24', NULL, '用户24', NULL, '这是用户24的简介', 'user', '2024-11-17 17:53:00', '2024-11-17 17:53:00', 0);
INSERT INTO `user` VALUES (1828339688465825819, 'user25', 'password25', NULL, 'MPOPENID25', NULL, '用户25', NULL, '这是用户25的简介', 'user', '2024-11-17 17:54:00', '2024-11-17 17:54:00', 0);
INSERT INTO `user` VALUES (1828339688465825820, 'user26', 'password26', NULL, 'MPOPENID26', NULL, '用户26', NULL, '这是用户26的简介', 'user', '2024-11-17 17:55:00', '2024-11-17 17:55:00', 0);
INSERT INTO `user` VALUES (1828339688465825821, 'user27', 'password27', NULL, 'MPOPENID27', NULL, '用户27', NULL, '这是用户27的简介', 'user', '2024-11-17 17:56:00', '2024-11-17 17:56:00', 0);
INSERT INTO `user` VALUES (1828339688465825822, 'user28', 'password28', NULL, 'MPOPENID28', NULL, '用户28', NULL, '这是用户28的简介', 'user', '2024-11-17 17:57:00', '2024-11-17 17:57:00', 0);
INSERT INTO `user` VALUES (1828339688465825823, 'user29', 'password29', NULL, 'MPOPENID29', NULL, '测试接口用的username', '', 'ceshi', 'user', '2024-11-17 17:58:00', '2024-11-18 16:28:28', 0);
INSERT INTO `user` VALUES (1828339688465825824, 'user30', 'password30', NULL, 'MPOPENID30', NULL, '用户30', NULL, '这是用户30的简介', 'user', '2024-11-17 17:59:00', '2024-11-18 16:26:28', 1);

-- ----------------------------
-- Table structure for vrtour
-- ----------------------------
DROP TABLE IF EXISTS `vrtour`;
CREATE TABLE `vrtour`  (
  `id` bigint(20) NOT NULL,
  `cover` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picture` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `location` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

-- ----------------------------
-- Records of vrtour
-- ----------------------------
INSERT INTO `vrtour` VALUES (18, 'http://sr3ylefaw.hd-bkt.clouddn.com/876b1036-d0d5-4a41-a2cf-bb66a76db040.jpg', 'http://sr3ylefaw.hd-bkt.clouddn.com/18460410-6665-4851-a849-d9a106527557.jpg', '开元寺', '福建省泉州市鲤城区西街176号');
INSERT INTO `vrtour` VALUES (1863215131576623106, 'http://sr3ylefaw.hd-bkt.clouddn.com/aa16791e-e5da-4fd4-9f39-11591c639b8e.jpg', 'http://sr3ylefaw.hd-bkt.clouddn.com/96537aaa-7594-46c8-bce8-f920072d2d6f.jpg', '洛阳桥', '福建省泉州市惠安县洛阳桥');
INSERT INTO `vrtour` VALUES (1863215377438334978, 'http://sr3ylefaw.hd-bkt.clouddn.com/a58fbbc7-9a83-4cb1-a056-6426d7427815.jpg', 'http://sr3ylefaw.hd-bkt.clouddn.com/636ea967-713a-4710-9d38-ca7efa63bacf.jpg', '海外交通史博物馆', '泉州市丰泽区开元寺东侧');

-- ----------------------------
-- View structure for visit_trend
-- ----------------------------
DROP VIEW IF EXISTS `visit_trend`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `visit_trend` AS select date_format(`d`.`date`,'%m-%d') AS `date`,coalesce(`r`.`reservations`,0) AS `预约人数`,coalesce(`o`.`purchases`,0) AS `购票人数` from ((((select (curdate() - interval `numbers`.`n` day) AS `date` from (select 0 AS `n` union all select 1 AS `1` union all select 2 AS `2` union all select 3 AS `3` union all select 4 AS `4` union all select 5 AS `5` union all select 6 AS `6`) `numbers`)) `d` left join (select cast(`my_db`.`reservations`.`createTime` as date) AS `date`,count(0) AS `reservations` from `my_db`.`reservations` where (`my_db`.`reservations`.`createTime` >= (curdate() - interval 6 day)) group by cast(`my_db`.`reservations`.`createTime` as date)) `r` on((`d`.`date` = `r`.`date`))) left join (select cast(`my_db`.`orders`.`purchaseDate` as date) AS `date`,count(0) AS `purchases` from `my_db`.`orders` where (`my_db`.`orders`.`purchaseDate` >= (curdate() - interval 6 day)) group by cast(`my_db`.`orders`.`purchaseDate` as date)) `o` on((`d`.`date` = `o`.`date`))) order by `d`.`date`;

SET FOREIGN_KEY_CHECKS = 1;
