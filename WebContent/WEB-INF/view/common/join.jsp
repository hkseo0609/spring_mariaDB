<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="common_head.jsp" />
	<header>
	<h1 class="gms-h1">MEMBER ADD</h1>
	</header>
	<hr /><br />
	<div id="image" class="gms-image"><a href="${ctx}/index.jsp"><img src="${ctx}/img/join.jpg" alt=""/></a></div><br />
		<form id="join_form" name="join_form" class="form-edit">
			<fieldset>
			<legend>회원가입</legend>
			<span class="join-span">ID</span>
			<input name="id" type="text" id="join_id" placeholder="아이디" /><br />
			
			<label>PASSWORD</label>
			<input name="pwd" type="password" id="join_pwd" placeholder="비밀번호"/><br />
			
			<span class="join-span">NAME</span>
			<input name="name" type="text" id="join_name" placeholder="이름"/><br />
			
			<span class="join-span">birthday</span>
			<input name="birthday" type="text" id="join_ssn"/><br />
			
			<span class="join-span">email</span>
			<input type="email" name="email" /><br />
			
			<input name="gender" type="radio" value="male">남자
			<input name="gender" type="radio" value="female">여자<br />
			<h4>전공</h4>
			<select name="major">
				<option value="computer" selected>컴퓨터공학과</option>
				<option value="economics">경제학</option>
				<option value="tourism">관광학</option>
				<option value="art">미술학</option>
			</select><br />
			<h4>수강과목</h4>
			<input type="checkbox" name="shbject" value="java" checked/>java <br />
			<input type="checkbox" name="shbject" value="C" checked/>C<br />
			<input type="checkbox" name="shbject" value="html" />html<br />
			<input type="checkbox" name="shbject" value="css" />css<br />
			<input type="checkbox" name="shbject" value="javascript" />javascript<br />
			<input type="checkbox" name="shbject" value="sql" />sql<br />
			<input type="checkbox" name="shbject" value="python" />python<br /><br />
			<input type="submit" id="yes_btn" value="등록" class="submit-pink" onclick="addAlert()">
			<input type="reset" id="no_btn" value="취소" class="submit-blue">
			<input type="hidden" name="action" value="join" />
			</fieldset>
		</form>

<jsp:include page="../common/footer.jsp" />
<script>
function addAlert(){
	var join_id = document.getElementById("join_id").value;
	var join_pwd = document.getElementById("join_pwd").value;
	var join_name = document.getElementById("join_name").value;
	var join_ssn = document.getElementById("join_ssn").value;
	if(join_id===""){
		alert("id를 입력해 주세요");
		return false;
	}
	if(join_pwd===""){
		alert("pass를 입력해 주세요");
		return false;
	}
	if(join_name===""){
		alert("pass를 입력해 주세요");
		return false;
	}
	if(join_ssn===""){
		alert("pass를 입력해 주세요");
		return false;
	}
	var form = document.getElementById('join_form');
	form.action="${ctx}/member.do";
	form.method="post";
	return true;
}
</script>