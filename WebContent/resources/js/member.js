/**
 * member javascript 
 */

var app=(function(){
	var init = function(ctx){
		session.init(ctx);
		member.init();
		onCreate();
	};
	var onCreate=function(){
		setContentView();
		location.href=ctx()+"/home.do";
	};
	var setContentView=function(){
		
	};
	var ctx=function(){
		return session.getPath('ctx');
	};
	var js=function(){
		return session.getJsPath('js');
	};
	var img=function(){
		return session.getImgPath('img');
	};
	var css=function(){
		return session.getCssPath('css');
	};

	return {
		
		init:init,
		ctx:ctx,
		js:js,
		img:img,
		css:css
	}
	
})();

var session=(function(){
	var init = function(ctx){
		sessionStorage.setItem('ctx', ctx);
		sessionStorage.setItem('js', '/resources/js');
		sessionStorage.setItem('img', '/resources/img');
		sessionStorage.setItem('css', '/resources/css');
	};
	var getPath=function(x){
		return sessionStorage.getItem(x);
	};
	
	return {
		'init' : init,
		'getPath' : getPath
	}
})();

var member=(function(){
	var init=function(){
		//이벤트 핸들러
		$('#loginBtn').on('click',function(){
			var input_id = $("#login_id").val();
			var input_pwd = $("#login_pwd").val();
			if($('#input_id').val()===""){
				alert("id를 입력해 주세요");
				return false;
			}
			if($('#input_pwd').val()===""){
				alert("pass를 입력해 주세요");
				return false;
			}
			$('#login_form').attr('action',app.ctx()+"/common.do");
			$('#login_form').attr('method','post');
			return true;
		});
	};
	
	return {
		init:init
	};
})();

var main=(function(){
	var init=function(){
		onCreate();
	};
	var onCreate=function(){
		setContentView();
	
		$('.list-group').children().addClass("list-group-item");
 		
 		$('.list-group-item a').eq(0).on('click',function(){
 			alert('0');
 			controller.moveTo('member','member_add');
 		});
 		$('.list-group-item a').eq(1).on('click',function(){
 			alert('1');
 			controller.list('member','member_list','1');
 		});
 		$('.list-group-item a').eq(2).on('click',function(){
 			alert('2');
 			controller.detailStudent(prompt('조회할 id를 입력하세요.'));
 		});
 		$('.list-group-item a').eq(3).on('click',function(){
 			alert('3');
 			controller.moveTo('member','member_update');
 		});
	};
	var setContentView=function(){
		//객체 앞에 $를 붙이면 컴포넌트 객체로 구분짓는 것 (값은 담는 임시 객체가 아닌)
		var $u1 = $("#main_ul_stu");
 		var $u2 = $("#main_ul_grade");
 		var $u3 = $("#main_ul_board");
 		
 		$u1.addClass("list-group");
 		$u2.addClass("list-group");
 		$u3.addClass("list-group");
 
	};
	return {
		init : init
	};
	
})();

var navbar=(function(){
	var init=function(){
		onCreate();
	};
	var onCreate=function(){
		setContentView();
		
	};
	var setContentView=function(){
		var $n1 = $("#nav_stu");
 		var $n2 = $("#nav_grade");
 		var $n3 = $("#nav_board");
 		var $n4 = $("#home");
 		var $n5 = $("#logout");
 		
 		$n1.addClass('dropdown-menu');
 		$n2.addClass('dropdown-menu');
 		$n3.addClass('dropdown-menu');
 		$n4.addClass("active");

		$('.active a').on('click',function(){
 			alert('home');
 			controller.moveTo('common','main');
 		});
 		$n5.on('click',function(){
 			alert('logout');
 			controller.logout('common','home');
 		});
 		$('.dropdown-menu a').eq(0).on('click',function(){
 			alert('0');
 			controller.moveTo('member','member_add');
 		});
 		$('.dropdown-menu a').eq(1).on('click',function(){
 			alert('1');
 			controller.list('member','member_list','1');
 		});
 		$('.dropdown-menu a').eq(2).on('click',function(){
 			
 			controller.detailStudent(prompt('조회할 id를 입력하세요.'));
 		});
 		$('.dropdown-menu a').eq(3).on('click',function(){
 			alert('2');
 			controller.deleteTarget('member');
 		});
	};
	return{
		init : init
	};
})();


var home=(function(){
	var init=function(){
		onCreate();
	};
	var onCreate=function(){
		setContentView();
	};
	var setContentView=function(){
		
	};
	return {
		init : init
	};
})();

var memberDetail=(function(){
	var init=function(){
		onCreate();
	};
	var onCreate=function(){
		
		$('#update_btn').on('click',function(){
			//id, phone, email, title
			sessionStorage.setItem('id', $("#detail_id").val());
			sessionStorage.setItem('name', $("#detail_name").text());
			sessionStorage.setItem('phone', $("#detail_phone").text());
			sessionStorage.setItem('email', $("#detail_email").text());
			sessionStorage.setItem('title', $("#update_title").text());
			controller.moveTo('member','member_update');
		});
		setContentView();
	};
	var setContentView=function(){
		
	};
	return {
		init : init
	};
})();

var memberUpdate=(function(){
	var init=function(){
		onCreate();
	};
	var onCreate=function(){
		setContentView();
		
	};
	var setContentView=function(){
		$('#update_id').text(sessionStorage.getItem('id')+' 의 회원정보');
		$('#name').attr('placeholder', sessionStorage.getItem('name'));
		$('#email').attr('placeholder', sessionStorage.getItem('email'));
		$('#phone').attr('placeholder', sessionStorage.getItem('phone'));
		
		$('#update_btn').on('click', function(){
			alert('수정할 ID: '+sessionStorage.getItem('id'));
			controller.updateStudent(sessionStorage.getItem('id'),$('#email').val());
		});

	};
	
	return {
		init : init
	};
	
})();

var controller=(function(){
	var init=function(){
	};
	var moveTo=function(dir,page){
		location.href=app.ctx()+'/'+dir+'.do?action=move&page='+page;
	};
	var list=function(dir, page, pageNum){
		location.href=app.ctx()+'/'+dir+".do?action=list&page="+page+"&pageNum="+pageNum;
	}
	var logout=function(dir, page){
		location.href=app.ctx()+'/'+dir+'.do?action=logout&page='+page;
	}
	var deleteTarget=function(var1){
		window.prompt(var1+" ID?","");
	}
	var updateStudent=function(id, email){
 		alert('update 진입');
 		location.href=app.ctx()+'/member.do?action=update&page=member_update&id='+id+'&email='+email;
 	}
	var deleteStudent=function(id){
 		alert('삭제할 id '+id);
 		location.href=app.ctx()+'/member.do?action=delete&page=member_list&id='+id;
 	}
	var detailStudent=function(id){
 		alert('조회할 id '+id);
 		location.href=app.ctx()+'/member.do?action=detail&page=member_detail&id='+id;
 	}
	var findName=function(){
 		var findname = document.getElementById("findName").value;
 		alert('find_name: '+findname);
 		location.href=app.ctx()+'/member.do?action=search&page=member_list&findname='+findname;
 	}
	return {
		init : init,
		moveTo : moveTo,
		logout : logout,
		deleteTarget : deleteTarget,
		list : list,
		updateStudent : updateStudent,
		deleteStudent : deleteStudent,
		detailStudent : detailStudent,
		findName : findName
	};
})();