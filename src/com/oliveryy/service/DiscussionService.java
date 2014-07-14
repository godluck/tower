package com.oliveryy.service;

import java.sql.Date;
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
			Object[] params={userId, groupId,title, content,type};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean reply(String discussionId, String content,
			String userId, Date time) {
		try{
			String sql="insert into reply values(null,?,?,?,?)";
			Object[] params={discussionId, content,userId, content,time};
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
		return dao.runSelect(sql, params);
	}

	@Override
	public Map getDiscussion(String discussionId,String type) {
		String sql="select * from discussion where discussion_id=? and discussion_type=?";
		Object[] params={discussionId,type};
		return dao.runSelect(sql, params)[0];
	}

	@Override
	public Map[] getDiscussions(String groupId,String type) {
		String sql="select * from discussion where group_id=? and discussion_type=?";
		Object[] params={groupId,type};
		return dao.runSelect(sql, params);
	}

	

}
