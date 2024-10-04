create database schedule_system;
use schedule_system;

set names utf8mb4;
set foreign_key_checks = 0;

# 日程表
drop table if exists `sys_schedule`;
create table `sys_schedule`
(
    `sid`       int    not null auto_increment,
    `uid`       int    null default null,
    `title`     varchar(20) default null,
    `completed` int(1) null default null,
    primary key (`sid`) using btree
) engine = InnoDB
  auto_increment = 1
  character set = utf8mb4
  row_format = dynamic;


# 用户表
drop table if exists `sys_user`;
create table `sys_user`
(
    `uid`      int not null auto_increment,
    `username` varchar(10)  default null,
    `password` varchar(100) default null,
    primary key (`uid`) using btree
) engine = InnoDB
  character set utf8mb4
  row_format = dynamic;

# 插入用户数据
insert into `sys_user`
values (1, '张三', md5('123456zs'));
insert into `sys_user`
values (2, '李四', md5('123456ls'));
set foreign_key_checks = 1;
