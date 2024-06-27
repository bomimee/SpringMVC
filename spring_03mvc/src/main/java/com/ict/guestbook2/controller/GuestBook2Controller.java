package com.ict.guestbook2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.file.FileAlreadyExistsException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ict.guestbook2.dao.VO;
import com.ict.guestbook2.service.Guestbook2Service;




@Controller
public class GuestBook2Controller {
	@Autowired
	private Guestbook2Service guestbook2Service;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder; 

	@GetMapping("gb2_list.do")
	public ModelAndView Guestbook2List() {
		ModelAndView mv = new ModelAndView("list");
		List<VO> list = guestbook2Service.getGuestBook2List();
		if(list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("error");
	}

}
