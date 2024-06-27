package com.ict.edu;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.service.Test01Service;

@Controller
public class Test01Controller {
	@Autowired
private Test01Service test01Service;

	@GetMapping("hello.do")
	public ModelAndView HelloCommand(HttpServletResponse res, HttpServletRequest req) {
		 ModelAndView mv = new ModelAndView("test01/result");
		
		 String msg = test01Service.getGreeting();
		 mv.addObject("msg", msg);
		 return mv;
	}
}
 