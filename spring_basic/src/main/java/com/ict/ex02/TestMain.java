package com.ict.ex02;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TestMain {
public static void main(String[] args) {
// DI => Spring Container 에서 객체(bean) 생성하고 관리한다.
	// Spring Container 에게 필요한 정보를 제공해야한다. => configuration matadata (config) 설정정보 : 기본적으로 xml 파일 형태, 추가적으로 annotation 을 이용한다.
	// Spring boot에서는 무조건 어노테이션을 이용한다.
	
	// Spring Container => BeanFactory => ApplicationContext, WebApplicationContext  형태로 되어있다.
	//ApplicationContext context = new GenericXmlApplicationContext("configuration location");
	
	// config file을 읽음으로써 oracle 객체생성됨
	ApplicationContext context = new GenericXmlApplicationContext("com/ict/ex02/config.xml");
	
	Service service = (Service) context.getBean("service");
	service.biz();
}
}
 