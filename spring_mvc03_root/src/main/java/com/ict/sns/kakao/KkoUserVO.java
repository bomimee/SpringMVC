package com.ict.sns.kakao;

public class KkoUserVO {

	private String id, connected_at;
	private Properties properties;
	private KakaoAccount kakao_account;
	public KkoUserVO(String id, String connected_at, Properties properties, KakaoAccount kakao_account) {
	
		this.id = id;
		this.connected_at = connected_at;
		this.properties = properties;
		this.kakao_account = kakao_account;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getConnected_at() {
		return connected_at;
	}
	public void setConnected_at(String connected_at) {
		this.connected_at = connected_at;
	}
	public Properties getProperties() {
		return properties;
	}
	public void setProperties(Properties properties) {
		this.properties = properties;
	}
	public KakaoAccount getKakao_account() {
		return kakao_account;
	}
	public void setKakao_account(KakaoAccount kakao_account) {
		this.kakao_account = kakao_account;
	}
	
}
