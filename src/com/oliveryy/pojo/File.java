package com.oliveryy.pojo;

public class File {
	private int groupId;
	private String fileName;
	private String fileType;
	private int fileStatus;
	private int fileScore;

	public File(String fileName, String fileType, int fileStatus) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileStatus = fileStatus;
	}

	public File(String fileName, String fileType, int fileStatus, int fileScore) {
		super();
		this.fileName = fileName;
		this.fileType = fileType;
		this.fileStatus = fileStatus;
		this.fileScore = fileScore;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public int getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(int fileStatus) {
		this.fileStatus = fileStatus;
	}

	public int getFileScore() {
		return fileScore;
	}

	public void setFileScore(int fileScore) {
		this.fileScore = fileScore;
	}

}
