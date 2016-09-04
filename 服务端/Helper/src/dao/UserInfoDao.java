package dao;

import bean.UserInfo;

public interface UserInfoDao {	
	/**
	 * 用户注册
	 * @param userbean
	 */
	public boolean Register(UserInfo userbean);

	/**
	 * 检测用户名是否存在
	 * @param loginname
	 * @return true : 该用户已存在,false : 该用户不存在，可以注册
	 */
	public boolean CheckUser(String loginname);
	/**
	 * 登陆检验
	 * @param userbean
	 */
	public boolean LoginCheck(String loginname,String password);
	
	/**
	 * 根据登录名获取用户个人基本信息
	 * @param loginname
	 */
	public UserInfo GetUserInfoByLoginname(String loginname);
	
	/**
	 * 头像上传路径修改
	 * @param loginname
	 * @param headphoto
	 */
	public boolean UpdateHeadPhoto(String loginname,String headphoto);
	
	/**
	 * 昵称修改
	 * @param loginname
	 * @param nickname
	 */
	public boolean UpdateNickName(String loginname,String nickname);
	
	/**
	 * 性别修改
	 * @param loginname
	 * @param sex
	 */
	public boolean UpdateSex(String loginname,String sex);
	
	/**
	 * 绑定手机修改
	 * @param loginname
	 * @param phone
	 */
	public boolean UpdatePhone(String loginname,String phone);
	
	/**
	 * 密码修改
	 * @param loginname
	 * @param password
	 */
	public boolean UpdatePassword(String loginname,String password);
	
	/**
	 * 个性签名修改
	 * @param loginname
	 * @param signature
	 */
	public boolean UpdateSignature(String loginname,String signature);

	/**
	 * 实名认证
	 * @param loginname 登录名
	 * @param realname 真实姓名
	 * @param identitycard 身份证号
	 */
	public boolean UpdateIdentificationStatus(String loginname,String realname,String identitycard);
}
