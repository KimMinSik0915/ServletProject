package com.webjjang.member.vo;

public class LoginVO {

	// 로그인에 필요한 정보 : 화면에 보여주는 정보 + 처리할때 필요한 정보
	
	private String id, pw;
	
	private String name , gradeName;
	
	private int gradeNo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGradeName() {
		return gradeName;
	}

	public void setGradeName(String gradeName) {
		this.gradeName = gradeName;
	}

	public int getGradeNo() {
		return gradeNo;
	}

	public void setGradeNo(int gradeNo) {
		this.gradeNo = gradeNo;
	}

	@Override
	public String toString() {
		return "LoginVO [id=" + id + ", pw=" + pw + ", name=" + name + ", gradeName=" + gradeName + ", gradeNo="
				+ gradeNo + "]";
	}
	
}
