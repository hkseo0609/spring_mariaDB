<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import = "com.gms.web.constants.DB" %>
<%@ page import = "java.sql.*" %>
<%
	String findName="";
	Class.forName(DB.MARIADB_DRIVER);
	Connection conn = DriverManager.getConnection(DB.MARIADB_URL,DB.USERNAME,DB.PASSWORD);
	Statement stmt = conn.createStatement();
	String sql="SELECT * FROM MEMBER WHERE member_id = 'kang'";
	ResultSet rs = stmt.executeQuery(sql);
	
	if(rs.next()){
		findName = rs.getString("name");
	}

%>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8" />
	<title>Document</title>
</head>
<body>
	<h1>Hello <%=findName %></h1>
</body>
</html>