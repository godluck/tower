package com.oliveryy.pojo;

import java.sql.Date;

public class Message {
	private int msgId;
	private String userId;
	private String msgContent;
	private Date time;
	
	public Message(int msgId, String userId, String msgContent, Date time) {
		super();
		this.msgId = msgId;
		this.userId = userId;
		this.msgContent = msgContent;
		this.time = time;
	}
	public int getMsgId() {
		return msgId;
	}
	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getMsgContent() {
		return msgContent;
	}
	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	

}
