package com.oliveryy.action;

import net.sf.json.JSONArray;

import org.springframework.beans.factory.annotation.Autowired;

import com.oliveryy.service.IGroupService;
import com.oliveryy.service.IUserService;

public class GroupAction extends BaseAction {
	@Autowired
	private IUserService userService;
	@Autowired
	private IGroupService groupService;
	private String id = getSession().get("id").toString();
	private int role=Integer.parseInt(userService.getUserInfo(id).get("user_role").toString());
	private String groupId;
	private String groupName;
	private String groupDescription;
	private String groupMember;

	public String createGroup() {
		if (userService.getUserInfo(id).get("group_id") != null) {
			int g=userService.setUpNewGroup(id, groupName, groupDescription);
			if (g>0) {
				JSONArray group_member=JSONArray.fromObject(groupMember);
				for(int i=0;i<group_member.size();i++){
					String userId=group_member.get(i).toString();
					if(!groupService.addMember(userId, String.valueOf(g))){
						getWriter().write("m"+i+"failed");
					}
				}
				getWriter().write("success");
			} else {
				getWriter().write("failed");
			}
		} else {
			getWriter().write("insufficient privileges");
		}
		return null;
	}

	public String deleteGroup() {
		if (role<2) {
			if (groupService.deleteGroup(groupId)) {
				getWriter().write("success");
			} else {
				getWriter().write("failed");
			}
		} else {
			getWriter().write("insufficient privileges");
		}
		return null;
	}

	public String addMember() {
		if (role<3) {
			if (groupService.addMember(groupMember, groupId)) {
				getWriter().write("success");
			} else {
				getWriter().write("failed");
			}
		} else {
			getWriter().write("insufficient privileges");
		}
		return null;
	}

	public String changeGroup() {
			if (userService.joinGroup(id, groupId)) {
				getWriter().write("success");
			} else {
				getWriter().write("failed");
			}
		return null;
	}

	public String editGroupName() {
		if (role<3) {
			if (groupService.setProject(groupId, groupName)) {
				getWriter().write("success");
			} else {
				getWriter().write("failed");
			}
		} else {
			getWriter().write("insufficient privileges");
		}
		return null;
	}
}
