package com.java.fileBoard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.java.fileBoard.dto.FileBoardDto;
import com.java.fileBoard.service.FileBoardService;

@Controller
public class FileBoardController{
	@Autowired
	private FileBoardService fileBoardService;

	@RequestMapping(value="/fileBoard/write.do")
	public ModelAndView fileBoardWrite(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		fileBoardService.fileBoardWrite(mav);
		return mav;
	}
	@RequestMapping("/fileBoard/writeOk.do")
	public ModelAndView fileBoardWriteOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("fileBoardDto", fileBoardDto);
		
		fileBoardService.fileBoardWriteOk(mav);
		return mav;
	}
	@RequestMapping(value="/fileBoard/list.do", method=RequestMethod.GET)
	public ModelAndView fileBoardList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		fileBoardService.fileBoardList(mav);
		return mav;
	}
	@RequestMapping("fileBoardRead")
	public ModelAndView fileBoardRead(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		fileBoardService.fileBoardRead(mav);
		return mav;
	}
	@RequestMapping("fileBoardDownLoad")
	public void fileBoardDownLoad(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("response", response);//다운로드 작업을 위해
		
		fileBoardService.fileBoardDownLoad(mav);
	}
	@RequestMapping("fileBoardDelete")
	public ModelAndView fileBoardDelete(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		fileBoardService.fileBoardDelete(mav);
		return mav;
	}
	@RequestMapping("fileBoardDeleteOk")
	public ModelAndView fileBoardDeleteOk(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		fileBoardService.fileBoardDeleteOk(mav);
		return mav;
	}
	@RequestMapping("fileBoardUpdate")
	public ModelAndView fileBoardUpdate(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		
		fileBoardService.fileBoardUpdate(mav);
		return mav;
	}
	@RequestMapping("fileBoardUpdateOk")
	public ModelAndView fileBoardUpdateOk(HttpServletRequest request, HttpServletResponse response, FileBoardDto fileBoardDto) {
		ModelAndView mav=new ModelAndView();
		mav.addObject("request", request);
		mav.addObject("fileBoardDto", fileBoardDto);
		fileBoardService.fileBoardUpdateOk(mav);
		return mav;
	}
}
