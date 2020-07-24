package com.java.di04;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx=new GenericXmlApplicationContext("appCTX.xml");
		
		CC cc=(CC)ctx.getBean("cc");
		
		cc.disp();
		
		ctx.close();
		
	}

}
