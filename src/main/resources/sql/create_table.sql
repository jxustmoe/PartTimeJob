# version 2.0
# 推荐使用datagrip
# 如果逗号变红，请更改方言为mysql
CREATE DATABASE IF NOT EXISTS meng;
USE meng;

CREATE TABLE IF NOT EXISTS job
(
  id       INT PRIMARY KEY AUTO_INCREMENT,
  jobName  VARCHAR(30) NOT NULL,
  detail   TEXT        NOT NULL,
  address  VARCHAR(50) NOT NULL,
  postTime varchar(20) NOT NULL,
  jobType  VARCHAR(20) NOT NULL,
  name     VARCHAR(30) NOT NULL,
  phone    VARCHAR(13) NOT NULL,
  email    VARCHAR(30) NOT NULL,
  isShow   TINYINT default 1
);
