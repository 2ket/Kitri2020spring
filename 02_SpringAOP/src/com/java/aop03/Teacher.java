package com.java.aop03;

//핵심코드
public class Teacher implements Person {

	@Override
	public void work() {	//핵심 함수
		// TODO Auto-generated method stub
		System.out.println("선생님은 강의를 합니다.");
	}

}
