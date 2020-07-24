package com.java.di04;

public class BB {	//setter
	private String apple;
	private String banana;
	
	public void setApple(String apple) {
		this.apple = apple;
	}
	public void setBanana(String banana) {
		this.banana = banana;
	}
	@Override
	public String toString() {
		return "BB [apple=" + apple + ", banana=" + banana + "]";
		
	}
	
}
