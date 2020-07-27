package com.java.bank.dao;

import org.mybatis.spring.SqlSessionTemplate;

import com.java.bank.dto.BankDto;

public class BankDaoImp implements BankDao {
	private SqlSessionTemplate sqlSessionTemplate;
	
	public BankDaoImp() {}
	public BankDaoImp(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}
	
	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate = sqlSessionTemplate;
	}

	@Override
	public int makeAccount(BankDto bankDto) {
		System.out.println(bankDto.toString());
		int check=0;
		check=sqlSessionTemplate.insert("bank_insert", bankDto);//Spring Mybatis는 auto commit이 된다
		return check;
	}

}
