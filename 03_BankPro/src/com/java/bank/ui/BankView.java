package com.java.bank.ui;

import java.util.List;
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
		
		System.out.println("선택하세요.");
		Scanner sc= new Scanner(System.in);
		int choice=sc.nextInt();
		
		switch (choice) {
		case 1: makeAccount();break;
		case 2: deposit();break;
		case 3: break;
		case 4: break;
		case 5: showData();break;
		case 6: break;
			
		
		default:
			System.out.println("잘못입력하셨습니다.");
		}
		
		sc.close();
	}
	
	public void printMenu() {
		System.out.println("MENU------------------");
		System.out.println("1. 계좌 개설");
		System.out.println("2. 입금");
		System.out.println("3. 출금");
		System.out.println("4. 잔액조회");
		System.out.println("5. 전체출력");
		System.out.println("6. 종료");
	}
	public void makeAccount() {
		Scanner sc=new Scanner(System.in);
		System.out.println("계좌번호 : ");
		
		bankDto.setId(sc.next());
		
		System.out.println("이름 : ");
		bankDto.setName(sc.next());
		
		System.out.println("입금액 : ");
		bankDto.setBalance(sc.nextLong());
		
		int check=bankDao.makeAccount(bankDto);
		LogAspect.logger.info(LogAspect.logMsg + check);
		
		sc.close();
	}
	public void showData() {
		List<BankDto> bankList=bankDao.showData();
		LogAspect.logger.info(LogAspect.logMsg+bankList.size());
		
		for(int i=0; i<bankList.size(); i++) {
			BankDto dto=bankList.get(i);
			System.out.println(dto.toString());
		}
	}
	public void deposit() {
		Scanner sc=new Scanner(System.in);
		System.out.println("계좌번호:");
		String id=sc.next();
		
		bankDto=bankDao.select(id);
		LogAspect.logger.info(LogAspect.logMsg+bankDto);
		if(bankDto != null) {
			System.out.println("입금액");
			long money=sc.nextLong();
			
			bankDto.setBalance(bankDto.getBalance()+money);
			int check=bankDao.update(bankDto);
			LogAspect.logger.info(LogAspect.logMsg+check);
			if(check>0) {
				System.out.println("입금완료. 잔액을 확인하세요.");
			}else {
				System.out.println("입금에 실패했습니다. 다시 시도해주세요.");
			}
		}else {
			System.out.println("계좌번호가 존재하지 않습니다.");
		}
		
		sc.close();
	}
}
