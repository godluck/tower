package com.oliveryy.action;

import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
//BaseAction
public class BaseAction {
	public Map getRequest(){
		return (Map)ActionContext.getContext().get("request");
	}
	//to get request
	public HttpServletResponse  getResponse(){
		HttpServletResponse respone=ServletActionContext.getResponse();
		//加一个字符集，避免乱码
		respone.setContentType("text/html;charset=utf-8");
		return respone;
	}	
	//to get session
	public Map getSession(){
		return (Map)ActionContext.getContext().get("session");
	}
}
