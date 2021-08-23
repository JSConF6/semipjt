CREATE TABLE MEMBER(
	USER_ID VARCHAR2(20) -- 회원 아이디
	CONSTRAINT PK_USER_ID PRIMARY KEY,
	USER_PASSWORD VARCHAR2(20) NOT NULL, -- 회원 비밀번호
	USER_NAME VARCHAR2(20) NOT NULL, -- 회원 이름
	USER_BIRTHDATE NUMBER(8) NOT NULL, -- 회원 생년월일
	USER_GENDER VARCHAR2(20) NOT NULL, -- 회원 성별
	USER_EMAIL VARCHAR2(20) NOT NULL, -- 회원 이메일
	USER_PHONE VARCHAR2(20) NOT NULL, -- 회원 전화번호
	USER_ADDRESS1 VARCHAR2(5) NOT NULL, -- 회원 우편번호
	USER_ADDRESS2 VARCHAR2(100) NOT NULL, -- 회원 주소
	USER_MEMBERFILE VARCHAR2(100), -- 회원 프로필 이미지
	USER_JOINDATE DATE -- 회원 가입날짜
);

insert into member
values('admin', 1234, '관리자', 19910101, '남자', 'admin@naver.com', '010-1111-1111', '31006', '서울 종로구', 'product.png', sysdate);

insert into member
values('jsp', 1234, '제이', 19940101, '여자', 'jsp@naver.com', '010-1111-1111', '01006', '서울 종로구', 'product.png', sysdate);

insert into member
values('java', 1234, '자바', 19960101, '남자', 'java@naver.com', '010-1111-1111', '31006', '서울 종로구', 'product.png', sysdate);

insert into member
values('servlet', 1234, '서블릿', 19920101, '여자', 'servlet@naver.com', '010-1111-1111', '31006', '서울 종로구', 'product.png', sysdate);

insert into member
values('javascript', 1234, '자바스크', 19980101, '남자', 'javascript@naver.com', '010-1111-1111', '31006', '서울 종로구', 'product.png', sysdate);

insert into member
values('spring', 1234, '스프링', 19970101, '여자', 'spring@naver.com', '010-1111-1111', '31006', '서울 종로구', 'product.png', sysdate);

insert into member
values('vue', 1234, '뷰', 19960101, '남자', 'vue@naver.com', '010-1111-1111', '001199', '서울 종로구', 'product.png', sysdate);

update MEMBER
set user_memberfile = 'profile.png'
where user_memberfile is null;

select *
from MEMBER;

delete member;

drop table member;