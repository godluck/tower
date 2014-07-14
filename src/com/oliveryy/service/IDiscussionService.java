package com.oliveryy.service;

import java.sql.Date;
import java.util.Map;

public interface IDiscussionService {

	public boolean createDiscussion(String userId, String groupId, String title,
			String content,String type);

	public boolean deleteDiscussion(String discussionId);

	public Map[] getDetail(String discussionId);

	boolean reply(String discussionId, String content, String userId, Date time);
	
	public Map getDiscussion(String discussionId,String type);
	public Map[] getDiscussions(String groupId,String type);
}
