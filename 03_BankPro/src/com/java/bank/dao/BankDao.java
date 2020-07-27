package com.java.bank.dao;

import java.util.List;

import com.java.bank.dto.BankDto;

public interface BankDao {
	public int makeAccount(BankDto bankDto);
	public List<BankDto> showData();
	public BankDto select(String id);
	public int update(BankDto bankDto);

}
