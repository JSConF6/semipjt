drop table member;
--1. index.jsp에서 시작합니다.
--2. 오라클DB계정 laptop, 비번1234를 만듭니다.  
--3. 관리자계정 admin, 비번 1234를 만듭니다.
--   일반계정 12345, 비번12345을 만듭니다.
--   일반계정 abcde, 비번 abcde을 만듭니다.
--   일반계정 korea, 비번 korea을 만듭니다. 


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



select * from member;

alter table member rename column user_datebirth to user_birthdate;

--Board_Ajax 프로젝트에 추가(회원가입 시 반영으로 불필요해짐 --
--alter table member
--add (memberfile VARCHAR2(50));

--select * from member;
--create table member_copy
--as
--select * from member;