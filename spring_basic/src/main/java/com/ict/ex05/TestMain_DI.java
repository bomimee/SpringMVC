package com.ict.ex05;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain_DI {
public static void main(String[] args) {
	ApplicationContext context = new GenericXmlApplicationContext("com/ict/ex05/config.xml");
//Myprocess myProcess = (Myprocess) context.getBean("myprocess");  //id 가 없는 경우 class 이름의 앞글자 소문자 
Myprocess myProcess = (Myprocess) context.getBean("mp");  //id 가 있는경우
myProcess.prn();
}
}
