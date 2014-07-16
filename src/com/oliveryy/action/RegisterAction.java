package com.oliveryy.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.ILoginService;
//RegisterAction for register function 
@Component 
public class RegisterAction extends BaseAction{
	@Autowired
	private ILoginService loginService;
	private String id;
	private String name;
	private String pwd;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String execute(){
		if(this.loginService.register(this.name,this.id,this.pwd)){
			try {
				getResponse().getWriter().write("{error:0}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}else{
			try {
				getResponse().getWriter().write("{error:1,reason:\"duplicate username\"}");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
	}




	

}
