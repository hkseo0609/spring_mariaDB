package com.gms.web.controller;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gms.web.constants.Action;
import com.gms.web.domain.MemberBean;
import com.gms.web.service.MemberService;
import com.gms.web.service.MemberServiceImpl;
import com.gms.web.util.DispatcherServlet;
import com.gms.web.util.Separator;

//주문 받는 사람 -> 팩토리에 전달
@WebServlet({"/home.do","/common.do"})
public class CommonController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("common controller get 진입");
		HttpSession session = request.getSession();
		Separator.init(request);
		switch(Separator.cmd.getAction()){
		case Action.MOVE:
			System.out.println("무브 탐");
			DispatcherServlet.send(request, response);
			break;
		case Action.LOGIN:
			System.out.println("로그인 탐");
			MemberService service = MemberServiceImpl.getInstance();
			MemberBean bean = new MemberBean();
			bean.setId(request.getParameter("login_id"));
			bean.setPwd(request.getParameter("login_pwd"));
			Map<String,Object> map=service.login(bean);
			
			if(map.get("page").equals("main")){
				session.setAttribute("user", map.get("user"));
			}
			Separator.cmd.setPage(String.valueOf(map.get("page")));
			Separator.cmd.process();
			DispatcherServlet.send(request, response);
			break;
		case Action.LOGOUT:
			session.invalidate();
			DispatcherServlet.send(request, response);
			break;
		default: break;
		}
	}

}
