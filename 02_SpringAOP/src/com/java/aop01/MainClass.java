package com.java.aop01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		GenericXmlApplicationContext ctx=new GenericXmlApplicationContext("classpath:com/java/aop01/appCTX.xml");
		
		//�������̽� ����� �ݵ�� �������̽��� �޾��ش�.
		
		//�ƺ�
		Person papa=(Person) ctx.getBean("papa");
		papa.awake();
		papa.work();
		papa.sleep();
		System.out.println("------------------");
		
		//����
		Person mama=(Person) ctx.getBean("mama");
		mama.awake();
		mama.work();
		mama.sleep();
		System.out.println("------------------");
		
		//�Ƶ�
		Person baby=(Person) ctx.getBean("baby");
		baby.awake();
		baby.work();
		baby.sleep();
		System.out.println("------------------");
	}

}
