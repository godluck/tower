package com.oliveryy.service;

import java.sql.Date;
import java.util.Map;

public interface IDiscussionService {

	public boolean createDiscussion(String userId, String groupId, String title,
			String content);

	public boolean deleteDiscussion(String discussionId);

	public Map[] getDetail(String discussionId);

	boolean reply(String discussionId, String content, String userId, Date time);
	
	public Map getDiscussion(String discussionId);
	public Map[] getDiscussions(String groupId);
}
