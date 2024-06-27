package com.ict.service2;

import org.springframework.stereotype.Service;

@Service
public class Test02Service {
	
 public int getCalc(int s1, int s2, String op) {
	 int result=0;
//	 switch (op) {
//	case "+": result = s1 + s2; break;
//	case "-": result = s1 - s2; break;
//	case "*": result = s1 * s2; break;
//	case "/": result = s1 / s2; break;
if (op=="+") {result = s1 + s2;}
if (op=="-") {result = s1 - s2;}
if (op=="*") {result = s1 * s2;}
if (op=="/") {result = s1 / s2;}

	 return result;
	 
 }
}
