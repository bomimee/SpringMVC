package com.ict.guestbook2.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.guestbook2.dao.DAO;
import com.ict.guestbook2.dao.VO;

@Service
public class Guestbook2ServiceImpl implements Guestbook2Service{

	@Autowired
	DAO dao;
	@Override
	public List<VO> getGuestBook2List() {
		// TODO Auto-generated method stub
		return dao.getGuestBook2List();
	}

	@Override
	public VO getGuestBook2Detail(String idx) {
		// TODO Auto-generated method stub
		return dao.getGuestBook2Detail(idx);
	}

	@Override
	public int getGuestBook2Insert(VO vo) {
		// TODO Auto-generated method stub
		return dao.getGuestBook2Insert(vo);
	}

	@Override
	public int getGuestbook2Delete(String idx) {
		// TODO Auto-generated method stub
		return dao.getGuestbook2Delete(idx);
	}

	@Override
	public int getGuestbook2Update(VO vo) {
		// TODO Auto-generated method stub
		return dao.getGuestbook2Update(vo);
	}

}
