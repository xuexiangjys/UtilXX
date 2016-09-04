package dao;

import java.util.List;

import bean.BankCard;
import bean.HelperPay;

public interface HelperPayDao {

	/**
	 * 注册乐居宝账户
	 * @param bankCard
	 */
	public boolean Register(HelperPay helperPay);
	
	/**
	 * 乐居宝密码验证
	 * @param username 账户名
	 * @param password 密码
	 */
	public boolean CheckPay(String username,String password);
	
	/**
	 * 乐居宝零钱变动更新
	 * @param username 账户名
	 * @param moneyChange 金额变动
	 */
	public boolean UpdateMoneyChange(String username,double moneyChange);
	
	/**
	 * 乐居宝零钱更新
	 * @param username 账户名
	 * @param money 金额
	 */
	public boolean UpdateMoney(String username,double money);
	
	/**
	 * 获取乐居宝上的零钱金额
	 * @param username  账户名
	 */
	public double GetPayMoney(String username);
	
	/**
	 * 乐居宝密码修改
	 * @param username  账户名
	 * @param newpassword 新密码
	 */
	public boolean UpdatePassword(String username,String newpassword);
	
	/**
	 * 获取乐居宝信息
	 * @param username  账户名
	 */
	public HelperPay GetHelperPayInfoByUsername(String username);
	
	/**
	 * 增加银行卡绑定
	 * @param username  账户名
	 * @param cardid  绑定银行卡号
	 */
	public boolean AddBankCardBind(String username,String newCardid);
	
	/**
	 * 更新银行卡绑定信息
	 * @param username 账户名
	 * @param newbankcardlist 绑定银行卡集合
	 */
	public boolean UpdateBankcardlist(String username,String newbankcardlist);
	
	/**
	 * 获取乐居宝上的银行卡绑定信息
	 * @param username  账户名
	 */
	public String GetBankcardListStr(String username);
	
	/**
	 * 获取乐居宝上的银行卡绑定信息
	 * @param username  账户名
	 */
	public List<BankCard> GetBankcardList(String username);
	
}
