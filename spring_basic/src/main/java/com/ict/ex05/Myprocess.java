package com.ict.ex05;

import org.springframework.stereotype.Component;

@Component ("mp")
public class Myprocess {
private String name="홍길동";
private int age = 13;
public Myprocess() {
	// TODO Auto-generated constructor stub
}
public Myprocess(String name, int age) {
	super();
	this.name = name;
	this.age = age;
}

public void prn() {
	System.out.println("name : "+name);
	System.out.println("age : "+age);
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public int getAge() {
	return age;
}
public void setAge(int age) {
	this.age = age;
}

}
