package com.ict.guestbook2.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
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
public class Guestbook2Controller {

	@Autowired
	private Guestbook2Service guestbook2Service;

	public void setGuestbook2Service(Guestbook2Service guestbook2Service) {
		this.guestbook2Service = guestbook2Service;
	}

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	// 1. list
	@GetMapping("gb2_list.do")
	public ModelAndView getGuestbook2List() {
		ModelAndView mv = new ModelAndView("guestbook2/list");
		List<VO> list = guestbook2Service.getGuestBook2List();
		if (list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("guestbook2/error");
	}

	// 2 write
	@GetMapping("gb2_write.do")
	public ModelAndView getGuestbook2Write() {
		return new ModelAndView("guestbook2/write");
	}

	// 3 write-ok
	@PostMapping("gb2_write_ok.do")
	public ModelAndView getGuestbook2WriteOk(HttpServletRequest req, VO vo) {
		try {
			ModelAndView mv = new ModelAndView("redirect:gb2_list.do");
			String path = req.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartFile file = vo.getFile();
			if (file.isEmpty()) {
				vo.setFilename("");
			} else {
				UUID uuid = UUID.randomUUID();
				String filename = uuid.toString() + "_" + file.getOriginalFilename();
				vo.setFilename(filename);

				// img 저장
				byte[] in = vo.getFile().getBytes();
				File out = new File(path, filename);
				FileCopyUtils.copy(in, out);
			}
			String pwd = passwordEncoder.encode(vo.getPwd());
			vo.setPwd(pwd);

			int res = guestbook2Service.getGuestBook2Insert(vo);
			if (res > 0) {
				return mv;
			}
			return new ModelAndView("guestbook2/error");
		} catch (Exception e) {
			System.out.println(e);

		}

		return new ModelAndView("guestbook2/error");
	}

	// 4. detail
	@GetMapping("gb2_detail.do")
	public ModelAndView getGuestbook2Detail(String idx) {
		ModelAndView mv = new ModelAndView("guestbook2/onelist");

		VO vo = guestbook2Service.getGuestBook2Detail(idx);
		if (vo != null) {
			mv.addObject("vo", vo);
			return mv;
		} else {
			return new ModelAndView("guestbook2/error");
		}

	}
	// 5. 파일 다운로드
	@GetMapping("guestbook2_down.do")
	public void getGuestbook2Down(HttpServletRequest req, HttpServletResponse res) {
		try {
			String filename = req.getParameter("filename");
			String path = req.getSession().getServletContext().getRealPath("/resources/upload/"+filename);
			String r_path = URLEncoder.encode(path, "utf-8");
			
			res.setContentType("application/x-msdownload");
			res.setHeader("Content-Disposition", "attachment; filename="+r_path);
			
			File file = new File(new String(path.getBytes(), "utf-8"));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = res.getOutputStream();
			FileCopyUtils.copy(in, out);
					
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	// 6. update
	
	@PostMapping("gb2_update.do")
	public ModelAndView getGuestbook2Update(String idx) {
		ModelAndView mv = new ModelAndView("guestbook2/update");
		VO vo = guestbook2Service.getGuestBook2Detail(idx);
		if(vo!=null) {
			mv.addObject("vo", vo);
			return mv;
		}
		return new ModelAndView("guestbook2/error");		
	}
	
	@PostMapping("gb2_update_ok.do")
	public ModelAndView getGuestbook2UpdateOk(VO vo, HttpServletRequest req) {
		ModelAndView mv = new ModelAndView("");
		
		String cpwd =vo.getPwd();
		VO vo2 = guestbook2Service.getGuestBook2Detail(vo.getIdx());
		String dpwd = vo2.getPwd();
		if(!passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("guestbook2/update");
			mv.addObject("pwdchk", "fail");
			mv.addObject("vo", vo2);
			return mv;
		}else {
			try {
				String path = req.getSession().getServletContext().getRealPath("/resources/upload");
				MultipartFile file = vo.getFile();
				String old_f_name = vo.getOld_f_name();
				if(file.isEmpty()) {
					vo.setFilename(old_f_name);
				}else {
					UUID uuid = UUID.randomUUID();
					String filename = uuid.toString()+"_"+file.getOriginalFilename();
					vo.setFilename(filename);
					
					byte [] in = file.getBytes();
					File out = new File(path, filename);
					FileCopyUtils.copy(in, out);
				}
				int res = guestbook2Service.getGuestbook2Update(vo);
				if( res> 0) {
					mv.setViewName("redirect:gb2_detail.do?idx="+vo.getIdx());
					return mv;
				}
				return new ModelAndView("guestbook2/error");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return new ModelAndView("guestbook2/error");
		
	}
	//7.게시판 삭제
	
	@PostMapping("gb2_delete.do")
	public ModelAndView getGuestbook2Delete(@ModelAttribute("vo2")VO vo) {
		
		return new ModelAndView("guestbook2/delete");
		
	}
	@PostMapping("gb2_delete_ok.do")
	public ModelAndView getGuestbook2DeleteOk(VO vo) {
		ModelAndView mv = new ModelAndView();
		String cpwd = vo.getPwd();
		VO vo2 = guestbook2Service.getGuestBook2Detail(vo.getIdx());
		String dpwd = vo2.getPwd();
		if(!passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("guestbook2/delete");
			mv.addObject("pwdchk", "fail");
			mv.addObject("vo2", vo);
			return mv;
		}else {
			int res = guestbook2Service.getGuestbook2Delete(vo.getIdx());
			if(res>0) {
				mv.setViewName("redirect:gb2_list.do");
				return mv;
			}
		}
		return  new ModelAndView("error");
		
	}
	

}
