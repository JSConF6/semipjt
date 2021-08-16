drop table member;
--1. index.jsp에서 시작합니다.
--2. 관리자계정 admin, 비번 1234를 만듭니다.
--3. 일반계정 1234, 비번1234을 만듭니다.
--   일반계정 7777, 비번 7777을 만듭니다.

create table member(
	user_id			varchar2(20),
	user_password	varchar2(20),
	user_name		varchar2(20),
	user_datebirth  number(8),
	user_gender		varchar2(20),
	user_email		varchar2(20),
	user_phone	    varchar2(20),
    user_address1   number(5),
    user_address2   varchar2(40),
    memberfile		varchar2(20),
	PRIMARY KEY(user_id)
);

--Board_Ajax 프로젝트에 추가 --
alter table member
add (memberfile VARCHAR2(50));

select * from member;
create table member_copy
as
select * from member;