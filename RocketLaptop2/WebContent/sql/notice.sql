create table notice(
	NOTICE_NUM number(3) not null,
	NOTICE_TITLE varchar2(100) not null,
	NOTICE_CONTENT varchar2(4000) not null,
	NOTICE_DATE date,
	NOTICE_READCOUNT number(4) not null,
	PRIMARY KEY(NOTICE_NUM)
);

drop table notice;

select *
from notice;

select *
from NOTICE
where notice_num in (7, 6, 5);

select nvl(max(nno), 0)+1 from notice;

-- 공지사항 리스트 쿼리 작성날짜 순으로 내림차순 정렬
select *
from (select rownum rnum, notice_num, notice_title, notice_content, notice_date, notice_readcount
	  from (select *
	  		from notice
	  		order by notice_date desc)
	  )
where rnum >= 1 and rnum <= 10;

-- 공지사항 검색할때 쓰는 쿼리
select *
from (select rownum rnum, notice_num, notice_title, notice_content, notice_date, notice_readcount
	  from (select *
	  		from notice
	  		where NOTICE_date like '%10%'
	  		order by notice_date desc)
	  )
where rnum >= 1 and rnum <= 10;

select * 
from (select rownum rnum, notice_num, notice_title, notice_content, notice_date, notice_readcount
	  from (select *
	  	    from notice 
	        where NOTICE_title like '%공지%' 
	        order by notice_date desc)
	  )
where rnum between 1 and 5;

