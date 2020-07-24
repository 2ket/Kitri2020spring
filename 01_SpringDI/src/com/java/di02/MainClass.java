package com.java.di02;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.java.di01.Su;

public class MainClass {

	public static void main(String[] args) {
		GenericXmlApplicationContext ctx=new GenericXmlApplicationContext("appCTX.xml");
		
		Example ex=(Example) ctx.getBean("example");
		ex.disp();
		
		Testing t=(Testing) ctx.getBean("testing");
		t.print();
		
		
		Su su=(Su) ctx.getBean("su");
		su.disp();
		
		ctx.close();
	}

}
