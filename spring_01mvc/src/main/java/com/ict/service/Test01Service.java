package com.ict.service;

import java.util.Calendar;

import org.springframework.stereotype.Service;

@Service
public class Test01Service {
	 public Test01Service() {
		 System.out.println("Test01Service");
	 }
	 
	 // 실행하고자하는 method
	 public String getGreeting() {
		 String  str="";
		 int hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
		if(hour >=7 && hour<=8) {
			str="goodmorning";
		}else if(hour >=12 && hour<= 14) {
			str = "good afternoon";
			
		}else if(hour >=19 && hour <=20) {
			str="good bye";
		}else if(hour >=23 && hour <=24) {
			str="good night";
		}else {
			str="hi";
		}
		return str;
	 }
}
