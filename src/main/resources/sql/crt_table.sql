--create account table
DROP TABLE IF EXISTS `account`;

CREATE TABLE `account` (
  `acct_id` int(11) NOT NULL AUTO_INCREMENT,
  `pay_name` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `user_id` int(11) DEFAULT NULL,
  `bank_no` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`acct_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--create customer table
DROP TABLE IF EXISTS `customer`;

CREATE TABLE `customer` (
  `cust_id` int(11) NOT NULL AUTO_INCREMENT,
  `cust_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `cert_no` varchar(45) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`cust_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--create user table
DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `svc_num` varchar(45) COLLATE utf8_bin NOT NULL,
  `user_pwd` varchar(45) COLLATE utf8_bin NOT NULL,
  `cust_id` int(11) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;