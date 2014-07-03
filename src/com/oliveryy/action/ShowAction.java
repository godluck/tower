package com.oliveryy.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.pojo.Message;
import com.oliveryy.pojo.MessageReply;
import com.oliveryy.service.IMessageService;
import com.oliveryy.service.IUserService;
//
@Component 
public class ShowAction extends BaseAction{
	@Autowired
	private IUserService userService; 
	@Autowired
	private IMessageService MessageService;
	@Autowired
	private Message Message;
	@Autowired
	private MessageReply MessageReply;
	private String[] messageIds;
	private String[] replyContents;
	private String replyContent;
	/*@Autowired
	private ShowAction ShowAction;*/
	
	private SimpleDateFormat date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//for add message function
	public void addMessage(){
		Message.setUserId(this.getSession().get("nickName").toString());
		Message.setDate(this.date.format(new Date()));
		Message.setContent(this.replyContent);
		this.MessageService.addMessage(Message);
	}
	//add All message reply 
	public void addAllMessageReply(){
		for(int i=0;i<messageIds.length;i++){
			MessageReply.setMessageId(Integer.parseInt(messageIds[i]));
			MessageReply.setDate(this.date.format(new Date()));
			MessageReply.setContent(replyContents[i]);
			this.MessageService.addMessageReply(MessageReply);
		}
	} 
	//default method
	@SuppressWarnings("unchecked")
	public String execute(){
		if(Integer.parseInt(this.getSession().get("isAdmin").toString())==1){
			this.addAllMessageReply();
			this.getRequest().put("rows", this.MessageService.AdminMessage());
			return "success";
		}
		else{
			this.addMessage();
			this.getRequest().put("rows", this.MessageService.CommonMessage(this.getSession().get("nickName").toString()));
			return "success";
		}
	}
	public String getReplyContent() {
		return replyContent;
	}
	public void setReplyContent(String replyContent) {
		this.replyContent = replyContent;
	}
	public String[] getMessageIds() {
		return messageIds;
	}
	public void setMessageIds(String[] messageIds) {
		this.messageIds = messageIds;
	}
	public String[] getReplyContents() {
		return replyContents;
	}
	public void setReplyContents(String[] replyContents) {
		this.replyContents = replyContents;
	}




	

}
