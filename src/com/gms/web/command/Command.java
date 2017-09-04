package com.gms.web.command;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.gms.web.constants.*;

import lombok.Getter;
import lombok.Setter;
//moveCommand를 위해 존재.. 객체화 시키기 위해
public class Command implements Commandable{
	//상속 가능하도록 protected로 설정, setter가 커스터마이징 필요한 거면 setter 롬복쓰면 안됨
   @Getter
   protected String action, pageNumber, view;
   
   //단순한 set get만 필요하니까 setter, getter 롬복 둘다 사용
   @Getter @Setter
   protected String dir, page, startRow, endRow, column, search;
   
   public void setPageNumber(String pageNumber){
      this.pageNumber =  (pageNumber==null)?"1":pageNumber;
      System.out.println("Command/페이지 번호: "+pageNumber);
   }
   
   public void setAction(String action){
      this.action =  (action==null)?"move":action;
      System.out.println("Command/액션: "+action);
   }
   
   
   @Override
   public void process() {   //setView 역할
	   this.view =(dir.equals("home")) ? "/WEB-INF/view/common/home.jsp" : Path.VIEW+dir+Path.SEPARATOR+page+Extention.JSP;
		System.out.println("commmand의 view :"+view);
   }

   @Override
   public String toString() {
      return "Command [DEST="+dir+"/"+page+".jsp, ACTION="+action+"]";
   }

	
}


