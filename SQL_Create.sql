/*供应商表：（sup_id int not null auto_increment ,
                                 sup_name varchar(255) not null,
                                 sup_address varchar(255) not null, 
                                 sup_phone int not null, primary key(sup_id))*/
--螺蛳粉供应表属性：供应商id ,厂名，厂址，厂电话，主键sup_id
CREATE TABLE Supplier_form(sup_id int not null auto_increment,
sup_name VARCHAR(255) not NULL,
sup_address VARCHAR(255) NOT null
,sup_phone int NOT NULL,PRIMARY KEY(sup_id));

--2.美食属性表(总)：（id not null int auto_increment,
--                                   sup_id int not null auto_increment ,
 --  add_id not null int auto_increment,
 --  k_id not null int auto_increment,
--）
--主键：id，外键： sup_id，add_id，k_id
--返回剩下几天过期
CREATE TABLE food_form(id int not null auto_increment,
sup_id int not null,
add_id int not null,
k_id  int not null,
primary key(id),
foreign key(sup_id) references supplier_form(sup_id) on delete cascade on update cascade,
foreign key(add_id) references address_form(add_id) on delete cascade on update cascade,
foreign key(k_id) references kinds_form(k_id) on delete cascade on update cascade);
/*
3.美食日期表(id not null int auto_increment,
                        pro_date date int not null comment'生产日期',
                        exp_date date not null comment'保质期',primary key(id)
                        )
--主键：id*/
CREATE TABLE date_form(id int not null auto_increment,
pro_date date not null comment'生产日期',
exp_date date not null comment'保质期',primary key(id));
/*
4.店铺地址表( add_id not null int,
                     address var not null, 
                     add_phone int not null auto_increment,
                     adaptive int not null ,primary key(add_id)
)*/
--属性：地址id，地址，地址电话，推荐指数，主键：add_id
CREATE TABLE address_form(add_id  int not null auto_increment ,
                     address varchar(255) not null, 
                     add_phone int not null ,);
/*5.美食种类表(   k_id not null int auto_increment,
                        price float not null,
     package var not null comment '包装类型',
      weight int not null,
      primary key(k_id))
//属性：种类k_id，价格，   包装   ，重量*/

CREATE TABLE attribute_form(a_id int not null auto_increment,
                        price float not null,
                        package varchar(255) not null comment '包装类型',
                        weight int not null,
						adaptive int not null ,primary key(add_id),
                        primary key(a_id));
--6、用户表
CREATE TABLE user(
u_name varchar not null,
u_password varchar not null,
u_sex varchar not null,
                         u_phone Integer not null,
u_mail varchar null,
u_level varchar not null,
)


)