package com.oliveryy.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
//BaseAction
public class BaseAction {
	public HttpServletRequest getRequest(){
		return ServletActionContext.getRequest();
	}
	//to get request
	public HttpServletResponse  getResponse(){
		HttpServletResponse respone=ServletActionContext.getResponse();
		
		respone.setContentType("text/html;charset=utf-8");
		return respone;
	}	
	//to get session
	public HttpSession getSession(){
		return getRequest().getSession();
	}
	public PrintWriter getWriter(){
		try {
			return getResponse().getWriter();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
}
