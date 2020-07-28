package com.java.member.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.java.member.dto.MemberDto;

public class MemberDaoImp implements MemberDao {
	//Mybats SqlSessionTemplate~~
	private SqlSessionTemplate sqlSessionTemplate;
	
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public MemberDaoImp() {}
	public MemberDaoImp(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}


	@Override
	public int memberInsert(MemberDto memberDto) {
		return sqlSessionTemplate.insert("member_insert", memberDto);
	}
	@Override
	public int memberIdCheck(String id) {
		String value=sqlSessionTemplate.selectOne("member_id_check", id);
		int check=0;
		if(value!=null) check=1;
		return check;
	}
}
