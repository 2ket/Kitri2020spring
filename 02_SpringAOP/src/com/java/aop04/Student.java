package com.java.aop04;

import java.util.Scanner;

//�ٽ��ڵ�
public class Student implements Person {

	@Override
	public void work() {	//�ٽ� �Լ�
		// TODO Auto-generated method stub
		System.out.println("�л��� ���θ� �մϴ�.");
		
		Scanner sc=new Scanner(System.in);
		System.out.println("�� �Է�: ");
		int su=sc.nextInt();
		
		if(su==5) System.out.println(su/0);
		
		sc.close();
	}

}
