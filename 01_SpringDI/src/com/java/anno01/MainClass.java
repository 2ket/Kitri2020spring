package com.java.anno01;

import java.lang.reflect.Method;

public class MainClass {

	public static void main(String[] args) throws Exception {
		/* 어노테이션은 메타데이터라고 볼 수 있다. 애플리케이션이 처리해야될 데이터가 아니라, 
		 컴파일 과정과 실행과정에서 코드를 어떻게 컴파일하고 처리할지를 알려준다.
		 1. 용도
		 	1) 컴파일러에게 코드 문법 에러를 체크하도록 정보를 제공 @Override
		 	2) 소프트웨어 개발시 빌드나 배치코드(batch code)를 자동으로 생성할 수 있도록 정보를 제공(bean)
		 	3) 실행시 특정 기능을 실행하도록 정보를 제공
		 	
		 2. 종류
		 	1) 시스템 정의 어노테이션 : @Override @Deprecated @SuppressWarnings
		 	2) 커스텀(개발자) 어노테이션
		 		2-1) 타입과정의 - 타입, 이름, 디폴트값
		 		2-2) 적용대상(@Target(ElementType.*)) - *METHOD, FILED, TYPE(CLASS), 생성자, PARAMETER 등이 있다.
		 		2-3) 유지정책(@Retention(RetentionPolicy.*)) - *SOURCE, CLASS, RUNTIME
		*/
		
		Target t=new Target();
		t.sub();
		
		String className="com.java.anno01.Target";
		//동적객체 생성으로 확인할 수 있다.
		Class<?> cls=Class.forName(className);		//클래스 찾기
		Target a=(Target)cls.getDeclaredConstructor().newInstance();	//동적객체 생성
		a.sub();
		
		Method[] methods=cls.getMethods();		// 클래스의 메소드
		//함수반환
		for(int i=0; i<methods.length; i++) {
			if(methods[i].isAnnotationPresent(MyAnno.class)) {	//메소드가 해당 어노테이션 클래스의 어노테이션을 가지고있는지 boolean
				MyAnno myAnno=methods[i].getAnnotation(MyAnno.class);
				System.out.println(myAnno.value());
			}
		}
	}

}
