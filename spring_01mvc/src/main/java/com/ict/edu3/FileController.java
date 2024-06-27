package com.ict.edu3;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class FileController {

	// COS Library
	@PostMapping("f_up.do")
	public ModelAndView fileUp(HttpServletRequest req, HttpServletResponse res) {
		try {
			ModelAndView mv = new ModelAndView("test3/result");
			String path = req.getSession().getServletContext().getRealPath("/resources/upload");
			MultipartRequest mr = new MultipartRequest(req, path, 500 * 1024 * 1024, "utf-8",
					new DefaultFileRenamePolicy());
			String name = mr.getParameter("name");
			String f_name = mr.getFilesystemName("f_name");
			String file_type = mr.getContentType("f_name");

			File file = mr.getFile(f_name);
			long size = file.length() / 1024; // KB 로 표시
			SimpleDateFormat sdf = new SimpleDateFormat("yy.MM.dd hh:mm:ss E"); // 연월일시분초 요일
			String lastday = sdf.format(file.lastModified()); // 마지막 수정날자

			mv.addObject("name", name);
			mv.addObject("f_name", f_name);
			mv.addObject("file-type", file_type);
			mv.addObject("size", size);
			mv.addObject("lastday", lastday);

			return mv;
		} catch (Exception e) {
			System.out.println(e);
		}
		return null;
	}

	// download
	@GetMapping("down.do")
	public void fileDown(HttpServletRequest req, HttpServletResponse res) {
		FileInputStream fis = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			String f_name = req.getParameter("f_name");
			String path = req.getSession().getServletContext().getRealPath("/resources/upload/" + f_name);
			String rpath = URLEncoder.encode(path, "utf-8");

			// 브라우저 설정
			res.setContentType("application/x-msdownload");
			res.setHeader("Content-Disposition", "attachment; filenam" + rpath);

			File file = new File(new String(path.getBytes(), "utf-8"));
			int b;

			fis = new FileInputStream(file);
			bis = new BufferedInputStream(fis);
			bos = new BufferedOutputStream(res.getOutputStream());

			while ((b = bis.read()) != -1) {
				bos.write(b);
				bos.flush();
			}
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			try {
				bos.close();
				bis.close();
				fis.close();
			} catch (Exception e2) {
			}
		}
	}

	// pom.xml 에 파일업로드와 다운로드에 관련된 라이브러리 등록
	// commos-fileupload, commons-io
	// Servlet-context.xml 에 파일업로드용 클래스 등록
	// 업로드시 파일 용량 제한설정
	@PostMapping("f_up2.do")
	public ModelAndView fileup2(@RequestParam("f_name") MultipartFile fname, @RequestParam("name") String name,
			HttpServletRequest request) {
		try {
			ModelAndView mv = new ModelAndView("test3/result");
			String path = request.getSession().getServletContext().getRealPath("/resources/upload");

			// 스프링의 파일업로드의 단점은 같은 이름처리를 하지 않습니다.
			// 저장폴더에 같은이름이 있으면 업로드 되지 않음
			String f_name = fname.getOriginalFilename();
			String file_type = fname.getContentType();
			long size = fname.getSize() / 1024;

			// 실제 올리는 작업 1.올릴파일을 바이트 배열로 만듬
			byte[] in = fname.getBytes();
			// 2. 올릴장소와 저장이름 지정
			File out = new File(path, f_name);
			// 3. 파일복사 (upload = download 모두 할수있다_)
			FileCopyUtils.copy(in, out);

			mv.addObject("name", name);
			mv.addObject("f_name", f_name);
			mv.addObject("file-type", file_type);
			mv.addObject("size", size);

			return mv;

		} catch (Exception e) {
			System.out.println(2);
		}
		return null;
	}

	@GetMapping("down2.do")
	public void fileDown2(@RequestParam("f_name") String f_name, HttpServletRequest req, HttpServletResponse res) {
		try {
			String path = req.getSession().getServletContext().getRealPath("/resources/imgs/" + f_name);
			String rpath = URLEncoder.encode(path, "utf-8");

			// 브라우저 설정 (브라우저에서 다운받으니까)
			res.setContentType("application/x-msdownload");
			res.setHeader("Content-Disposition", "attachment; filenam" + rpath);
			
			File file = new File(new String(path.getBytes(), "utf-8"));
			FileInputStream in = new FileInputStream(file);
			OutputStream out = res.getOutputStream();
			FileCopyUtils.copy(in, out);

		} catch (Exception e) {
			System.out.println(e);
		}
	}
}
