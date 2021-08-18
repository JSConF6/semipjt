create table order_detail(
	ORDER_DE_NUM NUMBER(15) NOT NULL
	CONSTRAINT PK_ORDER_DE_NUM PRIMARY KEY,
	ORDER_NUM VARCHAR2(20) NOT NULL,
	PRODUCT_CODE VARCHAR2(6) NOT NULL,
	CART_STOCK NUMBER(3) NOT NULL
);

alter table ORDER_DETAIL
add CONSTRAINT FK_ORDER_NUM FOREIGN KEY(ORDER_NUM)
REFERENCES ORDER_TB(ORDER_NUM)
ON DELETE CASCADE;

alter table ORDER_DETAIL
add CONSTRAINT FK_ORDER_PRODUCT_CODE FOREIGN KEY(PRODUCT_CODE)
REFERENCES PRODUCT(PRODUCT_CODE);

SELECT *
FROM ORDER_DETAIL;

create sequence order_detail_seq;

insert into order_detail (order_de_num, order_num, product_code, cart_stock)
	select order_detail_seq.nextval, '210818_118103', product_code, cart_stock
	from cart
	where user_id = 'admin';

drop table order_detail;

drop sequence order_detail_seq;