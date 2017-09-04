<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="board_head.jsp" />
<header>
<h1 class="gms-h1">W R I T E</h1>
</header>
<a href="../index.jsp" class="gms-gomain">메인으로 가기</a><br />
<hr /><br />
	<div id="container" class="board-div-style">
	<form action="">
	<textarea name="msg" id="" cols="30" rows="10" style="width: 500px">내용을 입력하세요.</textarea><br />
	<br />
	<input type="submit" value="글쓰기" class="submit-pink"/>
	
	</form>
	</div>
<br />
<jsp:include page="../common/footer.jsp" />