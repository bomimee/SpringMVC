package com.ict.guestbook.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class GuestbookDAO {

	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	public List<GuestbookVO> getGuestbookList() {
		try {
			return sqlSessionTemplate.selectList("guestbook.list");
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e);
		}
		return null;
		
	}
	
	public int getGuestbookInsert(GuestbookVO gvo) {
		try {
			return sqlSessionTemplate.insert("guestbook.insert", gvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;}
	
	public GuestbookVO getGuestbookDetail(String idx) {
		try {
			return sqlSessionTemplate.selectOne("guestbook.detail", idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;}
	
	public int getGuestbookDelete(String idx) {
		try {
			return sqlSessionTemplate.delete("guestbook.delete", idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;}
	
	public int getGuestbookUpdate(GuestbookVO gvo) {
		try {
			return sqlSessionTemplate.update("guestbook.update", gvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;}
}
