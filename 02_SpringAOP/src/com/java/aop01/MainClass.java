package com.java.aop01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx=new GenericXmlApplicationContext("classpath:com/java/aop01/appCTX.xml");
		
		//인터페이스 기반은 반드시 인터페이스로 받아준다.
		
		//아빠
		Person papa=(Person) ctx.getBean("papa");
		papa.awake();
		papa.work();
		papa.sleep();
		System.out.println("------------------");
		
		//엄마
		Person mama=(Person) ctx.getBean("mama");
		mama.awake();
		mama.work();
		mama.sleep();
		System.out.println("------------------");
		
		//아들
		Person baby=(Person) ctx.getBean("baby");
		baby.awake();
		baby.work();
		baby.sleep();
		System.out.println("------------------");
	}

}
