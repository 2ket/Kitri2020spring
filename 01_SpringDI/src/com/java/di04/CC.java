package com.java.di04;

public class CC {
	private AA aa;
	private BB bb;
	
	public CC(AA aa, BB bb) {
		this.aa = aa;
		this.bb = bb;
	}
	
	public void disp() {
		System.out.println(aa.toString());
		System.out.println(bb.toString());
	}
}
