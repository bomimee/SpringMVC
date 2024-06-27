package com.ict.sns.naver;

public class NaverLoginVO {
private String refresh_token,access_token, service_provider, getToken_type;

public String getGetToken_type() {
	return getToken_type;
}

public void setGetToken_type(String getToken_type) {
	this.getToken_type = getToken_type;
}

public String getRefresh_token() {
	return refresh_token;
}

public void setRefresh_token(String refresh_token) {
	this.refresh_token = refresh_token;
}

public String getAccess_token() {
	return access_token;
}

public void setAccess_token(String access_token) {
	this.access_token = access_token;
}

public String getService_provider() {
	return service_provider;
}

public void setService_provider(String service_provider) {
	this.service_provider = service_provider;
}


}
