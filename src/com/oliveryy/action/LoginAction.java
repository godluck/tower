package com.oliveryy.action;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.ILoginService;
import com.oliveryy.service.IUserService;

  
@Component 
public class LoginAction extends BaseAction{
	@Autowired
	private ILoginService loginService;
	@Autowired
	private IUserService userService;
	
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
		int p=loginService.canLogin(uid, pwd);
		if(p==-1){
			try {
				getResponse().getWriter().write("{error:1,reason:\"invalidate password\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}else if(p==-2){
			try {
				getResponse().getWriter().write("{error:1,reason:\"invalidate username\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}else if (p==0||p==1){
			getSession().setAttribute("id", uid);
			return "projrct";
		}else{
			getSession().setAttribute("id", uid);
			return "myself";
		}
	}

}
