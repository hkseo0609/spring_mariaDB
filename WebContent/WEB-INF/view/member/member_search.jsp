<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:include page="../common/common_head.jsp" />
<header>
<h1 class="gms-h1">MEMBER LIST</h1>
</header>
<hr /><br />
<div style="margin: 0 auto; width: 70%">
<div class="row">
  <div class="col-lg-4">
    <div class="input-group">
      <input type="text" id="findName" name="findName" class="form-control" placeholder="Search for...">
      <span class="input-group-btn">
        <button id="findbtn" class="btn btn-default" type="button" onclick="findName()">Go!</button>
      </span>
    </div>
  </div>
</div>
</div>
<div style="height: 40px; width: 300px;"></div>
	<table id="mem-list-tab">
		<tr>
			<th>No</th>
			<th>아이디</th>
			<th>이름</th>
			<th>생년월일</th>
			<th>이메일</th>
			<th>전화번호</th>
			<th>수강과목</th>
			<th>가입날짜</th>
			<th>수정/삭제</th>
		</tr>
		<c:forEach var="i" items="${requestScope.list}">
		<tr>
			<td>${i.num}</td>
			<td>${i.id}</td>
			<td><a onclick="detailStudent('${i.id}')">${i.name}</a></td>
			<td>${i.ssn}</td>
			<td>${i.email}</td>
			<td>${i.phone}</td>
			<td>${i.subj}</td>
			<td>${i.regdate}</td>
			<td>
			<a onclick="updateStudent('${i.id}')">수정</a>
			/
			<a onclick="deleteStudent('${i.id}')">삭제</a>
			
			</td>
		</tr>
		</c:forEach>
	</table>
<nav aria-label="Page navigation" style="width:350px; margin:0 auto;">
	  <ul class="pagination">
 		<c:if test="${requestScope.prevBlock gt 0 }">
	  	<li><a onclick="list('member','member_search','1')"><span class="glyphicon glyphicon-triangle-left" aria-hidden="true"></span></a></li>
	    <li>
	      <a onclick="list('member','member_search','${requestScope.startPage-1}')" aria-label="Previous">
	        <span aria-hidden="true">&laquo;</span>
	      </a>
	    </li>
	   	</c:if>
	    <c:forEach varStatus="i" begin="${requestScope.startPage}" end="${requestScope.endPage}" step="1">
	    <c:choose>
		    <c:when test="${requestScope.pageNumber eq i.index}">
		        <li class="active"><a href="#" >${i.index}</a></li>
		    </c:when>
		    <c:otherwise>
		    	 <li><a href="#" onclick="list('member','member_search','${i.index}')">${i.index}</a></li>
		    </c:otherwise>
		</c:choose>
	    </c:forEach>
	    <c:if test="${requestScope.nextBlock le requestScope.theNumberOfPage}">
	    <li>
	      <a onclick="list('member','member_search','${requestScope.endPage+1}')" aria-label="Next">
	        <span aria-hidden="true">&raquo;</span>
	      </a>
	    </li>
	    <li><a onclick="list('member','member_search','${requestScope.theNumberOfPage}')"><span class="glyphicon glyphicon-triangle-right" aria-hidden="true"></span></a></li>
	 	</c:if>
	  </ul>
	</nav>

<jsp:include page="../common/footer.jsp" />