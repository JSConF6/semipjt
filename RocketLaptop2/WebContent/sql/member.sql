drop table member;
--1. index.jsp에서 시작합니다.
--2. 관리자계정 admin, 비번 1234를 만듭니다.
--3. 사용자 계정을 3개 만듭니다.

create table member(
	user_id			varchar2(20),
	user_password	varchar2(20),
	user_password1	varchar2(10),
	user_name		varchar2(20),
	user_gender		varchar2(20),
	user_email		varchar2(20),
	user_phone	    number(20),
    user_address1   number(10),
    user_address1   varchar2(100),
    user_jumin      varchar2(13)
	PRIMARY KEY(user_id)
);

--Board_Ajax 프로젝트에 추가 --
alter table member
add (memberfile VARCHAR2(50));

select * from member;
create table member_copy
as
select * from member;