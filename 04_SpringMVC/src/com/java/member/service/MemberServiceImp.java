package com.java.member.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;

public class MemberServiceImp implements MemberService {
	private MemberDao memberDao;

	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public MemberServiceImp() {}
	public MemberServiceImp(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	
	@Override
	public void memberRegisterOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		MemberDto memberDto=(MemberDto)map.get("memberDto");
		
		memberDto.setMemberLevel("BB");
		
		int check=memberDao.memberInsert(memberDto);
		HAspect.logger.info(HAspect.logMsg+check);
		
		mav.addObject("check", check);
		mav.setViewName("member/registerOk");
	}
	@Override
	public void memberIdCheck(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		String id=request.getParameter("id");
		HAspect.logger.info(HAspect.logMsg+id);
		int check=memberDao.memberIdCheck(id);
		HAspect.logger.info(HAspect.logMsg+check);
		
		mav.addObject("check", check);
		mav.addObject("id", id);
		mav.setViewName("member/idCheck");
	}
}
