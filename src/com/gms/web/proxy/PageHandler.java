package com.gms.web.proxy;

import javax.servlet.http.HttpServletRequest;

import com.gms.web.command.Command;

//핸들러 : 이벤트를 처리하는 기능
public class PageHandler extends PaginationProxy{

	public PageHandler(HttpServletRequest request) {
		super(request);
	}
	public static Command attr(PaginationProxy pxy){
		Command cmd = new Command();
		if (pxy.getPageNumber() <= pxy.getTheNumberOfRows() /pxy.getPageSize()+1){
			if(pxy.getPageNumber() ==1){
				cmd.setStartRow("1");
				cmd.setEndRow(String.valueOf(pxy.getPageSize()));
			}else {
				cmd.setStartRow(String.valueOf((pxy.getPageNumber() - 1) * pxy.getPageSize()+1));
				cmd.setEndRow(String.valueOf(pxy.getPageNumber() * pxy.getPageSize()));
			}
		}
		return cmd;
	}

}
