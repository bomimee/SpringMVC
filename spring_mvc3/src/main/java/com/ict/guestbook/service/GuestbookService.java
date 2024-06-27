package com.ict.guestbook.service;

import java.util.List;

import com.ict.guestbook.dao.GuestbookVO;

public interface GuestbookService {
 //list
	public List<GuestbookVO> getGuestbookList();	
	//insert
	public int getGuestbookInsert(GuestbookVO gvo);
	//detail
	public GuestbookVO getGuestbookDetail(String idx);
	
	//delete
	public int getGuestbookDelete(String idx);
	//update
	public int getGuestbookUpdate(GuestbookVO gvo);
}
