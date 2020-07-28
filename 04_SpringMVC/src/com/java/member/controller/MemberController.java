package com.java.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.InternalResourceView;

import com.java.aop.HAspect;
import com.java.member.dto.MemberDto;
import com.java.member.service.MemberService;

public class MemberController extends MultiActionController{	//command
	private MemberService memberService;
	
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public MemberController() {}
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	public ModelAndView testing(HttpServletRequest request, HttpServletResponse response) {
		//서비스 - DAO&DTO - 서비스 
		
		System.out.println("OK.");
		
		//View
//		InternalResourceView view=new InternalResourceView("/WEB-INF/member/test.jsp");
//		ModelAndView mav=new ModelAndView();
//		mav.addObject("msg", "hi");
//		mav.setView(view);
		
		ModelAndView mav=new ModelAndView("member/testing");
		mav.addObject("msg", "hi");
		return mav;
	}
	public ModelAndView memberRegister(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/register");
	}
	public void memberRegisterOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		HAspect.logger.info(HAspect.logMsg+memberDto.toString());
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("memberDto", memberDto);
		
		memberService.memberRegisterOk(mav);//controller의 함수명과 같게 하는게 좋다.
	}
}
