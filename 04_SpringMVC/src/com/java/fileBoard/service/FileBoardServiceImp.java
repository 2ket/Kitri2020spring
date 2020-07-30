package com.java.fileBoard.service;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import com.java.aop.HAspect;
import com.java.fileBoard.dao.FileBoardDao;
import com.java.fileBoard.dto.FileBoardDto;

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
	
	
	@Override
	public void fileBoardWriteOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		FileBoardDto fileBoardDto=(FileBoardDto)map.get("fileBoardDto");
		MultipartHttpServletRequest request=(MultipartHttpServletRequest)map.get("request");
		//MultipartHttpServletRequest를 사용하면 formField로 들어왔는지 확인하던 작업이 필요없어진다. HSR보다 파일게시판에 쓰기 좋음
		
		fileBoardDto.setWriteDate(new Date());
		fileBoardDto.setReadCount(0);
		writeNumber(fileBoardDto);
		HAspect.logger.info(HAspect.logMsg+fileBoardDto);
		
		//log 결과 : boardNumber=0, writer=kkk, subject=kik, email=abc@abc.com, content=kkk, password=kkk, writeDate=Thu Jul 30 09:18:50 KST 2020, readCount=0, groupNumber=1, sequenceNumber=0, sequenceLevel=0, fileName=null, path=null, fileSize=0
		
		MultipartFile upFile=request.getFile("file");
		
		if(upFile.getSize()!=0) {
			//저장 경로, 파일명, 사이즈
			String fileName=Long.toString(System.currentTimeMillis())+"_"+upFile.getOriginalFilename();
			long fileSize=upFile.getSize();
			
			File path=new File("C:\\pds\\");
			
			path.mkdir();
			
			if(path.exists() && path.isDirectory()) {	//혹은 isFile() 써도 됨
				File file=new File(path, fileName);
				
				try {
					upFile.transferTo(file);
					
					fileBoardDto.setPath(file.getAbsolutePath());
					fileBoardDto.setFileName(fileName);
					fileBoardDto.setFileSize(fileSize);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
		}
		HAspect.logger.info(HAspect.logMsg+fileBoardDto);	
		int check=fileBoardDao.fileBoardWriteOk(fileBoardDto);
		
		HAspect.logger.info(HAspect.logMsg+check);	
		mav.addObject("check", check);
		mav.setViewName("fileBoard/writeOk");
	}
	
	
	
	public void writeNumber(FileBoardDto fileBoardDto) {
		// 그룹번호(ROOT), 글순서(자식), 글 레벨(자식)
		int boardNumber = fileBoardDto.getBoardNumber();
		int groupNumber = fileBoardDto.getGroupNumber();
		int sequenceNumber = fileBoardDto.getSequenceNumber();
		int sequenceLevel = fileBoardDto.getSequenceLevel();

	
		if (boardNumber == 0) { // root.
			int max=fileBoardDao.fileBoardGroupNumberMax();
			
			if(max!=0) {
				fileBoardDto.setGroupNumber(max+1);
			}
			HAspect.logger.info(HAspect.logMsg+max);	
			
		} else { // 자식글, 답글 : 글순서, 글레벨
			
			HashMap<String, Integer> hmap=new HashMap<String, Integer>();
			hmap.put("groupNumber", groupNumber);
			hmap.put("sequenceNumber", sequenceNumber);
			
			int check=fileBoardDao.fileBoardWriteNumber(hmap);//fileBoard_update_number
			HAspect.logger.info(HAspect.logMsg+check);	
			
			sequenceNumber+=1;
			sequenceLevel+=1;
			
			fileBoardDto.setSequenceNumber(sequenceNumber);
			fileBoardDto.setSequenceLevel(sequenceLevel);
		}
		
	}
	
	@Override
	public void fileBoardList(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		//페이징
		String pageNumber=request.getParameter("pageNumber");
		if(pageNumber==null) pageNumber="1";
		
		int currentPage=Integer.parseInt(pageNumber);	//요청한 페이지
		
		int boardSize=10;	//[1] start:1, end:10 [2] s:11, e:20
		
		int startRow=(currentPage-1)*boardSize+1;		// 시작번호 1 (1~10)
		int endRow=currentPage*boardSize;				// 끝번호 10

		//count 사용해서 글이 아예 없는경우 페이징 안보이게
		int count=fileBoardDao.fileBoardCount();
		HAspect.logger.info(HAspect.logMsg+count);	
		List<FileBoardDto> boardList=null;
		if(count>0) {
			//startRow, endRow
			boardList=fileBoardDao.fileBoardList(startRow, endRow);
			HAspect.logger.info(HAspect.logMsg+boardList.size());
		}
		
		mav.addObject("boardList", boardList);
		//db에 있는 애들 없는 애들 구분 잘해서 넘겨야 함
		mav.addObject("boardSize", boardSize);
		mav.addObject("currentPage", currentPage);
		mav.addObject("count", count);
		mav.setViewName("fileBoard/list");
	}
	
	//read의 경우 try-catch를 aop로 처리하기떄문에 rolback에 대한 설정을 따로 해줘야하는데 이걸 같이 할거임
	@Override
	public void fileBoardRead(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		HAspect.logger.info(HAspect.logMsg+boardNumber+"\t"+pageNumber);
		
		FileBoardDto fileBoardDto=fileBoardDao.fileBoardRead(boardNumber);
		HAspect.logger.info(HAspect.logMsg + "fileBoardDto : " + fileBoardDto);
		
		if(fileBoardDto.getFileSize()!=0) {
			int idx=fileBoardDto.getFileName().indexOf('_')+1;
			fileBoardDto.setFileName(fileBoardDto.getFileName().substring(idx));
		}
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("boardDto", fileBoardDto);
		mav.setViewName("fileBoard/read");
	}
	
	@Override
	public void fileBoardDownLoad(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		HttpServletResponse response=(HttpServletResponse)map.get("response");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		HAspect.logger.info(HAspect.logMsg+boardNumber);
		
		FileBoardDto boardDto=fileBoardDao.fileBoardSelect(boardNumber);
		HAspect.logger.info(HAspect.logMsg+boardDto);
		
		
		BufferedInputStream bis=null;
		BufferedOutputStream bos=null;
		try {
			int index=boardDto.getFileName().indexOf('_')+1;
			String fileName=boardDto.getFileName().substring(index);
			String utfName=new String(fileName.getBytes("utf-8"), "ISO-8859-1");
			long fileSize=boardDto.getFileSize();
			String path=boardDto.getPath();
			
			//content_Disposition : 다운로드 할때 나오는 그 창 띄우게 하는거
			response.setHeader("content-Disposition", "attachment;filename="+utfName);//정해진 고정 문구니까 외우기
			//한글 깨짐 위해 utf-8로 인코딩 해줘야한다. 혹은 //URLEncoder.encode(fileName, "utf-8")넣어준다.
			response.setContentType("application/octet-stream");	//8진수(octet) : binary형태일때
			response.setContentLengthLong(fileSize);
			
		
			bis=new BufferedInputStream(new FileInputStream(path), 1024);
			bos=new BufferedOutputStream(response.getOutputStream(), 1024);
			while(true) {
				int data=bis.read();
				if(data==-1) break;
				bos.write(data);
			}
			bos.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				if(bis!=null) bis.close();
				if(bos!=null) bos.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
	}
	@Override
	public void fileBoardDelete(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		mav.addObject("boardNumber", boardNumber);
		mav.addObject("pageNumber", pageNumber);

		mav.setViewName("fileBoard/delete");
	}
	@Override
	public void fileBoardDeleteOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		Map<String, Object> hmap=new HashMap<String, Object>();
		hmap.put("boardNumber", boardNumber);
		hmap.put("password", request.getParameter("password"));
		
		//파일경로 뽑아오기위한 Dto 삭제전에 불러오기
		FileBoardDto dto=fileBoardDao.fileBoardRead(boardNumber);
		
		int check=fileBoardDao.fileBoardDeleteOk(hmap);//DB삭제
		
		if(check>0 && dto.getFileName()!=null) {	//dto에 파일명이 있으면
			File file=new File(dto.getPath());		//파일경로의 파일 찾아서
			if(file.exists() && file.isFile()) file.delete();//그게 존재하는 파일이면 삭제
		}
		
		
		mav.addObject("pageNumber", pageNumber);
		mav.addObject("check", check);
		mav.setViewName("fileBoard/deleteOk");
	}
	
	@Override
	public void fileBoardUpdate(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		
		FileBoardDto dto=fileBoardDao.fileBoardRead(boardNumber);
		
		mav.addObject("boardDto", dto);
		mav.addObject("boardNumber", boardNumber);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("fileBoard/update");
	}
	@Override
	public void fileBoardUpdateOk(ModelAndView mav) {
		Map<String, Object> map=mav.getModelMap();
		HttpServletRequest request=(HttpServletRequest)map.get("request");
		int boardNumber=Integer.parseInt(request.getParameter("boardNumber"));
		int pageNumber=Integer.parseInt(request.getParameter("pageNumber"));
		int fileDelCheck=Integer.parseInt(request.getParameter("fileDelCheck"));
		
		FileBoardDto upadteDto = (FileBoardDto)map.get("fileBoardDto");
		
		
		HAspect.logger.info(HAspect.logMsg+upadteDto+"fileDelCheck : "+fileDelCheck);
		//파일경로 뽑아오기위한 Dto 삭제전에 불러오기
		FileBoardDto dto=fileBoardDao.fileBoardRead(boardNumber);
		if(fileDelCheck==1 && dto.getFileName()!=null) {	//dto에 파일명이 있으면
			File file=new File(dto.getPath());				//파일경로의 파일 찾아서
			if(file.exists() && file.isFile()) file.delete();//그게 존재하는 파일이면 삭제
		}
//		if(fileDelCheck)
		
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName("fileBoard/updateOk");
	}
}
