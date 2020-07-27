package com.java.bank.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
	public static Logger logger=Logger.getLogger(LogAspect.class.getName());
	public static final String logMsg="LogMsg>>>>>";
	
	public Object advice(ProceedingJoinPoint joinPoint) {	//dao의 결과값이 리턴된게 joinPoint에서도 데이터를 가지고 가야하므로 Object로 받는다.
		Object obj=null;
		try {
			logger.info(logMsg+joinPoint.getTarget().getClass().getName()+"\t\t"+joinPoint.getSignature().getName());//signature:함수(메소드)
			obj=joinPoint.proceed();
		}catch(Throwable e){
			e.printStackTrace();
		}
		return obj;
	}
}
