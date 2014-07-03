package com.oliveryy.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IMessageService;
import com.oliveryy.service.IUserService;
  
@Component 
public class LoginAction extends BaseAction{
	@Autowired
	private IUserService userService;
	@Autowired
	private IMessageService MessageService;
	
	private String uid;//get uid
	private String pwd;//get pwd
	public String getUid() {
		return uid;
	}
	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	//default function to be execute
	@SuppressWarnings("unchecked")
	public String execute(){
		if(this.userService.login(this.uid, this.pwd)){//judge sign in or not
			if(this.userService.isAdmin(this.uid)){
				//if not,set session isAdmin to be 1
				this.getSession().put("isAdmin", 1);
				this.getSession().put("nickName", this.uid);
				this.getRequest().put("rows", this.MessageService.AdminMessage());
				this.getSession().put("userinfo", "系统管理员");
				return "success";//to go to index.jsp page
			}
			else{
				//if not,set session isAdmin to be 0
				this.getSession().put("isAdmin", 0);
				this.getSession().put("nickName", this.uid);
				this.getRequest().put("rows", this.MessageService.CommonMessage(this.uid));
				this.getSession().put("userinfo", this.uid+" 你好！");
				return "success";//to go to index.jsp page
			}
		}
		return "error";//sign in failed, return error.jsp page
	}




	

}
