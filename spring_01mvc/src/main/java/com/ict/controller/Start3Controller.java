package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

//상속안받고 컨트롤러 어노테이션
@Controller
public class Start3Controller {
	
	// 요청이 오면 실행해야 한다.
	// 요청표시를 해야한다. ..  어떻게? 
	//@RequestMapping(value="start3.do", method=RequestMethod.GET)
	//@RequestMapping(value="start3.do", method=RequestMethod.POST)
	//@RequestMapping(start3.do"")  => get / post 인지 모를때 사용
	//*** 요즘방식
	//@GetMapping("start3.do")
	//@PostMapping("start3.do")
	
	@GetMapping("/start3.do")
 public ModelAndView exec(HttpServletRequest req, HttpServletResponse res) {
	 ModelAndView mv = new ModelAndView("admin/result3");
	 mv.addObject("city", "Seoul");
	 return mv; 
 }
}
