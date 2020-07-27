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
		
		boolean loop=true;
		Scanner sc=null;
		while(loop) {
		
			printMenu();
			sc=new Scanner(System.in);
			System.out.println("선택하세요.");
			int choice=sc.nextInt();
			
			switch (choice) {
			case 1: makeAccount();break;
			case 2: deposit();break;
			case 3: withdraw();break;
			case 4: inquiry();break;
			case 5: showData();break;
			case 6: loop=false;break;
				
			default:
				System.out.println("잘못입력하셨습니다.");
			}
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
		String id=sc.next();
		BankDto dto=bankDao.select(id);
		if(dto != null) {
			System.out.println("이미 존재하는 계좌번호입니다. 다른 번호를 입력해주세요.");
		}else {
			bankDto.setId(id);
			System.out.println("이름 : ");
			bankDto.setName(sc.next());
			
			System.out.println("입금액 : ");
			bankDto.setBalance(sc.nextLong());
			
			int check=bankDao.makeAccount(bankDto);
			LogAspect.logger.info(LogAspect.logMsg + check);
			
			System.out.println("계좌가 생성되었습니다.");
		}
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

	private void withdraw() {
		Scanner sc=new Scanner(System.in);
		System.out.println("계좌번호를 입력하세요.");
		String id=sc.next();
		bankDto=bankDao.select(id);
		if(bankDto!=null) {
			System.out.println("현재 잔액 : "+bankDto.getBalance());
			System.out.println("출금하실 금액을 입력하세요.");
			Long money=sc.nextLong();
			if(money>bankDto.getBalance()) {
				System.out.println("잔액이 부족합니다.");
			}else {
				bankDto.setBalance(bankDto.getBalance()-money);
				int check=bankDao.update(bankDto);
				if(check>0) {
					System.out.println("출금완료.");
				}else {
					System.out.println("출금에 실패했습니다. 다시 시도해주세요.");
				}
			}
		}else {
			System.out.println("계좌번호가 존재하지 않습니다.");
		}
		sc.close();
	}
	public void inquiry() {
		Scanner sc=new Scanner(System.in);
		System.out.println("계좌번호를 입력하세요.");
		String id=sc.next();
		bankDto=bankDao.select(id);
		if(bankDto!=null) {
			System.out.println(bankDto.getName()+"님 현재 잔액 : "+bankDto.getBalance());
		}else {
			System.out.println("계좌번호가 존재하지 않습니다.");
		}
		
		sc.close();
	}
}
