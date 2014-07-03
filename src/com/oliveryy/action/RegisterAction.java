package com.oliveryy.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IUserService;
//RegisterAction for register function 
@Component 
public class RegisterAction extends BaseAction{
	@Autowired
	private IUserService userService;
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
		if(this.userService.register(this.id.toString(), this.name,this.pwd)){
			return "success";//
		}
		return null;
	}




	

}
