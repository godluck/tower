package service;
@Component
public class LoginService {
	@Autowired
	private IDbhelper dao;
	public int canLogin(String userName, String password){
		String sql="select role from user where user_id=? and user_password=?";
		Object[] params={userName,password};
		Map[] rows=dao.runSelect(sql,params);
		if(rows.length<=0)
	}

	/**
	 * 
	 * @param role 表示登录者的身份，0代表user，1代表组长，2代表管理员，3代表超级管理员
	 * 
	 */
	public String toWhichPage(int role);


	/**
	 * 
	 * @param nickName 从前台获取昵称
	 * @param userName 从前台获取用户名
	 * @param password 从前台获取密码
	 */
	public void register(String nickName,String userName, String password);

}
