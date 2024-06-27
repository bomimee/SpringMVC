package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class Start4Controller {
 //
	@GetMapping("start4.do")
	public String exec(HttpServletRequest req, HttpServletResponse res) {
		
//		ModelAndView mv = new ModelAndView();
//		mv.addObject("computer", "14, i7");
//		
		req.setAttribute("computer", "14, i7");
		return "admin/result4";
	}
	
}
