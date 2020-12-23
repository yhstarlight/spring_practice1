create table tbl_reply(
	rno number(38) primary key
	,bno number(38) default 0
	-- 외래키 제약조건으로 추가 설정, 제약조건은 굳이 번호값을 저장하지 않아도 기본값 0 저장
	,replyer varchar2(100) not null
	,replytext varchar2(4000) not null
	,regdate date
	,updatedate date
);

select * from tbl_reply order by rno desc;

alter table tbl_reply add constraint fk_board foreign key(bno) references tbl_board(bno); 
-- 주인 테이블의 tbl_board의 기본키 컬럼bno를 참조

-- 댓글 번호값이 저장된 시퀀스 생성
create sequence rno_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache; --임시 저장소를 사용하지 않겠다.

--시퀀스 다음 번호값 확인
select rno_seq.nextval from dual;
--dual 테이블 오라클 설치시 설치되는 임시테이블로 시퀀스 번호값 확인용도,
--오라클 함수 값 확인 용도, 연산결과값 확인용도 등으로 활용됨

select sysdate from dual;