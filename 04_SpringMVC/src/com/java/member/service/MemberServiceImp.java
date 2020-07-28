package com.java.member.service;

import com.java.member.dao.MemberDao;

public class MemberServiceImp implements MemberService {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public MemberServiceImp() {}
	public MemberServiceImp(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
}
