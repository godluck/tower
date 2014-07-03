package com.oliveryy.pojo;


import org.springframework.stereotype.Component;
/*
 * Author:oliver
 * */
//MessageReply class
@Component
public class MessageReply {
	private int id;
	private int messageId;
	private String date;
	private String content;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
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
	
}
