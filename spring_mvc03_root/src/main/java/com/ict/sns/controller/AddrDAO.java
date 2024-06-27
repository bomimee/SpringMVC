package com.ict.sns.controller;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ict.sns.AddrVO;

@Repository
public class AddrDAO {

	@Autowired
	
	private SqlSessionTemplate sqlSessionTemplate;
	
	public int addr_Insert(AddrVO avo) {
		try {
			return sqlSessionTemplate.insert("member.addr_insert", avo);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return 0;
		
	}
}
