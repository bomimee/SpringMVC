package com.ict.guestbook2.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class G2DAO {
	
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;
	
	public List<G2VO> getGuestbook2List() {
		try {
			return sqlSessionTemplate.selectList("guestbook2.list");
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;}
	
	public int getGuestbook2Insert(G2VO gvo) {
		try {
			return sqlSessionTemplate.insert("guestbook2.insert", gvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;}
	public G2VO getGuestbook2Detail(String idx) {
		try {
			return sqlSessionTemplate.selectOne("guestbook2.detail", idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;}
	public int getGuestbook2Delete(String idx) {
		try {
			return sqlSessionTemplate.delete("guestbook2.delete",idx);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;}
	public int getGuestbook2Update(G2VO gvo) {
		try {
			return sqlSessionTemplate.update("guestbook2.update", gvo);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;}
}
