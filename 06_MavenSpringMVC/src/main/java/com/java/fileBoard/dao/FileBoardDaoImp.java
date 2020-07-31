package com.java.fileBoard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.java.fileBoard.dto.FileBoardDto;

@Component
public class FileBoardDaoImp implements FileBoardDao {
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	

	@Override
	public int fileBoardGroupNumberMax() {
		return sqlSessionTemplate.selectOne("fileBoard_maxGroup");
	}
	
	@Override
	public int fileBoardWriteNumber(HashMap<String, Integer> hmap) {
		return sqlSessionTemplate.update("fileBoard_update_number", hmap);
	}
	
	@Override
	public int fileBoardWriteOk(FileBoardDto fileBoardDto) {
		if(fileBoardDto.getFileName()==null) {
			return sqlSessionTemplate.insert("fileBoard_insert", fileBoardDto);
		}
		return sqlSessionTemplate.insert("fileBoard_insert_file", fileBoardDto);
	}
	
	@Override
	public int fileBoardCount() {
		return sqlSessionTemplate.selectOne("fileBoard_getCount");
	}
	@Override
	public List<FileBoardDto> fileBoardList(int startRow, int endRow) {
		Map<String, Integer> hMap=new HashMap<String, Integer>();
		hMap.put("startRow", startRow);
		hMap.put("endRow", endRow);
		return sqlSessionTemplate.selectList("fileBoard_list", hMap);
	}
	@Override
	public FileBoardDto fileBoardRead(int boardNumber) {
		sqlSessionTemplate.update("fileBoard_view", boardNumber);
		return sqlSessionTemplate.selectOne("fileBoard_read", boardNumber);
	}
	@Override
	public FileBoardDto fileBoardSelect(int boardNumber) {
		return sqlSessionTemplate.selectOne("fileBoard_read", boardNumber);
	}
	@Override
	public int fileBoardDeleteOk(Map<String, Object> hmap) {
		return sqlSessionTemplate.delete("fileBoard_delete", hmap);
	}
	@Override
	public int fileBoardUpdateOk(FileBoardDto updateDto, int fileDelCheck) {
		if(fileDelCheck==1&&updateDto.getFileName()==null) {
			return sqlSessionTemplate.update("fileBoard_update_delFile", updateDto);
		}else if(fileDelCheck==1&&updateDto.getFileName()!=null) {
			return sqlSessionTemplate.update("fileBoard_update_file", updateDto);
		}
		//그외 삭제명령 없이 파일변경없을때
		return sqlSessionTemplate.update("fileBoard_update", updateDto);
	}
}
