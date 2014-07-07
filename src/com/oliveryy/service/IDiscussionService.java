package com.oliveryy.service;

import java.sql.Date;
import java.util.Map;

public interface IDiscussionService {
	/**
	 * 
	 * 
	 * @param userId
	 *           
	 * @param groupId
	 * @param title
	 * @param content
	 * @param time
	 * @return
	 */
	public boolean createDiscussion(String userId, String groupId, String title,
			String content, Date time);

	/**
	 * 
	 * 
	 * @param userId
	 *          
	 * @param floor
	 * @param groupId
	 * @param title
	 * @param content
	 * @param time
	 * @param hostId
	 *           
	 * @return
	 */

	/**
	 * 
	 * 
	 * @param discussionId
	 * @return
	 */
	public boolean deleteDiscussion(String discussionId);

	/**
	 *
	 * 
	 * @param discussionId
	 */
	public Map[] getDetail(String discussionId);

	boolean reply(String discussionId, String content, String userId, Date time);
	
	public Map getDiscussion(String discussionId);
}
