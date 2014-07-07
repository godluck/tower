package com.oliveryy.pojo;

import java.sql.Date;

public class Discussion {
	private String title;
	private String content;
	private Date time;
	private String hostId;//楼主
	private int id;
	public Discussion(String title, String content, Date time, String hostName,
			int id) {
		super();
		this.title = title;
		this.content = content;
		this.time = time;
		this.hostId = hostName;
		this.id = id;
	}
	public Discussion(String title, String content, Date time, String hostName) {
		super();
		this.title = title;
		this.content = content;
		this.time = time;
		this.hostId = hostName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public String getHostName() {
		return hostId;
	}
	public void setHostName(String hostName) {
		this.hostId = hostName;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	

}
