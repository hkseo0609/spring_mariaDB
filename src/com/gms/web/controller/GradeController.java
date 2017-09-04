package com.gms.web.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.gms.web.constants.Action;
import com.gms.web.domain.MemberBean;
import com.gms.web.util.DispatcherServlet;
import com.gms.web.util.Separator;

@WebServlet("/grade.do")
public class GradeController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("grade controller get 진입");
		Separator.init(request);
		switch(request.getParameter(Action.CMD)){
		case Action.MOVE:
			DispatcherServlet.send(request, response);
			break;
		}	
	}
}
