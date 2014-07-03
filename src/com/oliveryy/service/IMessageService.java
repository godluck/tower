package com.oliveryy.service;

import java.util.Map;
import com.oliveryy.pojo.Message;
import com.oliveryy.pojo.MessageReply;
/*
 * Author:oliver
 * */
//interface IMessageService
public interface IMessageService {
	public Map[] CommonMessage(String nickName);
	public Map[] AdminMessage();
	public void addMessage(Message message);
	public void addMessageReply(MessageReply messageReply);
}
