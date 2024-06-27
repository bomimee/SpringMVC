package cpm.ict.ex04;

import java.util.Calendar;

public class Age {
	// 주민번호 받아서 나이 계싼
	// 젠더 1,2 1900
	// 젠더 3,4 2000
	public int count(String jumin, String gender) {
		int res = 0;
		int year = Calendar.getInstance().get(Calendar.YEAR);
		int user_year = 0;
		if(gender.equals("1") || gender.equals("2")) {
			
			user_year = 1900 + Integer.parseInt(jumin.substring(0, 2));
		}else if (gender.equals("3")|| gender.equals("4")) {
			user_year = 2000 + Integer.parseInt(jumin.substring(0, 2));		
		}
		res =  year - user_year;
		return res;
	}

}
