create table ORDER_TB(
	ORDER_NUM VARCHAR2(20)
	CONSTRAINT PK_ORDER_NUM PRIMARY KEY,
	USER_ID VARCHAR2(20) NOT NULL,
	ORDER_NAME VARCHAR2(20) NOT NULL,
	USER_ADDRESS1 VARCHAR2(10) NOT NULL,
	USER_ADDRESS2 VARCHAR2(100) NOT NULL,
	USER_ADDRESS3 VARCHAR2(100) NOT NULL,
	ORDER_PHONE VARCHAR2(20) NOT NULL,
	ORDER_TOTALPRICE NUMBER(10) NOT NULL,
	ORDER_PAYMENT VARCHAR2(50) NOT NULL,
	ORDER_DELIVERY VARCHAR2(50) default '배송준비',
	ORDER_DATE DATE DEFAULT SYSDATE,
	CONSTRAINT FK_ORDER_USER_ID FOREIGN KEY(USER_ID)
	REFERENCES MEMBER(USER_ID)
	ON DELETE CASCADE
);

SELECT *
FROM ORDER_TB;

select *
from order_detail;

-- 특정 id 주문목록 검색 쿼리
select o.order_num, o.user_id, o.order_name, o.user_address1, o.user_address2, o.user_address3,
	   o.order_phone, o.order_totalprice, o.order_payment, o.order_delivery, o.order_date,
	   d.order_de_num, d.product_code, d.cart_stock
from order_tb o, order_detail d
where o.order_num = d.order_num
and o.user_id = 'admin';

--
select *
from (select rownum rnum, order_num, user_id, order_name, user_address1, user_address2, user_address3,
	  		 order_phone, order_totalprice, order_payment, order_delivery, order_date
	  from order_tb o
	  where o.user_id = 'java2'
	  order by o.order_date asc)
where rnum >= 1 and rnum <= 5;

-- 특정 id의 주문상세보기 검색 쿼리
select o.order_num, o.user_id, o.order_name, o.user_address1, o.user_address2, o.user_address3,
	   o.order_phone, o.order_totalprice, o.order_payment, o.order_delivery, o.order_date,
	   d.order_de_num, d.product_code, d.cart_stock, p.product_name, p.product_image, p.product_price 
from order_tb o 
	 inner join order_detail d
		on o.order_num = d.order_num
	 inner join product p
		on p.product_code = d.product_code
where o.user_id = 'admin'
and o.order_num = '210818_295602';

-- 주문목록 전체 검색 쿼리
select *
from (select rownum rnum, order_num, user_id, order_name, user_address1, user_address2, user_address3,
	  		 order_phone, order_totalprice, order_payment, order_delivery, order_date
	  from order_tb
	  order by order_date desc)
where rnum >= 1 and rnum <= 5;

-- 검색 쿼리
select count(*)
from (select rownum rnum, order_num, user_id, order_name, user_address1, user_address2, user_address3,
	  		 order_phone, order_totalprice, order_payment, order_delivery, order_date
	  from order_tb
	  where order_num like '%210819%'
	  order by order_date desc);

select *
from (select rownum rnum, order_num, user_id, order_name, user_address1, user_address2, user_address3,
	  		 order_phone, order_totalprice, order_payment, order_delivery, order_date
	  from order_tb
	  where order_num like '%210819%'
	  order by order_date desc)
where rnum >= 1 and rnum <= 5;

--
select p.product_code, d.product_code, p.product_stock, d.cart_stock
from order_tb o 
	 inner join order_detail d
		on o.order_num = d.order_num
	 inner join product p
		on p.product_code = d.product_code
where o.user_id = 'java2'
and o.order_num = '210819_975349'
and o.order_delivery = '배송중'
and p.product_code = d.product_code;

select *
from ORDER_DETAIL
where order_num = '210819_975349';

delete order_tb;

DROP TABLE ORDER_TB;
