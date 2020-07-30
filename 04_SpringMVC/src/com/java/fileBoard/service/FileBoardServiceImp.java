package com.java.fileBoard.service;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
		int check=0;
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
			HAspect.logger.info(HAspect.logMsg+fileBoardDto);	
			check=fileBoardDao.fileBoardWriteOkFile(fileBoardDto);
		}else {
			check=fileBoardDao.fileBoardWriteOk(fileBoardDto);
		}
		
		HAspect.logger.info(HAspect.logMsg+check);	
		
	}
	
	public void writeNumber(FileBoardDto fileBoardDto) {
		// 그룹번호(ROOT), 글순서(자식), 글 레벨(자식)
		int boardNumber = fileBoardDto.getBoardNumber();
		int groupNumber = fileBoardDto.getGroupNumber();
		int sequenceNumber = fileBoardDto.getSequenceNumber();
		int sequenceLevel = fileBoardDto.getSequenceLevel();

	
		if (boardNumber == 0) { // root.
			int max=fileBoardDao.fileBoardGroupNumberMax();
					//session.selectOne("fileBoard_maxGroup");
			
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
		
	//read의 경우 try-catch를 aop로 처리하기떄문에 rolback에 대한 설정을 따로 해줘야하는데 이걸 같이 할거임
	
//		DiskFileItemFactory factory=new DiskFileItemFactory();		// 파일 보관 객체
//		ServletFileUpload upload=new ServletFileUpload(factory);			// 요청 처리 객체
//		List<FileItem> list=upload.parseRequest(request);
//		Iterator<FileItem> iter=list.iterator();
//		
//		BoardDto boardDto=new BoardDto();
//		HashMap<String, String> dataMap=new HashMap<String, String>();
//		
//		while(iter.hasNext()) {
//			FileItem fileItem=iter.next();
//			if(fileItem.isFormField()) {	// text(유저 입력이나 hidden속성으로 넣은 text형태들 : writer, groupNumber, subject...
//				//필드 이름 찍기
////				String name=fileItem.getFieldName();
////				logger.info(logMsg+ "text : "+name);
//				//맵방식이 아닌 기존의 경우 아래와 같이 필드마다 dto에 넣어줘야함
////				if(fileItem.getFieldName().equals("boardNumber")) {
////					boardDto.setBoardNumber(Integer.parseInt(fileItem.getString()));
////				}
//				
//				String name=fileItem.getFieldName();
//				String value=fileItem.getString("utf-8");
//				logger.info(logMsg+name+":"+value);
//				
//				dataMap.put(name, value);
//				
//			}else {							// text가 아닌 것들(파일관련) : file
////				String name=fileItem.getFieldName();
////				logger.info(logMsg+ "binary : "+name+", "+fileItem.getName()+", "+fileItem.getSize());
//				if(fileItem.getFieldName().equals("file")) {
//					//파일명 fileItem.getName() / 파일사이즈 fileItem.getSize() / getInputStream()
//					if(fileItem.getName()==null || fileItem.getName().equals("")) continue;
//					
//					upload.setFileSizeMax(1024*1024*10); 	// byte*kb*mb*gb
//					String fileName=System.currentTimeMillis()+"_"+fileItem.getName();
//					
//					//절대경로(안씀)
////					String dir="C:\\Users\\user\\git\\kitri2020mvc\\MVCHomepage\\WebContent\\pds";
//					String dir="C:\\Kitri2020\\mvc\\workspace\\MyBatisHomepage\\WebContent\\pds";
//					File file=new File(dir, fileName);
//					
//					//톰캣 실제 서버경로(웹서버에 직접 올리면 부피가 너무 커짐. 따로 ftp서버를 둬야함.
////					String dir=request.getServletContext().getRealPath("\\pds\\");
////					logger.info(logMsg+dir);
//					
//					//폴더 생성해주기
////					File dir=new File("c:\\pds\\");
////					dir.mkdir();
////					File file=null;
////					if(dir.exists() && dir.isDirectory()) {
////						file=new File(dir, fileName);
////						
////					}
//					
////					logger.info(logMsg+file.getAbsolutePath());
//					BufferedInputStream bis=null;	// 클라이언트의 파일을 읽어서
//					BufferedOutputStream bos=null; 	// 서버에 저장
//					try {
//						bis=new BufferedInputStream(fileItem.getInputStream(), 1024);
//						bos=new BufferedOutputStream(new FileOutputStream(file), 1024);
//						
//						while(true) {
//							int data=bis.read();
//							if(data==-1) break;
//							
//							bos.write(data);
//						}
//						bos.flush();
//					}catch(IOException e) {
//						e.printStackTrace();
//					}finally {
//						if(bis!=null) bis.close();
//						if(bos!=null) bos.close();
//					}
//					boardDto.setFileName(fileName);
//					boardDto.setFileSize(fileItem.getSize());
//					boardDto.setPath(file.getAbsolutePath());
//				}
//				
//			}
//			
//		}
//		boardDto.setDataMap(dataMap);
//		boardDto.setWriteDate(new Date());
//		logger.info(logMsg+boardDto.toString());
//		
//		int check=BoardDao.getInstance().insert(boardDto);
//		logger.info(logMsg+check);
//		
//		request.setAttribute("check", check);
//		
	
}
