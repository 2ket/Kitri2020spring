package com.java.aop04;

import java.util.Scanner;

//핵심코드
public class Student implements Person {

	@Override
	public void work() {	//핵심 함수
		// TODO Auto-generated method stub
		System.out.println("학생은 공부를 합니다.");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("수 입력: ");
		int su=sc.nextInt();
		
		if(su==5) System.out.println(su/0);
		
		sc.close();
	}

}
