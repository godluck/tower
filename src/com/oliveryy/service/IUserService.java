package com.oliveryy.service;
/*
 * Author:oliver
 * */
//interface IUserService
public interface IUserService {
	public boolean login(String uid,String pwd);
	public boolean register(String id,String name,String pwd);
	public boolean registerCheckName(String name);
	public boolean isAdmin(String userId);
}
