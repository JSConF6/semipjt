drop table bbs cascade constraints;
CREATE TABLE BOARD(
	BOARD_NO		NUMBER, 		--글 번호
	BOARD_TITLE		VARCHAR2(50),	--게시글 제목
	USER_ID	     	VARCHAR2(20),	--회원 ID
	BOARD_DATE DATE default sysdate,--게시글 날짜
	BOARD_CONTENT	VARCHAR2(4000), --내용
	BOARD_PAGECOUNT	NUMBER(3),      --페이지수
	BOARD_READCOUNT	NUMBER(3),  	--조회수
	BOARD_RE_REF    NUMBER,         --답변 글 작성시 참조되는 글의번호
	BOARD_RE_LEV	NUMBER,  		--답변 글의 깊이(원문 글:0, 답변:1, 답변의 답변2)
	BOARD_RE_SEQ	NUMBER,  		--답변 글의 순서(원문 글 기준으로 보여주는 순서)
	PRIMARY KEY(BOARD_NO)
);
select * from board;
