package com.ict.edu4.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.edu4.service.DAO;
import com.ict.edu4.service.VO;
import com.ict.guestbook.service.Guest_DAO;
import com.ict.guestbook.service.Guest_VO;

import oracle.jdbc.proxy.annotation.Post;


@Controller
public class OracleController {
	
	// @Inject == autowired
	@Autowired
	private DAO dao;
	
	@GetMapping("oracle_list.do")
	public ModelAndView oracleList() {
		ModelAndView mv = new ModelAndView("test4/list");
		List<VO> list = dao.getList();
		mv.addObject("list", list);
		return mv;
	}
	
	@Autowired
	private Guest_DAO dao2;
	

	@GetMapping("guestbook_list.do")
	public ModelAndView guestbookList() {
		ModelAndView mv = new ModelAndView("test4/guestbook");
		List<Guest_VO> book = dao2.getGuestbook();
		mv.addObject("book",book);
		
		return mv;
	}

	@GetMapping("gbwrite.do")
	public ModelAndView guestbookWrite() {

		return new ModelAndView("test4/write");
	}
	
	@GetMapping("gbwrite_ok.do")
	public ModelAndView guestbookWriteOk(Guest_VO gvo) {
		ModelAndView mv = new ModelAndView("redirect:guestbook_list.do");
		
		int result = dao2.guestbookInsert(gvo);
		if(result>0) {
			return mv;			
		}
//		return new ModelAndView("test4/error");
		return null;		
	}
@GetMapping("gb_detail.do")
public ModelAndView guestbookDetail(Guest_VO gvo) {
	ModelAndView mv = new ModelAndView("test4/onelist");
	Guest_VO g_vo = dao2.guestbookDetail(gvo.getIdx());
	
	if(g_vo != null) {
		return mv;
	}
	return null;
}
@PostMapping("gb_delete.do")
public ModelAndView guestbookDelete(@ModelAttribute("gvo") Guest_VO gvo) {
	return new ModelAndView("test4/delete");
}
@PostMapping("gb_update.do")
public ModelAndView guestbookUpdate(Guest_VO gvo) {
	ModelAndView mv = new ModelAndView("test4/update");
	Guest_VO g_vo = dao2.guestbookDetail(gvo.getIdx());
	return mv;
}
@PostMapping ("gb_delete_ok.do")
public ModelAndView guestbookDeleteOk(String idx) {
	ModelAndView mv = new ModelAndView("rediect:guestbook_list.do");
	int result = dao2.guestbookDelete(idx);
	if(result>0) {
		return mv;			
	}
//	return new ModelAndView("test4/error");
	return null;		
}
@PostMapping("gb_update_ok.do")
public ModelAndView guestbookUpdateOk(Guest_VO gvo) {
	ModelAndView mv = new ModelAndView("redirect:gb_detail.do?idx="+gvo.getIdx());
	int result = dao2.guestbookUpdate(gvo);
	if(result>0) {
		return mv;			
	}
//	return new ModelAndView("test4/error");
	return null;		
}
}
