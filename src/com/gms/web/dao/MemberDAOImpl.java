package com.gms.web.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.gms.web.command.Command;
import com.gms.web.constants.DB;
import com.gms.web.constants.SQL;
import com.gms.web.constants.Vendor;
import com.gms.web.domain.MajorBean;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;
import com.gms.web.factory.DatabaseFactory;

public class MemberDAOImpl implements MemberDAO{
	/*싱글턴 패턴 static으로 dao 미리 생성
	public static MemberDAOImpl instance = new MemberDAOImpl();*/
	Connection conn;
	public static MemberDAOImpl getInstance() {
		return new MemberDAOImpl();
	}
	private MemberDAOImpl(){
		conn = null;
	}

	@Override
	public List<?> selectAll(Command cmd) {
		List<StudentBean> list = new ArrayList<>();
		try {
			conn = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection();
			PreparedStatement pstmt = conn.prepareStatement(SQL.STUDENT_LIST);
			pstmt.setString(1, cmd.getStartRow());
			pstmt.setString(2, cmd.getEndRow());
			ResultSet rs = pstmt.executeQuery();
			StudentBean member = null;
			while(rs.next()){
				member = new StudentBean();
				member.setNum(rs.getString(DB.STUD_NUM));
				member.setSubj(rs.getString(DB.STUD_TITLE));
				member.setId(rs.getString(DB.ID));
				member.setName(rs.getString(DB.MEM_NAME));
				member.setSsn(rs.getString(DB.MEM_SSN));
				member.setEmail(rs.getString(DB.MEM_EMAIL));
				member.setPhone(rs.getString(DB.MEM_PHONE));
				member.setRegdate(rs.getString(DB.MEM_REGDATE));
				
				//member.setSubject(rs.getString(DB.MEM_REGDATE));
				list.add(member);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String count(Command cmd) {
		System.out.println("=========count 진입");
		System.out.println("dao 넘어온 이름: "+cmd.getSearch());
		System.out.println("dao 넘어온 칼럼: "+cmd.getColumn());
		int count=0;
		try {
			conn = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection();
			PreparedStatement pstmt = null;
			if(cmd.getSearch()==null){
				//sql = "SELECT COUNT(*) AS count FROM student";
				pstmt = conn.prepareStatement(SQL.STUDENT_COUNT);
				pstmt.setString(1, "%");
			}else{
				//sql = String.format("SELECT COUNT(*) AS count FROM student where name like '%s'", cmd.getSearch());
				pstmt = conn.prepareStatement(SQL.STUDENT_COUNT);
				pstmt.setString(1, "%"+cmd.getSearch()+"%");
			}
		
			ResultSet rs=pstmt.executeQuery();

			if(rs.next()){
				count = Integer.valueOf(rs.getString("count"));
				System.out.println("dao count값 : "+count);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return String.valueOf(count);
	}

	@Override
	public String insert(Map<?,?> map) {
		String rs=null;
		PreparedStatement pstmt = null;
		
		MemberBean member = (MemberBean)map.get("member");
		@SuppressWarnings("unchecked")
		List<MajorBean> list = (List<MajorBean>)map.get("major");
		
		//트랜잭션 -> 두개의 테이블에 하나의 값을 나눠서 담는 것
		try {
			conn = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(SQL.MEMBER_INSERT);
			pstmt.setString(1,member.getId());
			pstmt.setString(2,member.getPwd());
			pstmt.setString(3,member.getName());
			pstmt.setString(4,member.getSsn());
			pstmt.setString(5,member.getPhone());
			pstmt.setString(6,member.getEmail());
			pstmt.setString(7,member.getProfile());
			pstmt.executeUpdate();
			
			for(int i=0; i<list.size(); i++){
			pstmt = conn.prepareStatement(SQL.MAJOR_INSERT);
			pstmt.setString(1,list.get(i).getMajorId());
			pstmt.setString(2,list.get(i).getTitle());
			pstmt.setString(3,list.get(i).getId());
			pstmt.setString(4,list.get(i).getSubjId());
			rs=String.valueOf(pstmt.executeUpdate());
			}
			
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			if(conn!=null){
				try{
					conn.rollback();
				} catch(SQLException ex){
					e.printStackTrace();
				}
			}
		}
		
		try {
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
		
	}

	@Override
	public StudentBean selectByid(Command cmd) {
		StudentBean member = null;
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.STUDENT_FINDBYID);
			pstmt.setString(1, cmd.getSearch());
			ResultSet rs = pstmt.executeQuery();
	
			if(rs.next()){
				member = new StudentBean();
				member.setNum(rs.getString(DB.STUD_NUM));
				member.setSubj(rs.getString(DB.STUD_TITLE));
				member.setId(rs.getString(DB.ID));
				member.setName(rs.getString(DB.MEM_NAME));
				member.setSsn(rs.getString(DB.MEM_SSN));
				member.setEmail(rs.getString(DB.MEM_EMAIL));
				member.setPhone(rs.getString(DB.MEM_PHONE));
				member.setRegdate(rs.getString(DB.MEM_REGDATE));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
         return member;
	}

	@Override
	public List<?> selectByName(Command cmd) {
		System.out.println("파인드네임 진입 : "+cmd.getSearch());
		System.out.println("파인드네임 진입 : "+cmd.getColumn());
		List<StudentBean> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.STUDENT_SSEARCH);
			pstmt.setObject(1, "%"+cmd.getSearch()+"%");
			ResultSet rs = pstmt.executeQuery();
			StudentBean member = null;
			while(rs.next()){
				member = new StudentBean();
				member.setNum(rs.getString(DB.STUD_NUM));
				member.setSubj(rs.getString(DB.STUD_TITLE));
				member.setId(rs.getString(DB.ID));
				member.setName(rs.getString(DB.MEM_NAME));
				member.setSsn(rs.getString(DB.MEM_SSN));
				member.setEmail(rs.getString(DB.MEM_EMAIL));
				member.setPhone(rs.getString(DB.MEM_PHONE));
				member.setRegdate(rs.getString(DB.MEM_REGDATE));
				
				list.add(member);
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public String update(MemberBean bean) {
		String rs="";
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.MEMBER_UPDATE);
			pstmt.setString(1, bean.getPwd());
			pstmt.setString(2, bean.getId());
			rs=String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}

	@Override
	public String delete(Command cmd) {
		String rs="";
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.MEMBER_DELETE);
			pstmt.setString(1, cmd.getSearch());
			rs = String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
		
	}
	@Override
	public MemberBean login(Command cmd) {
		MemberBean member = null;
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.MEMBER_FINDBYID);
			pstmt.setString(1, cmd.getSearch());
			ResultSet rs = pstmt.executeQuery();
	
			if(rs.next()){
				member = new MemberBean();
				member.setId(rs.getString(DB.MEM_ID));
				member.setName(rs.getString(DB.MEM_NAME));
				member.setPwd(rs.getString(DB.MEM_PWD));
				member.setRegdate(rs.getString(DB.MEM_REGDATE));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
         return member;
	}

}
