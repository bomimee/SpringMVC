package com.ict.guestbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ict.guestbook.dao.GuestbookDAO;
import com.ict.guestbook.dao.GuestbookVO;

@Service
public class GuestbookServiceImpl implements GuestbookService{

	@Autowired
	GuestbookDAO guestbookDAO;
	
	@Override
	public List<GuestbookVO> getGuestbookList() {
		// TODO Auto-generated method stub
		return guestbookDAO.getGuestbookList();
	}

	@Override
	public int getGuestbookInsert(GuestbookVO gvo) {
		// TODO Auto-generated method stub
		return guestbookDAO.getGuestbookInsert(gvo);
	}

	@Override
	public GuestbookVO getGuestbookDetail(String idx) {
		// TODO Auto-generated method stub
		return guestbookDAO.getGuestbookDetail(idx);
	}

	@Override
	public int getGuestbookDelete(String idx) {
		// TODO Auto-generated method stub
		return guestbookDAO.getGuestbookDelete(idx);
	}

	@Override
	public int getGuestbookUpdate(GuestbookVO gvo) {
		// TODO Auto-generated method stub
		return guestbookDAO.getGuestbookUpdate(gvo);
	}

}
