package com.gms.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gms.web.constants.DB;

public class JDBCTest {
	public static void main(String[] args){
		String findName = "";
		Connection conn = null;
		try {
			Class.forName(DB.ORACLE_DRIVER);
			conn = DriverManager.getConnection(DB.ORACLE_URL,DB.USERNAME,DB.PASSWORD);
			Statement stmt = conn.createStatement();
			String sql="SELECT * FROM MEMBER WHERE id = 'bo'";
			ResultSet rs = stmt.executeQuery(sql);
			
			if(rs.next()){
				findName = rs.getString("name");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("### 결과 "+findName);
		
	}

}
