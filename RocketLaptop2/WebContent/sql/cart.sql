create table cart(
	CART_NUM NUMBER(15) NOT NULL,
	PRODUCT_CODE VARCHAR2(6) NOT NULL,
	USER_ID VARCHAR2(20) NOT NULL,
	CART_STOCK NUMBER(3) NOT NULL,
	ADDDATE DATE DEFAULT SYSDATE,
	PRIMARY KEY(CART_NUM)
);

create sequence cart_seq;

ALTER TABLE CART
ADD CONSTRAINT CART_USERID_FK FOREIGN KEY(USER_ID)
REFERENCES MEMBER(USER_ID)
ON DELETE CASCADE;

ALTER TABLE CART
ADD CONSTRAINT CART_PRODUCT_CODE_FK FOREIGN KEY(PRODUCT_CODE)
REFERENCES PRODUCT(PRODUCT_CODE)
ON DELETE CASCADE;

INSERT INTO CART(CART_NUM, PRODUCT_CODE, USER_ID, CART_STOCK)
VALUES(1, 'A00001', 'admin', 3);

delete cart;

SELECT *
FROM CART;

insert into cart (cart_num, product_code, user_id, cart_stock)
values(6, 'A00001', 'admin', 1);

insert into cart (cart_num, product_code, user_id, cart_stock)
values(7, 'A00001', 'admin', 2);

select c.cart_num, c.user_id, c.product_code, c.cart_stock,
	   c.adddate, p.product_name, p.product_price, p.product_image
from cart c, product p
where c.product_code = p.product_code
and c.user_id = 'admin'
order by c.cart_num;

delete CART
where cart_num in(6, 7)
and user_id = 'admin';

delete cart where cart_num in (6, 7) and user_id = 'admin';


DROP TABLE CART;

DROP SEQUENCE CART_SEQ;