package com.java.fileBoard.dao;

import java.util.HashMap;

import com.java.fileBoard.dto.FileBoardDto;

public interface FileBoardDao {

	int fileBoardGroupNumberMax();
	int fileBoardWriteNumber(HashMap<String, Integer> hmap);
	int fileBoardWriteOkFile(FileBoardDto fileBoardDto);
	int fileBoardWriteOk(FileBoardDto fileBoardDto);

}
