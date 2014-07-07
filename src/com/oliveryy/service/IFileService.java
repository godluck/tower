package com.oliveryy.service;

import java.util.Map;

public interface IFileService {
	/**
	 * 
	 * @param groupId
	 * @param fileName
	 * @param fileType
	 * @return
	 */
	public boolean upLoadReport(int groupId, String fileName, String fileType,String userId);
	public boolean upLoadPhoto(int groupId, String fileName, String fileType,String userId);
	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean score(String fileId,String score);

	/**
	 * 
	 * @param fileName
	 * @return
	 */
	public boolean submit(String fileId);

	/**
	 * 
	 * @return
	 */
	public Map[] getFileList(String groupId);
	boolean upLoadFile(int groupId, String fileName,
			String fileDescription,String userId);

}
