package com.java.fileBoard.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.java.fileBoard.dto.FileBoardDto;

public class FileBoardDaoImp implements FileBoardDao {

	private SqlSessionTemplate sqlSessionTemplate;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	public FileBoardDaoImp() {	}

	public FileBoardDaoImp(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

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
}
