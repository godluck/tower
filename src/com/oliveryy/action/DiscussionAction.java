package com.oliveryy.action;

import org.springframework.beans.factory.annotation.Autowired;

import com.oliveryy.service.IDiscussionService;

public class DiscussionAction extends BaseAction {
	@Autowired
	private IDiscussionService discussionService; 
	private String discussionName;
	private String groupId;
	private String discussionContent;
	private String id=getSession().get("id").toString();
	public String getDiscussionName() {
		return discussionName;
	}
	public void setDiscussionName(String discussionName) {
		this.discussionName = discussionName;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getDiscussionContent() {
		return discussionContent;
	}
	public void setDiscussionContent(String discussionContent) {
		this.discussionContent = discussionContent;
	}
	public String addDiscussion(){
		
		if(discussionService.createDiscussion(id, groupId, discussionName, discussionContent, "d")){
			getWriter().write("success");
		}else{
			getWriter().write("failed");
		};
		return null;
	}
}
