package com.java.di03;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx=new GenericXmlApplicationContext("appCTX.xml");
		Sub sub1=(Sub)ctx.getBean("sub1");
		System.out.println(sub1);
		
		Sub sub2=(Sub)ctx.getBean("sub2");
		System.out.println(sub2);
		
		ctx.close();
	}

}
