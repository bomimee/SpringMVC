package com.ict.controller;

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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.ict.dao.VO;
import com.ict.service.Guestbook2Service;



@Controller
public class GuestBook2Controller {
	@Autowired
	private Guestbook2Service guestbook2Service;

	// 암호화는 스프링 시큐리티에서 지원 => pom.xml 에 추가해야한다.
	// root-context.xml 에 빈생성 또는 새로운 xml을 만들어서 빈생성 (spring 오른쪽마우스키에서 스프링빈커피그레이션 파일
	// 생성)
	// 파일을 새로 생성했다면 web.xml 에서 xml파일을 지정해줘야 부팅하면서 읽고 실행 가능
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	public void setGuestbook2Service(Guestbook2Service guestbook2Service) {
		this.guestbook2Service = guestbook2Service;
	}

	// 1. list 보기
	@GetMapping("gb2_list.do")
	public ModelAndView getGuestBook2List() {
		ModelAndView mv = new ModelAndView("list");
		List<VO> list = guestbook2Service.getGuestBook2List();
		if (list != null) {
			mv.addObject("list", list);
			return mv;
		}
		return new ModelAndView("error");
	}

	// 2. write 작성하는곳으로 가기
	@GetMapping("gb2_write.do")
	public ModelAndView getGuestBook2Write() {
		return new ModelAndView("write");
	}

	//  3. 게시판 작성하기 
	@PostMapping("gb2_write_ok.do")
	public ModelAndView getGuestBook2WriteOK(VO vo, HttpServletRequest req) {
		// 파일 처리때문에 try catch 해야한다
		try {
			ModelAndView mv = new ModelAndView("redirect:gb2_list.do");
			String path = req.getSession().getServletContext().getRealPath("/resources/upload");
			// 넘어온 파일의 정보 중 f_name 에 넣어줘야 데이터베이스에 저장가능
			MultipartFile file = vo.getFile(); // 꺼내기
			if (file.isEmpty()) {
				// vo.setFilename("");
				vo.setFilename("");
			} else {
				// 파라미터로 받은 file을 이용해서 데이터베이스에 저장할 f_name 을 채우기
				// 그러나 같은 이름의 파일이 있으면 파일업로드가 안된다. UUID 를 이용해서 파일이름 변경해야한다.
				UUID uuid = UUID.randomUUID();
				String filename = uuid.toString() + "_" + file.getOriginalFilename();
				vo.setFilename(filename);

				// img 저장
				byte[] in = vo.getFile().getBytes(); // db 저장 끝
				// 실제저장
				File out = new File(path, filename);
				FileCopyUtils.copy(in, out);
			}
			// 패스워드 암호화
			String pwd = passwordEncoder.encode(vo.getPwd());
			vo.setPwd(pwd);
			// db 저장

			int res = guestbook2Service.getGuestBook2Insert(vo);
			if (res > 0) {
				return mv;
			}
			return new ModelAndView("error");

		} catch (Exception e) {
			System.out.println(e);
		}

		return new ModelAndView("error");

	}
	
	//4. 상세 보기 
	
	@GetMapping("gb2_detail.do")
	public ModelAndView getGuestBook2Detail(String idx) {
		ModelAndView mv = new ModelAndView("onelist");
	
		VO vo = guestbook2Service.getGuestBook2Detail(idx);
		if(vo != null) {
			mv.addObject("vo",vo);
		return mv;
		} else {
			return new ModelAndView("error");
		}
	}
	
	// 5. 파일 다운로드 
	@GetMapping("guestbook2_down.do")
	public void getGuestBook2Down(HttpServletRequest req, HttpServletResponse res) {	
		try {
			String filename = req.getParameter("filename");
			String path = req.getSession().getServletContext().getRealPath("/resources/upload/"+filename);
			String r_path = URLEncoder.encode(path, "utf-8");
			res.setContentType("application/x-msdownload");
			res.setHeader("Content-Disposition", "attachmentl; filename="+r_path);
			
			File file = new File(new String(path.getBytes(), "utf-8"));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = res.getOutputStream();
			FileCopyUtils.copy(in, out);
			
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	
	// 5. 게시판 삭제
	@PostMapping("gb2_delete.do")
	public ModelAndView getGuestBook2Delete(@ModelAttribute("vo2")VO vo) {
		
		return new ModelAndView("delete");
	}
	
	@PostMapping("gb2_delete_ok.do")
	public ModelAndView getGuestBook2DeleteOK(VO vo) {
		ModelAndView mv = new ModelAndView();  // 암호가 맞는지 틀린지에 따라 갈곳이 달라지므로 비워둔다
		//비밀번호가 맞는지 검사 (db 에 있는 비밀번호가 암호화 되어있다)
		
		// jsp에서 입력한 암호
		String cpwd = vo.getPwd();
		VO vo2 = guestbook2Service.getGuestBook2Detail(vo.getIdx());
		//db 에서 가져온 암호화된 암호
		String dpwd = vo2.getPwd();
		
		//passwordEncoder.matches(암호화안되었는것, 암호화된것) => boolean	
		if(!passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("delete");
			mv.addObject("pwdchk","fail");
			mv.addObject("vo2", vo);
			return mv;
		} else {
			int res = guestbook2Service.getGuestbook2Delete(vo.getIdx());
			if(res>0) {
				mv.setViewName("redirect:gb2_list.do");
				return mv;
			}
		}
		return new ModelAndView("error");
	}
	
	@PostMapping("gb2_update.do")
public ModelAndView getGuestBook2Update(String idx) {
		ModelAndView mv = new ModelAndView("update");
		VO vo = guestbook2Service.getGuestBook2Detail(idx);
		if(vo != null) {
			mv.addObject("vo", vo);
			return mv;
		}
		return new ModelAndView("error");
	}
	@PostMapping("gb2_update_ok.do")
	public ModelAndView getGuestBook2UpdateOK(VO vo , HttpServletRequest request) {
		ModelAndView mv = new ModelAndView();
		
		String cpwd = vo.getPwd();
		
		VO vo2 = guestbook2Service.getGuestBook2Detail(vo.getIdx());
		String dpwd = vo2.getPwd();
		
		if( ! passwordEncoder.matches(cpwd, dpwd)) {
			mv.setViewName("update");
			mv.addObject("pwdchk", "fail");
			mv.addObject("vo", vo2);
			return mv;
		}else {
			try {
				String path = request.getSession().getServletContext().getRealPath("/resources/upload");
				MultipartFile file = vo.getFile();
				String old_f_name = vo.getOld_f_name();
				if(file.isEmpty()) {
					vo.setFilename(old_f_name);
				}else {
					UUID uuid = UUID.randomUUID();
					String filename = uuid.toString()+"_"+file.getOriginalFilename();
					vo.setFilename(filename);
					
					// 이미지 복사 붙이기
					byte[] in = file.getBytes();
					File out = new File(path, filename);
					FileCopyUtils.copy(in, out);
				}
				int result = guestbook2Service.getGuestbook2Update(vo);
				if(result > 0) {
					mv.setViewName("redirect:gb2_detail.do?idx="+vo.getIdx());
					return mv ;
				}
				return new ModelAndView("error");
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return new ModelAndView("error");
	}
}

//	@PostMapping("gb2_update_ok.do")
//	public ModelAndView getGuestBook2UpdateOK(VO vo, HttpServletRequest req) {
//		ModelAndView mv = new ModelAndView();
//		String cpwd = vo.getPwd();
//		VO vo2 = guestbook2Service.getGuestBook2Detail(vo.getIdx());
//		String dpwd = vo2.getPwd();
//		
//		if(!passwordEncoder.matches(cpwd, dpwd)) {
//		mv.setViewName("update");	
//		mv.addObject("pwdchk", "fail");
//		mv.addObject("vo", vo2);
//		return mv;
//		
//		}else {
//			// file을 갖고있으므로 try catch
//			try {
//				String path = req.getSession().getServletContext().getRealPath("/resources/upload");
//				MultipartFile file = vo.getFile();
//				String old_f_name = vo.getOld_f_name();
//				if(file.isEmpty()) {
//					// 파일이 비어있는 경우 
//					vo.setFilename(old_f_name);
//				}else {
//					// 파일을 새로 업로드한경우 
//					UUID uuid = UUID.randomUUID();
//					String filename = uuid.toString()+"_"+file.getOriginalFilename();
//					vo.setFilename(filename);
//					
//					// 이미지 붙이기
//					byte [] in = file.getBytes();
//					File out = new File(path, filename);
//					FileCopyUtils.copy(in, out);			
//					
//				}
//				int res = guestbook2Service.getGuestbook2Update(vo);
//				
//				if(res>0) {
//					mv.setViewName("redirect:gb2_detail.do?idx="+vo.getIdx());
//					return mv;
//				}
//				return new ModelAndView("error");
//
//			} catch (Exception e) {
//				System.out.println(e);
//			}
//		}
//		return new ModelAndView("error");
//	}
//}
