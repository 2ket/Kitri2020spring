package com.java.aop03;

import org.aspectj.lang.JoinPoint;

public class PAspect {	//����Ŭ���� aspect
	//�ٽ��Լ� ��
	public void open(JoinPoint joinPoint) { //���� �Լ� advice, �ٽ��ڵ� joinPoint
		System.out.println("�����忡 ���´�.");
	}
	//�ٽ��Լ� ��
	public void close(JoinPoint joinPoint) {
		System.out.println("������!!�������� ������.");
	}
	
	//����
	public void error(JoinPoint joinPoint) {
		System.out.println("����� ���ǿ��� �Ѱܳ�.. �ıǾ���!!");
	}
	//����
	public void eat(JoinPoint joinPoint) {
		System.out.println("���ɽĻ縦 ������ ��!!");
	}
}
