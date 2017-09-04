<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/common_head.jsp" />
	<header>
	<h1 class="gms-h1">MEMBER ADD</h1>
	</header>
	<hr /><br />
	<div id="image" class="gms-image"><a href="${ctx}/index.jsp"><img src="${ctx}/img/join.jpg" alt=""/></a></div><br />
		<form id="join_form" class="form-edit" onsubmit="memberAdd()">
			<fieldset>
			<legend>회원가입</legend>
			<span class="join-span">ID</span>
			<input name="id" type="text" id="join_id" placeholder="아이디" /><br />
			
			<label>PASSWORD</label>
			<input name="pwd" type="password" id="join_pwd" placeholder="비밀번호" value="1"/><br />
			
			<span class="join-span">NAME</span>
			<input name="name" type="text" id="join_name" placeholder="이름" value="다니엘"/><br />
			
			<span class="join-span">birthday</span>
			<input name="birthday" type="text" id="join_ssn" value="123456789"/><br />
			
			<span class="join-span">email</span>
			<input type="email" name="email" value="ddd@test.net"/><br />
			
			<span class="join-span">phone</span>
			<input type="text" name="phone" value="010-1111-1234"/><br />
			
			<input name="gender" type="radio" value="male" checked>남자
			<input name="gender" type="radio" value="female">여자<br />
			<h4>전공</h4>
			<select name="major">
				<option value="computer" selected>컴퓨터공학과</option>
				<option value="economics">경제학</option>
				<option value="tourism">관광학</option>
				<option value="art">미술학</option>
			</select><br />
			<h4>수강과목</h4>
			<input type="checkbox" name="subject" value="java" checked/>java <br />
			<input type="checkbox" name="subject" value="c" checked/>C<br />
			<input type="checkbox" name="subject" value="html" checked/>html<br />
			<input type="checkbox" name="subject" value="css" />css<br />
			<input type="checkbox" name="subject" value="javascript" />javascript<br />
			<input type="checkbox" name="subject" value="sql" />sql<br />
			<input type="checkbox" name="subject" value="python" />python<br /><br />
			<input type="submit" id="yes_btn" value="등록" class="submit-pink" onsubmit="memberAdd()">
			<input type="reset" id="no_btn" value="취소" class="submit-blue">
			<input type="hidden" name="action" value="join" />
			<input type="hidden" name="page" value="main" />
			</fieldset>
		</form>

<jsp:include page="../common/footer.jsp" />
