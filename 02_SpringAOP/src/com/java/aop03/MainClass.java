package com.java.aop03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx=new GenericXmlApplicationContext("classpath:com/java/aop03/appCTX.xml");
		
		Person student=(Person) ctx.getBean("student");
		student.work();
		System.out.println();
		
		Person teacher=(Person) ctx.getBean("teacher");
		teacher.work();
		System.out.println();
		
		
		ctx.close();
	}

}
