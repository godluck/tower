package com.oliveryy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;
import com.oliveryy.pojo.Message;
import com.oliveryy.pojo.MessageReply;
/*
 * Author:oliver
 * */
//IMessageServiceImpl
@Component(value="MessageService")
public class IMessageServiceImpl implements IMessageService {
	@Autowired
	private IDbhelper dao;
	@SuppressWarnings("unchecked")
	@Override
	//get commonMessage
	public Map[] CommonMessage(String nickName) {
		String sql = "select userId from userinfo where nickName=?";
		Object[] params={nickName};
		Map row=dao.runSelect(sql, params)[0];
		int userId=Integer.parseInt(row.get("userId").toString());
		//获取整合后的消息
		String sql2 = "select * from Message m left join MessageReply mr on m.id = mr.messageId where m.userId=? order by m.sendDtm";
		Object[] params2={userId};
		Map[] row2=dao.runSelect(sql2, params2);
		int i = 0 ;
		for(Map t : row2){
			if(t.get("replyContent")==null){
				row2[i].put("replyContent", "管理员暂未回复");
			}
			i++;
		}
		return row2;
	}
	//get Admin reply messages
	@SuppressWarnings("unchecked")
	@Override
	public Map[] AdminMessage() {
		String sql = "select m.id id,m.sendDtm sendDtm,m.messageContent messageContent,mr.messageId messageId,mr.replyDtm replyDtm,mr.replyContent replyContent,u.userId userId,u.nickName nickName,u.isAdmin isAdmin from (Message m left join MessageReply mr on m.id = mr.messageId) left join userinfo u on u.userId = m.userId where isAdmin = 0 order by m.sendDtm";
		Map[] row=dao.runSelect(sql);
		int i = 0;
		for(Map t : row){
			if(t.get("replyContent")==null||t.get("replyContent").equals("")){
				row[i].put("replyContent", "管理员暂未回复");
			}
			i++;
		}
		return row;
	}
	//add Messages
	@Override
	public void addMessage(Message message) {
		String sql = "select userId from userinfo where nickName=?";
		Object[] params={message.getUserId()};
		Map row=dao.runSelect(sql, params)[0];
		int userId=Integer.parseInt(row.get("userId").toString());
		String sql2 = "insert into message(userId,sendDtm,messageContent) values(?,?,?)";
		Object[] params2={userId,message.getDate(),message.getContent()};
		dao.runUpdate(sql2, params2);
	}
	//add reply message
	@Override
	public void addMessageReply(MessageReply messageReply) {
		String sql = "insert into messageReply(messageId,replyDtm,ReplyContent) values(?,?,?)";
		Object[] params={messageReply.getMessageId(),messageReply.getDate(),messageReply.getContent()};
		dao.runUpdate(sql, params);
	}

}














