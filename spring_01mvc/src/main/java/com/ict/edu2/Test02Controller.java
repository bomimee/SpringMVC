package com.ict.edu2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ict.service2.Test02Service;
import com.ict.vo.VO;

@Controller
public class Test02Controller {
@Autowired
private Test02Service test02Service;

//	@PostMapping("calc.do")
//	public ModelAndView calc(HttpServletResponse res, HttpServletRequest req) {
//		ModelAndView mv = new ModelAndView("test02/result");
//		
//		String s1= req.getParameter("s1");
//		String s2= req.getParameter("s2");
//		String op= req.getParameter("op");
//		String cPage = req.getParameter("cPage");
//		
//		int su1 = Integer.parseInt(s1);
//		int su2 = Integer.parseInt(s2);
//		
//		
//		int result = test02Service.getCalc(su1, su2, op);
//		mv.addObject("result", result);
//		mv.addObject("s1",s1);
//		mv.addObject("s2",s2);
//		mv.addObject("op",op);
//		mv.addObject("cPage",cPage);

//String [] hobby = req.getParameterValues("hobby");                   // 배열받기
//mv.addObject("hobby",hobby)
//		return mv;
//	}



//@PostMapping("calc.do")
//// 파라미터 값을 VO 에 담는다.(vo 에 있는 변수는 파라미터 이름과 같아야한다. 
//public ModelAndView calc(VO vo) {
//	ModelAndView mv = new ModelAndView("test02/result");
//	int su1 = Integer.parseInt(vo.getS1());
//	int su2 = Integer.parseInt(vo.getS2());
//	
//	int result = test02Service.getCalc(su1, su2, vo.getOp());
//	
//	vo.setResult(String.valueOf(result));
//	mv.addObject("vo",vo);

//	return mv;
//
//}

@PostMapping("calc.do")
// 파라미터 값을 VO 에 담는다.(vo 에 있는 변수는 파라미터 이름과 같아야한다. 
public ModelAndView calc(@ModelAttribute("vo2")VO vo) {
	ModelAndView mv = new ModelAndView("test02/result");
	int su1 = Integer.parseInt(vo.getS1());
	int su2 = Integer.parseInt(vo.getS2());
	
	int result = test02Service.getCalc(su1, su2, vo.getOp());
	
	vo.setResult(String.valueOf(result));
	
	//@ModelAttribute("vo2");
	
	return mv;
	
}
}
