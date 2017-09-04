package com.gms.web.proxy;

import javax.servlet.http.HttpServletRequest;

import lombok.Getter;
//procy는 항상 handler와 짝꿍, 짐 실어주는 역할
public abstract class Proxy {
	@Getter
	//프록시는 setter가 있으면 보안이 깨진다
	HttpServletRequest request;
	//객체 생성자를 사용하는 이유 : 요청이 들어왔을 때만 proxy를 사용하기 위해
	public Proxy(HttpServletRequest request) {
		this.request = request;
	}
	
	

}
