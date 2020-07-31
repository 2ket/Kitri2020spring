package com.java.member.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

@Component		//@Cotroller 외엔 다 @Component쓴다
public class MemberDaoImp implements MemberDao {
	//Mybats SqlSessionTemplate~~
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	/* @Autowired를 사용하면 sqlSessionTemplate 주입받기위해 했던 일련의 과정들이 사라진다(setter, constructor using field)
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public MemberDaoImp() {}
	public MemberDaoImp(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
*/

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
	@Override
	public List<ZipcodeDto> zipcode(String dong) {
		return sqlSessionTemplate.selectList("member_zipcode", dong);//dto.memberDto.member_zipcode
	}
	
	@Override
	public String memberLoginOk(Map<String, String> map) {
		return sqlSessionTemplate.selectOne("member_login", map);
	}
	@Override
	public MemberDto memberUpdate(String id) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.selectOne("member_select", id);
	}
	@Override
	public int memberUpdateOk(MemberDto memberDto) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.update("member_update", memberDto);
	}
	@Override
	public int memberDeleteOk(Map<String, String> hmap) {
		return sqlSessionTemplate.delete("member_delete", hmap);
	}
}
