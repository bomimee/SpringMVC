package com.ict.ex03;

public class TestMain_nonDI {
public static void main(String[] args) {
	Myprocess process = new Myprocess();
	process.prn();
	
	System.out.println("==========");
	
	Myprocess process2 = new Myprocess("dully", 1);
	
	process2.prn();
	
	System.out.println("==========");
	
	Myprocess process3 = new Myprocess();
	
	process3.setName("희동이");
	process3.setAge(5);
	process3.prn();
}
}
