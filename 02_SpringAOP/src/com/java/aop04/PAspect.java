package com.java.aop04;

//import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

public class PAspect {	//����Ŭ���� aspect
	
	public void sub(ProceedingJoinPoint joinPoint) {
		try {
			//�ٽ��Լ� ��
			System.out.println("�����忡 ���´�.");
			
			//�ٽ��ڵ�
			joinPoint.proceed();
			
			//�����϶�
			System.out.println("���ɽĻ縦 ��������!!");
		}catch(Throwable e) {
			//����
			System.out.println("����� ���ǿ��� �Ѱܳ�.. �ıǾ���!!");
		}finally {
			//�ٽ��Լ� ��
			System.out.println("������!!�������� ������.");
		}
	}
	
//	//�ٽ��Լ� ��
//	public void open(JoinPoint joinPoint) { //���� �Լ� advice, �ٽ��ڵ� joinPoint
//		System.out.println("�����忡 ���´�.");
//	}
//	//�ٽ��Լ� ��
//	public void close(JoinPoint joinPoint) {
//		System.out.println("������!!�������� ������.");
//	}
//	
//	//����
//	public void error(JoinPoint joinPoint) {
//		System.out.println("����� ���ǿ��� �Ѱܳ�.. �ıǾ���!!");
//	}
//	//����
//	public void eat(JoinPoint joinPoint) {
//		System.out.println("���ɽĻ縦 ������ ��!!");
//	}
}
