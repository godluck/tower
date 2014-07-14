package com.oliveryy.service;

import java.util.Map;

import com.oliveryy.pojo.User;

public interface IUserService {
	
	public Map getUserInfo(String userId);


	public boolean joinGroup(String userId, String groupId);


	boolean updateNickname(String userId, String newUserNickname);


	boolean updatePassword(String userId, String newPassword);


	int setUpNewGroup(String userId,String groupName,String groupDescription);



}
