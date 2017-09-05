--*******************
-- 2017-09-04
-- [1]MAJOR_TAB
-- [2]SUBJECT_TAB
-- [3]MEMBER_TAB
-- [4]PROF_TAB
-- [5]GRADE_TAB
-- [6]BOARD_TAB
--*******************
	
--*******************
--
-- [1]MAJOR TABLE
-- 2017-09-04
-- 전공 테이블
-- major_id, title
--
--*******************
--DDL
CREATE TABLE major(
	major_id VARCHAR(10),
	title VARCHAR(10),
	subj_id varchar(10),
	member_id varchar(10),
	primary key(major_id)
);
--DML
drop table major;
SELECT * FROM major;
insert into major values('com','com', 'com');
ALTER TABLE major add subj_id varchar2(10);
delete from major where member_id='hhh';
update MAJOR set subj_id='c' where major_id = '2754';

--*******************
--
-- [2]SUBJECT TABLE
-- 2017-09-04
-- 과목 테이블
-- subj_id, major_id, title
--
--*******************
--DDL
CREATE TABLE subject(
	subj_id VARCHAR(10),
	title VARCHAR(10),
	primary key(subj_id)
);
--DML
SELECT * FROM subject;
insert into subject(subj_id, title) values('python', 'python');
insert into subject(subj_id, title) values('java', '자바');
insert into subject(subj_id, title) values('c', 'C');
insert into subject(subj_id, title) values('css', 'CSS');
insert into subject(subj_id, title) values('javascript', '자바스크립트');
insert into subject(subj_id, title) values('html', 'HTML');
insert into subject(subj_id, title) values('sql', 'SQL');


--*******************
-- [3]MEMBER TABLE
-- 2017-09-04
-- 회원관리 테이블
-- member_id, pwd ,name, ssn, regdate, 
-- phone, email, major_id, profile
--*******************
--DDL
CREATE table Member(
	member_id VARCHAR(10), 
	pwd VARCHAR(10), 
	name VARCHAR(20), 
	ssn VARCHAR(15),
	regdate DATETIME,
	phone VARCHAR(13),
	email VARCHAR(30),
	profile VARCHAR(30),
	primary key(member_id)
);
--기존 테이블에 칼럼 추가 & 칼럼명 수정
DROP TABLE MEMBER;
--칼럼명 수정은 다른 테이블에서 그 칼럼을 fk로 쓸 경우 테이블마다 칼럼명 수정해줘야 함, 자동X
ALTER TABLE member ADD profile VARCHAR2(30);
ALTER TABLE member rename column id to member_id;
--기존 테이블에 없는 칼럼을 추가해서 fk로 만드는 로직
ALTER TABLE member
ADD CONSTRAINT member_fk_major
FOREIGN KEY (major_id) REFERENCES major(major_id);
--DML

insert into Member(MEMBER_ID, pwd ,name, ssn, regdate, phone, email, profile) 
values('kimprof','1234','김교수','710101-1111244',now(), '010-1234-5678','kimprof@test.net', 'prof.jpg');

insert into Member(MEMBER_ID, pwd ,name, ssn, regdate, phone, email, profile) 
values('naprof','1234','나교수','640101-1111244',now(), '010-1234-5678','kimprof@test.net', 'prof.jpg');

insert into Member(MEMBER_ID, pwd ,name, ssn, regdate, phone, email, profile) 
values('ohprof','1234','오교수','580101-1111244',now(), '010-1234-5678','ohprof@test.net', 'prof.jpg');


UPDATE member SET name='bbb', phone='bbb' WHERE member_id='aaaa';
UPDATE member SET name='김민주' WHERE member_id='min';
DELETE FROM MEMBER WHERE member_id='hhh';
select * from member order by ssn asc;
SELECT * FROM MEMBER;
SELECT * FROM MEMBER WHERE member_id='you';
--*******************
-- [4]PROF TABLE
-- 2017-09-04
-- 교수 테이블
-- member_id, salary
--*******************
--DDL
CREATE TABLE prof(
	member_id VARCHAR(10),
	salary VARCHAR(10),
	primary key(member_id)
	);
--DML
SELECT * FROM PROF;
insert into PROF(MEMBER_ID, SALARY) VALUES('kimprof','5200');
insert into PROF(MEMBER_ID, SALARY) VALUES('naprof','4000');
insert into PROF(MEMBER_ID, SALARY) VALUES('ohprof','3200');


--*******************
-- [5]GRADE TABLE
-- 2017-09-04
-- 성적 테이블
-- grade_seq, member_id, subj_id, score, exam_date
--*******************
--DDL
CREATE TABLE grade(
	grade_seq int auto_increment,
	member_id VARCHAR(10),
	subj_id VARCHAR(10),
	score VARCHAR(10),
	exam_date VARCHAR(10),
	primary key(grade_seq)
);
DELETE FROM grade WHERE grade_seq='2012';
select * from grade;
--DML
INSERT INTO GRADE( member_id, subj_id, score, exam_date) 
VALUES('you', 'java', '90', '2017-03');
--id 'bo'의 평균점수를 알기 위한 쿼리 (member_id를 입력하면 평균점수를 반환하는 sql)
select avg(score) 
from (select distinct m.member_id as id, m.name, 
	mj.title as major, g.score, sj.title as subject, g.exam_date 
	from member m, student s, grade g, subject sj, major mj
	where 
		m.MEMBER_ID = s.MEMBER_ID 
		and m.member_id=g.member_id 
		and sj.major_id=mj.major_id 
		and sj.SUBJ_ID = g.SUBJ_ID) t 
where t.id='bo';

select avg(score) 
from (select 
		m.member_id as id, m.name as name,  
		g.score as score, g.exam_date as exam_date 
	from Grade g
		join subject s on g.subj_id=s.subj_id
		join member m on m.member_id=g.member_id
	) t 
where t.id='bo';
-- groub by
select t.id id, avg(score) avg
from (select 
		m.member_id as id,   
		g.score as score
	from Grade g
		join subject s on g.subj_id=s.subj_id
		join member m on m.member_id=g.member_id
	) t
group by t.id having avg(score)>=50 order by avg(score);

select rownum NO, t2.*
from (select t.id id, avg(score) avg
from (select 
		m.member_id as id,   
		g.score as score
	from Grade g
		join subject s on g.subj_id=s.subj_id
		join member m on m.member_id=g.member_id
	) t
group by t.id having avg(score)>=50 order by avg(score) desc) t2
where rownum <4 ;


--*******************
-- [6]BOARD TABLE
-- 2017-09-04
-- 게시판 테이블
-- article_seq, hitcount, member_id, title, content, regdate 
--*******************
--DDL
CREATE TABLE Board(
	article_seq int auto_increment,
	hitcount int,
	member_id VARCHAR(10),
	title VARCHAR(20),
	content VARCHAR(100),
	regdate DATETIME,
	primary key(article_seq)
);
--DML
insert into board( hitcount, member_id, title, content, regdate) 
values(0,'you','유선호의 글','꽃 봄바람을 무엇이 뿐이다.',now());

UPDATE BOARD
SET title='수정된 내용', content='수정된 내용'
WHERE article_seq='1000';

select distinct id from board where id like '%o%';
select * from BOARD;

select t.name, t.title 
from (select 
		m.member_id as id, m.name as name,  
		b.title as title, b.content as content 
	from board b
		join member m on m.member_id=b.member_id
	) t 
where t.name='유선호';


create view student (num, id, name, ssn, email, phone, subj, regdate)
as
select rownum as NO, t.id, t.name, t.ssn, t.email, t.phone, t.subj, t.regdate from
(select m.member_id as id, m.name as name, substr(m.ssn,1,6) as ssn, m.email as email, m.phone as phone, listagg(s.title,',') within group(order by s.title)as subj, substr(m.REGDATE,1) as regdate
from member m left join major j on m.member_id like j.member_id
left join subject s on j.subj_id like s.subj_id
group by m.member_id, name, ssn, email, phone, regdate 
order by m.regdate)t 
order by NO desc;

select t.* from (select rownum as r, s.* from student s)t where t.r between 1 and 5;
select t2.* from (select rownum seq, t.* from (select * from student order by num desc)t )t2 where t2.seq between 1 and 5;

SELECT 
	COUNT(*) FROM 
