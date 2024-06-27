package com.ict.sns.kakao;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KkoAjaxController {

	@RequestMapping(value="kakaoUser.do", produces="application/json; charset:utf-8")
	@ResponseBody
	public String memberChk(HttpSession session) {
		//access_token 가지고 사용자 정보를 가져옴
		
		String access_token = (String) session.getAttribute("access_token");
		
		String apiURL = "https://kapi.kakao.com/v2/user/me";
		String header =  "Bearer "+access_token;
		Map<String, String> requestHeaders = new HashMap<String, String>();
		requestHeaders.put("Authorization", header);
		
		String responseBody = kakao_send(apiURL, requestHeaders, session);
		return responseBody;
	}
	

	
	public String kakao_send(String apiURL, Map<String, String> requestHeaders, HttpSession session) {
		HttpsURLConnection conn = null;
		try {
			URL url = new URL(apiURL);
			conn = (HttpsURLConnection)url.openConnection();
			
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			
			for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
				conn.setRequestProperty(header.getKey(), header.getValue());
			}
			int responseCode = conn.getResponseCode();
			if(responseCode == HttpsURLConnection.HTTP_OK) {
	
				return readBody(conn.getInputStream(), session);
			}else {
				return readBody(conn.getErrorStream(), session);
			}
		} catch (Exception e) {
			System.out.println("fail");
		}finally {
			try {
				conn.disconnect();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		
		}
		return null;
		
	}
	
	
	public String readBody(InputStream body, HttpSession session) {
		InputStreamReader isr = new InputStreamReader(body);
		try {
			BufferedReader br = new BufferedReader(isr);
			StringBuffer sb = new StringBuffer();
			String line = "";
			 while ((line = br.readLine()) != null) {
		            sb.append(line);
		        }
		// db 저장하려면
			 session.setAttribute("loginchk", line);
		        return sb.toString();
		} catch (Exception e) {
		System.out.println("API response fail");
		}
		return null;
	}
	


}
