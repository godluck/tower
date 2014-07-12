package com.oliveryy.service;

import java.util.Map;

public interface IFileService {

	public boolean score(String fileId,String score);
	public boolean submit(String fileId);
	public Map[] getFileList(String groupId);
	public boolean upLoadFile(String id, String groupId, String fileName,String fileType, String userId);
	public boolean upLoadReport(String id, String groupId, String fileName,String fileType, String userId);
	public boolean upLoadPhoto(String id, String groupId, String fileName,String fileType, String userId);
	public String downLoadFile(String id);
}
