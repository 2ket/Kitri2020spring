package com.java.fileBoard.dao;

import java.util.HashMap;

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
	public int fileBoardWriteOkFile(FileBoardDto fileBoardDto) {
		// TODO Auto-generated method stub
		return sqlSessionTemplate.insert("fileBoard_insert_file", fileBoardDto);
	}
	@Override
	public int fileBoardWriteOk(FileBoardDto fileBoardDto) {
		return sqlSessionTemplate.insert("fileBoard_insert", fileBoardDto);
	}
}
