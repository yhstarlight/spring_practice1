create table gongji(
	gongji_no number(38) primary key,
	gongji_name varchar2(30) not null, --작성자
	gongji_title varchar2(200) not null, --공지 제목
	gongji_pwd varchar2(20) not null, --공지 비번
	gongji_cont varchar2(4000) not null, --공지 내용
	gongji_hit number(38) default 0, --조회수
	gongji_date date --공지 등록 날짜
);

select * from gongji order by gongji_no desc;

create sequence g_no_seq
start with 1 --1부터 시작
increment by 1 --1씩 증가
nocache; --임시 메모리 쓰지 말라

--시퀀스 번호값
select g_no_seq.nextval from dual;