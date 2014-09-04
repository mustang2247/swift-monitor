CREATE DATABASE `swiftmonitor`;

DROP TABLE IF EXISTS `seeds`;
CREATE TABLE `seeds` (
  `ID` int(11) NOT NULL auto_increment,
  `URL` varchar(500) COMMENT '种子的url地址',
  `NAME` varchar(30) COMMENT '种子网站名称',
  `IS_INSIDE` int(11) COMMENT '是否是爬虫程序内置的，1表示是，0表示否',
  `INSIDE_NAME` varchar(30) COMMENT '如果是爬虫程序内置的种子，可以有简称',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `nodes`;
CREATE TABLE `nodes` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(30) COMMENT 'ip地址',
  `PORT` int(11) COMMENT '端口地址',
  `IS_ALIVE` int(11) COMMENT '是否激活，1表示是，0表示否',
  `OS` varchar(30) COMMENT '操作系统',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `monitor_info`;
CREATE TABLE `monitor_info` (
  `ID` int(11) NOT NULL auto_increment,
  `NODE_ID` varchar(30) COMMENT 'node id',
  `MEM_RATE` float(11) COMMENT '内存使用率',
  `CPU_RATE` float(11) COMMENT 'CPU使用率',
  `CHECK_TIME` varchar(20) COMMENT '检测时间',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `configuration`;
CREATE TABLE `configuration` (
  `ID` int(11) NOT NULL auto_increment,
  `NODE_ID` varchar(30) COMMENT '爬虫节点',
  `INTERVAL_TIME` varchar(50) COMMENT '每次抓取网站时间间隔',
  `START_TIME` varchar(30) COMMENT '每次抓取网站的开始时间',
  `IN_SEED_IDS` varchar(30) COMMENT '内置的种子ID，可以是多个用逗号隔开',
  `OUT_SEED_IDS` varchar(30) COMMENT '外置的种子IP，可以是多个用逗号隔开',
  `HTTP_PROXY_IDS` varchar(30) COMMENT 'HTTP代理ID，可以是多个用逗号隔开',
  `IN_IS_DOWNLOAD` int(11) COMMENT '针对内置种子网站是否下载页面，1表示是，0表示否',
  `OUT_IS_DOWNLOAD` int(11) COMMENT '针对外置种子网站是否下载页面，1表示是，0表示否',
  `JS_SUPPORT` int(11) COMMENT '针对内置种子网站是否支持JS解析，1表示是，0表示否',
  `IS_CASCADE` int(11) COMMENT '针对外置种子网站是否支持级联抓取，1表示是，0表示否',
  `OUT_DEPTH` int(11) COMMENT '针对外置种子网站支持抓取的网站深度，1表示只抓取到一级域名，以此类推',
  `IN_DELAY` varchar(30) COMMENT '每两次抓取相同内置种子网站的时间间隔',
  `OUT_DELAY` varchar(30) COMMENT '每两次抓取相同外置种子网站的时间间隔',
  `DEP_STATUS` varchar(30) default 0 COMMENT '部署状态，0表示部署失败，1表示部署成功',
  `JOB_STATUS` varchar(30) default 0 COMMENT '爬虫工作状态，0表示job未开始，1表示job正常，2表示job暂停，3表示取消',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


DROP TABLE IF EXISTS `http_proxys`;
CREATE TABLE `http_proxys` (
  `ID` int(11) NOT NULL auto_increment,
  `IP` varchar(30) COMMENT 'ip地址',
  `PORT` int(11) COMMENT '端口地址',
  `USERNAME` varchar(30) COMMENT '代理用户名',
  `PASSWORD` varchar(30) COMMENT '代理密码',
  PRIMARY KEY  (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;




INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (1, 'http://www.ppdai.com', '拍拍贷', 1, 'ppdai');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (2, 'http://www.365edai.cn/', '365易贷', 1, 'edai365');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (3, 'http://www.firstp2p.com/', '第一P2P', 1, 'firstp2p');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (4, 'http://www.jimubox.com/', '积木盒子', 1, 'jimubox');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (5, 'http://www.lufax.com/', '陆金所', 1, 'lufax');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (6, 'http://www.my089.com/', '红岭创投', 1, 'my089');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (7, 'http://www.niwodai.com/', '你我贷', 1, 'niwodai');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (8, 'http://www.ppdai.com/', '拍拍贷', 1, 'ppdai');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (9, 'http://www.ppmoney.com/', '万惠投资', 1, 'ppmoney');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (10, 'http://www.renrendai.com/', '人人贷', 1, 'renrendai');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (11, 'http://www.royidai.com/', '讯贷网', 1, 'royidai');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (12, 'http://www.yirendai.com/', '宜人贷', 1, 'yirendai');
INSERT INTO seeds (ID, URL, NAME, IS_INSIDE, INSIDE_NAME) VALUES (13, 'http://www.yooli.com/', '有利网', 1, 'yooli');


INSERT INTO http_proxys (ID, IP, PORT) VALUES (1, '60.191.39.252', 80);


