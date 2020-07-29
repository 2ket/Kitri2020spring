package com.java.fileBoard.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.fileBoard.dao.FileBoardDao;

public class FileBoardServiceImp implements FileBoardService {
	private FileBoardDao fileBoardDao;

	public void setFileBoardDao(FileBoardDao fileBoardDao) {
		this.fileBoardDao = fileBoardDao;
	}

	public FileBoardServiceImp() {	}

	public FileBoardServiceImp(FileBoardDao fileBoardDao) {
		this.fileBoardDao = fileBoardDao;
	}

	@Override
	public void fileBoardWrite(ModelAndView mav) {
		//부모글일 때*(ROOT)
		int boardNumber=0;		// ROOT글이면 0, 
		int groupNumber=1;		// 그룹번호
		int sequenceNumber=0;	// 글 순서
		int sequenceLevel=0;	// 글 레벨
		
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		//답글일때 boardNumber=> ROOT의 글번호, 그룹번호, 글순서, 글레벨
		if(request.getParameter("boardNumber")!=null) {
			//나중에
			boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
			groupNumber=Integer.parseInt(request.getParameter("groupNumber"));
			sequenceNumber=Integer.parseInt(request.getParameter("sequenceNumber"));
			sequenceLevel=Integer.parseInt(request.getParameter("sequenceLevel"));
		}
		
		
		HAspect.logger.info(HAspect.logMsg+boardNumber+"\t"+groupNumber+"\t"+sequenceNumber+"\t"+sequenceLevel);
		
		
		mav.addObject("boardNumber", boardNumber);
		mav.addObject("groupNumber", groupNumber);
		mav.addObject("sequenceNumber", sequenceNumber);
		mav.addObject("sequenceLevel", sequenceLevel);

		mav.setViewName("fileBoard/write");
	}
}
