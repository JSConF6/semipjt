DROP TABLE PRODUCT;

CREATE TABLE PRODUCT(
	PRODUCT_CODE VARCHAR2(6) -- 상품 코드
	CONSTRAINT PK_PRODUCT_CODE PRIMARY KEY,
	CATEGORY_CODE VARCHAR2(50), -- 카테고리 코드
	PRODUCT_NAME VARCHAR2(100) NOT NULL, -- 상품명
	PRODUCT_PRICE NUMBER(7) NOT NULL, -- 상품가격
	PRODUCT_DETAILS VARCHAR2(1000), -- 상세정보
	PRODUCT_STATUS VARCHAR2(10) CHECK(PRODUCT_STATUS IN('신규', '중고')), -- 상태(신규, 중고)
	PRODUCT_IMAGE VARCHAR2(500) NOT NULL, -- 이미지
	PRODUCT_SALES NUMBER(3) DEFAULT 0, -- 판매량
	PRODUCT_DATE DATE, -- 상품등록일
	CONSTRAINT FK_CATEGORY_CODE FOREIGN KEY(CATEGORY_CODE) 
	REFERENCES CATEGORY(CATEGORY_CODE)
	ON DELETE CASCADE
);

insert into PRODUCT
values('A00001', 'ctg_001', '삼성 노트북', 1500000, '삼성에서 판매하는 노트북 입니다.', 10, '신규', 'PRODUCT.JPG', 0, SYSDATE);

insert into PRODUCT
values('A00002', 'ctg_002', 'LG 노트북', 1200000, 'LG에서 판매하는 노트북 입니다.', 1, '중고', 'PRODUCT.JPG', 0, SYSDATE);

insert into PRODUCT
values('A00003', 'ctg_003', '맥북', 1900000, '애플에서 판매하는 노트북 입니다.', 6, '신규', 'PRODUCT.JPG', 0, SYSDATE);

select *
from product;

-- 상품 전체 조회 쿼리
select *
from (select rownum rnum, p.*
	  from (select p.product_code, p.category_code, c.category_name, p.product_name,
	  		p.product_price, p.product_details, p.product_stock, p.product_status,
	  		p.product_image, p.product_sales, p.product_date
	  		from product p, category c
	  		where p.category_code = c.category_code
	  		order by product_code asc) p
	  )
where rnum >= 1 and rnum <= 10;

--검색할때 사용하는 쿼리
select *
from (select rownum rnum, p.*
	  from (select p.product_code, p.category_code, c.category_name, p.product_name,
	  		p.product_price, p.product_details, p.product_stock, p.product_status,
	  		p.product_image, p.product_sales, p.product_date
	  		from product p, category c
	  		where p.category_code = c.category_code
	  		order by product_code asc) p
	  where category_name like '%SAMSUNG%'
	  )
where rnum >= 1 and rnum <= 10;

-- 상품 상세정보 쿼리
select p.*
from (select p.product_code, p.category_code, c.category_name, p.product_name,
	  p.product_price, p.product_details, p.product_stock, p.product_status,
	  p.product_image, p.product_sales, p.product_date
	  from product p, category c
	  where p.category_code = c.category_code) p
where product_code = 'A00001';

-- 베스트 상품 정렬 쿼리
select *
from (select rownum rnum, p.*
	  from (select p.product_code, p.category_code, c.category_name, p.product_name,
	  		p.product_price, p.product_details, p.product_stock, p.product_status,
	  		p.product_image, p.product_sales, p.product_date
	  		from product p, category c
	  		where p.category_code = c.category_code
	  		order by product_sales desc) p
	  )
where rnum >= 1 and rnum <= 3;

select *
from (select rownum rnum, p.*
	  from (select p.product_code, p.category_code, c.category_name, p.product_name,
	  		p.product_price, p.product_details, p.product_stock, p.product_status,
	  		p.product_image, p.product_sales, p.product_date
	  		from product p, category c
	  		where p.category_code = c.category_code
	  		order by product_sales desc) p
	  )
where product_sales != 0;

-- 새로운 상품 정렬 쿼리
select *
from (select rownum rnum, p.*
	  from (select p.product_code, p.category_code, c.category_name, p.product_name,
	  		p.product_price, p.product_details, p.product_stock, p.product_status,
	  		p.product_image, p.product_sales, p.product_date
	  		from product p, category c
	  		where p.category_code = c.category_code
	  		order by product_date desc) p
	  )
where rnum >= 1 and rnum <= 3;

select *
from (select rownum rnum, p.*
	  from (select p.product_code, p.category_code, c.category_name, p.product_name,
	  		p.product_price, p.product_details, p.product_stock, p.product_status,
	  		p.product_image, p.product_sales, p.product_date
	  		from product p, category c
	  		where p.category_code = c.category_code
	  		order by product_date desc) p
	  )
where product_date between TO_DATE('2021/08/17') and TO_DATE('2021/08/18');

update PRODUCT
set product_sales = 5
where product_code = 'A00001';

update PRODUCT
set product_sales = 10
where product_code = 'A00003';

update PRODUCT
set product_sales = 3
where product_code = 'A00004';

update PRODUCT
set product_sales = 7
where product_code = 'A00006';

update PRODUCT
set product_sales = 8
where product_code = 'A00008';

delete PRODUCT;

DROP TABLE PRODUCT CASCADE CONSTRAINT;