package com.gms.web.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Clock {
	public String getNow(){
		/*String now ="";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss");
		now=new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss").format(new Date());*/
		
		return new SimpleDateFormat("yyyy년 MM월 dd일 a hh:mm:ss").format(new Date());
	}
}
