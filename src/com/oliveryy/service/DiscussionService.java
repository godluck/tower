package com.oliveryy.service;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;
@Component
public class DiscussionService implements IDiscussionService {
	@Autowired
	private IDbhelper dao;
	@Override
	public boolean createDiscussion(String userId, String groupId,
			String title, String content,String type) {
		try{
			String sql="insert into discussion values(null,?,?,?,?,now(),?)";
			Object[] params={groupId,userId,title, content,type};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean reply(String discussionId, String content,
			String userId) {
		try{
			String sql="insert into reply values(?,null,?,now(),?)";
			Object[] params={discussionId, userId,content};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean deleteDiscussion(String discussionId) {
		try{
			String sql="delete from discussion where discussion_id=?";
			Object[] params={discussionId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public Map[] getDetail(String discussionId) {
		String sql="select * from reply where discussion_id=?";
		Object[] params={discussionId};
		Map[] tasks= dao.runSelect(sql, params);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("reply_time").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				java.util.Date dld = df.parse(dl);
				tasks[i].remove("reply_time");
				tasks[i].put("reply_time",dld);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks;
	}

	@Override
	public Map getDiscussion(String discussionId,String type) {
		String sql="select * from discussion where discussion_id=? and discussion_type=?";
		Object[] params={discussionId,type};
		Map[] tasks= dao.runSelect(sql, params);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("discussion_time").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				java.util.Date dld = df.parse(dl);
				tasks[i].remove("discussion_time");
				tasks[i].put("discussion_time",dld);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks[0];
	}

	@Override
	public Map[] getDiscussions(String groupId,String type) {
		String sql="select * from discussion where group_id=? and discussion_type=?";
		Object[] params={groupId,type};
		Map[] tasks= dao.runSelect(sql, params);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("discussion_time").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				java.util.Date dld = df.parse(dl);
				tasks[i].remove("discussion_time");
				tasks[i].put("discussion_time",dld);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks;
	}

	@Override
	public Map[] getUserReports(String userId, String type) {
		String sql="select * from discussion where user_id=? and discussion_type=?";
		Object[] params={userId,type};
		Map[] tasks= dao.runSelect(sql, params);
		for (int i = 0; i < tasks.length; i++) {
			String dl = tasks[i].get("discussion_time").toString();
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			try {
				java.util.Date dld = df.parse(dl);
				tasks[i].remove("discussion_time");
				tasks[i].put("discussion_time",dld);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return tasks;
	}

	

}
