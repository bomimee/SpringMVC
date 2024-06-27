package com.ict.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

public class Start2Controller implements Controller{

	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView mv = new ModelAndView("admin/result2");
		
		String[] dogName = {"aa", "bb", "cc", "dd"};
		mv.addObject("dogName", dogName);
		
		ArrayList<String> list = new ArrayList<String>();
		list.add("haery");
		list.add("emma");
		list.add("ron");
		list.add("boldmort");
		return mv;
	}

}
