package com.oliveryy.action;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IDiscussionService;
import com.oliveryy.service.IUserService;
@Component
public class DiscussionAction extends BaseAction {
	@Autowired
	private IDiscussionService discussionService;
	@Autowired
	private IUserService userService;
	private String discussionId;
	private String discussionName;
	private String groupId;
	private String discussionContent;
	private String id;

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

	public String getDiscussionId() {
		return discussionId;
	}

	public void setDiscussionId(String discussionId) {
		this.discussionId = discussionId;
	}
	public String addDiscussion() {
		id=getSession().getAttribute("id").toString();
		if (discussionService.createDiscussion(id, groupId, discussionName,
				discussionContent, "d")) {
			getWriter().write("{\"error\":0}");
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"failed to create new discussion\"}");
		}
		;
		return null;
	}

	public String addReport() {
		id=getSession().getAttribute("id").toString();
		if (discussionService.createDiscussion(id, groupId, discussionName,
				discussionContent, "r")) {
			getWriter().write("{\"error\":0}");
		} else {
			getWriter().write("{\"error\":1,\"reason\":\"failed to create new report\"}");
		}
		;
		return null;
	}

	public String reply() {
		id=getSession().getAttribute("id").toString();
		if (discussionService.reply(discussionId, discussionContent, id)) {
			getWriter().write("{error:0}");
		} else {
			getWriter().write("{error:1,reason:\"failed to create new reply\"}");
		}
		;
		return null;
	}

	public String deleteDiscussion() {
		id=getSession().getAttribute("id").toString();
		if (Integer.parseInt(userService.getUserInfo(id).get("user_role")
				.toString()) < 3) {
			if (discussionService.deleteDiscussion(discussionId)) {
				getWriter().write("{error:0}");
			} else {
				getWriter().write("{error:1,reason:\"failed to delete discussion\"}");
			}
			;
		} else {
			getWriter().write("{error:1,reason:\"insufficient privileges\"}");
		}
		return null;
	}
}
