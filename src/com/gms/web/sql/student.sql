create view student (id, pwd, name, ssn, email, phone, subj, regdate)
as(
    SELECT M.MEMBER_ID ID, M.PWD PWD, M.NAME, RPAD(SUBSTR(M.SSN,1,8),14,'*') SSN, M.EMAIL, M.PHONE, 
           GROUP_CONCAT(S.TITLE) SUBJ, DATE_FORMAT(M.REGDATE,'%Y-%m-%d') REGDATE
    FROM
        	MEMBER M
        	LEFT JOIN MAJOR J ON M.MEMBER_ID LIKE J.MEMBER_ID
        	LEFT JOIN SUBJECT S ON J.SUBJ_ID LIKE S.SUBJ_ID
        	GROUP BY M.MEMBER_ID, M.NAME, M.SSN, M.REGDATE, M.EMAIL, M.PHONE
        	ORDER BY M.REGDATE
   );
   
select @rnum := @rnum +1 as no, t.*
from 
	student t, (select @rnum := 0) b;