package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

//통합 컨트롤러 만들기
@Controller
public class MyController {
	@GetMapping("start6.do")
 public ModelAndView Start06Command(HttpServletRequest req, HttpServletResponse res) {
	 ModelAndView mv = new ModelAndView("admin/result6");
	 mv.addObject("name", "hong");
	 
	 return mv;
 }
	@GetMapping("start5.do")
 public ModelAndView Start05Command(HttpServletRequest req, HttpServletResponse res) {
 ModelAndView mv = new ModelAndView("admin/result5");
 mv.addObject("name", "go");
	 return mv;
 }
}
