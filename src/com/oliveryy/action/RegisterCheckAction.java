package com.oliveryy.action;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.oliveryy.service.IUserService;
//check register information
@Component 
public class RegisterCheckAction extends BaseAction{
	@Autowired
	private IUserService userService;
	
	private String r_name;
	public String execute() throws IOException{
		System.out.println("test");
		if(this.userService.registerCheckName(r_name)){
			System.out.println("test");
			this.getResponse().getWriter().print("exist");
		}
		return null;
	}
	public String getR_name() {
		return r_name;
	}
	public void setR_name(String r_name) {
		this.r_name = r_name;
	}




	

}
