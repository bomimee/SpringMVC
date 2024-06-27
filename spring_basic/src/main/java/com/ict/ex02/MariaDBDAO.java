package com.ict.ex02;

public class MariaDBDAO implements DAO{

	public MariaDBDAO() {
		System.out.println("마리아 디비 생성자");
	}
	@Override
	public void prn() {
		System.out.println("MariaDBDAO prn()");
		
	}

}
