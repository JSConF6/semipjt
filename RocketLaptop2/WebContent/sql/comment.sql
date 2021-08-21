drop table comm cascade constraints;
CREATE TABLE comm(
 num		 number		  primary key,
 user_id	 varchar2(30) references member(user_id),
 content	 varchar2(200),
 reg_date	 date,
 comment_qna_num number	references qna(qna_num) on delete cascade,
 										-- comm 테이블이 참조하는 보드 글 번호
 comment_re_lev number(1) check(comment_re_lev in (0,1,2)), --원문은 자신 글번호, 답글이면 원문 글번호
 comment_re_seq number,	-- 원문이면 0
 comment_re_ref number	-- 원문은 자신 글번호, 답글이면 원문 글번호
);
-- 게시판 글이 삭제되면 참조하는 댓글도 삭제됩니다. --

drop sequence comm_seq;

-- 시퀀스를 생성합니다
create sequence comm_seq;

select * 
from comm;

delete comm;

--member에 있는 memberfile까지 조회해 봅시다
select comm.*, member.user_memberfile
from comm inner join member
on comm.user_id=member.user_id
where comment_qna_num = 2
order by comment_re_ref desc,
comment_re_seq asc;

select comm.num, comm.user_id, comm.content, comm.reg_date, comm.comment_re_lev,
	   comm.comment_re_seq, comm.comment_re_ref, member.user_memberfile
from comm inner join member
	 on comm.user_id = member.user_id
where comm.comment_qna_num = 2
order by comm.comment_re_ref desc,
comm.comment_re_seq asc;

