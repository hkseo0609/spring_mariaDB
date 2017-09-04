package com.gms.web.service;

import java.util.List;
import java.util.Map;

import com.gms.web.command.Command;
import com.gms.web.domain.MemberBean;
import com.gms.web.domain.StudentBean;

public interface MemberService {
	//modify와 add빼고는 다 command로 받기
	public List<?> list(Command cmd);
	public String countMembers(Command cmd);
	public String addMember(Map<String, Object> map);
	public StudentBean findByid(Command cmd);
	public List<?> findName(Command cmd);
	public String modfiy(MemberBean bean);
	public String remove(Command cmd);
	public Map<String,Object> login(MemberBean bean);

}
