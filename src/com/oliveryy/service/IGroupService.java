package com.oliveryy.service;

import java.util.ArrayList;
import java.util.Map;

import com.oliveryy.pojo.User;

public interface IGroupService {
	
	public boolean addMember(String userId, String groupId);

	
	public boolean deleteMember(String userId);

	
	public Map getLeader(String groupId);

	
	public Map getAdmin(String groupId);

	
	public Map[] getMember(String groupId);

	
	public boolean setProject(String groupId, String projName);
	
	public Map[] getGroups();
	
	public boolean deleteGroup(String groupId);
}
