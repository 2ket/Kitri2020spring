package com.java.anno02;

public class Target {

	@MyAnno		// 홍길동, 27
	public void subA() {
		System.out.println("Target SubA Function-------------");
	}
	
	@MyAnno(name="이영자")	// 이영자, 27
	public void subB() {
		System.out.println("Target SubB Function-------------");
	}
	
	@MyAnno(age=33)
	public void subC() {
		System.out.println("Target SubC Function-------------");
	}
	@MyAnno(name="조인성", age=40)
	public void subD() {
		System.out.println("Target SubD Function-------------");
	}
	
	
}
