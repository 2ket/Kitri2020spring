package com.java.bank.ui;

import java.util.Scanner;

import com.java.bank.aop.LogAspect;
import com.java.bank.dao.BankDao;
import com.java.bank.dto.BankDto;

public class BankView implements BankUI {
private BankDto bankDto;
private BankDao bankDao;

	public BankView() {}
	
	public BankView(BankDto bankDto, BankDao bankDao) {
		this.bankDto=bankDto;
		this.bankDao=bankDao;
	}
	
	public void setBankDto(BankDto bankDto) {
		this.bankDto = bankDto;
	}

	public void setBankDao(BankDao bankDao) {
		this.bankDao = bankDao;
	}

	@Override
	public void execute() {
		// TODO Auto-generated method stub
		printMenu();
		
		System.out.println("�����ϼ���.");
		Scanner sc= new Scanner(System.in);
		int choice=sc.nextInt();
		
		switch (choice) {
		case 1: makeAccount();break;
		case 2: break;
		case 3: break;
		case 4: break;
		case 5: break;
		case 6: break;
			
		
		default:
			System.out.println("�߸��Է��ϼ̽��ϴ�.");
		}
		
		sc.close();
	}
	
	public void printMenu() {
		System.out.println("MENU------------------");
		System.out.println("1. ���� ����");
		System.out.println("2. �Ա�");
		System.out.println("3. ���");
		System.out.println("4. �ܾ���ȸ");
		System.out.println("5. ��ü���");
		System.out.println("6. ����");
	}
	public void makeAccount() {
		Scanner sc=new Scanner(System.in);
		System.out.println("���¹�ȣ : ");
		
		bankDto.setId(sc.next());
		
		System.out.println("�̸� : ");
		bankDto.setName(sc.next());
		
		System.out.println("�Աݾ� : ");
		bankDto.setBalance(sc.nextLong());
		
		int check=bankDao.makeAccount(bankDto);
		LogAspect.logger.info(LogAspect.logMsg + check);
		
		sc.close();
	}

}
