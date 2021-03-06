package com.java.di01;

import org.springframework.context.support.GenericXmlApplicationContext;

public class MainClass {
	public static void main(String[] args) {
		//Su su=new Su();
		
		/* 
		 * 클래스간 결합이 강해지고 의존성이 높아지면 코드를 수정해야 하는 범위가 넓어진다.
		 * 클래스간의 결합도를 약하게 하고 의존성을 낮게 작성해야 한다.
		 * 이런 처리방식의 설계패턴을 DI(Dependency Injection)라고 한다.
		 * DI는 의존객체를 외부로부터 전달받아서 구현한다.
		 *  */
		
		GenericXmlApplicationContext ctx=new GenericXmlApplicationContext("appCTX.xml");
		Su su=(Su)ctx.getBean("su");
		su.disp();
		
		//중복성 제거. 의존성높은 것들을 중복제거
		//DI 의존성을 낮춰주기 위해 생성됨. 객체 생성을 XML에서 함
		
		ctx.close();//항상 닫아줘야함.
	}
}
