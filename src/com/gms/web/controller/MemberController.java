package com.gms.web.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gms.web.command.Command;
import com.gms.web.constants.Action;
import com.gms.web.domain.MajorBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;
import com.gms.web.proxy.BlockHandler;
import com.gms.web.proxy.PageHandler;
import com.gms.web.proxy.PaginationProxy;
import com.gms.web.service.MemberService;
import com.gms.web.service.MemberServiceImpl;
import com.gms.web.util.DispatcherServlet;
import com.gms.web.util.ParamsIterator;
import com.gms.web.util.Separator;

@WebServlet({"/member.do"})
public class MemberController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("null")
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("member controller get 진입");
		Separator.init(request);
		MemberBean member = new MemberBean();
		MemberService service = MemberServiceImpl.getInstance();
		Command cmd = new Command();
		Map<?,?> map = null;
		PaginationProxy pxy = new PaginationProxy(request);
		pxy.setPageSize(5);
		pxy.setBlockSize(5);
		switch(request.getParameter(Action.CMD)){
		case Action.MOVE:
			DispatcherServlet.send(request, response);
			break;
		case Action.JOIN:
			System.out.println("join 시작");
			map=ParamsIterator.execute(request);
			member.setId((String)map.get("id"));
			member.setPwd((String)map.get("pwd"));
			member.setName((String)map.get("name"));
			member.setSsn((String)map.get("ssn"));
			member.setEmail((String)map.get("email"));
			member.setSsn((String)map.get("birthday"));
			member.setPhone((String)map.get("phone"));
			//major는 여러 행을 입력해야함, for문
			
			String[] subject=((String)map.get("subject")).split(",");
			List<MajorBean> list = new ArrayList<>();
			MajorBean major = null;
			for(int i=0; i<subject.length;i++){
				major = new MajorBean();
				major.setMajorId(String.valueOf((int)((Math.random() * 9999) + 1000)));
				major.setId((String)map.get("id"));
				major.setTitle((String)map.get("name"));
				major.setSubjId(subject[i]);
				list.add(major);
			}
			Map<String, Object> tempMap=new HashMap<>();
			tempMap.put("member", member);
			tempMap.put("major", list);
			String page = service.addMember(tempMap);
			System.out.println("id"+map.get("id"));
			
			Separator.cmd.setDir("common");
			Separator.cmd.setPage(page);
			Separator.cmd.process();
			DispatcherServlet.send(request, response);
			break;
		case Action.LIST:
			System.out.println("list 진입");
			//System.out.println(map.get("column"));
			
			//cmd.setColumn(String.valueOf(map.get("column")));
			//cmd.setSearch(String.valueOf(map.get("findname")));
			pxy.setTheNumberOfRows(Integer.parseInt(service.countMembers(cmd)));
			pxy.setPageNumber(Integer.parseInt(request.getParameter("pageNum")));
			pxy.execute(BlockHandler.attr(pxy), service.list(PageHandler.attr(pxy)));
			DispatcherServlet.send(request, response);
			break;
		case Action.SEARCH:
			System.out.println("search 진입");
			map = ParamsIterator.execute(request);
			
			cmd = PageHandler.attr(pxy);
			cmd.setColumn("name");
			cmd.setSearch(String.valueOf(map.get("findname")));
			cmd.setStartRow(PageHandler.attr(pxy).getStartRow());
			cmd.setEndRow(PageHandler.attr(pxy).getEndRow());
			cmd.setPageNumber(request.getParameter("pageNum"));
			pxy.setTheNumberOfRows(Integer.parseInt(service.countMembers(cmd)));
			pxy.setPageNumber(Integer.parseInt(cmd.getPageNumber()));
			pxy.execute(BlockHandler.attr(pxy), service.findName(cmd));
			DispatcherServlet.send(request, response);
			break;
		case Action.UPDATE:
			System.out.println("update 진입");
			map=ParamsIterator.execute(request);
			System.out.println("수정할 id: "+map.get("id"));
			System.out.println("수정할 email: "+map.get("email"));
			//request.setAttribute("update",service.modfiy(service.findByid(cmd)));
			DispatcherServlet.send(request, response);
			break;
		case Action.DETAIL:
			System.out.println("detail 진입");
			cmd.setSearch(request.getParameter("id"));
			request.setAttribute("detail", service.findByid(cmd));
			DispatcherServlet.send(request, response);
			break;
		
		case Action.DELETE:
			System.out.println("delete 진입");
			String path = request.getContextPath();
			System.out.println(path);
			//request.setAttribute("student", service.remove(request.getParameter("id")));
			response.sendRedirect(path+"/member.do?action=list&page=member_list&pageNum=1");
			break;
		}	
	}
}

