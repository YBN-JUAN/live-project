drop database if exists maskorder;
create database maskorder;

use maskorder;

create table orders (

id int auto_increment primary key, /*预约编号，表示第几次预约活动*/

opening boolean default true, /*该次预约是否正在开放*/

masknum int not null /*该次预约口罩数量*/
);

create table records (

id int auto_increment primary key, /*记录编号*/

orderid int, /*预约编号*/
FOREIGN KEY(orderid) REFERENCES orders(id)
	ON UPDATE CASCADE
    ON DELETE CASCADE,

username varchar(255) not null, /*真实姓名*/

userid varchar(20) not null, /*身份证号*/

telnum varchar(20) not null, /*手机号*/

ordernum int default 3, /*预约口罩数量*/

selected boolean not null default false /*是否中签*/
);