package com.gms.web.proxy;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
import lombok.Setter;

public class PaginationProxy extends Proxy {
	@Setter @Getter
	protected int pageSize, blockSize, theNumberOfRows, pageNumber;

	public PaginationProxy(HttpServletRequest request) {
		super(request);
	}
	
	public void execute(int[] arr, List<?> list){
		super.getRequest().setAttribute("pageNumber", arr[0]);
		super.getRequest().setAttribute("theNumberOfPage",arr[1]);
		super.getRequest().setAttribute("startPage",arr[2]);
		super.getRequest().setAttribute("endPage",arr[3]);
		super.getRequest().setAttribute("prevBlock",arr[4]);
		super.getRequest().setAttribute("nextBlock",arr[5]);
		super.getRequest().setAttribute("list", list);
	}
	


}
