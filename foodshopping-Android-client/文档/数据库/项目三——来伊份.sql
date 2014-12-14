--删除表
DROP TABLE SHOPPING_USERS; --1
DROP TABLE SHOPPING_ACCOUNT; --2
DROP TABLE SHOPPING_ORDERS; --3
DROP TABLE SHOPPING_RECIEVER_ADDRESS; --4
DROP TABLE SHOPPING_SHOPPING_BASKET; --5
DROP TABLE SHOPPING_CATE_COMMENT; --6
DROP TABLE SHOPPING_LIKE_EAT; --7
DROP TABLE SHOPPING_CATE_IMAGE; --8
DROP TABLE SHOPPING_CATE; --9
DROP TABLE SHOPPING_CATEGORY; --10
DROP TABLE SHOPPING_FLAVOR_CATEGORY; --11
DROP TABLE SHOPPING_RECHARGE_RECORD; --12
DROP TABLE SHOPPING_CONSUME_RECORD; --13
DROP TABLE SHOPPING_CATE_TRACK; --14
-- Create table
--1用户表 SHOPPING_USERS

create table SHOPPING_USERS
(
  user_id number(10) PRIMARY KEY,
  user_name Varchar2(50) not NULL,
  user_password Varchar2(50) NOT NULL,
  user_phone    NUMBER(11) NOT NULL,
  user_region  VARCHAR2(200),--地区
  user_street_address VARCHAR2(200),--街道地址
  user_postcode NUMBER(6), --邮编
  user_email    VARCHAR2(40) NOT NULL

)
select * from SHOPPING_USERS;
INSERT INTO SHOPPING_USERS VALUES(1,'cw','cw123',13554588281,'mhyncw@163.com');
COMMIT;
--create SEQUENCE
--用户号 序列 SEQ_SHOPPING_USERS_USERID
CREATE SEQUENCE SEQ_SHOPPING_USERS_USERID
MINVALUE 1
MAXVALUE 999999999
START WITH 1
INCREMENT BY 1
CACHE 10
ORDER ; 

--create Trigger
--用户号 触发器 TRI_SHOPPING_USERS_USERID
CREATE OR REPLACE  TRIGGER TRI_SHOPPING_USERS_USERID
       BEFORE INSERT OR UPDATE OF user_id ON SHOPPING_USERS
       FOR EACH ROW
BEGIN 
       SELECT SEQ_SHOPPING_USERS_USERID.Nextval INTO:new.user_id FROM dual;
END;
--2用户的账户
CREATE TABLE SHOPPING_ACCOUNT
(
 account_id NUMBER(10) PRIMARY key,
 --client_user_id NUMBER(10)FOREIGN KEY,
 user_id NUMBER(10) REFERENCES SHOPPING_USERS(user_id),
 account_balance NUMBER(10,2),--余额
 account_integral NUMBER(10,2)--积分
)
SELECT * FROM SHOPPING_ACCOUNT
--create SEQUENCE
--用户号 序列 SEQ_SHOPPING_ACCOUNT_ID
CREATE SEQUENCE SEQ_SHOPPING_ACCOUNT_ID
MINVALUE 1
MAXVALUE 999999999
START WITH 1
INCREMENT BY 1
CACHE 10
ORDER ; 

--create Trigger
--用户号 触发器 TRI_SHOPPING_ACCOUNT_ID
CREATE OR REPLACE  TRIGGER TRI_SHOPPING_ACCOUNT_ID
       BEFORE INSERT OR UPDATE OF account_id ON SHOPPING_ACCOUNT
       FOR EACH ROW
BEGIN 
       SELECT SEQ_SHOPPING_ACCOUNT_ID.Nextval INTO:new.account_id FROM dual;
END;



<<<<<<< .mine
--3订单
select * from SHOPPING_ORDERS
=======
--3订单
>>>>>>> .r1543
CREATE TABLE SHOPPING_ORDERS
(
  orders_id NUMBER(10) PRIMARY KEY,
 -- client_user_id NUMBER(10) FOREIGN KEY,
  user_id NUMBER(10) REFERENCES SHOPPING_USERS(user_id),
  reciever_address_id NUMBER(10) REFERENCES  SHOPPING_RECIEVER_ADDRESS(reciever_address_id),
  orders_status VARCHAR2(6),--状态（已支付、未支付）
  orders_goods_item_count NUMBER(10),--商品数量
  orders_goods_amount NUMBER(10,2),--商品总额
  orders_mail_charge NUMBER(10,2),--邮费
  orders_pay_mode VARCHAR2(200),--支付模式
  orders_date DATE,
  orders_mail_mode VARCHAR2(200),--邮递方式
  orders_bill varchar2(200)--发票抬头
)
<<<<<<< .mine
SELECT * FROM SHOPPING_ORDERS WHERE user_id=33 and orders_status='待付款'
SELECT * FROM SHOPPING_ORDERS WHERE user_id=33 and orders_status='待发货'
=======
>>>>>>> .r1543

<<<<<<< .mine
SELECT  a.* ,b.* from SHOPPING_ORDERS a,SHOPPING_SHOPPING_BASKET b where a.orders_id=27 and b.user_id=33

INSERT INTO SHOPPING_ORDERS VALUES(10,33, 9,'待付款', 22, 656.00, 12.00 ,'网银', to_date('2014/11/6 7:11:54','yyyy/mm/dd hh24;mi;ss'),'快递', 5626);
INSERT INTO SHOPPING_ORDERS VALUES(10,33, 9,'待发货', 63, 966.00, 22.00 ,'网银', to_date('2014/11/6 2:2:54','yyyy/mm/dd hh24;mi;ss'),'EMS', 5626);
INSERT INTO SHOPPING_ORDERS VALUES(10,33, 9,'待付款', 42, 345.00, 50.00 ,'网银', to_date('2014/11/6 2:5:54','yyyy/mm/dd hh24;mi;ss'),'快递', 5626);
INSERT INTO SHOPPING_ORDERS VALUES(10,33, 9,'待付款', 22, 634.00, 10.00 ,'网银', to_date('2014/11/6 5:5:54','yyyy/mm/dd hh24;mi;ss'),'EMS', 5626);
INSERT INTO SHOPPING_ORDERS VALUES(10,33, 9,'待发货', 21, 626.00, 22.00 ,'网银', to_date('2014/11/6 6:56:54','yyyy/mm/dd hh24;mi;ss'),'快递', 5626);
INSERT INTO SHOPPING_ORDERS VALUES(10,33, 9,'待发货', 65, 132.00, 18.00 ,'网银', to_date('2014/11/6 4:56:54','yyyy/mm/dd hh24;mi;ss'),'快递', 5626);
INSERT INTO SHOPPING_ORDERS VALUES(10,33, 9,'待付款', 23, 123.00, 10.00 ,'网银', to_date('2014/11/6 6:56:54','yyyy/mm/dd hh24;mi;ss'),'EMS', 5626);


---自动增长主键  序列 sequences
CREATE SEQUENCE SEQ_SHOPPING_ORDERS_ID
MINVALUE 1
MAXVALUE 999999999
START WITH 1
INCREMENT BY 1
CACHE 10
ORDER ; 
--create Trigger
--用户号 触发器 TRI_SHOPPING_ACCOUNT_ID
CREATE OR REPLACE  TRIGGER TRI_SHOPPING_ORDERS_ID
       BEFORE INSERT OR UPDATE OF orders_id ON SHOPPING_ORDERS
       FOR EACH ROW
BEGIN 
       SELECT SEQ_SHOPPING_ORDERS_ID.Nextval INTO:new.orders_id FROM dual;
END;

select * from SHOPPING_RECIEVER_ADDRESS where reciever_address_id =10
--4收货地址
=======
--4收货地址
>>>>>>> .r1543
CREATE TABLE SHOPPING_RECIEVER_ADDRESS
( 
  reciever_address_id NUMBER(10)PRIMARY KEY,
  user_id NUMBER(10) REFERENCES SHOPPING_USERS(user_id),
  reciever_address_name VARCHAR2(50) NOT NULL,
  reciever_address_region VARCHAR2(200) NOT NULL,--地区
  reciever_address_address varchar2(200) NOT NULL,--街道地址
  reciever_address_postcode NUMBER(6) NOT NULL,--邮编
  reciever_address_phone NUMBER(11) NOT NULL
)

--5购物篮
CREATE TABLE SHOPPING_SHOPPING_BASKET
(
   shopping_basket_id NUMBER(10) PRIMARY KEY,
   user_id number(10), 
   shopping_basket_name VARCHAR2(200),
   shopping_basket_price VARCHAR2(10),
   shopping_basket_image VARCHAR(30),
   shopping_basket_count NUMBER(10)--商品数量
)  
--用户id作为外键
ALTER TABLE SHOPPING_SHOPPING_BASKET 
   ADD CONSTRAINT FK_BASKET_USER_ID FOREIGN KEY(user_id) REFERENCES SHOPPING_USERS(user_id) ON DELETE CASCADE;

---自动增长主键  序列 sequences
DROP SEQUENCE SEQ_shopping_basket_id;

CREATE SEQUENCE SEQ_shopping_basket_id
MINVALUE 1
MAXVALUE 9999999
START WITH 1
INCREMENT BY 1
CACHE 20
ORDER;

--增加序列以后 如何往表中添加数据
TRUNCATE TABLE SHOPPING_SHOPPING_BASKET;

SELECT SEQ_shopping_basket_id.CURRVAL FROM dual;
SELECT SEQ_shopping_basket_id.Nextval FROM dual;


--触发器 triggers   表级  行级
-- 当插入主键的时候， 使用序列
--创建行级触发器
DROP TRIGGER TRI_shopping_basket_id;

CREATE OR REPLACE TRIGGER TRI_shopping_basket_id
   BEFORE INSERT OR UPDATE OF shopping_basket_id ON SHOPPING_SHOPPING_BASKET
   FOR EACH ROW
BEGIN
  SELECT SEQ_shopping_basket_id.Nextval  INTO:NEW.shopping_basket_id FROM DUAL;
END;
INSERT INTO SHOPPING_USERS   Values(100, null, null,'cxl','123','15965','121321','湖北',1132,'32@qq.com') ;
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(31, '五香牛肉粒120g','￥22.33', 'wuxiangniurou.png',3);
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(31, '蜂蜜小番茄','￥13.11', 'fengmixiaofanqie.png',1);
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
<<<<<<< .mine
                                                  VALUES(31, '鸭旽','￥28.03', 'yatun.png',2);                                                 
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(31, '风味鸭脖','￥20.71', 'fengweiyabo.png',3);
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(31, '山椒凤爪','￥17.58', 'shanjiaofengzhuan.png',1);
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(31, '紫署仔','￥6.46', 'zisuzai.png',2);
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(31, '小孩核仁','￥35.91', 'xiaogetaoren.png',2);
=======
                                                  VALUES(31, '鸭旽','￥28.03', 'yatun.png',2);
>>>>>>> .r1543
                                                  
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
<<<<<<< .mine
                                                  VALUES(33, '五香牛肉粒120g','￥22.33', 'wuxiangniurou.png',3);
=======
                                                  VALUES(31, '风味鸭脖','￥20.71', 'fengweiyabo.png',3);
>>>>>>> .r1543
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
<<<<<<< .mine
                                                  VALUES(33, '蜂蜜小番茄','￥13.11', 'fengmixiaofanqie.png',1);
=======
                                                  VALUES(31, '山椒凤爪','￥17.58', 'shanjiaofengzhuan.png',1);
>>>>>>> .r1543
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
<<<<<<< .mine
                                                  VALUES(33, '鸭旽','￥28.03', 'yatun.png',2);                                                
=======
                                                  VALUES(31, '紫署仔','￥6.46', 'zisuzai.png',2);
>>>>>>> .r1543
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
<<<<<<< .mine
                                                  VALUES(33, '风味鸭脖','￥20.71', 'fengweiyabo.png',3);
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(33, '山椒凤爪','￥17.58', 'shanjiaofengzhuan.png',1);
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(33, '紫署仔','￥6.46', 'zisuzai.png',2);
INSERT INTO SHOPPING_SHOPPING_BASKET(user_id, shopping_basket_name, shopping_basket_price,shopping_basket_image,shopping_basket_count) 
                                                  VALUES(33, '小孩核仁','￥35.91', 'xiaogetaoren.png',2); 
                                                
=======
  
                                                  VALUES(31, '小孩核仁','￥35.91', 'xiaogetaoren.png',2);
>>>>>>> .r1543
select * from SHOPPING_SHOPPING_BASKET where user_id = 31;  
           
delete from SHOPPING_SHOPPING_BASKET where rowid in
 (select rowid from SHOPPING_SHOPPING_BASKET where rowid not in 
         (select max(rowid) from SHOPPING_SHOPPING_BASKET t group by t.shopping_basket_name))
commit;
--6评论
CREATE TABLE SHOPPING_CATE_COMMENT
(
   cate_comment_id NUMBER(10) PRIMARY KEY,
   cate_id NUMBER(10) REFERENCES SHOPPING_CATE(cate_id),
   cate_comment_date DATE,
   cate_user_name Varchar2(50),
   cate_comment_context VARCHAR2(300),--评论内容
   cate_star_level  NUMBER(10)--评论星级
)
--评论id序列
create sequence seq_SHOPPING_COMMENT_id
increment by 1
start with 101
maxvalue 99999999
minvalue 101

drop sequence seq_SHOPPING_COMMENT_id
--评论触发器
create or replace trigger tri_SHOPPING_COMMENT_id
before insert on SHOPPING_CATE_COMMENT
for each row
  begin select seq_SHOPPING_COMMENT_id.nextval into:new.cate_comment_id from dual;
  end;
--插入评论数据
insert into SHOPPING_CATE_COMMENT values(101,1041,to_date('2014-10-30','yyyy/mm/dd'),'alex','不错的宝贝',4);
insert into SHOPPING_CATE_COMMENT values(101,1041,to_date('2014/8/30','yyyy-mm-dd'),'toalto','老板很好',3);
insert into SHOPPING_CATE_COMMENT values(101,1041,to_date('2014/8/30','yyyy-mm-dd'),'toalto','老板很好',3);
insert into SHOPPING_CATE_COMMENT values(101,1042,to_date('2014/9/30','yyyy-mm-dd'),'toalto','老板很好',3);
insert into SHOPPING_CATE_COMMENT values(101,1042,to_date('2014/8/30','yyyy-mm-dd'),'britney','差评',2);
insert into SHOPPING_CATE_COMMENT values(101,1043,to_date('2014/9/5','yyyy-mm-dd'),'alex','好吃',3);
insert into SHOPPING_CATE_COMMENT values(101,1043,to_date('2012/9/30','yyyy-mm-dd'),'toalto','番茄好甜',5);
insert into SHOPPING_CATE_COMMENT values(101,1044,to_date('2011/9/5','yyyy-mm-dd'),'alex','牛肉粒很香',3);
insert into SHOPPING_CATE_COMMENT values(101,1044,to_date('2012/9/30','yyyy-mm-dd'),'toalto','不好吃',2);
insert into SHOPPING_CATE_COMMENT values(101,1045,to_date('2011/9/5','yyyy-mm-dd'),'alex','牛肉粒很香',3);
insert into SHOPPING_CATE_COMMENT values(101,1045,to_date('2012/9/30','yyyy-mm-dd'),'toalto','不好吃',2);
insert into SHOPPING_CATE_COMMENT values(101,1045,to_date('2012/9/30','yyyy-mm-dd'),'toalto','好吃',5);
insert into SHOPPING_CATE_COMMENT values(101,1046,to_date('2011/9/5','yyyy-mm-dd'),'alex','牛肉粒很香',3);
insert into SHOPPING_CATE_COMMENT values(101,1046,to_date('2012/9/30','yyyy-mm-dd'),'toalto','不好吃',2);
insert into SHOPPING_CATE_COMMENT values(101,1046,to_date('2012/9/30','yyyy-mm-dd'),'toalto','好吃',5);
insert into SHOPPING_CATE_COMMENT values(101,1047,to_date('2012/9/30','yyyy-mm-dd'),'toalto','不好吃',2);
insert into SHOPPING_CATE_COMMENT values(101,1047,to_date('2012/9/30','yyyy-mm-dd'),'toalto','好吃',5);
delete from SHOPPING_CATE_COMMENT
drop table SHOPPING_CATE_COMMENT
select * from SHOPPING_CATE_COMMENT
commit;
--7爱吃
CREATE TABLE SHOPPING_LIKE_EAT
(
  like_eat_id NUMBER(10) PRIMARY KEY,
  cate_id NUMBER(10) REFERENCES SHOPPING_CATE(cate_id)
)
--8图片
CREATE TABLE SHOPPING_CATE_IMAGE
(
  cate_image_id NUMBER(10)PRIMARY KEY,
  cate_id refrences SHOPPING_CATE(cate_id),
  cate_image_path01 VARCHAR2(200), 
  cate_image_path02 VARCHAR2(200),
  cate_image_path03 VARCHAR2(200),
  cate_image_path04 VARCHAR2(200)  
)
--图片表插入数据
insert into SHOPPING_CATE_IMAGE values(1,'wuxiangniurou.png','meat.jpg','meat.jpg','meat.jpg');
insert into SHOPPING_CATE_IMAGE values(2,'fengmixiaofanqie.png','meat.jpg','meat.jpg','meat.jpg');
insert into SHOPPING_CATE_IMAGE values(3,'shanjiaofengzhuan','meat.jpg','meat.jpg','meat.jpg');
insert into SHOPPING_CATE_IMAGE values(4,'wuxiangniurou.png','meat.jpg','meat.jpg','meat.jpg');
insert into SHOPPING_CATE_IMAGE values(5,'xiaogetaoren.png','meat.jpg','meat.jpg','meat.jpg');
drop table SHOPPING_CATE_IMAGE;
delete from SHOPPING_CATE_IMAGE;
select * from SHOPPING_CATE_IMAGE
update SHOPPING_CATE_IMAGE set cate_image_path01 = 'guazi1.png',cate_image_path02 = 'guazi2.png',cate_image_path03 ='guazi3.png',cate_image_path04 ='guazi4.png' where cate_image_id = 3

commit;

--9商品
CREATE TABLE SHOPPING_CATE
(
    cate_id NUMBER(10) PRIMARY KEY,
    cate_name VARCHAR2(200) ,
    cate_inventory NUMBER(10),--库存
    cate_sale_count NUMBER(10),--销售数量
    cate_description VARCHAR2(200),--美食描述
    cate_oldprice NUMBER (10,2),
    cate_price NUMBER(10,2),
    cate_image_id NUMBER(10) REFERENCES  SHOPPING_CATE_IMAGE(cate_image_id),--美食图片
    category_id NUMBER(10) REFERENCES SHOPPING_CATEGORY(category_id),--美食种类
    flavor_category_id NUMBER(10)REFERENCES SHOPPING_FLAVOR_CATEGORY(flavor_category_id) ,--美食口味种类
    cate_sale_info VARCHAR2(200) --美食促销信息
)

--食品id序列
create sequence seq_SHOPPING_CATE_id
increment by 1
start with 1001
maxvalue 99999999
minvalue 1001

--食品触发器
create or replace trigger tri_SHOPPING_CATE_id
before insert on SHOPPING_CATE
for each row
  begin select seq_SHOPPING_CATE_id.nextval into:new.cate_id from dual;
  end;

--商品表插入数据

insert into SHOPPING_CATE values(1001,'粒粒香牛肉粒120g',100,10,'肉质鲜嫩 口感微辣',13.20,10,5,1,1,'手机专享价');
insert into SHOPPING_CATE values(1002,'蜂蜜小番茄',10,5,'蜂蜜清香 营养美味',13.20,5,2,1,1,'会员专享价');
insert into SHOPPING_CATE values(1003,'鸭旽',80,15,'肉质鲜嫩 口感微辣',13.20,15,3,1,1,'手机专享价');
insert into SHOPPING_CATE values(1003,'风味鸭脖',100,7,'肉质鲜嫩 口感微辣',13.20,7,4,1,1,'淘宝专享价');
insert into SHOPPING_CATE values(1003,'山椒凤爪',100,20,'肉质鲜嫩 口感微辣',13.20,20,2,1,1,'会员专享价');
insert into SHOPPING_CATE values(1003,'紫署仔',100,35,'肉质鲜嫩 口感微辣',13.20,35,1,1,1,'淘宝专享价');
insert into SHOPPING_CATE values(1003,'小孩核仁',100,13,'肉质鲜嫩 口感微辣',13.20,13,4,1,1,'淘宝专享价');
commit;

update SHOPPING_CATE set cate_sale_count = 1100 where cate_id = 1043 

drop table SHOPPING_CATE

select * from SHOPPING_CATE

delete from SHOPPING_CATE

select t1.*,t2.*  from SHOPPING_CATE t1,SHOPPING_CATE_IMAGE t2  where t1.cate_image_id= t2.cate_image_id and t1.cate_id = 1041
select t1.*,t2.*  from SHOPPING_CATE t1,SHOPPING_CATE_IMAGE t2  where t1.cate_image_id= t2.cate_image_id and t1.cate_id = 1041
--10商品种类
CREATE TABLE SHOPPING_CATEGORY
(
     category_id NUMBER(10) PRIMARY KEY,
     category_name VARCHAR2(200)NOT NULL,
     category_image VARCHAR2(200) NOT NULL, 
     category_description VARCHAR2(200) NOT NULL
)
--商品种类表插入数据
insert into SHOPPING_CATEGORY values(1,'肉类即食','meat.jpg','牛肉 猪肉');
commit;
drop table SHOPPING_CATEGORY
--11商品口味种类
CREATE TABLE SHOPPING_FLAVOR_CATEGORY
(
  flavor_category_id NUMBER(10)PRIMARY KEY,
  flavor_category_name VARCHAR2(200)NOT NULL,
  flavor_category_image VARCHAR2(200)NOT NULL,
  flavor_category_description VARCHAR2(200) NOT NULL   
)
--商品口味种类表插入数据
insert into SHOPPING_FLAVOR_CATEGORY values(1,'咸味','meat.jpg','咸味 椒盐');
commit;
drop table SHOPPING_FLAVOR_CATEGORY;

--12充值记录
CREATE TABLE SHOPPING_RECHARGE_RECORD
(
     recharge_record_id NUMBER(10)PRIMARY KEY,
     recharge_record_date DATE 

)
--13消费记录
CREATE TABLE SHOPPING_CONSUME_RECORD
(
   consume_record_id NUMBER(10)PRIMARY KEY,
   consume_record_date DATE
)
--14美味足迹
CREATE TABLE SHOPPING_CATE_TRACK
(
   cate_track_id NUMBER(10)PRIMARY KEY,
   cate_id NUMBER(10) REFERENCES SHOPPING_CATE(cate_id)
)

--15管理员
create table shopping_manager 
(
  manager_id number(10) primary key,
  manager_name varchar2(50) not null,
  manager_password varchar2(50) not null,
  manager-right_id number(10) references shopping_manager_right(manager_right_id)
)

--16管理员权限
create table shopping_manager_right
(
  manager_right_id number(10) primary key,
  manager_right_name varchar2(50) not null,
  manager_right_description varchar2(200)
)




