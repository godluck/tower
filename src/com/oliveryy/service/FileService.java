package com.oliveryy.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.dao.IDbhelper;
@Component
public class FileService implements IFileService {
	@Autowired
	private IDbhelper dao;
	@Override
	public boolean upLoadFile(int groupId, String fileName,String fileDescription,String userId) {
		try{
			String sql="insert into file values(null,?,?,?,0,null,?,?)";
			Object[] params={groupId,fileName,fileName.substring(fileName.lastIndexOf(".")+1),fileDescription,userId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean upLoadReport(int groupId, String fileName, String fileType,String userId) {
		try{
			String sql="insert into file values(null,?,?,?,3,null,null,?)";
			Object[] params={groupId,fileName,fileName.substring(fileName.lastIndexOf(".")+1),userId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean score(String fileId,String score) {
		try{
			String sql="update file set file_score=? where file_id=?";
			Object[] params={fileId,score};
			String sql2="update file set file_status=2 where file_id=?";
			Object[] params2={fileId};
			dao.runUpdate(sql, params);
			dao.runUpdate(sql2, params2);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public boolean submit(String fileId) {
		try{
			String sql="update file set file_status=1 where file_id=?";
			Object[] params={fileId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

	@Override
	public Map[] getFileList(String groupId) {
		String sql="select * from file where group_id=?";
		Object[] params={groupId};
		return dao.runSelect(sql, params);
	}

	@Override
	public boolean upLoadPhoto(int groupId, String fileName, String fileType,String userId) {
		try{
			String sql="insert into file values(null,?,?,?,4,null,null,?)";
			Object[] params={groupId,fileName,fileName.substring(fileName.lastIndexOf(".")+1),userId};
			dao.runUpdate(sql, params);
			}catch(Exception e){
				return false;
			}
		return true;
	}

}
