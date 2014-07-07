package com.oliveryy.pojo;

public class User {
	private String userId;
	private String name;
	private String password;
	private String role;
	private String groupId;
	

	public User(String userId, String name, String password, String role,
			String groupId) {
		super();
		this.userId = userId;
		this.name = name;
		this.password = password;
		this.role = role;
		this.groupId = groupId;
	}



	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

}
