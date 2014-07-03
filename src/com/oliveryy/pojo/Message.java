package com.oliveryy.pojo;

import org.springframework.stereotype.Component;
/*
 * Author:oliver
 * */
//message class
@Component
public class Message {
	private int id;
	private String userId;
	private String date;
	private String content;
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
}
