package com.java.member.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.member.dao.MemberDao;
import com.java.member.dto.MemberDto;
import com.java.member.dto.ZipcodeDto;

@Component
public class MemberServiceImp implements MemberService {
	@Autowired
	private MemberDao memberDao;

	/*	@Autowired로 사라진 코드
	public void setMemberDao(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	public MemberServiceImp() {}
	public MemberServiceImp(MemberDao memberDao) {
		this.memberDao = memberDao;
	}
	*/
	@Override
	public void memberRegisterOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		MemberDto memberDto=(MemberDto)map.get("memberDto");
		
		memberDto.setMemberLevel("BB");
		
		int check=memberDao.memberInsert(memberDto);
		HAspect.logger.info(HAspect.logMsg+check);
		
		mav.addObject("check", check);
		//mav.setViewName("member/registerOk");
		mav.setViewName("member/registerOk.tiles");
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
		mav.setViewName("member/idCheck.empty");
	}
	@Override
	public void memberZipcode(ModelAndView mav) {
		// TODO Auto-generated method stub
		 Map<String, Object> map = mav.getModelMap();
	     HttpServletRequest request = (HttpServletRequest) map.get("request");
	     
	     String dong = request.getParameter("dong");
	     
	     if(dong != null) {
	        List<ZipcodeDto> zipcodeDto=memberDao.zipcode(dong);
	        HAspect.logger.info(HAspect.logMsg+zipcodeDto.size());
	        
	        mav.addObject("zipcodeList", zipcodeDto);
	     }
	     
	     mav.setViewName("member/zipcode.empty");
	     
	}
	@Override
	public void memberLoginOk(ModelAndView mav) {
		// TODO Auto-generated method stub
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		
		Map<String, String> hmap=new HashMap<String, String>();
		hmap.put("id", request.getParameter("id"));
		hmap.put("pw", request.getParameter("pw"));
		
		HAspect.logger.info(HAspect.logMsg+hmap.toString());
		String value=memberDao.memberLoginOk(hmap);
		HAspect.logger.info(HAspect.logMsg+value);
		
		mav.addObject("memberLevel",value);
		mav.addObject("id", hmap.get("id"));
		//mav.setViewName("member/loginOk");
		mav.setViewName("member/loginOk.tiles");
	}
	
	@Override
	public void memberUpdate(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		HttpSession session=request.getSession();
		
		
		String id=(String)session.getAttribute("id");
		MemberDto memberDto=memberDao.memberUpdate(id);
		HAspect.logger.info(HAspect.logMsg+memberDto);
		
		mav.addObject("memberDto",memberDto);
		//mav.setViewName("member/update");
		mav.setViewName("member/update.tiles");
		
	}
	@Override
	public void memberUpdateOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		MemberDto memberDto=(MemberDto)map.get("memberDto");
		HAspect.logger.info(HAspect.logMsg+memberDto);
		
		int check=memberDao.memberUpdateOk(memberDto);
		HAspect.logger.info(HAspect.logMsg+check);
		mav.addObject("check",check);
		//mav.setViewName("member/updateOk");
		mav.setViewName("member/updateOk.tiles");
	}
	
	@Override
	public void memberDeleteOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		HttpSession session=request.getSession();
//		String pw=request.getParameter("pw");
//		String id=(String)session.getAttribute("id");
		
		Map<String, String> hmap=new HashMap<String, String>();
		hmap.put("id", (String)session.getAttribute("id"));
		hmap.put("pw", request.getParameter("pw"));
		int check=memberDao.memberDeleteOk(hmap);
		HAspect.logger.info(HAspect.logMsg+check);
		
		mav.addObject("check", check);
		//mav.setViewName("member/deleteOk");
		mav.setViewName("member/deleteOk.tiles");
	}
}
