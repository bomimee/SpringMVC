package com.ict.guestbook2.dao;

import org.springframework.web.multipart.MultipartFile;

public class VO {
	// 디비 에 저장할때
 private String idx, name, subject, content, email, pwd, filename, regdate, old_f_name;
 //파라미터에 저장할때 사용할 변수 
 private MultipartFile file;
public String getIdx() {
	return idx;
}
public void setIdx(String idx) {
	this.idx = idx;
}
public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}
public String getSubject() {
	return subject;
}
public void setSubject(String subject) {
	this.subject = subject;
}
public String getContent() {
	return content;
}
public void setContent(String content) {
	this.content = content;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
public String getPwd() {
	return pwd;
}
public void setPwd(String pwd) {
	this.pwd = pwd;
}
public String getFilename() {
	return filename;
}
public void setFilename(String filename) {
	this.filename = filename;
}
public String getRegdate() {
	return regdate;
}
public void setRegdate(String regdate) {
	this.regdate = regdate;
}
public String getOld_f_name() {
	return old_f_name;
}
public void setOld_f_name(String old_f_name) {
	this.old_f_name = old_f_name;
}
public MultipartFile getFile() {
	return file;
}
public void setFile(MultipartFile file) {
	this.file = file;
}
 
 
}
