package com.java.aop02;

import org.aspectj.lang.JoinPoint;

public class PASpect {	// ���� Ŭ���� 		aspect
	public void awake(JoinPoint joinPoint) {	//���� �Լ�		advice
		// TODO Auto-generated method stub		//JoinPoint �ٽ��ڵ带 �����´�.
		System.out.println("�Ͼ��.");
	}
	public void sleep(JoinPoint joinPoint) {	//���� �Լ�
		// TODO Auto-generated method stub
		System.out.println("�����ܴ�.");
	}
}
