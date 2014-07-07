package com.oliveryy.service;

import java.sql.Date;
import java.util.Map;

public interface IMessageService {
	/**
	 * 
	 * 
	 * @param userId
	 * @param msgContent
	 * @param msgTime
	 */
	public boolean addMsg(String userId, String msgContent, Date msgTime);

	/**
	 *
	 * 
	 * @return
	 */
	public Map[] getMsg(String userId);
	
	public boolean readMsg(String userId);

}
