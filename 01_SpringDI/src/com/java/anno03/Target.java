package com.java.anno03;


@MyAnno				//ElementType.TYPE
public class Target {
	
	private @MyAnno String str;		//ElementType.FIELD
	
	@MyAnno
	public Target() {}				//ElementType.CONSTRUCTOR
	
	public void sub(@MyAnno String value) {	//ElementType.PARAMETER
		@MyAnno() int x=10;			//ElementType.LOCAL_VARIABLE
		
	}
	
	
}
