package com.ict.guestbook.service;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;


public class Guest_DAO {
	private SqlSessionTemplate sqlSessionTemplate2;

	public void setSqlSessionTemplate(SqlSessionTemplate sqlSessionTemplate) {
		this.sqlSessionTemplate2 = sqlSessionTemplate;
	}


	public List<Guest_VO> getGuestbook(){
		try {
			List<Guest_VO> book = null;
			book = sqlSessionTemplate2.selectList("guestbook.list");
			
			return book;
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return null;
	}
	//insert
	public int guestbookInsert(Guest_VO gvo) {
		try {
			int result = 0;
			result = sqlSessionTemplate2.insert("guestbook.insert",gvo);
			return result;
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}
	
	// datail
	public Guest_VO guestbookDetail(String idx) {
		try {
			Guest_VO gvo = sqlSessionTemplate2.selectOne("guestbook.detail",idx);
			
			return gvo;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}
	public int guestbookDelete(String idx) {
		try {
			int res = 0;
			res = sqlSessionTemplate2.delete("guestbook.delete",idx);
			return res;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1;
	}
	public int guestbookUpdate(Guest_VO gvo) {
		try {
			int res = 0;
			res = sqlSessionTemplate2.update("guestbook.update", gvo);
			
			return res;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return -1 ;
	}
}
