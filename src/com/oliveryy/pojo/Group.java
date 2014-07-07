package com.oliveryy.pojo;

public class Group {
	private int groupId;
	private String groupName;
	private int status;

	public Group(int groupId) {
		super();
		this.groupId = groupId;
	}

	public Group(int groupId, String groupName, int status) {
		super();
		this.groupId = groupId;
		this.groupName = groupName;
		this.status = status;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

}
