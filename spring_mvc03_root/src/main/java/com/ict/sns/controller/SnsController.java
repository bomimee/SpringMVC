package com.ict.sns.controller;

import java.io.BufferedReader;
import java.io.BufferedWriter;

import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.ict.sns.AddrVO;
import com.ict.sns.kakao.KkoVO;
import com.ict.sns.naver.NaverLoginVO;
import com.ict.sns.naver.NaverUserVO;

@Controller
public class SnsController {
	
	@Autowired
	private AddrDAO addrDao;
	
	@RequestMapping("spring_sns_go.do")
	public ModelAndView getSpringSnsGo() {
		return new ModelAndView("sns/loginForm");
	}

	@RequestMapping("kko.do")
	public ModelAndView kkoLogin(HttpServletRequest req) {
		// 1. code
		String code = req.getParameter("code");
		System.out.println(code);

		// 2. token
		String reqURL = "https://kauth.kakao.com/oauth/token";
		try {
			URL url = new URL(reqURL);
			HttpsURLConnection conn = (HttpsURLConnection) url.openConnection();

			// post
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// header
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// main
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=79e97b402dec41b958cb37d23906a6be");
			sb.append("&redirect_uri=http://localhost:8090/kko.do");
			sb.append("&code=" + code);
			bw.write(sb.toString());
			bw.flush();

			// res = 200 >>> success
			int responseCode = conn.getResponseCode();

			// tpken req 를 통해결과를 받자 => JSON type
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line = "";
			StringBuffer sb2 = new StringBuffer();
			while ((line = br.readLine()) != null) {
				sb2.append(line);
			}
			String res = sb2.toString();
			// System.out.println(res);

			// 3. 사용자 정보 - token 이용해서
			// 받은정보는 세션에 저장(ajax 사용해서 사용자 정보 가져온다)
			Gson gson = new Gson();
			KkoVO kvo = gson.fromJson(res, KkoVO.class);

			req.getSession().setAttribute("access_token", kvo.getAccess_token());
			req.getSession().setAttribute("refresh_token", kvo.getRefresh_token());
			req.getSession().setAttribute("token_type", kvo.getToken_type());

			return new ModelAndView("sns/result");
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("sns/error");
	}

	@RequestMapping("kakaomap01.do")
	public ModelAndView kakaoMap01() {
		return new ModelAndView("sns/kakaomap01");
	}
	@RequestMapping("kakaomap02.do")
	public ModelAndView kakaoMap02() {
		return new ModelAndView("sns/kakaomap02");
	}
	@RequestMapping("kakaomap03.do")
	public ModelAndView kakaoMap03() {
		return new ModelAndView("sns/kakaomap03");
	}
	@RequestMapping("kakaomap04.do")
	public ModelAndView kakaoMap04() {
		return new ModelAndView("sns/kakaomap04");
	}
	@RequestMapping("kakaoaddr.do")
	public ModelAndView kakaoAddr() {
		return new ModelAndView("sns/addr");
	}
	
	

	@RequestMapping("kakaoaddr_ok.do")
	public ModelAndView kakaoAddrOK(AddrVO avo) {
		try {
			//DB 
			int res = addrDao.addr_Insert(avo);
			if(res>0) {
				return new ModelAndView("sns/loginForm");
						
			}
		} catch (Exception e) {
			return new ModelAndView("sns/error");
		}
		return new ModelAndView("sns/kakaoaddr");
	}
	
 @RequestMapping("dynamic.do")
 public ModelAndView dynamicQuery() {
	 return new ModelAndView("emp/dynamic");
 }
 
 @RequestMapping("naverlogin.do")
	public ModelAndView NaverLogin(HttpServletRequest request) {
		String code = request.getParameter("code");
		String state = request.getParameter("state");

		// 2. 토큰 받기 (인증코드필요)
		String reqURL = "https://nid.naver.com/oauth2.0/token";
		try {
			URL url = new URL(reqURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			// POST 요청
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// 헤더 요청
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

			// 본문
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));

			StringBuffer sb = new StringBuffer();
			sb.append("grant_type=authorization_code");
			sb.append("&client_id=y0uYS0PIjookcvhFn2xI");
			sb.append("&client_secret=4lTGOJ1aYn");
			sb.append("&code=" + code);
			sb.append("&state=" + state);
			bw.write(sb.toString());
			bw.flush();

			// 결과 코드가 200이면 성공
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {
				// 토큰요청을 통한 결과를 얻는데 JSON 타입
				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = "";
				StringBuffer sb2 = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb2.append(line);
				}
				String result = sb2.toString();
				
				Gson gson = new Gson();
				NaverLoginVO nvo = gson.fromJson(result, NaverLoginVO.class);
				request.getSession().setAttribute("access_token", nvo.getAccess_token());
				request.getSession().setAttribute("token_type", nvo.getGetToken_type());
				request.getSession().setAttribute("refresh_token", nvo.getRefresh_token());

				return new ModelAndView("sns/result2");
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new ModelAndView("sns/error");
	}

  //	네이버 로그아웃 
	@RequestMapping("naverlogout.do")
	public ModelAndView getNaverLogout(HttpSession session) {
		session.invalidate();
	   return new ModelAndView("sns/loginForm");
	}
	
	// 네이버 서비스 해제 
	@RequestMapping("naverlogout2.do")
	public ModelAndView getSnsLogout(HttpSession session) {
		// 연동해제
		String apiURL = "https://nid.naver.com/oauth2.0/token" + "?grant_type=delete"
				+ "&client_id=y0uYS0PIjookcvhFn2xI" + "&client_secret=4lTGOJ1aYn" + "&access_token="
				+ session.getAttribute("access_token") + "&service_provider='NAVER'";

		try {
			URL url = new URL(apiURL);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setDoOutput(true);

			// 결과 코드가 200 이면 성공
			int responseCode = conn.getResponseCode();
			if (responseCode == HttpURLConnection.HTTP_OK) {

				// 토큰 요청을 통한 결과를 얻는데 JSON 타입이다.
				BufferedReader br = 
						new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String result = sb.toString();
				System.out.println(result);
		     // 세션 초기화
		     session.invalidate();
		     // 다시 로그인 폼으로
		     return new ModelAndView("sns/loginForm");
		   }
		} catch (Exception e) {
		}
		return null;
	}

	// 카카오 로그아웃
	@RequestMapping("kakaologout.do")
	public ModelAndView getKakaoLogout(HttpSession session) {
		session.invalidate();
		return new ModelAndView("sns/loginForm");
	}

	
	
}
