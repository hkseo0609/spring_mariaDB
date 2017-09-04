package com.gms.web.domain;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class MemberBean implements Serializable{
	private static final long serialVersionUID = 1L;
	private String id, pwd, name, ssn, regdate, email, major, subject, phone, profile;

}
