package com.oliveryy.action;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IGroupService;
import com.oliveryy.service.IUserService;
@Component
public class GroupAction extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	private String id;
	private int role;
	private String groupId;
	private String groupName;
	private String groupDescription;
	private String groupMember;
	private String userId;
	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getGroupDescription() {
		return groupDescription;
	}

	public void setGroupDescription(String groupDescription) {
		this.groupDescription = groupDescription;
	}

	public String getGroupMember() {
		return groupMember;
	}

	public void setGroupMember(String groupMember) {
		this.groupMember = groupMember;
	}

	public String createGroup() {
		id=getSession().getAttribute("id").toString();
		if (userService.getUserInfo(id).get("group_id") == null) {
			int g=userService.setUpNewGroup(id, groupName, groupDescription);
			if (g>=0) {
				JSONArray group_member=JSONArray.fromObject(groupMember);
				for(int i=0;i<group_member.size();i++){
					String userId=group_member.get(i).toString();
					if(!groupService.addMember(userId, String.valueOf(g))){
						getWriter().write("m"+i+"failed");
					}
				}
				getWriter().write("{\"error\":0}");
			} else {
				getWriter().write("{\"error\":1,\"reason\":\"failed to setup new group\"}");
			}
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"You already have a group\"");
		}
		return null;
	}

	public String deleteGroup() {
		id=getSession().getAttribute("id").toString();
		role=Integer.parseInt(userService.getUserInfo(id).get("user_role").toString());
		if (role<2) {
			if (groupService.deleteGroup(groupId)) {
				getWriter().write("{\"error\":0}");
			} else {
				getWriter().write("{\"error\":1,\"reason\":\"failed to delete the group\"}");
			}
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"insufficient privileges\"}");
		}
		return null;
	}

	public String addMember() {
		id=getSession().getAttribute("id").toString();
		role=Integer.parseInt(userService.getUserInfo(id).get("user_role").toString());
		if (role<3) {
			if (groupService.addMember(groupMember, groupId)) {
				getWriter().write("{\"error\":0}");
			} else {
				getWriter().write("{\"error\":1,\"reason\":\"failed to add new member\"}");
			}
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"insufficient privileges\"}");
		}
		return null;
	}

	public String changeGroup() {
		id=getSession().getAttribute("id").toString();
			if (userService.joinGroup(id, groupId)) {
				getWriter().write("{\"error\":0}");
			} else {
				getWriter().write("{\"error\":1,\"reason\":\"failed to change group\"}");
			}
		return null;
	}

	public String editGroupName() {
		id=getSession().getAttribute("id").toString();
		role=Integer.parseInt(userService.getUserInfo(id).get("user_role").toString());
		if (role<3) {
			if (groupService.setProject(groupId, groupName)) {
				getWriter().write("{\"error\":0}");
			} else {
				getWriter().write("{\"error\":1,\"reason\":\"failed to edit group name\"}");
			}
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"insufficient privileges\"}");
		}
		return null;
	}
	public String setManager(){
		id=getSession().getAttribute("id").toString();
		role=Integer.parseInt(userService.getUserInfo(id).get("user_role").toString());
		if(role<2){
			if (groupService.setManager(groupId, userId)) {
				getWriter().write("{\"error\":0}");
			} else {
				getWriter().write("{\"error\":1,\"reason\":\"failed to set manager\"}");
			}
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"insufficient privileges\"}");
		}
		return null;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
