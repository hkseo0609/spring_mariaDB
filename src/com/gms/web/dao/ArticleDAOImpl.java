package com.gms.web.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.gms.web.constants.DB;
import com.gms.web.constants.SQL;
import com.gms.web.constants.Vendor;
import com.gms.web.domain.ArticleBean;
import com.gms.web.factory.DatabaseFactory;

public class ArticleDAOImpl implements ArticleDAO {
	//public static ArticleDAOImpl instance = new ArticleDAOImpl();
	
	public static ArticleDAOImpl getInstance() {

		return new ArticleDAOImpl();
	}
	
	private ArticleDAOImpl() {}

	@Override
	public String insert(ArticleBean bean) {
		int rs=0;
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_INSERT);
			pstmt.setString(1, bean.getId());
			pstmt.setString(2, bean.getTitle());
			pstmt.setString(3, bean.getContent());
			rs = pstmt.executeUpdate();
			System.out.println(SQL.BOARD_INSERT);
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return String.valueOf(rs);
	}

	@Override
	public List<ArticleBean> selectAll() {
		List<ArticleBean> list = new ArrayList<>();
		try {	
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_LIST);
			ResultSet rs = pstmt.executeQuery();
			ArticleBean bean = null; 
			while(rs.next()){
				bean = new ArticleBean();
				bean.setArticleSeq(rs.getInt("article_seq"));
				bean.setHitcount(rs.getInt("hitcount"));
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setRegdate(rs.getString("regdate"));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<ArticleBean> selectByid(String id) {
		List<ArticleBean> list = new ArrayList<>();
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_FINDBYID);
			pstmt.setString(1, id);
			ResultSet rs = pstmt.executeQuery();
			ArticleBean bean = null; 
			while(rs.next()){
				bean = new ArticleBean();
				bean.setArticleSeq(rs.getInt("article_seq"));
				bean.setHitcount(rs.getInt("hitcount"));
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setRegdate(rs.getString("regdate"));
				list.add(bean);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public ArticleBean selectBySeq(String seq) {
		ArticleBean bean = new ArticleBean();
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_FINDBYSEQ);
			pstmt.setString(1, seq);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				bean.setArticleSeq(rs.getInt("article_seq"));
				bean.setHitcount(rs.getInt("hitcount"));
				bean.setId(rs.getString("id"));
				bean.setTitle(rs.getString("title"));
				bean.setContent(rs.getString("content"));
				bean.setRegdate(rs.getString("regdate"));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bean;
	}

	@Override
	public String count() {
		String count = "";
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_COUNT);
			ResultSet rs = pstmt.executeQuery();
			if(rs.next()){
				count = rs.getString("count");
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public String update(ArticleBean bean) {
		String rs="";
		ArticleBean temp = selectBySeq(String.valueOf(bean.getArticleSeq()));
		String title = (bean.getTitle().equals(""))?temp.getTitle():bean.getTitle();
		String content = (bean.getContent().equals(""))?temp.getContent():bean.getContent();
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_UPDATE);
		    pstmt.setString(1, title);
		    pstmt.setString(2, content);
		    pstmt.setInt(3, bean.getArticleSeq());
			rs = String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

	@Override
	public String delete(String seq) {
		String rs="";
		try {
			PreparedStatement pstmt = DatabaseFactory.createDatabse(Vendor.ORACLE, DB.USERNAME, DB.PASSWORD).getConnection().prepareStatement(SQL.BOARD_DELETE);
			pstmt.setString(1, seq);
			rs = String.valueOf(pstmt.executeUpdate());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rs;
	}

}
