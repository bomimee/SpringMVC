package com.ict.ex06;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class Service {
	// 1. OracleDAO oracleDAO = new OracleDAO();
    //2.MariaDBDAO mariaDBDAO = new MariaDBDAO();

	// 인터페이스를 자료형으로 사용
	// 변수 이름과 참조하는 클래스의 아이디가 같을 때 사용  >>> @Autowired
	@Autowired
	//같은 이름이 없을 때는 참조하고자하는 클래스의 아이디를 입력한다. >>> @Qualifier("mariaDBDAO")
	@Qualifier("mariaDBDAO")
	private DAO dao;
	

	public Service() {}
	public Service(DAO dao) {
		this.dao = dao;
	}
	
	public DAO getDao() {
		return dao;
	}
	public void setDao(DAO dao) {
		this.dao = dao;
	}
	
	// 사용하고 싶은 메서드
	public void biz() {
		// OracleDAO, MariaDBDAO => prn 메서드 실행 
		
		// 1. oracleDAO.prn();
		//2.mariaDBDAO.prn();
		dao.prn();
	}
}
