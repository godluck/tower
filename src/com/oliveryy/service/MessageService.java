package com.oliveryy.service;

import java.sql.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;
@Component
public class MessageService implements IMessageService {
	@Autowired
	private IDbhelper dao;
	private String[] msgTemplate;
	@Override
	public boolean addMsg(String userId, String msgContent, Date msgTime) {
		// TODO Auto-generated method stub
		try{
			//modify msgContent
			//not yet
			String sql="insert into message values(null,?,?,?,0)";
			Object[] params={userId,msgContent,msgTime};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public Map[] getMsg(String userId) {
		String sql="select * from message where user_id=?";
		Object[] params={userId};
		return dao.runSelect(sql, params);
	}

	@Override
	public boolean readMsg(String userId) {
		try{
			String sql="update table message set status=1 where user_id=?";
			Object[] params={userId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

}
