package com.gms.web.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class GradeBean {
	@Getter @Setter
	private String gradeSeq, Id, subjId, score, examDate;
	
}
