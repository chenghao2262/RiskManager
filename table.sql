DROP TABLE IF EXISTS project;
CREATE TABLE IF NOT EXISTS project(
  pid INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  name VARCHAR(356),
  creator INTEGER NOT NULL,
  createTime DATETIME
);

DROP TABLE IF EXISTS risk;
CREATE TABLE IF NOT EXISTS risk(
  rid INTEGER NOT NULL  PRIMARY KEY  AUTO_INCREMENT,
  createTime DATETIME NOT NULL ,
  creator VARCHAR(256)
);

DROP TABLE IF EXISTS risk_project;
CREATE TABLE IF NOT EXISTS risk_project(
  rid INTEGER NOT NULL,
  pid INTEGER NOT NULL,
  UNIQUE (rid,pid)
);

DROP TABLE IF EXISTS risk_detail;
CREATE TABLE IF NOT EXISTS risk_detail(
  rid INTEGER NOT NULL,
  rdid INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
  updateTime DATETIME NOT NULL ,
  updater VARCHAR(256) NOT NULL ,
  riskTitle VARCHAR(256) NOT NULL ,
  riskPossibility VARCHAR(256) NOT NULL ,
  riskInfluence VARCHAR(256) NOT NULL ,
  threshold VARCHAR(256) NOT NULL ,
  content VARCHAR(256) NOT NULL
);

SELECT * FROM (select r1.*,t.times from
  (select risk.rid,count(risk_detail.rid) as times from risk left join risk_detail  on risk.rid=risk_detail.rid group by risk.rid order by risk.rid) as t,risk r1
    WHERE r1.rid=t.rid) as t2 LEFT JOIN risk_detail ON t2.rid=risk_detail.rid

