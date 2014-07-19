package com.oliveryy.action;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.oliveryy.service.IFileService;
@Component
public class UploadAction extends BaseAction {

	private SmartUpload up = new SmartUpload();
	@Autowired
	private IFileService fileService;
	private String groupId;
	private String id;
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String excute() {
		id=getSession().getAttribute("id").toString();
		int num = 0;
		try {
			up.initialize(ServletActionContext.getPageContext()
					.getServletConfig(), getRequest(), getResponse());
			up.upload();
			for (int i = 0; i < up.getFiles().getCount(); i++) {
				com.jspsmart.upload.File f = up.getFiles().getFile(i);
				String fileName = f.getFileName();
				String fileType=f.getFileExt();
				SimpleDateFormat df=new SimpleDateFormat("yyyyMMddHHmmss");
				String fileId=df.format(new Date());
				num = up.save("/file/"+fileId+"."+fileType);
				fileService.upLoadFile(fileId, groupId, fileName,fileType ,id);
			}
			getWriter().write("{\"error\":0}");
			return null;
		} catch (Exception e) {
			getWriter().write("{\"error\":1,\"reason\":\"failed to upload\"}");
			e.printStackTrace();
			return null;
		}
	}
}
