package com.oliveryy.service;

public interface ILoginService {
		/**
		 * 
		 * @param userName ��ǰ̨��ȡ���û���
		 * @param password ��ǰ̨��ȡ������
		 * @param role ��ʾ��¼�ߵ���ݣ�0����user��1�����鳤��2�������Ա��3����������Ա
		 * @return ����һ��intֵ��action�жϽ�ɫ
		 * 		   	����3��ת��user
		 * 		   	����2��ת��leader
		 * 		   	����1��ת��admin
		 * 		   	����0��ת��superAdmin
		 * 			����-1��ת��error
		 */
		public int canLogin(String userName, String password);

		/**
		 * 
		 * @param nickName ��ǰ̨��ȡ�ǳ�
		 * @param userName ��ǰ̨��ȡ�û���
		 * @param password ��ǰ̨��ȡ����
		 */
		public boolean register(String nickName,String userName, String password);

}
