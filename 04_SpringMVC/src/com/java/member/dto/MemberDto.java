package com.java.member.dto;

import java.util.Date;


//DTO하고 DB는 갯수가 같아야한다.
public class MemberDto {
	private int num;	//sequence(seq.nextval)
	
	private String id;
	private String pw;
	private String name;
	private String jumin1;
	private String jumin2;
	private String email;
	private String zipCode;
	private String addr;
	private String job;
	private String mailing;
	private String interest;
	
	private String memberLevel;	// 
	private Date registerDate;	// java : Date, DB: sysdate
	
	
	
	public MemberDto() {	}



	//Construct Using Field
	public MemberDto(int num, String id, String pw, String name, String jumin1, String jumin2, String email,
			String zipCode, String addr, String job, String mailing, String interest, String memberLevel,
			Date registerDate) {
		super();
		this.num = num;
		this.id = id;
		this.pw = pw;
		this.name = name;
		this.jumin1 = jumin1;
		this.jumin2 = jumin2;
		this.email = email;
		this.zipCode = zipCode;
		this.addr = addr;
		this.job = job;
		this.mailing = mailing;
		this.interest = interest;
		this.memberLevel = memberLevel;
		this.registerDate = registerDate;
	}



	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
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
	public String getJumin1() {
		return jumin1;
	}
	public void setJumin1(String jumin1) {
		this.jumin1 = jumin1;
	}
	public String getJumin2() {
		return jumin2;
	}
	public void setJumin2(String jumin2) {
		this.jumin2 = jumin2;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public String getJob() {
		return job;
	}
	public void setJob(String job) {
		this.job = job;
	}
	public String getMailing() {
		return mailing;
	}
	public void setMailing(String mailing) {
		this.mailing = mailing;
	}
	public String getInterest() {
		return interest;
	}
	public void setInterest(String interest) {
		this.interest = interest;
	}
	public String getMemberLevel() {
		return memberLevel;
	}
	public void setMemberLevel(String memberLevel) {
		this.memberLevel = memberLevel;
	}
	public Date getRegisterDate() {
		return registerDate;
	}
	public void setRegisterDate(Date registerDate) {
		this.registerDate = registerDate;
	}
	
	@Override
	public String toString() {
		return "MemberDto [num=" + num + ", id=" + id + ", pw=" + pw + ", name=" + name + ", jumin1=" + jumin1
				+ ", jumin2=" + jumin2 + ", email=" + email + ", zipCode=" + zipCode + ", addr=" + addr + ", job=" + job
				+ ", mailing=" + mailing + ", interest=" + interest + ", memberLevel=" + memberLevel + ", registerDate="
				+ registerDate + "]";
	}
	
	
}
