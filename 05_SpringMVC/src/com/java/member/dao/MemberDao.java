package com.java.member.dao;

import java.util.List;
import java.util.Map;

import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

public interface MemberDao {

	public int memberInsert(MemberDto memberDto);
	public int memberIdCheck(String id);
	public List<ZipcodeDto> zipcode(String dong);
	public String memberLoginOk(Map<String, String> map);
	public MemberDto memberUpdate(String id);
	public int memberUpdateOk(MemberDto memberDto);
	public int memberDeleteOk(Map<String, String> hmap);
}
