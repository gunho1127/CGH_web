package com.cgh.biz.student;

import lombok.Data;

@Data
public class StudentVO {

	private int studentid; // 학번 
	private String name; // 이름 
	private String major; // 전공 
	private String phonenumber; // 전화번호 
}

