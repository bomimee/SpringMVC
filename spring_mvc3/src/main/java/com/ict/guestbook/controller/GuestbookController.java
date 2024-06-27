package com.ict.guestbook.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook.dao.GuestbookVO;
import com.ict.guestbook.service.GuestbookService;

@Controller
public class GuestbookController {

	@Autowired
	private GuestbookService guestbookService;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@GetMapping("gb_list.do")
	public ModelAndView getGuestbookList() {
		ModelAndView mv = new ModelAndView("guestbook/list");
		List<GuestbookVO> list = guestbookService.getGuestbookList();
		if (list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}

	@GetMapping("gb_write.do")
	public ModelAndView getGuestbookInsert() {
		return new ModelAndView("guestbook/write");
	}

	@GetMapping("gb_write_ok.do")
	public ModelAndView getGuestbookInsertOK(GuestbookVO gvo) {
		ModelAndView mv = new ModelAndView("redirect:gb_list.do");
		// 비번 암호화
		String pwd = passwordEncoder.encode(gvo.getPwd());
		gvo.setPwd(pwd);
		int result = guestbookService.getGuestbookInsert(gvo);
		if (result > 0) {

			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
	@GetMapping("gb_detail.do")
	public ModelAndView getGuestbookDetail(String idx) {
		ModelAndView mv = new ModelAndView("guestbook/onelist");
		GuestbookVO gvo = guestbookService.getGuestbookDetail(idx);
		if(gvo != null) {
			mv.addObject("gvo", gvo);
			return mv;
		}
		return new ModelAndView("guestbook/error");
	}
 @PostMapping("gb_delete.do")
 public ModelAndView getGuestbookDelete(@ModelAttribute("idx") String idx) {
	return new ModelAndView("guestbook/delete");
 }
 
 // 파라미터 두개를 받아야 하니까 vo 로 받는다 ( 비번, idx)
 @PostMapping("gb_delete_ok.do")
 public ModelAndView getGuestbookDeleteOK(GuestbookVO gvo) {
	 ModelAndView mv = new ModelAndView("");
	 // guest 의 비번
	 String cpwd =gvo.getPwd();
	 GuestbookVO gvo2 = guestbookService.getGuestbookDetail(gvo.getIdx());
	 // db 에 있는 긴 암호
	 String dpwd = gvo2.getPwd();
	 
	 if(!passwordEncoder.matches(cpwd, dpwd)) {
		 mv.setViewName("guestbook/delete");
		 mv.addObject("pwdchk", "fail");
		 mv.addObject("idx", gvo.getIdx());
		 return mv;
	 } else {
		 int res = guestbookService.getGuestbookDelete(gvo.getIdx());
		 if(res>0) {
			 mv.setViewName("redirect:gb_list.do");
			 return mv;	 
		 }
		 return new ModelAndView("guestbook/error");
	 }
 }
 @PostMapping("gb_update.do")
 public ModelAndView getGuestbookUpdate(String idx) {
	ModelAndView mv = new ModelAndView("guestbook/update");
	GuestbookVO gvo = guestbookService.getGuestbookDetail(idx);
	if(gvo != null) {
		mv.addObject("gvo",gvo);
		return mv;
	}
	 return new ModelAndView("guestbook/error");
 }

 
 @PostMapping("gb_update_ok.do")
 public ModelAndView getGuestbookUpdateOK(GuestbookVO gvo ) {
	 ModelAndView mv = new ModelAndView();
	 String cpwd =gvo.getPwd();
	 GuestbookVO gvo2 = guestbookService.getGuestbookDetail(gvo.getIdx());
	 // db 에 있는 긴 암호
	 String dpwd = gvo2.getPwd();
	 
	 if(!passwordEncoder.matches(cpwd, dpwd)) {
		 mv.setViewName("guestbook/update");
		 mv.addObject("pwdchk", "fail");
		 mv.addObject("idx", gvo.getIdx());
		 //수정전 내용을 되돌려주려면 : gvo2
		 //수정후 내용을 되돌려 주려면 : gvo
		 mv.addObject("gvo", gvo);
		 return mv;
	 } else {
		 int res = guestbookService.getGuestbookUpdate(gvo);
		 if(res>0) {
			 mv.setViewName("redirect:gb_detail.do?idx="+gvo.getIdx());
			 return mv;	 
		 }
		 return new ModelAndView("guestbook/error");
	 }
	 
 }
}
