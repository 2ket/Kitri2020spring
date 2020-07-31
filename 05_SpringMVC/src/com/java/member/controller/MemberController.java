package com.java.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;
import org.springframework.web.servlet.view.InternalResourceView;

import com.java.aop.HAspect;
import com.java.member.dto.MemberDto;
import com.java.member.service.MemberService;

@Controller
public class MemberController{	//command
	@Autowired
	private MemberService memberService;
	
	/* service를 주입받기 위해서 setter와 생성자를 만들었던것. Annotation으론 @Autowired으로 한번에 주입받을 수 있다.
	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}
	public MemberController() {}
	public MemberController(MemberService memberService) {
		this.memberService = memberService;
	}
	*/
	
	@RequestMapping(value="/member/test.do", method=RequestMethod.GET)
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
	@RequestMapping(value="/member/register.do", method=RequestMethod.GET)
	public ModelAndView memberRegister(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/register");
	}
	@RequestMapping(value="/member/registerOk.do", method=RequestMethod.POST)
	public ModelAndView memberRegisterOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		HAspect.logger.info(HAspect.logMsg+memberDto.toString());
		
		ModelAndView mav=new ModelAndView();
		mav.addObject("memberDto", memberDto);
		
		memberService.memberRegisterOk(mav);//controller의 함수명과 같게 하는게 좋다.
		return mav;
	}
	@RequestMapping(value="/member/idCheck.do", method=RequestMethod.GET)
	public ModelAndView memberIdCheck(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		memberService.memberIdCheck(mav);
		return mav;
	}
	@RequestMapping(value="/member/zipcode.do", method=RequestMethod.GET)
	public ModelAndView memberZipcode(HttpServletRequest request, HttpServletResponse response) {
      ModelAndView mav = new ModelAndView();
      mav.addObject("request", request);
      memberService.memberZipcode(mav);
      
      return mav;
	}
	@RequestMapping(value="/member/login.do", method=RequestMethod.GET)
	public ModelAndView memberLogin(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/login");
	}
	@RequestMapping(value="/member/loginOk.do", method=RequestMethod.POST)
	public ModelAndView memberLoginOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav= new ModelAndView();
		mav.addObject("request",request);
		memberService.memberLoginOk(mav);
		
		return mav;
	}
	@RequestMapping(value="/member/main.do", method=RequestMethod.GET)
	public ModelAndView memberMain(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/main");
	}
	@RequestMapping(value="/member/logout.do", method=RequestMethod.GET)
	public ModelAndView memberLogout(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/logout");
	}
	@RequestMapping(value="/member/update.do", method=RequestMethod.GET)
	public ModelAndView memberUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav= new ModelAndView();
		mav.addObject("request",request);
		
		memberService.memberUpdate(mav);
		return mav;
	}
	@RequestMapping(value="/member/updateOk.do", method=RequestMethod.POST)
	public ModelAndView memberUpdateOk(HttpServletRequest request, HttpServletResponse response, MemberDto memberDto) {
		ModelAndView mav= new ModelAndView();
		mav.addObject("memberDto",memberDto);
		memberService.memberUpdateOk(mav);
		return mav;
	}
	@RequestMapping(value="/member/delete.do", method=RequestMethod.GET)
	public ModelAndView memberDelete(HttpServletRequest request, HttpServletResponse response) {
		return new ModelAndView("member/delete");
	}
	@RequestMapping(value="/member/deleteOk.do", method=RequestMethod.POST)
	public ModelAndView memberDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav= new ModelAndView();
		mav.addObject("request",request);
		memberService.memberDeleteOk(mav);
		
		return mav;
	}
	
}
