package com.ict.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

//컨트롤러를 어노테이션하지 않으면 반드시 Controller 상속 해야한다
public class Start1Controller implements Controller{
	
	// 실제 실행하는 메서드(컨트롤 스페이스바 앤터_)
	//어노테이션을 하지않으면 반환형은 모델엔뷰 다 
	// 어노테이션을 하면 반환형으로 스트링, 모델앤뷰,  모두 가능
@Override
public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
    
   // ModelAndView mv = new ModelAndView("뷰네임(되돌아갈 jsp이름)");  === mv.setViewName("뷰네임(되돌아갈 jsp이름)");  =>.jsp 를 붙이지 않는다
    // 일처리 저장하는 곳이 모델이고, 되돌아갈 jsp 지정하는 것이 view 
    
	ModelAndView mv = new ModelAndView();
	mv.setViewName("result1");
	
	// 일처리 = 비지니스 로직 => 서비스 => 서비스임플=> 매퍼(DAO) => DB처리
	mv.addObject("name", "홍길동");
	mv.addObject("age", 17);
	
	request.setAttribute("addr", "서울");
	
	request.getSession().setAttribute("phone", "010-8888-9999");
	return mv;
}
}
