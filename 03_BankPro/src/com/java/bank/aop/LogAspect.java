package com.java.bank.aop;

import java.util.logging.Logger;

import org.aspectj.lang.ProceedingJoinPoint;

public class LogAspect {
	public static Logger logger=Logger.getLogger(LogAspect.class.getName());
	public static final String logMsg="LogMsg>>>>>";
	
	public Object advice(ProceedingJoinPoint joinPoint) {	//dao�� ������� ���ϵȰ� joinPoint������ �����͸� ������ �����ϹǷ� Object�� �޴´�.
		Object obj=null;
		try {
			logger.info(logMsg+joinPoint.getTarget().getClass().getName()+"\t\t"+joinPoint.getSignature().getName());//signature:�Լ�(�޼ҵ�)
			obj=joinPoint.proceed();
		}catch(Throwable e){
			e.printStackTrace();
		}
		return obj;
	}
}
