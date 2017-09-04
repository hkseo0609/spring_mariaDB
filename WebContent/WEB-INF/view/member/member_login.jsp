<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/common_head.jsp" />
<!doctype html>
<html lang="ko">
<head>
<meta charset="UTF-8">
<title>L O G I N</title>
<link rel="stylesheet" href="${ctx}/resources/css/member.css" />
</head>
<body>
<div id="wrapper">
<header>
<h1 class="gms-h1">ADMIN LOGIN</h1>
</header>
<hr /><br />
	<div id="image" class="gms-image"><a href="${ctx}/index.jsp"><img src="${ctx}/resources/img/login.gif" alt="" /></a></div><br />
		<form id="login_form" action="${ctx}/common.do" class="form-edit" method="get">
		<fieldset>
		<legend>로그인</legend>
			<span class="login-span">ID</span>
			<input type="text" name="id" value="you"/><br />
			<label>PASSWORD</label><input type="password" name="pwd" value="11111"/><br />
			<mark style="font-size: 13px"> *ID는 숫자 포함 8글자 이내*</mark><br />
			<input type="submit" value="LOING" class="submit-pink">
			<input type="submit" value="CANCLE" class="submit-blue">
			<input type="hidden" name="action" value="login" />
			<input type="hidden" name="page" value="main" />
		</fieldset>
		</form>
</div>
<br /><hr />
<footer>
  <div>
  <p>Posted by: Seo HeeKyoung</p>
  <p>Contact information: <a href="mailto:someone@example.com">
  someone@example.com</a>.</p>
  <a href="util/jdbc_test.jsp">DB연결 테스트</a>
  </div>
</footer>
</body>
</html>
