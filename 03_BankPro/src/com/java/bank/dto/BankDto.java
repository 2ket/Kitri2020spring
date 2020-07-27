package com.java.bank.dto;

public class BankDto {
	private int num;
	private String id;
	private String name;
	private long balance;
	
	public BankDto() {}

	public BankDto(int num, String id, String name, long balance) {
		super();
		this.num = num;
		this.id = id;
		this.name = name;
		this.balance = balance;
	}

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getBalance() {
		return balance;
	}

	public void setBalance(long balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "BankDto [num=" + num + ", id=" + id + ", name=" + name + ", balance=" + balance + "]";
	}
	
	
}
