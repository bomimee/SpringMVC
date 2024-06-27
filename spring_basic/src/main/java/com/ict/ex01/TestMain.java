package com.ict.ex01;

public class TestMain {
public static void main(String[] args) {
	// 1. 첫번째방법 (일반적)
//	Service service = new Service();
//	service.biz();
	
	//2. 두번째 방법 (생성자 이용)
//	Service service = new Service(new OracleDAO());
//	service.biz();
	
	//3.세번째 방법 setter  이용
	Service service = new Service(new OracleDAO());
	service.setDao(new MariaDBDAO());
	service.biz();
}
}
