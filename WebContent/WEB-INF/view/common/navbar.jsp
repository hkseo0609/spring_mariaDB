<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${css}/member.css" />
<nav class="navbar navbar-inverse">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">HEEKYOUNG</a>
    </div>
    <ul class="nav navbar-nav">
      <li id="home"><a><span class="glyphicon glyphicon-home"></span>&nbsp;Home</a></li>
      <li class="dropdown">
      	<a href="#" class="dropdown-toggle"
      	data-toggle="dropdown" role="button"
      	aria-haspopup="true"
      	aria-expanded="false">학생관리<span class="caret"></span></a>
      	<ul id="nav_stu">
	      	<li><a>학생 추가</a></li>
	      	<li><a>학생 목록</a></li>
	      	<li><a>학생 조회</a></li>
	      	<li></li>
	      	<li><a>학생 삭제</a></li>
      	</ul>
   	 </li>
   	 <li class="dropdown">
      	<a href="#" class="dropdown-toggle"
      	data-toggle="dropdown" role="button"
      	aria-haspopup="true"
      	aria-expanded="false">성적관리<span class="caret"></span></a>
      	<ul id="nav_grade">
	      	<li><a>성적 추가</a></li>
	      	<li><a>성적 상제</a></li>
	      	<li><a>성적 목록</a></li>
	      	<li></li>
	      	<li><a>성적 삭제</a></li>
      	</ul>
   	 </li>
   	 <li class="dropdown">
      	<a href="#" class="dropdown-toggle"
      	data-toggle="dropdown" role="button"
      	aria-haspopup="true"
      	aria-expanded="false">게시판관리<span class="caret"></span></a>
      	<ul id="nav_board">
	      	<li><a>게시글 추가</a></li>
	      	<li><a>게시글 목록</a></li>
	      	<li><a>게시글 조회</a></li>
	      	<li></li>
	      	<li><a>게시글 삭제</a></li>
      	</ul>
   	 </li>
   	 </ul>
    <span class="login-d">${sessionScope.user.name}&nbsp;<a id="logout">로그아웃</a> </span>
  </div>
</nav>
<script>
	navbar.init();
	/*window.onload=navbarLoad();	
	function moveTo(dir, action, page){
		location.href="${ctx}/"+dir+".do?action="+action+"&page="+page;
	}
	function deleteTarget(var1){
		window.prompt(var1+" ID?","");
	}
	function list(dir, page, pageNum){
		location.href="${ctx}/"+dir+".do?action=list&page="+page+"&pageNum="+pageNum;
	}
 	 
 	function mainLoad(){
 		//native js 코딩
 		var u1 = document.getElementById("main_ul_stu");
 		var u2 = document.getElementById("main_ul_grade");
 		var u3 = document.getElementById("main_ul_board");
 		u1.setAttribute("class", "list-group");
 		u2.setAttribute("class", "list-group");
 		u3.setAttribute("class", "list-group");
 		var u1c = u1.children;
 		var u2c = u2.children;
 		var u3c = u3.children;
 		var i;
 		for(i=0; i<u1c.length; i++){
 			u1c[i].setAttribute("class","list-group-item");
 		}
 		for(i=0; i<u2c.length; i++){
 			u2c[i].setAttribute("class","list-group-item");
 		}
 		for(i=0; i<u3c.length; i++){
 			u3c[i].setAttribute("class","list-group-item");
 		}

 		u1c[0].setAttribute("onclick","moveTo('member','move','member_add')");
 		u1c[1].setAttribute("onclick","list('member','member_list','1')");
 		u1c[2].setAttribute("onclick","moveTo('member','move','member_search')");
 		u1c[3].setAttribute("onclick","moveTo('member','move','member_update')");
 		u1c[4].setAttribute("onclick","deleteTarget('member')");
 		
 		u2c[0].setAttribute("onclick","moveTo('grade','move','grade_add')");
 		u2c[1].setAttribute("onclick","moveTo('grade','move','grade_list')");
 		u2c[2].setAttribute("onclick","moveTo('grade','move','grade_detail')");
 		u2c[3].setAttribute("onclick","moveTo('grade','move','grade_update')");
 		u2c[4].setAttribute("onclick","deleteTarget('grade')");
 		
 		u3c[0].setAttribute("onclick","moveTo('board','move','board_write')");
 		u3c[1].setAttribute("onclick","moveTo('board','move','board_list')");
 		u3c[2].setAttribute("onclick","moveTo('board','move','board_detail')");
 		u3c[3].setAttribute("onclick","moveTo('board','move','board_update')");
 		u3c[4].setAttribute("onclick","deleteTarget('board')");
 	}*/
 	
 	/*function navbarLoad(){
 	 	var n1 = document.getElementById("nav_stu");
 		var n2 = document.getElementById("nav_grade");
 		var n3 = document.getElementById("nav_board");
 		
 		n1.setAttribute("class", "dropdown-menu");
 		n2.setAttribute("class", "dropdown-menu");
 		n3.setAttribute("class", "dropdown-menu");
 		var n1c = n1.children;
 		var n2c = n2.children;
 		var n3c = n3.children;
 		
 		n1c[0].setAttribute("onclick","moveTo('member','move','member_add')");
 		n1c[1].setAttribute("onclick","list('member','member_list','1')");
 		n1c[2].setAttribute("onclick","moveTo('member','move','member_search')");
 		n1c[3].setAttribute("role", "separator");
 		n1c[3].setAttribute("class", "divider");	
 		n1c[4].setAttribute("onclick","deleteTarget('member')");
 		
 		n2c[0].setAttribute("onclick","moveTo('grade','move','grade_add')");
 		n2c[1].setAttribute("onclick","moveTo('grade','move','grade_detail')");
 		n2c[2].setAttribute("onclick","moveTo('grade','move','grade_list')");
 		n2c[3].setAttribute("role", "separator");
 		n2c[3].setAttribute("class", "divider");	
 		n2c[4].setAttribute("onclick","deleteTarget('grade')");
 		
 		n3c[0].setAttribute("onclick","moveTo('board','move','board_write')");
 		n3c[1].setAttribute("onclick","moveTo('board','move','board_list')");
 		n3c[2].setAttribute("onclick","moveTo('board','move','board_detail')");
 		n3c[3].setAttribute("role", "separator")
 		n3c[3].setAttribute("class", "divider");
 		n3c[4].setAttribute("onclick","deleteTarget('board')");
 		
 		document.getElementById('logout').setAttribute("onclick","moveTo('common','logout','home')");
 	}
 	
 	function updateStudent(id){
 		alert('수정할 id'+id);
 		location.href="${ctx}/member.do?action=update&page=member_update&id="+id;
 	}
 	function deleteStudent(id){
 		alert('삭제할 id'+id);
 		location.href="${ctx}/member.do?action=delete&page=member_list&id="+id;
 	}
 	function detailStudent(id){
 		alert('조회할 id'+id);
 		location.href="${ctx}/member.do?action=detail&page=member_detail&id="+id;
 	}
 	function findName(){
 		var findname = document.getElementById("findName").value;
 		alert('find_name: '+findname);
 		location.href="${ctx}/member.do?action=search&page=member_list&findname="+findname;
 	}
 	
 	function memberAdd(){
 		var form = document.getElementById('join_form');
 		form.setAtrribute("action","${ctx}/member.do");
 		form.setAtrribute("method","post");
 		form.submit();
 		return true;
 	}*/

 	/*function test(){
  	  document.querySelector('#update_btn').onclick=studentInfo;
    }
    function studentInfo(){
  	  var id = 'id';
  	  var id_val ='${requestScope.detail.id}',
  	  		name = 'name',
  	  		name_val = '${requestScope.detail.name}',
  	  		email = 'email',
  	  		email_val = '${requestScope.detail.email}';
  	  sessionStorage.setItem(id,id_val);
  	  sessionStorage.setItem(name,name_val);
  	  sessionStorage.setItem(email,email_val);
  	  
    }*/

</script>