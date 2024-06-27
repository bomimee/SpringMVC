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

import com.google.gson.Gson;

@RestController
public class KkoAjaxController2 {

	@RequestMapping(value = "kakaoUser2.do", produces = "text/plain; charset:utf-8")
	@ResponseBody
	public String memberChk(HttpSession session) {
		// access_token 가지고 사용자 정보를 가져옴

		String access_token = (String) session.getAttribute("access_token");

		String apiURL = "https://kapi.kakao.com/v2/user/me";
		

		HttpsURLConnection conn = null;
		try {
			URL url = new URL(apiURL);
			conn = (HttpsURLConnection) url.openConnection();

			conn.setRequestMethod("POST");
			conn.setDoOutput(true);

			// header request
			conn.setRequestProperty("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
			conn.setRequestProperty("Authorization", "Bearer " + access_token);

			int responseCode = conn.getResponseCode();
			if (responseCode == HttpsURLConnection.HTTP_OK) {

				BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				String line = "";
				StringBuffer sb = new StringBuffer();
				while ((line = br.readLine()) != null) {
					sb.append(line);
				}
				String res = sb.toString();

				Gson gson = new Gson();
				// json 을 객체로 => fromJson(변경할것, 변경된 것)

				KkoUserVO kvo = gson.fromJson(res, KkoUserVO.class);

				String kakao_id = kvo.getId();
				String kakao_nickname = kvo.getProperties().getNickname();
				String kakao_email = kvo.getKakao_account().getEmail();

				// DB 저장하기

				
				
				return kakao_id + "/" + kakao_nickname + "/" + kakao_email;
			}
		} catch (Exception e) {
			System.out.println("fail");
		}
		return null;

	}



}