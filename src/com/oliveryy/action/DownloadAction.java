package com.oliveryy.action;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jspsmart.upload.SmartUpload;
import com.oliveryy.service.IFileService;
@Component
public class DownloadAction extends BaseAction {
	private SmartUpload up = new SmartUpload();
	@Autowired
	private IFileService fileService;
	private String fileId;
	public String getFileId() {
		return fileId;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public String excute(){
		try {
			up.initialize(ServletActionContext.getPageContext()
					.getServletConfig(), getRequest(), getResponse());
			up.setContentDisposition(null);
			String fn=fileService.downLoadFile(fileId);
		    up.downloadFile("/file/"+fileId+fn.substring(fn.lastIndexOf(".")),"",fn);  
			return null;
		} catch (Exception e) {
			getWriter().write("unknown error");
			e.printStackTrace();
			return null;
		}
	}
}
