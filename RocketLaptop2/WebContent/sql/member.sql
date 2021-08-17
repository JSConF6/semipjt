drop table member;
--1. index.jsp에서 시작합니다.
--2. 오라클DB계정 laptop, 비번1234를 만듭니다.  
--3. 관리자계정 admin, 비번 1234를 만듭니다.
--   일반계정 12345, 비번12345을 만듭니다.
--   일반계정 abcde, 비번 abcde을 만듭니다.
--   일반계정 korea, 비번 korea을 만듭니다. 

create table member(
	user_id			    varchar2(20),
	user_password	    varchar2(20),
	user_name		    varchar2(20),
	user_datebirth      number(8),
	user_gender		    varchar2(3),
	user_email		    varchar2(30),
	user_phone	        varchar2(20),
    user_address1       varchar2(5),
    user_address2       varchar2(40),
    user_memberfile		varchar2(20),
    user_joindate       varchar2(20),
	PRIMARY KEY(user_id)
);

select * from member;
--Board_Ajax 프로젝트에 추가 --
alter table member
add (memberfile VARCHAR2(50));

select * from member;
create table member_copy
as
select * from member;