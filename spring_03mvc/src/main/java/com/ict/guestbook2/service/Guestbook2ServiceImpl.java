package com.ict.guestbook2.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.guestbook2.dao.DAO;
import com.ict.guestbook2.dao.VO;


@Service
public class Guestbook2ServiceImpl implements Guestbook2Service{

	private static final Logger logger = LoggerFactory.getLogger(Guestbook2ServiceImpl.class);
	@Autowired
	private DAO dao;
	

	@Override
	public List<VO> getGuestBook2List() {
	
		return dao.getGuestBook2List();
	
	}

	
	
	@Override
	public VO getGuestBook2Detail(String idx) {
		
		return dao.getGuestBook2Detail(idx);
	}


	@Override
	public int getGuestbook2Delete(String idx) {
		
		return dao.getGuestbook2Delete(idx);
	}

	@Override
	public int getGuestbook2Update(VO vo) {
		
		return dao.getGuestbook2Update(vo);
	}



	@Override
	public int getGuestBook2Insert(VO vo) {
		// TODO Auto-generated method stub
		return 0;
	}


}
