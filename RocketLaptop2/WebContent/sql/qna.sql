drop table qna cascade constraints;

CREATE TABLE QNA(
	QNA_NUM			NUMBER, 		--글 번호
	QNA_NAME		VARCHAR2(30),	--작성자
	QNA_PASS		VARCHAR2(30),	--비밀번호
	QNA_SUBJECT		VARCHAR2(300),  --제목
	QNA_CONTENT		VARCHAR2(4000), --내용
	QNA_FILE		VARCHAR2(50),   --첨부될 파일명
	QNA_RE_REF		NUMBER,  		--답변 글 작성시 참조되는 글의 번호
	QNA_RE_LEV		NUMBER,  		--답변 글의 깊이(원문 글:0, 답변:1, 답변의 답변2)
	QNA_RE_SEQ		NUMBER,  		--답변 글의 순서(원문 글 기준으로 보여주는 순서)
	QNA_READCOUNT 	NUMBER,      	--글의 조회수
	QNA_DATE DATE default sysdate,     --글의 작성 날짜
	PRIMARY KEY(QNA_NUM)
);

select * from qna;

update qna
set qna_date = TO_DATE('2021-08-19 12:00:00', 'YYYY-MM-DD HH:MI:SS')
where qna_num = 4;