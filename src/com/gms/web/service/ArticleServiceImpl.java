package com.gms.web.service;

import java.util.List;

import com.gms.web.dao.ArticleDAO;
import com.gms.web.dao.ArticleDAOImpl;
import com.gms.web.domain.ArticleBean;
public class ArticleServiceImpl implements ArticleService{
	public static ArticleServiceImpl instance = new ArticleServiceImpl();
	public static ArticleServiceImpl getInstance() {
		return instance;
	}
	private ArticleServiceImpl() {}
	
	@Override
	public List<ArticleBean> list() {
		return ArticleDAOImpl.getInstance().selectAll();
	}
	
	@Override
	public String write(ArticleBean bean) {
		String result = ArticleDAOImpl.getInstance().insert(bean);
		String msg = (Integer.parseInt(result)==1)?"등록되었습니다.":"등록에 실패하였습니다.";
		return msg;
	}

	@Override
	public List<ArticleBean> findByid(String id) {
		return ArticleDAOImpl.getInstance().selectByid(id);
	}

	@Override
	public ArticleBean findBySeq(String seq) {
		return ArticleDAOImpl.getInstance().selectBySeq(seq);
	}

	@Override
	public String count() {
		return ArticleDAOImpl.getInstance().count();
	}

	@Override
	public String modfiy(ArticleBean bean) {
		String msg="";
		String result = ArticleDAOImpl.getInstance().update(bean);
		msg = (result.equals("1"))?"수정 완료":"수정 실패";
		return msg;
	}

	@Override
	public String remove(String seq) {
		String result = ArticleDAOImpl.getInstance().delete(seq);
		String msg = (Integer.parseInt(result)==1) ? "삭제되었습니다." : "삭제에 실패하였습니다.";
		return msg;
	}

}
