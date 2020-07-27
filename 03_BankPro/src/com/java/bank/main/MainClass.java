package com.java.bank.main;

import org.springframework.context.support.GenericXmlApplicationContext;

import com.java.bank.ui.BankUI;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx=new GenericXmlApplicationContext("appCTX.xml");
		
		BankUI bankUi=(BankUI) ctx.getBean("bankView");
		bankUi.execute();
		
		ctx.close();
	}

}
