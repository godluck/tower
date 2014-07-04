package com.oliveryy.service;

public interface ILoginService {
		/**
		 * 
		 * @param userName 从前台获取的用户名
		 * @param password 从前台获取的密码
		 * @param role 表示登录者的身份，0代表user，1代表组长，2代表管理员，3代表超级管理员
		 * @return 返回一个int值给action判断角色
		 * 		   	返回3跳转至user
		 * 		   	返回2跳转至leader
		 * 		   	返回1跳转至admin
		 * 		   	返回0跳转至superAdmin
		 * 			返回-1跳转至error
		 */
		public int canLogin(String userName, String password);

		/**
		 * 
		 * @param nickName 从前台获取昵称
		 * @param userName 从前台获取用户名
		 * @param password 从前台获取密码
		 */
		public boolean register(String nickName,String userName, String password);

}
