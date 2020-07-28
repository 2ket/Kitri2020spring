package com.java.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

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
	
	public void testing(HttpServletRequest request, HttpServletResponse response) {
		//서비스 - DAO&DTO - 서비스 
		
		System.out.println("OK. 나오나요?");
		
		//return "";
	}

}
