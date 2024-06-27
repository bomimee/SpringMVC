package com.ict.edu4.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.ict.guestbook.service.Guest_VO;

public class DAO {
	private SqlSessionTemplate sqlSessionTemplate1;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate1 = sqlSessionTemplate;
	}

	// list
	public List<VO> getList(){
		try {
			List<VO> list = null;
			list = sqlSessionTemplate1.selectList("members.list");
			
			return list;
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return null;
	}

}
