package com.java.aop02;

import org.aspectj.lang.JoinPoint;

public class PASpect {	// 공통 클래스 		aspect
	public void awake(JoinPoint joinPoint) {	//공통 함수		advice
		// TODO Auto-generated method stub		//JoinPoint 핵심코드를 가져온다.
		System.out.println("일어난다.");
	}
	public void sleep(JoinPoint joinPoint) {	//공통 함수
		// TODO Auto-generated method stub
		System.out.println("잠을잔다.");
	}
}
