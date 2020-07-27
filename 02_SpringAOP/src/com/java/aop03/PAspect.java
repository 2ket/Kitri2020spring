package com.java.aop03;

import org.aspectj.lang.JoinPoint;

public class PAspect {	//공통클래스 aspect
	//핵심함수 전
	public void open(JoinPoint joinPoint) { //공통 함수 advice, 핵심코드 joinPoint
		System.out.println("강의장에 들어온다.");
	}
	//핵심함수 후
	public void close(JoinPoint joinPoint) {
		System.out.println("수업끝!!강의장을 나간다.");
	}
	
	//에러
	public void error(JoinPoint joinPoint) {
		System.out.println("떠들어 강의에서 쫓겨남.. 식권없음!!");
	}
	//정상
	public void eat(JoinPoint joinPoint) {
		System.out.println("점심식사를 맛나게 함!!");
	}
}
