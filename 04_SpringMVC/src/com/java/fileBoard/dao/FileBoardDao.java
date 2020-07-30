package com.java.fileBoard.dao;

import java.util.HashMap;
import java.util.List;

import com.java.fileBoard.dto.FileBoardDto;

public interface FileBoardDao {

	public int fileBoardGroupNumberMax();
	public int fileBoardWriteNumber(HashMap<String, Integer> hmap);
	public int fileBoardWriteOk(FileBoardDto fileBoardDto);
	public int fileBoardCount();
	public List<FileBoardDto> fileBoardList(int startRow, int endRow);
	public FileBoardDto fileBoardRead(int boardNumber);

}
